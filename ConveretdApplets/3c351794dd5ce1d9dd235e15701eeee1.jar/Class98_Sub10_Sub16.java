// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub16 extends Class98_Sub10
{
    static Class29 aClass29_5620;
    static Class85 aClass85_5621;
    
    public Class98_Sub10_Sub16() {
        super(1, true);
    }
    
    public static void method1051(final boolean b) {
        try {
            if (b) {
                method1052(-69, -23, -110, null, -114, 47, 46);
            }
            Class98_Sub10_Sub16.aClass85_5621 = null;
            Class98_Sub10_Sub16.aClass29_5620 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ir.D(" + b + ')');
        }
    }
    
    static final Class146 method1052(final int n, final int n2, final int n3, final ha ha, final int n4, final int n5, final int n6) {
        try {
            final long n7 = n4;
            Class146 method1790 = (Class146)Class246_Sub4_Sub1.aClass79_6170.method802(-126, n7);
            final int n8 = 2055;
            if (method1790 == null) {
                final Class178 method1791 = Class98_Sub6.method981(0, -9252, Class76_Sub9.aClass207_3787, n4);
                if (method1791 == null) {
                    return null;
                }
                if (~method1791.anInt1387 > -14) {
                    method1791.method2592(13746, 2);
                }
                method1790 = ha.method1790(method1791, n8, Class98_Sub10_Sub13.anInt5600, 64, 768);
                Class246_Sub4_Sub1.aClass79_6170.method805(n7, method1790, (byte)(-80));
            }
            final Class146 method1792 = method1790.method2341((byte)2, n8, true);
            if (n2 != 0) {
                method1792.a(n2);
            }
            if (n3 != 0) {
                method1792.FA(n3);
            }
            if (~n != -1) {
                method1792.VA(n);
            }
            if (n5 < 68) {
                method1052(80, 14, 63, null, -118, 89, 104);
            }
            if (n6 != 0) {
                method1792.H(0, n6, 0);
            }
            return method1792;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ir.B(" + n + ',' + n2 + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                Class98_Sub10_Sub16.aClass85_5621 = null;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int[][] method238 = this.method994(n2, 24431, 0);
                final int[] array = method238[0];
                final int[] array2 = method238[1];
                final int[] array3 = method238[2];
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    method237[n3] = (array[n3] + (array2[n3] - -array3[n3])) / 3;
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ir.G(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub16.aClass85_5621 = new Class85(6, 3);
    }
}
