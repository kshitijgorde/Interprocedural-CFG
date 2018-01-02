// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

public class e extends c
{
    protected int b;
    protected static final int[] c;
    protected static final int[] d;
    protected static final int[] e;
    protected static final int[] f;
    protected static final int[] g;
    protected static final int[] h;
    protected static final int[] i;
    protected static final int[] j;
    protected static final int[] k;
    protected static final int[] l;
    
    public e(final int n, final int b) {
        super(n);
        this.b = b;
    }
    
    public boolean a(final int n, final int n2, final int n3, int n4, int n5, final boolean b) {
        if (n < 1900) {
            return false;
        }
        int b2 = this.b;
        if (n < 2007 && b2 == 13) {
            b2 = 18;
        }
        int n6 = b.a.c.e.e[b2];
        int i;
        if (b.a.c.e.d[b2] < 0) {
            i = b.a.c.e.f[b2];
        }
        else {
            for (i = b.a.c.k.a(n, n6, b.a.c.e.d[b2], b.a.c.e.c[b2]); i < b.a.c.e.f[b2]; i += 7) {}
        }
        if (n == 2000 && (b2 == 0 || b2 == 2)) {
            n6 = 8;
            i = 27;
        }
        final int n7 = b.a.c.e.j[b2];
        int j;
        if (b.a.c.e.i[b2] < 0) {
            j = b.a.c.e.k[b2];
        }
        else {
            for (j = b.a.c.k.a(n, n7, b.a.c.e.i[b2], b.a.c.e.h[b2]); j < b.a.c.e.k[b2]; j += 7) {}
        }
        if (n6 < n7) {
            if (n2 < n6 || n2 > n7) {
                return false;
            }
            if (n2 > n6 && n2 < n7) {
                return true;
            }
        }
        else {
            if (n2 > n7 && n2 < n6) {
                return false;
            }
            if (n2 < n7 || n2 > n6) {
                return true;
            }
        }
        final int a = this.a(n, n2);
        if (n2 == n6) {
            final int a2 = this.a(b.a.c.e.g[b2]);
            if (b) {
                n5 -= a;
                if (n5 < 0) {
                    n5 += 60;
                    --n4;
                }
            }
            return n3 >= i && (n3 != i || n4 >= a2);
        }
        final int a3 = this.a(b.a.c.e.l[b2]);
        if (!b) {
            n5 += a;
            if (n5 >= 60) {
                n5 -= 60;
                ++n4;
            }
        }
        return n3 <= j && (n3 != j || n4 < a3);
    }
    
    protected int a(int n) {
        if (n > 0) {
            return n;
        }
        n = this.a / 60 - n;
        if (n < 1) {
            n = 1;
        }
        else if (n > 3) {
            n = 3;
        }
        return n;
    }
    
    public int a(final int n, final int n2) {
        if (this.a == 630 && this.b == 0) {
            return 30;
        }
        return 60;
    }
    
    static {
        c = new int[] { 5, 5, 1, 1, 1, 0, 5, 5, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1 };
        d = new int[] { 0, 0, 0, 0, 0, -1, 5, 0, 0, 0, -1, 0, 0, 0, 0, 6, 5, 5, 0 };
        e = new int[] { 10, 10, 10, 10, 10, 4, 4, 3, 9, 11, 4, 9, 10, 3, 10, 10, 4, 4, 4 };
        f = new int[] { 1, 1, 1, 1, 9, 1, 1, 1, 8, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1 };
        g = new int[] { 2, 2, 2, 2, 2, 2, 2, -1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
        h = new int[] { 5, 5, 5, 5, 1, 5, 5, 5, 1, 5, 0, 1, 1, 1, 5, 1, 1, 1, 5 };
        i = new int[] { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, -1, 0, 0, 0, 6, 6, 5, 5, 0 };
        j = new int[] { 3, 3, 3, 2, 3, 10, 9, 10, 4, 2, 10, 4, 3, 11, 2, 4, 9, 10, 10 };
        k = new int[] { 1, 1, 1, 1, 9, 1, 1, 1, 6, 1, 1, 1, 5, 1, 1, 15, 1, 5, 1 };
        l = new int[] { 3, 3, 3, 3, 3, 3, 3, -1, 3, 3, 3, 3, 3, 2, 3, 3, 2, 2, 2 };
    }
}
