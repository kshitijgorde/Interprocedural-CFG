// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub29 extends Class64
{
    static Class196 aClass196_3720;
    
    Class64_Sub29(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    static final void method673(final ha ha, final d d, final int n, final int n2, final int n3, final byte b, final int n4) {
        try {
            if (Class212.anInt1600 < 100) {
                Class339.method3783(ha, d, 107);
            }
            ha.KA(n2, n, n4 + n2, n + n3);
            if (Class212.anInt1600 < 100) {
                final int n5 = 20;
                final int n6 = n4 / 2 + n2;
                final int n7 = -18 + (n + n3 / 2 + -n5);
                ha.aa(n2, n, n4, n3, -16777216, 0);
                ha.method1779(-152 + n6, n7, 304, 34, Class224_Sub1.aColorArray5036[Class98_Sub9.anInt3855].getRGB(), 0);
                ha.aa(n6 - 150, n7 + 2, Class212.anInt1600 * 3, 30, Class301.aColorArray2508[Class98_Sub9.anInt3855].getRGB(), 0);
                Class98_Sub10_Sub34.aClass43_5730.method415(Class98_Sub5_Sub1.aColorArray5533[Class98_Sub9.anInt3855].getRGB(), Class309.aClass309_2604.method3615(Class374.anInt3159, (byte)25), n6, -1, (byte)(-50), n5 + n7);
            }
            else {
                final int n8 = Class42_Sub4.anInt5371 - (int)(n4 / Class278.aFloat2064);
                final int n9 = (int)(n3 / Class278.aFloat2064) + Class98_Sub40.anInt4197;
                final int n10 = (int)(n4 / Class278.aFloat2064) + Class42_Sub4.anInt5371;
                Class231.anInt1739 = -(int)(n3 / Class278.aFloat2064) + Class98_Sub40.anInt4197;
                aa.anInt48 = (int)(n4 * 2 / Class278.aFloat2064);
                Class246_Sub3_Sub5_Sub2.anInt6268 = (int)(2 * n3 / Class278.aFloat2064);
                final int n11 = -(int)(n3 / Class278.aFloat2064) + Class98_Sub40.anInt4197;
                Class166.anInt1279 = -(int)(n4 / Class278.aFloat2064) + Class42_Sub4.anInt5371;
                Class278.method3308(Class278.anInt2075 + n8, Class278.anInt2078 + n9, n10 + Class278.anInt2075, Class278.anInt2078 + n11, n2, n, n4 + n2, n3 + n + 1);
                Class278.method3309(ha);
                Class86.method843(0, ha, Class278.method3315(ha), -1, 0);
                if (~Class64_Sub25.anInt3711 < -1) {
                    --Class287.anInt2186;
                    if (Class287.anInt2186 == 0) {
                        --Class64_Sub25.anInt3711;
                        Class287.anInt2186 = 20;
                    }
                }
                if (Class91.aBoolean725) {
                    final int n12 = n4 + (n2 - 5);
                    int n13 = n3 + (n - 8);
                    Class195.aClass43_1499.method397(16776960, 0, n12, -1, "Fps:" + Class338.anInt2842, n13);
                    n13 -= 15;
                    final Runtime runtime = Runtime.getRuntime();
                    final int n14 = (int)((runtime.totalMemory() + -runtime.freeMemory()) / 1024L);
                    int n15 = 16776960;
                    if (~n14 < -65537) {
                        n15 = 16711680;
                    }
                    Class195.aClass43_1499.method397(n15, 0, n12, -1, "Mem:" + n14 + "k", n13);
                    n13 -= 15;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.G(" + ((ha != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ')');
        }
    }
    
    Class64_Sub29(final int n, final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return -116;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.A(" + n + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (~super.anInt494 != 0xFFFFFFFE && ~super.anInt494 != -1) {
                super.anInt494 = this.method552(0);
            }
            if (b < 118) {
                method675(103, 107);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.C(" + b + ')');
        }
    }
    
    static final void method674(final int anInt5037, final int anInt5038, final int anInt5039, final Class207 aClass207_2025, final int n, final boolean aBoolean66, final byte b) {
        try {
            if (~n >= -1) {
                s_Sub1.method3434(aClass207_2025, aBoolean66, anInt5037, anInt5039, anInt5038, -16523);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            Class116.aClass98_Sub31_Sub2_965 = null;
            Class98_Sub18.anInt3951 = anInt5039;
            Class257.anInt1948 = 1;
            Class269.aClass207_2025 = aClass207_2025;
            Class76_Sub8.anInt3770 = anInt5038;
            Class1.aBoolean66 = aBoolean66;
            Class224_Sub3.anInt5037 = anInt5037;
            Class348.anInt2911 = Class366.aClass98_Sub31_Sub2_3122.method1360(-16257) / n;
            if (Class348.anInt2911 < 1) {
                Class348.anInt2911 = 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.D(" + anInt5037 + ',' + anInt5038 + ',' + anInt5039 + ',' + ((aClass207_2025 != null) ? "{...}" : "null") + ',' + n + ',' + aBoolean66 + ',' + b + ')');
        }
    }
    
    static final String method675(final int n, final int n2) {
        try {
            String s = Integer.toString(n);
            if (n2 != 16474) {
                Class64_Sub29.aClass196_3720 = null;
            }
            for (int n3 = s.length() - 3; ~n3 < -1; n3 -= 3) {
                s = s.substring(0, n3) + "," + s.substring(n3);
            }
            if (s.length() > 9) {
                return " <col=00ff80>" + s.substring(0, -8 + s.length()) + Class309.aClass309_2619.method3615(Class374.anInt3159, (byte)25) + " (" + s + ")</col>";
            }
            if (~s.length() < -7) {
                return " <col=ffffff>" + s.substring(0, -4 + s.length()) + Class309.aClass309_2621.method3615(Class374.anInt3159, (byte)25) + " (" + s + ")</col>";
            }
            return " <col=ffff00>" + s + "</col>";
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.H(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub29.aClass196_3720 = null;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.F(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method676(final byte b) {
        try {
            if (b != -58) {
                Class64_Sub29.aClass196_3720 = null;
            }
            Class64_Sub29.aClass196_3720 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.I(" + b + ')');
        }
    }
    
    final int method677(final byte b) {
        try {
            if (b < 119) {
                Class64_Sub29.aClass196_3720 = null;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wfa.E(" + b + ')');
        }
    }
}
