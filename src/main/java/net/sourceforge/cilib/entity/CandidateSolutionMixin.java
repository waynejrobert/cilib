/*
 * CandidateSolutionMixin.java
 *
 * Copyright (C) 2003 - 2008
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.entity;

import net.sourceforge.cilib.problem.Fitness;
import net.sourceforge.cilib.type.types.Blackboard;
import net.sourceforge.cilib.type.types.Type;

/**
 * <p>
 * A <code>CandidateSolution</code> is a potential solution within an optimisation.
 *
 * <p>
 * <code>CandidateSolution</code> is a base class that all concrete <code>Entity</code>
 * instances inherit from. All <code>Entity</code> objects have their respective contents
 * represented and maintained within the <code>Blackboard</code> as defined by the
 * <code>CandidateSolution</code>.
 */
public class CandidateSolutionMixin implements CandidateSolution {
	private static final long serialVersionUID = 4539668687773346284L;
	private final Blackboard<String, Type> properties;

	/**
	 * Create the Mixin class, providing the reference to the shared {@linkplain Blackboard}
	 * data structure.
	 * @param properties The shared {@linkplain Blackboard}
	 */
	public CandidateSolutionMixin(Blackboard<String, Type> properties) {
		this.properties = properties;
	}

	/**
	 * Instantiate a new instance, based on the provided <code>CandidateSolutionMixin</code>.
	 * This is a shallow copy instantiation.
	 * @param copy The template object to copy.
	 */
	public CandidateSolutionMixin(CandidateSolutionMixin copy) {
		this.properties = copy.properties;
	}

	/**
	 * Get a clone of the current {@linkplain CandidateSolutionMixin}.
	 * @return A shallow clone of the current object - The {@linkplain Blackboard} is not cloned.
	 */
	public CandidateSolutionMixin getClone() {
		return new CandidateSolutionMixin(this);
	}

	/**
	 * Get the associated candidate solution representation that the current {@linkplain Entity}
	 * represents.
	 * @return A {@linkplain Type} representing the solution of the {@linkplain Entity}
	 */
	public Type getContents() {
		return properties.get("content");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setContents(Type content) {
		properties.put("content", content);
	}

	/**
	 * {@inheritDoc}
	 */
	public Fitness getFitness() {
		return (Fitness) properties.get("fitness");
	}

}