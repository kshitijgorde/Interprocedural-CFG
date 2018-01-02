// 
// Decompiled by Procyon v0.5.30
// 

final class Class270
{
    static boolean aBoolean2031;
    static Class268 aClass268_2032;
    
    public static void method3275(final boolean b) {
        try {
            Class270.aClass268_2032 = null;
            if (b) {
                Class270.aBoolean2031 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qr.A(" + b + ')');
        }
    }
    
    static {
        Class270.aBoolean2031 = false;
    }
}
