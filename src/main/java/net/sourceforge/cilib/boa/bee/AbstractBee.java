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
package net.sourceforge.cilib.boa.bee;

import net.sourceforge.cilib.boa.positionupdatestrategies.BeePositionUpdateStrategy;
import net.sourceforge.cilib.boa.positionupdatestrategies.VisualPositionUpdateStategy;
import net.sourceforge.cilib.entity.AbstractEntity;
import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.problem.InferiorFitness;
import net.sourceforge.cilib.problem.OptimisationProblem;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.util.selection.recipes.RandomSelector;
import net.sourceforge.cilib.util.selection.recipes.Selector;

/**
 * The entity class for the ABC algorithm that represents the bees.
 * @author Andrich
 *
 */
public abstract class AbstractBee extends AbstractEntity implements HoneyBee {

    private static final long serialVersionUID = 7005546673802814268L;
    protected BeePositionUpdateStrategy positionUpdateStrategy;
    protected Selector<HoneyBee> targetSelectionStrategy;
    protected int dimension;

    /**
     * Default constructor. Defines reasonable defaults for common members.
     */
    public AbstractBee() {
        this.positionUpdateStrategy = new VisualPositionUpdateStategy();
        this.targetSelectionStrategy = new RandomSelector();
    }

    /**
     * Copy constructor. Create a copy of the provided instance.
     * @param copy the reference of the bee that is deep copied.
     */
    public AbstractBee(AbstractBee copy) {
        super(copy);
        this.positionUpdateStrategy = copy.positionUpdateStrategy;
        this.targetSelectionStrategy = copy.targetSelectionStrategy;
        this.dimension = copy.dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract AbstractBee getClone();

    /**
     * {@inheritDoc}
     */
    @Override
    public BeePositionUpdateStrategy getPositionUpdateStrategy() {
        return this.positionUpdateStrategy;
    }

    /**
     * Sets the position update strategy of the bee.
     * @param positionUpdateStrategy the new position update strategy.
     */
    public void setPositionUpdateStrategy(BeePositionUpdateStrategy positionUpdateStrategy) {
        this.positionUpdateStrategy = positionUpdateStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updatePosition();

    /**
     * {@inheritDoc}
     */
    @Override
    public void calculateFitness() {
        this.getProperties().put(EntityType.FITNESS, getFitnessCalculator().getFitness(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Entity o) {
        return getFitness().compareTo(o.getFitness());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Sets the dimension of the solution used by the bee.
     * @param dimension the new dimension of the solution.
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * {@inheritDoc}
     */
    public Vector getPosition() {
        return (Vector) this.getCandidateSolution();
    }

    /**
     * {@inheritDoc}
     */
    public void setPosition(Vector position) {
        this.setCandidateSolution(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise(OptimisationProblem problem) {
        Vector candidate = Vector.newBuilder().copyOf(problem.getDomain().getBuiltRepresenation()).buildRandom();
        this.setCandidateSolution(candidate);
        this.dimension = candidate.size();
        this.getProperties().put(EntityType.FITNESS, InferiorFitness.instance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reinitialise() {
        throw new UnsupportedOperationException("Reinitialise not implemented for AbstractBee");
    }

    /**
     * Gets the target selection strategy, for selecting bees to follow in position updates.
     * @return the target selection strategy.
     */
    public Selector getTargetSelectionStrategy() {
        return targetSelectionStrategy;
    }

    /**
     * Sets the target selection strategy, for selecting bees to follow in position updates.
     * @param targetSelectionStrategy  the new target selection strategy.
     */
    public void setTargetSelectionStrategy(Selector targetSelectionStrategy) {
        this.targetSelectionStrategy = targetSelectionStrategy;
    }
}
