// 
// Decompiled by Procyon v0.5.30
// 

public class Mp extends Np
{
    private double jb;
    private double b;
    
    public Mp() {
    }
    
    public Mp(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
    }
    
    public double b(final double n) {
        if (!super.ob) {
            this.g();
        }
        if (super.pb) {
            return Math.round(this.jb * n + this.b);
        }
        return this.jb * n + this.b;
    }
    
    public double e(final double n) {
        if (!super.ob) {
            this.g();
        }
        return (n - this.b) / this.jb;
    }
    
    protected void g() {
        if (super.nb != super.mb && super.lb != super.kb) {
            this.jb = (super.nb - super.mb) / (super.lb - super.kb);
            this.b = (super.kb * super.nb - super.lb * super.mb) / (super.kb - super.lb);
        }
        else {
            this.jb = 1.0;
            this.b = 0.0;
        }
        super.ob = true;
    }
}
