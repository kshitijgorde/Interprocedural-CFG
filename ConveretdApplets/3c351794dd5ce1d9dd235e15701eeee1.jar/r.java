// 
// Decompiled by Procyon v0.5.30
// 

abstract class r extends Class98_Sub46
{
    static final void method1641(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            final int method3219 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n7);
            final int method3220 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n2);
            final int method3221 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3);
            final int method3222 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5);
            final int method3223 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, n7 + n4);
            final int i = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, -n4 + n2);
            for (int n9 = method3219; ~method3223 < ~n9; ++n9) {
                Class333.method3761(n, Class97.anIntArrayArray814[n9], method3221, method3222, (byte)103);
            }
            if (n6 == -18907) {
                for (int n10 = method3220; ~n10 < ~i; --n10) {
                    Class333.method3761(n, Class97.anIntArrayArray814[n10], method3221, method3222, (byte)126);
                }
                final int method3224 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 - -n4);
                final int method3225 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 - n4);
                for (int n11 = method3223; i >= n11; ++n11) {
                    final int[] array = Class97.anIntArrayArray814[n11];
                    Class333.method3761(n, array, method3221, method3224, (byte)112);
                    Class333.method3761(n8, array, method3224, method3225, (byte)(-128));
                    Class333.method3761(n, array, method3225, method3222, (byte)3);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "r.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
}
