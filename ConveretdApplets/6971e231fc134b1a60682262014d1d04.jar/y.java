import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class y extends j
{
    public static final int t = 10;
    public int period;
    
    public y(final bk bk) {
        this(bk, 10);
    }
    
    public y(final bk bk, final int period) {
        super(bk);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        return (super._fldnull.a(n) - super._fldnull.a(n - this.period)) / super._fldnull.a(n - this.period) * 100.0;
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
