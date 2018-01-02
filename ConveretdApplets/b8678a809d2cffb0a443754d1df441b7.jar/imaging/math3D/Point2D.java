// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

public class Point2D
{
    private float x;
    private float y;
    
    public Point2D() {
        this(0.0f, 0.0f);
    }
    
    public Point2D(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void add(final float x, final float y) {
        this.addX(x);
        this.addX(y);
    }
    
    public void addX(final float x) {
        this.x += x;
    }
    
    public void addY(final float y) {
        this.y += y;
    }
    
    public void subtract(final float x, final float y) {
        this.subtractX(x);
        this.subtractX(y);
    }
    
    public void subtractX(final float x) {
        this.x -= x;
    }
    
    public void subtractY(final float y) {
        this.y -= y;
    }
    
    public void setTo(final Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
}
