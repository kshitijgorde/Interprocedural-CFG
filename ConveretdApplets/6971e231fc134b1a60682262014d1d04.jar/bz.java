import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bz extends j
{
    q z;
    am y;
    
    public bz(final bk bk, final q z, final am y) {
        super(bk);
        this.z = z;
        this.y = y;
        super._flddo = "Band %b";
    }
    
    public double _mthfor(final int n) throws c {
        final double a = super._fldnull.a(n);
        if (Double.isNaN(a)) {
            return Double.NaN;
        }
        final double a2 = this.z.a(n);
        if (Double.isNaN(a2)) {
            return Double.NaN;
        }
        final double a3 = this.y.a(n);
        if (Double.isNaN(a3)) {
            return Double.NaN;
        }
        return (a - a3) / (a2 - a3);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (a >= 0.9 && a <= 1.1) {
            return 3;
        }
        if (a >= -0.1 && a <= 0.1) {
            return 1;
        }
        if (a >= 1.0) {
            return 4;
        }
        return (a > 0.0) ? 2 : 0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
