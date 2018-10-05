package perceptron;

import java.util.List;
import java.util.Random;

public class Perceptron {

    private final List<Image> images;

    public Perceptron(List<Image> images) {
        this.images = images;
    }

    /**
     * Fill in the perceptron implementation here.
     *
     * @param targetChar
     * @param oppositeChar
     * @param steps
     * @return
     */
    public double[] train(int targetChar, int oppositeChar, int steps) {
        Random rand = new Random();
        double[] w = new double[28 * 28];

        for (int step = 0; step < steps; ) {
            int example = rand.nextInt(5000); // pick random example

            // only care about the two classes
            if (images.get(example).characterClass != targetChar
                    && images.get(example).characterClass != oppositeChar) {
                continue;
            }
            step++;
            double[] x=images.get(example).vec;
            double z = 0;
            for (int i = 0; i < w.length; i++) {
                z+=w[i]*x[i];
            }
            if(z>=0 && images.get(example).characterClass==oppositeChar){
                for (int i = 0; i < w.length; i++) {
                    w[i]-=x[i];
                }
            }
            if(z<0 && images.get(example).characterClass==targetChar){
                for (int i = 0; i < w.length; i++) {
                    w[i]+=x[i];
                }
            }
        }
        return w;
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
    double test(double[] weights, int targetChar, int oppositeChar) {
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
