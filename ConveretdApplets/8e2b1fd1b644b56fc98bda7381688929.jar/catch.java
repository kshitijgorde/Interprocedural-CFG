// 
// Decompiled by Procyon v0.5.30
// 

public class catch extends o
{
    private double ara;
    private double b;
    
    public catch() {
    }
    
    public catch(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
    }
    
    public double b(final double n) {
        if (!super.bra) {
            this.U();
        }
        if (super.cra) {
            return Math.round(this.ara * n + this.b);
        }
        return this.ara * n + this.b;
    }
    
    public double _(final double n) {
        if (!super.bra) {
            this.U();
        }
        return (n - this.b) / this.ara;
    }
    
    protected void U() {
        if (super.dra != super.era && super.fra != super.gra) {
            this.ara = (super.dra - super.era) / (super.fra - super.gra);
            this.b = (super.gra * super.dra - super.fra * super.era) / (super.gra - super.fra);
        }
        else {
            this.ara = 1.0;
            this.b = 0.0;
        }
        super.bra = true;
    }
}
