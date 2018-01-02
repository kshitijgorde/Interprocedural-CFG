// 
// Decompiled by Procyon v0.5.30
// 

package b.a.a;

public abstract class f
{
    protected Object a;
    protected double b;
    protected int c;
    protected double d;
    protected double e;
    protected double f;
    protected double g;
    protected double[] h;
    protected double i;
    protected double j;
    protected int k;
    
    public f(final Object a, final double b, final int c, final double d, final double e, final double f, final double g) {
        this.b = 0.0;
        this.c = 0;
        this.k = 0;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public f(final Object o, final double n, final int n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        this(o, n, n2, n3, n4, n5, n6);
        (this.h = new double[1])[0] = n7;
    }
    
    public f(final Object o, final double n, final int n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        this(o, n, n2, n3, n4, n5, n6);
        (this.h = new double[2])[0] = n7;
        this.h[1] = n8;
    }
    
    public double a() {
        int k = 0;
        while (++k <= this.c) {
            this.i = this.d - this.e / (this.g - this.e) * (this.f - this.d);
            this.j = this.a(this.i);
            if (Math.abs(this.j) <= this.b) {
                break;
            }
            if ((this.e < this.g && this.j < 0.0) || (this.e > this.g && this.j > 0.0)) {
                this.d = this.i;
                this.e = this.j;
            }
            else {
                this.f = this.i;
                this.g = this.j;
            }
        }
        this.k = k;
        return this.i;
    }
    
    public abstract double a(final double p0);
}
