// 
// Decompiled by Procyon v0.5.30
// 

public class TurtleTurtle extends TurtlePixels
{
    private static final int TURTLE_HEIGHT = 30;
    private static final int TURTLE_WIDTH = 25;
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    
    public TurtleTurtle(final float n) {
        super(30, 25, n);
    }
    
    public void initTurtlePixels(final int[] array, final int n) {
        final int n2 = (n - 30) / 2;
        final int n3 = (n - 25) / 2;
        this.fillLinePixels(n2 + 29, n3 + 12, n2 + 26, n3 + 15, -16777216);
        this.fillLinePixels(n2 + 29, n3 + 12, n2 + 26, n3 + 9, -16777216);
        this.fillLinePixels(n2 + 26, n3 + 15, n2 + 22, n3 + 14, -16777216);
        this.fillLinePixels(n2 + 26, n3 + 9, n2 + 22, n3 + 10, -16777216);
        this.fillLinePixels(n2 + 22, n3 + 13, n2 + 19, n3 + 18, -16777216);
        this.fillLinePixels(n2 + 16, n3 + 21, n2 + 10, n3 + 20, -16777216);
        this.fillLinePixels(n2 + 5, n3 + 18, n2 + 4, n3 + 13, -16777216);
        this.fillLinePixels(n2 + 22, n3 + 11, n2 + 19, n3 + 6, -16777216);
        this.fillLinePixels(n2 + 16, n3 + 3, n2 + 10, n3 + 4, -16777216);
        this.fillLinePixels(n2 + 5, n3 + 6, n2 + 4, n3 + 11, -16777216);
        this.fillLinePixels(n2 + 0, n3 + 12, n2 + 4, n3 + 13, -16777216);
        this.fillLinePixels(n2 + 0, n3 + 12, n2 + 4, n3 + 11, -16777216);
        this.fillLinePixels(n2 + 20, n3 + 24, n2 + 19, n3 + 19, -16777216);
        this.fillLinePixels(n2 + 19, n3 + 24, n2 + 17, n3 + 22, -16777216);
        this.fillLinePixels(n2 + 20, n3 + 0, n2 + 19, n3 + 5, -16777216);
        this.fillLinePixels(n2 + 19, n3 + 0, n2 + 17, n3 + 2, -16777216);
        this.fillLinePixels(n2 + 5, n3 + 22, n2 + 9, n3 + 20, -16777216);
        this.fillLinePixels(n2 + 4, n3 + 22, n2 + 6, n3 + 19, -16777216);
        this.fillLinePixels(n2 + 5, n3 + 2, n2 + 9, n3 + 4, -16777216);
        this.fillLinePixels(n2 + 4, n3 + 2, n2 + 6, n3 + 5, -16777216);
    }
}
