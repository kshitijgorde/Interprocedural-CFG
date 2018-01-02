// 
// Decompiled by Procyon v0.5.30
// 

public class d extends throws
{
    private static final double Ska = 1.0E-6;
    private double Tka;
    private double b;
    
    public static final double l(final double n) {
        return Math.log(n) / Math.log(10.0);
    }
    
    public d() {
    }
    
    public d(double hka, final double ika, final double uka, final double vka) {
        if (hka < 1.0E-6) {
            hka = 1.0E-6;
        }
        super.hka = hka;
        super.ika = ika;
        super.Uka = uka;
        super.Vka = vka;
        super.Wka = false;
        super.Xka = false;
    }
    
    public void b(double hka, final double ika) {
        if (hka < 1.0E-6) {
            hka = 1.0E-6;
        }
        super.hka = hka;
        super.ika = ika;
        super.Wka = false;
    }
    
    public double b(double n) {
        if (n <= 1.0E-6) {
            n = 1.0E-6;
        }
        if (!super.Wka) {
            this.o();
        }
        if (super.Xka) {
            return Math.round(this.Tka * l(n) + this.b);
        }
        return this.Tka * l(n) + this.b;
    }
    
    public double a(final double n) {
        if (!super.Wka) {
            this.o();
        }
        return Math.pow(10.0, (n - this.b) / this.Tka);
    }
    
    protected void o() {
        if (super.Vka != super.Uka && super.ika != super.hka && super.ika > 0.0 && super.hka > 0.0) {
            this.Tka = (super.Vka - super.Uka) / (l(super.ika) - l(super.hka));
            this.b = (l(super.hka) * super.Vka - l(super.ika) * super.Uka) / (l(super.hka) - l(super.ika));
        }
        else {
            this.Tka = 1.0;
            this.b = 0.0;
        }
        super.Wka = true;
    }
}
