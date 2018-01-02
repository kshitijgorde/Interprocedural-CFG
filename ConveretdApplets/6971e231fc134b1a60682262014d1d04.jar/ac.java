import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ac extends j
{
    public static final int u = 10;
    public int period;
    
    public ac(final bk bk) {
        this(bk, 10);
    }
    
    public ac(final bk bk, final int period) {
        super(bk);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        int n2 = 0;
        if (this.period == 0) {
            return Double.NaN;
        }
        if (n < this.period) {
            return Double.NaN;
        }
        if (n >= super._fldnew.size()) {
            return Double.NaN;
        }
        for (int i = n - this.period + 1; i <= n; ++i) {
            if (super._fldnull.a(i) > super._fldnull.a(i - 1)) {
                ++n2;
            }
        }
        return n2 / this.period * 100.0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
