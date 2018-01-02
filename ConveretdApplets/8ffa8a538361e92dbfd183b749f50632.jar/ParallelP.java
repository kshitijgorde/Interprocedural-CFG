// 
// Decompiled by Procyon v0.5.30
// 

public class ParallelP extends PlaneElement
{
    PlaneElement P;
    
    ParallelP(final PlaneElement p2, final PointElement a) {
        super.dimension = 2;
        this.P = p2;
        super.A = a;
        super.B = new PointElement(this);
        super.C = new PointElement(this);
        super.S = this.P.S;
        super.T = this.P.T;
        super.U = this.P.U;
    }
    
    protected void translate(final double n, final double n2) {
        super.B.translate(n, n2);
        super.C.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.B.rotate(pointElement, n, n2);
        super.C.rotate(pointElement, n, n2);
    }
    
    protected void update() {
        super.B.to(this.P.B).minus(this.P.A).plus(super.A);
        super.C.to(this.P.C).minus(this.P.A).plus(super.A);
    }
}
