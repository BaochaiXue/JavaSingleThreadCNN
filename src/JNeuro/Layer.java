package JNeuro;

public interface Layer {
    public double[][][] forward(double[][][] input);

    public double[][][] backPropagation(double[][][] back, double learning_rate);

    public void save(String path);

    public void load(String path);
}