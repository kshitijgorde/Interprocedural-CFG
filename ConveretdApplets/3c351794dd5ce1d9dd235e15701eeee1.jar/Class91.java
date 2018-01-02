import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class91
{
    static Class28 aClass28_722;
    private int anInt723;
    static int anInt724;
    static boolean aBoolean725;
    
    final void method886(final byte b) {
        try {
            OpenGL.glEndList();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fo.B(" + b + ')');
        }
    }
    
    final void method887(final int n, final int n2) {
        try {
            if (n2 == -30389) {
                OpenGL.glNewList(n + this.anInt723, 4864);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fo.C(" + n + ',' + n2 + ')');
        }
    }
    
    final void method888(final char c, final boolean b) {
        try {
            OpenGL.glCallList(c + this.anInt723);
            if (b) {
                method889(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fo.A(" + c + ',' + b + ')');
        }
    }
    
    public static void method889(final boolean b) {
        try {
            if (!b) {
                method889(true);
            }
            Class91.aClass28_722 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fo.D(" + b + ')');
        }
    }
    
    static final void method890(final int n, int n2, int n3, int i, final byte b, int n4) {
        try {
            int n5 = -n2 + n3;
            int n6 = -n4 + i;
            if (n6 == 0) {
                if (~n5 != -1) {
                    Class48_Sub2_Sub1.method471(n4, (byte)(-123), n, n2, n3);
                }
            }
            else if (~n5 == -1) {
                Class160.method2513((byte)(-83), n, n4, i, n2);
            }
            else {
                if (n5 < 0) {
                    n5 = -n5;
                }
                if (~n6 > -1) {
                    n6 = -n6;
                }
                final boolean b2 = ~n6 > ~n5;
                if (b2) {
                    final int n7 = n4;
                    n4 = n2;
                    final int n8 = i;
                    i = n3;
                    n2 = n7;
                    n3 = n8;
                }
                if (~i > ~n4) {
                    final int n9 = n4;
                    final int n10 = n2;
                    n4 = i;
                    i = n9;
                    n2 = n3;
                    n3 = n10;
                }
                if (b <= -21) {
                    int n11 = n2;
                    final int n12 = i + -n4;
                    int n13 = n3 - n2;
                    int n14 = -(n12 >> -1811836063);
                    final int n15 = (~n2 > ~n3) ? 1 : -1;
                    if (n13 < 0) {
                        n13 = -n13;
                    }
                    if (!b2) {
                        for (int n16 = n4; i >= n16; ++n16) {
                            n14 += n13;
                            Class97.anIntArrayArray814[n11][n16] = n;
                            if (~n14 < -1) {
                                n11 += n15;
                                n14 -= n12;
                            }
                        }
                    }
                    else {
                        for (int j = n4; j <= i; ++j) {
                            n14 += n13;
                            Class97.anIntArrayArray814[j][n11] = n;
                            if (n14 > 0) {
                                n11 += n15;
                                n14 -= n12;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fo.E(" + n + ',' + n2 + ',' + n3 + ',' + i + ',' + b + ',' + n4 + ')');
        }
    }
    
    Class91(final ha_Sub1 ha_Sub1, final int n) {
        try {
            this.anInt723 = OpenGL.glGenLists(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fo.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class91.anInt724 = -1;
        Class91.aBoolean725 = false;
    }
}
