import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends j
{
    n s;
    public int period;
    
    public s(final bk bk, final int period) {
        super(bk);
        this.period = period;
        this.s = new n(bk, period);
    }
    
    public double _mthfor(final int n) throws c {
        double n2 = 0.0;
        final double a = this.s.a(n);
        if (Double.isNaN(a)) {
            return Double.NaN;
        }
        for (int i = n - this.period + 1; i <= n; ++i) {
            n2 += Math.pow(super._fldnull.a(i) - a, 2.0);
        }
        return Math.sqrt(n2 / this.period);
    }
    
    public void a() {
        super.a();
        this.s.a("period", new Integer(this.period));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
