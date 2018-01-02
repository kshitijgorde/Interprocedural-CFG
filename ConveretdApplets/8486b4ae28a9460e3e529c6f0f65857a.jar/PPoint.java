// 
// Decompiled by Procyon v0.5.30
// 

public class PPoint
{
    public double x;
    public double y;
    
    public PPoint(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public PPoint(final PPoint pPoint, final double n, final double n2) {
        this.x = pPoint.x + n;
        this.y = pPoint.y + n2;
    }
    
    public PPoint() {
        this.x = 0.0;
        this.y = 0.0;
    }
    
    public PPoint translate(final double n, final double n2) {
        return new PPoint(this.x + n, this.y + n2);
    }
    
    public final double distanceTo(final PPoint pPoint) {
        return Math.sqrt((this.x - pPoint.x) * (this.x - pPoint.x) + (this.y - pPoint.y) * (this.y - pPoint.y));
    }
    
    public String toString() {
        return "PP[" + this.x + "," + this.y + "]";
    }
}
