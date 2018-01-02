// 
// Decompiled by Procyon v0.5.30
// 

public class Court
{
    private static final int WALL_WIDTH = 8;
    private static final int WALL_HAF_WID = 4;
    private int leftX;
    private int rightX;
    private int lowerY;
    private int upperY;
    private Turtle turtle;
    
    public Court(final Turtle turtle, final int leftX, final int rightX, final int lowerY, final int upperY) {
        this.turtle = turtle;
        this.leftX = leftX;
        this.rightX = rightX;
        this.lowerY = lowerY;
        this.upperY = upperY;
        this.paint();
    }
    
    private int hitLeft(final int n, final int n2, final int n3, final int n4) {
        if (n4 < 270) {
            return 90 + (270 - n4);
        }
        if (n4 > 270) {
            return 90 - (n4 - 270);
        }
        return n4;
    }
    
    private int hitRight(final int n, final int n2, final int n3, final int n4) {
        if (n4 > 90) {
            return 270 - (n4 - 90);
        }
        if (n4 < 90) {
            return 270 + (90 - n4);
        }
        return n4;
    }
    
    private int hitTop(final int n, final int n2, final int n3, final int n4) {
        if (n4 < 90) {
            return 90 + (90 - n4);
        }
        if (n4 > 270) {
            return 180 + (90 - (n4 - 270));
        }
        return n4;
    }
    
    public int checkHitWall(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 / 2;
        if (n - n5 <= this.leftX + 4) {
            return this.hitLeft(n, n2, n3, n4);
        }
        if (n2 + n5 >= this.upperY - 4) {
            return this.hitTop(n, n2, n3, n4);
        }
        if (n + n5 >= this.rightX - 4) {
            return this.hitRight(n, n2, n3, n4);
        }
        return n4;
    }
    
    public void paint() {
        this.turtle.penup();
        this.turtle.setxy(this.leftX, this.lowerY);
        this.turtle.pendown();
        this.turtle.setpensize(8);
        this.turtle.setpencolor(0);
        this.turtle.sety(this.upperY);
        this.turtle.setx(this.rightX);
        this.turtle.sety(this.lowerY);
    }
}
