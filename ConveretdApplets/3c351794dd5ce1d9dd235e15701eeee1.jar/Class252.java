// 
// Decompiled by Procyon v0.5.30
// 

final class Class252
{
    private Class215 aClass215_1922;
    static int anInt1923;
    static int anInt1924;
    private Class98_Sub46 aClass98_Sub46_1925;
    static Class225 aClass225_1926;
    static Class172[][][] aClass172ArrayArrayArray1927;
    
    public static void method3172(final int n) {
        try {
            if (n == 0) {
                Class252.aClass225_1926 = null;
                Class252.aClass172ArrayArrayArray1927 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pq.A(" + n + ')');
        }
    }
    
    final Class98_Sub46 method3173(final boolean b) {
        try {
            if (!b) {
                return null;
            }
            final Class98_Sub46 aClass98_Sub46_4262 = this.aClass215_1922.aClass98_Sub46_1615.aClass98_Sub46_4262;
            if (aClass98_Sub46_4262 == this.aClass215_1922.aClass98_Sub46_1615) {
                return this.aClass98_Sub46_1925 = null;
            }
            this.aClass98_Sub46_1925 = aClass98_Sub46_4262.aClass98_Sub46_4262;
            return aClass98_Sub46_4262;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pq.C(" + b + ')');
        }
    }
    
    final Class98_Sub46 method3174(final int n) {
        try {
            if (n != 0) {
                method3172(-48);
            }
            final Class98_Sub46 aClass98_Sub46_1925 = this.aClass98_Sub46_1925;
            if (this.aClass215_1922.aClass98_Sub46_1615 == aClass98_Sub46_1925) {
                return this.aClass98_Sub46_1925 = null;
            }
            this.aClass98_Sub46_1925 = aClass98_Sub46_1925.aClass98_Sub46_4262;
            return aClass98_Sub46_1925;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pq.B(" + n + ')');
        }
    }
    
    public Class252() {
    }
    
    Class252(final Class215 aClass215_1922) {
        try {
            this.aClass215_1922 = aClass215_1922;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pq.<init>(" + ((aClass215_1922 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class252.anInt1923 = 0;
    }
}
