// 
// Decompiled by Procyon v0.5.30
// 

public class Chord extends LineElement
{
    PointElement D;
    PointElement E;
    CircleElement C;
    
    Chord(final PointElement d, final PointElement e, final CircleElement c) {
        super.dimension = 1;
        this.C = c;
        this.D = d;
        this.E = e;
        super.A = new PointElement(this.C.AP);
        super.B = new PointElement(this.C.AP);
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
        super.B.to(this.C.Center).toLine(this.D, this.E, false);
        final double distance2 = this.C.Center.distance2(super.B);
        final double radius2 = this.C.radius2();
        if (distance2 > radius2) {
            final PointElement a = super.A;
            final PointElement a2 = super.A;
            final PointElement a3 = super.A;
            final PointElement b = super.B;
            final PointElement b2 = super.B;
            final PointElement b3 = super.B;
            final double n = Double.NaN;
            b3.z = n;
            b2.y = n;
            b.x = n;
            a3.z = n;
            a2.y = n;
            a.x = n;
            return;
        }
        final double sqrt = Math.sqrt(radius2 - distance2);
        final double n2 = sqrt / this.D.distance(super.B);
        if (n2 < 1.0E10) {
            super.A.to(this.D).minus(super.B).times(n2).plus(super.B);
        }
        else {
            super.A.to(this.E).minus(super.B).times(sqrt / this.E.distance(super.B)).plus(super.B);
        }
        super.B.times(2.0).minus(super.A);
    }
}
