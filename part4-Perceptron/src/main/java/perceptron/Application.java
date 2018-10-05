package perceptron;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {

    /**
     * Implement the perceptron algorithm in the Perceptron class. After that you can try out the
     * values of different number pairs by changing the values of the 'targetChar' and
     * 'oppositeChar' variables.
     */
    public static void main(String[] args) {
        int steps = 100;

        if (args.length > 0) {
            steps = Integer.parseInt(args[0]);
        }

        int targetChar = 3; // tämä on plus-luokka
        int oppositeChar = 5; // tämä on miinus-luokka

        System.out.println("Learning to classify " + targetChar + " vs " + oppositeChar);
        List<Image> images = readImages();
        testInput(images); // creates the test image to the project root. (test100.bmp)

        Perceptron perceptron = new Perceptron(images);
        System.out.print("Perceptron learning algorithm, " + steps + " iterations...");
        double[] w = perceptron.train(targetChar, oppositeChar, steps);
        visualizeWeights(w); // Saves the image to project root (weights.bmp)
        System.out.println(" complete");
        System.out.println(
                "Failure rate: " + 100 * perceptron.test(w, targetChar, oppositeChar) + " %");
        NN nn=new NN(images);
        System.out.println("NN failure rate: "+nn.test());
    }

    // reads x- and y-files
    static List<Image> readImages() {
        String xfilename = "mnist-x.data";
        String yfilename = "mnist-y.data";
        List<Image> images = new ArrayList<Image>();
        try {
            File xfile =
                    new File(Perceptron.class.getClassLoader().getResource(xfilename).getFile());
            File yfile =
                    new File(Perceptron.class.getClassLoader().getResource(yfilename).getFile());
            // Scanner xscanner = new Scanner(new File(xfilename));
            // Scanner yscanner = new Scanner(new File(yfilename));
            Scanner xscanner = new Scanner(xfile);
            Scanner yscanner = new Scanner(yfile);
            while (xscanner.hasNextLine()) {
                Image i = new Image();
                String line = xscanner.nextLine();
                int characterClass = yscanner.nextInt();
                String splitarr[] = line.split(",");
                i.vec = new double[28 * 28];
                int j = 0;
                for (String s : splitarr) {
                    if (s.equals("1")) {
                        i.vec[j++] = 1.0;
                    } else {
                        i.vec[j++] = -1.0;
                    }
                }
                i.characterClass = characterClass;
                images.add(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return images;
    }

    /**
     * Takes 100 first ones and sorts by character class. After that it draws a big picture that can
     * be used for checking that the pictures representing the same number come after one another.
     *
     * @param images
     */
    static void testInput(List<Image> images) {

        List<Image> i100 = new ArrayList<Image>();
        for (int i = 0; i < 100; ++i) {
            i100.add(images.get(i));
        }

        Collections.sort(i100);

        BufferedImage bi = new BufferedImage(28 * 100, 28, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < 100; ++i) {
            for (int y = 0; y < 28; ++y) {
                for (int x = 0; x < 28; ++x) {
                    int ind = y * 28 + x;
                    bi.setRGB(x + i * 28, y, (i100.get(i).vec[ind] > 0.5 ? 0 : 0xffffff));
                }
            }
        }
        try {
            ImageIO.write(bi, "BMP", new File("test100.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws a 28x28 grayscale picture of the weights
     *
     * @param w
     */
    static void visualizeWeights(double[] w) {

        BufferedImage bi = new BufferedImage(28, 28, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = 0; y < 28; ++y) {
            for (int x = 0; x < 28; ++x) {
                int ind = y * 28 + x;
                float w01 = .01f + .98f / (1.0f + (float) Math.exp(-w[ind]));
                bi.setRGB(x, y, (new Color(w01, w01, w01)).getRGB());
            }
        }
        try {
            ImageIO.write(bi, "BMP", new File("weights.bmp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

};
