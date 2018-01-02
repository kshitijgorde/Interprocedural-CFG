// 
// Decompiled by Procyon v0.5.30
// 

public class rb
{
    private static ob a;
    
    public static void a(final Object[] array, final pb pb) {
        final boolean a = ub.a;
        final int n = 0;
        final int n2 = array.length - 1;
        if (n == n2) {
            return;
        }
        a(array, n, a(n, n2));
        int n3 = n;
        int i = n + 1;
        while (i <= n2) {
            if (pb.a(array[i], array[n]) < 0) {
                a(array, ++n3, i);
            }
            ++i;
            if (a) {
                break;
            }
        }
        a(array, n, n3);
        a(array, n, n3 - 1, pb);
        a(array, n3 + 1, n2, pb);
    }
    
    public static void a(final Object[] array, int n, final int n2, final pb pb) {
        final boolean a = ub.a;
        if (n >= n2) {
            return;
        }
        a(array, n, a(n, n2));
        int i = n + 1;
        while (i <= n2) {
            if (pb.a(array[i], array[n]) < 0) {
                a(array, ++n, i);
            }
            ++i;
            if (a) {
                break;
            }
        }
        a(array, n, n);
        a(array, n, n - 1, pb);
        a(array, n + 1, n2, pb);
    }
    
    private static void a(final Object[] array, final int n, final int n2) {
        final Object o = array[n];
        array[n] = array[n2];
        array[n2] = o;
    }
    
    private static int a(final int n, final int n2) {
        return n + rb.a.a(n2 - n);
    }
    
    static {
        rb.a = new ob();
    }
}
