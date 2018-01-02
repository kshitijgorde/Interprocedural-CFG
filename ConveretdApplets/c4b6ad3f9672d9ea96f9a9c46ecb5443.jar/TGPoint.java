// 
// Decompiled by Procyon v0.5.30
// 

public class TGPoint implements Cloneable
{
    float x;
    float y;
    
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
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return new TGPoint(this.x, this.y);
        }
    }
    
    public String toString() {
        return "x=" + this.x + ", y=" + this.y;
    }
}
