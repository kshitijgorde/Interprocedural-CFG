// 
// Decompiled by Procyon v0.5.30
// 

final class Class303
{
    static Class13 aClass13_2529;
    static int anInt2530;
    static int[] anIntArray2531;
    
    static final void method3556(final Class332[] array, final int n) {
        try {
            RuntimeException_Sub1.anInt3201 = array.length;
            Class217.aClass332Array3408 = new Class332[10 + RuntimeException_Sub1.anInt3201];
            Class64_Sub5.anIntArray3652 = new int[RuntimeException_Sub1.anInt3201 + 10];
            Class236.method2892(array, 0, Class217.aClass332Array3408, n, RuntimeException_Sub1.anInt3201);
            for (int i = 0; i < RuntimeException_Sub1.anInt3201; ++i) {
                Class64_Sub5.anIntArray3652[i] = Class217.aClass332Array3408[i].method3749();
            }
            for (int j = RuntimeException_Sub1.anInt3201; j < Class217.aClass332Array3408.length; ++j) {
                Class64_Sub5.anIntArray3652[j] = 12;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sj.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3557(final int anInt3984, final int n, final String aString3978, final int n2, final int n3) {
        try {
            final Class293 method3139 = Class246_Sub9.method3139((byte)72, n3, n);
            if (method3139 != null) {
                if (method3139.anObjectArray2329 != null) {
                    final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                    class98_Sub21.anObjectArray3981 = method3139.anObjectArray2329;
                    class98_Sub21.aClass293_3986 = method3139;
                    class98_Sub21.anInt3984 = anInt3984;
                    class98_Sub21.aString3978 = aString3978;
                    Class247.method3144(class98_Sub21);
                }
                if (Class177.anInt1376 == 10 && client.method116(method3139).method1666((byte)(-72), -1 + anInt3984)) {
                    if (~anInt3984 == 0xFFFFFFFE) {
                        final Class98_Sub11 method3140 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub5_Sub1.aClass171_6221, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3140);
                        Class98_Sub10_Sub29.sendPacket(false, method3140);
                    }
                    if (anInt3984 == 2) {
                        final Class98_Sub11 method3141 = Class246_Sub3_Sub4.method3023(260, aa_Sub2.aClass171_3564, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3141);
                        Class98_Sub10_Sub29.sendPacket(false, method3141);
                    }
                    if (anInt3984 == 3) {
                        final Class98_Sub11 method3142 = Class246_Sub3_Sub4.method3023(260, Class98_Sub46_Sub9.aClass171_6000, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3142);
                        Class98_Sub10_Sub29.sendPacket(false, method3142);
                    }
                    if (~anInt3984 == 0xFFFFFFFB) {
                        final Class98_Sub11 method3143 = Class246_Sub3_Sub4.method3023(260, Class234.aClass171_1747, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3143);
                        Class98_Sub10_Sub29.sendPacket(false, method3143);
                    }
                    if (~anInt3984 == 0xFFFFFFFA) {
                        final Class98_Sub11 method3144 = Class246_Sub3_Sub4.method3023(260, aa.aClass171_49, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3144);
                        Class98_Sub10_Sub29.sendPacket(false, method3144);
                    }
                    if (n2 > -114) {
                        method3557(-106, 15, null, 96, 60);
                    }
                    if (~anInt3984 == 0xFFFFFFF9) {
                        final Class98_Sub11 method3145 = Class246_Sub3_Sub4.method3023(260, Class98_Sub1.aClass171_3811, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3145);
                        Class98_Sub10_Sub29.sendPacket(false, method3145);
                    }
                    if (~anInt3984 == 0xFFFFFFF8) {
                        final Class98_Sub11 method3146 = Class246_Sub3_Sub4.method3023(260, Class17.aClass171_202, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3146);
                        Class98_Sub10_Sub29.sendPacket(false, method3146);
                    }
                    if (~anInt3984 == 0xFFFFFFF7) {
                        final Class98_Sub11 method3147 = Class246_Sub3_Sub4.method3023(260, Class15.aClass171_177, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3147);
                        Class98_Sub10_Sub29.sendPacket(false, method3147);
                    }
                    if (~anInt3984 == 0xFFFFFFF6) {
                        final Class98_Sub11 method3148 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub2_Sub1.aClass171_6342, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3148);
                        Class98_Sub10_Sub29.sendPacket(false, method3148);
                    }
                    if (~anInt3984 == 0xFFFFFFF5) {
                        final Class98_Sub11 method3149 = Class246_Sub3_Sub4.method3023(260, Class13.aClass171_164, Class331.aClass117_2811);
                        Class180.method2604(n, -21568, n3, method3139.anInt2302, method3149);
                        Class98_Sub10_Sub29.sendPacket(false, method3149);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sj.B(" + anInt3984 + ',' + n + ',' + ((aString3978 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method3558(final int n) {
        try {
            Class303.aClass13_2529 = null;
            Class303.anIntArray2531 = null;
            if (n != 10) {
                Class303.anIntArray2531 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sj.C(" + n + ')');
        }
    }
    
    static {
        Class303.anIntArray2531 = new int[] { 28, 35, 40, 44 };
    }
}
