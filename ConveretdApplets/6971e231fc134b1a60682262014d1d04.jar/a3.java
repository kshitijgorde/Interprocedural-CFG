import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a3 extends n
{
    public static final int e = 10;
    
    public a3(final Vector vector) {
        this(vector, 10);
    }
    
    public a3(final Vector vector, final int n) {
        super(new aa(vector), n);
    }
    
    public int _mthdo(final int n) throws c {
        final double a = this.a(n);
        final double a2 = this.a(n - 1);
        if (Double.isNaN(a)) {
            return -1;
        }
        if (Double.isNaN(a2)) {
            if (a > 0.0) {
                return 4;
            }
            if (a < 0.0) {
                return 0;
            }
        }
        else {
            if (a > 0.0 && a2 >= 0.0) {
                return 4;
            }
            if (a > 0.0 && a2 < 0.0) {
                return 1;
            }
            if (a < 0.0 && a2 > 0.0) {
                return 3;
            }
            if (a < 0.0 && a2 <= 0.0) {
                return 0;
            }
        }
        return 2;
    }
}
