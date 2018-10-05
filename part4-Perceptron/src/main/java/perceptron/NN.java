package perceptron;

import java.util.List;
import java.util.Random;

public class NN {
    private final List<Image> images;

    public NN(List<Image> images) {
        this.images = images;
    }
 
    public int classify(double[] img) {
        Random rand = new Random();
        double minD=Integer.MAX_VALUE;
        int res=0;
        for (int i=0;i<5000;i++) {
            double[] x=images.get(i).vec;
            double distance=0;
            for (int j = 0; j < x.length; j++) {
                distance+=Math.pow(x[j]-img[j], 2);
            }
            distance=Math.sqrt(distance);
            if(distance<minD){
                minD=distance;
                res=images.get(i).characterClass;
            }
        }
        return res;
    }

    double test() {
        int success = 0;
        int trials = 0;
        for (int example = 5000; example < (int) images.size(); example++) {
            int classification=classify(images.get(example).vec);
            if (images.get(example).characterClass == classification) {
                success++;
            }
            trials++;
        }

        return (double) (trials - success) / trials;
    }
}
