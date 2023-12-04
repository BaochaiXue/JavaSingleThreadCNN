
package JNeuro;

import java.io.IOException;

public class JNeuro {

    private static void train(int training_size, String path) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork();
        ImageDataFrame trainningData = new ImageDataFrame(path);
        cnn.train(training_size, trainningData);
    }

    private static void test(int test_size, String path) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork();
        ImageDataFrame testData = new ImageDataFrame(path);
        cnn.test(test_size, testData);
    }

    public static void main(String[] args) throws IOException {
        train(300000, "data/training");
        test(100, "data/training");
    }

}
