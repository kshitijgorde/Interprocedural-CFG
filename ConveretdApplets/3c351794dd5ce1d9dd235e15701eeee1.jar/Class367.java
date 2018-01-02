// 
// Decompiled by Procyon v0.5.30
// 

final class Class367 implements Interface21
{
    static double aDouble3543;
    int anInt3544;
    static Class215 aClass215_3545;
    static int[] anIntArray3546;
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                Class367.aClass215_3545 = null;
            }
            return Class4.aClass113_80;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wd.A(" + n + ')');
        }
    }
    
    public static void method3948(final boolean b) {
        try {
            Class367.aClass215_3545 = null;
            Class367.anIntArray3546 = null;
            if (!b) {
                method3948(false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wd.B(" + b + ')');
        }
    }
    
    Class367(final int anInt3544) {
        try {
            this.anInt3544 = anInt3544;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wd.<init>(" + anInt3544 + ')');
        }
    }
    
    static {
        Class367.aClass215_3545 = new Class215();
        Class367.anIntArray3546 = new int[14];
    }
}
