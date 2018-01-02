// 
// Decompiled by Procyon v0.5.30
// 

public class ak extends z
{
    public static int a;
    public float[][] b;
    public int[] c;
    public int[] d;
    public Object[] e;
    
    public ak() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    public void a(final Object o) {
    }
    
    public Object a(final s s, final w w) {
        final ar ar = new ar();
        if (w.d(1) != 0) {
            ar.a = w.d(4) + 1;
        }
        else {
            ar.a = 1;
        }
        if (w.d(1) != 0) {
            ar.g = w.d(8) + 1;
            for (int i = 0; i < ar.g; ++i) {
                final int[] h = ar.h;
                final int n = i;
                final int d = w.d(ae.a(s.c, 2));
                h[n] = d;
                final int n2 = d;
                final int[] j = ar.i;
                final int n3 = i;
                final int d2 = w.d(ae.a(s.c, 2));
                j[n3] = d2;
                final int n4 = d2;
                if (n2 < 0 || n4 < 0 || n2 == n4 || n2 >= s.c || n4 >= s.c) {
                    ar.a();
                    return null;
                }
            }
        }
        if (w.d(2) > 0) {
            ar.a();
            return null;
        }
        if (ar.a > 1) {
            for (int k = 0; k < s.c; ++k) {
                ar.b[k] = w.d(4);
                if (ar.b[k] >= ar.a) {
                    ar.a();
                    return null;
                }
            }
        }
        for (int l = 0; l < ar.a; ++l) {
            ar.c[l] = w.d(8);
            if (ar.c[l] >= s.k) {
                ar.a();
                return null;
            }
            ar.d[l] = w.d(8);
            if (ar.d[l] >= s.l) {
                ar.a();
                return null;
            }
            ar.e[l] = w.d(8);
            if (ar.e[l] >= s.m) {
                ar.a();
                return null;
            }
        }
        return ar;
    }
    
    public synchronized int a(final v v, final Object o) {
        final u k = v.k;
        final s b = k.b;
        final aq aq = (aq)o;
        final ar a = aq.a;
        final af b2 = aq.b;
        final int f = b.h[v.d];
        v.f = f;
        final int n = f;
        final float[] array = k.s[v.d][v.c][v.e][b2.b];
        if (this.b == null || this.b.length < b.c) {
            this.b = new float[b.c][];
            this.d = new int[b.c];
            this.c = new int[b.c];
            this.e = new Object[b.c];
        }
        for (int i = 0; i < b.c; ++i) {
            final float[] array2 = v.a[i];
            final int n2 = a.b[i];
            this.e[i] = aq.f[n2].a(v, aq.d[n2], this.e[i]);
            if (this.e[i] != null) {
                this.d[i] = 1;
            }
            else {
                this.d[i] = 0;
            }
            for (int j = 0; j < n / 2; ++j) {
                array2[j] = 0.0f;
            }
        }
        for (int l = 0; l < a.g; ++l) {
            if (this.d[a.h[l]] != 0 || this.d[a.i[l]] != 0) {
                this.d[a.h[l]] = 1;
                this.d[a.i[l]] = 1;
            }
        }
        for (int n3 = 0; n3 < a.a; ++n3) {
            int n4 = 0;
            for (int n5 = 0; n5 < b.c; ++n5) {
                if (a.b[n5] == n3) {
                    if (this.d[n5] != 0) {
                        this.c[n4] = 1;
                    }
                    else {
                        this.c[n4] = 0;
                    }
                    this.b[n4++] = v.a[n5];
                }
            }
            aq.g[n3].a(v, aq.e[n3], this.b, this.c, n4);
        }
        for (int n6 = a.g - 1; n6 >= 0; --n6) {
            final float[] array3 = v.a[a.h[n6]];
            final float[] array4 = v.a[a.i[n6]];
            for (int n7 = 0; n7 < n / 2; ++n7) {
                final float n8 = array3[n7];
                final float n9 = array4[n7];
                if (n8 > 0.0f) {
                    if (n9 > 0.0f) {
                        array4[n7] = (array3[n7] = n8) - n9;
                    }
                    else {
                        array3[n7] = (array4[n7] = n8) + n9;
                    }
                }
                else if (n9 > 0.0f) {
                    array4[n7] = (array3[n7] = n8) + n9;
                }
                else {
                    array3[n7] = (array4[n7] = n8) - n9;
                }
            }
        }
        for (int n10 = 0; n10 < b.c; ++n10) {
            final float[] array5 = v.a[n10];
            final int n11 = a.b[n10];
            aq.f[n11].a(v, aq.d[n11], this.e[n10], array5);
        }
        for (int n12 = 0; n12 < b.c; ++n12) {
            final float[] array6 = v.a[n12];
            ((ag)k.t[v.d][0]).a(array6, array6);
        }
        for (int n13 = 0; n13 < b.c; ++n13) {
            final float[] array7 = v.a[n13];
            if (this.d[n13] != 0) {
                for (int n14 = 0; n14 < n; ++n14) {
                    final float[] array8 = array7;
                    final int n15 = n14;
                    array8[n15] *= array[n14];
                }
            }
            else {
                for (int n16 = 0; n16 < n; ++n16) {
                    array7[n16] = 0.0f;
                }
            }
        }
        return 0;
    }
    
    static {
        ak.a = 0;
    }
}
