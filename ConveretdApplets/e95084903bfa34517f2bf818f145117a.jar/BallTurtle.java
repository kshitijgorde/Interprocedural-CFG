import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class BallTurtle extends TurtlePixels
{
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int DEFAULT_TURTLE_DIAMETER = 15;
    
    public BallTurtle(final Color color, final float n) {
        this(15, color, n);
    }
    
    public BallTurtle(final int n, final Color color, final float n2) {
        super(n, n, color, n2);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        this.fillCirclePixels(n / 2, n / 2, super.getTurtleHeight() / 2, -16777216);
    }
}
