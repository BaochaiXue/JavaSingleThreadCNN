
package JNeuro;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SoftMaxLayer implements Layer, java.io.Serializable {

    private double[][] weights;
    private double[][] input;
    private double[][] bias;
    private double[][] output;
    private int depth;
    private int height;
    private int width;
    private int outputDepth;
    private int outputHeight;
    private int outputWidth;

    public SoftMaxLayer(int depth, int height, int width, int outputDepth, int outputHeight, int outputWidth) {
        int inputSize = depth * height * width;
        this.depth = depth;
        this.height = height;
        this.width = width;
        this.outputDepth = outputDepth;
        this.outputHeight = outputHeight;
        this.outputWidth = outputWidth;
        int outPutSize = outputDepth * outputHeight * outputWidth;
        this.weights = MathUtil.scaling(MathUtil.randoms(inputSize, outPutSize), 1.0 / inputSize);
        this.bias = MathUtil.zeros(1, outPutSize);

    }

    @Override
    public double[][][] forward(double[][][] input) {
        double[][] readIn = MathUtil.flatten(input);
        this.output = new double[1][outputDepth * outputHeight * outputWidth];
        output = MathUtil.matrixAdd(MathUtil.matrixMutiplication(readIn, weights), bias);
        double[][] exp = MathUtil.exp(output);
        double inverseFactor = 1 / MathUtil.sum(exp);
        this.input = readIn;
        return MathUtil.reshape(MathUtil.scaling(exp, inverseFactor), outputDepth, outputHeight, outputWidth);
    }

    @Override
    public double[][][] backPropagation(double[][][] deltaOutput3D, double learningRate) {
        double[][] deltaOutput = MathUtil.flatten(deltaOutput3D);
        double[][] delta = new double[1][deltaOutput[0].length];
        double[][] exp = MathUtil.exp(output);
        double S = MathUtil.sum(exp);
        double[][] deltaInput = null;

        for (int i = 0; i < deltaOutput[0].length; i++) {
            double grad = deltaOutput[0][i];
            if (grad == 0) {
                continue;
            }
            double[][] outputMove = MathUtil.scaling(exp, -exp[0][i] / (S * S));
            outputMove[0][i] = exp[0][i] * (S - exp[0][i]) / (S * S);
            delta = MathUtil.scaling(outputMove, grad);
            double[][] deltaWeight = MathUtil.transpose(input);
            double[][] moveInput = weights;
            double[][] moveWeight = MathUtil.matrixMutiplication(deltaWeight, delta);
            deltaInput = MathUtil.matrixMutiplication(moveInput, MathUtil.transpose(delta));
            double[][] deltaLoss = delta;
            weights = MathUtil.matrixAdd(MathUtil.scaling(moveWeight, -learningRate), weights);
            bias = MathUtil.matrixAdd(MathUtil.scaling(deltaLoss, -learningRate), bias);
        }
        return MathUtil.reshape(MathUtil.transpose(deltaInput), depth, height, width);
    }

    @Override
    public void save(String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("we can not save the model softmax layer by the path: " + path);
            i.printStackTrace();
        }
    }

    @Override
    public void load(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            SoftMaxLayer layer = (SoftMaxLayer) in.readObject();
            this.weights = layer.weights;
            this.bias = layer.bias;
            this.input = layer.input;
            this.output = layer.output;
            this.depth = layer.depth;
            this.height = layer.height;
            this.width = layer.width;
            this.outputDepth = layer.outputDepth;
            this.outputHeight = layer.outputHeight;
            this.outputWidth = layer.outputWidth;
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("we can not load the model softmax layer by the path: " + path);
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("this class can transfer to softmax layer");
            c.printStackTrace();
            return;
        }
    }
}
