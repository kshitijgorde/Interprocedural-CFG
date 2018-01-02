// 
// Decompiled by Procyon v0.5.30
// 

final class Class222
{
    static boolean aBoolean1667;
    int anInt1668;
    static OutgoingOpcode aClass171_1669;
    static int anInt1670;
    static float[] aFloatArray1671;
    static int anInt1672;
    
    static final int method2824(final byte b, final Class98_Sub46_Sub8 class98_Sub46_Sub8) {
        try {
            String s = Class86.method845((byte)(-124), class98_Sub46_Sub8);
            int[] array = null;
            if (Class299_Sub2.method3526(124, class98_Sub46_Sub8.anInt5990)) {
                array = Class98_Sub46_Sub19.aClass205_6068.method2714((int)class98_Sub46_Sub8.aLong5987, (byte)(-128)).anIntArray2436;
            }
            else if (~class98_Sub46_Sub8.anInt5988 != 0x0) {
                array = Class98_Sub46_Sub19.aClass205_6068.method2714(class98_Sub46_Sub8.anInt5988, (byte)(-125)).anIntArray2436;
            }
            else if (!Class36.method340(class98_Sub46_Sub8.anInt5990, (byte)(-70))) {
                if (Class98_Sub10_Sub21.method1064(class98_Sub46_Sub8.anInt5990, false)) {
                    Class352 class352;
                    if (class98_Sub46_Sub8.anInt5990 == 1009) {
                        class352 = Class130.aClass302_1028.method3546((int)class98_Sub46_Sub8.aLong5987, (byte)119);
                    }
                    else {
                        class352 = Class130.aClass302_1028.method3546((int)(class98_Sub46_Sub8.aLong5987 >>> 762669664 & 0x7FFFFFFFL), (byte)119);
                    }
                    if (class352.anIntArray2928 != null) {
                        class352 = class352.method3852(Class75.aClass140_584, (byte)(-38));
                    }
                    if (class352 != null) {
                        array = class352.anIntArray2934;
                    }
                }
            }
            else {
                final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990((int)class98_Sub46_Sub8.aLong5987, -1);
                if (class98_Sub39 != null) {
                    Class141 class353 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504;
                    if (class353.anIntArray1109 != null) {
                        class353 = class353.method2300(Class75.aClass140_584, (byte)55);
                    }
                    if (class353 != null) {
                        array = class353.anIntArray1152;
                    }
                }
            }
            if (array != null) {
                s += Class64_Sub25.method653(0, array);
            }
            int method2676 = Class42_Sub1.aClass197_5354.method2676((byte)(-126), Class217.aClass332Array3408, s);
            if (class98_Sub46_Sub8.aBoolean5983) {
                method2676 += Class284_Sub2_Sub2.aClass332_6199.method3734() + 4;
            }
            return method2676;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oe.D(" + b + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method2825(final int n, final int n2) {
        try {
            if (n >= -31) {
                return -69;
            }
            return (n2 * (n2 * n2 >> -1546827604) >> -2091093524) * ((n2 * (n2 * 6 - 61440) >> -1680263604) + 40960) >> -94375156;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oe.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method2826(final Class246_Sub3_Sub4 class246_Sub3_Sub4, final boolean b) {
        final boolean b2 = Class78.aSArray594 == Class81.aSArray618;
        int n = 0;
        short n2 = 0;
        byte b3 = 0;
        class246_Sub3_Sub4.method3022(-8675);
        if (class246_Sub3_Sub4.aShort6158 < 0 || class246_Sub3_Sub4.aShort6157 < 0 || class246_Sub3_Sub4.aShort6160 >= Class366.anInt3112 || class246_Sub3_Sub4.aShort6159 >= Class64_Sub9.anInt3662) {
            return false;
        }
        short aShort6149 = 0;
        for (short aShort6150 = class246_Sub3_Sub4.aShort6158; aShort6150 <= class246_Sub3_Sub4.aShort6160; ++aShort6150) {
            for (short aShort6151 = class246_Sub3_Sub4.aShort6157; aShort6151 <= class246_Sub3_Sub4.aShort6159; ++aShort6151) {
                final Class172 method1693 = Class100.method1693(class246_Sub3_Sub4.aByte5088, aShort6150, aShort6151);
                if (method1693 != null) {
                    final Class154 method1694 = Class299_Sub2.method3524((byte)(-90), class246_Sub3_Sub4);
                    Class154 class154 = method1693.aClass154_1325;
                    if (class154 == null) {
                        method1693.aClass154_1325 = method1694;
                    }
                    else {
                        while (class154.aClass154_1233 != null) {
                            class154 = class154.aClass154_1233;
                        }
                        class154.aClass154_1233 = method1694;
                    }
                    if (b2 && (Class40.anIntArrayArray367[aShort6150][aShort6151] & 0xFF000000) != 0x0) {
                        n = Class40.anIntArrayArray367[aShort6150][aShort6151];
                        n2 = Class304.aShortArrayArray2534[aShort6150][aShort6151];
                        b3 = Class299_Sub2.aByteArrayArray5291[aShort6150][aShort6151];
                    }
                    if (!b && method1693.aClass246_Sub3_Sub1_1332 != null && method1693.aClass246_Sub3_Sub1_1332.aShort6149 > aShort6149) {
                        aShort6149 = method1693.aClass246_Sub3_Sub1_1332.aShort6149;
                    }
                }
            }
        }
        if (b2 && (n & 0xFF000000) != 0x0) {
            for (short aShort6152 = class246_Sub3_Sub4.aShort6158; aShort6152 <= class246_Sub3_Sub4.aShort6160; ++aShort6152) {
                for (short aShort6153 = class246_Sub3_Sub4.aShort6157; aShort6153 <= class246_Sub3_Sub4.aShort6159; ++aShort6153) {
                    if ((Class40.anIntArrayArray367[aShort6152][aShort6153] & 0xFF000000) == 0x0) {
                        Class40.anIntArrayArray367[aShort6152][aShort6153] = n;
                        Class304.aShortArrayArray2534[aShort6152][aShort6153] = n2;
                        Class299_Sub2.aByteArrayArray5291[aShort6152][aShort6153] = b3;
                    }
                }
            }
        }
        if (b) {
            Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[Class347.anInt2907++] = class246_Sub3_Sub4;
        }
        else {
            final int n3 = (Class78.aSArray594 == Class81.aSArray618) ? 1 : 0;
            if (class246_Sub3_Sub4.method2978(-127)) {
                if (class246_Sub3_Sub4.method2987(6540)) {
                    class246_Sub3_Sub4.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n3];
                    Class359.aClass246_Sub3Array3056[n3] = class246_Sub3_Sub4;
                }
                else {
                    class246_Sub3_Sub4.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n3];
                    Class379.aClass246_Sub3Array3198[n3] = class246_Sub3_Sub4;
                    Class358.aBoolean3033 = true;
                }
            }
            else {
                class246_Sub3_Sub4.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n3];
                Class130.aClass246_Sub3Array1029[n3] = class246_Sub3_Sub4;
            }
        }
        if (b) {
            class246_Sub3_Sub4.anInt5089 -= aShort6149;
        }
        return true;
    }
    
    public static void method2827(final byte b) {
        try {
            if (b > -7) {
                method2826(null, true);
            }
            Class222.aClass171_1669 = null;
            Class222.aFloatArray1671 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oe.A(" + b + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oe.toString()");
        }
    }
    
    Class222(final int anInt1668, final int n) {
        try {
            this.anInt1668 = anInt1668;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oe.<init>(" + anInt1668 + ',' + n + ')');
        }
    }
    
    static {
        Class222.aBoolean1667 = false;
        Class222.aClass171_1669 = new OutgoingOpcode(22, 7);
        Class222.anInt1670 = -1;
        Class222.aFloatArray1671 = new float[] { 0.0f, -1.0f, 0.0f, 0.0f };
    }
}
