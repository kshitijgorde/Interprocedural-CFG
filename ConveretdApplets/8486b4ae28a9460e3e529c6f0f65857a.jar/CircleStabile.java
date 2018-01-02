// 
// Decompiled by Procyon v0.5.30
// 

public class CircleStabile extends Stabile
{
    protected double radius;
    
    public CircleStabile(final PPoint pPoint, final double radius) {
        super(pPoint);
        this.radius = radius;
    }
    
    public double angleToDistance(final double n) {
        return n * this.radius;
    }
    
    public double distanceToAngle(final double n) {
        return n / this.radius;
    }
    
    public double distanceToNormal(final double n, final boolean b) {
        double distanceToAngle = this.distanceToAngle(n);
        if (b) {
            distanceToAngle += 3.141592653589793;
        }
        return distanceToAngle;
    }
    
    public PPoint distanceToPoint(final double n) {
        final double distanceToAngle = this.distanceToAngle(n);
        return new PPoint(super.center, this.radius * Math.cos(distanceToAngle), this.radius * Math.sin(distanceToAngle));
    }
    
    public String toString() {
        return "CircleStabile[c=" + super.center + ", rad=" + this.radius + "]";
    }
}
