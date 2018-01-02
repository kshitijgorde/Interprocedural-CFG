// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Stabile implements PShape
{
    protected PPoint center;
    
    public Stabile(final PPoint center) {
        this.center = center;
    }
    
    public PPoint getCenter() {
        return this.center;
    }
    
    public abstract double distanceToNormal(final double p0, final boolean p1);
    
    public abstract PPoint distanceToPoint(final double p0);
    
    public abstract double distanceToAngle(final double p0);
    
    public abstract double angleToDistance(final double p0);
}
