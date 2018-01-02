import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bx extends bk
{
    bk ab;
    bk aa;
    
    public bx(final bk ab, final bk aa) {
        super(ab._fldnew);
        this.ab = ab;
        this.aa = aa;
    }
    
    public double _mthfor(final int n) throws c {
        return this.ab.a(n) - this.aa.a(n);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        final double a2 = this.a(n - 1);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (Double.isNaN(a2)) {
            if (a > 0.0) {
                return 4;
            }
            if (a < 0.0) {
                return 0;
            }
        }
        else {
            if (a > 0.0 && a2 >= 0.0) {
                return 4;
            }
            if (a > 0.0 && a2 < 0.0) {
                return 1;
            }
            if (a < 0.0 && a2 > 0.0) {
                return 3;
            }
            if (a < 0.0 && a2 <= 0.0) {
                return 0;
            }
        }
        return 2;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
