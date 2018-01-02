// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    public double p;
    public double d;
    public double a;
    public double n;
    public double v;
    public double i;
    public double l;
    public double b;
    public double c;
    
    public final void p(final l l) {
        this.p = l.p;
        this.d = l.d;
        this.a = l.a;
        this.n = l.n;
        this.v = l.v;
        this.i = l.i;
    }
    
    public final void p(final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double p = cos * this.p + sin * this.a;
        final double a = -sin * this.p + cos * this.a;
        this.p = p;
        this.a = a;
    }
    
    public final void p(final double p5, final double d, final double a, final double v, final double i) {
        this.p = p5;
        this.d = d;
        this.a = a;
        this.v = v;
        this.i = i;
        this.n = 1.0;
    }
    
    public final l d(final l l, final l i) {
        l.p = this.p + i.p;
        l.d = this.d + i.d;
        l.a = this.a + i.a;
        l.n = this.n + i.n;
        l.v = this.v + i.v;
        l.i = this.i + i.i;
        return l;
    }
    
    public final void p(final double n, final double n2, final double n3, final double n4) {
        final double p4 = this.p * n / n3;
        final double d = this.d * n / n4;
        final double a = this.a * n2 / (n2 - n) - n * n2 / (n2 - n);
        final double a2 = this.a;
        this.p = p4;
        this.d = d;
        this.a = a;
        this.n = a2;
    }
    
    public final l p(final l l, final l i) {
        l.p = this.p - i.p;
        l.d = this.d - i.d;
        l.a = this.a - i.a;
        l.n = this.n - i.n;
        l.v = this.v - i.v;
        l.i = this.i - i.i;
        return l;
    }
    
    public final void d(final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double d = cos * this.d + -sin * this.a;
        final double a = sin * this.d + cos * this.a;
        this.d = d;
        this.a = a;
    }
    
    public final l p(final l l, final double n) {
        l.p = this.p * n;
        l.d = this.d * n;
        l.a = this.a * n;
        l.n = this.n * n;
        l.v = this.v * n;
        l.i = this.i * n;
        return l;
    }
    
    public final void p() {
        this.p /= this.n;
        this.d /= this.n;
        this.a /= this.n;
        if (Math.abs(this.p) < 1.0E-6) {
            this.p = 0.0;
        }
        if (Math.abs(this.d) < 1.0E-6) {
            this.d = 0.0;
        }
        if (Math.abs(this.a) < 1.0E-6) {
            this.a = 0.0;
        }
        this.l = this.v / this.n;
        this.b = this.i / this.n;
        this.c = 1.0 / this.n;
        this.n = 1.0;
    }
}
