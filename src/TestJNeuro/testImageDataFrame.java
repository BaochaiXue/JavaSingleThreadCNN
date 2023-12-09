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

    @Test
    public void testCreateData() {
        ImageDataFrame imageDataFrame = null;
        try {
            imageDataFrame = new ImageDataFrame("testSupport/training");
        } catch (Exception e) {
            assertEquals("the label 9 is empty", e.getMessage());
        }
        imageDataFrame = new ImageDataFrame("testSupport/testing");
        double[][][] image = new double[1][28][28];
        for (int label = 0; label < 10; label++) {
            image = imageDataFrame.sampledFromLabel(label);
            assertEquals(1, image.length);
            assertEquals(28, image[0].length);
            assertEquals(28, image[0][0].length);
            image = imageDataFrame.sampledFromLabelIncrementally(label);
            assertEquals(1, image.length);
            assertEquals(28, image[0].length);
            assertEquals(28, image[0][0].length);
        }
    }

    @Test
    public void testLoadAndSliceEightDigitsImage() {
        double[][][] image = null;
        try {
            image = ImageDataFrame.loadAndSliceEightDigitsImage("testSupport/8digits.jpg");
        } catch (Exception e) {
            // there should be no exception
        }
        assertEquals(8, image.length);
        assertEquals(28, image[0].length);
        assertEquals(28, image[0][0].length);
    }

    @Test
    public void testCreateData3() {
        ImageDataFrame imageDataFrame = null;
        try {
            imageDataFrame = new ImageDataFrame("testSupport/training3");
        } catch (Exception e) {
            assertEquals("the label 0 is empty", e.getMessage());
        }
        imageDataFrame = new ImageDataFrame("testSupport/testing");
        double[][][] image = new double[1][28][28];
        for (int label = 0; label < 10; label++) {
            image = imageDataFrame.sampledFromLabel(label);
            assertEquals(1, image.length);
            assertEquals(28, image[0].length);
            assertEquals(28, image[0][0].length);
            image = imageDataFrame.sampledFromLabelIncrementally(label);
            assertEquals(1, image.length);
            assertEquals(28, image[0].length);
            assertEquals(28, image[0][0].length);
        }
    }
}