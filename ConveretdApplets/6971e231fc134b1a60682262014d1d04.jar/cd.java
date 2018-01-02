// 
// Decompiled by Procyon v0.5.30
// 

public class cd extends cf
{
    public static final int r = 5;
    public static final double q = 0.3333333333333333;
    
    public cd(final b7 b7) {
        super(b7, 5, 0.3333333333333333);
        super._flddo = "Stochastic %D";
    }
    
    public cd(final b7 b7, final int n, final double n2) {
        super(b7, n, n2);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (a >= 79.0 && a <= 81.0) {
            return 3;
        }
        if (a >= 19.0 && a <= 21.0) {
            return 1;
        }
        if (a >= 80.0) {
            return 4;
        }
        return (a > 20.0) ? 2 : 0;
    }
}
