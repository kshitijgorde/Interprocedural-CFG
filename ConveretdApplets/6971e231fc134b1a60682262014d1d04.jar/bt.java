import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bt extends j
{
    public static final int x = 5;
    public int period;
    
    public bt(final bk bk) {
        this(bk, 5);
    }
    
    public bt(final bk bk, final int period) {
        super(bk);
        this.period = period;
    }
    
    public double _mthfor(final int n) throws c {
        return super._fldnull.a(n) - super._fldnull.a(n - this.period);
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
