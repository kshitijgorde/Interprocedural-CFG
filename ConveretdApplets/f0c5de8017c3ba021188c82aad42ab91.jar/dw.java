import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class dw extends ba
{
    private double a;
    private int b;
    
    public dw(final String s, final String s2, final int n, final Hashtable hashtable, final j j, final String s3, final double a, final int b, final int n2, final String[] array) {
        super(s, s2, n, hashtable, j, s3, n2, array);
        this.b = b;
        this.a = a;
        if (ak.a.k()) {
            ak.a.i("ctor PostRequestHandler: " + this);
        }
    }
    
    public void a(final Vector vector, final bf bf, final String s, long n, final String s2) {
        if (n.e()) {
            n.a(vector != null && vector.size() >= 1, "PostRequestHandler got invalid vector");
        }
        if (vector.size() == 1) {
            final x x = vector.elementAt(0);
            final int e = x.e();
            if (e < this.b || e > super.f) {
                if (ak.a.k()) {
                    ak.a.i("PostRequestHandler: using single/GET request mode for " + x + " with size " + x.e());
                }
                super.a(x, bf, s, n, s2);
                return;
            }
            if (ak.a.k()) {
                ak.a.i("PostRequestHandler: using POST for single request " + x + " with size " + x.e());
            }
        }
        n += (long)((vector.size() - 1) * n * this.a);
        final x x2 = new x(s2);
        x2.a("NR_REQUESTS", vector.size() + "");
        x2.a("RID", System.currentTimeMillis() + "");
        this.a(x2, bf, 0, s, n, this, vector);
    }
    
    public String toString() {
        return "PostRequestHandler[" + super.b + ":" + super.d + ":" + super.a + ":" + this.b + ":" + super.f + "]";
    }
}
