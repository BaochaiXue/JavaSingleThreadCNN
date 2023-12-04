
package JNeuro;

public class SoftMaxLayer implements Layer {

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

    public double[][][] forward(double[][][] input) {
        double[][] readIn = MathUtil.flatten(input);
        this.output = new double[1][outputDepth * outputHeight * outputWidth];
        output = MathUtil.matrixAdd(MathUtil.matrixHadamardProduct(readIn, weights), bias);
        double[][] exp = MathUtil.exp(output);
        double inverseFactor = 1 / MathUtil.sum(exp);
        this.input = readIn;
        return MathUtil.reshape(MathUtil.scaling(exp, inverseFactor), outputDepth, outputHeight, outputWidth);
    }

    @Override
    public double[][][] backPropagation(double[][][] deltaOutput3D, double learning_rate) {
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
            double[][] moveWeight = MathUtil.matrixHadamardProduct(deltaWeight, delta);
            deltaInput = MathUtil.matrixHadamardProduct(moveInput, MathUtil.transpose(delta));
            double[][] d_L_d_b = delta;
            weights = MathUtil.matrixAdd(MathUtil.scaling(moveWeight, -learning_rate), weights);
            bias = MathUtil.matrixAdd(MathUtil.scaling(d_L_d_b, -learning_rate), bias);
        }
        return MathUtil.reshape(MathUtil.transpose(deltaInput), depth, height, width);
    }

}
