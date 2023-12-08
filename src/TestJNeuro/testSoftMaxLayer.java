package TestJNeuro;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.Test;

import JNeuro.SoftMaxLayer;

public class testSoftMaxLayer {
    @Test
    public void testForward() {
        double[][][] input = new double[3][20][20];
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        double[][][] output = layer.forward(input);
        assertEquals(1, output.length);
        assertEquals(1, output[0].length);
        assertEquals(10, output[0][0].length);
    }

    @Test
    public void testBackPropagation() {
        double[][][] input = new double[3][20][20];
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        double[][][] output = layer.forward(input);
        double[][][] delta = layer.backPropagation(output, 0.1);
        assertEquals(3, delta.length);
        assertEquals(20, delta[0].length);
        assertEquals(20, delta[0][0].length);
    }

    @Test
    public void testSave() {
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer.save("testSupport/testSoftMaxLayer");
        java.io.File file = new java.io.File("testSupport/testSoftMaxLayer");
        assertTrue(file.exists());
    }

    @Test
    public void testSave2() {
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        double[][][] input = new double[3][20][20];
        layer.forward(input);
        layer.save("testSupport/testSoftMaxLayer");
        SoftMaxLayer layer2 = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSupport/testSoftMaxLayer"));
            layer2 = (SoftMaxLayer) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertArrayEquals(layer.getOutput(), layer2.getOutput());
        assertArrayEquals(layer.getBias(), layer2.getBias());
        assertArrayEquals(layer.getInput(), layer2.getInput());
        assertArrayEquals(layer.getWeights(), layer2.getWeights());
    }

    @Test
    public void testSave3() {
        String pathDoNotExist = "testSupport/model/testSoftMaxLayer";
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer.save(pathDoNotExist);
        java.io.File file = new java.io.File(pathDoNotExist);
        assertFalse(file.exists());
    }

    @Test
    public void testLoad() {
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer.save("testSupport/testSoftMaxLayer");
        SoftMaxLayer layer2 = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer2.load("testSupport/testSoftMaxLayer");
        assertArrayEquals(layer.getOutput(), layer2.getOutput());
        assertArrayEquals(layer.getBias(), layer2.getBias());
        assertArrayEquals(layer.getInput(), layer2.getInput());
        assertArrayEquals(layer.getWeights(), layer2.getWeights());
    }

    @Test
    public void testLoad2() {
        double[][][] input = new double[3][20][20];
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer.forward(input);
        String pathDoNotExist = "testSupport/model/testSoftMaxLayer";
        layer.save(pathDoNotExist);
        SoftMaxLayer layer2 = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer2.load(pathDoNotExist);
        for (int i = 0; i < layer.getWeights().length; i++) {
            for (int j = 0; j < layer.getWeights()[0].length; j++) {
                if (layer.getWeights()[i][j] != layer2.getWeights()[i][j]) {
                    assert (true);
                    return;
                }
            }
        }
        assert (false);
    }

    @Test
    public void testLoad3() {
        String path = "testSupport/testForAllLoad";
        SoftMaxLayer layer = new SoftMaxLayer(3, 20, 20, 1, 1, 10);
        layer.load(path);
        // load failed, all bias should be 0
        for (int i = 0; i < layer.getBias()[0].length; i++) {
            assertEquals(0, layer.getBias()[0][i], 0.0001);
        }

    }
}
