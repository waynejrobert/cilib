/**
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
package net.sourceforge.cilib.util.selection.weighing.entity;

import java.util.Arrays;
import java.util.List;
import net.sourceforge.cilib.ec.Individual;
import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.problem.Fitness;
import net.sourceforge.cilib.problem.MaximisationFitness;
import net.sourceforge.cilib.problem.MinimisationFitness;
import net.sourceforge.cilib.util.selection.Selection;
import net.sourceforge.cilib.util.selection.Selection.Entry;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wiehann Matthysen
 */
public class EntityWeighingTest {

    @Test
    public void entityWeighingMaximise() {
        Individual i1 = createIndividual(new MaximisationFitness(1.0));
        Individual i2 = createIndividual(new MaximisationFitness(2.0));
        Individual i3 = createIndividual(new MaximisationFitness(3.0));

        List<Individual> individuals = Arrays.asList(i1, i2, i3);
        List<Selection.Entry<Individual>> entries = Selection.from(individuals).entries();
        EntityWeighing<Individual> weighing = new EntityWeighing<Individual>();
        weighing.weigh(entries);

        Assert.assertEquals(0.0, entries.get(0).getWeight(), 0.0001);
        Assert.assertEquals(0.5, entries.get(1).getWeight(), 0.0001);
        Assert.assertEquals(1.0, entries.get(2).getWeight(), 0.0001);
    }

    @Test
    public void entityWeighingMinimise() {
        Individual i1 = createIndividual(new MinimisationFitness(1.0));
        Individual i2 = createIndividual(new MinimisationFitness(2.0));
        Individual i3 = createIndividual(new MinimisationFitness(3.0));

        List<Individual> individuals = Arrays.asList(i1, i2, i3);
        List<Selection.Entry<Individual>> entries = Selection.from(individuals).entries();
        EntityWeighing<Individual> weighing = new EntityWeighing<Individual>();
        weighing.weigh(entries);

        Assert.assertEquals(1.0, entries.get(0).getWeight(), 0.0001);
        Assert.assertEquals(0.5, entries.get(1).getWeight(), 0.0001);
        Assert.assertEquals(0.0, entries.get(2).getWeight(), 0.0001);
    }

    private Individual createIndividual(Fitness fitness) {
        Individual i = new Individual();
        i.getProperties().put(EntityType.FITNESS, fitness);
        return i;
    }
}
