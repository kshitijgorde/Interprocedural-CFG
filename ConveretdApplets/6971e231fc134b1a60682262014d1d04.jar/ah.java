import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ah extends bk
{
    bv D;
    public int percentage;
    
    public ah(final bv bv) {
        this(bv, 6);
    }
    
    public ah(final bv d, final int percentage) {
        super(d._fldnew);
        this.D = d;
        this.percentage = percentage;
    }
    
    public double _mthfor(final int n) throws c {
        final double a = this.D.a(n);
        if (Double.isNaN(a)) {
            return Double.NaN;
        }
        return a * (100 - this.percentage) / 100.0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
