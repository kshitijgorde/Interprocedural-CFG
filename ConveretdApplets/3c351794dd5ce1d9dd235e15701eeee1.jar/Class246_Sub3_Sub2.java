// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3_Sub2 extends Class246_Sub3
{
    static IncomingOpcode aClass58_6151;
    
    static final void method3003(final int n) {
        try {
            for (int n2 = 0; ~n2 > ~Class306.anInt2566; ++n2) {
                final Class338 class338 = Class245.aClass338Array1865[n2];
                boolean b = false;
                if (class338.aClass98_Sub31_Sub5_2836 == null) {
                    final Class338 class339 = class338;
                    --class339.anInt2832;
                    if (class338.anInt2832 < (class338.method3782(-4) ? -1500 : -10)) {
                        b = true;
                    }
                    else {
                        if (~class338.aByte2840 == 0xFFFFFFFE && class338.aClass37_2835 == null) {
                            class338.aClass37_2835 = Class37.method342(Class76_Sub2.aClass207_3733, class338.anInt2830, 0);
                            if (class338.aClass37_2835 == null) {
                                continue;
                            }
                            final Class338 class340 = class338;
                            class340.anInt2832 += class338.aClass37_2835.method343();
                        }
                        else if (class338.method3782(n ^ 0x548B) && (class338.aClass98_Sub13_2838 == null || class338.aClass98_Sub24_Sub1_2839 == null)) {
                            if (class338.aClass98_Sub13_2838 == null) {
                                class338.aClass98_Sub13_2838 = Class98_Sub13.method1137(Class196.aClass207_1512, class338.anInt2830);
                            }
                            if (class338.aClass98_Sub13_2838 == null) {
                                continue;
                            }
                            if (class338.aClass98_Sub24_Sub1_2839 == null) {
                                class338.aClass98_Sub24_Sub1_2839 = class338.aClass98_Sub13_2838.method1132(new int[] { 22050 });
                                if (class338.aClass98_Sub24_Sub1_2839 == null) {
                                    continue;
                                }
                            }
                        }
                        if (class338.anInt2832 < 0) {
                            int n3 = 8192;
                            int n4;
                            if (~class338.anInt2837 == -1) {
                                n4 = class338.anInt2841 * ((class338.aByte2840 == 3) ? Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4054.method641((byte)121) : Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066.method641((byte)125)) >> 1244172898;
                            }
                            else if (~(0x3 & class338.anInt2837 >> 43771896) != ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088) {
                                n4 = 0;
                            }
                            else {
                                final int n5 = (0xFF & class338.anInt2837) << -898000343;
                                final int n6 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) << -1121556408;
                                final int n7 = n6 + (((class338.anInt2837 & 0xFF3AD9) >> -375828144 << -1239261591) - (-256 - -Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084));
                                final int n8 = -Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 + (((class338.anInt2837 >> -936393368 & 0xFF) << -1280127351) - (-256 - n6));
                                int n9 = -512 + (Math.abs(n7) + Math.abs(n8));
                                if (~n5 > ~n9) {
                                    class338.anInt2832 = -99999;
                                    continue;
                                }
                                if (n9 < 0) {
                                    n9 = 0;
                                }
                                n4 = (n5 + -n9) * (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4051.method641((byte)127) * class338.anInt2841) / n5 >> 420110114;
                                if (class338.aClass246_Sub3_2834 != null && class338.aClass246_Sub3_2834 instanceof Class246_Sub3_Sub4) {
                                    final Class246_Sub3_Sub4 class246_Sub3_Sub4 = (Class246_Sub3_Sub4)class338.aClass246_Sub3_2834;
                                    final short aShort6157 = class246_Sub3_Sub4.aShort6157;
                                    final short aShort6158 = class246_Sub3_Sub4.aShort6158;
                                }
                                if (n7 != 0 || ~n8 != -1) {
                                    int n10 = 0x3FFF & -4096 + -Class186.anInt3424 - (int)(Math.atan2(n7, n8) * 2607.5945876176133);
                                    if (n10 > 8192) {
                                        n10 = -n10 + 16384;
                                    }
                                    int n11;
                                    if (~n9 >= -1) {
                                        n11 = 8192;
                                    }
                                    else if (n9 >= 4096) {
                                        n11 = 16384;
                                    }
                                    else {
                                        n11 = (-n9 + 8192) / 4096 + 8192;
                                    }
                                    n3 = n11 * n10 / 8192 - -(-n11 + 16384 >> 1231343681);
                                }
                            }
                            if (~n4 < -1) {
                                Class98_Sub24_Sub1 class98_Sub24_Sub1 = null;
                                if (~class338.aByte2840 == 0xFFFFFFFE) {
                                    class98_Sub24_Sub1 = class338.aClass37_2835.method344().method1269(Class148.aClass314_1195);
                                }
                                else if (class338.method3782(n + 21637)) {
                                    class98_Sub24_Sub1 = class338.aClass98_Sub24_Sub1_2839;
                                }
                                final Class338 class341 = class338;
                                final Class98_Sub31_Sub5 method1402 = Class98_Sub31_Sub5.method1402(class98_Sub24_Sub1, class338.anInt2843, n4, n3);
                                class341.aClass98_Sub31_Sub5_2836 = method1402;
                                final Class98_Sub31_Sub5 class98_Sub31_Sub5 = method1402;
                                class98_Sub31_Sub5.method1422(-1 + class338.anInt2831);
                                Class81.aClass98_Sub31_Sub3_619.method1371(class98_Sub31_Sub5);
                            }
                        }
                    }
                }
                else if (!class338.aClass98_Sub31_Sub5_2836.method941((byte)78)) {
                    b = true;
                }
                if (b) {
                    --Class306.anInt2566;
                    for (int n12 = n2; ~Class306.anInt2566 < ~n12; ++n12) {
                        Class245.aClass338Array1865[n12] = Class245.aClass338Array1865[n12 + 1];
                    }
                    --n2;
                }
            }
            if (Class151_Sub5.aBoolean4991 && !Class8.method188(false)) {
                if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)120) != 0 && ~Class144.anInt1169 != 0x0) {
                    if (Class151_Sub8.aClass98_Sub31_Sub2_5013 == null) {
                        s_Sub1.method3434(Class98_Sub10_Sub1.aClass207_5544, false, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)123), Class144.anInt1169, 0, -16523);
                    }
                    else {
                        Class372.method3959(256, Class144.anInt1169, Class151_Sub8.aClass98_Sub31_Sub2_5013, Class98_Sub10_Sub1.aClass207_5544, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)122), 0, false);
                    }
                }
                Class151_Sub5.aBoolean4991 = false;
                Class151_Sub8.aClass98_Sub31_Sub2_5013 = null;
            }
            else if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)126) != -1 && Class144.anInt1169 != -1 && !Class8.method188(false)) {
                final Class98_Sub11 method1403 = Class246_Sub3_Sub4.method3023(260, Class140.aClass171_3248, Class331.aClass117_2811);
                method1403.aClass98_Sub22_Sub1_3865.writeInt(n ^ 0xA24F121F, Class144.anInt1169);
                Class98_Sub10_Sub29.sendPacket(false, method1403);
                Class144.anInt1169 = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.IA(" + n + ')');
        }
    }
    
    @Override
    final boolean method2991(final boolean b) {
        try {
            if (b) {
                Class246_Sub3_Sub2.aClass58_6151 = null;
            }
            return Class74.aBooleanArrayArray551[Class259.anInt1959 + ((super.anInt5084 >> Class151_Sub8.anInt5015) + -Class241.anInt1845)][Class259.anInt1959 + -Class64_Sub26.anInt3714 + (super.anInt5079 >> Class151_Sub8.anInt5015)];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.FA(" + b + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2977(final ha ha, final byte b) {
        try {
            if (b != 77) {
                this.method2981(null, (byte)76, true, 85, null, -57, 85);
            }
            final Class154 method914 = Class94.method914(super.aByte5088, super.anInt5084 >> Class151_Sub8.anInt5015, super.anInt5079 >> Class151_Sub8.anInt5015);
            if (method914 == null || !method914.aClass246_Sub3_Sub4_1232.aBoolean6162) {
                return Class76_Sub5.method758((byte)103, super.aByte5088, super.anInt5079 >> Class151_Sub8.anInt5015, super.anInt5084 >> Class151_Sub8.anInt5015);
            }
            return Class195.method2661(super.aByte5088, super.anInt5079 >> Class151_Sub8.anInt5015, super.anInt5084 >> Class151_Sub8.anInt5015, method914.aClass246_Sub3_Sub4_1232.method2990(0) + this.method2990(b ^ 0x4D), (byte)(-118));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.AA(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method3004(final int n) {
        try {
            if (n < -117) {
                Class246_Sub3_Sub2.aClass58_6151 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.EA(" + n + ')');
        }
    }
    
    static final void method3005(final int n) {
        try {
            final int length = Class105.aByteArrayArray3414.length;
            int n2 = 0;
            if (n != 21378) {
                method3005(36);
            }
            while (~n2 > ~length) {
                if (Class105.aByteArrayArray3414[n2] != null) {
                    int n3 = -1;
                    for (int n4 = 0; ~n4 > ~Class254.anInt1944; ++n4) {
                        if (~Class121.anIntArray1006[n2] == ~Class213.anIntArray1607[n4]) {
                            n3 = n4;
                            break;
                        }
                    }
                    if (n3 == -1) {
                        Class213.anIntArray1607[Class254.anInt1944] = Class121.anIntArray1006[n2];
                        n3 = Class254.anInt1944++;
                    }
                    final Class98_Sub22 class98_Sub22 = new Class98_Sub22(Class105.aByteArrayArray3414[n2]);
                    int n5 = 0;
                    while (~Class105.aByteArrayArray3414[n2].length < ~class98_Sub22.anInt3991 && n5 < 511) {
                        if (Class150.anInt1211 >= 1023) {
                            break;
                        }
                        final int anInt6369 = n5++ << 934686470 | n3;
                        final int short1 = class98_Sub22.readShort((byte)127);
                        final int n6 = short1 >> 1106851790;
                        final int n7 = (short1 & 0x1FD6) >> 62218407;
                        final int n8 = 0x3F & short1;
                        final int n9 = n7 + (Class121.anIntArray1006[n2] >> -1827624344) * 64 - Class272.anInt2038;
                        final int n10 = -aa_Sub2.anInt3562 + (64 * (0xFF & Class121.anIntArray1006[n2]) - -n8);
                        final Class141 method3538 = Class4.aClass301_85.method3538(n - 21373, class98_Sub22.readShort((byte)127));
                        if (Class260.aClass377_3254.method3990(anInt6369, n ^ 0xFFFFAC7D) != null || (method3538.aByte1103 & 0x1) <= 0 || ~n6 != ~Class115.anInt963 || n9 < 0 || Class165.anInt1276 <= n9 + method3538.anInt1112 || n10 < 0 || ~Class98_Sub10_Sub7.anInt5572 >= ~(method3538.anInt1112 + n10)) {
                            continue;
                        }
                        final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1 = new Class246_Sub3_Sub4_Sub2_Sub1();
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6369 = anInt6369;
                        final Class98_Sub39 class98_Sub23 = new Class98_Sub39(class246_Sub3_Sub4_Sub2_Sub1);
                        Class260.aClass377_3254.method3996(class98_Sub23, anInt6369, n - 21379);
                        Class163.aClass98_Sub39Array3516[Class98_Sub10_Sub20.anInt5640++] = class98_Sub23;
                        Class325.anIntArray2726[Class150.anInt1211++] = anInt6369;
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6406 = Class215.anInt1614;
                        class246_Sub3_Sub4_Sub2_Sub1.method3054(method3538, 1);
                        class246_Sub3_Sub4_Sub2_Sub1.method3045((byte)70, class246_Sub3_Sub4_Sub2_Sub1.aClass141_6504.anInt1112);
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6414 = class246_Sub3_Sub4_Sub2_Sub1.aClass141_6504.anInt1091 << -1695734205;
                        class246_Sub3_Sub4_Sub2_Sub1.method3047(class246_Sub3_Sub4_Sub2_Sub1.aClass141_6504.aByte1141 + 4 << 907165931 & 0x3FDA, true, 16);
                        class246_Sub3_Sub4_Sub2_Sub1.method3049(class246_Sub3_Sub4_Sub2_Sub1.method3034(0), n9, true, (byte)(-122), n10, n6);
                    }
                }
                ++n2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.BA(" + n + ')');
        }
    }
    
    Class246_Sub3_Sub2(final int anInt5084, final int anInt5085, final int anInt5086, final int n, final int n2) {
        try {
            super.anInt5089 = anInt5085;
            super.anInt5079 = anInt5086;
            super.aByte5081 = (byte)n2;
            super.anInt5084 = anInt5084;
            super.aByte5088 = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.<init>(" + anInt5084 + ',' + anInt5085 + ',' + anInt5086 + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                Class246_Sub3_Sub2.aClass58_6151 = null;
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.DA(" + b + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b >= -70) {
                Class246_Sub3_Sub2.aClass58_6151 = null;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.HA(" + b + ')');
        }
    }
    
    @Override
    final int method2980(final int n, final Class98_Sub5[] array) {
        try {
            return this.method2989(super.anInt5084 >> Class151_Sub8.anInt5015, false, array, super.anInt5079 >> Class151_Sub8.anInt5015);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ic.GA(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub2.aClass58_6151 = new IncomingOpcode(46, 2);
    }
}
