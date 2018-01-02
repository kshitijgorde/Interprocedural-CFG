// 
// Decompiled by Procyon v0.5.30
// 

public abstract class throws
{
    protected double hka;
    protected double ika;
    protected double Uka;
    protected double Vka;
    protected boolean Wka;
    protected boolean Xka;
    
    public throws() {
        this.Wka = false;
        this.Xka = true;
    }
    
    public throws(final double hka, final double ika, final double uka, final double vka) {
        this.hka = hka;
        this.ika = ika;
        this.Uka = uka;
        this.Vka = vka;
        this.Wka = false;
        this.Xka = true;
    }
    
    public void b(final double hka, final double ika) {
        this.hka = hka;
        this.ika = ika;
        this.Wka = false;
    }
    
    public void _(final double uka, final double vka) {
        this.Uka = uka;
        this.Vka = vka;
        this.Wka = false;
    }
    
    public void g(final boolean xka) {
        this.Xka = xka;
    }
    
    public double k() {
        return this.hka;
    }
    
    public double l() {
        return this.ika;
    }
    
    public double m() {
        return this.Uka;
    }
    
    public double n() {
        return this.Vka;
    }
    
    public abstract double b(final double p0);
    
    public abstract double a(final double p0);
    
    protected abstract void o();
}
