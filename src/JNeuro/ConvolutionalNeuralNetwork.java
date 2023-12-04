package JNeuro;

import java.io.IOException;
import java.util.ArrayList;

public class ConvolutionalNeuralNetwork {
    private ArrayList<Layer> layers = new ArrayList<Layer>();
    private String weightPath;
    private boolean isStartNewly;

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

    public ConvolutionalNeuralNetwork(boolean isStartNewly, String pathOfWeight) {
        this.layers.add(new ConvolutionLayer(32, 28, 28, 3));
        this.layers.add(new PoolingLayer());
        // this.layers.add(new SoftMaxLayer(8, 14, 14, 1, 14, 14));// hidden layer
        // this.layers.add(new SoftMaxLayer(1, 14, 14, 1, 14, 14));// hidden layer 2
        this.layers.add(new SoftMaxLayer(32, 14, 14, 1, 1, 10));// output layer
        this.weightPath = pathOfWeight;
        this.isStartNewly = isStartNewly;
        if (!isStartNewly) {
            this.load(pathOfWeight);
        }
    }

    public void train(int trainningSize, ImageDataFrame trainningData) throws IOException {
        System.out.println("start training...");
        int trueLabel = 0;
        double loss = 0;
        double accuracy = 0;
        double totalAccuracy = 0.0;
        double learnRate = isStartNewly ? 0.005 : 0.001;
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
            learnRate *= 0.99999; // learning rate decay
            if (step % 1000 == 0) {
                if (lastAccuracy > accuracy) {
                    learnRate *= 0.5;
                }
                lastAccuracy = accuracy;
                System.out
                        .println(" step: " + step + " loss: " + loss / 1000.0 + " accuracy: " + accuracy / 10.0 + "%"
                                + " learnRate: " + learnRate);
                loss = 0;
                totalAccuracy += accuracy;
                accuracy = 0;
            }
        }
        System.out.println("tranning average accuracy: " + totalAccuracy / trainningSize * 100 + "%"
                + " trainning size: " + trainningSize);
        this.save(this.weightPath);
        isStartNewly = false;
        System.out.println("training finished");
    }

    public void test(int testSize, ImageDataFrame testData) throws IOException {
        System.out.println("start testing...");
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
        System.out
                .println("test average accuracy: " + totalAccuracy / testSize * 100 + "%" + " test size: " + testSize);
        System.out.println("testing finished");
    }
}