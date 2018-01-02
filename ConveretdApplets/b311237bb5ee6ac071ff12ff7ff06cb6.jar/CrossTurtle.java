import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class CrossTurtle extends TurtlePixels
{
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int DEFAULT_CROSS_HEIGHT = 17;
    private static final int DEFAULT_CROSS_WIDTH = 17;
    
    public CrossTurtle(final Color color, final float n) {
        this(17, 17, color, n);
    }
    
    public CrossTurtle(final int n, final int n2, final Color color, final float n3) {
        super((n > 9) ? n : 9, (n2 > 9) ? n2 : 9, color, n3);
    }
    
    private void drawLine(final float n, final float n2, final float n3, final float n4) {
        this.fillLinePixels(Math.round(n) - 1, Math.round(n2) - 1, Math.round(n3) - 1, Math.round(n4) - 1, -16777216);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        final float n2 = n / 2.0f;
        final int turtleHeight = super.getTurtleHeight();
        final float n3 = turtleHeight / 2.0f;
        final int turtleWidth = super.getTurtleWidth();
        final float n4 = turtleWidth / 2.0f;
        float n5 = turtleHeight / 8;
        if (turtleWidth < turtleHeight) {
            n5 = turtleWidth / 8;
        }
        final float n6 = n5 / 2.0f;
        this.drawLine(n2 - n6, n2 + n6, n2 - n6, n2 + n4);
        this.drawLine(n2 - n6, n2 + n4, n2 + n6, n2 + n4);
        this.drawLine(n2 + n6, n2 + n6, n2 + n6, n2 + n4);
        this.drawLine(n2 + n6, n2 + n6, n2 + n3, n2 + n6);
        this.drawLine(n2 + n3, n2 - n6, n2 + n3, n2 + n6);
        this.drawLine(n2 + n6, n2 - n6, n2 + n3, n2 - n6);
        this.drawLine(n2 + n6, n2 - n6, n2 + n6, n2 - n4);
        this.drawLine(n2 - n6, n2 - n4, n2 + n6, n2 - n4);
        this.drawLine(n2 - n6, n2 - n6, n2 - n6, n2 - n4);
        this.drawLine(n2 - n6, n2 - n6, n2 - n3, n2 - n6);
        this.drawLine(n2 - n3, n2 - n6, n2 - n3, n2 + n6);
        this.drawLine(n2 - n3, n2 + n6, n2 - n6, n2 + n6);
    }
}
