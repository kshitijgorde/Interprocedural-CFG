// 
// Decompiled by Procyon v0.5.30
// 

final class Class239
{
    int anInt1838;
    static boolean aBoolean1839;
    static boolean aBoolean1840;
    static int anInt1841;
    private Class207 aClass207_1842;
    static int anInt1843;
    static int anInt1844;
    
    static final void method2921(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            if (n7 != 8) {
                method2921(-3, 122, -57, -125, 9, -70, 106);
            }
            if (~n6 == 0xFFFFFFF7 || n6 == 16) {
                if (n6 != 8) {
                    final int n8 = r_Sub2.anInt6333 + (n << Class151_Sub8.anInt5015);
                    final int n9 = -r_Sub2.anInt6333 + n8;
                    final int n10 = n3 << Class151_Sub8.anInt5015;
                    final int n11 = r_Sub2.anInt6333 + n10;
                    final int method3420 = Class98_Sub46_Sub2_Sub2.aSArray6298[n2].method3420(n3, n7 ^ 0xFFFFCEA9, 1 + n);
                    final int method3421 = Class98_Sub46_Sub2_Sub2.aSArray6298[n2].method3420(1 + n3, -12639, n);
                    Class98_Sub32_Sub1.aClass155Array5889[ha.anInt936++] = new Class155(n6, n2, n8, n9, n9, n8, method3420, method3421, -n5 + method3421, method3420 - n5, n10, n11, n11, n10);
                }
                else {
                    final int n12 = n << Class151_Sub8.anInt5015;
                    final int n13 = r_Sub2.anInt6333 + n12;
                    final int n14 = n3 << Class151_Sub8.anInt5015;
                    final int n15 = n14 - -r_Sub2.anInt6333;
                    final int method3422 = Class98_Sub46_Sub2_Sub2.aSArray6298[n2].method3420(n3, -12639, n);
                    final int method3423 = Class98_Sub46_Sub2_Sub2.aSArray6298[n2].method3420(n3 + 1, -12639, n + 1);
                    Class98_Sub32_Sub1.aClass155Array5889[ha.anInt936++] = new Class155(n6, n2, n12, n13, n13, n12, method3422, method3423, method3423 + -n5, method3422 + -n5, n14, n15, n15, n14);
                }
            }
            else {
                Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n2][n][n3];
                if (class172 == null) {
                    class172 = new Class172(n2);
                }
                if (~n6 == 0xFFFFFFFE) {
                    class172.aShort1335 = (short)n5;
                    class172.aShort1329 = (short)n4;
                }
                else if (n6 == 2) {
                    class172.aShort1328 = (short)n5;
                    class172.aShort1323 = (short)n4;
                }
                if (OutputStream_Sub1.aBoolean35) {
                    Class64_Sub22.method644(-44);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pc.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    Class239(final Class279 class279, final int n, final Class207 aClass207_1842) {
        new Class79(64);
        try {
            this.aClass207_1842 = aClass207_1842;
            this.anInt1838 = this.aClass207_1842.method2761(0, 15);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pc.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1842 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class239.aBoolean1839 = false;
        Class239.aBoolean1840 = false;
        Class239.anInt1843 = 999999;
        Class239.anInt1841 = 1401;
        Class239.anInt1844 = 0;
    }
}
