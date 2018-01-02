// 
// Decompiled by Procyon v0.5.30
// 

public class Perpendicular extends LineElement
{
    PointElement C;
    PointElement D;
    PointElement E;
    PointElement F;
    PlaneElement P;
    
    Perpendicular(final PointElement c, final PointElement d, final PlaneElement p5, final PointElement e, final PointElement f) {
        super.dimension = 1;
        this.P = p5;
        super.A = new PointElement(this.P);
        super.B = new PointElement(this.P);
        this.C = c;
        this.D = d;
        this.E = e;
        this.F = f;
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
        super.A.to(this.C).toPlane(this.P);
        super.B.to(this.D).minus(super.A);
        final double dot = PointElement.dot(super.B, this.P.S);
        final double dot2 = PointElement.dot(super.B, this.P.T);
        final double sqrt = Math.sqrt(this.E.distance2(this.F) / (dot * dot + dot2 * dot2));
        final double n = -dot / sqrt;
        final double n2 = dot2 / sqrt;
        super.B.x = n2 * this.P.S.x + n * this.P.T.x + super.A.x;
        super.B.y = n2 * this.P.S.y + n * this.P.T.y + super.A.y;
        super.B.z = n2 * this.P.S.z + n * this.P.T.z + super.A.z;
    }
}
