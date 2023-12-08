package TestJNeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
    public void testSave() {
        JNeuro.ConvolutionLayer ConvolutionLayer = new JNeuro.ConvolutionLayer(3, 5, 5, 3);
        ConvolutionLayer.save("testSupport/testConvolutionLayer");
        // check if the file exists
        java.io.File file = new java.io.File("testSupport/testConvolutionLayer");
        assertEquals(true, file.exists());
    }

    @Test
    public void testSave2() {
        double[][][] input = new double[1][5][5];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = j * 4 + k;
                }
            }
        }
        JNeuro.ConvolutionLayer ConvolutionLayer = new JNeuro.ConvolutionLayer(3, 5, 5, 3);
        ConvolutionLayer.forward(input);
        ConvolutionLayer.save("testSupport/testConvolutionLayer");
        JNeuro.ConvolutionLayer ConvolutionLayer2 = null;
        try {
            FileInputStream fileIn = new FileInputStream("testSupport/testConvolutionLayer");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ConvolutionLayer2 = (JNeuro.ConvolutionLayer) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("ConvolutionLayer class not found");
            c.printStackTrace();
            return;
        }
        assertArrayEquals(ConvolutionLayer.getFilters(), ConvolutionLayer2.getFilters());
        assertArrayEquals(ConvolutionLayer.getSensingFields(), ConvolutionLayer2.getSensingFields());
    }

    @Test
    public void testSave3() {
        String pathDoNotExist = "testSupport/model/testConvolutionLayer";
        JNeuro.ConvolutionLayer ConvolutionLayer = new JNeuro.ConvolutionLayer(3, 5, 5, 3);
        ConvolutionLayer.save(pathDoNotExist);
        // check if the file exists
        java.io.File file = new java.io.File(pathDoNotExist);
        assertNotEquals(true, file.exists());
    }

    @Test
    public void testLoad() {
        double[][][] input = new double[1][4][4];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = j * 4 + k;
                }
            }
        }
        JNeuro.ConvolutionLayer ConvolutionLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        ConvolutionLayer.forward(input);
        ConvolutionLayer.save("testSupport/testConvolutionLayer");
        JNeuro.ConvolutionLayer ConvolutionLayer2 = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        ConvolutionLayer2.load("testSupport/testConvolutionLayer");
        assertArrayEquals(ConvolutionLayer.getFilters(), ConvolutionLayer2.getFilters());
        assertArrayEquals(ConvolutionLayer.getSensingFields(), ConvolutionLayer2.getSensingFields());
    }

    @Test
    public void testLoad2() {
        double[][][] input = new double[1][4][4];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = j * 4 + k;
                }
            }
        }
        String pathDoNotExist = "testSupport/model/testConvolutionLayer";
        JNeuro.ConvolutionLayer ConvolutionLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        ConvolutionLayer.forward(input);
        ConvolutionLayer.save(pathDoNotExist);
        JNeuro.ConvolutionLayer loadLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        loadLayer.load(pathDoNotExist);
        JNeuro.ConvolutionLayer emptyLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        // We check the two values of input and output
        assertEquals(emptyLayer.getFilters().length, loadLayer.getFilters().length);
        assertEquals(emptyLayer.getFilters()[0].length, loadLayer.getFilters()[0].length);
        assertEquals(emptyLayer.getFilters()[0][0].length, loadLayer.getFilters()[0][0].length);
        assertArrayEquals(emptyLayer.getSensingFields(), loadLayer.getSensingFields());
    }

    @Test
    public void testLoad3() {
        String pathDoNotExist = "testSupport/model/testConvolutionLayer";
        JNeuro.ConvolutionLayer loadLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        loadLayer.load(pathDoNotExist);
        JNeuro.ConvolutionLayer emptyLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        assertEquals(emptyLayer.getFilters().length, loadLayer.getFilters().length);
        assertEquals(emptyLayer.getFilters()[0].length, loadLayer.getFilters()[0].length);
        assertEquals(emptyLayer.getFilters()[0][0].length, loadLayer.getFilters()[0][0].length);
        assertArrayEquals(emptyLayer.getSensingFields(), loadLayer.getSensingFields());
    }

    @Test
    public void testLoad4() {
        // test to raise class not found exception
        String path = "testSupport/testForAllLoad";
        JNeuro.ConvolutionLayer loadLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        loadLayer.load(path);
        JNeuro.ConvolutionLayer emptyLayer = new JNeuro.ConvolutionLayer(3, 4, 4, 3);
        assertEquals(emptyLayer.getFilters().length, loadLayer.getFilters().length);
        assertEquals(emptyLayer.getFilters()[0].length, loadLayer.getFilters()[0].length);
        assertEquals(emptyLayer.getFilters()[0][0].length, loadLayer.getFilters()[0][0].length);
        assertArrayEquals(emptyLayer.getSensingFields(), loadLayer.getSensingFields());

    }
}
