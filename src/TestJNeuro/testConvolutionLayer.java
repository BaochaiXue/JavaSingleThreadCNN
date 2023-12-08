package TestJNeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import JNeuro.ConvolutionLayer;

public class testConvolutionLayer {
    @Test
    public void testCreateFilters() {
        ConvolutionLayer layer = new ConvolutionLayer(3, 5, 5, 3);
        assertEquals(3, layer.getFilters().length);
        assertEquals(3, layer.getFilters()[0].length);
        assertEquals(3, layer.getFilters()[0][0].length);
    }

    @Test
    public void testForward() {
        ConvolutionLayer layer = new ConvolutionLayer(3, 5, 5, 3);
        double[][][] input = new double[1][5][5];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = 1;
                }
            }
        }
        double[][][] output = layer.forward(input);
        System.out.println(output.length);
        System.out.println(output[0].length);
        System.out.println(output[0][0].length);
        assertEquals(3, output.length);
        assertEquals(5, output[0].length);
        assertEquals(5, output[0][0].length);
    }

    @Test
    public void testBackPropagation() {
        ConvolutionLayer layer = new ConvolutionLayer(3, 5, 5, 3);
        double[][][] input = new double[1][5][5];
        double[][][] back = new double[3][5][5];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = 1;
                }
            }
        }
        back[0][0][0] = 1;
        back[0][0][1] = 2;
        back[0][0][2] = 3;
        back[0][0][3] = 4;
        back[0][0][4] = 5;
        back[0][1][0] = 6;
        back[0][1][1] = 7;
        back[0][1][2] = 8;
        back[0][1][3] = 9;
        back[0][1][4] = 10;
        back[0][2][0] = 11;
        back[0][2][1] = 12;
        back[0][2][2] = 13;
        back[0][2][3] = 14;
        back[0][2][4] = 15;
        back[0][3][0] = 16;
        back[0][3][1] = 17;
        back[0][3][2] = 18;
        back[0][3][3] = 19;
        back[0][3][4] = 20;
        back[0][4][0] = 21;
        back[0][4][1] = 22;
        back[0][4][2] = 23;
        back[0][4][3] = 24;
        back[0][4][4] = 25;
        layer.forward(input);
        double[][][] result = layer.backPropagation(back, 0.1);
        assertEquals(result.length, input.length);
        assertEquals(result[0].length, input[0].length);
        assertEquals(result[0][0].length, input[0][0].length);
    }

    @Test
    public void testSave()
}
