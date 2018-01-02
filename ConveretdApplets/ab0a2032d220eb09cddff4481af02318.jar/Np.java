// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Np
{
    protected double kb;
    protected double lb;
    protected double mb;
    protected double nb;
    protected boolean ob;
    protected boolean pb;
    
    public Np() {
        this.ob = false;
        this.pb = true;
    }
    
    public Np(final double kb, final double lb, final double mb, final double nb) {
        this.kb = kb;
        this.lb = lb;
        this.mb = mb;
        this.nb = nb;
        this.ob = false;
        this.pb = true;
    }
    
    public void a(final double kb, final double lb) {
        this.kb = kb;
        this.lb = lb;
        this.ob = false;
    }
    
    public void b(final double mb, final double nb) {
        this.mb = mb;
        this.nb = nb;
        this.ob = false;
    }
    
    public void b(final boolean pb) {
        this.pb = pb;
    }
    
    public double _() {
        return this.kb;
    }
    
    public double b() {
        return this.lb;
    }
    
    public double l() {
        return this.mb;
    }
    
    public double a() {
        return this.nb;
    }
    
    public abstract double b(final double p0);
    
    public abstract double e(final double p0);
    
    protected abstract void g();
}
