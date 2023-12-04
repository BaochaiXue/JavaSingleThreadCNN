package JNeuro;

public class MathUtil {

    public static double[][] matrixMutiplication(final double[][] matrixA, final double[][] matrixB) {
        // check if the two matrix have the compatible size
        if (matrixA[0].length != matrixB.length) {
            throw new RuntimeException("the two matrix have incompatible size, the first matrix is " + matrixA.length
                    + " * " + matrixA[0].length + " and the second matrix is " + matrixB.length + " * "
                    + matrixB[0].length);
        }

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

    public static double[][] matrixAdd(final double[][] matrixA, final double[][] matrixB) {
        // check if the two matrix have the same size
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            throw new RuntimeException("the two matrix have different size, the first matrix is " + matrixA.length
                    + " * " + matrixA[0].length + " and the second matrix is " + matrixB.length + " * "
                    + matrixB[0].length);
        }
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    public static double[][] zeros(final int height, final int width) {
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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
                matrixScaling[i][j] = matrix[i][j] * scale;
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

    public static double[][] randoms(final int height, final int width) {
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = Math.random();
            }
        }
        return result;
    }

    public static double[][] submatrix(final double[][] matrix, final int x1, final int x2, final int y1,
            final int y2) {
        // check if the submatrix is valid
        if (x1 < 0 || x2 >= matrix.length || y1 < 0 || y2 >= matrix[0].length) {
            throw new RuntimeException("the submatrix is invalid");
        }
        double[][] region = new double[x2 - x1 + 1][y2 - y1 + 1];
        for (int i = 0; i < region.length; i++) {
            for (int j = 0; j < region[0].length; j++) {
                region[i][j] = matrix[x1 + i][y1 + j];
            }
        }
        return region;
    }

    public static double[][] submatrixAndFillZero(final double[][] matrix, final int x1, final int x2, final int y1,
            final int y2) {
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

    public static double sumOfMatrixProduct(final double[][] matrixA, final double[][] matrixB) {
        double sum = 0;
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                sum += matrixA[i][j] * matrixB[i][j];
            }
        }
        return sum;
    }

    public static double maxPooling(final double[][] matrix) {
        double max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = max < matrix[i][j] ? matrix[i][j] : max;
            }
        }
        return max;
    }

    public static int argmax(final double[][] vector) {
        int arg = 0;
        for (int i = 0; i < vector[0].length; i++) {
            arg = vector[0][arg] < vector[0][i] ? i : arg;
        }
        return arg;
    }

    public static double[][] flatten(final double[][][] matrix) {
        double[][] vector = new double[1][matrix.length * matrix[0].length * matrix[0][0].length];
        int pos = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 0; k < matrix[0][0].length; k++) {
                    vector[0][pos] = matrix[i][j][k];
                    pos++;
                }
            }
        }
        return vector;
    }

    public static double[][][] reshape(final double[][] vector, final int depth, final int height, final int width) {
        double[][][] output = new double[depth][height][width];
        int input_index = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < width; k++) {
                    output[i][j][k] = vector[0][input_index];
                    input_index++;
                }
            }
        }
        return output;
    }

    public static double[][] flip(final double[][] matrix) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            result[matrix.length - 1 - i] = reverse(matrix[i]);
        }
        return result;
    }

    private static double[] reverse(final double[] matrix) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[matrix.length - 1 - i];
        }
        return result;
    }
}
