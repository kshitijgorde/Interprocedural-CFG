// 
// Decompiled by Procyon v0.5.30
// 

public class AngleDivider extends PointElement
{
    PointElement A;
    PointElement B;
    PointElement C;
    int n;
    
    AngleDivider(final PointElement b, final PointElement a, final PointElement c, final PlaneElement ap, final int n) {
        super.dimension = 0;
        this.A = a;
        this.B = b;
        this.C = c;
        super.AP = ap;
        this.n = n;
    }
    
    protected void update() {
        final double n = this.A.angle(this.B, this.C, super.AP) / this.n;
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        if (super.AP.isScreen) {
            super.x = this.A.x + cos * (this.B.x - this.A.x) - sin * (this.B.y - this.A.y);
            super.y = this.A.y + sin * (this.B.x - this.A.x) + cos * (this.B.y - this.A.y);
            super.z = 0.0;
        }
        else {
            this.to(this.B).rotate(this.A, cos, sin, super.AP);
        }
        this.toIntersection(this, this.A, this.B, this.C, super.AP);
    }
}
