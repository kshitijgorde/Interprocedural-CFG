// 
// Decompiled by Procyon v0.5.30
// 

public class al extends aa
{
    public float[] a;
    
    public al() {
        this.a = null;
    }
    
    public Object a(final s s, final w w) {
        final as as = new as();
        as.a = w.d(8);
        as.b = w.d(16);
        as.c = w.d(16);
        as.d = w.d(6);
        as.e = w.d(8);
        as.f = w.d(4) + 1;
        if (as.a < 1 || as.b < 1 || as.c < 1 || as.f < 1) {
            return null;
        }
        for (int i = 0; i < as.f; ++i) {
            as.g[i] = w.d(8);
            if (as.g[i] < 0 || as.g[i] >= s.n) {
                return null;
            }
        }
        return as;
    }
    
    public Object a(final u u, final af af, final Object o) {
        final s b = u.b;
        final as e = (as)o;
        final ax ax = new ax();
        ax.c = e.a;
        ax.a = b.h[af.a] / 2;
        ax.b = e.c;
        ax.e = e;
        float n = (float)(13.1 * Math.atan(7.4E-4 * (float)(e.b / 2.0)) + 2.24 * Math.atan((float)(e.b / 2.0) * (float)(e.b / 2.0) * 1.85E-8) + 1.0E-4 * (float)(e.b / 2.0));
        if (n <= 0.0f) {
            if (n >= 0.0f) {
                n = 0.01f;
            }
        }
        final float n2 = ax.b / n;
        ax.d = new int[ax.a];
        for (int i = 0; i < ax.a; ++i) {
            float n3 = (float)(13.1 * Math.atan(7.4E-4 * (float)(e.b / 2.0 / ax.a * i)) + 2.24 * Math.atan((float)(e.b / 2.0 / ax.a * i) * (float)(e.b / 2.0 / ax.a * i) * 1.85E-8) + 1.0E-4 * (float)(e.b / 2.0 / ax.a * i));
            if (n3 <= 0.0f) {
                if (n3 >= 0.0f) {
                    n3 = 0.01f;
                }
            }
            int b2 = (int)Math.floor(n3 * n2);
            if (b2 >= ax.b) {
                b2 = ax.b;
            }
            ax.d[i] = b2;
        }
        return ax;
    }
    
    public void a(final Object o) {
    }
    
    public Object a(final v v, final Object o, final Object o2) {
        final ax ax = (ax)o;
        final as e = ax.e;
        float[] array = null;
        if (o2 instanceof float[]) {
            array = (float[])o2;
        }
        final int d = v.b.d(e.d);
        if (d > 0) {
            final float n = d / ((1 << e.d) - 1) * e.e;
            final int d2 = v.b.d(ae.a(e.f, 0));
            if (d2 != -1 && d2 < e.f) {
                final ah ah = v.k.u[e.g[d2]];
                float n2 = 0.0f;
                if (array == null || array.length < ax.c + 1) {
                    array = new float[ax.c + 1];
                }
                else {
                    for (int i = 0; i < array.length; ++i) {
                        array[i] = 0.0f;
                    }
                }
                for (int j = 0; j < ax.c; j += ah.a) {
                    if (ah.c(array, j, v.b, ah.a) == -1) {
                        return null;
                    }
                }
                int k = 0;
                while (k < ax.c) {
                    for (int l = 0; l < ah.a; ++l, ++k) {
                        final float[] array2 = array;
                        final int n3 = k;
                        array2[n3] += n2;
                    }
                    n2 = array[k - 1];
                }
                array[ax.c] = n;
                return array;
            }
        }
        return null;
    }
    
    public int a(final v v, final Object o, final Object o2, final float[] array) {
        final ax ax = (ax)o;
        final as e = ax.e;
        if (o2 != null) {
            final float[] array2 = (float[])o2;
            at.a(array, ax.d, ax.a, ax.b, array2, ax.c, array2[ax.c], e.e);
            return 1;
        }
        for (int i = 0; i < ax.a; ++i) {
            array[i] = 0.0f;
        }
        return 0;
    }
}
