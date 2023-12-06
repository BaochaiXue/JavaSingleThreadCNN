package JNeuro;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ConvolutionLayer implements Layer, java.io.Serializable {
    private double[][][] sensingFields;
    private double[][][] filters;

    private static double[][][] createFilters(int depth, int size) {
        double[][][] result = new double[depth][size][size];
        for (int k = 0; k < depth; k++) {
            result[k] = MathUtil.randoms(size, size);
        }
        return result;
    }

    public ConvolutionLayer(int depth, int height, int width, int filterSize) {
        this.filters = createFilters(depth, filterSize);
    }

    private double[][] convolve(double[][] image, double[][] filter) {
        double[][] result = new double[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                var convolveField = MathUtil.submatrixAndFillZero(image, i - filter.length / 2, i + filter.length / 2,
                        j - filter[0].length / 2, j + filter[0].length / 2);
                result[i][j] = MathUtil.sumOfHadamardProduct(convolveField, filter);
            }
        }
        return result;
    }

    @Override
    public double[][][] forward(double[][][] image) {
        this.sensingFields = image;
        double[][][] result = new double[filters.length * image.length][image[0].length][image[0][0].length];
        for (int s = 0; s < image.length; s++) {
            for (int k = 0; k < filters.length; k++) {
                result[s * filters.length + k] = convolve(image[s], filters[k]);
            }
        }
        return result;
    }

    @Override
    public double[][][] backPropagation(double[][][] back, double learning_rate) {
        double[][][] deltaFilters = new double[filters.length][filters[0].length][filters[0][0].length];
        for (int s = 0; s < this.sensingFields.length; s++) {
            for (int i = 0; i < this.sensingFields[0].length; i++) {
                for (int j = 0; j < this.sensingFields[0][0].length; j++) {
                    for (int k = 0; k < filters.length; k++) {
                        deltaFilters[k] = MathUtil.matrixAdd(deltaFilters[k],
                                MathUtil.scaling(
                                        MathUtil.submatrixAndFillZero(back[s * filters.length + k],
                                                i - filters[0].length / 2,
                                                i + filters[0].length / 2,
                                                j - filters[0][0].length / 2,
                                                j + filters[0][0].length / 2),
                                        this.sensingFields[s][i][j]));
                    }
                }
            }
        }

        for (int m = 0; m < filters.length; m++) {
            filters[m] = MathUtil.matrixAdd(filters[m], MathUtil.scaling(deltaFilters[m], -learning_rate));
        }

        double[][][] result = new double[sensingFields.length][sensingFields[0].length][sensingFields[0][0].length];
        for (int s = 0; s < sensingFields.length; s++) {
            for (int i = 0; i < sensingFields[0].length; i++) {
                for (int j = 0; j < sensingFields[0][0].length; j++) {
                    for (int k = 0; k < filters.length; k++) {
                        result[s][i][j] += MathUtil.sumOfHadamardProduct(
                                MathUtil.submatrixAndFillZero(back[s * filters.length + k], i - filters[0].length / 2,
                                        i + filters[0].length / 2, j - filters[0][0].length / 2,
                                        j + filters[0][0].length / 2),
                                MathUtil.flip(filters[k]));
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
            System.out.println("we can not save the model convolutional layer by the path: " + path);
            i.printStackTrace();
        }
    }

    @Override
    public void load(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ConvolutionLayer convolutionLayerLayer = (ConvolutionLayer) in.readObject();
            this.filters = convolutionLayerLayer.filters;
            this.sensingFields = convolutionLayerLayer.sensingFields;
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("ConvolutionLayer layer can not be found in the path: " + path);
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("this class can transfer to ConvolutionLayer layer");
            c.printStackTrace();
            return;
        }
    }

}