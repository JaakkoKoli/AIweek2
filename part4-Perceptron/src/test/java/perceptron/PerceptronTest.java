package perceptron;

import fi.helsinki.cs.tmc.edutestutils.Points;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static perceptron.Application.readImages;

@Points("Perceptron")
public class PerceptronTest {
    @Test
    public void classificationAccuracyIsGoodEnough() {
        int steps = 100;
        int targetChar = 3;
        int oppositeChar = 5;
        List<Image> images = readImages();

        Perceptron perceptron = new Perceptron(images);
        double[] w = perceptron.train(targetChar, oppositeChar, steps);
        double failureRate = test(w, images, targetChar, oppositeChar);
        assertTrue(
                "Your algorithm was not good enough. Try to get the failure rate to under 20%.",
                failureRate < 0.2);
    }


    /**
     * Tests the learned perceptron (with weight vector weights) with the last 1000 x,y pairs. (Note that
     * this only counts those ones that belong either to the plus or minus classes.
     *
     * @param weights
     * @param targetChar
     * @param oppositeChar
     * @return ratio of failures
     */
    double test(double[] weights, List<Image> images, int targetChar, int oppositeChar) {
        int success = 0;
        int trials = 0;

        for (int example = 5000; example < (int) images.size(); example++) {

            // choosing only from the + and - classes
            if (images.get(example).characterClass != targetChar
                    && images.get(example).characterClass != oppositeChar) {
                continue;
            }

            // calculate z
            double z = 0.0;
            for (int j = 0; j < 28 * 28; j++) {
                z += images.get(example).vec[j] * weights[j];
            }

            // Is the classification correct=
            if ((z >= 0 && images.get(example).characterClass == targetChar)
                    || (z < 0 && images.get(example).characterClass == oppositeChar)) {
                success++;
            }
            trials++;
        }

        return (double) (trials - success) / trials;
    }
}
