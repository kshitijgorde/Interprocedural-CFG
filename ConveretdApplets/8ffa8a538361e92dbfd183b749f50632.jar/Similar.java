// 
// Decompiled by Procyon v0.5.30
// 

public class Similar extends PointElement
{
    PointElement A;
    PointElement B;
    PointElement D;
    PointElement E;
    PointElement F;
    PlaneElement Q;
    
    Similar(final PointElement a, final PointElement b, final PlaneElement ap, final PointElement d, final PointElement e, final PointElement f, final PlaneElement q) {
        super.dimension = 0;
        this.A = a;
        this.B = b;
        super.AP = ap;
        this.D = d;
        this.E = e;
        this.F = f;
        this.Q = q;
    }
    
    protected void update() {
        this.toSimilar(this.A, this.B, super.AP, this.D, this.E, this.F, this.Q);
    }
}
