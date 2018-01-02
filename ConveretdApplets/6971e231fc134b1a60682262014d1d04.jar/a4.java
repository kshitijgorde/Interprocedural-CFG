import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class a4 extends bk
{
    public static final int K = 20;
    public int period;
    
    public a4(final Vector vector) {
        this(vector, 20);
    }
    
    public a4(final Vector vector, final int period) {
        super(vector);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        if (this.period == 0) {
            return Double.NaN;
        }
        if (n < this.period) {
            return Double.NaN;
        }
        if (n >= super._fldnew.size()) {
            return Double.NaN;
        }
        for (int i = n - this.period + 1; i <= n; ++i) {
            try {
                final a2 a2 = super._fldnew.elementAt(i);
                final a2 a3 = super._fldnew.elementAt(i - 1);
                if (a2._fldint > a3._fldint) {
                    n2 += a2._fldfor;
                }
                else if (a2._fldint < a3._fldint) {
                    n3 += a2._fldfor;
                }
                else {
                    n4 += a2._fldfor;
                }
            }
            catch (NullPointerException ex) {
                throw new c(i);
            }
        }
        return (n2 + n4) / 2.0 / ((n3 + n4) / 2.0) * 100.0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
