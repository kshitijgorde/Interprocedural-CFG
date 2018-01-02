// 
// Decompiled by Procyon v0.5.30
// 

public class CircleMobile extends Mobile
{
    public CircleMobile(final double n, final double n2) {
        super(n, n2);
    }
    
    public double angleToDistance(final double n) {
        return n * super.radius;
    }
    
    public double distanceToAngle(final double n) {
        return n / super.radius;
    }
    
    public void findPen(final PPoint pPoint, final double n, final double n2, final boolean b, final PPoint pPoint2) {
        final double n3 = pPoint.x + super.radius * Math.cos(n);
        final double n4 = pPoint.y + super.radius * Math.sin(n);
        double n5;
        if (b) {
            n5 = this.distanceToAngle(n2) + 3.141592653589793 + n;
        }
        else {
            n5 = this.distanceToAngle(n2) + n;
        }
        pPoint2.x = n3 + super.penRadius * Math.cos(n5);
        pPoint2.y = n4 + super.penRadius * Math.sin(n5);
    }
    
    public String toString() {
        return "CircleMobile[r=" + super.radius + ", pr=" + super.penRadius + "]";
    }
}
