// 
// Decompiled by Procyon v0.5.30
// 

final class Class333 implements Interface13
{
    static int anInt3385;
    static int anInt3386;
    int anInt3387;
    int anInt3388;
    float[] aFloatArray3389;
    static int anInt3390;
    
    static final void method3761(final int n, final int[] array, int i, int n2, final byte b) {
        try {
            --i;
            while (--n2 - 7 > i) {
                array[++i] = n;
                array[++i] = n;
                array[++i] = n;
                array[++i] = n;
                array[++i] = n;
                array[++i] = n;
                array[++i] = n;
                array[++i] = n;
            }
            while (i < n2) {
                array[++i] = n;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uh.B(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + i + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final void method3762(final byte b, final boolean b2, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2) {
        try {
            final Class294 method3039 = class246_Sub3_Sub4_Sub2.method3039(1);
            if (class246_Sub3_Sub4_Sub2.anInt6434 == 0) {
                Class64_Sub23.anInt3708 = -1;
                Class366.anInt3121 = 0;
                class246_Sub3_Sub4_Sub2.anInt6433 = 0;
            }
            else {
                if (class246_Sub3_Sub4_Sub2.anInt6413 != -1 && class246_Sub3_Sub4_Sub2.anInt6400 == 0) {
                    final Class97 method3040 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6413, 16383);
                    if (~class246_Sub3_Sub4_Sub2.anInt6436 < -1 && method3040.anInt821 == 0) {
                        ++class246_Sub3_Sub4_Sub2.anInt6433;
                        Class64_Sub23.anInt3708 = -1;
                        Class366.anInt3121 = 0;
                        return;
                    }
                    if (~class246_Sub3_Sub4_Sub2.anInt6436 >= -1 && ~method3040.anInt816 == -1) {
                        Class366.anInt3121 = 0;
                        ++class246_Sub3_Sub4_Sub2.anInt6433;
                        Class64_Sub23.anInt3708 = -1;
                        return;
                    }
                }
                if (class246_Sub3_Sub4_Sub2.anInt6379 != -1 && class246_Sub3_Sub4_Sub2.anInt6391 <= Class215.anInt1614) {
                    final Class107 method3041 = Class196.aClass304_1509.method3564(2, class246_Sub3_Sub4_Sub2.anInt6379);
                    if (method3041.aBoolean909 && method3041.anInt910 != -1) {
                        final Class97 method3042 = Class151_Sub7.aClass183_5001.method2623(method3041.anInt910, 16383);
                        if (class246_Sub3_Sub4_Sub2.anInt6436 > 0 && method3042.anInt821 == 0) {
                            ++class246_Sub3_Sub4_Sub2.anInt6433;
                            Class366.anInt3121 = 0;
                            Class64_Sub23.anInt3708 = -1;
                            return;
                        }
                        if (class246_Sub3_Sub4_Sub2.anInt6436 <= 0 && ~method3042.anInt816 == -1) {
                            ++class246_Sub3_Sub4_Sub2.anInt6433;
                            Class64_Sub23.anInt3708 = -1;
                            Class366.anInt3121 = 0;
                            return;
                        }
                    }
                }
                if (~class246_Sub3_Sub4_Sub2.anInt6379 != 0x0 && Class215.anInt1614 >= class246_Sub3_Sub4_Sub2.anInt6391) {
                    final Class107 method3043 = Class196.aClass304_1509.method3564(2, class246_Sub3_Sub4_Sub2.anInt6379);
                    if (method3043.aBoolean909 && method3043.anInt910 != -1) {
                        final Class97 method3044 = Class151_Sub7.aClass183_5001.method2623(method3043.anInt910, 16383);
                        if (class246_Sub3_Sub4_Sub2.anInt6436 > 0 && method3044.anInt821 == 0) {
                            Class366.anInt3121 = 0;
                            ++class246_Sub3_Sub4_Sub2.anInt6433;
                            Class64_Sub23.anInt3708 = -1;
                            return;
                        }
                        if (~class246_Sub3_Sub4_Sub2.anInt6436 >= -1 && ~method3044.anInt816 == -1) {
                            ++class246_Sub3_Sub4_Sub2.anInt6433;
                            Class64_Sub23.anInt3708 = -1;
                            Class366.anInt3121 = 0;
                            return;
                        }
                    }
                }
                final int anInt5084 = class246_Sub3_Sub4_Sub2.anInt5084;
                final int anInt5085 = class246_Sub3_Sub4_Sub2.anInt5079;
                final int anInt5086 = 512 * class246_Sub3_Sub4_Sub2.anIntArray6437[-1 + class246_Sub3_Sub4_Sub2.anInt6434] + 256 * class246_Sub3_Sub4_Sub2.method3034(0);
                final int anInt5087 = class246_Sub3_Sub4_Sub2.anIntArray6438[-1 + class246_Sub3_Sub4_Sub2.anInt6434] * 512 - -(class246_Sub3_Sub4_Sub2.method3034(0) * 256);
                if (anInt5084 < anInt5086) {
                    if (anInt5085 >= anInt5087) {
                        if (~anInt5085 < ~anInt5087) {
                            class246_Sub3_Sub4_Sub2.method3042(14336, -8193);
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.method3042(12288, -8193);
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.method3042(10240, -8193);
                    }
                }
                else if (anInt5086 < anInt5084) {
                    if (anInt5085 < anInt5087) {
                        class246_Sub3_Sub4_Sub2.method3042(6144, -8193);
                    }
                    else if (~anInt5085 >= ~anInt5087) {
                        class246_Sub3_Sub4_Sub2.method3042(4096, -8193);
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.method3042(2048, -8193);
                    }
                }
                else if (anInt5087 <= anInt5085) {
                    if (~anInt5085 < ~anInt5087) {
                        class246_Sub3_Sub4_Sub2.method3042(0, -8193);
                    }
                }
                else {
                    class246_Sub3_Sub4_Sub2.method3042(8192, -8193);
                }
                final byte anInt5088 = class246_Sub3_Sub4_Sub2.aByteArray6443[class246_Sub3_Sub4_Sub2.anInt6434 - 1];
                if (!b2 && (anInt5086 + -anInt5084 > 1024 || ~(anInt5086 + -anInt5084) > 1023 || ~(-anInt5085 + anInt5087) < -1025 || -anInt5085 + anInt5087 < -1024)) {
                    class246_Sub3_Sub4_Sub2.anInt5084 = anInt5086;
                    class246_Sub3_Sub4_Sub2.anInt5079 = anInt5087;
                    class246_Sub3_Sub4_Sub2.method3047(class246_Sub3_Sub4_Sub2.anInt6415, false, 75);
                    Class64_Sub23.anInt3708 = -1;
                    if (class246_Sub3_Sub4_Sub2.anInt6436 > 0) {
                        --class246_Sub3_Sub4_Sub2.anInt6436;
                    }
                    --class246_Sub3_Sub4_Sub2.anInt6434;
                    Class366.anInt3121 = 0;
                }
                else {
                    int n = 16;
                    boolean aBoolean1126 = true;
                    if (class246_Sub3_Sub4_Sub2 instanceof Class246_Sub3_Sub4_Sub2_Sub1) {
                        aBoolean1126 = ((Class246_Sub3_Sub4_Sub2_Sub1)class246_Sub3_Sub4_Sub2).aClass141_6504.aBoolean1126;
                    }
                    if (!aBoolean1126) {
                        if (!b2 && class246_Sub3_Sub4_Sub2.anInt6434 > 1) {
                            n = 24;
                        }
                        if (!b2 && ~class246_Sub3_Sub4_Sub2.anInt6434 < -3) {
                            n = 32;
                        }
                    }
                    else {
                        if (~(-class246_Sub3_Sub4_Sub2.aClass325_6399.anInt2730 + class246_Sub3_Sub4_Sub2.anInt6415) != -1 && ~class246_Sub3_Sub4_Sub2.anInt6364 == 0x0 && ~class246_Sub3_Sub4_Sub2.anInt6414 != -1) {
                            n = 8;
                        }
                        if (!b2 && ~class246_Sub3_Sub4_Sub2.anInt6434 < -3) {
                            n = 24;
                        }
                        if (!b2 && ~class246_Sub3_Sub4_Sub2.anInt6434 < -4) {
                            n = 32;
                        }
                    }
                    if (~class246_Sub3_Sub4_Sub2.anInt6433 < -1 && class246_Sub3_Sub4_Sub2.anInt6434 > 1) {
                        n = 32;
                        --class246_Sub3_Sub4_Sub2.anInt6433;
                    }
                    if (~anInt5088 == 0xFFFFFFFD) {
                        n <<= 1;
                    }
                    else if (anInt5088 == 0) {
                        n >>= 1;
                    }
                    if (method3039.anInt2401 != -1) {
                        final int n2 = n << 9;
                        if (~class246_Sub3_Sub4_Sub2.anInt6434 == 0xFFFFFFFE) {
                            final int n3 = class246_Sub3_Sub4_Sub2.anInt6435 * class246_Sub3_Sub4_Sub2.anInt6435;
                            final int n4 = ((~class246_Sub3_Sub4_Sub2.anInt5084 >= ~anInt5086) ? (anInt5086 + -class246_Sub3_Sub4_Sub2.anInt5084) : (class246_Sub3_Sub4_Sub2.anInt5084 - anInt5086)) << -1268948087;
                            final int n5 = ((anInt5087 < class246_Sub3_Sub4_Sub2.anInt5079) ? (class246_Sub3_Sub4_Sub2.anInt5079 + -anInt5087) : (anInt5087 + -class246_Sub3_Sub4_Sub2.anInt5079)) << -1396718167;
                            final int n6 = (~n4 < ~n5) ? n4 : n5;
                            if (method3039.anInt2401 * 2 * n6 >= n3) {
                                if (~(n3 / 2) < ~n6) {
                                    class246_Sub3_Sub4_Sub2.anInt6435 -= method3039.anInt2401;
                                    if (~class246_Sub3_Sub4_Sub2.anInt6435 > -1) {
                                        class246_Sub3_Sub4_Sub2.anInt6435 = 0;
                                    }
                                }
                                else if (~class246_Sub3_Sub4_Sub2.anInt6435 > ~n2) {
                                    class246_Sub3_Sub4_Sub2.anInt6435 += method3039.anInt2401;
                                    if (~n2 > ~class246_Sub3_Sub4_Sub2.anInt6435) {
                                        class246_Sub3_Sub4_Sub2.anInt6435 = n2;
                                    }
                                }
                            }
                            else {
                                class246_Sub3_Sub4_Sub2.anInt6435 /= 2;
                            }
                        }
                        else if (class246_Sub3_Sub4_Sub2.anInt6435 >= n2) {
                            if (class246_Sub3_Sub4_Sub2.anInt6435 > 0) {
                                class246_Sub3_Sub4_Sub2.anInt6435 -= method3039.anInt2401;
                                if (class246_Sub3_Sub4_Sub2.anInt6435 < 0) {
                                    class246_Sub3_Sub4_Sub2.anInt6435 = 0;
                                }
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6435 += method3039.anInt2401;
                            if (~class246_Sub3_Sub4_Sub2.anInt6435 < ~n2) {
                                class246_Sub3_Sub4_Sub2.anInt6435 = n2;
                            }
                        }
                        n = class246_Sub3_Sub4_Sub2.anInt6435 >> -1583796215;
                        if (~n > -2) {
                            n = 1;
                        }
                    }
                    Class366.anInt3121 = 0;
                    if (anInt5086 != anInt5084 || anInt5087 != anInt5085) {
                        if (~anInt5086 < ~anInt5084) {
                            class246_Sub3_Sub4_Sub2.anInt5084 += n;
                            Class366.anInt3121 |= 0x4;
                            if (~anInt5086 > ~class246_Sub3_Sub4_Sub2.anInt5084) {
                                class246_Sub3_Sub4_Sub2.anInt5084 = anInt5086;
                            }
                        }
                        else if (~anInt5086 > ~anInt5084) {
                            Class366.anInt3121 |= 0x8;
                            class246_Sub3_Sub4_Sub2.anInt5084 -= n;
                            if (class246_Sub3_Sub4_Sub2.anInt5084 < anInt5086) {
                                class246_Sub3_Sub4_Sub2.anInt5084 = anInt5086;
                            }
                        }
                        if (~n > -33) {
                            Class64_Sub23.anInt3708 = anInt5088;
                        }
                        else {
                            Class64_Sub23.anInt3708 = 2;
                        }
                        if (anInt5085 >= anInt5087) {
                            if (~anInt5087 > ~anInt5085) {
                                Class366.anInt3121 |= 0x2;
                                class246_Sub3_Sub4_Sub2.anInt5079 -= n;
                                if (~class246_Sub3_Sub4_Sub2.anInt5079 > ~anInt5087) {
                                    class246_Sub3_Sub4_Sub2.anInt5079 = anInt5087;
                                }
                            }
                        }
                        else {
                            Class366.anInt3121 |= 0x1;
                            class246_Sub3_Sub4_Sub2.anInt5079 += n;
                            if (anInt5087 < class246_Sub3_Sub4_Sub2.anInt5079) {
                                class246_Sub3_Sub4_Sub2.anInt5079 = anInt5087;
                            }
                        }
                    }
                    else {
                        Class64_Sub23.anInt3708 = -1;
                    }
                    if (anInt5086 == class246_Sub3_Sub4_Sub2.anInt5084 && ~class246_Sub3_Sub4_Sub2.anInt5079 == ~anInt5087) {
                        if (class246_Sub3_Sub4_Sub2.anInt6436 > 0) {
                            --class246_Sub3_Sub4_Sub2.anInt6436;
                        }
                        --class246_Sub3_Sub4_Sub2.anInt6434;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uh.A(" + b + ',' + b2 + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class333(final int anInt3388, final int anInt3389) {
        try {
            this.anInt3387 = anInt3389;
            this.aFloatArray3389 = new float[anInt3389 * anInt3388];
            this.anInt3388 = anInt3388;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uh.<init>(" + anInt3388 + ',' + anInt3389 + ')');
        }
    }
    
    static {
        Class333.anInt3386 = 0;
        Class333.anInt3390 = 0;
    }
}
