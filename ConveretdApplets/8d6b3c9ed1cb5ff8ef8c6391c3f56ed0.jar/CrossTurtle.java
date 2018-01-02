import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class CrossTurtle extends TurtlePixels
{
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int DEFAULT_CROSS_HEIGHT = 15;
    private static final int DEFAULT_CROSS_WIDTH = 15;
    
    public CrossTurtle(final Color color, final float n) {
        this(15, 15, color, n);
    }
    
    public CrossTurtle(final int n, final int n2, final Color color, final float n3) {
        super(n, n2, color, n3);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        final float n2 = n / 2.0f;
        final int turtleHeight = super.getTurtleHeight();
        final int turtleWidth = super.getTurtleWidth();
        float n3 = turtleHeight / 8;
        if (turtleWidth < turtleHeight) {
            n3 = turtleWidth / 8;
        }
        final int round = Math.round((turtleHeight - n3) / 2.0f);
        final int round2 = Math.round((turtleWidth - n3) / 2.0f);
        final int round3 = Math.round(n3);
        final int round4 = Math.round(n2 - n3 / 2.0f);
        final int round5 = Math.round(n2 + n3 / 2.0f);
        this.fillLinePixels(round4, round5, round4, round5 + round, -16777216);
        this.fillLinePixels(round4, round5 + round, round4 + round3, round5 + round, -16777216);
        this.fillLinePixels(round4 + round3, round5, round4 + round3, round5 + round, -16777216);
        this.fillLinePixels(round4 + round3, round5, round4 + round3 + round2, round5, -16777216);
        this.fillLinePixels(round4 + round3 + round2, round5, round4 + round3 + round2, round5 - round3, -16777216);
        this.fillLinePixels(round4 + round3, round5 - round3, round4 + round3 + round2, round5 - round3, -16777216);
        this.fillLinePixels(round4 + round3, round5 - round3, round4 + round3, round5 - (round3 + round), -16777216);
        this.fillLinePixels(round4, round5 - (round3 + round), round4 + round3, round5 - (round3 + round), -16777216);
        this.fillLinePixels(round4, round5 - round3, round4, round5 - (round3 + round), -16777216);
        this.fillLinePixels(round4, round5 - round3, round4 - round2, round5 - round3, -16777216);
        this.fillLinePixels(round4 - round2, round5, round4 - round2, round5 - round3, -16777216);
        this.fillLinePixels(round4, round5, round4 - round2, round5, -16777216);
    }
}
