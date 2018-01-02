// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class151
{
    static int anInt1213;
    static int anInt1214;
    ha_Sub1 aHa_Sub1_1215;
    static Class63 aClass63_1216;
    
    public static void method2438(final int n) {
        try {
            if (n != -24883) {
                method2438(14);
            }
            Class151.aClass63_1216 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kd.L(" + n + ')');
        }
    }
    
    abstract boolean method2439(final int p0);
    
    abstract void method2440(final boolean p0, final boolean p1);
    
    Class151(final ha_Sub1 aHa_Sub1_1215) {
        try {
            this.aHa_Sub1_1215 = aHa_Sub1_1215;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kd.<init>(" + ((aHa_Sub1_1215 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method2441(final int p0, final int p1, final int p2);
    
    abstract void method2442(final Class42 p0, final boolean p1, final int p2);
    
    abstract void method2443(final boolean p0, final int p1);
    
    static final int method2444(final int n, final int n2) {
        try {
            return n & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kd.M(" + n + ',' + n2 + ')');
        }
    }
    
    abstract void method2445(final byte p0);
    
    static {
        Class151.anInt1213 = 0;
    }
}
