// 
// Decompiled by Procyon v0.5.30
// 

final class Class61
{
    private int anInt477;
    static short[][][] aShortArrayArrayArray478;
    static int anInt479;
    private Class377 aClass377_480;
    static Class207 aClass207_481;
    private Class98 aClass98_482;
    
    static final void method537(final byte b) {
        try {
            Class98_Sub27.method1282((byte)8, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046.method617((byte)120));
            final int n = (Class272.anInt2038 >> 913933219) + (Class98_Sub46_Sub10.anInt6020 >> -195505812);
            final int n2 = (Class134.anInt3461 >> -1524816148) + (aa_Sub2.anInt3562 >> -522339549);
            final Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_660 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
            final boolean b2 = false;
            aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 = (byte)(b2 ? 1 : 0);
            Class43.anInt377 = (b2 ? 1 : 0);
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3060(8, 8, 1470);
            final int n3 = 18;
            Class105.aByteArrayArray3414 = new byte[n3][];
            Class255.aByteArrayArray3211 = new byte[n3][];
            client.aByteArrayArray3551 = new byte[n3][];
            Class98_Sub46_Sub17.anIntArrayArray6049 = new int[n3][4];
            Class121.anIntArray1006 = new int[n3];
            Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533 = new byte[n3][];
            Class98_Sub36.anIntArray4162 = new int[n3];
            Class287.anIntArray2188 = new int[n3];
            Class377.aByteArrayArray3182 = new byte[n3][];
            Class377.anIntArray3178 = new int[n3];
            Class76_Sub7.anIntArray3765 = new int[n3];
            Class302.anIntArray2517 = new int[n3];
            if (b <= 64) {
                method542(true);
            }
            int n4 = 0;
            for (int i = (n - (Class165.anInt1276 >> -2009281948)) / 8; i <= ((Class165.anInt1276 >> 1983437604) + n) / 8; ++i) {
                for (int j = (-(Class98_Sub10_Sub7.anInt5572 >> -1249052220) + n2) / 8; j <= ((Class98_Sub10_Sub7.anInt5572 >> 226644196) + n2) / 8; ++j) {
                    Class121.anIntArray1006[n4] = j + (i << 1247776552);
                    Class287.anIntArray2188[n4] = Class234.aClass207_1748.method2750((byte)(-90), "m" + i + "_" + j);
                    Class98_Sub36.anIntArray4162[n4] = Class234.aClass207_1748.method2750((byte)(-69), "l" + i + "_" + j);
                    Class76_Sub7.anIntArray3765[n4] = Class234.aClass207_1748.method2750((byte)(-100), "n" + i + "_" + j);
                    Class302.anIntArray2517[n4] = Class234.aClass207_1748.method2750((byte)(-109), "um" + i + "_" + j);
                    Class377.anIntArray3178[n4] = Class234.aClass207_1748.method2750((byte)(-124), "ul" + i + "_" + j);
                    if (~Class76_Sub7.anIntArray3765[n4] == 0x0) {
                        Class287.anIntArray2188[n4] = -1;
                        Class98_Sub36.anIntArray4162[n4] = -1;
                        Class302.anIntArray2517[n4] = -1;
                        Class377.anIntArray3178[n4] = -1;
                    }
                    ++n4;
                }
            }
            for (int n5 = n4; ~n5 > ~Class76_Sub7.anIntArray3765.length; ++n5) {
                Class76_Sub7.anIntArray3765[n5] = -1;
                Class287.anIntArray2188[n5] = -1;
                Class98_Sub36.anIntArray4162[n5] = -1;
                Class302.anIntArray2517[n5] = -1;
                Class377.anIntArray3178[n5] = -1;
            }
            int n6;
            if (Class177.anInt1376 != 3) {
                n6 = 8;
            }
            else {
                n6 = 4;
            }
            Class251.method3170(-6547, n2, false, n, n6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.C(" + b + ')');
        }
    }
    
    static final void method538(final int anInt1376, final boolean b) {
        try {
            if (~anInt1376 != ~Class177.anInt1376) {
                if (anInt1376 == 13) {
                    if (Class98_Sub10_Sub10.aString5593 == null) {
                        Class251.method3171(Class360.aString3064, -17877, Class98_Sub5.aString3837, Class146_Sub2.anInt4855);
                    }
                    else {
                        Class98_Sub10_Sub14.method1045(Class146_Sub2.anInt4855, -6182);
                    }
                }
                if (anInt1376 != 13 && Class318.aClass123_2698 != null) {
                    Class318.aClass123_2698.method2207(-61);
                    Class318.aClass123_2698 = null;
                }
                if (anInt1376 == 3) {
                    Class269.method3269(Class15.anInt185 != Class297.anInt2470, 0);
                }
                if (~anInt1376 == 0xFFFFFFF8) {
                    Class96.method922(~Class15.anInt185 != ~Class19.anInt3450, -1);
                }
                if (anInt1376 == 5) {
                    if (Class98_Sub10_Sub10.aString5593 != null) {
                        Class27.method296((byte)55);
                    }
                    else {
                        Class276.method3285(Class360.aString3064, Class98_Sub5.aString3837, (byte)(-68));
                    }
                }
                else if (anInt1376 != 6) {
                    if (~anInt1376 == 0xFFFFFFF6) {
                        if (Class98_Sub10_Sub10.aString5593 == null) {
                            Class251.method3171(Class360.aString3064, -17877, Class98_Sub5.aString3837, Class146_Sub2.anInt4855);
                        }
                        else {
                            Class98_Sub10_Sub14.method1045(Class146_Sub2.anInt4855, -6182);
                        }
                    }
                    else if (~anInt1376 == 0xFFFFFFF3) {
                        if (Class98_Sub10_Sub10.aString5593 == null) {
                            Class276.method3285(Class360.aString3064, Class98_Sub5.aString3837, (byte)(-68));
                        }
                        else {
                            Class27.method296((byte)55);
                        }
                    }
                }
                else if (Class98_Sub10_Sub10.aString5593 != null) {
                    Class98_Sub10_Sub14.method1045(Class146_Sub2.anInt4855, -6182);
                }
                else {
                    Class251.method3171(Class360.aString3064, -17877, Class98_Sub5.aString3837, Class146_Sub2.anInt4855);
                }
                if (Class199.method2690(Class177.anInt1376, 8)) {
                    client.aClass207_3549.anInt1575 = 2;
                    Class98_Sub10_Sub24.aClass207_5668.anInt1575 = 2;
                    Class375.aClass207_3167.anInt1575 = 2;
                    Class234.aClass207_1751.anInt1575 = 2;
                    Class208.aClass207_1581.anInt1575 = 2;
                    Class246_Sub3.aClass207_5087.anInt1575 = 2;
                    PlayerUpdateMask.aClass207_525.anInt1575 = 2;
                }
                if (Class199.method2690(anInt1376, 8)) {
                    Class280.anInt2105 = 1;
                    Class98_Sub5_Sub3.anInt5538 = 1;
                    Class130.anInt1031 = 0;
                    Class142.anInt1160 = 0;
                    Class132.anInt1043 = 0;
                    Class118.method2173(true, 122);
                    client.aClass207_3549.anInt1575 = 1;
                    Class98_Sub10_Sub24.aClass207_5668.anInt1575 = 1;
                    Class375.aClass207_3167.anInt1575 = 1;
                    Class234.aClass207_1751.anInt1575 = 1;
                    Class208.aClass207_1581.anInt1575 = 1;
                    Class246_Sub3.aClass207_5087.anInt1575 = 1;
                    PlayerUpdateMask.aClass207_525.anInt1575 = 1;
                }
                if (~anInt1376 == 0xFFFFFFF4 || anInt1376 == 3) {
                    Class4.method174((byte)99);
                }
                final boolean b2 = ~anInt1376 == 0xFFFFFFFD || Class53_Sub1.method499(2048, anInt1376) || Class246_Sub3_Sub3.method3011(-6410, anInt1376);
                if (b2 == (~Class177.anInt1376 != 0xFFFFFFFD && !Class53_Sub1.method499(2048, Class177.anInt1376) && !Class246_Sub3_Sub3.method3011(-6410, Class177.anInt1376))) {
                    if (b2) {
                        Class144.anInt1169 = Class94.anInt795;
                        if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4072.method641((byte)125) == -1) {
                            Class96.method923(103, 2);
                        }
                        else {
                            Class226.method2854(false, false, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4072.method641((byte)124), Class98_Sub10_Sub1.aClass207_5544, 0, 2, Class94.anInt795);
                            Class233.method2883((byte)111);
                        }
                        Class98_Sub10_Sub38.aClass135_5765.method2255(2, false);
                    }
                    else {
                        Class96.method923(100, 2);
                        Class98_Sub10_Sub38.aClass135_5765.method2255(2, true);
                    }
                }
                if (Class199.method2690(anInt1376, 8) || ~anInt1376 == 0xFFFFFFF2) {
                    Class265.aHa1974.method1817();
                }
                if (!b) {
                    Class177.anInt1376 = anInt1376;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.D(" + anInt1376 + ',' + b + ')');
        }
    }
    
    final Class98 method539(final int n) {
        try {
            if (n != 2) {
                this.aClass377_480 = null;
            }
            if (~this.anInt477 < -1 && this.aClass377_480.aClass98Array3180[this.anInt477 - 1] != this.aClass98_482) {
                final Class98 aClass98_482 = this.aClass98_482;
                this.aClass98_482 = aClass98_482.aClass98_836;
                return aClass98_482;
            }
            while (~this.anInt477 > ~this.aClass377_480.anInt3179) {
                final Class98 aClass98_483 = this.aClass377_480.aClass98Array3180[this.anInt477++].aClass98_836;
                if (aClass98_483 != this.aClass377_480.aClass98Array3180[-1 + this.anInt477]) {
                    this.aClass98_482 = aClass98_483.aClass98_836;
                    return aClass98_483;
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.E(" + n + ')');
        }
    }
    
    static final void method540(final byte b) {
        try {
            if (b != -51) {
                method537((byte)114);
            }
            Class246.method2962(false);
            za_Sub2.aBoolean6079 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.A(" + b + ')');
        }
    }
    
    public Class61() {
        this.anInt477 = 0;
    }
    
    final Class98 method541(final int anInt477) {
        try {
            this.anInt477 = anInt477;
            return this.method539(2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.F(" + anInt477 + ')');
        }
    }
    
    Class61(final Class377 aClass377_480) {
        this.anInt477 = 0;
        try {
            this.aClass377_480 = aClass377_480;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.<init>(" + ((aClass377_480 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method542(final boolean b) {
        try {
            if (b) {
                method537((byte)(-49));
            }
            Class61.aClass207_481 = null;
            Class61.aShortArrayArrayArray478 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ec.B(" + b + ')');
        }
    }
    
    static {
        Class61.anInt479 = 0;
    }
}
