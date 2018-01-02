import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bp extends bk
{
    public static final int X = 6;
    public static final int W = 9;
    public static final double V = 0.2;
    public int term;
    public int period;
    public double weight;
    cf U;
    
    public bp(final bk bk) {
        this(bk, 6, 9, 0.2);
    }
    
    public bp(final bk bk, final int term, final int period, final double weight) {
        super(bk._fldnew);
        this.term = term;
        this.period = period;
        this.weight = weight;
        this.U = new cf(bk, period, weight);
    }
    
    public double _mthfor(final int n) throws c {
        if (n < this.term) {
            return Double.NaN;
        }
        return this.U.a(n) - this.U.a(n - this.term);
    }
    
    public void a() {
        super.a();
        this.U.a("period", new Integer(this.period));
        this.U.a("weight", new Double(this.weight));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
