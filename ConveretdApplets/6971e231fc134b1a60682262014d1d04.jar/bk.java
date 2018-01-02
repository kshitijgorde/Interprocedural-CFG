import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class bk
{
    String _flddo;
    public static final int _fldbyte = -1;
    public static final int _fldif = 0;
    public static final int _fldcase = 1;
    public static final int _fldfor = 2;
    public static final int _fldtry = 3;
    public static final int _fldint = 4;
    public static final int _fldelse = 5;
    Class _fldchar;
    Vector _fldnew;
    Vector a;
    
    public bk(final Vector fldnew) {
        this._flddo = this.getClass().getName();
        this.a = new Vector();
        this._fldchar = this.getClass();
        this._fldnew = fldnew;
    }
    
    public Object a(final String s) {
        Object value = null;
        try {
            value = this._fldchar.getField(s).get(this);
        }
        catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        return value;
    }
    
    public void a(final String s, final Object o) {
        try {
            this._fldchar.getField(s).set(this, o);
        }
        catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        this.a();
    }
    
    public double a(final int n) throws c {
        if (n < 0) {
            return Double.NaN;
        }
        if (n >= this._fldnew.size()) {
            return Double.NaN;
        }
        if (this._fldnew.size() != this.a.size()) {
            this.a.setSize(this._fldnew.size());
        }
        if (this.a.elementAt(n) != null) {
            return this.a.elementAt(n);
        }
        final double mthfor = this._mthfor(n);
        this.a.setElementAt(new Double(mthfor), n);
        return mthfor;
    }
    
    public abstract Vector _mthif(final int p0) throws c;
    
    public abstract double _mthfor(final int p0) throws c;
    
    public int _mthdo(final int n) throws c {
        return -1;
    }
    
    public void a() {
        this.a.removeAllElements();
    }
    
    public String _mthif() {
        return this._flddo;
    }
}
