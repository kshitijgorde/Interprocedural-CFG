// 
// Decompiled by Procyon v0.5.30
// 

class Class98_Sub10_Sub5 extends Class98_Sub10
{
    int[] anIntArray5552;
    private int anInt5553;
    static int anInt5554;
    int anInt5555;
    int anInt5556;
    static Class aClass5557;
    
    public Class98_Sub10_Sub5() {
        super(0, false);
        this.anInt5553 = -1;
    }
    
    static final float method1014(final float n, final byte b, final float n2, final float n3) {
        try {
            return n + (n2 - n) * n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.F(" + n + ',' + b + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683 && this.method1016(-1)) {
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                int n3 = this.anInt5556 * ((Class63.anInt492 != this.anInt5555) ? (n2 * this.anInt5555 / Class63.anInt492) : n2);
                if (~this.anInt5556 == ~Class25.anInt268) {
                    for (int i = 0; i < Class25.anInt268; ++i) {
                        final int n4 = this.anIntArray5552[n3++];
                        array3[i] = Class202.method2702(4080, n4 << -1987589340);
                        array2[i] = Class202.method2702(4080, n4 >> 1948667748);
                        array[i] = Class202.method2702(4080, n4 >> 1591355276);
                    }
                }
                else {
                    for (int n5 = 0; ~n5 > ~Class25.anInt268; ++n5) {
                        final int n6 = this.anIntArray5552[n3 + n5 * this.anInt5556 / Class25.anInt268];
                        array3[n5] = Class202.method2702(4080, n6 << -1398173052);
                        array2[n5] = Class202.method2702(n6, 65280) >> -678736348;
                        array[n5] = Class202.method2702(4080, n6 >> -415498644);
                    }
                }
            }
            if (n >= -76) {
                this.anInt5555 = -123;
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method996(final byte b) {
        try {
            if (b > -119) {
                Class98_Sub10_Sub5.anInt5554 = 39;
            }
            return this.anInt5553;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.M(" + b + ')');
        }
    }
    
    @Override
    final void method993(final int n) {
        try {
            super.method993((short)n);
            this.anIntArray5552 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.P(" + n + ')');
        }
    }
    
    static final boolean method1015(final ha ha, final int n, final int n2) {
        try {
            final int n3 = (Class165.anInt1276 - 104) / 2;
            final int n4 = (-104 + Class98_Sub10_Sub7.anInt5572) / 2;
            boolean b = true;
            for (int n5 = n3; ~n5 > ~(104 + n3); ++n5) {
                for (int n6 = n4; ~(n4 + 104) < ~n6; ++n6) {
                    for (int i = n; i <= 3; ++i) {
                        if (Class294.method3477(n6, i, n5, n, n2 ^ 0x22BE)) {
                            int n7 = i;
                            if (Class1.method162(n6, (byte)(-92), n5)) {
                                --n7;
                            }
                            if (~n7 <= -1) {
                                b &= Class65.method679(n5, n7, (byte)112, n6);
                            }
                        }
                    }
                }
            }
            if (!b) {
                return false;
            }
            final int[] array = new int[262144];
            for (int n8 = 0; ~array.length < ~n8; ++n8) {
                array[n8] = -16777216;
            }
            Class64_Sub9.aClass332_3663 = ha.method1748(-7962, 0, 512, 512, array, 512);
            Class98_Sub35.method1452(0);
            final int n9 = 0xFF000000 | 238 + (int)(20.0 * Math.random()) - (10 - ((int)(Math.random() * 20.0) + 238 - 10 << -599914896)) + (238 + (int)(20.0 * Math.random()) - 10 << 975075624);
            final int n10 = 0xFF000000 | -10 + ((int)(Math.random() * 20.0) + 238) << 2084461680;
            final int n11 = (int)(Math.random() * 8.0) | ((int)(Math.random() * 8.0) << -36704600 | (int)(8.0 * Math.random()) << -1428728144);
            final boolean[][] array2 = new boolean[1 + Class55.anInt443 + 2][1 + (Class55.anInt443 + 2)];
            for (int n12 = n3; ~(104 + n3) < ~n12; n12 += Class55.anInt443) {
                for (int j = n4; j < 104 + n4; j += Class55.anInt443) {
                    int n13 = 0;
                    int n14 = 0;
                    int n15 = n12;
                    if (n15 > 0) {
                        n13 += 4;
                        --n15;
                    }
                    int n16 = j;
                    if (n16 > 0) {
                        --n16;
                    }
                    int n17 = Class55.anInt443 + n12;
                    if (~n17 > -105) {
                        ++n17;
                    }
                    int n18 = Class55.anInt443 + j;
                    if (n18 < 104) {
                        n14 += 4;
                        ++n18;
                    }
                    ha.KA(0, 0, Class55.anInt443 * 4 + n13, n14 + 4 * Class55.anInt443);
                    ha.GA(-16777216);
                    for (int n19 = n; ~n19 >= -4; ++n19) {
                        for (int n20 = 0; Class55.anInt443 >= n20; ++n20) {
                            for (int n21 = 0; ~Class55.anInt443 <= ~n21; ++n21) {
                                array2[n20][n21] = Class294.method3477(n16 - -n21, n19, n15 - -n20, n, 107);
                            }
                        }
                        Class98_Sub46_Sub2_Sub2.aSArray6298[n19].method3422(0, 0, 1024, n15, n16, n17, n18, array2);
                        if (!Class44.aBoolean378) {
                            for (int n22 = -4; ~Class55.anInt443 < ~n22; ++n22) {
                                for (int n23 = -4; ~n23 > ~Class55.anInt443; ++n23) {
                                    final int n24 = n12 + n22;
                                    final int n25 = n23 + j;
                                    if (n3 <= n24 && n4 <= n25 && Class294.method3477(n25, n19, n24, n, 56)) {
                                        int n26 = n19;
                                        if (Class1.method162(n25, (byte)(-101), n24)) {
                                            --n26;
                                        }
                                        if (n26 >= 0) {
                                            Class277.method3289(n26, n13 + 4 * n22, n25, n10, ha, -4 + n14 + 4 * (Class55.anInt443 - n23), true, n24, n9);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (Class44.aBoolean378) {
                        final Class243 class243 = Class167.aClass243Array1281[n];
                        for (int n27 = 0; ~n27 > ~Class55.anInt443; ++n27) {
                            for (int n28 = 0; Class55.anInt443 > n28; ++n28) {
                                final int n29 = class243.anIntArrayArray1853[-class243.anInt1854 + (n12 - -n27)][j - -n28 + -class243.anInt1855];
                                if ((0x40240000 & n29) == 0x0) {
                                    if (~(0x800000 & n29) != -1) {
                                        ha.method1753(22294, 4, -1713569622, 4 * (Class55.anInt443 - n28) + (n14 - 4), 4 * n27 + n13);
                                    }
                                    else if ((0x2000000 & n29) == 0x0) {
                                        if ((n29 & 0x8000000) != 0x0) {
                                            ha.method1753(22294, 4, -1713569622, -4 + (4 * (-n28 + Class55.anInt443) + n14) + 3, 4 * n27 + n13);
                                        }
                                        else if (~(n29 & 0x20000000) != -1) {
                                            ha.method1755(n2 - 460, -4 + (4 * (-n28 + Class55.anInt443) + n14), n27 * 4 + n13, -1713569622, 4);
                                        }
                                    }
                                    else {
                                        ha.method1755(8479, n14 - (-((-n28 + Class55.anInt443) * 4) + 4), 3 + n27 * 4 + n13, -1713569622, 4);
                                    }
                                }
                                else {
                                    ha.method1760(4, 4, -4 + (4 * (-n28 + Class55.anInt443) + n14), -1713569622, (byte)(-66), n27 * 4 + n13);
                                }
                            }
                        }
                    }
                    ha.aa(n13, n14, 4 * Class55.anInt443, 4 * Class55.anInt443, n11, 2);
                    Class64_Sub9.aClass332_3663.method3736(48 + 4 * (-n3 + n12), -(4 * Class55.anInt443) + (464 - (j + -n4) * 4), Class55.anInt443 * 4, Class55.anInt443 * 4, n13, n14);
                }
            }
            ha.la();
            ha.GA(-16777215);
            if (n2 != 8939) {
                method1015(null, 43, 57);
            }
            Class98_Sub43.method1481(2);
            Class98_Sub10_Sub7.anInt5577 = 0;
            Class64_Sub8.aClass148_3659.method2422((byte)47);
            if (!Class44.aBoolean378) {
                for (int k = n3; k < 104 + n3; ++k) {
                    for (int n30 = n4; ~n30 > ~(n4 + 104); ++n30) {
                        for (int n31 = n; ~(1 + n) <= ~n31 && ~n31 >= -4; ++n31) {
                            if (Class294.method3477(n30, n31, k, n, n2 - 9060)) {
                                Interface19 interface19 = (Interface19)Class253.method3177(n31, k, n30);
                                if (interface19 == null) {
                                    interface19 = (Interface19)Class97.method931(n31, k, n30, (Class98_Sub10_Sub5.aClass5557 != null) ? Class98_Sub10_Sub5.aClass5557 : (Class98_Sub10_Sub5.aClass5557 = method1017("Interface19")));
                                }
                                if (interface19 == null) {
                                    interface19 = (Interface19)Class21_Sub1.method268(n31, k, n30);
                                }
                                if (interface19 == null) {
                                    interface19 = (Interface19)Class101.method1701(n31, k, n30);
                                }
                                if (interface19 != null) {
                                    final Class352 method3546 = Class130.aClass302_1028.method3546(interface19.method64(30472), (byte)119);
                                    if (!method3546.aBoolean2927 || Class79.aBoolean602) {
                                        int n32 = method3546.anInt2958;
                                        if (method3546.anIntArray2928 != null) {
                                            for (int n33 = 0; ~n33 > ~method3546.anIntArray2928.length; ++n33) {
                                                if (method3546.anIntArray2928[n33] != -1) {
                                                    final Class352 method3547 = Class130.aClass302_1028.method3546(method3546.anIntArray2928[n33], (byte)119);
                                                    if (~method3547.anInt2958 <= -1) {
                                                        n32 = method3547.anInt2958;
                                                    }
                                                }
                                            }
                                        }
                                        if (~n32 <= -1) {
                                            boolean b2 = false;
                                            if (~n32 <= -1) {
                                                final Class24 method3548 = Class216.aClass341_1622.method3807(-70, n32);
                                                if (method3548 != null && method3548.aBoolean261) {
                                                    b2 = true;
                                                }
                                            }
                                            int n34 = k;
                                            int n35 = n30;
                                            if (b2) {
                                                final int[][] anIntArrayArray1853 = Class167.aClass243Array1281[n31].anIntArrayArray1853;
                                                final int anInt1854 = Class167.aClass243Array1281[n31].anInt1854;
                                                final int anInt1855 = Class167.aClass243Array1281[n31].anInt1855;
                                                for (int l = 0; l < 10; ++l) {
                                                    final int n36 = (int)(Math.random() * 4.0);
                                                    if (n36 == 0 && ~n3 > ~n34 && ~(-3 + k) > ~n34 && ~(anIntArrayArray1853[-anInt1854 + (-1 + n34)][-anInt1855 + n35] & 0x2C0108) == -1) {
                                                        --n34;
                                                    }
                                                    if (~n36 == 0xFFFFFFFE && ~n34 > ~(-1 + (104 + n3)) && n34 < 3 + k && (0x2C0180 & anIntArrayArray1853[-anInt1854 + 1 + n34][n35 - anInt1855]) == 0x0) {
                                                        ++n34;
                                                    }
                                                    if (n36 == 2 && ~n35 < ~n4 && n35 > n30 - 3 && ~(anIntArrayArray1853[n34 - anInt1854][-anInt1855 - 1 + n35] & 0x2C0102) == -1) {
                                                        --n35;
                                                    }
                                                    if (~n36 == 0xFFFFFFFC && ~n35 > ~(103 + n4) && ~n35 > ~(n30 + 3) && ~(anIntArrayArray1853[-anInt1854 + n34][1 + (n35 - anInt1855)] & 0x2C0120) == -1) {
                                                        ++n35;
                                                    }
                                                }
                                            }
                                            Class79.anIntArray603[Class98_Sub10_Sub7.anInt5577] = method3546.id;
                                            Class19.anIntArray3451[Class98_Sub10_Sub7.anInt5577] = n34;
                                            Class6.anIntArray91[Class98_Sub10_Sub7.anInt5577] = n35;
                                            ++Class98_Sub10_Sub7.anInt5577;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (Class64_Sub23.aClass370_3707 != null) {
                    client.aClass207_3549.anInt1575 = 1;
                    Class216.aClass341_1622.method3809(64, -30502, 1024);
                    for (int n37 = 0; ~Class64_Sub23.aClass370_3707.anInt3137 < ~n37; ++n37) {
                        final int n38 = Class64_Sub23.aClass370_3707.anIntArray3133[n37];
                        if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 == ~(n38 >> 2135781756)) {
                            final int n39 = -Class272.anInt2038 + (n38 >> 32300910 & 0x3FFF);
                            final int n40 = -aa_Sub2.anInt3562 + (n38 & 0x3FFF);
                            if (~n39 <= -1 && ~Class165.anInt1276 < ~n39 && ~n40 <= -1 && ~Class98_Sub10_Sub7.anInt5572 < ~n40) {
                                Class64_Sub8.aClass148_3659.method2419(new Class98_Sub34(n37), -20911);
                            }
                            else {
                                final Class24 method3549 = Class216.aClass341_1622.method3807(-25, Class64_Sub23.aClass370_3707.anIntArray3138[n37]);
                                if (method3549.anIntArray265 != null && method3549.anInt247 + n39 >= 0 && Class165.anInt1276 > n39 + method3549.anInt244 && n40 + method3549.anInt262 >= 0 && ~Class98_Sub10_Sub7.anInt5572 < ~(method3549.anInt248 + n40)) {
                                    Class64_Sub8.aClass148_3659.method2419(new Class98_Sub34(n37), -20911);
                                }
                            }
                        }
                    }
                    Class216.aClass341_1622.method3809(64, -30502, 128);
                    client.aClass207_3549.anInt1575 = 2;
                    client.aClass207_3549.method2760((byte)(-120));
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.E(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n == 0) {
                this.anInt5553 = class98_Sub22.readShort((byte)127);
            }
            if (b >= -92) {
                this.anInt5553 = -74;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final boolean method1016(final int n) {
        try {
            if (this.anIntArray5552 != null) {
                return true;
            }
            if (this.anInt5553 >= 0) {
                final Class324 class324 = (~Class98_Sub10_Sub26.anInt5683 <= -1) ? Class324.method3685(Class127.aClass207_1019, Class98_Sub10_Sub26.anInt5683, this.anInt5553) : Class324.method3683(Class127.aClass207_1019, this.anInt5553);
                class324.method3692();
                this.anIntArray5552 = class324.method3686();
                this.anInt5555 = class324.anInt2720;
                this.anInt5556 = class324.anInt2722;
                return true;
            }
            if (n != -1) {
                this.method1016(22);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cp.D(" + n + ')');
        }
    }
    
    static Class method1017(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class98_Sub10_Sub5.anInt5554 = 0;
    }
}
