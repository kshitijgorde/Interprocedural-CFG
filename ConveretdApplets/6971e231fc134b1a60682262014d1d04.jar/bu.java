import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bu extends bk
{
    public static final int Y = 20;
    public int period;
    b5 Z;
    
    public bu(final Vector vector) {
        this(vector, 20);
    }
    
    public bu(final Vector vector, final int period) {
        super(vector);
        this.period = period;
        this.Z = new b5(vector);
    }
    
    public double _mthfor(final int n) throws c {
        if (this.period == 0) {
            return Double.NaN;
        }
        if (n < this.period) {
            return Double.NaN;
        }
        if (n >= super._fldnew.size()) {
            return Double.NaN;
        }
        double n2 = 0.0;
        double n3 = 0.0;
        for (int i = n - this.period + 1; i <= n; ++i) {
            try {
                final a2 a2 = super._fldnew.elementAt(i);
                final double a3 = this.Z.a(i);
                if (Double.isNaN(a3)) {
                    return Double.NaN;
                }
                final double a4 = this.Z.a(i - 1);
                if (Double.isNaN(a4)) {
                    return Double.NaN;
                }
                if (a3 > a4) {
                    n2 += a3 * a2._fldfor;
                }
                else if (a3 < a4) {
                    n3 += a3 * a2._fldfor;
                }
            }
            catch (NullPointerException ex) {
                throw new c(i);
            }
        }
        return 100.0 - 100.0 / (1.0 + n2 / n3);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (a >= 79.0 && a <= 81.0) {
            return 3;
        }
        if (a >= 19.0 && a <= 21.0) {
            return 1;
        }
        if (a >= 80.0) {
            return 0;
        }
        return (a > 20.0) ? 2 : 4;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
