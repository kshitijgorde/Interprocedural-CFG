import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class am extends bk
{
    b4 E;
    public double sigma;
    
    public am(final b4 b4) {
        this(b4, 2.0);
    }
    
    public am(final b4 e, final double sigma) {
        super(e._fldnew);
        this.E = e;
        this.sigma = sigma;
    }
    
    public double _mthfor(final int n) throws c {
        final double a = this.E.a(n);
        if (Double.isNaN(a)) {
            return Double.NaN;
        }
        final double a2 = this.E.i.a(n);
        if (Double.isNaN(a2)) {
            return Double.NaN;
        }
        return a - this.sigma * a2;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
