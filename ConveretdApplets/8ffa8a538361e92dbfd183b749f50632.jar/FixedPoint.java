// 
// Decompiled by Procyon v0.5.30
// 

public class FixedPoint extends PointElement
{
    PointElement A;
    PointElement B;
    double initx;
    double inity;
    double initz;
    
    FixedPoint(final double n, final double n2, final double n3) {
        super.dimension = 0;
        this.initx = n;
        super.x = n;
        this.inity = n2;
        super.y = n2;
        this.initz = n3;
        super.z = n3;
    }
    
    protected void reset() {
        super.x = this.initx;
        super.y = this.inity;
        super.z = this.initz;
    }
}
