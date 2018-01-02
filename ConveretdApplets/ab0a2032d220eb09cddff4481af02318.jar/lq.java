// 
// Decompiled by Procyon v0.5.30
// 

public class lq extends Np
{
    private static final double ib = 1.0E-6;
    private double jb;
    private double b;
    
    public static final double a(final double n) {
        return Math.log(n) / Math.log(10.0);
    }
    
    public lq() {
    }
    
    public lq(double kb, final double lb, final double mb, final double nb) {
        if (kb < 1.0E-6) {
            kb = 1.0E-6;
        }
        super.kb = kb;
        super.lb = lb;
        super.mb = mb;
        super.nb = nb;
        super.ob = false;
        super.pb = false;
    }
    
    public void a(double kb, final double lb) {
        if (kb < 1.0E-6) {
            kb = 1.0E-6;
        }
        super.kb = kb;
        super.lb = lb;
        super.ob = false;
    }
    
    public double b(double n) {
        if (n <= 1.0E-6) {
            n = 1.0E-6;
        }
        if (!super.ob) {
            this.g();
        }
        if (super.pb) {
            return Math.round(this.jb * a(n) + this.b);
        }
        return this.jb * a(n) + this.b;
    }
    
    public double e(final double n) {
        if (!super.ob) {
            this.g();
        }
        return Math.pow(10.0, (n - this.b) / this.jb);
    }
    
    protected void g() {
        if (super.nb != super.mb && super.lb != super.kb && super.lb > 0.0 && super.kb > 0.0) {
            this.jb = (super.nb - super.mb) / (a(super.lb) - a(super.kb));
            this.b = (a(super.kb) * super.nb - a(super.lb) * super.mb) / (a(super.kb) - a(super.lb));
        }
        else {
            this.jb = 1.0;
            this.b = 0.0;
        }
        super.ob = true;
    }
}
