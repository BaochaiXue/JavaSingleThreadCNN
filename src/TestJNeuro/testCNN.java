package TestJNeuro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import JNeuro.ConvolutionalNeuralNetwork;
import JNeuro.ImageDataFrame;

public class testCNN {
    @Test
    public void testTrainning() {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(true, "testSupport/testCNN");
        try {
            cnn.train(1100, new ImageDataFrame("testSupport/training2"));
        } catch (Exception e) {
            // the exception is not expected
            assert false;
        }
        // see if the weight file is created
        java.io.File file = new java.io.File("testSupport/testCNN");
        assertEquals(true, file.exists());
        for (int i = 0; i < 3; i++) {
            file = new java.io.File("testSupport/testCNN" + "/layer" + i);
            assertEquals(true, file.exists());
        }
        cnn = new ConvolutionalNeuralNetwork(false, "testSupport/testCNN");
        try {
            cnn.train(1100, new ImageDataFrame("testSupport/training2"));
        } catch (Exception e) {
            // the exception is not expected
            assert false;
        }
        // see if the weight file is saved
        file = new java.io.File("testSupport/testCNN");
        assertEquals(true, file.exists());
        for (int i = 0; i < 3; i++) {
            file = new java.io.File("testSupport/testCNN" + "/layer" + i);
            assertEquals(true, file.exists());
        }
    }

    @Test
    public void testTesting() {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(false, "testSupport/testWeight");
        try {
            cnn.test(1100, new ImageDataFrame("testSupport/testing"));
        } catch (Exception e) {
            // the exception is not expected
            assert false;
        }

    }

    @Test
    public void testPredict() {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(false, "testSupport/testWeight");
        double[][][] image = new double[1][28][28];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                for (int k = 0; k < image[0][0].length; k++) {
                    image[i][j][k] = 1;
                }
            }
        }
        int label = cnn.predict(image);
        assertEquals(true, label >= 0 && label <= 9);
    }

}
