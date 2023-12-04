package JNeuro;

public class PoolingLayer implements Layer {

    private double[][][] input;
    private double[][][] output;

    public double[][] pooling(double[][] img) {
        double[][] pool = new double[img.length / 2][img[0].length / 2];
        for (int i = 0; i < pool.length - 1; i++) {
            for (int j = 0; j < pool[0].length - 1; j++) {
                pool[i][j] = MathUtil.maxPooling(MathUtil.submatrix(img, i * 2, i * 2 + 1, j * 2, j * 2 + 1));
            }
        }
        return pool;
    }

    @Override
    public double[][][] forward(double[][][] dta) {
        input = dta;
        double[][][] result = new double[dta.length][dta[0].length][dta[0][0].length];
        for (int k = 0; k < dta.length; k++) {
            double[][] res = pooling(dta[k]);
            result[k] = res;
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
}
