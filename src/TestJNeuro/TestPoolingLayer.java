package TestJNeuro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPoolingLayer {
    @Test
    public void testForward() {
        double[][][] input = new double[1][4][4];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = j * 4 + k;
                }
            }
        }
        double[][][] output = new double[1][2][2];
        output[0][0][0] = 5;
        output[0][0][1] = 7;
        output[0][1][0] = 13;
        output[0][1][1] = 15;
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        double[][][] result = poolingLayer.forward(input);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int k = 0; k < result[0][0].length; k++) {
                    assertEquals(output[i][j][k], result[i][j][k], 1e-10);
                }
            }
        }
    }

}
