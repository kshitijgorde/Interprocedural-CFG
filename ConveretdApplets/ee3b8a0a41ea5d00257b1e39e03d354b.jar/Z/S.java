// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class S extends E
{
    final Object I(final L l, final Z z) {
        final M m = new M();
        m.I = z.C(8);
        m.Z = z.C(16);
        m.C = z.C(16);
        m.B = z.C(6);
        m.D = z.C(8);
        m.F = z.C(4) + 1;
        if (m.I < 1 || m.Z < 1 || m.C < 1 || m.F < 1) {
            return null;
        }
        for (int i = 0; i < m.F; ++i) {
            m.J[i] = z.C(8);
            if (m.J[i] < 0 || m.J[i] >= l.K) {
                return null;
            }
        }
        return m;
    }
    
    final Object I(final J j, final P p3, final Object o) {
        final L z = j.Z;
        final M c = (M)o;
        final R r = new R();
        r.m = c.I;
        r.n = z.J[p3.I] / 2;
        r.I = c.C;
        r.C = c;
        r.B.I(r.I, r.m);
        final float n = r.I / I((float)(c.Z / 2.0));
        r.Z = new int[r.n];
        for (int i = 0; i < r.n; ++i) {
            int k = (int)Math.floor(I((float)(c.Z / 2.0 / r.n * i)) * n);
            if (k >= r.I) {
                k = r.I;
            }
            r.Z[i] = k;
        }
        return r;
    }
    
    private static float I(final float n) {
        return (float)(13.1 * Math.atan(7.4E-4 * n) + 2.24 * Math.atan(n * n * 1.85E-8) + 1.0E-4 * n);
    }
    
    final void I(final Object o) {
    }
    
    final Object I(final I i, final Object o, final Object o2) {
        final R r = (R)o;
        final M c = r.C;
        float[] array = null;
        if (o2 instanceof float[]) {
            array = (float[])o2;
        }
        final int c2 = i.Z.C(c.B);
        if (c2 > 0) {
            final float n = c2 / ((1 << c.B) - 1) * c.D;
            final int c3 = i.Z.C(atan(c.F));
            if (c3 != -1 && c3 < c.F) {
                final C c4 = i.E.P[c.J[c3]];
                float n2 = 0.0f;
                if (array == null || array.length < r.m + 1) {
                    array = new float[r.m + 1];
                }
                else {
                    for (int j = 0; j < array.length; ++j) {
                        array[j] = 0.0f;
                    }
                }
                for (int k = 0; k < r.m; k += c4.I) {
                    if (c4.C(array, k, i.Z, c4.I) == -1) {
                        return null;
                    }
                }
                int l = 0;
                while (l < r.m) {
                    for (int n3 = 0; n3 < c4.I; ++n3) {
                        final float[] array2 = array;
                        final int n4 = l;
                        array2[n4] += n2;
                        ++l;
                    }
                    n2 = array[l - 1];
                }
                array[r.m] = n;
                return array;
            }
        }
        return null;
    }
    
    final int I(final I i, final Object o, final Object o2, final float[] array) {
        final R r = (R)o;
        final M c = r.C;
        if (o2 != null) {
            final float[] array2 = (float[])o2;
            Y.I(array, r.Z, r.n, r.I, array2, r.m, array2[r.m], c.D);
            return 1;
        }
        for (int j = 0; j < r.n; ++j) {
            array[j] = 0.0f;
        }
        return 0;
    }
    
    private static int atan(int i) {
        int n = 0;
        while (i != 0) {
            ++n;
            i >>>= 1;
        }
        return n;
    }
}
