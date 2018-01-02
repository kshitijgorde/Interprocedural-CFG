// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

import java.util.Vector;
import java.util.Collection;

public final class F
{
    public static final int B = -1;
    public static final int A = 0;
    
    public static void A(final Collection collection, final N n, final G g, final String s, int n2) {
        final B b = new B(s);
        int b2 = 0;
        while (--n2 != 0 && n.B(b, g)) {
            final M a = n.A();
            collection.add(s.substring(b2, a.C(0)));
            b2 = a.B(0);
        }
        collection.add(s.substring(b2, s.length()));
    }
    
    public static void A(final Collection collection, final N n, final G g, final String s) {
        A(collection, n, g, s, 0);
    }
    
    public static Vector A(final N n, final G g, final String s, final int n2) {
        final Vector vector = new Vector(20);
        A(vector, n, g, s, n2);
        return vector;
    }
    
    public static Vector A(final N n, final G g, final String s) {
        return A(n, g, s, 0);
    }
    
    public static String A(final N n, final G g, final J j, final String s, final int n2) {
        final StringBuffer sb = new StringBuffer(s.length());
        if (A(sb, n, g, j, new B(s), n2) != 0) {
            return sb.toString();
        }
        return s;
    }
    
    public static String A(final N n, final G g, final J j, final String s) {
        return A(n, g, j, s, 1);
    }
    
    public static int A(final StringBuffer sb, final N n, final G g, final J j, final String s, final int n2) {
        return A(sb, n, g, j, new B(s), n2);
    }
    
    public static int A(final StringBuffer sb, final N n, final G g, final J j, final B b, int n2) {
        int n3 = 0;
        int n4 = b.F();
        final char[] i = b.J();
        while (n2 != 0 && n.B(b, g)) {
            --n2;
            ++n3;
            sb.append(i, n4, b.G() - n4);
            j.A(sb, n.A(), n3, b, n, g);
            n4 = b.E();
        }
        sb.append(i, n4, b.L() - n4);
        return n3;
    }
}
