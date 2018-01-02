// 
// Decompiled by Procyon v0.5.30
// 

final class Class216
{
    int anInt1617;
    int anInt1618;
    int anInt1619;
    int anInt1620;
    int anInt1621;
    static Class341 aClass341_1622;
    int anInt1623;
    int anInt1624;
    int anInt1625;
    int anInt1626;
    int anInt1627;
    int anInt1628;
    int anInt1629;
    
    static final boolean method2793(final int n, final byte b, final int n2) {
        try {
            if (b >= -87) {
                Class216.aClass341_1622 = null;
            }
            return Class161.method2514(n2, 16, n) & Class322.method3672(n, n2, 2048);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nt.E(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final void method2794(final byte b) {
        try {
            if (b <= -74) {
                for (int n = 0; ~n > ~Class150.anInt1211; ++n) {
                    final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[n], -1);
                    if (class98_Sub39 != null) {
                        final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        Class98_Sub10_Sub10.method1038(aClass246_Sub3_Sub4_Sub2_Sub1_4187, aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112, -12212);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nt.D(" + b + ')');
        }
    }
    
    final boolean method2795(final Class216 class216, final boolean b) {
        try {
            if (~this.anInt1617 == ~class216.anInt1617 && ~this.anInt1618 == ~class216.anInt1618 && this.anInt1621 == class216.anInt1621) {
                return true;
            }
            if (!b) {
                method2797(64, 32, 21, 60);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nt.B(" + ((class216 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method2796(final int n, final int n2, final int n3, final int n4, final byte b) {
        try {
            final int anInt5554 = Class98_Sub10_Sub5.anInt5554;
            if (anInt5554 != 0) {
                if (~anInt5554 == 0xFFFFFFFE) {
                    Class98_Sub10_Sub5.anInt5554 = 2;
                    Class64_Sub27.anInt3716 = n2;
                    Class82.anInt629 = n;
                    Class98_Sub19.anInt3956 = n3;
                    Applet_Sub1.anInt2 = n4;
                    return;
                }
                if (~anInt5554 != 0xFFFFFFFD) {
                    return;
                }
                if (!client.aBoolean3553) {
                    if (n3 > Class98_Sub19.anInt3956) {
                        Class98_Sub19.anInt3956 = n3;
                    }
                    if (~n < ~Class82.anInt629) {
                        Class82.anInt629 = n;
                    }
                    if (n4 < Applet_Sub1.anInt2) {
                        Applet_Sub1.anInt2 = n4;
                    }
                    if (~Class64_Sub27.anInt3716 < ~n2) {
                        Class64_Sub27.anInt3716 = n2;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nt.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static final void method2797(final int anInt6141, final int anInt6142, final int anInt6143, final int n) {
        try {
            if (~anInt6141 != ~Class224_Sub2_Sub1.anInt6141 || ~Class109.anInt926 != ~anInt6143 || ~aa_Sub1.anInt3558 != ~anInt6142) {
                Class224_Sub2_Sub1.anInt6141 = anInt6141;
                Class109.anInt926 = anInt6143;
                Class358.aBoolean3033 = true;
                aa_Sub1.anInt3558 = anInt6142;
                final double n2 = -(2 * anInt6141 * 3.141592653589793) / 16384.0;
                final double n3 = -(anInt6143 * 2 * 3.141592653589793) / 16384.0;
                final double cos = Math.cos(n3);
                final double sin = Math.sin(n3);
                final double cos2 = Math.cos(n2);
                final double sin2 = Math.sin(n2);
                Class224_Sub3.aDouble5038 = -sin * cos2;
                Class98_Sub5_Sub2.aDouble5537 = sin2;
                Class64_Sub11.aDouble3669 = sin * sin2;
                Class367.aDouble3543 = cos * cos2;
                Class279.aDouble2100 = 0.0;
                if (n == 25980) {
                    Class76_Sub5.aDouble3747 = sin;
                    Class98_Sub10_Sub25.aDouble5675 = cos2;
                    za_Sub2.aDouble6081 = cos;
                    Class283.aDouble2145 = sin2 * -cos;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nt.C(" + anInt6141 + ',' + anInt6142 + ',' + anInt6143 + ',' + n + ')');
        }
    }
    
    public static void method2798(final int n) {
        try {
            Class216.aClass341_1622 = null;
            if (n != 0) {
                Class216.aClass341_1622 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nt.F(" + n + ')');
        }
    }
}
