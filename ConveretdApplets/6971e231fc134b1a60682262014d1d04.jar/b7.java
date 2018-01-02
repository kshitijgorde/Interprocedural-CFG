// 
// Decompiled by Procyon v0.5.30
// 

public class b7 extends cf
{
    public static final int p = 5;
    public static final double o = 0.3333333333333333;
    
    public b7(final aw aw) {
        super(aw, 5, 0.3333333333333333);
        super._flddo = "Stochastic %K";
    }
    
    public b7(final aw aw, final int n, final double n2) {
        super(aw, n, n2);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (a >= 69.0 && a <= 71.0) {
            return 3;
        }
        if (a >= 29.0 && a <= 31.0) {
            return 1;
        }
        if (a >= 70.0) {
            return 4;
        }
        return (a > 30.0) ? 2 : 0;
    }
}
