import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class bc
{
    static final Color _fldtry;
    static final Color _fldnew;
    static final boolean _fldbyte = true;
    static final boolean a = true;
    Color _flddo;
    Color _fldif;
    boolean _fldcase;
    boolean _fldfor;
    Vector _fldint;
    
    public bc(final Vector vector) {
        this(true, true, vector);
    }
    
    public bc(final boolean b, final boolean b2, final Vector vector) {
        this(bc._fldtry, bc._fldnew, b, b2, vector);
    }
    
    public bc(final Color flddo, final Color fldif, final boolean fldcase, final boolean fldfor, final Vector fldint) {
        this._flddo = flddo;
        this._fldif = fldif;
        this._fldcase = fldcase;
        this._fldfor = fldfor;
        this._fldint = fldint;
    }
    
    static {
        _fldtry = new Color(204, 0, 0);
        _fldnew = new Color(0, 51, 153);
    }
}
