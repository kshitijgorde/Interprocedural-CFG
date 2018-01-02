// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import ji.util.d;
import ji.awt.c;
import ji.v1event.a6;
import ji.v1event.af;
import ji.io.q;
import ji.io.ac;

public class oo
{
    private aay[] a;
    private String b;
    private ac c;
    
    public oo() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    public final void a(final Object o, final dx dx, final q q, final String s) {
        try {
            if (dx.b3 > 0) {
                this.b = q.n();
                this.c = new ac(this.b, true, true, 102400, false, o, true, s);
                this.a = new aay[dx.b7];
                for (int i = 0; i < dx.b7; ++i) {
                    this.a[i] = new aay();
                    final int n = i / dx.b5;
                    this.a[i].a = (i - n * dx.b5) * dx.b3;
                    this.a[i].b = this.a[i].a + dx.b3;
                    this.a[i].c = n * dx.b4;
                    this.a[i].d = this.a[i].c + dx.b4;
                    this.a[i].g = new int[dx.b4];
                    this.a[i].h = new int[dx.b4];
                    this.a[i].i = new int[dx.b4];
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final Object o, final dx dx, final af af, final ds ds, final boolean b, final String s) {
        a6 a6 = null;
        try {
            if (dx.b8 && this.b != null) {
                if (af != null && a6 == null) {
                    a6 = new a6(this, 4, "");
                }
                this.c.a(o);
                this.c = new ac(this.b, false, true, 102400, false, o, false, s);
                try {
                    if (b) {
                        final int[] array = new int[dx.b5];
                        final int[] array2 = new int[dx.b3 * 2];
                        final c c = new c("jiDecodeG31a");
                        final c c2 = new c("jiDecodeG31b");
                        for (int i = 0; i < dx.n; i += dx.b4) {
                            int n = 0;
                            int n2 = 0;
                            for (int j = 0; j < this.a.length; ++j) {
                                if (this.a[j].c <= i && this.a[j].d > i) {
                                    array[n++] = j;
                                    n2 += this.a[j].f;
                                }
                            }
                            if (n2 < 512000) {
                                for (int k = 0; k < array.length; ++k) {
                                    final int n3 = array[k];
                                    n2 += this.a[n3].f;
                                    this.c.a((long)this.a[n3].e);
                                    final int[] array3 = new int[this.a[n3].f];
                                    this.c.a(array3, 0, array3.length);
                                    c2.c(array3);
                                }
                            }
                            for (int n4 = i + dx.b4, l = i; l < n4; ++l) {
                                c.c();
                                int n5 = 0;
                                int n6 = 0;
                                for (int n7 = 0; n7 < dx.m; n7 += dx.b3) {
                                    final int n8 = array[n6++];
                                    if (this.a[n8] != null) {
                                        final int n9 = l - this.a[n8].c;
                                        int a7 = this.a[n8].g[n9];
                                        if (c2.b() > n8) {
                                            final int[] array4 = (int[])c2.b(n8);
                                            int n10 = 0;
                                            if (n9 > 0) {
                                                n10 = this.a[n8].i[n9 - 1];
                                            }
                                            System.arraycopy(array4, n10, array2, 0, a7);
                                        }
                                        else {
                                            this.c.a((long)this.a[n8].h[n9]);
                                            a7 = this.c.a(array2, 0, a7);
                                        }
                                        final byte[] a8 = d.a(array2, dx.b3, -1);
                                        n5 += a7;
                                        c.c(a8);
                                    }
                                }
                                final byte[] array5 = new byte[dx.b3 * dx.b5];
                                int n11 = 0;
                                for (int n12 = 0; n12 < c.b(); ++n12) {
                                    System.arraycopy(c.b(n12), 0, array5, n11, dx.b3);
                                    n11 += dx.b3;
                                }
                                final short[] d = ji.util.d.d(array5);
                                final int[] array6 = new int[d.length];
                                for (int n13 = 0; n13 < d.length; ++n13) {
                                    array6[n13] = d[n13];
                                }
                                ds.a(array6, array6.length, l, false, true);
                            }
                            c.c();
                            if (af != null) {
                                a6.a("".concat(String.valueOf(String.valueOf(100 * i / dx.n))));
                                af.a(a6);
                            }
                        }
                        if (this.a != null) {
                            for (int n14 = 0; n14 < this.a.length; ++n14) {
                                this.a[n14] = null;
                            }
                            this.a = null;
                        }
                    }
                    else {
                        final int[] array7 = new int[dx.b5];
                        final short[] array8 = new short[dx.b3 * 2];
                        final c c3 = new c("jiDecodeG31c");
                        final c c4 = new c("jiDecodeG31d");
                        for (int n15 = 0; n15 < dx.n; n15 += dx.b4) {
                            int n16 = 0;
                            int n17 = 0;
                            for (int n18 = 0; n18 < this.a.length; ++n18) {
                                if (this.a[n18].c <= n15 && this.a[n18].d > n15) {
                                    array7[n16++] = n18;
                                    n17 += this.a[n18].f;
                                }
                            }
                            if (n17 < 512000) {
                                for (int n19 = 0; n19 < array7.length; ++n19) {
                                    final int n20 = array7[n19];
                                    n17 += this.a[n20].f;
                                    this.c.a((long)this.a[n20].e);
                                    final short[] array9 = new short[this.a[n20].f];
                                    this.c.a(array9, 0, array9.length);
                                    c4.c(array9);
                                }
                            }
                            for (int n21 = n15 + dx.b4, n22 = n15; n22 < n21; ++n22) {
                                c3.c();
                                int n23 = 0;
                                int n24 = 0;
                                for (int n25 = 0; n25 < dx.m; n25 += dx.b3) {
                                    int n26 = array7[n24++];
                                    if (this.a[n26] != null) {
                                        final int n27 = n22 - this.a[n26].c;
                                        int a9 = this.a[n26].g[n27];
                                        if (c4.b() > n26) {
                                            final short[] array10 = (short[])c4.b(n26);
                                            int n28 = 0;
                                            if (n27 > 0) {
                                                n28 = this.a[n26].i[n27 - 1];
                                            }
                                            System.arraycopy(array10, n28, array8, 0, a9);
                                        }
                                        else {
                                            this.c.a((long)this.a[n26].h[n27]);
                                            a9 = this.c.a(array8, 0, a9);
                                        }
                                        final byte[] a10 = d.a(array8, dx.b3, -1);
                                        n23 += a9;
                                        c3.c(a10);
                                    }
                                    ++n26;
                                }
                                final byte[] array11 = new byte[dx.b3 * dx.b5];
                                int n29 = 0;
                                for (int n30 = 0; n30 < c3.b(); ++n30) {
                                    System.arraycopy(c3.b(n30), 0, array11, n29, dx.b3);
                                    n29 += dx.b3;
                                }
                                final short[] d2 = d.d(array11);
                                ds.a(d2, d2.length);
                            }
                            c3.c();
                            if (af != null) {
                                a6.a("".concat(String.valueOf(String.valueOf(100 * n15 / dx.n))));
                                af.a(a6);
                            }
                        }
                        if (this.a != null) {
                            for (int n31 = 0; n31 < this.a.length; ++n31) {
                                this.a[n31] = null;
                            }
                            this.a = null;
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.b != null) {
                this.c.a(o);
                this.c = null;
                this.b = null;
            }
        }
        catch (Exception ex3) {}
    }
    
    public final void a(final dx dx, final int n) {
        try {
            if (dx.b8) {
                this.a[n].e = (int)this.c.r();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final short[] array, final int n, final int n2, final int n3, final ds ds, final dx dx) throws Exception {
        if (dx.b8) {
            try {
                this.a[n3].h[n2] = (int)this.c.r();
                this.a[n3].g[n2] = n;
                final aay aay = this.a[n3];
                aay.f += n;
                int n4 = 0;
                if (n2 > 0) {
                    n4 = this.a[n3].i[n2 - 1];
                }
                this.a[n3].i[n2] = n + n4;
                this.c.b(array, 0, n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            ds.a(array, n);
        }
    }
    
    public final void a(final int[] array, final int n, final int n2, final int n3, final int n4, final dx dx, final ds ds, final boolean b) throws Exception {
        if (dx.b8) {
            try {
                this.a[n4].h[n3] = (int)this.c.r();
                this.a[n4].g[n3] = n;
                final aay aay = this.a[n4];
                aay.f += n;
                int n5 = 0;
                if (n3 > 0) {
                    n5 = this.a[n4].i[n3 - 1];
                }
                this.a[n4].i[n3] = n + n5;
                this.c.b(array, 0, n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            ds.a(array, n, n2, false, b);
        }
    }
    
    class aay
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int[] g;
        int[] h;
        int[] i;
        
        aay(final oo oo) {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = null;
            this.h = null;
            this.i = null;
        }
    }
}
