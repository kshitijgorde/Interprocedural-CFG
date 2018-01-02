// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Mobile implements PShape
{
    double radius;
    double penRadius;
    
    public Mobile(final double radius, final double penRadius) {
        this.radius = radius;
        this.penRadius = penRadius;
    }
    
    public abstract void findPen(final PPoint p0, final double p1, final double p2, final boolean p3, final PPoint p4);
    
    public abstract double distanceToAngle(final double p0);
    
    public abstract double angleToDistance(final double p0);
}
