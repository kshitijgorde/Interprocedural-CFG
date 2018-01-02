// 
// Decompiled by Procyon v0.5.30
// 

final class Class159
{
    static Class293[][] aClass293ArrayArray1252;
    static IncomingOpcode aClass58_1253;
    static float aFloat1254;
    static byte[] aByteArray1255;
    static int anInt1256;
    
    static final void method2508(final int n, final int n2, final byte b, final ha aHa4185) {
        try {
            Class98_Sub37.aHa4185 = aHa4185;
            Class76.aClass28ArrayArray586 = new Class28[n][n2];
            if (Class50.anIntArray417 != null) {
                Class246_Sub7.aClass48_5119 = Class13.method217(5, Class50.anIntArray417[3], Class50.anIntArray417[5], Class50.anIntArray417[4], Class50.anIntArray417[2], Class50.anIntArray417[0], Class50.anIntArray417[1]);
            }
            Class91.aClass28_722 = new Class28();
            Class369.method3954(0);
            if (b != -50) {
                Class159.aFloat1254 = 0.79444367f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "km.B(" + n + ',' + n2 + ',' + b + ',' + ((aHa4185 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class293 method2509(final int n, final int n2) {
        try {
            final int n3 = n >> 1456192752;
            if (n2 != -9820) {
                Class159.aClass293ArrayArray1252 = null;
            }
            final int n4 = n & 0xFFFF;
            if ((Class159.aClass293ArrayArray1252[n3] == null || Class159.aClass293ArrayArray1252[n3][n4] == null) && !Class85.method837(n3, 85)) {
                return null;
            }
            return Class159.aClass293ArrayArray1252[n3][n4];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "km.A(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method2510(final int n) {
        try {
            Class159.aClass58_1253 = null;
            if (n != 4) {
                method2509(21, -77);
            }
            Class159.aByteArray1255 = null;
            Class159.aClass293ArrayArray1252 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "km.C(" + n + ')');
        }
    }
    
    static {
        Class159.aClass58_1253 = new IncomingOpcode(85, -1);
        Class159.aByteArray1255 = new byte[520];
    }
}
