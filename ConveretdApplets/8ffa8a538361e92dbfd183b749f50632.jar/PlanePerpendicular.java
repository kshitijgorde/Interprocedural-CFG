// 
// Decompiled by Procyon v0.5.30
// 

public class PlanePerpendicular extends LineElement
{
    PointElement C;
    PointElement D;
    PointElement E;
    PlaneElement P;
    
    PlanePerpendicular(final PointElement c, final PlaneElement p4, final PointElement d, final PointElement e) {
        super.dimension = 1;
        super.A = new PointElement(this.P);
        super.B = new PointElement();
        this.C = c;
        this.D = d;
        this.E = e;
        this.P = p4;
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
        super.B.toCross(this.P.S, this.P.T);
        super.B.times(this.D.distance(this.E)).plus(super.A);
    }
}
