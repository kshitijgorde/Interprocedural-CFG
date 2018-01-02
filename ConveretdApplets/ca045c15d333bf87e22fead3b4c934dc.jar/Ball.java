// 
// Decompiled by Procyon v0.5.30
// 

public class Ball
{
    private double x;
    private double y;
    
    public Ball(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setXY(final double x, final double y) {
        this.y = y;
        this.x = x;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
}
