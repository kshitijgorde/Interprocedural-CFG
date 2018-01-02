// 
// Decompiled by Procyon v0.5.30
// 

final class Class202
{
    private ha_Sub1 aHa_Sub1_1546;
    static Class110 aClass110_1547;
    static boolean aBoolean1548;
    int anInt1549;
    static Class308 aClass308_1550;
    
    static final void method2698(final byte b, int n, final int n2, int n3, final int n4) {
        try {
            final float n5 = Class278.anInt2084 / Class278.anInt2089;
            int n6 = n2;
            if (b < 47) {
                method2700(43, null, true, -10, null);
            }
            int n7 = n4;
            Label_0068: {
                if (n5 >= 1.0f) {
                    n6 = (int)(n4 / n5);
                    if (!client.aBoolean3553) {
                        break Label_0068;
                    }
                }
                n7 = (int)(n2 * n5);
            }
            n -= (n4 + -n7) / 2;
            n3 -= (-n6 + n2) / 2;
            Class98_Sub40.anInt4197 = Class278.anInt2084 + -(n * Class278.anInt2084 / n7);
            Class169.anInt1307 = -1;
            Class101.anInt849 = -1;
            Class42_Sub4.anInt5371 = n3 * Class278.anInt2089 / n6;
            aa_Sub1.method155(-1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.A(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub1_1546.method1872(this.anInt1549, 2834);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.finalize()");
        }
    }
    
    public static void method2699(final byte b) {
        try {
            if (b == 44) {
                Class202.aClass110_1547 = null;
                Class202.aClass308_1550 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.B(" + b + ')');
        }
    }
    
    static final int method2700(final int n, final Class53_Sub1 class53_Sub1, final boolean b, final int n2, final Class53_Sub1 class53_Sub2) {
        try {
            if (~n == 0xFFFFFFFE) {
                int anInt429 = class53_Sub1.anInt429;
                int anInt430 = class53_Sub2.anInt429;
                if (!b) {
                    if (~anInt430 == 0x0) {
                        anInt430 = 2001;
                    }
                    if (~anInt429 == 0x0) {
                        anInt429 = 2001;
                    }
                }
                return anInt429 - anInt430;
            }
            if (~n == 0xFFFFFFFD) {
                return Class336.method3772(class53_Sub1.method501(n2).aString957, class53_Sub2.method501(-1).aString957, Class374.anInt3159, 1166845806);
            }
            if (~n == 0xFFFFFFFC) {
                if (!class53_Sub1.aString3630.equals("-")) {
                    if (!class53_Sub2.aString3630.equals("-")) {
                        return Class336.method3772(class53_Sub1.aString3630, class53_Sub2.aString3630, Class374.anInt3159, 1166845806);
                    }
                    if (b) {
                        return 1;
                    }
                    return -1;
                }
                else {
                    if (class53_Sub2.aString3630.equals("-")) {
                        return 0;
                    }
                    if (b) {
                        return -1;
                    }
                    return 1;
                }
            }
            else if (~n == 0xFFFFFFFB) {
                if (!class53_Sub1.method495((byte)122)) {
                    if (class53_Sub2.method495((byte)127)) {
                        return -1;
                    }
                    return 0;
                }
                else {
                    if (!class53_Sub2.method495((byte)122)) {
                        return 1;
                    }
                    return 0;
                }
            }
            else if (n == 5) {
                if (class53_Sub1.method493(9811)) {
                    if (class53_Sub2.method493(9811)) {
                        return 0;
                    }
                    return 1;
                }
                else {
                    if (class53_Sub2.method493(9811)) {
                        return -1;
                    }
                    return 0;
                }
            }
            else if (~n == 0xFFFFFFF9) {
                if (class53_Sub1.method496((byte)72)) {
                    if (!class53_Sub2.method496((byte)90)) {
                        return 1;
                    }
                    return 0;
                }
                else {
                    if (class53_Sub2.method496((byte)60)) {
                        return -1;
                    }
                    return 0;
                }
            }
            else {
                if (n2 != -1) {
                    method2700(-89, null, true, -108, null);
                }
                if (~n == 0xFFFFFFF8) {
                    if (!class53_Sub1.method497(false)) {
                        if (class53_Sub2.method497(false)) {
                            return -1;
                        }
                        return 0;
                    }
                    else {
                        if (class53_Sub2.method497(false)) {
                            return 0;
                        }
                        return 1;
                    }
                }
                else {
                    if (~n == 0xFFFFFFF7) {
                        int anInt431 = class53_Sub1.anInt3631;
                        int anInt432 = class53_Sub2.anInt3631;
                        if (!b) {
                            if (anInt431 == -1) {
                                anInt431 = 1000;
                            }
                            if (anInt432 != -1) {
                                return -anInt432 + anInt431;
                            }
                            anInt432 = 1000;
                            if (!client.aBoolean3553) {
                                return -anInt432 + anInt431;
                            }
                        }
                        if (~anInt432 == 0xFFFFFC17) {
                            anInt432 = -1;
                        }
                        if (anInt431 == 1000) {
                            anInt431 = -1;
                        }
                        return -anInt432 + anInt431;
                    }
                    return class53_Sub1.anInt3632 - class53_Sub2.anInt3632;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.F(" + n + ',' + ((class53_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ',' + ((class53_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2701(int n, final int n2, final ha ha, int n3, final byte b, final int n4) {
        try {
            ha.KA(n, n3, n + n2, n4 + n3);
            ha.method1760(n2, n4, n3, -16777216, (byte)(-66), n);
            if (~Class212.anInt1600 <= -101) {
                final float n5 = Class278.anInt2084 / Class278.anInt2089;
                int n6 = n2;
                int n7 = n4;
                if (n5 >= 1.0f) {
                    n6 = (int)(n4 / n5);
                }
                else {
                    n7 = (int)(n2 * n5);
                }
                n += (n2 - n6) / 2;
                n3 += (-n7 + n4) / 2;
                if (Class152.aClass332_1221 == null || Class152.aClass332_1221.method3734() != n2 || n4 != Class152.aClass332_1221.method3731()) {
                    Class278.method3308(Class278.anInt2075, Class278.anInt2078 + Class278.anInt2084, Class278.anInt2089 + Class278.anInt2075, Class278.anInt2078, n, n3, n - -n6, n7 + n3);
                    Class278.method3309(ha);
                    Class152.aClass332_1221 = ha.method1797(n, n3, n6, n7, false);
                }
                if (b == -90) {
                    Class152.aClass332_1221.method3735(n, n3);
                    final int n8 = n6 * aa.anInt48 / Class278.anInt2089;
                    final int n9 = Class246_Sub3_Sub5_Sub2.anInt6268 * n7 / Class278.anInt2084;
                    final int n10 = Class166.anInt1279 * n6 / Class278.anInt2089 + n;
                    final int n11 = -n9 + -(Class231.anInt1739 * n7 / Class278.anInt2084) + (n7 + n3);
                    int n12 = -1996554240;
                    if (Class4.aClass279_86 == Class64_Sub2.aClass279_3643) {
                        n12 = -1996488705;
                    }
                    ha.aa(n10, n11, n8, n9, n12, 1);
                    ha.method1779(n10, n11, n8, n9, n12, 0);
                    if (Class64_Sub25.anInt3711 > 0) {
                        int n13;
                        if (Class287.anInt2186 <= 50) {
                            n13 = 5 * Class287.anInt2186;
                        }
                        else {
                            n13 = -(Class287.anInt2186 * 5) + 500;
                        }
                        for (Class98_Sub47 class98_Sub47 = (Class98_Sub47)Class278.aClass148_2065.method2418(32); class98_Sub47 != null; class98_Sub47 = (Class98_Sub47)Class278.aClass148_2065.method2417(105)) {
                            final Class24 method3807 = Class278.aClass341_2057.method3807(117, class98_Sub47.anInt4268);
                            if (Class87.method855(b + 215, method3807)) {
                                if (Class98_Sub5_Sub2.anInt5536 == class98_Sub47.anInt4268) {
                                    ha.method1760(4, 4, -2 + (n3 - -((-class98_Sub47.anInt4267 + Class278.anInt2084) * n7 / Class278.anInt2084)), n13 << 2063784920 | 0xFFFF00, (byte)(-66), n + n6 * class98_Sub47.anInt4272 / Class278.anInt2089 - 2);
                                }
                                else if (~Class256.anInt1945 != 0x0 && ~Class256.anInt1945 == ~method3807.anInt246) {
                                    ha.method1760(4, 4, -2 + (n7 * (Class278.anInt2084 + -class98_Sub47.anInt4267) / Class278.anInt2084 + n3), 0xFFFF00 | n13 << 1169738168, (byte)(-66), -2 + (n6 * class98_Sub47.anInt4272 / Class278.anInt2089 + n));
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.E(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n3 + ',' + b + ',' + n4 + ')');
        }
    }
    
    static int method2702(final int n, final int n2) {
        try {
            return n & n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method2703(final boolean b) {
        try {
            if (Class88.aString696.toLowerCase().indexOf("microsoft") != -1) {
                Class87.anIntArray667[192] = 58;
                Class87.anIntArray667[191] = 73;
                Class87.anIntArray667[220] = 74;
                Class87.anIntArray667[221] = 43;
                Class87.anIntArray667[188] = 71;
                Class87.anIntArray667[190] = 72;
                Class87.anIntArray667[189] = 26;
                Class87.anIntArray667[223] = 28;
                Class87.anIntArray667[187] = 27;
                Class87.anIntArray667[219] = 42;
                Class87.anIntArray667[222] = 59;
                Class87.anIntArray667[186] = 57;
            }
            else {
                Class87.anIntArray667[45] = 26;
                Class87.anIntArray667[92] = 74;
                Class87.anIntArray667[46] = 72;
                Class87.anIntArray667[59] = 57;
                Class87.anIntArray667[47] = 73;
                Class87.anIntArray667[91] = 42;
                if (Class88.aMethod700 != null) {
                    Class87.anIntArray667[222] = 58;
                    Class87.anIntArray667[520] = 59;
                    Class87.anIntArray667[192] = 28;
                }
                else {
                    Class87.anIntArray667[222] = 59;
                    Class87.anIntArray667[192] = 58;
                }
                Class87.anIntArray667[61] = 27;
                Class87.anIntArray667[93] = 43;
                Class87.anIntArray667[44] = 71;
            }
            if (b) {
                method2700(-70, null, false, 0, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.D(" + b + ')');
        }
    }
    
    Class202(final ha_Sub1 aHa_Sub1_1546, final int n, final int anInt1549) {
        try {
            this.aHa_Sub1_1546 = aHa_Sub1_1546;
            this.anInt1549 = anInt1549;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ne.<init>(" + ((aHa_Sub1_1546 != null) ? "{...}" : "null") + ',' + n + ',' + anInt1549 + ')');
        }
    }
    
    static {
        Class202.aBoolean1548 = false;
    }
}
