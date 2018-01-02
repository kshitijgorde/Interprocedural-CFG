// 
// Decompiled by Procyon v0.5.30
// 

final class Class176
{
    static Class204 aClass204_1372;
    
    static final void method2579(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        try {
            if (n5 == n4 && n9 == n && n7 == n8 && n2 == n3) {
                Class91.method890(n6, n, n3, n8, (byte)(-105), n5);
            }
            else {
                int n11 = n5;
                int n12 = n;
                final int n13 = 3 * n5;
                final int n14 = n * 3;
                final int n15 = n4 * 3;
                final int n16 = 3 * n9;
                final int n17 = n7 * 3;
                final int n18 = 3 * n2;
                final int n19 = n15 + n8 + (-n17 - n5);
                final int n20 = -n + (n3 - n18 + n16);
                final int n21 = -n15 + (n17 - (n15 - n13));
                final int n22 = n14 + -n16 + (-n16 + n18);
                final int n23 = -n13 + n15;
                final int n24 = n16 - n14;
                for (int i = 128; i <= 4096; i += 128) {
                    final int n25 = i * i >> -1499707220;
                    final int n26 = n25 * i >> -1727145204;
                    final int n27 = n19 * n26;
                    final int n28 = n20 * n26;
                    final int n29 = n21 * n25;
                    final int n30 = n22 * n25;
                    final int n31 = i * n23;
                    final int n32 = n24 * i;
                    final int n33 = n5 + (n27 - -n29 - -n31 >> 390504332);
                    final int n34 = n + (n30 + (n28 + n32) >> 1323025196);
                    Class91.method890(n6, n12, n34, n33, (byte)(-36), n11);
                    n11 = n33;
                    n12 = n34;
                }
            }
            if (n10 != 22024) {
                Class176.aClass204_1372 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lt.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    static final void method2580(final int n, final String s) {
        try {
            if (s != null) {
                if ((~Class314.anInt2692 <= -201 && !Class64_Sub18.aBoolean3690) || Class314.anInt2692 >= 200) {
                    za_Sub2.method1684(Class309.aClass309_2596.method3615(Class374.anInt3159, (byte)25), 4, (byte)49);
                    final String method3615 = Class309.aClass309_2597.method3615(Class374.anInt3159, (byte)25);
                    if (method3615 != null) {
                        za_Sub2.method1684(method3615, 4, (byte)127);
                    }
                }
                else {
                    final String method3616 = Class353.method3867(-1, s);
                    if (method3616 != null && n == 4) {
                        for (int n2 = 0; ~Class314.anInt2692 < ~n2; ++n2) {
                            final String method3617 = Class353.method3867(-1, Class98_Sub25.aStringArray4026[n2]);
                            if (method3617 != null && method3617.equals(method3616)) {
                                za_Sub2.method1684(s + Class309.aClass309_2624.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-32));
                                return;
                            }
                            if (Class315.aStringArray3527[n2] != null) {
                                final String method3618 = Class353.method3867(n - 5, Class315.aStringArray3527[n2]);
                                if (method3618 != null && method3618.equals(method3616)) {
                                    za_Sub2.method1684(s + Class309.aClass309_2624.method3615(Class374.anInt3159, (byte)25), 4, (byte)119);
                                    return;
                                }
                            }
                        }
                        for (int i = 0; i < Class248.anInt1897; ++i) {
                            final String method3619 = Class353.method3867(n ^ 0xFFFFFFFB, Class246_Sub4_Sub1.aStringArray6171[i]);
                            if (method3619 != null && method3619.equals(method3616)) {
                                za_Sub2.method1684(Class309.aClass309_2629.method3615(Class374.anInt3159, (byte)25) + s + Class309.aClass309_2630.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-96));
                                return;
                            }
                            if (Class98_Sub45.aStringArray4255[i] != null) {
                                final String method3620 = Class353.method3867(-1, Class98_Sub45.aStringArray4255[i]);
                                if (method3620 != null && method3620.equals(method3616)) {
                                    za_Sub2.method1684(Class309.aClass309_2629.method3615(Class374.anInt3159, (byte)25) + s + Class309.aClass309_2630.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-103));
                                    return;
                                }
                            }
                        }
                        if (Class353.method3867(-1, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537).equals(method3616)) {
                            za_Sub2.method1684(Class309.aClass309_2627.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-100));
                        }
                        else {
                            final Class98_Sub11 method3621 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub4_Sub3.aClass171_6446, Class331.aClass117_2811);
                            method3621.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(s, (byte)88), n + 81);
                            method3621.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
                            Class98_Sub10_Sub29.sendPacket(false, method3621);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lt.C(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2581(final int n) {
        try {
            if (n > 59) {
                Class176.aClass204_1372 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lt.B(" + n + ')');
        }
    }
    
    static final void method2582(final byte b) {
        try {
            Class98_Sub46_Sub11.aClass332Array6032 = null;
            Class119_Sub4.aClass332Array4739 = null;
            Class352.aClass332Array3000 = null;
            Class306.aClass332Array2557 = null;
            Class334.aClass332_3471 = null;
            Class287_Sub2.aClass332Array3275 = null;
            Class69_Sub2.aClass43_5336 = null;
            Class177.aClass332Array1382 = null;
            Class98_Sub10_Sub34.aClass43_5730 = null;
            Class254.aClass332Array1943 = null;
            Class64_Sub18.aClass332Array3689 = null;
            Class64_Sub14.aClass332Array3675 = null;
            Class284_Sub2_Sub2.aClass332_6199 = null;
            Class195.aClass43_1499 = null;
            Class340.aClass332Array2848 = null;
            Class76_Sub7.aClass332Array3764 = null;
            Class2.aClass332Array72 = null;
            if (b >= 11) {
                Class93.aClass332Array3512 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lt.D(" + b + ')');
        }
    }
    
    static {
        Class176.aClass204_1372 = new Class204();
    }
}
