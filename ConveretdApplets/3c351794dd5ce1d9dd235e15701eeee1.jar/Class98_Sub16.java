// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub16 extends Class98
{
    Class98_Sub44 aClass98_Sub44_3918;
    int anInt3919;
    int anInt3920;
    int anInt3921;
    int anInt3922;
    int anInt3923;
    int anInt3924;
    int anInt3925;
    int anInt3926;
    static int anInt3927;
    static int[] anIntArray3928;
    int anInt3929;
    int anInt3930;
    int anInt3931;
    int anInt3932;
    static int[] anIntArray3933;
    Class98_Sub24_Sub1 aClass98_Sub24_Sub1_3934;
    Class89 aClass89_3935;
    int anInt3936;
    int anInt3937;
    int anInt3938;
    Class98_Sub31_Sub5 aClass98_Sub31_Sub5_3939;
    int anInt3940;
    int anInt3941;
    
    public static void method1146(final boolean b) {
        try {
            Class98_Sub16.anIntArray3933 = null;
            Class98_Sub16.anIntArray3928 = null;
            if (b) {
                Class98_Sub16.anIntArray3933 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hea.D(" + b + ')');
        }
    }
    
    static final void method1147(final int n, final int n2) {
        try {
            Class185.method2628(n2, -126, 6).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hea.E(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1148(final int n) {
        try {
            this.aClass98_Sub31_Sub5_3939 = null;
            this.aClass98_Sub44_3918 = null;
            this.aClass98_Sub24_Sub1_3934 = null;
            this.aClass89_3935 = null;
            if (n != -1) {
                method1146(false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hea.A(" + n + ')');
        }
    }
    
    static final int method1149(final boolean b, final Class199 class199, final ha ha) {
        try {
            if (b) {
                Class98_Sub16.anIntArray3933 = null;
            }
            if (class199.anInt1540 != -1) {
                return class199.anInt1540;
            }
            if (class199.anInt1542 != -1) {
                final Class238 method11 = ha.aD938.method11(class199.anInt1542, -28755);
                if (!method11.aBoolean1825) {
                    return method11.aShort1831;
                }
            }
            return class199.anInt1537;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hea.B(" + b + ',' + ((class199 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method1150(final int n, final int n2, final int n3, final int n4, boolean b) {
        try {
            Class230.method2871(n3 - 117);
            Class98_Sub10_Sub25.aLong5677 = 0L;
            final int method2391 = Class146_Sub2.method2391((byte)(-68));
            if (n == n3 || ~method2391 == 0xFFFFFFFC) {
                b = true;
            }
            if (!Class265.aHa1974.method1800()) {
                b = true;
            }
            Class93_Sub1_Sub1.method909(n, n3 - 29761, b, method2391, n2, n4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hea.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub16.anIntArray3928 = new int[32];
        Class98_Sub16.anIntArray3933 = new int[4];
    }
}
