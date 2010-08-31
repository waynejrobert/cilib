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
package net.cilib.algorithm;

import net.cilib.collection.Topology;
import net.cilib.entity.Entity;

/**
 * Algorithm that operates on a {@link Topology}.
 *
 * @since 0.8
 * @author gpampara
 */
//public interface PopulationBasedAlgorithm<A> extends Algorithm {
public interface PopulationBasedAlgorithm extends Algorithm {

    /**
     * Perform a loop of the population based algorithm. The iteration of the
     * algorithm will mutate the provided {@link Topology} instance.
     * @param topology the population for the algorithm to operate on
     * @return the given topology, post algorithm iteration.
     */
    Topology<Entity> iterate(Topology<Entity> topology);
}
