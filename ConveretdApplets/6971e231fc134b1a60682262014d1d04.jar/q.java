import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends bk
{
    b4 A;
    public double sigma;
    
    public q(final b4 b4) {
        this(b4, 2.0);
    }
    
    public q(final b4 a, final double sigma) {
        super(a._fldnew);
        this.A = a;
        this.sigma = sigma;
    }
    
    public double _mthfor(final int n) throws c {
        final double a = this.A.a(n);
        if (Double.isNaN(a)) {
            return Double.NaN;
        }
        final double a2 = this.A.i.a(n);
        if (Double.isNaN(a2)) {
            return Double.NaN;
        }
        return a + this.sigma * a2;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
