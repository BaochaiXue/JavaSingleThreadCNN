package JNeuro;

import java.io.IOException;
import java.util.ArrayList;

public class ConvolutionalNeuralNetwork {
    private ArrayList<Layer> layers = new ArrayList<Layer>();

    private double[][][] forward(double[][][] input) {
        double[][][] result = input;
        for (Layer layer : this.layers) {
            result = layer.forward(result);
        }
        return result;
    }

    private double[][][] backPropagation(double[][][] back, double learning_rate) {
        double[][][] result = back;
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            result = this.layers.get(i).backPropagation(result, learning_rate);
        }
        return result;
    }

    private void save(String path) {
        for (int i = 0; i < this.layers.size(); i++) {
            this.layers.get(i).save(path + "/layer" + i);
        }
    }

    private void load(String path) {
        for (int i = 0; i < this.layers.size(); i++) {
            this.layers.get(i).load(path + "/layer" + i);
        }
    }

    public ConvolutionalNeuralNetwork() {
        this.layers.add(new ConvolutionLayer(16, 28, 28, 3));
        this.layers.add(new PoolingLayer()); // this result in 8*14*14
        // this.layers.add(new SoftMaxLayer(8, 14, 14, 1, 14, 14));// hidden layer
        // this.layers.add(new SoftMaxLayer(1, 14, 14, 1, 14, 14));// hidden layer 2
        this.layers.add(new SoftMaxLayer(16, 14, 14, 1, 1, 10));// output layer
    }

    public void train(int trainningSize, ImageDataFrame trainningData, String path) throws IOException {
        this.load(path);
        int trueLabel = 0;
        double loss = 0;
        double accuracy = 0;
        double totalAccuracy = 0.0;
        double learnRate = 0.005;
        double[][][] softmaxResult3D;
        double lastAccuracy = 0.0;
        for (int step = 1; step <= trainningSize; step++) {
            double[][][] pixels = trainningData.sampledFromLabelIncrementally(trueLabel);
            int correct_label = trueLabel;
            trueLabel = (trueLabel + 1) % 10;
            softmaxResult3D = this.forward(pixels);
            double[][] softmaxResult = MathUtil.flatten(softmaxResult3D);
            loss += (double) -Math.log(softmaxResult[0][correct_label]);
            accuracy += correct_label == MathUtil.argmax(softmaxResult) ? 1 : 0;
            double[][] gradient = MathUtil.zeros(1, 10);
            gradient[0][correct_label] = -1 / softmaxResult[0][correct_label];
            this.backPropagation(MathUtil.reshape(gradient, 1, 1, 10), learnRate);
            if (step % 1000 == 0) {
                System.out
                        .print(" step: " + step + " loss: " + loss / 1000.0 + " accuracy: " + accuracy / 10.0 + "%");
                if (lastAccuracy >= accuracy) {
                    learnRate *= 0.99;
                    System.out.println(" learn rate: " + learnRate);
                } else {
                    learnRate *= 1.005;
                    System.out.println(" learn rate: " + learnRate);
                }
                loss = 0;
                totalAccuracy += accuracy;
                lastAccuracy = accuracy;
                accuracy = 0;
            }
        }
        System.out.println("tranning average accuracy:- " + totalAccuracy / trainningSize * 100 + "%");
        this.save(path);
    }

    public void test(int testSize, ImageDataFrame testData, String path) throws IOException {
        this.load(path);
        int trueLabel = 0;
        double totalAccuracy = 0.0;
        for (int step = 1; step <= testSize; step++) {
            double[][][] pixels = testData.sampledFromLabel(trueLabel);
            int correct_label = trueLabel;
            trueLabel = (trueLabel + 1) % 10;
            double[][][] softmaxResult3D = this.forward(pixels);
            double[][] softmaxResult = MathUtil.flatten(softmaxResult3D);
            totalAccuracy += correct_label == MathUtil.argmax(softmaxResult) ? 1 : 0;
        }
        System.out.println("test average accuracy:- " + totalAccuracy / testSize * 100 + "%");
    }
}