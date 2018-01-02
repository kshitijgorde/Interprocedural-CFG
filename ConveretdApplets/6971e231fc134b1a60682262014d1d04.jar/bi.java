import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bi extends j
{
    public static final int v = 20;
    public int period;
    n w;
    
    public bi(final bk bk) {
        this(bk, 20);
    }
    
    public bi(final bk bk, final int period) {
        super(bk);
        this.period = period;
        this.w = new n(bk, period);
    }
    
    public double _mthfor(final int n) throws c {
        return super._fldnull.a(n) / this.w.a(n) * 100.0;
    }
    
    public void a() {
        super.a();
        this.w.a("period", new Integer(this.period));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
