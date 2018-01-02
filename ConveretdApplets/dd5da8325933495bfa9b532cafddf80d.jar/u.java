import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public final class u
{
    private static q[] a;
    private static int b;
    private static Random c;
    private static short[] d;
    
    public static void a() {
        final boolean dj = p.dJ;
        u.b = 0;
        int n = 0;
        while (true) {
            Label_0045: {
                if (!dj) {
                    break Label_0045;
                }
                final int b = u.a[n].b;
                u.a[n].c = b;
                u.b += b;
                ++n;
            }
            if (n >= u.a.length) {
                if (u.d == null) {
                    int n2 = 0;
                    u.d = new short[u.b];
                    int n3 = 0;
                    while (true) {
                        Label_0111: {
                            if (!dj) {
                                break Label_0111;
                            }
                            int n4 = 0;
                            while (true) {
                                Label_0096: {
                                    if (!dj) {
                                        break Label_0096;
                                    }
                                    u.d[n2++] = (short)n3;
                                    ++n4;
                                }
                                if (n4 < u.a[n3].b) {
                                    continue;
                                }
                                break;
                            }
                            ++n3;
                        }
                        if (n3 < u.a.length) {
                            continue;
                        }
                        break;
                    }
                }
                return;
            }
            continue;
        }
    }
    
    public static void b() {
        int n = 0;
        while (true) {
            Label_0020: {
                if (!p.dJ) {
                    break Label_0020;
                }
                u.a[n].h = 0;
                ++n;
            }
            if (n >= u.a.length) {
                return;
            }
            continue;
        }
    }
    
    public static void c() {
        int n = 0;
        while (true) {
            Label_0049: {
                if (!p.dJ) {
                    break Label_0049;
                }
                u.a[n].f = 0;
                u.a[n].g = 0;
                u.a[n].i = p.dj[u.a[n].d];
                ++n;
            }
            if (n >= 26) {
                return;
            }
            continue;
        }
    }
    
    public static void a(final int n) {
        final q q = u.a[n];
        ++q.f;
    }
    
    public static void b(final int n) {
        final q q = u.a[n];
        ++q.g;
    }
    
    public static float a(final int n, final int n2) {
        if (u.a[n].f < 5 || n2 == 1) {
            final float n3 = u.a[n].i * f(n) / 100.0f;
            if (!p.dJ) {
                return n3;
            }
        }
        float n4;
        if ((n4 = u.a[n].g / u.a[n].f) < 0.5f) {
            n4 = 0.5f;
        }
        return n4 * f(n);
    }
    
    public static int d() {
        short n2 = 0;
    Block_1:
        while (true) {
            final short n = u.d[i.a(0, u.b - 1)];
            int i = u.a[n].c;
            while (i > 0) {
                n2 = (short)(i = n);
                if (!p.dJ) {
                    break Block_1;
                }
            }
        }
        return n2;
    }
    
    public static void c(final int n) {
        final q q = u.a[n];
        --q.c;
    }
    
    public static void d(final int n) {
        final q q = u.a[n];
        ++q.c;
    }
    
    public static int e(final int n) {
        return u.a[n].c;
    }
    
    public static int f(final int n) {
        return u.a[n].d * 10;
    }
    
    public static int a(final byte[] array, final int n) {
        final boolean dj = p.dJ;
        int n2 = 0;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0026: {
                    if (!dj) {
                        break Label_0026;
                    }
                    final int n4 = n2;
                    f(array[n3]);
                    final int n5;
                    n2 = n4 + n5;
                    ++n3;
                }
                if (n3 < n) {
                    continue;
                }
                break;
            }
            final int n4 = n;
            final int n5 = 6;
            if (!dj) {
                if (n == n5) {
                    n2 += 100;
                }
                return n2;
            }
            continue;
        }
    }
    
    public static String g(final int n) {
        return u.a[n].a;
    }
    
    public static void b(final int n, final int e) {
        int n2 = 0;
        while (true) {
            Label_0032: {
                if (!p.dJ) {
                    break Label_0032;
                }
                if (u.a[n2].d == n) {
                    u.a[n2].e = e;
                }
                ++n2;
            }
            if (n2 >= 26) {
                return;
            }
            continue;
        }
    }
    
    public static int h(final int n) {
        return u.a[n].e;
    }
    
    public static boolean i(final int n) {
        return u.a[n].j;
    }
    
    public static int j(final int n) {
        return u.a[n].h;
    }
    
    public static void k(final int n) {
        final q q = u.a[n];
        ++q.h;
    }
    
    static {
        u.a = new q[] { new q("A", 11, 1, true), new q("B", 3, 4, false), new q("C", 5, 3, false), new q("D", 6, 2, false), new q("E", 19, 1, true), new q("F", 4, 3, false), new q("G", 3, 3, false), new q("H", 7, 3, false), new q("I", 11, 1, true), new q("J", 1, 5, false), new q("K", 2, 4, false), new q("L", 6, 2, false), new q("M", 4, 3, false), new q("N", 10, 2, false), new q("O", 12, 1, true), new q("P", 3, 3, false), new q("Q", 1, 5, false), new q("R", 10, 2, false), new q("S", 10, 2, false), new q("T", 13, 2, false), new q("U", 5, 1, true), new q("V", 2, 4, false), new q("W", 3, 4, false), new q("X", 1, 5, false), new q("Y", 3, 4, false), new q("Z", 1, 5, false) };
        u.c = null;
        u.d = null;
    }
}
