// 
// Decompiled by Procyon v0.5.30
// 

public abstract class o
{
    protected double gra;
    protected double fra;
    protected double era;
    protected double dra;
    protected boolean bra;
    protected boolean cra;
    
    public o() {
        this.bra = false;
        this.cra = true;
    }
    
    public o(final double gra, final double fra, final double era, final double dra) {
        this.gra = gra;
        this.fra = fra;
        this.era = era;
        this.dra = dra;
        this.bra = false;
        this.cra = true;
    }
    
    public void a(final double gra, final double fra) {
        this.gra = gra;
        this.fra = fra;
        this.bra = false;
    }
    
    public void b(final double era, final double dra) {
        this.era = era;
        this.dra = dra;
        this.bra = false;
    }
    
    public void b(final boolean cra) {
        this.cra = cra;
    }
    
    public double _() {
        return this.gra;
    }
    
    public double b() {
        return this.fra;
    }
    
    public double k() {
        return this.era;
    }
    
    public double a() {
        return this.dra;
    }
    
    public abstract double b(final double p0);
    
    public abstract double _(final double p0);
    
    protected abstract void U();
}
