// 
// Decompiled by Procyon v0.5.30
// 

public class var extends throws
{
    private double Tka;
    private double b;
    
    public var() {
    }
    
    public var(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
    }
    
    public double b(final double n) {
        if (!super.Wka) {
            this.o();
        }
        if (super.Xka) {
            return Math.round(this.Tka * n + this.b);
        }
        return this.Tka * n + this.b;
    }
    
    public double a(final double n) {
        if (!super.Wka) {
            this.o();
        }
        return (n - this.b) / this.Tka;
    }
    
    protected void o() {
        if (super.Vka != super.Uka && super.ika != super.hka) {
            this.Tka = (super.Vka - super.Uka) / (super.ika - super.hka);
            this.b = (super.hka * super.Vka - super.ika * super.Uka) / (super.hka - super.ika);
        }
        else {
            this.Tka = 1.0;
            this.b = 0.0;
        }
        super.Wka = true;
    }
}
