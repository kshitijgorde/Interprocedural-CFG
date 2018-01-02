// 
// Decompiled by Procyon v0.5.30
// 

public class Intersection extends PointElement
{
    PointElement A;
    PointElement B;
    PointElement C;
    PointElement D;
    
    Intersection(final PointElement a, final PointElement b, final PointElement c, final PointElement d, final PlaneElement ap) {
        super.dimension = 0;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        super.AP = ap;
    }
    
    protected void update() {
        this.toIntersection(this.A, this.B, this.C, this.D, super.AP);
    }
}
