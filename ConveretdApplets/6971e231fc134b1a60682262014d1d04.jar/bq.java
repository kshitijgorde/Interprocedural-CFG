import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class bq extends bk
{
    public bq(final Vector vector) {
        super(vector);
    }
    
    public double _mthfor(final int n) throws c {
        if (n < 1) {
            return Double.NaN;
        }
        if (n >= super._fldnew.size()) {
            return Double.NaN;
        }
        try {
            final a2 a2 = super._fldnew.elementAt(n);
            final a2 a3 = super._fldnew.elementAt(n - 1);
            double n2 = 0.0;
            double n3 = 0.0;
            if (a2._fldif > a3._fldif) {
                n2 = a2._fldif - a3._fldif;
            }
            if (a2._fldnew < a3._fldnew) {
                n3 = a3._fldnew - a2._fldnew;
            }
            if (n2 >= n3) {
                n3 = 0.0;
            }
            return n3;
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
