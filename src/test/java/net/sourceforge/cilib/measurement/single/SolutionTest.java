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
package net.sourceforge.cilib.measurement.single;

import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.problem.InferiorFitness;
import net.sourceforge.cilib.problem.OptimisationSolution;
import net.sourceforge.cilib.type.parser.DomainParser;
import net.sourceforge.cilib.type.types.StringType;
import net.sourceforge.cilib.type.types.container.TypeList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Gary Pampara
 */
@RunWith(JMock.class)
public class SolutionTest {
    private Mockery mockery = new JUnit4Mockery();

    @Test
    public void result() {
        final Algorithm algorithm = mockery.mock(Algorithm.class);
        final OptimisationSolution mockSolution = new OptimisationSolution(Vector.of(1.0), InferiorFitness.instance());

        mockery.checking(new Expectations() {{
            oneOf(algorithm).getBestSolution(); will(returnValue(mockSolution));
        }});

        Measurement m = new Solution();
        Assert.assertEquals(m.getValue(algorithm).toString(), mockSolution.getPosition().toString());
    }

    @Test
    public void testSolutionDomain() {
        Measurement m = new Solution();

        TypeList vector = (TypeList) DomainParser.parse(m.getDomain());

        assertEquals(1, vector.size());
        assertTrue(vector.get(0) instanceof StringType);
    }

}
