import java.util.Vector;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class at extends a2 implements Serializable
{
    public double _fldbyte;
    public double _fldtry;
    public double _fldnull;
    public double _fldchar;
    public double _fldgoto;
    public Vector _fldcase;
    public int _fldelse;
    public int _fldlong;
    
    public at(final String s, final double n, final double n2, final double n3, final double n4, final double n5, final double fldbyte, final double fldtry, final double fldnull, final double fldchar, final double fldgoto, final Vector fldcase) {
        super(s, n, n2, n3, n4, n5);
        this._fldbyte = fldbyte;
        this._fldtry = fldtry;
        this._fldnull = fldnull;
        this._fldchar = fldchar;
        this._fldgoto = fldgoto;
        this._fldcase = fldcase;
        this._fldelse = -1;
        this._fldlong = -1;
    }
    
    public at(final String s, final double n, final double n2, final double n3, final double n4, final double n5) {
        super(s, n, n2, n3, n4, n5);
    }
    
    public String a() {
        return new String("\uc77c\uc790: " + super.a + "\n" + "\uc2dc\uac00: " + String.valueOf(super._flddo) + "\n" + "\uace0\uac00: " + String.valueOf(super._fldif) + "\n" + "\uc800\uac00: " + String.valueOf(super._fldnew) + "\n" + "\uc885\uac00: " + String.valueOf(super._fldint) + "\n" + "\uac70\ub798\ub7c9: " + String.valueOf(super._fldfor));
    }
}
