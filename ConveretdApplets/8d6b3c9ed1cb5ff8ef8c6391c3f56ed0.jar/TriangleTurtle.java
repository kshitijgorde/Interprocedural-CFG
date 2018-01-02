import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class TriangleTurtle extends TurtlePixels
{
    private static final int TURTLE_HEIGHT = 21;
    private static final int TURTLE_WIDTH = 15;
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    
    public TriangleTurtle(final Color color, final float n) {
        super(15, 21, color, n);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        final int n2 = n / 2;
        int i = n2 - 10;
        for (int j = n2 - 7; j <= n2 + 7; ++j) {
            array[j * n + i] = -16777216;
        }
        final int n3 = 21;
        final int n4 = 7;
        final int n5 = 2 * n4;
        final int n6 = 2 * (n4 - n3);
        int n7 = 2 * n4 - n3;
        int n8 = n2 + 7;
        int n9 = n2 - 7;
        while (i <= n2 + 10) {
            if (n7 <= 0) {
                n7 += n5;
                ++i;
            }
            else {
                n7 += n6;
                ++i;
                --n8;
                ++n9;
            }
            array[n9 * n + i] = (array[n8 * n + i] = -16777216);
        }
    }
}
