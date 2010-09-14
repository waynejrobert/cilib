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
package net.cilib.entity;

import com.google.inject.Inject;
import java.util.Comparator;

/**
 *
 * @author gpampara
 */
public class Particle implements Entity, HasVelocity, HasMemory {

    private final CandidateSolution position;
    private final CandidateSolution bestPosition;
    private final Velocity velocity;
    private final Fitness fitness;

    /**
     *
     * @param position
     * @param bestPosition
     * @param velocity
     * @param fitness
     */
    @Inject
    public Particle(CandidateSolution position,
        CandidateSolution bestPosition,
        Velocity velocity,
        Fitness fitness) {
        this.position = position;
        this.bestPosition = bestPosition;
        this.velocity = velocity;
        this.fitness = fitness;
    }

    @Override
    public CandidateSolution solution() {
        return position;
    }

    @Override
    public int size() {
        return position.size();
    }

    @Override
    public Fitness fitness() {
        return fitness;
    }

    @Override
    public Entity moreFit(Entity that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entity moreFit(Entity that, Comparator<? super Entity> comparator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entity lessFit(Entity that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Entity lessFit(Entity that, Comparator<? super Entity> comparator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equiv(Entity that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(Entity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Velocity velocity() {
        return velocity;
    }

    @Override
    public CandidateSolution memory() {
        return bestPosition;
    }
//    public ParticleBuilder newBuilder() {
//        return new ParticleBuilder(this);
//    }
//
//    public static class ParticleBuilder implements ParticleProvider<Particle> {
//
//        private final CandidateSolution previousPosition;
//        private final CandidateSolution previousBestPosition;
//        private final double[] internalPosition;
//        private final double[] internalVelocity;
//
//        private ParticleBuilder(Particle particle) {
//            internalPosition = particle.position.toArray();
//            internalVelocity = particle.velocity.toArray();
//            previousPosition = particle.solution();
//            previousBestPosition = particle.memory();
//        }
//
//        public ParticleBuilder withVelocity(Velocity velocity) {
//            final double[] velocityArray = velocity.toArray();
//            System.arraycopy(velocityArray, 0, internalVelocity, 0, velocityArray.length);
//            return this;
//        }
//
//        public ParticleBuilder withPosition(MutableSeq newPosition) {
//            final double[] pos = newPosition.toArray();
//            System.arraycopy(pos, 0, internalPosition, 0, pos.length);
//            return this;
//        }
//
//        @Override
//        public Particle get() {
//            return new Particle(CandidateSolution.copyOf(internalPosition),
//                previousBestPosition,
//                Velocity.copyOf(internalVelocity),
//                Fitnesses.inferior());
//        }
//    }
}