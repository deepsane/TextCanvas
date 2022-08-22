import org.junit.Test;

import static org.junit.Assert.*;

public class DrawerTest {
    private Drawer drawer = Drawer.getInstance();

    @Test
    public void getInstance() {
        assert (drawer != null);
    }

    @Test
    public void getWidth() {
        assert (drawer.getWidth() == 0);
    }

    @Test
    public void setWidth() {
        drawer.setWidth(1);
        assert (drawer.getWidth() == 1);
    }

    @Test
    public void getHeight() {
        assert (drawer.getWidth() == 0);
    }

    @Test
    public void setHeight() {
        drawer.setHeight(1);
        assert (drawer.getHeight() == 1);
    }

    @Test
    public void createCanvas() {
        drawer.createCanvas(10, 10);
        assertEquals (10, drawer.getWidth());
        assertEquals (10, drawer.getHeight());
        assertEquals (12, drawer.getCanvas().length);
        assertEquals (12, drawer.getCanvas()[0].length );
    }

    @Test
    public void drawHorizontalLine() {
        drawer.createCanvas(10, 10);
        drawer.drawLine(1 ,1, 1, 3, false);
        assertEquals ('x', drawer.getCanvas()[1][1].charValue());
        assertEquals ('x', drawer.getCanvas()[1][2].charValue());
        assertEquals ('x', drawer.getCanvas()[1][3].charValue());
    }

    @Test
    public void drawVerticalLine() {
        drawer.createCanvas(10, 10);
        drawer.drawLine(1 ,1, 3, 1, false);
        assertEquals ('x', drawer.getCanvas()[1][1].charValue());
        assertEquals ('x', drawer.getCanvas()[2][1].charValue());
        assertEquals ('x', drawer.getCanvas()[3][1].charValue());
    }

    @Test
    public void getCanvas() {
        assertNotNull (drawer.getCanvas());
    }

    @Test
    public void setCanvas() {
        drawer.setCanvas(new Character[5][5]);
        assertEquals(3, drawer.getHeight());
        assertEquals(3, drawer.getWidth());
    }

    @Test
    public void reset() {
        drawer.reset();
        assertEquals(-2, drawer.getWidth());
        assertEquals(-2, drawer.getHeight());
    }
}