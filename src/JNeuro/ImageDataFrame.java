package JNeuro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageDataFrame {
    protected class DigitalizedImage {
        private double[][][] image;
        private int label;

        public DigitalizedImage(double[][][] image, int label) {
            this.image = image;
            this.label = label;
        }

        public double[][][] getImage() {
            return image;
        }

        public int getLabel() {
            return label;
        }
    }

    private Map<Integer, ArrayList<DigitalizedImage>> data;

    private static BufferedImage imageRead(String path) throws IOException {
        return ImageIO.read(new File(path));
    }

    private static double[][][] digitize(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);// get pixels
        double[][][] digitized = new double[1][height][width];
        int column = 0;
        int row = 0;
        for (int position = 0; position < pixels.length; position++) {
            digitized[0][row][column] = (((int) pixels[position] >> 16 & 0xff)) / 255.0;
            column++;
            if (column == width) {
                column = 0;
                row++;
            }
        }
        return digitized;
    }

    private static BufferedImage[] loadAllImages(String path, int label) throws IOException {
        File folder = new File(path + "/" + label);
        File[] listOfFiles = folder.listFiles();
        // erase the file that is not a image
        for (int i = 0; i < listOfFiles.length; i++) {
            if (!listOfFiles[i].getName().endsWith(".png")) {
                listOfFiles[i].delete();
            }
        }
        listOfFiles = folder.listFiles();
        BufferedImage[] images = new BufferedImage[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            images[i] = imageRead(path + "/" + label + "/" + listOfFiles[i].getName());
            if (images[i] == null) {
                throw new IOException(
                        "the image file { " + path + "/" + label + "/" + listOfFiles[i].getName() + " } is not found");
            }
        }
        return images;
    }

    public ImageDataFrame(String path) {
        this.data = new HashMap<Integer, ArrayList<DigitalizedImage>>();
        this.index = new HashMap<Integer, Integer>();
        for (int i = 0; i < 10; i++) {
            this.data.put(i, new ArrayList<DigitalizedImage>());
            this.index.put(i, 0);
        }
        for (int i = 0; i < 10; i++) {
            try {
                BufferedImage[] images = loadAllImages(path, i);
                for (int j = 0; j < images.length; j++) {
                    this.data.get(i).add(new DigitalizedImage(digitize(images[j]), i));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        // randomly give the index a start value
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            this.index.put(i, rand.nextInt(this.data.get(i).size()));
        }
    }

    public double[][][] sampledFromLabel(int label) throws RuntimeException {
        Random rand = new Random();
        // check empty
        if (this.data.get(label).size() == 0) {
            throw new RuntimeException("the label " + label + " is empty");
        }
        int index = rand.nextInt(this.data.get(label).size());
        // check if the image is null
        while (this.data.get(label).get(index).getImage() == null) {
            index = rand.nextInt(this.data.get(label).size());
        }
        return this.data.get(label).get(index).getImage();
    }

    private Map<Integer, Integer> index;

    public double[][][] sampledFromLabelIncrementally(int label) throws RuntimeException {
        if (this.data.get(label).size() == 0) {
            throw new RuntimeException("the label " + label + " is empty");
        }
        int index = this.index.get(label);
        // check if the image is null
        if (this.data.get(label).get(index).getImage() == null) {
            throw new RuntimeException("the image is null");
        }
        this.index.put(label, (index + 1) % this.data.get(label).size());
        return this.data.get(label).get(index).getImage();
    }
}
