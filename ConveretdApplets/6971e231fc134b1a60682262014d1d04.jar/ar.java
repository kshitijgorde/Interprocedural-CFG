import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar extends bk
{
    bv F;
    public int percentage;
    
    public ar(final bv bv) {
        this(bv, 6);
    }
    
    public ar(final bv f, final int percentage) {
        super(f._fldnew);
        this.F = f;
        this.percentage = percentage;
    }
    
    public double _mthfor(final int n) throws c {
        final double a = this.F.a(n);
        if (Double.isNaN(a)) {
            return Double.NaN;
        }
        return a * (100 + this.percentage) / 100.0;
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
