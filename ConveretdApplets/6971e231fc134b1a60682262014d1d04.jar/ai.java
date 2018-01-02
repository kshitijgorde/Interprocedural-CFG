import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ai extends bk
{
    public ai(final Vector vector) {
        super(vector);
    }
    
    public double _mthfor(final int n) throws c {
        if (n < 0) {
            return Double.NaN;
        }
        double n2 = 0.0;
        if (n == 0) {
            return n2;
        }
        for (int i = 1; i <= n; ++i) {
            try {
                final a2 a2 = super._fldnew.elementAt(i);
                final a2 a3 = super._fldnew.elementAt(i - 1);
                if (a2._fldint > a3._fldint) {
                    n2 += a2._fldfor;
                }
                else if (a2._fldint < a3._fldint) {
                    n2 -= a2._fldfor;
                }
                super.a.setElementAt(new Double(n2), i);
            }
            catch (NullPointerException ex) {
                throw new c(i);
            }
        }
        return n2;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
