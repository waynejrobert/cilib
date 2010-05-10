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
package net.sourceforge.cilib.functions.continuous.dynamic;

import net.sourceforge.cilib.functions.ContinuousFunction;

public abstract class DynamicFunction implements ContinuousFunction {

    protected double dynamicFactor_w1; //how fast the minimum's position moves
    protected double dynamicFactor_w2; //how fast the minimum grows/reduces
    protected double initialPosition = 0.0;
    protected double initialHeight = 0.0;

    /**
     * @return the dynamicFactor
     */
    public double getDynamicFactor_w1() {
        return dynamicFactor_w1;
    }

    /**
     * @param dynamicFactor the dynamicFactor to set
     */
    public void setDynamicFactor_w1(double dynamicFactor) {
        this.dynamicFactor_w1 = dynamicFactor;
    }

    /**
     * @return the dynamicFactor
     */
    public double getDynamicFactor_w2() {
        return dynamicFactor_w2;
    }

    /**
     * @param dynamicFactor the dynamicFactor to set
     */
    public void setDynamicFactor_w2(double dynamicFactor) {
        this.dynamicFactor_w2 = dynamicFactor;
    }

    public double getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(double initial_position) {
        this.initialPosition = initial_position;
    }

    public double getInitialHeight() {
        return initialHeight;
    }

    public void setInitialHeight(double initial_height) {
        this.initialHeight = initial_height;
    }
}
