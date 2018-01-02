import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class aa extends bk
{
    public aa(final Vector vector) {
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
            return ((a2._fldif + a2._fldnew) / 2.0 - (a3._fldif + a3._fldnew) / 2.0) / (a2._fldfor / (a2._fldif - a2._fldnew));
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
