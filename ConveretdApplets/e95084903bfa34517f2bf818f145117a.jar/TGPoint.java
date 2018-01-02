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
    
    public int imageX(final int n) {
        return n / 2 + this.xIntValue();
    }
    
    public int imageX(final float n, final int n2) {
        return n2 / 2 + Math.round(this.x + n);
    }
    
    public int imageY(final int n) {
        return n / 2 - this.yIntValue();
    }
    
    public int imageY(final float n, final int n2) {
        return n2 / 2 - Math.round(this.y + n);
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
        final double sin = Math.sin(n);
        double n3 = Math.cos(n) * n2;
        if ((n3 < 0.0 && n3 > -1.0E-5) || (n3 > 0.0 && n3 < 1.0E-5)) {
            n3 = 0.0;
        }
        double n4 = sin * n2;
        if ((n4 < 0.0 && n4 > -1.0E-5) || (n4 > 0.0 && n4 < 1.0E-5)) {
            n4 = 0.0;
        }
        return new TGPoint(this.x + n3, this.y + n4);
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
    
    public float xFloatValue() {
        return this.x;
    }
    
    public int xIntValue() {
        return Math.round(this.x);
    }
    
    public float yFloatValue() {
        return this.y;
    }
    
    public int yIntValue() {
        return Math.round(this.y);
    }
    
    public String toString() {
        return "{" + this.x + "," + this.y + "}";
    }
}
