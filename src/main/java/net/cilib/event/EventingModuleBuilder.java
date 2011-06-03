/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.cilib.event;

import com.google.common.collect.Maps;
import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.util.Modules;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The {@code EventingModuleBuilder} is a builder that creates a
 * {@code Module} for a {@code Guice} {@code Injector} instance.
 * <p/>
 * <p/>
 * Per default, (unless disabled by the user), two events are explicitly
 * captured and processed:
 * <ul>
 * <li>Iteration events for iteration counting</li>
 * <li>Function evaluation events for evaluation counting</li>
 * </ul>
 * <p/>
 * Additional {@code Event} monitoring can be configured using the builder
 * methods and then finally creating the {@code Module} by using the
 * {@code build()} method.
 */
public final class EventingModuleBuilder {

    private Map<EventBinding, MethodInterceptor> interceptors = Maps.newHashMap();
    private boolean includeDefaultInterceptors = true;
    private final Module DEFAULT_EVENTS = new AbstractModule() {
        @Override
        protected void configure() {
            requireBinding(EventProcessor.class);

            bindInterceptor(Matchers.any(),
                    Events.raising(IterationEvent.class),
                    new IterationInterceptor(getProvider(EventProcessor.class)));
            bindInterceptor(Matchers.any(),
                    Events.raising(FitnessEvaluationEvent.class),
                    new FitnessEvaluationInterceptor(getProvider(EventProcessor.class)));
        }
    };

    public EventingModuleBuilder disableDefaults() {
        includeDefaultInterceptors = false;
        return this;
    }

    public EventingModuleBuilder intercept(Class<?> source, Class<? extends Event> eventType, MethodInterceptor interceptor) {
        interceptors.put(EventBinding.get(source, eventType), interceptor);
        return this;
    }

    public Module build() {
        final Module constructed = new AbstractModule() {
            @Override
            protected void configure() {
                install(new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(new TypeLiteral<Queue<Event>>(){}).to(new TypeLiteral<LinkedBlockingQueue<Event>>(){});
                    }
                });
                for (Map.Entry<EventBinding, MethodInterceptor> entry : interceptors.entrySet()) {
                    bindInterceptor(Events.on(entry.getKey().source),
                            Events.raising(entry.getKey().eventType),
                            entry.getValue());
                }
            }
        };

        return includeDefaultInterceptors ? Modules.combine(new AbstractModule() {
            @Override
            protected void configure() {
                bind(new TypeLiteral<Queue<Event>>(){}).to(new TypeLiteral<LinkedBlockingQueue<Event>>(){});
            }
        }, DEFAULT_EVENTS, constructed) : constructed;
    }

    private static class EventBinding {

        private final Class<?> source;
        private final Class<? extends Event> eventType;

        static EventBinding get(Class<?> source, Class<? extends Event> eventType) {
            return new EventBinding(source, eventType);
        }

        private EventBinding(Class<?> source, Class<? extends Event> eventType) {
            this.source = source;
            this.eventType = eventType;
        }
    }

    public static class IterationInterceptor implements MethodInterceptor {
        private final Provider<EventProcessor> processorProvider;

        @Inject
        IterationInterceptor(Provider<EventProcessor> eventProcessor) {
            processorProvider = eventProcessor;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            EventProcessor processor = processorProvider.get();
            System.out.println("iteration start");
            processor.process(IterationEvent.START);
            Object result = invocation.proceed();
            processor.process(IterationEvent.STOP);
            return result;
        }
    }

    private static class FitnessEvaluationInterceptor implements MethodInterceptor {
        private final Provider<EventProcessor> processorProvider;

        @Inject
        FitnessEvaluationInterceptor(Provider<EventProcessor> eventProcessor) {
            this.processorProvider = eventProcessor;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            EventProcessor processor = processorProvider.get();
            processor.process(FitnessEvaluationEvent.START);
            Object result = invocation.proceed();
            processor.process(FitnessEvaluationEvent.STOP);
            return result;
        }
    }
}