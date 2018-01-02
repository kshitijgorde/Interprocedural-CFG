// 
// Decompiled by Procyon v0.5.30
// 

public class IntersectionSS extends CircleElement
{
    SphereElement S;
    SphereElement T;
    
    IntersectionSS(final SphereElement s, final SphereElement t) {
        super.dimension = 2;
        this.S = s;
        this.T = t;
        final PointElement pointElement = new PointElement();
        super.A = pointElement;
        super.Center = pointElement;
        super.B = new PointElement();
        super.AP = new PerpendicularPL(super.Center, this.T.Center);
        super.Center.AP = super.AP;
    }
    
    protected void update() {
        final double distance2 = this.T.Center.distance2(this.S.Center);
        final double radius2 = this.T.radius2();
        super.Center.to(this.S.Center).minus(this.T.Center).times(0.5 + (radius2 - this.S.radius2()) / (2.0 * distance2)).plus(this.T.Center);
        final double sqrt = Math.sqrt(radius2 - super.Center.distance2(this.T.Center));
        super.B.to(super.Center);
        final PointElement b = super.B;
        b.z += sqrt;
        super.AP.update();
    }
    
    protected void translate(final double n, final double n2) {
        super.Center.translate(n, n2);
        super.B.translate(n, n2);
        super.AP.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.Center.rotate(pointElement, n, n2);
        super.B.rotate(pointElement, n, n2);
        super.AP.rotate(pointElement, n, n2);
    }
}
