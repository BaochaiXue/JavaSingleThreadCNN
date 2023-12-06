package JNeuro;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PoolingLayer implements Layer, java.io.Serializable {

    private double[][][] input;
    private double[][][] output;

    private double[][] pooling(double[][] img) {
        double[][] pool = new double[img.length / 2][img[0].length / 2];
        for (int i = 0; i < pool.length; i++) {
            for (int j = 0; j < pool[0].length; j++) {
                pool[i][j] = MathUtil.maxPooling(MathUtil.submatrix(img, i * 2, i * 2 + 1, j * 2, j * 2 + 1));
            }
        }
        return pool;
    }

    @Override
    public double[][][] forward(double[][][] dataInput) {
        input = dataInput;
        double[][][] result = new double[dataInput.length][dataInput[0].length / 2][dataInput[0][0].length / 2];
        for (int k = 0; k < dataInput.length; k++) {
            result[k] = pooling(dataInput[k]);
        }
        output = result;
        return result;
    }

    @Override
    public double[][][] backPropagation(double[][][] backResult, double learning_rate) {
        double[][][] result = new double[input.length][input[0].length][input[0][0].length];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                for (int k = 0; k < output[0][0].length; k++) {
                    double[][] region = MathUtil.submatrix(input[i], j * 2, j * 2 + 1, k * 2, k * 2 + 1);
                    for (int m = 0; m < region.length; m++) {
                        for (int n = 0; n < region[0].length; n++) {
                            if (Math.abs(output[i][j][k] - region[m][n]) < 1e-10) {
                                result[i][j * 2 + m][k * 2 + n] = backResult[i][j][k];
                            }
                        }
                    }
                }
            }
        }
        return result;
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
            System.out.println("we can not save the model pooling layer by the path: " + path);
            i.printStackTrace();
        }
    }

    @Override
    public void load(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            PoolingLayer layer = (PoolingLayer) in.readObject();
            this.input = layer.input;
            this.output = layer.output;
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("we can not load the model pooling layer by the path: " + path);
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("this class can transfer to pooling layer");
            c.printStackTrace();
            return;
        }
    }
}
