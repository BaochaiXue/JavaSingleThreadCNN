
package JNeuro;

import java.io.IOException;

public class JNeuro {

    private static void train(int training_size, String pathOfReadInData, String pathOfWeight) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork();
        ImageDataFrame trainningData = new ImageDataFrame(pathOfReadInData);
        cnn.train(training_size, trainningData, pathOfWeight);
    }

    private static void test(int test_size, String pathOfReadInData, String pathOfWeight) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork();
        ImageDataFrame testData = new ImageDataFrame(pathOfReadInData);
        cnn.test(test_size, testData, pathOfWeight);
    }

    public static void main(String[] args) throws IOException {
        train(3000, "data/training", "weight");
        test(100, "data/training", "weight");
    }

}
