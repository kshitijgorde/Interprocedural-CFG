// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class48_Sub2 extends Class48
{
    abstract Interface4_Impl3 method469(final int p0);
    
    static final void method470(final byte[][] array, final Class305_Sub1 class305_Sub1, final int n) {
        try {
            if (n != -4789) {
                method470(null, null, -69);
            }
            for (int n2 = 0; ~n2 > ~class305_Sub1.anInt2547; ++n2) {
                Class128.method2224(n ^ 0xFFFFB5E3);
                for (int i = 0; i < Class165.anInt1276 >> -1696914461; ++i) {
                    for (int j = 0; j < Class98_Sub10_Sub7.anInt5572 >> -516098653; ++j) {
                        final int n3 = Class170.anIntArrayArrayArray1311[n2][i][j];
                        if (~n3 != 0x0) {
                            final int n4 = 0x3 & n3 >> -1093162696;
                            if (!class305_Sub1.aBoolean2544 || n4 == 0) {
                                final int n5 = 0x3 & n3 >> -610352415;
                                final int n6 = (n3 & 0xFFC290) >> -1877248338;
                                final int n7 = (n3 & 0x3FFC) >> 255846019;
                                final int n8 = (n6 / 8 << -1553791576) - -(n7 / 8);
                                for (int k = 0; k < Class121.anIntArray1006.length; ++k) {
                                    if (~n8 == ~Class121.anIntArray1006[k] && array[k] != null) {
                                        class305_Sub1.method3584(Class167.aClass243Array1281, n2, n4, array[k], j * 8, 8 * i, (n6 & 0x7) * 8, (0x7 & n7) * 8, n5, Class265.aHa1974, n ^ 0xFFFFED33);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jv.H(" + ((array != null) ? "{...}" : "null") + ',' + ((class305_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
}
