// 
// Decompiled by Procyon v0.5.30
// 

final class Class137
{
    static Class113 aClass113_1078;
    static int anInt1079;
    static Class326 aClass326_1080;
    static int[] anIntArray1081;
    
    public static void method2275(final int n) {
        try {
            Class137.anIntArray1081 = null;
            Class137.aClass326_1080 = null;
            Class137.aClass113_1078 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jfa.B(" + n + ')');
        }
    }
    
    static final void method2276(final String s, final int n, final String s2, final String s3, final int n2, final String s4, final byte b, final int n3, final String s5) {
        try {
            Class131 class131 = Class98_Sub46_Sub3.aClass131Array5953[99];
            for (int i = 99; i > 0; --i) {
                Class98_Sub46_Sub3.aClass131Array5953[i] = Class98_Sub46_Sub3.aClass131Array5953[i - 1];
            }
            if (b > -50) {
                method2275(-6);
            }
            if (class131 != null) {
                class131.method2232(n2, s3, s2, s4, n3, s5, n, s, -30991);
            }
            else {
                class131 = new Class131(n, n3, s2, s5, s, s4, n2, s3);
            }
            ++Class133.anInt3452;
            Class98_Sub46_Sub3.aClass131Array5953[0] = class131;
            Class98_Sub3.anInt3825 = Class24.anInt242;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jfa.A(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((s3 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((s4 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ',' + ((s5 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class137.aClass113_1078 = new Class113(2, 2);
        Class137.anIntArray1081 = new int[] { 1, 2, 4, 8 };
    }
}
