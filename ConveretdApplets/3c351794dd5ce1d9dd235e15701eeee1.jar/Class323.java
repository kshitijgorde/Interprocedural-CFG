// 
// Decompiled by Procyon v0.5.30
// 

final class Class323
{
    static Class128 aClass128_2715;
    static Class207 aClass207_2716;
    
    static final void method3675(final int n, final int anInt6054, final boolean b, final int anInt6055, final int anInt6056) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -40, 10);
            if (!b) {
                Class323.aClass207_2716 = null;
            }
            method2628.method1626((byte)(-103));
            method2628.anInt6051 = anInt6055;
            method2628.anInt6054 = anInt6054;
            method2628.anInt6053 = anInt6056;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tt.D(" + n + ',' + anInt6054 + ',' + b + ',' + anInt6055 + ',' + anInt6056 + ')');
        }
    }
    
    static final void method3676(final int n, final int n2, final int n3, int n4, final byte[] array, final boolean b, int n5, final byte[] array2, int n6) {
        try {
            if (!b) {
                final int n7 = -(n4 >> -1495816926);
                n4 = -(n4 & 0x3);
                for (int i = -n; i < 0; ++i) {
                    for (int n8 = n7; ~n8 > -1; ++n8) {
                        final int n9 = n6++;
                        array[n9] += (byte)(-array2[n5++]);
                        final int n10 = n6++;
                        array[n10] += (byte)(-array2[n5++]);
                        final int n11 = n6++;
                        array[n11] += (byte)(-array2[n5++]);
                        final int n12 = n6++;
                        array[n12] += (byte)(-array2[n5++]);
                    }
                    for (int j = n4; j < 0; ++j) {
                        final int n13 = n6++;
                        array[n13] += (byte)(-array2[n5++]);
                    }
                    n5 += n3;
                    n6 += n2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tt.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n5 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    static final Class98_Sub18 method3677(final boolean b, final int n, final int anInt3945, final int n2, final int anInt3946) {
        try {
            final Class98_Sub18 class98_Sub18 = new Class98_Sub18();
            class98_Sub18.anInt3947 = anInt3946;
            class98_Sub18.anInt3945 = anInt3945;
            if (n >= -125) {
                return null;
            }
            Class116.aClass377_964.method3996(class98_Sub18, n2, -1);
            Class98_Sub46_Sub15.method1609(anInt3945, -12889);
            final Class293 method2509 = Class159.method2509(n2, -9820);
            if (method2509 != null) {
                Class341.method3812(1, method2509);
            }
            if (OutputStream_Sub1.aClass293_33 != null) {
                Class341.method3812(1, OutputStream_Sub1.aClass293_33);
                OutputStream_Sub1.aClass293_33 = null;
            }
            Class230.method2869(44);
            if (method2509 != null) {
                Class63.method549(method2509, !b, (byte)66);
            }
            if (!b) {
                Class247.method3155(anInt3945);
            }
            if (!b && Class15.anInt185 != -1) {
                Class207.method2764(1, Class15.anInt185, -121);
            }
            return class98_Sub18;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tt.C(" + b + ',' + n + ',' + anInt3945 + ',' + n2 + ',' + anInt3946 + ')');
        }
    }
    
    static final int method3678(final byte b, final Class38 class38) {
        try {
            if (b != 115) {
                return -85;
            }
            if (class38 == Class98.aClass38_834) {
                return 9216;
            }
            if (class38 == Class357.aClass38_3026) {
                return 34065;
            }
            if (class38 == Class204.aClass38_1552) {
                return 34066;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tt.E(" + b + ',' + ((class38 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3679(final int n) {
        try {
            Class323.aClass128_2715 = null;
            Class323.aClass207_2716 = null;
            if (n != -1443) {
                method3677(true, -27, 83, -34, -122);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tt.A(" + n + ')');
        }
    }
    
    static {
        Class323.aClass128_2715 = new Class128();
    }
}
