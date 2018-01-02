import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends j
{
    public static final int _fldvoid = 5;
    public static final int c = 20;
    public static final int b = 60;
    public static final int d = 120;
    public int period;
    
    public n(final bk bk, final int period) {
        super(bk);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        if (this.period == 0) {
            return Double.NaN;
        }
        double n2 = 0.0;
        for (int i = n - this.period + 1; i <= n; ++i) {
            n2 += super._fldnull.a(i);
        }
        return n2 / this.period;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
