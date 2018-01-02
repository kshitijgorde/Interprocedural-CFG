import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class b5 extends bk
{
    public b5(final Vector vector) {
        super(vector);
    }
    
    public double _mthfor(final int n) throws c {
        try {
            final a2 a2 = super._fldnew.elementAt(n);
            return (a2._fldif + a2._fldnew + a2._fldint) / 3.0;
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
