package JNeuro;

public class MathUtil {

    public static double[][] matrixHadamardProduct(final double[][] matrixA, final double[][] matrixB) {
        double[][] result = new double[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] matrixAdd(final double[][] matrix1, final double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static double[][] zeros(final int height, final int weight) {
        double[][] result = new double[height][weight];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                result[i][j] = 0;
            }
        }
        return result;
    }

    public static double[][] exp(final double[][] matrix) {
        double[][] expMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                expMatrix[i][j] = Math.exp(matrix[i][j]);
            }
        }
        return expMatrix;
    }

    public static double[][] scaling(final double[][] matrix, final double scale) {
        double[][] matrixScaling = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixScaling[i][j] = (double) matrix[i][j] * scale;
            }
        }
        return matrixScaling;
    }

    public static double sum(final double[][] matrix) {
        double sum = 0;
        for (double[] row : matrix) {
            for (double element : row) {
                sum += element;
            }
        }
        return sum;
    }

    public static double[][] transpose(final double[][] matrix) {
        double[][] transpose = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

    public static double[][] randoms(final int height, final int weight) {
        double[][] result = new double[height][weight];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                result[i][j] = Math.random();
            }
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
