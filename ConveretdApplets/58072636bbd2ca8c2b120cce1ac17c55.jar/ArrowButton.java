import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JButton;

// 
// Decompiled by Procyon v0.5.30
// 

public class ArrowButton extends JButton
{
    public static int UP_ARROW;
    public static int DOWN_ARROW;
    public static int LEFT_ARROW;
    public static int RIGHT_ARROW;
    private static int width;
    private static int height;
    private static int border;
    private int direction;
    
    public ArrowButton(final int direction) {
        super("");
        this.direction = direction;
        this.setPreferredSize(new Dimension(ArrowButton.width, ArrowButton.height));
    }
    
    public void paint(final Graphics graphics) {
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        super.paint(graphics);
        if (this.direction == ArrowButton.UP_ARROW) {
            array[0] = ArrowButton.border;
            array[1] = Math.round(ArrowButton.width / 2);
            array[2] = ArrowButton.width - ArrowButton.border;
            array2[0] = ArrowButton.height - ArrowButton.border;
            array2[1] = ArrowButton.border;
            array2[2] = ArrowButton.height - ArrowButton.border;
        }
        else if (this.direction == ArrowButton.DOWN_ARROW) {
            array[0] = ArrowButton.border;
            array[1] = Math.round(ArrowButton.width / 2);
            array[2] = ArrowButton.width - ArrowButton.border;
            array2[0] = ArrowButton.border;
            array2[1] = ArrowButton.height - ArrowButton.border;
            array2[2] = ArrowButton.border;
        }
        else if (this.direction == ArrowButton.LEFT_ARROW) {
            array[0] = ArrowButton.border;
            array[1] = ArrowButton.width - ArrowButton.border;
            array[2] = ArrowButton.width - ArrowButton.border;
            array2[0] = Math.round(ArrowButton.height / 2);
            array2[1] = ArrowButton.border;
            array2[2] = ArrowButton.height - ArrowButton.border;
        }
        else {
            array[0] = ArrowButton.border;
            array[1] = ArrowButton.border;
            array[2] = ArrowButton.width - ArrowButton.border;
            array2[0] = ArrowButton.border;
            array2[1] = ArrowButton.height - ArrowButton.border;
            array2[2] = Math.round(ArrowButton.height / 2);
        }
        graphics.fillPolygon(array, array2, 3);
    }
    
    static {
        ArrowButton.UP_ARROW = 0;
        ArrowButton.DOWN_ARROW = 1;
        ArrowButton.LEFT_ARROW = 2;
        ArrowButton.RIGHT_ARROW = 3;
        ArrowButton.width = 20;
        ArrowButton.height = 20;
        ArrowButton.border = 3;
    }
}
