// 
// Decompiled by Procyon v0.5.30
// 

public class an extends ab
{
    public static int[][][] a;
    
    public Object a(final s s, final w w) {
        int n = 0;
        final aw aw = new aw();
        aw.a = w.d(24);
        aw.b = w.d(24);
        aw.c = w.d(24) + 1;
        aw.d = w.d(6) + 1;
        aw.e = w.d(8);
        for (int i = 0; i < aw.d; ++i) {
            int d = w.d(3);
            if (w.d(1) != 0) {
                d |= w.d(5) << 3;
            }
            aw.f[i] = d;
            n += ae.a(d);
        }
        for (int j = 0; j < n; ++j) {
            aw.g[j] = w.d(8);
        }
        if (aw.e >= s.n) {
            this.a(aw);
            return null;
        }
        for (int k = 0; k < n; ++k) {
            if (aw.g[k] >= s.n) {
                this.a(aw);
                return null;
            }
        }
        return aw;
    }
    
    public Object a(final u u, final af af, final Object o) {
        final aw a = (aw)o;
        final a1 a2 = new a1();
        int n = 0;
        int d = 0;
        a2.a = a;
        a2.b = af.d;
        a2.c = a.d;
        a2.e = u.u;
        a2.f = u.u[a.e];
        final int a3 = a2.f.a;
        a2.g = new int[a2.c][];
        for (int i = 0; i < a2.c; ++i) {
            final int a4 = ae.a(a.f[i], 0);
            if (a4 != 0) {
                if (a4 > d) {
                    d = a4;
                }
                a2.g[i] = new int[a4];
                for (int j = 0; j < a4; ++j) {
                    if ((a.f[i] & 1 << j) != 0x0) {
                        a2.g[i][j] = a.g[n++];
                    }
                }
            }
        }
        a2.h = (int)Math.rint(Math.pow(a2.c, a3));
        a2.d = d;
        a2.i = new int[a2.h][];
        for (int k = 0; k < a2.h; ++k) {
            int n2 = k;
            int n3 = a2.h / a2.c;
            a2.i[k] = new int[a3];
            for (int l = 0; l < a3; ++l) {
                final int n4 = n2 / n3;
                n2 -= n4 * n3;
                n3 /= a2.c;
                a2.i[k][l] = n4;
            }
        }
        return a2;
    }
    
    public void a(final Object o) {
    }
    
    public static synchronized int a(final v v, final Object o, final float[][] array, final int n, final int n2) {
        final a1 a1 = (a1)o;
        final aw a2 = a1.a;
        final int c = a2.c;
        final int a3 = a1.f.a;
        final int n3 = (a2.b - a2.a) / c;
        final int n4 = (n3 + a3 - 1) / a3;
        if (an.a.length < n) {
            an.a = new int[n][][];
            for (int i = 0; i < n; ++i) {
                an.a[i] = new int[n4][];
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                if (an.a[j] == null || an.a[j].length < n4) {
                    an.a[j] = new int[n4][];
                }
            }
        }
        for (int k = 0; k < a1.d; ++k) {
            int l = 0;
            int n5 = 0;
            while (l < n3) {
                if (k == 0) {
                    for (int n6 = 0; n6 < n; ++n6) {
                        final int a4 = a1.f.a(v.b);
                        if (a4 == -1) {
                            return 0;
                        }
                        an.a[n6][n5] = a1.i[a4];
                        if (an.a[n6][n5] == null) {
                            return 0;
                        }
                    }
                }
                for (int n7 = 0; n7 < a3 && l < n3; ++n7, ++l) {
                    for (int n8 = 0; n8 < n; ++n8) {
                        final int n9 = a2.a + l * c;
                        if ((a2.f[an.a[n8][n5][n7]] & 1 << k) != 0x0) {
                            final ah ah = a1.e[a1.g[an.a[n8][n5][n7]][k]];
                            if (ah != null) {
                                if (n2 == 0) {
                                    if (ah.a(array[n8], n9, v.b, c) == -1) {
                                        return 0;
                                    }
                                }
                                else if (n2 == 1 && ah.b(array[n8], n9, v.b, c) == -1) {
                                    return 0;
                                }
                            }
                        }
                    }
                }
                ++n5;
            }
        }
        return 0;
    }
    
    public int a(final v v, final Object o, final float[][] array, final int[] array2, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            if (array2[i] != 0) {
                array[n2++] = array[i];
            }
        }
        if (n2 != 0) {
            return a(v, o, array, n2, 0);
        }
        return 0;
    }
    
    static {
        an.a = new int[2][][];
    }
}
