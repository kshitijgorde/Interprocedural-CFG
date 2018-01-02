// 
// Decompiled by Procyon v0.5.30
// 

public class IntersectionPL extends PointElement
{
    PointElement A;
    PointElement B;
    
    IntersectionPL(final PlaneElement ap, final PointElement a, final PointElement b) {
        super.dimension = 0;
        this.A = a;
        this.B = b;
        super.AP = ap;
    }
    
    protected void update() {
        this.toIntersectionPL(super.AP, this.A, this.B);
    }
}
