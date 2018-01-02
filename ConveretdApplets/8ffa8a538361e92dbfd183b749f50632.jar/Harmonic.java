// 
// Decompiled by Procyon v0.5.30
// 

public class Harmonic extends PointElement
{
    PointElement B;
    PointElement C;
    PointElement D;
    PointElement E;
    PointElement F;
    PointElement M;
    
    Harmonic(final PointElement b, final PointElement c, final PointElement d) {
        super.dimension = 0;
        this.B = b;
        this.C = c;
        this.D = d;
        this.E = new PointElement();
        this.F = new PointElement();
        this.M = new PointElement();
    }
    
    protected void update() {
        if (this.B.z == 0.0 && this.C.z == 0.0 && this.D.z == 0.0) {
            final double n = this.B.x - this.C.x;
            final double n2 = this.B.y - this.C.y;
            final double n3 = this.B.x - this.D.x;
            final double n4 = this.B.y - this.D.y;
            final double n5 = this.C.x * n3 - this.C.y * n4;
            final double n6 = this.C.x * n4 + this.C.y * n3;
            final double n7 = this.D.x * n - this.D.y * n2;
            final double n8 = this.D.x * n2 + this.D.y * n;
            final double n9 = (n3 + n) * (n3 + n) + (n4 + n2) * (n4 + n2);
            final double n10 = (n5 + n7) * (n3 + n) + (n6 + n8) * (n4 + n2);
            final double n11 = -(n5 + n7) * (n4 + n2) + (n6 + n8) * (n3 + n);
            super.x = n10 / n9;
            super.y = n11 / n9;
            return;
        }
        this.M.to(this.C).plus(this.D).times(0.5);
        this.F.to(this.C).minus(this.M);
        this.E.to(this.B).minus(this.M);
        this.to(this.F).times(2.0 * PointElement.dot(this.E, this.F)).minus(this.E.times(this.F.length2())).times(1.0 / this.E.length2()).plus(this.M);
    }
}
