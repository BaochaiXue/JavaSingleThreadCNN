package TestJNeuro;

import JNeuro.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestJNeuro {
    @Test
    public void testRecoginizeImage() {
        String pathOfTheImage = "testSupport/8digits.jpg";
        String pathOfTheWeight = "testSupport/testWeight";
        try {
            String result = JNeuro.recoginizeImage(pathOfTheImage, pathOfTheWeight);
            assertEquals(result.length(), 8);
        } catch (Exception e) {
            // the exception is not expected
            assert false;
        }
    }

    @Test
    public void testMain() {
        String[] args = new String[0];
        try {
            JNeuro.main(args);
        } catch (Exception e) {
            // the exception is not expected
            assert false;
        }
    }

    @Test
    public void TestJNeuroAll() {
        JNeuro jneuro = new JNeuro();
        assertEquals(jneuro.getClass().getName(), "JNeuro.JNeuro");
    }

}
