// 
// Decompiled by Procyon v0.5.30
// 

final class Class187
{
    static Class143 aClass143_1449;
    static int anInt1450;
    static boolean aBoolean1451;
    
    static final void method2634(final int n, final Class293[] array, final int n2) {
        try {
            for (int n3 = n; ~array.length < ~n3; ++n3) {
                final Class293 class293 = array[n3];
                if (class293 != null) {
                    if (~class293.anInt2354 == -1) {
                        if (class293.aClass293Array2339 != null) {
                            method2634(n, class293.aClass293Array2339, n2);
                        }
                        final Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3990(class293.anInt2248, -1);
                        if (class98_Sub18 != null) {
                            Class207.method2764(n2, class98_Sub18.anInt3945, -105);
                        }
                    }
                    if (~n2 == -1 && class293.anObjectArray2330 != null) {
                        final Class98_Sub21 class98_Sub19 = new Class98_Sub21();
                        class98_Sub19.anObjectArray3981 = class293.anObjectArray2330;
                        class98_Sub19.aClass293_3986 = class293;
                        Class247.method3144(class98_Sub19);
                    }
                    if (~n2 == 0xFFFFFFFE && class293.anObjectArray2319 != null) {
                        if (~class293.anInt2300 <= -1) {
                            final Class293 method2509 = Class159.method2509(class293.anInt2248, -9820);
                            if (method2509 == null || method2509.aClass293Array2339 == null || ~method2509.aClass293Array2339.length >= ~class293.anInt2300) {
                                continue;
                            }
                            if (method2509.aClass293Array2339[class293.anInt2300] != class293) {
                                continue;
                            }
                        }
                        final Class98_Sub21 class98_Sub20 = new Class98_Sub21();
                        class98_Sub20.aClass293_3986 = class293;
                        class98_Sub20.anObjectArray3981 = class293.anObjectArray2319;
                        Class247.method3144(class98_Sub20);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mh.A(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    public static void method2635(final int n) {
        try {
            if (n >= 99) {
                Class187.aClass143_1449 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mh.D(" + n + ')');
        }
    }
    
    static final boolean method2636(final int n, final int n2, final int n3) {
        try {
            return n3 != 3 || (Class151_Sub2.method2451(n, 544, n2) | ~(n & 0x10000) != -1) || Class64_Sub28.method670(n, -12294, n2) || (~(0x37 & n2) == -1 && Class228.method2864(55, n, n2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mh.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2637(final int n, int n2, int n3, final byte[] array, final byte[] array2, final int n4, final int n5, final int n6, int n7) {
        try {
            final int n8 = -(n7 >> 14184194);
            n7 = -(0x3 & n7);
            if (n4 != -18305) {
                method2634(8, null, 105);
            }
            for (int n9 = -n; ~n9 > -1; ++n9) {
                for (int n10 = n8; ~n10 > -1; ++n10) {
                    final int n11 = n2++;
                    array2[n11] += (byte)(-array[n3++]);
                    final int n12 = n2++;
                    array2[n12] += (byte)(-array[n3++]);
                    final int n13 = n2++;
                    array2[n13] += (byte)(-array[n3++]);
                    final int n14 = n2++;
                    array2[n14] += (byte)(-array[n3++]);
                }
                for (int n15 = n7; ~n15 > -1; ++n15) {
                    final int n16 = n2++;
                    array2[n16] += (byte)(-array[n3++]);
                }
                n3 += n5;
                n2 += n6;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mh.C(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static {
        Class187.aBoolean1451 = false;
    }
}
