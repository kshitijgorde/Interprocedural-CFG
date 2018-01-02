// 
// Decompiled by Procyon v0.5.30
// 

public class TGPoint implements Cloneable
{
    private float x;
    private float y;
    
    TGPoint() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
    
    TGPoint(final double n, final double n2) {
        this.x = (float)n;
        this.y = (float)n2;
    }
    
    TGPoint(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    TGPoint(final int n, final int n2) {
        this.x = n;
        this.y = n2;
    }
    
    public int canvasX(final int n) {
        return this.xIntValue() + n / 2;
    }
    
    public int canvasX(final float n, final int n2) {
        return (int)Math.rint(this.x + n) + n2 / 2;
    }
    
    public int canvasY(final int n) {
        return n / 2 - this.yIntValue();
    }
    
    public int canvasY(final float n, final int n2) {
        return n2 / 2 - (int)Math.rint(this.y + n);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return new TGPoint(this.x, this.y);
        }
    }
    
    public TGPoint otherEndPoint(final double n, final double n2) {
        return new TGPoint(this.x + Math.cos(n) * n2, this.y + Math.sin(n) * n2);
    }
    
    public float xFloatValue() {
        return this.x;
    }
    
    public int xIntValue() {
        return (int)Math.rint(this.x);
    }
    
    public float yFloatValue() {
        return this.y;
    }
    
    public int yIntValue() {
        return (int)Math.rint(this.y);
    }
    
    public String toString() {
        return "{" + this.x + "," + this.y + "}";
    }
}
