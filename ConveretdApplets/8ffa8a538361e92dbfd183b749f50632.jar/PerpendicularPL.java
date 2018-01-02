// 
// Decompiled by Procyon v0.5.30
// 

public class PerpendicularPL extends PlaneElement
{
    PointElement E;
    
    PerpendicularPL(final PointElement a, final PointElement e) {
        super.dimension = 2;
        super.A = a;
        this.E = e;
        super.B = new PointElement(this);
        super.C = new PointElement(this);
        super.S = new PointElement();
        super.T = new PointElement();
    }
    
    protected void translate(final double n, final double n2) {
        super.B.translate(n, n2);
        super.C.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.B.rotate(pointElement, n, n2);
        super.C.rotate(pointElement, n, n2);
        super.update();
    }
    
    protected void update() {
        super.U = PointElement.difference(this.E, super.A);
        final double length = super.U.length();
        super.U.times(1.0 / length);
        final double sqrt = Math.sqrt(super.U.x * super.U.x + super.U.y * super.U.y);
        if (sqrt >= 1.0E-6) {
            super.S.x = -super.U.y / sqrt;
            super.S.y = super.U.x / sqrt;
            super.S.z = 0.0;
            super.T.toCross(super.U, super.S);
        }
        else {
            super.S.x = 1.0;
            super.S.y = 0.0;
            super.S.z = 0.0;
            super.T.x = 0.0;
            super.T.y = 1.0;
            super.T.z = 0.0;
        }
        super.B.to(super.S).times(length).plus(super.A);
        super.C.to(super.T).times(length).plus(super.A);
    }
}
