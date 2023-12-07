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
}
