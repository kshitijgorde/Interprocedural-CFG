// 
// Decompiled by Procyon v0.5.30
// 

final class Class256_Sub1 extends Class256
{
    static int[] anIntArray5156;
    static String aString5157;
    static int[] anIntArray5158;
    Class49[] aClass49Array5159;
    
    public static void method3194(final int n) {
        try {
            if (n != 0) {
                method3195(116, null);
            }
            Class256_Sub1.aString5157 = null;
            Class256_Sub1.anIntArray5156 = null;
            Class256_Sub1.anIntArray5158 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "su.D(" + n + ')');
        }
    }
    
    static final boolean method3195(final int n, final String s) {
        try {
            if (s == null) {
                return false;
            }
            for (int i = n; i < Class314.anInt2692; ++i) {
                if (s.equalsIgnoreCase(Class98_Sub25.aStringArray4026[i])) {
                    return true;
                }
            }
            return s.equalsIgnoreCase(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "su.B(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class98_Sub47 method3196(final byte b) {
        try {
            if (Class278.aClass148_2065 == null || Class98_Sub5.aClass157_3835 == null) {
                return null;
            }
            Class98_Sub5.aClass157_3835.method2505((byte)26, Class278.aClass148_2065);
            final Class98_Sub47 class98_Sub47 = (Class98_Sub47)Class98_Sub5.aClass157_3835.method2504((byte)(-116));
            if (class98_Sub47 == null) {
                return null;
            }
            final Class24 method3807 = Class278.aClass341_2057.method3807(-88, class98_Sub47.anInt4268);
            if (b >= -68) {
                method3196((byte)(-25));
            }
            if (method3807 != null && method3807.aBoolean241 && method3807.method284(124, Class278.anInterface6_2060)) {
                return class98_Sub47;
            }
            return Class246_Sub3.method2979(-76);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "su.C(" + b + ')');
        }
    }
    
    Class256_Sub1(final Class49[] aClass49Array5159) {
        try {
            this.aClass49Array5159 = aClass49Array5159;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "su.<init>(" + ((aClass49Array5159 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class256_Sub1.anIntArray5156 = new int[14];
        Class256_Sub1.anIntArray5158 = new int[25];
    }
}
