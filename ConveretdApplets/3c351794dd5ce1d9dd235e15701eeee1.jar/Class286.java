// 
// Decompiled by Procyon v0.5.30
// 

final class Class286
{
    int anInt2180;
    int anInt2181;
    static float aFloat2182;
    int anInt2183;
    int anInt2184;
    
    static final void method3380(final int n, final int n2) {
        try {
            Class185.method2628(n2, n - 72, n).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rp.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3381(final int n, final int n2, final int n3, final int n4, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final int n5, final Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_5076, final int n6, final int n7, final int n8) {
        try {
            final int method3035 = aClass246_Sub3_Sub4_Sub2_5076.method3035(28213);
            if (method3035 != -1) {
                Class332 method3036 = (Class332)Class378.aClass79_3189.method802(-120, method3035);
                if (method3036 == null) {
                    final Class324[] method3037 = Class324.method3680(Class332_Sub2.aClass207_5423, method3035, 0);
                    if (method3037 == null) {
                        return;
                    }
                    method3036 = Class265.aHa1974.method1758(method3037[0], true);
                    Class378.aClass79_3189.method805(method3035, method3036, (byte)(-80));
                }
                Class168.method2533(n7 >> -1098562655, class246_Sub3_Sub4_Sub2.anInt5084, n8 >> 101128225, 256 * class246_Sub3_Sub4_Sub2.method3034(0), (byte)94, class246_Sub3_Sub4_Sub2.anInt5079, n, class246_Sub3_Sub4_Sub2.aByte5088, n3, 0);
                final int n9 = n2 - -Class259.anIntArray1957[0] - 18;
                final int n10 = -70 + (Class259.anIntArray1957[1] + n5);
                final int anInt5074 = n9 + n6 / 4 * 18;
                final int anInt5075 = n10 + 18 * (n6 % 4);
                method3036.method3735(anInt5074, anInt5075);
                if (aClass246_Sub3_Sub4_Sub2_5076 == class246_Sub3_Sub4_Sub2) {
                    Class265.aHa1974.method1781(true, 18, 18, -256, anInt5074 - 1, -1 + anInt5075);
                }
                Class93_Sub1_Sub1.method908(anInt5075 + 18, -1 + anInt5075, false, anInt5074 - 1, 18 + anInt5074);
                final Class246_Sub2 method3038 = Class90.method883(-90);
                if (n4 > 15) {
                    method3038.anInt5071 = anInt5075;
                    method3038.aClass246_Sub3_Sub4_Sub2_5076 = aClass246_Sub3_Sub4_Sub2_5076;
                    method3038.anInt5075 = anInt5075 + 16;
                    method3038.anInt5073 = 16 + anInt5074;
                    method3038.anInt5074 = anInt5074;
                    Class151_Sub2.aClass218_4973.method2808(true, method3038);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rp.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + n5 + ',' + ((aClass246_Sub3_Sub4_Sub2_5076 != null) ? "{...}" : "null") + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    static final void method3382(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (Class40.anIntArrayArray367 != null) {
            Class40.anIntArrayArray367[n][n2] = (0xFF000000 | n3);
        }
        if (Class304.aShortArrayArray2534 != null) {
            Class304.aShortArrayArray2534[n][n2] = (short)n4;
        }
        if (Class299_Sub2.aByteArrayArray5291 != null) {
            Class299_Sub2.aByteArrayArray5291[n][n2] = (byte)n5;
        }
    }
    
    static final int method3383(final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2, final boolean b) {
        try {
            if (!b) {
                return -121;
            }
            int n = class246_Sub3_Sub4_Sub2_Sub2.anInt6522;
            final Class294 method3039 = class246_Sub3_Sub4_Sub2_Sub2.method3039(1);
            if (class246_Sub3_Sub4_Sub2_Sub2.anInt6385 == -1 || class246_Sub3_Sub4_Sub2_Sub2.aBoolean6359) {
                n = class246_Sub3_Sub4_Sub2_Sub2.anInt6527;
            }
            else if (~class246_Sub3_Sub4_Sub2_Sub2.anInt6385 != ~method3039.anInt2389 && ~method3039.anInt2361 != ~class246_Sub3_Sub4_Sub2_Sub2.anInt6385 && ~class246_Sub3_Sub4_Sub2_Sub2.anInt6385 != ~method3039.anInt2402 && method3039.anInt2357 != class246_Sub3_Sub4_Sub2_Sub2.anInt6385) {
                if (~class246_Sub3_Sub4_Sub2_Sub2.anInt6385 == ~method3039.anInt2368 || ~method3039.anInt2394 == ~class246_Sub3_Sub4_Sub2_Sub2.anInt6385 || class246_Sub3_Sub4_Sub2_Sub2.anInt6385 == method3039.anInt2403 || ~class246_Sub3_Sub4_Sub2_Sub2.anInt6385 == ~method3039.anInt2377) {
                    n = class246_Sub3_Sub4_Sub2_Sub2.anInt6524;
                }
            }
            else {
                n = class246_Sub3_Sub4_Sub2_Sub2.anInt6517;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rp.B(" + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
}
