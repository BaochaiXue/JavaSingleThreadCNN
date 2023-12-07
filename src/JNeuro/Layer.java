package JNeuro;

public interface Layer {
    public double[][][] forward(double[][][] input);

    public double[][][] backPropagation(double[][][] back, double learningRate);

    public void save(String path);

    public void load(String path);
}