package JNeuro;

import java.io.IOException;

public class JNeuro {

    private static void train(int training_size, String pathOfReadInData, String pathOfWeight, boolean isStartNewly)
            throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(isStartNewly, pathOfWeight);
        ImageDataFrame trainningData = new ImageDataFrame(pathOfReadInData);
        cnn.train(training_size, trainningData);
    }

    private static void test(int test_size, String pathOfReadInData, String pathOfWeight) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(false, pathOfWeight);
        ImageDataFrame testData = new ImageDataFrame(pathOfReadInData);
        cnn.test(test_size, testData);
    }

    public static void main(String[] args) throws IOException {
        train(300000, "data/training", "weight", false);
        test(1000, "data/training", "weight");
        System.out.println(recoginizeImage("data/0.jpg", "weight"));
    }

    public static String recoginizeImage(String pathOfTheImage, String pathOfTheWeight) throws IOException {
        ConvolutionalNeuralNetwork cnn = new ConvolutionalNeuralNetwork(false, pathOfTheWeight);
        double[][][] image = ImageDataFrame.loadAndSliceEightDigitsImage(pathOfTheImage);
        String resultString = "";
        for (int i = 0; i < 8; i++) {
            double[][][] digit = new double[1][28][28];
            for (int j = 0; j < 28; j++) {
                for (int k = 0; k < 28; k++) {
                    digit[0][j][k] = image[i][j][k];
                }
            }
            int result = cnn.predict(digit);
            resultString += result;
        }
        return resultString;
    }

}
