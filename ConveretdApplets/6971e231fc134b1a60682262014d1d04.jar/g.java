import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends bk
{
    public static final double _fldlong = 0.02;
    public double factor;
    
    public g(final Vector vector) {
        this(vector, 0.02);
    }
    
    public g(final Vector vector, final double factor) {
        super(vector);
        this.factor = factor;
    }
    
    public double _mthfor(final int n) throws c {
        int n2 = 0;
        double n3 = Double.NaN;
        double n4 = Double.NaN;
        double n5 = Double.NEGATIVE_INFINITY;
        double n6 = Double.POSITIVE_INFINITY;
        double n7 = Double.NaN;
        if (n <= 0) {
            return Double.NaN;
        }
        for (int i = 1; i <= n; ++i) {
            if (i == 1) {
                try {
                    final a2 a2 = super._fldnew.elementAt(1);
                    final a2 a3 = super._fldnew.elementAt(0);
                    n5 = a3._fldif;
                    n6 = a3._fldnew;
                    if (a2._fldint > a3._fldint) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                catch (NullPointerException ex) {
                    throw new c(i);
                }
            }
            try {
                final a2 a4 = super._fldnew.elementAt(i);
                final a2 a5 = super._fldnew.elementAt(i - 1);
                n5 = Math.max(n5, a4._fldif);
                n6 = Math.min(n6, a4._fldnew);
                if (Double.isNaN(n3)) {
                    if (a4._fldint > a5._fldint && n2 == 0) {
                        n2 = 1;
                        n3 = n6;
                        n7 = this.factor;
                        n4 = n5;
                    }
                    else if (a4._fldint < a5._fldint && n2 != 0) {
                        n2 = 0;
                        n3 = n5;
                        n7 = this.factor;
                        n4 = n6;
                    }
                    else {
                        n3 = Double.NaN;
                    }
                }
                else if (n3 >= a5._fldnew && n2 != 0 && n7 != this.factor) {
                    n2 = 0;
                    n3 = n5;
                    n7 = this.factor;
                    n4 = n6;
                    n6 = Double.POSITIVE_INFINITY;
                    n5 = Double.NEGATIVE_INFINITY;
                }
                else if (n3 <= a5._fldif && n2 == 0 && n7 != this.factor) {
                    n2 = 1;
                    n3 = n6;
                    n7 = this.factor;
                    n4 = n5;
                    n6 = Double.POSITIVE_INFINITY;
                    n5 = Double.NEGATIVE_INFINITY;
                }
                else {
                    n3 += n7 * (n4 - n3);
                    n7 += this.factor;
                    if (n7 > this.factor * 10.0) {
                        n7 = this.factor * 10.0;
                    }
                    if (n2 != 0) {
                        n4 = n5;
                    }
                    else {
                        n4 = n6;
                    }
                }
                super.a.setElementAt(new Double(n3), i);
            }
            catch (NullPointerException ex2) {
                throw new c(i);
            }
        }
        return n3;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
