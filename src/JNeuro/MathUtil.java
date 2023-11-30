package JNeuro;

public class MathUtil {

    public static double[][] matrixHadamardProduct(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] matrixAdd(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    public static double[][] zeros(int size) {
        double[][] result = new double[1][size];
        for (int i = 0; i < size; i++) {
            result[0][i] = 0.0;
        }
        return result;
    }

    public static double[][] fullfill(int size, double value) {
        double[][] result = new double[1][size];
        for (int i = 0; i < result[0].length; i++) {
            result[0][i] = value;
        }
        return result;
    }

    public static double[][] exp(double[][] v) {
        double[][] exp = new double[1][v[0].length];
        for (int i = 0; i < v[0].length; i++) {
            exp[0][i] = (double) Math.exp(v[0][i]);
        }
        return exp;
    }

    public static double[][] vectorScaling(double[][] v, double scale) {
        double[][] scl = new double[1][v[0].length];
        for (int i = 0; i < v[0].length; i++) {
            scl[0][i] = (double) v[0][i] * scale;
        }
        return scl;
    }

    public static double[][] matrixScaling(double[][] mat, double scale) {
        double[][] scl = new double[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                scl[i][j] = (double) mat[i][j] * scale;
            }
        }
        return scl;
    }

    public static double sum(double[][] v) {
        double sum = 0;
        for (int i = 0; i < v[0].length; i++) {
            sum += v[0][i];
        }
        return sum;
    }

    public static double[][] transpose(double[][] mat) {
        double[][] transpose = new double[mat[0].length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                transpose[j][i] = mat[i][j];
            }
        }
        return transpose;
    }

    public static double squareMagnitude(double[][] v) {
        double sq_sum = 0;
        for (int i = 0; i < v[0].length; i++) {
            sq_sum += v[0][i] * v[0][i];
        }
        return sq_sum;
    }

    public static double[][] random(int h, int w) {
        double[][] result = new double[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = (double) Math.random();
            }
        }
        return result;
    }

    public static double[][] zeros(int h, int w) {
        double[][] result = new double[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = (double) Math.random();
            }
        }
        return result;
    }

    public static double[][] random(int w) {
        double[][] result = new double[1][w];
        for (int j = 0; j < w; j++) {
            result[0][j] = (double) Math.random();
        }

        return result;
    }

    public static double[][] submatrix(double[][] matrix, int x1, int x2, int y1, int y2) {
        // check if the submatrix is valid
        if (x1 < 0 || x2 >= matrix.length || y1 < 0 || y2 >= matrix[0].length) {
            throw new RuntimeException("the submatrix is invalid");
        }
        double[][] sub = new double[x2 - x1 + 1][y2 - y1 + 1];
        for (int i = 0; i < sub.length; i++) {
            for (int j = 0; j < sub[0].length; j++) {
                sub[i][j] = matrix[x1 + i][y1 + j];
            }
        }
        return sub;
    }

    public static double[][] submatrixAndFillZero(double[][] matrix, int x1, int x2, int y1, int y2) {
        double[][] sub = new double[x2 - x1 + 1][y2 - y1 + 1];
        for (int i = 0; i < sub.length; i++) {
            for (int j = 0; j < sub[0].length; j++) {
                if (x1 + i < 0 || x1 + i >= matrix.length || y1 + j < 0 || y1 + j >= matrix[0].length) {
                    sub[i][j] = 0;
                } else {
                    sub[i][j] = matrix[x1 + i][y1 + j];
                }
            }
        }
        return sub;
    }

    public static double sumOfHadamardProduct(double[][] mat1, double[][] mat2) {
        double sum = 0;
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                sum += mat1[i][j] * mat2[i][j];
            }
        }
        return sum;
    }

    public static double matrixMax(double[][] mat) {
        double max = mat[0][0];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                max = max < mat[i][j] ? mat[i][j] : max;
            }
        }
        return max;
    }

    public static double vectorMax(double[][] vec) {
        double max = vec[0][0];
        for (int i = 0; i < vec[0].length; i++) {
            max = max < vec[0][i] ? vec[0][i] : max;
        }
        return max;
    }

    public static int argmax(double[][] vec) {
        int arg = 0;
        for (int i = 0; i < vec[0].length; i++) {
            arg = vec[0][arg] < vec[0][i] ? i : arg;
        }
        return arg;
    }

    public static double[] shape(double[][] mat) {
        return new double[] { mat.length, mat[0].length };
    }

    public static double[][] flattern(double[][] mat) {
        double[][] v = new double[1][mat.length * mat[0].length];
        int k = 0; // vector iterator
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                v[0][k] = mat[i][j];
                k++;
            }
        }
        return v;
    }

    public static double[][] flatten(double[][][] mat) {
        double[][] v = new double[1][mat.length * mat[0].length * mat[0][0].length];
        int pos = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                for (int k = 0; k < mat[0][0].length; k++) {
                    v[0][pos] = mat[i][j][k];
                    pos++;
                }
            }
        }
        return v;
    }

    public static double[][] argumentRowVector(double[] input) {
        double[][] result = new double[1][input.length];
        for (int i = 0; i < input.length; i++) {
            result[0][i] = input[i];
        }
        return result;
    }

    public static double[][] argumentColumnVector(double[] input) {
        double[][] result = new double[input.length][1];
        for (int i = 0; i < input.length; i++) {
            result[i][0] = input[i];
        }
        return result;
    }

    public static double[][][] reshape(double[][] input, int d, int h, int w) {
        double[][][] output = new double[d][h][w];
        int input_index = 0;
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    output[i][j][k] = input[0][input_index];
                    input_index++;
                }
            }
        }
        return output;
    }

    public static double[][] flip(double[][] mat) {
        double[][] result = new double[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            result[mat.length - 1 - i] = reverse(mat[i]);
        }
        return result;
    }

    private static double[] reverse(double[] ds) {
        double[] result = new double[ds.length];
        for (int i = 0; i < ds.length; i++) {
            result[i] = ds[ds.length - 1 - i];
        }
        return result;
    }
}
