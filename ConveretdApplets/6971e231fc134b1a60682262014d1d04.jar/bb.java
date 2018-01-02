import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bb extends bk
{
    public static final int N = 9;
    public int period;
    b5 P;
    n M;
    n O;
    
    public bb(final Vector vector) {
        this(vector, 9);
    }
    
    public bb(final Vector vector, final int period) {
        super(vector);
        this.period = period;
        this.P = new b5(vector);
        this.M = new n(this.P, period);
        this.O = new n(new x(this.P, this.M), period);
    }
    
    public double _mthfor(final int n) throws c {
        return (this.P.a(n) - this.M.a(n)) / (this.O.a(n) * 0.015);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (a >= 99.0 && a <= 101.0) {
            return 3;
        }
        if (a >= -101.0 && a <= -99.0) {
            return 1;
        }
        if (a >= 100.0) {
            return 4;
        }
        return (a > -100.0) ? 2 : 0;
    }
    
    public void a() {
        super.a();
        this.M.a("period", new Integer(this.period));
        this.O.a("period", new Integer(this.period));
        ((x)this.O._fldnull).B.a("period", new Integer(this.period));
    }
    
    public Vector _mthif(final int n) throws c {
        return null;
    }
}
