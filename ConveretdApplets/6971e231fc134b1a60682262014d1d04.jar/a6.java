import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a6 extends bk
{
    public int L;
    
    public a6(final Vector vector, final int l) {
        super(vector);
        this.L = l;
    }
    
    public double _mthfor(final int n) throws c {
        try {
            if (this.L == 1) {
                return super._fldnew.elementAt(n)._fldbyte;
            }
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
        if (this.L == 2) {
            return super._fldnew.elementAt(n)._fldtry;
        }
        if (this.L == 3) {
            return super._fldnew.elementAt(n)._fldnull;
        }
        if (this.L == 4) {
            return super._fldnew.elementAt(n)._fldchar;
        }
        if (this.L == 5) {
            return super._fldnew.elementAt(n)._fldgoto;
        }
        return 0.0;
    }
    
    public Vector _mthif(final int n) throws c {
        try {
            if (super._fldnew.elementAt(n)._fldcase.size() < 0) {
                return null;
            }
        }
        catch (NullPointerException ex) {
            throw new c(n);
        }
        final Vector fldcase = super._fldnew.elementAt(n)._fldcase;
        final Vector<bd> vector = new Vector<bd>();
        if (this.L == 1) {
            for (int i = 0; i < fldcase.size(); ++i) {
                vector.addElement(new bd(fldcase.elementAt(i).j, fldcase.elementAt(i)._fldfor));
            }
            return vector;
        }
        if (this.L == 2) {
            for (int j = 0; j < fldcase.size(); ++j) {
                vector.addElement(new bd(fldcase.elementAt(j).j, fldcase.elementAt(j)._flddo));
            }
            return vector;
        }
        if (this.L == 3) {
            for (int k = 0; k < fldcase.size(); ++k) {
                vector.addElement(new bd(fldcase.elementAt(k).j, fldcase.elementAt(k)._fldif));
            }
            return vector;
        }
        if (this.L == 4) {
            for (int l = 0; l < fldcase.size(); ++l) {
                vector.addElement(new bd(fldcase.elementAt(l).j, fldcase.elementAt(l).k));
            }
            return vector;
        }
        if (this.L == 5) {
            for (int n2 = 0; n2 < fldcase.size(); ++n2) {
                vector.addElement(new bd(fldcase.elementAt(n2).j, fldcase.elementAt(n2).i));
            }
            return vector;
        }
        return null;
    }
}
