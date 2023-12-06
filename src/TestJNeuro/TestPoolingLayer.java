package TestJNeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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

    @Test
    public void testBackPropagation() {
        double[][][] input = new double[1][4][4];
        double[][][] back = new double[1][2][2];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = j * 4 + k;
                }
            }
        }
        back[0][0][0] = 1;
        back[0][0][1] = 2;
        back[0][1][0] = 3;
        back[0][1][1] = 4;
        double[][][] output = new double[1][4][4];
        output[0][0][0] = 0;
        output[0][0][1] = 0;
        output[0][0][2] = 0;
        output[0][0][3] = 0;
        output[0][1][0] = 0;
        output[0][1][1] = 1;
        output[0][1][2] = 0;
        output[0][1][3] = 2;
        output[0][2][0] = 0;
        output[0][2][1] = 0;
        output[0][2][2] = 0;
        output[0][2][3] = 0;
        output[0][3][0] = 0;
        output[0][3][1] = 3;
        output[0][3][2] = 0;
        output[0][3][3] = 4;
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        poolingLayer.forward(input);
        double[][][] result = poolingLayer.backPropagation(back, 0.1);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int k = 0; k < result[0][0].length; k++) {
                    assertEquals(output[i][j][k], result[i][j][k], 1e-10);

                }
            }
        }
    }

    @Test
    public void testSave() {
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        poolingLayer.save("testPoolingLayer");
        // check if the file exists
        java.io.File file = new java.io.File("testPoolingLayer");
        assertEquals(true, file.exists());
    }

    @Test
    public void testSave2() {
        double[][][] input = new double[1][4][4];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                for (int k = 0; k < input[0][0].length; k++) {
                    input[i][j][k] = j * 4 + k;
                }
            }
        }
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        poolingLayer.forward(input);
        poolingLayer.save("testPoolingLayer");
        JNeuro.PoolingLayer poolingLayer2 = null;
        try {
            FileInputStream fileIn = new FileInputStream("testPoolingLayer");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            poolingLayer2 = (JNeuro.PoolingLayer) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("PoolingLayer class not found");
            c.printStackTrace();
            return;
        }
        assertEquals(poolingLayer, poolingLayer2);
    }

    @Test
    public void testSave3() {
        String pathDoNotExist = "model/testPoolingLayer";
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        poolingLayer.save(pathDoNotExist);
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
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        poolingLayer.forward(input);
        poolingLayer.save("testPoolingLayer");
        JNeuro.PoolingLayer poolingLayer2 = null;
        try {
            FileInputStream fileIn = new FileInputStream("testPoolingLayer");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            poolingLayer2 = (JNeuro.PoolingLayer) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("PoolingLayer class not found");
            c.printStackTrace();
            return;
        }
        assertEquals(poolingLayer, poolingLayer2);
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
        String pathDoNotExist = "model/testPoolingLayer";
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        poolingLayer.forward(input);
        poolingLayer.save(pathDoNotExist);
        JNeuro.PoolingLayer loadLayer = new JNeuro.PoolingLayer();
        loadLayer.load(pathDoNotExist);
        JNeuro.PoolingLayer emptyLayer = new JNeuro.PoolingLayer();
        assertEquals(emptyLayer, loadLayer);
    }

    @Test
    public void testLoad3() {
        String pathDoNotExist = "model/testPoolingLayer";
        JNeuro.PoolingLayer loadLayer = new JNeuro.PoolingLayer();
        loadLayer.load(pathDoNotExist);
        JNeuro.PoolingLayer emptyLayer = new JNeuro.PoolingLayer();
        assertEquals(emptyLayer, loadLayer);
    }

    @Test
    public void testEquals() {
        JNeuro.PoolingLayer poolingLayer = new JNeuro.PoolingLayer();
        JNeuro.PoolingLayer poolingLayer2 = new JNeuro.PoolingLayer();
        assertEquals(poolingLayer, poolingLayer2);
    }

}
