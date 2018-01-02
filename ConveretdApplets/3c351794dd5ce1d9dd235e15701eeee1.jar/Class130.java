// 
// Decompiled by Procyon v0.5.30
// 

final class Class130
{
    static Class302 aClass302_1028;
    static Class246_Sub3[] aClass246_Sub3Array1029;
    static Object anObject1030;
    static int anInt1031;
    
    static final boolean method2230(final int n, int n2, final int n3, final int n4, final int n5, final byte[] array, final int n6) {
        try {
            final int n7 = n5 % n;
            int n8;
            if (~n7 != -1) {
                n8 = n - n7;
            }
            else {
                n8 = 0;
            }
            final int n9 = -((-1 + n + n6) / n);
            final int n10 = -((n + (n5 - n3)) / n);
            for (int n11 = n9; ~n11 > -1; ++n11) {
                for (int n12 = n10; ~n12 > -1; ++n12) {
                    if (~array[n2] == -1) {
                        return true;
                    }
                    n2 += n;
                }
                n2 -= n8;
                if (array[-1 + n2] == 0) {
                    return true;
                }
                n2 += n4;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ip.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((array != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    public static void method2231(final int n) {
        try {
            Class130.aClass302_1028 = null;
            Class130.aClass246_Sub3Array1029 = null;
            Class130.anObject1030 = null;
            if (n != 0) {
                Class130.anInt1031 = 29;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ip.B(" + n + ')');
        }
    }
    
    static {
        Class130.anInt1031 = 0;
    }
}
