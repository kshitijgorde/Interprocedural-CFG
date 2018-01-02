import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ArrowTurtle extends TurtlePixels
{
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int DEFAULT_ARROW_HEIGHT = 24;
    private static final int DEFAULT_ARROW_WIDTH = 12;
    
    public ArrowTurtle(final Color color, final float n) {
        this(12, 24, color, n);
    }
    
    public ArrowTurtle(final int n, final int n2, final Color color, final float n3) {
        super(n, n2, color, n3);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        final float n2 = n / 2.0f;
        final int turtleHeight = super.getTurtleHeight();
        final int turtleWidth = super.getTurtleWidth();
        final float n3 = turtleWidth / 4;
        final int round = Math.round(n2 - turtleWidth / 2.0f);
        final int n4 = round + turtleWidth;
        final int round2 = Math.round(n2 - turtleHeight / 2.0f);
        final int n5 = round2 + turtleHeight;
        Math.round(n3);
        final int round3 = Math.round(n2 + turtleHeight / 2.0f - turtleWidth);
        final int round4 = Math.round(n2 + n3 / 2.0f);
        final int round5 = Math.round(n2 - n3 / 2.0f);
        this.fillLinePixels(round3, round4, round3, n4, -16777216);
        this.fillLinePixels(round3, n4, n5, Math.round(n2), -16777216);
        this.fillLinePixels(round3, round5, round3, round, -16777216);
        this.fillLinePixels(round3, round, n5, Math.round(n2), -16777216);
        this.fillLinePixels(round3, round4, round2, round4, -16777216);
        this.fillLinePixels(round2, round4, round2, round5, -16777216);
        this.fillLinePixels(round3, round5, round2, round5, -16777216);
    }
}
