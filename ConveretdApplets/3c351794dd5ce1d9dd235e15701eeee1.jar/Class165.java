// 
// Decompiled by Procyon v0.5.30
// 

final class Class165
{
    static int anInt1276;
    static int[] anIntArray1277;
    
    static final void method2523(final int n, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2) {
        try {
            final int n2 = -Class215.anInt1614 + class246_Sub3_Sub4_Sub2.anInt6390;
            final int n3 = class246_Sub3_Sub4_Sub2.anInt6378 * 512 + 256 * class246_Sub3_Sub4_Sub2.method3034(n ^ n);
            class246_Sub3_Sub4_Sub2.anInt5079 += (-class246_Sub3_Sub4_Sub2.anInt5079 + (class246_Sub3_Sub4_Sub2.anInt6347 * 512 + 256 * class246_Sub3_Sub4_Sub2.method3034(0))) / n2;
            class246_Sub3_Sub4_Sub2.anInt5084 += (-class246_Sub3_Sub4_Sub2.anInt5084 + n3) / n2;
            class246_Sub3_Sub4_Sub2.anInt6433 = 0;
            if (~class246_Sub3_Sub4_Sub2.anInt6407 == -1) {
                class246_Sub3_Sub4_Sub2.method3042(8192, n - 20481);
            }
            if (class246_Sub3_Sub4_Sub2.anInt6407 == 1) {
                class246_Sub3_Sub4_Sub2.method3042(12288, -8193);
            }
            if (class246_Sub3_Sub4_Sub2.anInt6407 == 2) {
                class246_Sub3_Sub4_Sub2.method3042(0, -8193);
            }
            if (class246_Sub3_Sub4_Sub2.anInt6407 == 3) {
                class246_Sub3_Sub4_Sub2.method3042(4096, -8193);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kv.A(" + n + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2524(final byte b) {
        try {
            if (b > -103) {
                method2524((byte)15);
            }
            Class165.anIntArray1277 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kv.B(" + b + ')');
        }
    }
    
    static {
        Class165.anInt1276 = 104;
        Class165.anIntArray1277 = new int[1];
    }
}
