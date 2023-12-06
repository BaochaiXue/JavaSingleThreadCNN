package TestJNeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.experimental.theories.ParametersSuppliedBy;

import JNeuro.MathUtil;

public class TestMahtUtil {
    @Test
    public void testMatrixMutiplication1() {
        double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] matrixB = new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        double[][] result = JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        double[][] expected = new double[][] { { 22, 28 }, { 49, 64 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assert (result[i][j] == expected[i][j]);
            }
        }
    }

    @Test
    public void testMatrixMutiplication2() {
        // try to catch the exception
        try {
            double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            double[][] matrixB = new double[][] { { 1, 2 }, { 3, 4 } };
            JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals(
                    "the two matrix have incompatible size, the first matrix is 2 * 3 and the second matrix is 2 * 2"));
        }
    }

    @Test
    public void testMatrixMutiplication3() {
        double[][] matrixA = new double[][] { { 1, 2 }, { 3, 4 } };
        double[][] matrixB = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        double[][] expected = new double[][] { { 9, 12, 15 }, { 19, 26, 33 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assert (result[i][j] == expected[i][j]);
            }
        }
    }

    @Test
    public void testMatrixMutiplication4() {
        double[][] matrixA = new double[][] { { 1, 2 }, { 3, 4 } };
        double[][] matrixB = new double[][] { { 1, 2 }, { 3, 4 } };
        double[][] result = JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        double[][] expected = new double[][] { { 7, 10 }, { 15, 22 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assert (result[i][j] == expected[i][j]);
            }
        }
    }

    @Test
    public void testMatrixMutiplication5() {
        double[][] matrixA = new double[][] { { 1, 2, 3 } };
        double[][] matrixB = new double[][] { { 1 }, { 2 }, { 3 } };
        double[][] result = JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        double[][] expected = new double[][] { { 14 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[0][0] == expected[0][0]);
    }

    @Test
    public void testMatrixMutiplication6() {
        double[][] matrixA = new double[][] { { 1 }, { 2 }, { 3 } };
        double[][] matrixB = new double[][] { { 1, 2, 3 } };
        double[][] result = JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        double[][] expected = new double[][] { { 1, 2, 3 }, { 2, 4, 6 }, { 3, 6, 9 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[0][0] == expected[0][0]);
    }

    @Test
    public void testMatrixMutiplication7() {
        double[][] matrixA = null;
        double[][] matrixB = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
        } catch (RuntimeException e) {
            assertEquals("the two matrix is null", e.getMessage());
        }

    }

    @Test
    public void testMatrixMutiplication8() {
        // we enumerate all the possible cases of if the matrix is null
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                double[][] matrixA = null;
                double[][] matrixB = new double[][] { { 1, 2, 3 } };
                // try to catch the exception
                try {
                    JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the two matrix is null", e.getMessage());
                }
            } else if (i == 1) {
                double[][] matrixA = new double[][] { { 1, 2, 3 } };
                double[][] matrixB = null;
                // try to catch the exception
                try {
                    JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the two matrix is null", e.getMessage());
                }
            } else if (i == 2) {
                double[][] matrixA = null;
                double[][] matrixB = null;
                // try to catch the exception
                try {
                    JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the two matrix is null", e.getMessage());
                }
            } else {
                double[][] matrixA = new double[][] { { 1, 2, 3 } };
                double[][] matrixB = new double[][] { { 1, 2, 3 } };
                try {
                    JNeuro.MathUtil.matrixMutiplication(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertNotEquals("the two matrix is null", e.getMessage());
                }
            }
        }
    }

    @Test
    public void testMatrixAdd1() {
        double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] matrixB = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
        double[][] expected = new double[][] { { 2, 4, 6 }, { 8, 10, 12 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assert (result[i][j] == expected[i][j]);
            }
        }
    }

    @Test
    public void testMatrixAdd2() {
        // try to catch the exception
        try {
            double[][] matrixA = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            double[][] matrixB = new double[][] { { 1, 2 }, { 3, 4 } };
            JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals(
                    "the two matrix have different size, the first matrix is 2 * 3 and the second matrix is 2 * 2"));
        }
    }

    @Test
    public void testMatrixAdd3() {
        double[][] matrixA = new double[][] { { 1, 2 }, { 3, 4 } };
        double[][] matrixB = new double[][] { { 1, 2 }, { 3, 4 } };
        double[][] result = JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
        double[][] expected = new double[][] { { 2, 4 }, { 6, 8 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[0][0] == expected[0][0]);
    }

    @Test
    public void testMatrixAdd4() {
        double[][] matrixA = null;
        double[][] matrixB = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
        } catch (RuntimeException e) {
            assertEquals("the two matrix is null", e.getMessage());
        }
    }

    @Test
    public void testMatrixAdd5() {
        // we enumerate all the possible cases of if the matrix is null
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                double[][] matrixA = null;
                double[][] matrixB = new double[][] { { 1, 2, 3 } };
                // try to catch the exception
                try {
                    JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the two matrix is null", e.getMessage());
                }
            } else if (i == 1) {
                double[][] matrixA = new double[][] { { 1, 2, 3 } };
                double[][] matrixB = null;
                // try to catch the exception
                try {
                    JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the two matrix is null", e.getMessage());
                }
            } else if (i == 2) {
                double[][] matrixA = null;
                double[][] matrixB = null;
                // try to catch the exception
                try {
                    JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the two matrix is null", e.getMessage());
                }
            } else {
                double[][] matrixA = new double[][] { { 1, 2, 3 } };
                double[][] matrixB = new double[][] { { 1, 2, 3, 4 } };
                try {
                    JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertNotEquals("the two matrix is null", e.getMessage());
                }
                matrixA = new double[][] { { 1, 2 }, { 3, 4 } };
                matrixB = new double[][] { { 1, 2 } };
                try {
                    JNeuro.MathUtil.matrixAdd(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertNotEquals("the two matrix is null", e.getMessage());
                }
            }
        }
    }

    @Test
    public void testZeros() {
        double[][] result = JNeuro.MathUtil.zeros(2, 3);
        double[][] expected = new double[][] { { 0, 0, 0 }, { 0, 0, 0 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough to 0
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testExp1() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.exp(matrix);
        double[][] expected = new double[][] { { Math.exp(1), Math.exp(2), Math.exp(3) },
                { Math.exp(4), Math.exp(5), Math.exp(6) } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testExp2() {
        double[][] matrix = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.exp(matrix);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testScaling() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double scale = 2;
        double[][] result = JNeuro.MathUtil.scaling(matrix, scale);
        double[][] expected = new double[][] { { 2, 4, 6 }, { 8, 10, 12 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testScaling2() {
        double[][] matrix = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.scaling(matrix, 2);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testSum() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double result = JNeuro.MathUtil.sum(matrix);
        double expected = 21;
        assert (Math.abs(result - expected) < 1e-10);
    }

    @Test
    public void testSum2() {
        double[][] matrix = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.sum(matrix);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testTranspose() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.transpose(matrix);
        double[][] expected = new double[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testTranspose2() {
        double[][] matrix = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.transpose(matrix);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testRandoms() {
        double[][] result = JNeuro.MathUtil.randoms(2, 3);
        assert (result.length == 2);
        assert (result[0].length == 3);
        assert (result[1].length == 3);
    }

    @Test
    public void testSubmatrix() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrix(matrix, 0, 0, 1, 1);
        double[][] expected = new double[][] { { 2 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrix2() {
        // try to catch the exception
        try {
            double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            JNeuro.MathUtil.submatrix(matrix, 0, 0, -1, 2);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("the submatrix is out of bound"));
        }
    }

    @Test
    public void testSubmatrix3() {
        // try to catch the exception
        try {
            double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            JNeuro.MathUtil.submatrix(matrix, 0, 0, 2, 1);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("the submatrix is out of bound"));
        }
    }

    @Test
    public void testSubmatrix4() {
        // try to catch the exception
        try {
            double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            JNeuro.MathUtil.submatrix(matrix, 0, 0, 1, 3);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("the submatrix is out of bound"));
        }
    }

    @Test
    public void testSubmatrix5() {
        // try to catch the exception
        try {
            double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            JNeuro.MathUtil.submatrix(matrix, 0, 0, 3, 1);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("the submatrix is out of bound"));
        }
    }

    @Test
    public void testSubmatrix6() {
        // try to catch the exception
        try {
            double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
            JNeuro.MathUtil.submatrix(matrix, 0, 0, 3, 3);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("the submatrix is out of bound"));
        }
    }

    @Test
    public void testSubmatrix7() {
        // try to catch the exception
        try {
            double[][] matrix = null;
            JNeuro.MathUtil.submatrix(matrix, 0, 0, 1, 1);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals("the matrix is null"));
        }
    }

    @Test
    public void testSubmatrix8() {
        // test branches of if (x1 < 0 || x2 >= matrix.length || y1 < 0 || y2 >=
        // matrix[0].length)
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        for (int x1 = -1; x1 < 2; x1++) {
            for (int x2 = 1; x2 < 4; x2++) {
                for (int y1 = -1; y1 < 2; y1++) {
                    for (int y2 = 1; y2 < 4; y2++) {
                        if (x1 < 0 || x2 >= matrix.length || y1 < 0 || y2 >= matrix[0].length) {
                            try {
                                JNeuro.MathUtil.submatrix(matrix, x1, x2, y1, y2);
                            } catch (RuntimeException e) {
                                assert (e.getMessage().equals("the submatrix is out of bound"));
                            }
                        }
                    }
                }
            }
        }
        // test branches of if (x1 > x2 || y1 > y2)
        for (int x1 = 0; x1 < 2; x1++) {
            for (int x2 = 0; x2 < 2; x2++) {
                for (int y1 = 0; y1 < 2; y1++) {
                    for (int y2 = 0; y2 < 2; y2++) {
                        if (x1 > x2 || y1 > y2) {
                            try {
                                JNeuro.MathUtil.submatrix(matrix, x1, x2, y1, y2);
                            } catch (RuntimeException e) {
                                assert (e.getMessage().equals("the submatrix is out of bound"));
                            }
                        }
                    }
                }
            }
        }

    }

    @Test
    public void testSubmatrixAndFillZero() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 0, 0, 1, 1);
        double[][] expected = new double[][] { { 2 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero2() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, -1, 0, 1, 1);
        double[][] expected = new double[][] { { 0 }, { 2 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero3() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 0, 0, 1, 2);
        double[][] expected = new double[][] { { 2, 3 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero4() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 0, 0, 1, 3);
        double[][] expected = new double[][] { { 2, 3, 0 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero5() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 0, 0, 1, 4);
        double[][] expected = new double[][] { { 2, 3, 0, 0 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero6() {
        double[][] matrix = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.submatrixAndFillZero(matrix, 0, 0, 1, 1);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testSubmatrixAndFillZero7() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, -1, 1, 1, 4);
        double[][] expected = new double[][] { { 0, 0, 0, 0 }, { 2, 3, 0, 0 }, { 5, 6, 0, 0 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero8() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 9, 9, 11, 11);
        double[][] expected = new double[][] { { 0 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero9() {
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 9, 9, -1, -1);
        double[][] expected = new double[][] { { 0 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i].length == expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                // test if the double is close enough
                assert (Math.abs(result[i][j] - expected[i][j]) < 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero10() {
        double[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        double[][] expected = {
                { 6, 0 },
                { 9, 0 }
        };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, 1, 2, 2, 3);
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[1].length == expected[1].length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
        }
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assertEquals(result[i][j], expected[i][j], 1e-10);
            }
        }
    }

    @Test
    public void testSubmatrixAndFillZero11() {
        double[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        double[][] expected = {
                { 0, 0 },
                { 0, 1 }
        };
        double[][] result = JNeuro.MathUtil.submatrixAndFillZero(matrix, -1, 0, -1, 0);
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[1].length == expected[1].length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
        }
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assertEquals(result[i][j], expected[i][j], 1e-10);
            }
        }
    }

    @Test
    public void testSumOfHadamardProduct() {
        double[][] matrixA = new double[][] { { 1, 2 }, { 4, 5 } };
        double[][] matrixB = new double[][] { { 1, 2 }, { 3, 4 } };
        double result = JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
        double expected = 1 + 4 + 12 + 20;
        assert (Math.abs(result - expected) < 1e-10);
    }

    @Test
    public void testSumOfHadamardProduct2() {
        double[][] matrixA = null;
        double[][] matrixB = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testSumOfHadamardProduct3() {
        // we enumerate all the possible cases of if the matrix is null
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                double[][] matrixA = null;
                double[][] matrixB = new double[][] { { 1, 2, 3 } };
                // try to catch the exception
                try {
                    JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the matrix is null", e.getMessage());
                }
            } else if (i == 1) {
                double[][] matrixA = new double[][] { { 1, 2, 3 } };
                double[][] matrixB = null;
                // try to catch the exception
                try {
                    JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the matrix is null", e.getMessage());
                }
            } else if (i == 2) {
                double[][] matrixA = null;
                double[][] matrixB = null;
                // try to catch the exception
                try {
                    JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
                } catch (RuntimeException e) {
                    assertEquals("the matrix is null", e.getMessage());
                }
            }
        }
    }

    @Test
    public void testSumOfHadamardProduct4() {
        double[][] matrixA = new double[][] { { 1, 2, 3 } };
        double[][] matrixB = new double[][] { { 2, 3, 4 } };
        double result = JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
        double expected = 2 + 6 + 12;
        assertEquals(result, expected, 1e-10);
    }

    @Test
    public void testSumOfHadamardProduct5() {
        // try to catch exception of if (matrixA.length != matrixB.length ||
        // matrixA[0].length != matrixB[0].length)
        try {
            double[][] matrixA = new double[][] { { 1, 2, 3 } };
            double[][] matrixB = new double[][] { { 1, 2 } };
            JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals(
                    "the two matrix have different size, the first matrix is 1 * 3 and the second matrix is 1 * 2"));
        }
        try {
            double[][] matrixA = new double[][] { { 1, 2, 3 } };
            double[][] matrixB = new double[][] { { 1, 2, 3 }, { 1, 2, 3 } };
            JNeuro.MathUtil.sumOfHadamardProduct(matrixA, matrixB);
        } catch (RuntimeException e) {
            assert (e.getMessage().equals(
                    "the two matrix have different size, the first matrix is 1 * 3 and the second matrix is 2 * 3"));
        }

    }

    @Test
    public void testMaxPooling() {
        double[][] matrix = new double[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
        double result = JNeuro.MathUtil.maxPooling(matrix);
        double expected = 8;
        assert (Math.abs(result - expected) < 1e-10);
    }

    @Test
    public void testMaxPooling2() {
        double[][] matrix = new double[][] { { 1 } };
        double result = JNeuro.MathUtil.maxPooling(matrix);
        double expected = 1;
        assertEquals(expected, result, 1e-10);
    }

    @Test
    public void testMaxPooling3() {
        double[][] matrix = null;
        // try to catch the exception
        try {
            JNeuro.MathUtil.maxPooling(matrix);
        } catch (RuntimeException e) {
            assertEquals("the matrix is null", e.getMessage());
        }
    }

    @Test
    public void testArgmax() {
        double[][] rowVector = new double[][] { { 1, 2, 3, 4 } };
        int result = JNeuro.MathUtil.argmax(rowVector);
        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void testArgmax2() {
        double[][] rowVector = new double[][] { { 1, 12, 3, 4 } };
        int result = JNeuro.MathUtil.argmax(rowVector);
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void testArgmax3() {
        double[][] rowVector = new double[][] { { 1 } };
        int result = JNeuro.MathUtil.argmax(rowVector);
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void testFlatten() {
        double[][][] matrix = new double[][][] { { { 1, 2, 3 }, { 4, 5, 6 } } };
        double[][] result = JNeuro.MathUtil.flatten(matrix);
        double[][] expected = new double[][] { { 1, 2, 3, 4, 5, 6 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
        }
    }

    @Test
    public void testReshape() {
        double[][] matrix = new double[][] { { 1, 2, 3, 4, 5, 6 } };
        double[][][] result = JNeuro.MathUtil.reshape(matrix, 1, 2, 3);
        double[][][] expected = new double[][][] { { { 1, 2, 3 }, { 4, 5, 6 } } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[0][0].length == expected[0][0].length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
        }
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assertEquals(result[i][j].length, expected[i][j].length);
            }
        }
    }

    @Test
    public void testFlip() {
        MathUtil mathUtil = new MathUtil();
        assertNotEquals(mathUtil, null);
        double[][] matrix = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        double[][] result = JNeuro.MathUtil.flip(matrix);
        double[][] expected = new double[][] { { 6, 5, 4 }, { 3, 2, 1 } };
        assert (result.length == expected.length);
        assert (result[0].length == expected[0].length);
        assert (result[1].length == expected[1].length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
        }
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].length, expected[i].length);
            for (int j = 0; j < result[i].length; j++) {
                assertEquals(result[i][j], expected[i][j], 1e-10);
            }
        }

    }
}
