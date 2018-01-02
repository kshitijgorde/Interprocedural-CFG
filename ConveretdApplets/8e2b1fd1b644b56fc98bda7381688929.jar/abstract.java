// 
// Decompiled by Procyon v0.5.30
// 

public class abstract extends o
{
    private static final double lra = 1.0E-6;
    private double ara;
    private double b;
    
    public static final double n(final double n) {
        return Math.log(n) / Math.log(10.0);
    }
    
    public abstract() {
    }
    
    public abstract(double gra, final double fra, final double era, final double dra) {
        if (gra < 1.0E-6) {
            gra = 1.0E-6;
        }
        super.gra = gra;
        super.fra = fra;
        super.era = era;
        super.dra = dra;
        super.bra = false;
        super.cra = false;
    }
    
    public void a(double gra, final double fra) {
        if (gra < 1.0E-6) {
            gra = 1.0E-6;
        }
        super.gra = gra;
        super.fra = fra;
        super.bra = false;
    }
    
    public double b(double n) {
        if (n <= 1.0E-6) {
            n = 1.0E-6;
        }
        if (!super.bra) {
            this.U();
        }
        if (super.cra) {
            return Math.round(this.ara * n(n) + this.b);
        }
        return this.ara * n(n) + this.b;
    }
    
    public double _(final double n) {
        if (!super.bra) {
            this.U();
        }
        return Math.pow(10.0, (n - this.b) / this.ara);
    }
    
    protected void U() {
        if (super.dra != super.era && super.fra != super.gra && super.fra > 0.0 && super.gra > 0.0) {
            this.ara = (super.dra - super.era) / (n(super.fra) - n(super.gra));
            this.b = (n(super.gra) * super.dra - n(super.fra) * super.era) / (n(super.gra) - n(super.fra));
        }
        else {
            this.ara = 1.0;
            this.b = 0.0;
        }
        super.bra = true;
    }
}
