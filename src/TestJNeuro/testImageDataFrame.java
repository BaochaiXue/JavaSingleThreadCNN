package TestJNeuro;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import JNeuro.ImageDataFrame;

public class testImageDataFrame {
    private class StubImageDataFrame extends ImageDataFrame {
        public StubImageDataFrame() {
            super("data/trainning");
        }

        public static double[][][] getDigitalizedImageImage(double[][][] image, int label) {
            ImageDataFrame.DigitalizedImage digitalizedImage = new ImageDataFrame.DigitalizedImage(image, label);
            return digitalizedImage.getImage();
        }

        public static int getDigitalizedImageLabel(double[][][] image, int label) {
            ImageDataFrame.DigitalizedImage digitalizedImage = new ImageDataFrame.DigitalizedImage(image, label);
            return digitalizedImage.getLabel();
        }
    }

    @Test
    public void testDigitalizedImage() {
        double[][][] image = new double[1][28][28];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                for (int k = 0; k < image[0][0].length; k++) {
                    image[i][j][k] = 1;
                }
            }
        }
        double[][][] digitalizedImage = StubImageDataFrame.getDigitalizedImageImage(image, 0);
        assertEquals(1, digitalizedImage.length);
        assertEquals(28, digitalizedImage[0].length);
        assertEquals(28, digitalizedImage[0][0].length);
        assertArrayEquals(image, digitalizedImage);
        int label = StubImageDataFrame.getDigitalizedImageLabel(image, 0);
        assertEquals(0, label);
    }

}
