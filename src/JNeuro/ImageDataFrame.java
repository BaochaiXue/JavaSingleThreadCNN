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
    protected static class DigitalizedImage {
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
        if (listOfFiles == null) {
            throw new IOException("the folder { " + path + "/" + label + " } is not found");
        }
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
            if (this.data.get(i).size() == 0) {
                throw new RuntimeException("the label " + i + " is empty");
            }
            this.index.put(i, rand.nextInt(this.data.get(i).size()));
        }
    }

    public double[][][] sampledFromLabel(int label) throws RuntimeException {
        Random rand = new Random();
        int index = rand.nextInt(this.data.get(label).size());
        return this.data.get(label).get(index).getImage();
    }

    private Map<Integer, Integer> index;

    public double[][][] sampledFromLabelIncrementally(int label) throws RuntimeException {
        int index = this.index.get(label);
        this.index.put(label, (index + 1) % this.data.get(label).size());
        return this.data.get(label).get(index).getImage();
    }

    public static double[][][] loadAndSliceEightDigitsImage(String path) throws IOException {
        BufferedImage image = imageRead(path);
        BufferedImage scaledImage = new BufferedImage(224, 28, BufferedImage.TYPE_INT_RGB);
        scaledImage.getGraphics().drawImage(image, 0, 0, 224, 28, null); // scale the image
        double[][][] digitized = digitize(scaledImage);
        double[][][] result = new double[8][28][28];
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 224; j++) {
                result[j / 28][i][j % 28] = digitized[0][i][j];
            }
        }
        return result;
    }
}
