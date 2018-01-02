// 
// Decompiled by Procyon v0.5.30
// 

final class Class50
{
    static int[] anIntArray417;
    static int anInt418;
    static float aFloat419;
    
    public static void method483(final int n) {
        try {
            if (n != -11543) {
                method483(-8);
            }
            Class50.anIntArray417 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dj.D(" + n + ')');
        }
    }
    
    static final void method484(final int n, final int n2) {
        try {
            final int n3 = Class215.anInt1614 + -Class98_Sub10_Sub14.anInt5613;
            if (n3 >= 100) {
                Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
                Class98_Sub46_Sub20_Sub2.anInt6319 = 1;
            }
            else {
                int n4 = (int)Class119_Sub4.aFloat4740;
                if (~n4 > ~(Class43.anInt372 >> 237711528)) {
                    n4 = Class43.anInt372 >> 238117992;
                }
                if (Class217.aBooleanArray3410[4] && 128 + Class98_Sub10_Sub13.anIntArray5603[4] > n4) {
                    n4 = 128 + Class98_Sub10_Sub13.anIntArray5603[4];
                }
                Class104.method1712(false, Class98_Sub10_Sub9.anInt5581 + (int)Class98_Sub22_Sub2.aFloat5794 & 0x3FFF, (n4 >> 756311203) * 3 + 600 << 995508098, Class224_Sub3_Sub1.anInt6147, n, Class201.anInt1545, n4, Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084, 24111) - 200);
                final float n5 = 1.0f - (100 + -n3) * ((-n3 + 100) * (100 - n3)) / 1000000.0f;
                Class246_Sub3_Sub4_Sub2.anInt6357 = (int)((-Class299.anInt2494 + Class246_Sub3_Sub4_Sub2.anInt6357) * n5 + Class299.anInt2494);
                Class98_Sub46_Sub10.anInt6020 = (int)(Class246_Sub3_Sub4_Sub2_Sub1.anInt6511 + (-Class246_Sub3_Sub4_Sub2_Sub1.anInt6511 + Class98_Sub46_Sub10.anInt6020) * n5);
                Class79.anInt601 = (int)(Class363.anInt3095 + n5 * (-Class363.anInt3095 + Class79.anInt601));
                Class134.anInt3461 = (int)(Class98_Sub50.anInt4292 + (Class134.anInt3461 + -Class98_Sub50.anInt4292) * n5);
                int n6 = -Class96.anInt801 + Class186.anInt3424;
                Label_0331: {
                    if (~n6 >= -8193) {
                        if (n6 >= -8192) {
                            break Label_0331;
                        }
                        n6 += 16384;
                        if (!client.aBoolean3553) {
                            break Label_0331;
                        }
                    }
                    n6 -= 16384;
                }
                Class186.anInt3424 = (int)(Class96.anInt801 + n6 * n5);
                Class186.anInt3424 &= 0x3FFF;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dj.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method485(final int n) {
        try {
            final int anInt71 = Class2.anInt71;
            final int[] anIntArray2705 = Class319.anIntArray2705;
            for (int i = 0; i < anInt71; ++i) {
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[i]];
                if (class246_Sub3_Sub4_Sub2_Sub2 != null) {
                    Class98_Sub10_Sub10.method1038(class246_Sub3_Sub4_Sub2_Sub2, class246_Sub3_Sub4_Sub2_Sub2.method3034(0), -12212);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dj.A(" + n + ')');
        }
    }
    
    static final Class98_Sub11 method486(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean b, final int n8) {
        try {
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class254.aClass171_1940, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.writeShort(Class272.anInt2038 + n7, 128);
            method3023.aClass98_Sub22_Sub1_3865.writeShort(aa_Sub2.anInt3562 + n6, 128);
            method3023.aClass98_Sub22_Sub1_3865.method1194(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, 72);
            return method3023;
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static {
        Class50.anInt418 = 0;
    }
}
