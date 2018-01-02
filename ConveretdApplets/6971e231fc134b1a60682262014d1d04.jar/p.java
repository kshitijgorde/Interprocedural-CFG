import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class p extends bk
{
    public p(final Vector vector) {
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
            return Math.max(Math.max(a2._fldif - a2._fldnew, Math.abs(a2._fldnew - a3._fldint)), Math.abs(a2._fldif - a3._fldint));
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
