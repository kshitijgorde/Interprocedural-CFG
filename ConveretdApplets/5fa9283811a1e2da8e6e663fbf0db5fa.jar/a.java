// 
// Decompiled by Procyon v0.5.30
// 

public class a extends r
{
    public double p;
    public double d;
    public double a;
    public double n;
    
    public final void p(final z z, final z z2, final z z3) {
        this.p = (z.a - z2.a) * (z.d - z3.d) + (z.a - z3.a) * (z2.d - z.d);
        this.d = (z.p - z2.p) * (z.a - z3.a) + (z.p - z3.p) * (z2.a - z.a);
        this.a = (z2.p - z.p) * (z.d - z3.d) + (z.p - z3.p) * (z.d - z2.d);
        this.n = -this.p * z.p - this.d * z.d - this.a * z.a;
    }
    
    public final double p(final z z) {
        return this.p * z.p + this.d * z.d + this.a * z.a + this.n;
    }
    
    public a() {
        this.p = 0.0;
        this.d = 0.0;
        this.a = 0.0;
        this.n = 0.0;
    }
    
    public a(final z z, final z z2, final z z3) {
        this.p(z, z2, z3);
    }
}
