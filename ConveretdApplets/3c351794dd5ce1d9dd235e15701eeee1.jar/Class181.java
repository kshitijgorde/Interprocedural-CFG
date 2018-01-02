// 
// Decompiled by Procyon v0.5.30
// 

final class Class181
{
    int anInt1425;
    int anInt1426;
    int anInt1427;
    int anInt1428;
    int anInt1429;
    static Class148 aClass148_1430;
    int anInt1431;
    static int anInt1432;
    
    public static void method2606(final int n) {
        try {
            if (n != -16841) {
                Class181.aClass148_1430 = null;
            }
            Class181.aClass148_1430 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.A(" + n + ')');
        }
    }
    
    static final void method2607(final byte b) {
        try {
            Class257.method3201((byte)65, false);
            Class142.anInt1160 = 0;
            boolean b2 = true;
            for (int n = 0; ~client.aByteArrayArray3551.length < ~n; ++n) {
                if (~Class287.anIntArray2188[n] != 0x0 && client.aByteArrayArray3551[n] == null) {
                    client.aByteArrayArray3551[n] = Class234.aClass207_1748.method2745(0, Class287.anIntArray2188[n], false);
                    if (client.aByteArrayArray3551[n] == null) {
                        ++Class142.anInt1160;
                        b2 = false;
                    }
                }
                if (~Class98_Sub36.anIntArray4162[n] != 0x0 && Class255.aByteArrayArray3211[n] == null) {
                    Class255.aByteArrayArray3211[n] = Class234.aClass207_1748.method2736(Class98_Sub46_Sub17.anIntArrayArray6049[n], 5, 0, Class98_Sub36.anIntArray4162[n]);
                    if (Class255.aByteArrayArray3211[n] == null) {
                        b2 = false;
                        ++Class142.anInt1160;
                    }
                }
                if (~Class302.anIntArray2517[n] != 0x0 && Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533[n] == null) {
                    Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533[n] = Class234.aClass207_1748.method2745(0, Class302.anIntArray2517[n], false);
                    if (Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533[n] == null) {
                        ++Class142.anInt1160;
                        b2 = false;
                    }
                }
                if (~Class377.anIntArray3178[n] != 0x0 && Class377.aByteArrayArray3182[n] == null) {
                    Class377.aByteArrayArray3182[n] = Class234.aClass207_1748.method2745(0, Class377.anIntArray3178[n], false);
                    if (Class377.aByteArrayArray3182[n] == null) {
                        ++Class142.anInt1160;
                        b2 = false;
                    }
                }
                if (Class76_Sub7.anIntArray3765 != null && Class105.aByteArrayArray3414[n] == null && Class76_Sub7.anIntArray3765[n] != -1) {
                    Class105.aByteArrayArray3414[n] = Class234.aClass207_1748.method2736(Class98_Sub46_Sub17.anIntArrayArray6049[n], 5, 0, Class76_Sub7.anIntArray3765[n]);
                    if (Class105.aByteArrayArray3414[n] == null) {
                        ++Class142.anInt1160;
                        b2 = false;
                    }
                }
            }
            if (Class64_Sub23.aClass370_3707 == null) {
                if (Class98_Sub40.aClass98_Sub46_Sub10_4195 != null && Class257.aClass207_1947.method2728(Class98_Sub40.aClass98_Sub46_Sub10_4195.aString6017 + "_staticelements", 0)) {
                    if (Class257.aClass207_1947.method2741(Class98_Sub40.aClass98_Sub46_Sub10_4195.aString6017 + "_staticelements", 0)) {
                        Class64_Sub23.aClass370_3707 = Class52.method491(113, Class79.aBoolean602, Class257.aClass207_1947, Class98_Sub40.aClass98_Sub46_Sub10_4195.aString6017 + "_staticelements");
                    }
                    else {
                        ++Class142.anInt1160;
                        b2 = false;
                    }
                }
                else {
                    Class64_Sub23.aClass370_3707 = new Class370(0);
                }
            }
            if (!b2) {
                Class130.anInt1031 = 1;
            }
            else {
                boolean b3 = true;
                Class132.anInt1043 = 0;
                for (int n2 = 0; ~client.aByteArrayArray3551.length < ~n2; ++n2) {
                    final byte[] array = Class255.aByteArrayArray3211[n2];
                    if (array != null) {
                        int n3 = -Class272.anInt2038 + (Class121.anIntArray1006[n2] >> 1965275688) * 64;
                        int n4 = (0xFF & Class121.anIntArray1006[n2]) * 64 - aa_Sub2.anInt3562;
                        if (Class151_Sub9.anInt5028 != 0) {
                            n4 = 10;
                            n3 = 10;
                        }
                        b3 &= Class123.method2205(n4, array, n3, Class165.anInt1276, Class98_Sub10_Sub7.anInt5572, 107);
                    }
                    final byte[] array2 = Class377.aByteArrayArray3182[n2];
                    if (array2 != null) {
                        int n5 = 64 * (Class121.anIntArray1006[n2] >> -389792216) - Class272.anInt2038;
                        int n6 = 64 * (Class121.anIntArray1006[n2] & 0xFF) + -aa_Sub2.anInt3562;
                        if (Class151_Sub9.anInt5028 != 0) {
                            n5 = 10;
                            n6 = 10;
                        }
                        b3 &= Class123.method2205(n6, array2, n5, Class165.anInt1276, Class98_Sub10_Sub7.anInt5572, 119);
                    }
                }
                if (!b3) {
                    Class130.anInt1031 = 2;
                }
                else {
                    if (Class130.anInt1031 != 0) {
                        Class246_Sub2.method2972(-77, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, true, Class265.aHa1974, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25) + "<br>(100%)");
                    }
                    Class128.method2224(22696);
                    Class98_Sub10_Sub15.method1050((byte)101);
                    Class301.method3542(50);
                    boolean b4 = false;
                    if (Class265.aHa1974.method1747() && Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064.method668((byte)124) == 2) {
                        for (int n7 = 0; client.aByteArrayArray3551.length > n7; ++n7) {
                            if (Class377.aByteArrayArray3182[n7] != null || Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533[n7] != null) {
                                b4 = true;
                                break;
                            }
                        }
                    }
                    int n8;
                    if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049.method609((byte)125) == 0xFFFFFFFE) {
                        n8 = Class262.anIntArray1961[Class98_Sub22.anInt3994];
                    }
                    else {
                        n8 = Class303.anIntArray2531[Class98_Sub22.anInt3994];
                    }
                    if (Class265.aHa1974.method1788()) {
                        ++n8;
                    }
                    Class117.method2164(Class265.aHa1974, Class337_Sub1.anInt5499, 9, 4, Class165.anInt1276, Class98_Sub10_Sub7.anInt5572, n8, b4, Class265.aHa1974.method1822() > 0);
                    Class83.method825(Class98_Sub10_Sub14.anInt5614);
                    if (Class98_Sub10_Sub14.anInt5614 == 0) {
                        Class207.method2746(null);
                    }
                    else {
                        Class207.method2746(Class69_Sub2.aClass43_5336);
                    }
                    for (int n9 = 0; ~n9 > -5; ++n9) {
                        Class167.aClass243Array1281[n9].method2950((byte)(-99));
                    }
                    Class238.method2920(-125);
                    Class309.method3614(false, -119);
                    Class369.method3954(0);
                    Class232.aBoolean1744 = false;
                    s_Sub1.aClass346_5202 = null;
                    Class128.method2224(22696);
                    System.gc();
                    Class257.method3201((byte)56, true);
                    Class113.method2145((byte)(-46));
                    Class61.anInt479 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)124);
                    Class202.aBoolean1548 = (Class292.anInt3359 >= 96);
                    Class98_Sub46_Sub9.aBoolean6002 = (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064.method668((byte)121) == 0xFFFFFFFD);
                    Class97.aBoolean830 = (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070.method592((byte)121) == 0xFFFFFFFE);
                    Class113.anInt950 = ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)120) != 1) ? Class115.anInt963 : -1);
                    Class319.aBoolean2707 = (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039.method655((byte)125) == 1);
                    Class369.aBoolean3130 = (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method634((byte)122) == 0xFFFFFFFE);
                    Class146_Sub3.aClass305_Sub1_4952 = new Class305_Sub1(4, Class165.anInt1276, Class98_Sub10_Sub7.anInt5572, false);
                    if (Class151_Sub9.anInt5028 != 0) {
                        Class93.method900(11948, client.aByteArrayArray3551, Class146_Sub3.aClass305_Sub1_4952);
                    }
                    else {
                        Class42.method374(Class146_Sub3.aClass305_Sub1_4952, 0, client.aByteArrayArray3551);
                    }
                    Class246_Sub3_Sub4_Sub2_Sub2.method3065(Class165.anInt1276 >> -1731069852, Class98_Sub10_Sub7.anInt5572 >> -1481341692, true);
                    Class329.method3708(-1);
                    if (b4) {
                        Class248.method3158(true);
                        Class98_Sub31_Sub1.aClass305_Sub1_5816 = new Class305_Sub1(1, Class165.anInt1276, Class98_Sub10_Sub7.anInt5572, true);
                        if (~Class151_Sub9.anInt5028 != -1) {
                            Class93.method900(11948, Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533, Class98_Sub31_Sub1.aClass305_Sub1_5816);
                            Class257.method3201((byte)123, true);
                        }
                        else {
                            Class42.method374(Class98_Sub31_Sub1.aClass305_Sub1_5816, 0, Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533);
                            Class257.method3201((byte)113, true);
                        }
                        Class98_Sub31_Sub1.aClass305_Sub1_5816.method3577(0, -57, Class146_Sub3.aClass305_Sub1_4952.anIntArrayArrayArray2549[0]);
                        Class98_Sub31_Sub1.aClass305_Sub1_5816.method3579(0, null, Class265.aHa1974, null);
                        Class248.method3158(false);
                    }
                    Class146_Sub3.aClass305_Sub1_4952.method3579(0, Class167.aClass243Array1281, Class265.aHa1974, b4 ? Class98_Sub31_Sub1.aClass305_Sub1_5816.anIntArrayArrayArray2549 : ((int[][][])null));
                    if (~Class151_Sub9.anInt5028 == -1) {
                        Class257.method3201((byte)50, true);
                        Class92.method898(false, Class255.aByteArrayArray3211, Class146_Sub3.aClass305_Sub1_4952);
                        if (Class105.aByteArrayArray3414 != null) {
                            Class246_Sub3_Sub2.method3005(21378);
                        }
                    }
                    else {
                        Class257.method3201((byte)67, true);
                        Class48_Sub2.method470(Class255.aByteArrayArray3211, Class146_Sub3.aClass305_Sub1_4952, -4789);
                    }
                    Class98_Sub10_Sub15.method1050((byte)104);
                    if (~Class292.anInt3359 > -97) {
                        Class206.method2727(79);
                    }
                    Class257.method3201((byte)64, true);
                    Class146_Sub3.aClass305_Sub1_4952.method3568(null, (byte)114, b4 ? Class81.aSArray618[0] : null, Class265.aHa1974);
                    Class146_Sub3.aClass305_Sub1_4952.method3589(false, (byte)105, Class265.aHa1974);
                    Class257.method3201((byte)49, true);
                    if (b4) {
                        Class248.method3158(true);
                        Class257.method3201((byte)96, true);
                        if (Class151_Sub9.anInt5028 == 0) {
                            Class92.method898(false, Class377.aByteArrayArray3182, Class98_Sub31_Sub1.aClass305_Sub1_5816);
                        }
                        else {
                            Class48_Sub2.method470(Class377.aByteArrayArray3182, Class98_Sub31_Sub1.aClass305_Sub1_5816, -4789);
                        }
                        Class98_Sub10_Sub15.method1050((byte)123);
                        Class257.method3201((byte)126, true);
                        Class98_Sub31_Sub1.aClass305_Sub1_5816.method3568(Class98_Sub46_Sub2_Sub2.aSArray6298[0], (byte)(-81), null, Class265.aHa1974);
                        Class98_Sub31_Sub1.aClass305_Sub1_5816.method3589(true, (byte)105, Class265.aHa1974);
                        Class257.method3201((byte)47, true);
                        Class248.method3158(false);
                    }
                    r_Sub1.method1645(-125);
                    int n10 = Class146_Sub3.aClass305_Sub1_4952.anInt5302;
                    if (n10 > Class43.anInt377) {
                        n10 = Class43.anInt377;
                    }
                    if (Class43.anInt377 - 1 > n10) {
                        n10 = Class43.anInt377 - 1;
                    }
                    if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)126) != 0) {
                        Class46.method439(0);
                    }
                    else {
                        Class46.method439(n10);
                    }
                    for (int n11 = 0; ~n11 > -5; ++n11) {
                        for (int n12 = 0; Class165.anInt1276 > n12; ++n12) {
                            for (int i = 0; i < Class98_Sub10_Sub7.anInt5572; ++i) {
                                Class98_Sub32.method1437(n12, n11, (byte)64, i);
                            }
                        }
                    }
                    Class135.method2264((byte)(-109));
                    Class128.method2224(22696);
                    Class77_Sub1.method789(125);
                    Class98_Sub10_Sub15.method1050((byte)103);
                    Class96.method928((byte)(-42));
                    if (Class284.aFrame2168 != null && aa_Sub1.aClass123_3561 != null && Class177.anInt1376 == 11) {
                        final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, aa_Sub3.aClass171_3570, Class331.aClass117_2811);
                        method3023.aClass98_Sub22_Sub1_3865.writeInt(1571862888, 1057001181);
                        Class98_Sub10_Sub29.sendPacket(false, method3023);
                    }
                    if (b <= 26) {
                        method2607((byte)(-1));
                    }
                    if (~Class151_Sub9.anInt5028 == -1) {
                        final int n13 = (-(Class165.anInt1276 >> 1784299556) + Class160.anInt1258) / 8;
                        final int n14 = ((Class165.anInt1276 >> 554209988) + Class160.anInt1258) / 8;
                        final int n15 = (-(Class98_Sub10_Sub7.anInt5572 >> -69835196) + Class275.anInt2047) / 8;
                        final int n16 = (Class275.anInt2047 - -(Class98_Sub10_Sub7.anInt5572 >> -1955440636)) / 8;
                        for (int n17 = n13 - 1; ~n17 >= ~(n14 + 1); ++n17) {
                            for (int j = -1 + n15; j <= 1 + n16; ++j) {
                                if (n13 > n17 || n14 < n17 || ~j > ~n15 || j > n16) {
                                    Class234.aClass207_1748.method2755("m" + n17 + "_" + j, -114);
                                    Class234.aClass207_1748.method2755("l" + n17 + "_" + j, -127);
                                }
                            }
                        }
                    }
                    if (~Class177.anInt1376 != 0xFFFFFFFB) {
                        if (Class177.anInt1376 == 8) {
                            Class61.method538(7, false);
                        }
                        else {
                            Class61.method538(10, false);
                            if (aa_Sub1.aClass123_3561 != null) {
                                Class98_Sub10_Sub29.sendPacket(false, Class246_Sub3_Sub4.method3023(260, Class292.aClass171_3339, Class331.aClass117_2811));
                            }
                        }
                    }
                    else {
                        Class61.method538(3, false);
                    }
                    Class338.method3778((byte)(-72));
                    Class128.method2224(22696);
                    Class32.method316(false);
                    Class358.aBoolean3033 = true;
                    if (Class270.aBoolean2031) {
                        Class98_Sub46.method1525("Took: " + (-Class123.aLong1011 + Class343.method3819(-47)) + "ms", -80);
                        Class270.aBoolean2031 = false;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.F(" + b + ')');
        }
    }
    
    static final void method2608(final int anInt6400, final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2, final int[] array, final int n) {
        try {
            if (class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373 != null) {
                boolean b = true;
                for (int n2 = 0; ~n2 > ~class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373.length; ++n2) {
                    if (~class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373[n2] != ~array[n2]) {
                        b = false;
                        break;
                    }
                }
                if (b && ~class246_Sub3_Sub4_Sub2_Sub2.anInt6413 != 0x0) {
                    final Class97 method2623 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2_Sub2.anInt6413, 16383);
                    final int anInt6401 = method2623.anInt819;
                    if (~anInt6401 == 0xFFFFFFFE) {
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6361 = 1;
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6400 = anInt6400;
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6366 = 0;
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6405 = 0;
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6393 = 0;
                        if (!class246_Sub3_Sub4_Sub2_Sub2.aBoolean6371) {
                            Class349.method3840((byte)(-128), class246_Sub3_Sub4_Sub2_Sub2, class246_Sub3_Sub4_Sub2_Sub2.anInt6393, method2623);
                        }
                    }
                    if (anInt6401 == 2) {
                        class246_Sub3_Sub4_Sub2_Sub2.anInt6405 = 0;
                    }
                }
            }
            boolean b2 = true;
            for (int n3 = n; array.length > n3; ++n3) {
                if (~array[n3] != 0x0) {
                    b2 = false;
                }
                if (class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373 == null || ~class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373[n3] == 0x0 || ~Class151_Sub7.aClass183_5001.method2623(array[n3], 16383).anInt829 <= ~Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373[n3], 16383).anInt829) {
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6400 = anInt6400;
                    class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373 = array;
                    break;
                }
            }
            if (b2) {
                class246_Sub3_Sub4_Sub2_Sub2.anIntArray6373 = array;
                class246_Sub3_Sub4_Sub2_Sub2.anInt6400 = anInt6400;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.D(" + anInt6400 + ',' + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method2609(final Class181 class181, final byte b) {
        try {
            this.anInt1429 = class181.anInt1429;
            this.anInt1428 = class181.anInt1428;
            this.anInt1426 = class181.anInt1426;
            this.anInt1425 = class181.anInt1425;
            this.anInt1431 = class181.anInt1431;
            this.anInt1427 = class181.anInt1427;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.B(" + ((class181 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method2610(final boolean b, final boolean b2, final int n) {
        try {
            final Class98_Sub3 method669 = Class64_Sub28.method669(n, b2, 6);
            if (b && method669 != null) {
                method669.method942(111);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.E(" + b + ',' + b2 + ',' + n + ')');
        }
    }
    
    final Class181 method2611(final int n) {
        try {
            if (n != -1) {
                Class181.aClass148_1430 = null;
            }
            return new Class181(this.anInt1428, this.anInt1429, this.anInt1425, this.anInt1426, this.anInt1427, this.anInt1431);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.C(" + n + ')');
        }
    }
    
    Class181(final int anInt1428) {
        this.anInt1425 = 128;
        this.anInt1429 = 128;
        try {
            this.anInt1428 = anInt1428;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.<init>(" + anInt1428 + ')');
        }
    }
    
    private Class181(final int anInt1428, final int anInt1429, final int anInt1430, final int anInt1431, final int anInt1432, final int anInt1433) {
        this.anInt1425 = 128;
        this.anInt1429 = 128;
        try {
            this.anInt1428 = anInt1428;
            this.anInt1425 = anInt1430;
            this.anInt1426 = anInt1431;
            this.anInt1427 = anInt1432;
            this.anInt1431 = anInt1433;
            this.anInt1429 = anInt1429;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mc.<init>(" + anInt1428 + ',' + anInt1429 + ',' + anInt1430 + ',' + anInt1431 + ',' + anInt1432 + ',' + anInt1433 + ')');
        }
    }
    
    static {
        Class181.aClass148_1430 = new Class148();
        Class181.anInt1432 = 0;
    }
}
