
package JNeuro;

import java.io.IOException;

public class JNeuro {

    private static void train(int training_size, String pathOfReadInData, String pathOfWeight) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(false, pathOfWeight);
        ImageDataFrame trainningData = new ImageDataFrame(pathOfReadInData);
        cnn.train(training_size, trainningData);
    }

    private static void test(int test_size, String pathOfReadInData, String pathOfWeight) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(false, pathOfWeight);
        ImageDataFrame testData = new ImageDataFrame(pathOfReadInData);
        cnn.test(test_size, testData);
    }

    public static void main(String[] args) throws IOException {
        train(300000, "data/training", "weight");
        test(1000, "data/training", "weight");
    }

}
