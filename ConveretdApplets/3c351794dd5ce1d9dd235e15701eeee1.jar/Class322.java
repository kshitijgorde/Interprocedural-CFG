// 
// Decompiled by Procyon v0.5.30
// 

final class Class322
{
    Interface8 anInterface8_2711;
    static float aFloat2712;
    static IncomingOpcode aClass58_2713;
    static Class207 aClass207_2714;
    
    public static void method3669(final int n) {
        try {
            Class322.aClass207_2714 = null;
            Class322.aClass58_2713 = null;
            Class322.aFloat2712 = -1.0144556f;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tr.F(" + n + ')');
        }
    }
    
    static final String method3670(final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            return Class98_Sub10_Sub26.method1084(false, class98_Sub22, 32767);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tr.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method3671(final int n, final int n2, final int n3, final Class246_Sub3_Sub1 aClass246_Sub3_Sub1_1332) {
        final Class172 method1693 = Class100.method1693(n, n2, n3);
        if (method1693 != null) {
            method1693.aClass246_Sub3_Sub1_1332 = aClass246_Sub3_Sub1_1332;
            final int n4 = (Class78.aSArray594 == Class81.aSArray618) ? 1 : 0;
            if (aClass246_Sub3_Sub1_1332.method2978(-127)) {
                if (aClass246_Sub3_Sub1_1332.method2987(6540)) {
                    aClass246_Sub3_Sub1_1332.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n4];
                    Class359.aClass246_Sub3Array3056[n4] = aClass246_Sub3_Sub1_1332;
                }
                else {
                    aClass246_Sub3_Sub1_1332.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4];
                    Class379.aClass246_Sub3Array3198[n4] = aClass246_Sub3_Sub1_1332;
                    Class358.aBoolean3033 = true;
                }
            }
            else {
                aClass246_Sub3_Sub1_1332.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n4];
                Class130.aClass246_Sub3Array1029[n4] = aClass246_Sub3_Sub1_1332;
            }
        }
    }
    
    static final boolean method3672(final int n, final int n2, final int n3) {
        try {
            return n3 != 2048 || (0x800 & n2) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tr.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method3673(final int n, final int n2, final int n3, final boolean b) {
        try {
            Class281.aByteArrayArrayArray2117 = new byte[n3][n2][n];
            if (!b) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tr.E(" + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    static final void method3674(final int n, final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2) {
        try {
            final Class98_Sub42 class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub14.aClass377_5612.method3990(class246_Sub3_Sub4_Sub2_Sub2.anInt6369, -1);
            if (class98_Sub42 == null) {
                Class98_Sub31_Sub4.method1383(class246_Sub3_Sub4_Sub2_Sub2, null, class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0], 0, n + 3, class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub2.aByte5088, null);
            }
            else {
                class98_Sub42.method1478(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tr.A(" + n + ',' + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class322.aClass58_2713 = new IncomingOpcode(50, 7);
    }
}
