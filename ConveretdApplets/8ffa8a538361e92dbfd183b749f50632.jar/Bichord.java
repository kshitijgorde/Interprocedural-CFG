// 
// Decompiled by Procyon v0.5.30
// 

public class Bichord extends LineElement
{
    CircleElement C;
    CircleElement D;
    
    Bichord(final CircleElement c, final CircleElement d) {
        super.dimension = 1;
        super.A = new PointElement();
        super.B = new PointElement();
        this.C = c;
        this.D = d;
        final PointElement a = super.A;
        final PointElement b = super.B;
        final PlaneElement ap = this.C.AP;
        b.AP = ap;
        a.AP = ap;
    }
    
    protected void translate(final double n, final double n2) {
        super.A.translate(n, n2);
        super.B.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.A.rotate(pointElement, n, n2);
        super.B.rotate(pointElement, n, n2);
    }
    
    protected void update() {
        final double radius = this.C.radius();
        final double radius2 = this.D.radius();
        final double distance = this.C.Center.distance(this.D.Center);
        if (distance > radius + radius2) {
            final PointElement a = super.A;
            final PointElement a2 = super.A;
            final PointElement b = super.B;
            final PointElement b2 = super.B;
            final PointElement a3 = super.A;
            final PointElement b3 = super.B;
            final double n = Double.NaN;
            b3.z = n;
            a3.z = n;
            b2.y = n;
            b.x = n;
            a2.y = n;
            a.x = n;
            return;
        }
        final double n2 = (distance * distance + radius * radius - radius2 * radius2) / (2.0 * distance * radius);
        final double sqrt = Math.sqrt(1.0 - n2 * n2);
        super.A.to(this.D.Center).toCircle(this.C);
        super.B.to(super.A);
        super.A.rotate(this.C.Center, n2, sqrt, this.C.AP);
        super.B.rotate(this.C.Center, n2, -sqrt, this.C.AP);
    }
}
