// 
// Decompiled by Procyon v0.5.30
// 

final class Class195
{
    static int[] anIntArray1497;
    Interface4_Impl1 anInterface4_Impl1_1498;
    static Class43 aClass43_1499;
    Interface4_Impl1 anInterface4_Impl1_1500;
    boolean aBoolean1501;
    static Class225 aClass225_1502;
    Interface4_Impl2[] anInterface4_Impl2Array1503;
    private ha_Sub3 aHa_Sub3_1504;
    private Interface4_Impl2[] anInterface4_Impl2Array1505;
    static int anInt1506;
    
    final boolean method2659(final int n) {
        try {
            if (n != -22382) {
                return false;
            }
            if (!this.aBoolean1501) {
                return this.anInterface4_Impl2Array1503 != null;
            }
            return this.anInterface4_Impl1_1498 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.B(" + n + ')');
        }
    }
    
    public static void method2660(final int n) {
        try {
            Class195.aClass225_1502 = null;
            if (n != 26845) {
                method2660(22);
            }
            Class195.anIntArray1497 = null;
            Class195.aClass43_1499 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.D(" + n + ')');
        }
    }
    
    static final boolean method2661(final int n, final int n2, final int n3, final int n4, final byte b) {
        try {
            if (b > -94) {
                method2662(-60);
            }
            if (!Class98_Sub17.aBoolean3942 || !Class135.aBoolean1052) {
                return false;
            }
            if (~Class4.anInt81 > -101) {
                return false;
            }
            if (!Class76_Sub5.method758((byte)72, n, n2, n3)) {
                return false;
            }
            if (s_Sub1.method3427(Class78.aSArray594[n].method3420(n2, -12639, n3), r_Sub2.anInt6333, n4, r_Sub2.anInt6333, (byte)16, n2 << Class151_Sub8.anInt5015, n3 << Class151_Sub8.anInt5015)) {
                ++Class151_Sub7.anInt5006;
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static final boolean method2662(final int n) {
        try {
            if (n > -84) {
                Class195.aClass225_1502 = null;
            }
            if (Class76_Sub7.aBoolean3761) {
                try {
                    Class203.method2704("showVideoAd", Class76_Sub11.anApplet3799, -26978);
                    return true;
                }
                catch (Throwable t) {}
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.F(" + n + ')');
        }
    }
    
    static final boolean method2663(final int n, final int n2, final boolean b) {
        try {
            if (b) {
                method2660(-61);
            }
            return ~(n & 0x34) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.A(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    final boolean method2664(final int n) {
        try {
            if (this.anInterface4_Impl1_1500 == null) {
                if (Class332_Sub1.anObject5409 == null) {
                    Class332_Sub1.anObject5409 = Class64_Sub25.method654(2, Class134_Sub1.method2244(-31633, 16.0f, 0.5f, 0.6f, 16, 8, 128, new Class39_Sub1(419684), 4.0f, 4.0f, 128), false);
                }
                final byte[] method1310 = Class98_Sub28_Sub1.method1310(false, Class332_Sub1.anObject5409, false);
                final byte[] array = new byte[method1310.length * 4];
                int n2 = 0;
                for (int n3 = 0; ~n3 > -17; ++n3) {
                    final int n5;
                    int n4 = n5 = 128 * n3 * 128;
                    for (int n6 = 0; ~n6 > -129; ++n6) {
                        final int n7 = n5 + n6 * 128;
                        final int n8 = n5 - -(128 * (0x7F & -1 + n6));
                        final int n9 = n5 + 128 * (n6 + 1 & 0x7F);
                        for (int i = 0; i < 128; ++i) {
                            final float n10 = (method1310[i + n8] & 0xFF) - (0xFF & method1310[n9 + i]);
                            final float n11 = (method1310[(-1 + i & 0x7F) + n7] & 0xFF) - (method1310[(1 + i & 0x7F) + n7] & 0xFF);
                            final float n12 = (float)(128.0 / Math.sqrt(n10 * n10 + (16384.0f + n11 * n11)));
                            array[n2++] = (byte)(n11 * n12 + 127.0f);
                            array[n2++] = (byte)(127.0f + 128.0f * n12);
                            array[n2++] = (byte)(n12 * n10 + 127.0f);
                            array[n2++] = method1310[n4++];
                        }
                    }
                }
                this.anInterface4_Impl1_1500 = this.aHa_Sub3_1504.method2044(-81, Class62.aClass164_486, array, 128, 128, 16);
            }
            return this.anInterface4_Impl1_1500 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.E(" + n + ')');
        }
    }
    
    Class195(final ha_Sub3 aHa_Sub3_1504) {
        this.anInterface4_Impl1_1500 = null;
        this.anInterface4_Impl2Array1503 = null;
        this.anInterface4_Impl1_1498 = null;
        this.anInterface4_Impl2Array1505 = null;
        try {
            this.aHa_Sub3_1504 = aHa_Sub3_1504;
            this.aBoolean1501 = this.aHa_Sub3_1504.aBoolean4588;
            if (this.aBoolean1501 && !this.aHa_Sub3_1504.method1977(Class162.aClass162_1266, true, Class74.aClass164_547)) {
                this.aBoolean1501 = false;
            }
            if (this.aBoolean1501 || this.aHa_Sub3_1504.method1942(0, Class74.aClass164_547, Class162.aClass162_1266)) {
                Class98_Sub42.method1477(false);
                if (!this.aBoolean1501) {
                    this.anInterface4_Impl2Array1503 = new Interface4_Impl2[16];
                    for (int n = 0; ~n > -17; ++n) {
                        this.anInterface4_Impl2Array1503[n] = this.aHa_Sub3_1504.method2053(128, Class74.aClass164_547, (byte)87, Class98_Sub10_Sub20.method1061(2, 32768, 2 * (128 * (n * 128)), Class241.anObject1847), true, 128);
                    }
                    this.anInterface4_Impl2Array1505 = new Interface4_Impl2[16];
                    for (int i = 0; i < 16; ++i) {
                        this.anInterface4_Impl2Array1505[i] = this.aHa_Sub3_1504.method2053(128, Class74.aClass164_547, (byte)87, Class98_Sub10_Sub20.method1061(2, 32768, 16384 * i * 2, Class64_Sub24.anObject3709), true, 128);
                    }
                }
                else {
                    this.anInterface4_Impl1_1498 = this.aHa_Sub3_1504.method2044(95, Class74.aClass164_547, Class98_Sub28_Sub1.method1310(false, Class241.anObject1847, false), 128, 128, 16);
                    this.aHa_Sub3_1504.method2044(125, Class74.aClass164_547, Class98_Sub28_Sub1.method1310(false, Class64_Sub24.anObject3709, false), 128, 128, 16);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mt.<init>(" + ((aHa_Sub3_1504 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class195.anIntArray1497 = new int[16];
    }
}
