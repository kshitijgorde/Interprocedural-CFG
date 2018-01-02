// 
// Decompiled by Procyon v0.5.30
// 

public class Layoff extends PointElement
{
    PointElement A;
    PointElement C;
    PointElement D;
    PointElement E;
    PointElement F;
    
    Layoff(final PointElement a, final PointElement c, final PointElement d, final PointElement e, final PointElement f) {
        super.dimension = 0;
        this.A = a;
        this.C = c;
        this.D = d;
        this.E = e;
        this.F = f;
        if (this.A.AP == this.C.AP && this.A.AP == this.D.AP) {
            super.AP = this.A.AP;
        }
    }
    
    protected void update() {
        final double n = this.E.distance(this.F) / this.C.distance(this.D);
        this.to(this.D).minus(this.C);
        this.times(n).plus(this.A);
    }
}
