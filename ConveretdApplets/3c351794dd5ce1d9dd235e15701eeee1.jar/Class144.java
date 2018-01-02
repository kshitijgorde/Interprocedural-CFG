// 
// Decompiled by Procyon v0.5.30
// 

final class Class144
{
    static Class258 aClass258_1168;
    static int anInt1169;
    
    static final boolean method2311(final boolean b, final int n) {
        try {
            return b || ~n == 0xFFFFFFFE || ~n == 0xFFFFFFFC || ~n == 0xFFFFFFFA;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jw.A(" + b + ',' + n + ')');
        }
    }
    
    public static void method2312(final int n) {
        try {
            if (n != -6569) {
                Class144.aClass258_1168 = null;
            }
            Class144.aClass258_1168 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jw.B(" + n + ')');
        }
    }
    
    static {
        Class144.aClass258_1168 = new Class258();
        Class144.anInt1169 = -1;
    }
}
