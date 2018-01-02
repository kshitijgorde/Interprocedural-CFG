import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bm extends bk
{
    public static final int S = 12;
    public static final int T = 26;
    public static final double Q = 0.15384615384615385;
    public static final double R = 0.07407407407407407;
    public int speriod;
    public int lperiod;
    public double sweight;
    public double lweight;
    cf sema;
    cf lema;
    
    public bm(final bk bk) {
        this(bk, 12, 26, 0.15384615384615385, 0.07407407407407407);
    }
    
    public bm(final bk bk, final int speriod, final int lperiod, final double sweight, final double lweight) {
        super(bk._fldnew);
        this.speriod = speriod;
        this.lperiod = lperiod;
        this.sweight = sweight;
        this.lweight = lweight;
        this.sema = new cf(bk, speriod, sweight);
        this.lema = new cf(bk, lperiod, lweight);
    }
    
    public double _mthfor(final int n) throws c {
        return this.sema.a(n) - this.lema.a(n);
    }
    
    public void a() {
        super.a();
        this.sema.a("period", new Integer(this.speriod));
        this.sema.a("weight", new Double(this.sweight));
        this.lema.a("period", new Integer(this.lperiod));
        this.lema.a("weight", new Double(this.lweight));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
