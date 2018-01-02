// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub11 extends Class98
{
    OutgoingOpcode aClass171_3864;
    Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3865;
    static Class148 aClass148_3866;
    int anInt3867;
    static Class85 aClass85_3868;
    int anInt3869;
    static Class98_Sub46_Sub16[] aClass98_Sub46_Sub16Array3870;
    
    public static void method1123(final byte b) {
        try {
            if (b != -90) {
                method1124(-18, (byte)126);
            }
            Class98_Sub11.aClass148_3866 = null;
            Class98_Sub11.aClass98_Sub46_Sub16Array3870 = null;
            Class98_Sub11.aClass85_3868 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eda.A(" + b + ')');
        }
    }
    
    static final void method1124(final int n, final byte b) {
        try {
            Class232.aClass79_1740.method800((byte)62, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eda.E(" + n + ',' + b + ')');
        }
    }
    
    final void method1125(final byte b) {
        try {
            if (~Class98_Sub33.anInt4117 > ~Class98_Sub46_Sub2_Sub2.aClass98_Sub11Array6302.length) {
                if (b != 6) {
                    method1124(-24, (byte)(-11));
                }
                Class98_Sub46_Sub2_Sub2.aClass98_Sub11Array6302[Class98_Sub33.anInt4117++] = this;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eda.C(" + b + ')');
        }
    }
    
    static final void method1126(final boolean b, final short[] array, final String[] array2) {
        try {
            Class260.method3207(47, array, array2, array2.length - 1, 0);
            if (!b) {
                method1123((byte)9);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eda.B(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method1127(final byte b, final int n) {
        try {
            final Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3990(n, b - 68);
            if (class98_Sub36 != null) {
                class98_Sub36.aClass237_Sub1_4157.method2903(8);
                Class291.method3414(-1, class98_Sub36.aBoolean4154, class98_Sub36.anInt4160);
                class98_Sub36.method942(69);
            }
            if (b != 67) {
                method1126(true, null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eda.D(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub11.aClass148_3866 = new Class148();
        Class98_Sub11.aClass98_Sub46_Sub16Array3870 = new Class98_Sub46_Sub16[14];
        Class98_Sub11.aClass85_3868 = new Class85(10, 7);
    }
}
