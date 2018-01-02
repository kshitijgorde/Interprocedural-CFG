// 
// Decompiled by Procyon v0.5.30
// 

final class Class284_Sub1_Sub2 extends Class284_Sub1
{
    static OutgoingOpcode aClass171_6191;
    static int anInt6192;
    static int[] anIntArray6193;
    private byte[] aByteArray6194;
    
    public Class284_Sub1_Sub2() {
        super(12, 5, 16, 2, 2, 0.45f);
    }
    
    public static void method3369(final byte b) {
        try {
            Class284_Sub1_Sub2.anIntArray6193 = null;
            if (b >= -28) {
                method3371(-40);
            }
            Class284_Sub1_Sub2.aClass171_6191 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tb.K(" + b + ')');
        }
    }
    
    static final void method3370(final int n, final int n2, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final int n3, final int n4) {
        try {
            final Class294 method3039 = class246_Sub3_Sub4_Sub2.method3039(1);
            if (n2 != 6144) {
                Class284_Sub1_Sub2.anIntArray6193 = null;
            }
            final int n5 = class246_Sub3_Sub4_Sub2.anInt6415 - class246_Sub3_Sub4_Sub2.aClass325_6399.anInt2730 & 0x3FFF;
            if (n3 == -1) {
                if (~n5 == -1 && ~class246_Sub3_Sub4_Sub2.anInt6408 >= -26) {
                    if (!class246_Sub3_Sub4_Sub2.aBoolean6359 || !method3039.method3480((byte)(-53), class246_Sub3_Sub4_Sub2.anInt6385)) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.method3478(n2 ^ 0x1820);
                        class246_Sub3_Sub4_Sub2.aBoolean6359 = (class246_Sub3_Sub4_Sub2.anInt6385 != -1);
                    }
                }
                else {
                    if (~n4 <= -1 || method3039.anInt2376 == -1) {
                        if (n4 <= 0 || method3039.anInt2388 == -1) {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2399;
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2388;
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2376;
                    }
                    class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                }
            }
            else if (~class246_Sub3_Sub4_Sub2.anInt6364 == 0x0 || (n5 < 10240 && n5 > 2048)) {
                if (~n5 != -1 || class246_Sub3_Sub4_Sub2.anInt6408 > 25) {
                    if (n3 != 2 || ~method3039.anInt2389 == 0x0) {
                        if (n3 == 0 && method3039.anInt2368 != -1) {
                            if (n4 < 0 && method3039.anInt2405 != -1) {
                                class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2405;
                            }
                            else if (~n4 >= -1 || method3039.anInt2404 == -1) {
                                class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2368;
                            }
                            else {
                                class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2404;
                            }
                        }
                        else if (~n4 <= -1 || method3039.anInt2378 == -1) {
                            if (~n4 < -1 && method3039.anInt2369 != -1) {
                                class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2369;
                            }
                            else {
                                class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2399;
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2378;
                        }
                    }
                    else if (n4 < 0 && ~method3039.anInt2384 != 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2384;
                    }
                    else if (n4 <= 0 || ~method3039.anInt2370 == 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2389;
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2370;
                    }
                    class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                }
                else {
                    if (~n3 == 0xFFFFFFFD && ~method3039.anInt2389 != 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2389;
                    }
                    else if (n3 == 0 && ~method3039.anInt2368 != 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2368;
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2399;
                    }
                    class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                }
            }
            else {
                final int n6 = 0x3FFF & -class246_Sub3_Sub4_Sub2.aClass325_6399.anInt2730 + Class98_Sub43_Sub1.anIntArray5896[n];
                if (~n3 == 0xFFFFFFFD && method3039.anInt2389 != -1) {
                    if (n6 > 2048 && n6 <= 6144 && ~method3039.anInt2402 != 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2402;
                    }
                    else if (n6 < 10240 || ~n6 <= -14337 || method3039.anInt2357 == -1) {
                        if (~n6 < -6145 && ~n6 > -10241 && ~method3039.anInt2361 != 0x0) {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2361;
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2389;
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2357;
                    }
                }
                else if (~n3 == -1 && ~method3039.anInt2368 != 0x0) {
                    if (~n6 < -2049 && n6 <= 6144 && ~method3039.anInt2403 != 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2403;
                    }
                    else if (n6 < 10240 || ~n6 <= -14337 || ~method3039.anInt2377 == 0x0) {
                        if (n6 > 6144 && ~n6 > -10241 && ~method3039.anInt2394 != 0x0) {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2394;
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2368;
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2377;
                    }
                }
                else if (n6 > 2048 && n6 <= 6144 && ~method3039.anInt2372 != 0x0) {
                    class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2372;
                }
                else if (n6 < 10240 || ~n6 <= -14337 || ~method3039.anInt2359 == 0x0) {
                    if (~n6 >= -6145 || n6 >= 10240 || ~method3039.anInt2365 == 0x0) {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2399;
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2365;
                    }
                }
                else {
                    class246_Sub3_Sub4_Sub2.anInt6385 = method3039.anInt2359;
                }
                class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tb.L(" + n + ',' + n2 + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method3371(final int n) {
        try {
            Class64_Sub22.method644(-67);
            if (n != 31398) {
                Class284_Sub1_Sub2.aClass171_6191 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tb.N(" + n + ')');
        }
    }
    
    final byte[] method3372(final boolean b, final int n, final int n2, final int n3) {
        try {
            this.aByteArray6194 = new byte[2 * (n2 * n * n3)];
            if (!b) {
                this.method3363((byte)104, -114, (byte)118);
            }
            this.method3361((byte)(-45), n3, n, n2);
            return this.aByteArray6194;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tb.J(" + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method3363(byte b, final int n, final byte b2) {
        try {
            b = (byte)(((b & 0xFF) >> -428365951) + 127);
            int n2 = n * 2;
            this.aByteArray6194[n2++] = b;
            this.aByteArray6194[n2] = b;
            if (b2 != 42) {
                Class284_Sub1_Sub2.aClass171_6191 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tb.M(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    static final boolean method3373(final int n, final int n2, final byte b) {
        try {
            if (b <= 10) {
                method3370(36, -41, null, 23, 85);
            }
            return ~(n2 & 0xC580) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tb.O(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static {
        Class284_Sub1_Sub2.anIntArray6193 = new int[4];
        Class284_Sub1_Sub2.aClass171_6191 = new OutgoingOpcode(37, -1);
    }
}
