package JNeuro;

public interface Layer {
    public double[][][] forward(double[][][] input);

    public double[][][] backPropagation(double[][][] back, double learning_rate);
}