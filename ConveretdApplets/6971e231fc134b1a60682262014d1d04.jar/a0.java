import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a0 extends bk
{
    public static final int J = 9;
    public int period;
    
    public a0(final Vector vector) {
        this(vector, 9);
    }
    
    public a0(final Vector vector, final int period) {
        super(vector);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        double n2 = 0.0;
        double n3 = 0.0;
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
                if (a3._fldint < a2._fldint) {
                    n2 += a2._fldint - a3._fldint;
                }
                if (a3._fldint > a2._fldint) {
                    n3 += a3._fldint - a2._fldint;
                }
            }
            catch (NullPointerException ex) {
                throw new c(i);
            }
        }
        return n2 / (n2 + n3) * 100.0;
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (a >= 69.0 && a <= 71.0) {
            return 3;
        }
        if (a >= 29.0 && a <= 31.0) {
            return 1;
        }
        if (a >= 70.0) {
            return 4;
        }
        return (a > 30.0) ? 2 : 0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
