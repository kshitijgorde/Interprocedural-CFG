import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends bk
{
    public a(final Vector vector) {
        super(vector);
    }
    
    public double _mthfor(final int n) throws c {
        try {
            return super._fldnew.elementAt(n)._fldint;
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
