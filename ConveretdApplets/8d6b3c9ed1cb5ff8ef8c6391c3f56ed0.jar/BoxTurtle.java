import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class BoxTurtle extends TurtlePixels
{
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int DEFAULT_BOX_HEIGHT = 15;
    private static final int DEFAULT_BOX_WIDTH = 15;
    
    public BoxTurtle(final Color color, final float n) {
        this(15, 15, color, n);
    }
    
    public BoxTurtle(final int n, final int n2, final Color color, final float n3) {
        super(n, n2, color, n3);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        final int turtleHeight = super.getTurtleHeight();
        final int turtleWidth = super.getTurtleWidth();
        final float n2 = n / 2.0f;
        final float n3 = n2 - turtleHeight / 2.0f;
        final float n4 = n2 - turtleWidth / 2.0f;
        final int round = Math.round(n3);
        final int round2 = Math.round(n4);
        final int n5 = round + turtleHeight - 1;
        final int n6 = round2 + turtleWidth - 1;
        this.fillLinePixels(round, round2, round, n6, -16777216);
        this.fillLinePixels(round, round2, n5, round2, -16777216);
        this.fillLinePixels(n5, round2, n5, n6, -16777216);
        this.fillLinePixels(round, n6, n5, n6, -16777216);
    }
}
