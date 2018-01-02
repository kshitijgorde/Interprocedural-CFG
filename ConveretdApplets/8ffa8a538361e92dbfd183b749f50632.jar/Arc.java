// 
// Decompiled by Procyon v0.5.30
// 

public class Arc extends SectorElement
{
    PointElement M;
    
    Arc(final PointElement a, final PointElement m, final PointElement b, final PlaneElement p4) {
        super.dimension = 2;
        super.Center = new PointElement();
        super.A = a;
        this.M = m;
        super.B = b;
        super.P = p4;
    }
    
    protected void update() {
        super.P.update();
        super.Center.toCircumcenter(super.A, this.M, super.B);
    }
    
    protected void translate(final double n, final double n2) {
        super.Center.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.Center.rotate(pointElement, n, n2);
    }
}
