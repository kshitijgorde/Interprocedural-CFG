// 
// Decompiled by Procyon v0.5.30
// 

final class Class154
{
    static ha aHa1231;
    Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232;
    Class154 aClass154_1233;
    
    final void method2491(final int n) {
        try {
            if (Class76_Sub8.anInt3766 < 500) {
                this.aClass154_1233 = Class119_Sub1.aClass154_4718;
                if (n != 2) {
                    this.aClass246_Sub3_Sub4_1232 = null;
                }
                this.aClass246_Sub3_Sub4_1232 = null;
                ++Class76_Sub8.anInt3766;
                Class119_Sub1.aClass154_4718 = this;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kfa.B(" + n + ')');
        }
    }
    
    public static void method2492(final int n) {
        try {
            Class154.aHa1231 = null;
            if (n != -1) {
                method2492(65);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kfa.C(" + n + ')');
        }
    }
    
    static final void method2493(final int anInt890, final int n, final byte[][][] array, final int n2, final int anInt891, final int[] array2, final boolean b, final int[] array3, final byte b2, final boolean b3, final int n3, final int n4, final int[] array4, final int n5, final int[] array5, final int anInt892, final int[] array6, final boolean b4) {
        try {
            if (Class98_Sub46.anInt4261 != -1) {
                final int[] y = Class154.aHa1231.Y();
                if (b3) {
                    final int anInt893 = y[0];
                    final int anInt894 = y[1];
                    final int n6 = y[2];
                    final int n7 = y[3];
                    int n8 = n6;
                    int n9 = n7;
                    if (Class98_Sub46.anInt4261 == 1) {
                        n8 = Class149.anInt1208 * n6 / Class48_Sub1_Sub2.anInt5519;
                        n9 = n7 * Class149.anInt1208 / Class48_Sub1_Sub2.anInt5519;
                    }
                    if (!Class358.aBoolean3033) {
                        if (~Class98_Sub46.anInt4261 == 0xFFFFFFFE) {
                            Class53_Sub1.method498(0);
                        }
                        final int n10 = anInt891 + -Canvas_Sub1.anInt23;
                        final int n11 = anInt890 - Class103.anInt890;
                        final int n12 = -Class24.anInt223 + anInt892;
                        final int n13 = (int)(n8 * (n12 * Class76_Sub5.aDouble3747 + (n11 * Class279.aDouble2100 + n10 * za_Sub2.aDouble6081)) / n2);
                        final int n14 = (int)(n9 * (n12 * Class283.aDouble2145 + (n11 * Class98_Sub10_Sub25.aDouble5675 + n10 * Class64_Sub11.aDouble3669)) / n2);
                        final double n15 = Class367.aDouble3543 * n12 + (n10 * Class224_Sub3.aDouble5038 + n11 * Class98_Sub5_Sub2.aDouble5537);
                        final int anInt895 = Class377.anInt3183 + n13 + -Class151_Sub5.anInt4993;
                        final int anInt896 = Class111_Sub3.anInt4701 + (n14 + -Class38.anInt359);
                        final int n16 = anInt895 - -Class151_Sub7.anInt5005;
                        final int n17 = anInt896 - -Class149.anInt1208;
                        if ((~anInt895 > -1 || anInt896 < 0 || aa_Sub1.anInt3556 < n16 || Class48_Sub1_Sub2.anInt5519 < n17) && Class98_Sub46.anInt4261 != 2) {
                            if (~n16 >= -1 || ~n17 >= -1 || aa_Sub1.anInt3556 <= anInt895 || anInt896 >= Class48_Sub1_Sub2.anInt5519) {
                                Class358.aBoolean3033 = true;
                            }
                            else {
                                final int n18 = anInt895 + -Class377.anInt3183;
                                final int n19 = anInt896 + -Class111_Sub3.anInt4701;
                                int n20 = 0;
                                int n21 = 0;
                                int n22 = 0;
                                int n23 = 0;
                                double n24 = 0.0;
                                if (~Class98_Sub46.anInt4261 != -1) {
                                    if (Class98_Sub46.anInt4261 == 1) {
                                        n23 = n19 / Class98_Sub10_Sub38.anInt5761;
                                        n22 = n18 / Class197.anInt1513;
                                        n21 = n23 * Class98_Sub10_Sub38.anInt5761;
                                        n20 = Class197.anInt1513 * n22;
                                        n24 = (n18 * n20 - -(n19 * n21)) * (Class98_Sub39.aDouble4188 + n15) / (n18 * n18 + n19 * n19);
                                    }
                                }
                                else {
                                    n24 = Class98_Sub39.aDouble4188 + n15;
                                    n20 = n18;
                                    n21 = n19;
                                }
                                final double n25 = -n24;
                                int n26 = 0;
                                int n27 = 0;
                                int n28 = 0;
                                int n29 = 0;
                                int n30 = 0;
                                int n31;
                                int n32;
                                int n33;
                                int n34;
                                if (n20 >= 0) {
                                    n31 = aa_Sub1.anInt3556 - n20;
                                    n32 = 0;
                                    if (~Class98_Sub46.anInt4261 == 0xFFFFFFFE) {
                                        n30 = n22;
                                        n28 = -n22 + Class191.anInt1477;
                                    }
                                    n33 = n20;
                                    n34 = n31;
                                }
                                else {
                                    n34 = 0;
                                    n32 = -n20;
                                    n31 = aa_Sub1.anInt3556 - -n20;
                                    n33 = n32;
                                    if (~Class98_Sub46.anInt4261 == 0xFFFFFFFE) {
                                        n30 = -n22;
                                        n28 = 0;
                                    }
                                }
                                int n35 = 0;
                                int n36;
                                int n37;
                                int n38;
                                int n39;
                                int n40;
                                int n41;
                                if (n21 >= 0) {
                                    n36 = 0;
                                    n37 = Class48_Sub1_Sub2.anInt5519 + -n21;
                                    if (Class98_Sub46.anInt4261 == 1) {
                                        n29 = 0;
                                        n27 = n23;
                                        n26 = (n35 = -n23 + Class63.anInt493);
                                    }
                                    n38 = n37;
                                    n39 = n37;
                                    n40 = 0;
                                    n41 = n21;
                                }
                                else {
                                    n37 = Class48_Sub1_Sub2.anInt5519 + n21;
                                    n39 = 0;
                                    n36 = -n21;
                                    n41 = (n40 = n36);
                                    if (Class98_Sub46.anInt4261 == 1) {
                                        n26 = 0;
                                        n27 = -n23;
                                        n35 = Class63.anInt493 - -n23;
                                        n29 = n27;
                                    }
                                    n38 = n37;
                                }
                                final Class218 aClass218_635 = Class266.aClass84_1988.aClass218_635;
                                for (Class246_Sub1 class246_Sub1 = (Class246_Sub1)aClass218_635.method2803((byte)15); class246_Sub1 != null; class246_Sub1 = (Class246_Sub1)aClass218_635.method2809(false)) {
                                    final Class246_Sub6[] aClass246_Sub6Array5067 = class246_Sub1.aClass246_Sub6Array5067;
                                    int n42 = 1;
                                    for (int n43 = 0; aClass246_Sub6Array5067.length > n43; ++n43) {
                                        final Class246_Sub6 class246_Sub2 = aClass246_Sub6Array5067[n43];
                                        final int anInt897 = class246_Sub2.anInt5111;
                                        final int anInt898 = class246_Sub2.anInt5113;
                                        final int anInt899 = class246_Sub2.anInt5110;
                                        final int anInt900 = class246_Sub2.anInt5112;
                                        final int n44 = class246_Sub2.anInt5110 = anInt899 - n20;
                                        final int anInt901 = class246_Sub2.anInt5109;
                                        final int n45 = class246_Sub2.anInt5112 = -n21 + anInt900;
                                        final int n46 = class246_Sub2.anInt5111 = -n20 + anInt897;
                                        final int n47 = class246_Sub2.anInt5113 = -n21 + anInt898;
                                        if (n42 != 0 && aa_Sub1.anInt3556 >= ((~n44 >= ~n46) ? n44 : n46) - anInt901 && Class48_Sub1_Sub2.anInt5519 >= -anInt901 + ((n47 < n45) ? n47 : n45) && ~(anInt901 + ((~n44 < ~n46) ? n44 : n46)) <= -1 && ~(anInt901 + ((n45 > n47) ? n45 : n47)) <= -1) {
                                            n42 = 0;
                                        }
                                    }
                                    if (n42 != 0) {
                                        class246_Sub1.method2965((byte)(-64));
                                        Class35.method333(class246_Sub1, 9);
                                    }
                                }
                                if (~Class98_Sub46.anInt4261 == -1) {
                                    Class154.aHa1231.method1740(Class31.anInterface17_301);
                                }
                                Class154.aHa1231.F(-n20, -n21);
                                Class154.aHa1231.b(n32, n36, n31, n37, n25);
                                Class41.method367(Class98_Sub39.aDouble4188 + n25, 14794);
                                Class291.aDouble2199 = Class98_Sub39.aDouble4188 + n25;
                                if (Class98_Sub46.anInt4261 != 1) {
                                    Class76_Sub11.anInt3798 = -n21 + (-Class38.anInt359 + (Class111_Sub3.anInt4701 + anInt894));
                                    Class246_Sub10.anInt5154 = -Class151_Sub5.anInt4993 + Class377.anInt3183 + anInt893 - n20;
                                    Class59.anInt466 = n9;
                                    Class138.anInt1085 = n8;
                                    Class154.aHa1231.DA(Class246_Sub10.anInt5154, Class76_Sub11.anInt3798, Class138.anInt1085, Class59.anInt466);
                                }
                                else {
                                    Class76_Sub11.anInt3798 = -n21 + anInt894 - Class38.anInt359;
                                    Class246_Sub10.anInt5154 = -n20 + -Class151_Sub5.anInt4993 + anInt893;
                                    Class138.anInt1085 = n8;
                                    Class59.anInt466 = n9;
                                    Class154.aHa1231.DA(Class246_Sub10.anInt5154, Class76_Sub11.anInt3798, Class138.anInt1085, Class59.anInt466);
                                }
                                Class85.method838(Class266.aClass84_1988);
                                if (~n41 < -1) {
                                    Class154.aHa1231.KA(0, n39, aa_Sub1.anInt3556, n41 + n39);
                                    Class154.aHa1231.ya();
                                    Class154.aHa1231.GA(Class49.anInt415);
                                    Class60.method535(n5, anInt891, anInt890, anInt892, array, array3, array6, array4, array5, array2, n, b2, n3, n4, b, b4, n2, 1, false);
                                }
                                if (n33 > 0) {
                                    Class154.aHa1231.KA(n34, n40, n34 + n33, n40 - -n38);
                                    Class154.aHa1231.ya();
                                    Class154.aHa1231.GA(Class49.anInt415);
                                    Class60.method535(n5, anInt891, anInt890, anInt892, array, array3, array6, array4, array5, array2, n, b2, n3, n4, b, b4, n2, 1, false);
                                }
                                Class154.aHa1231.la();
                                Class115.method2155();
                                if (Class98_Sub46.anInt4261 == 0) {
                                    Class154.aHa1231.method1776();
                                }
                                Class98_Sub39.aDouble4188 += n25;
                                Class38.anInt359 += n21;
                                Class151_Sub5.anInt4993 += n20;
                                Class98_Sub46_Sub13_Sub2.anInt6309 = -Class151_Sub5.anInt4993 + Class377.anInt3183 - -n13;
                                Class272.anInt2037 = -Class38.anInt359 + n14 + Class111_Sub3.anInt4701;
                                if (Class98_Sub46.anInt4261 == 1) {
                                    Class268.anInt2007 += n23;
                                    Class76_Sub8.anInt3780 += n22;
                                    for (int i = 0; i < Class63.anInt493; ++i) {
                                        final int n48 = Class198.method2678((byte)6, i - -Class268.anInt2007, Class63.anInt493) * Class191.anInt1477;
                                        for (int n49 = 0; ~Class191.anInt1477 < ~n49; ++n49) {
                                            Class172.anInterface17Array1327[Class198.method2678((byte)6, n49 + Class76_Sub8.anInt3780, Class191.anInt1477) + n48].method56(n49 * Class197.anInt1513, i * Class98_Sub10_Sub38.anInt5761, Class197.anInt1513, Class98_Sub10_Sub38.anInt5761, 0, 0, (i >= n26 && i < n26 - -n27) || (~n29 >= ~i && i < n35 + n29 && n49 >= n28 && ~(n30 + n28) < ~n49), true);
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            Class98_Sub46_Sub13_Sub2.anInt6309 = anInt895;
                            Class272.anInt2037 = anInt896;
                            if (Class98_Sub46.anInt4261 == 2) {
                                Class98_Sub39.aDouble4188 = -n15;
                            }
                        }
                    }
                    if (Class358.aBoolean3033) {
                        Class98_Sub39.aDouble4188 = 0.0;
                        Class98_Sub46_Sub13_Sub2.anInt6309 = Class377.anInt3183;
                        Class151_Sub5.anInt4993 = 0;
                        Class103.anInt890 = anInt890;
                        Class24.anInt223 = anInt892;
                        Class272.anInt2037 = Class111_Sub3.anInt4701;
                        Canvas_Sub1.anInt23 = anInt891;
                        Class38.anInt359 = 0;
                        if (~Class98_Sub46.anInt4261 == -1) {
                            Class154.aHa1231.method1740(Class31.anInterface17_301);
                        }
                        Class154.aHa1231.la();
                        Class154.aHa1231.ya();
                        Class154.aHa1231.GA(Class49.anInt415);
                        Class98_Sub5_Sub3.aClass111_5540.method2093(Canvas_Sub1.anInt23, Class103.anInt890, Class24.anInt223, Class224_Sub2_Sub1.anInt6141, Class109.anInt926, aa_Sub1.anInt3558);
                        Class154.aHa1231.a(Class98_Sub5_Sub3.aClass111_5540);
                        if (Class98_Sub46.anInt4261 != 1) {
                            Class59.anInt466 = n9;
                            Class246_Sub10.anInt5154 = Class377.anInt3183 + anInt893;
                            Class138.anInt1085 = n8;
                            Class76_Sub11.anInt3798 = Class111_Sub3.anInt4701 + anInt894;
                            Class154.aHa1231.DA(Class246_Sub10.anInt5154, Class76_Sub11.anInt3798, Class138.anInt1085, Class59.anInt466);
                        }
                        else {
                            Class138.anInt1085 = n8;
                            Class59.anInt466 = n9;
                            Class76_Sub11.anInt3798 = anInt894;
                            Class246_Sub10.anInt5154 = anInt893;
                            Class154.aHa1231.DA(Class246_Sub10.anInt5154, Class76_Sub11.anInt3798, Class138.anInt1085, Class59.anInt466);
                        }
                        Class291.aDouble2199 = 0.0;
                        Class266.aClass84_1988.method833(0);
                        Class85.method838(Class266.aClass84_1988);
                        Class60.method535(n5, anInt891, anInt890, anInt892, array, array3, array6, array4, array5, array2, n, b2, n3, n4, b, b4, n2, 1, false);
                        Class115.method2155();
                        Class358.aBoolean3033 = false;
                        if (~Class98_Sub46.anInt4261 == -1) {
                            Class154.aHa1231.method1776();
                        }
                        if (~Class98_Sub46.anInt4261 == 0xFFFFFFFE) {
                            Class147.method2411(0);
                        }
                    }
                    if (~Class98_Sub46.anInt4261 == -1) {
                        Class31.anInterface17_301.method57(Class98_Sub46_Sub13_Sub2.anInt6309, Class272.anInt2037, Class151_Sub7.anInt5005, Class149.anInt1208, 0, 0, true, true);
                    }
                    ++Class230.anInt1732;
                    Class41.method367(Class98_Sub39.aDouble4188, 14794);
                    Class263.aDouble1966 = Class98_Sub39.aDouble4188;
                    if (Class98_Sub46.anInt4261 == 0 || Class98_Sub46.anInt4261 == 2) {
                        if (Class98_Sub46.anInt4261 == 2) {
                            Class154.aHa1231.GA(Class49.anInt415);
                            Class154.aHa1231.ya();
                        }
                        Class98_Sub10_Sub1.anInt5543 = Class111_Sub3.anInt4701 + (anInt894 - Class38.anInt359) + -Class272.anInt2037;
                        Class224_Sub2_Sub1.anInt6143 = anInt893 + (Class377.anInt3183 - (Class151_Sub5.anInt4993 + Class98_Sub46_Sub13_Sub2.anInt6309));
                        Class370.anInt3140 = n8;
                        Class246_Sub3_Sub4_Sub2_Sub1.anInt6509 = n9;
                        Class154.aHa1231.DA(Class224_Sub2_Sub1.anInt6143, Class98_Sub10_Sub1.anInt5543, Class370.anInt3140, Class246_Sub3_Sub4_Sub2_Sub1.anInt6509);
                    }
                    else if (~Class98_Sub46.anInt4261 == 0xFFFFFFFE) {
                        Class224_Sub2_Sub1.anInt6143 = -Class151_Sub5.anInt4993 + anInt893;
                        Class98_Sub10_Sub1.anInt5543 = anInt894 - Class38.anInt359;
                        Class246_Sub3_Sub4_Sub2_Sub1.anInt6509 = n9;
                        Class370.anInt3140 = n8;
                        Class154.aHa1231.DA(Class224_Sub2_Sub1.anInt6143, Class98_Sub10_Sub1.anInt5543, Class370.anInt3140, Class246_Sub3_Sub4_Sub2_Sub1.anInt6509);
                        Class154.aHa1231.KA(Class98_Sub46_Sub13_Sub2.anInt6309, Class272.anInt2037, Class98_Sub46_Sub13_Sub2.anInt6309 - -Class151_Sub7.anInt5005, Class272.anInt2037 + Class149.anInt1208);
                    }
                    Class60.method535(n5, anInt891, anInt890, anInt892, array, array3, array6, array4, array5, array2, n, b2, n3, n4, b, b4, n2, (~Class98_Sub46.anInt4261 == 0xFFFFFFFD) ? 0 : 2, ~Class98_Sub46.anInt4261 == 0xFFFFFFFE);
                    Class154.aHa1231.la();
                    Class154.aHa1231.DA(anInt893, anInt894, n6, n7);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kfa.A(" + anInt890 + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + anInt891 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + b + ',' + ((array3 != null) ? "{...}" : "null") + ',' + b2 + ',' + b3 + ',' + n3 + ',' + n4 + ',' + ((array4 != null) ? "{...}" : "null") + ',' + n5 + ',' + ((array5 != null) ? "{...}" : "null") + ',' + anInt892 + ',' + ((array6 != null) ? "{...}" : "null") + ',' + b4 + ')');
        }
    }
}
