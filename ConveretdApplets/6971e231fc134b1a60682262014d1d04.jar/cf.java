// 
// Decompiled by Procyon v0.5.30
// 

public class cf extends n
{
    public double weight;
    
    public cf(final bk bk, final int n, final double weight) {
        super(bk, n);
        this.weight = weight;
    }
    
    public double _mthfor(final int n) throws c {
        int n2;
        for (n2 = 0; n2 < super._fldnew.size() && Double.isNaN(super._fldnull.a(n2)); ++n2) {}
        if (n < n2 + super.period - 1) {
            return Double.NaN;
        }
        double mthfor = super._mthfor(n2 + super.period - 1);
        super.a.setElementAt(new Double(mthfor), n2 + super.period - 1);
        if (Double.isNaN(mthfor)) {
            return Double.NaN;
        }
        for (int i = n2 + super.period; i <= n; ++i) {
            mthfor += this.weight * (super._fldnull.a(i) - mthfor);
            super.a.setElementAt(new Double(mthfor), i);
        }
        return mthfor;
    }
}
