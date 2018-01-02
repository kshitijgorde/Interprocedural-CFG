import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends bk
{
    public static final int _fldgoto = 14;
    public int period;
    
    public b(final Vector vector) {
        this(vector, 14);
        super._flddo = "Williams %R";
    }
    
    public b(final Vector vector, final int period) {
        super(vector);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        double fldif = Double.NEGATIVE_INFINITY;
        double fldnew = Double.POSITIVE_INFINITY;
        if (this.period == 0) {
            return Double.NaN;
        }
        if (n < this.period - 1) {
            return Double.NaN;
        }
        if (n >= super._fldnew.size()) {
            return Double.NaN;
        }
        final a2 a2 = super._fldnew.elementAt(n);
        for (int i = n - this.period + 1; i <= n; ++i) {
            try {
                final a2 a3 = super._fldnew.elementAt(i);
                if (fldnew > a3._fldnew) {
                    fldnew = a3._fldnew;
                }
                if (fldif < a3._fldif) {
                    fldif = a3._fldif;
                }
            }
            catch (NullPointerException ex) {
                throw new c(i);
            }
        }
        return (fldif - super._fldnew.elementAt(n)._fldint) / (fldif - fldnew) * -100.0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
