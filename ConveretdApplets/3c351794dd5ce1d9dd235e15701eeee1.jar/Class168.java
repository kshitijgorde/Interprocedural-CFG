// 
// Decompiled by Procyon v0.5.30
// 

final class Class168
{
    static Class377 aClass377_1287;
    short[] aShortArray1288;
    byte[] aByteArray1289;
    static Class253 aClass253_1290;
    short[] aShortArray1291;
    short[] aShortArray1292;
    
    public static void method2532(final byte b) {
        try {
            if (b == -6) {
                Class168.aClass253_1290 = null;
                Class168.aClass377_1287 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "le.A(" + b + ')');
        }
    }
    
    static final void method2533(final int n, final int n2, final int n3, final int n4, final byte b, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            if (b > 79) {
                if (~n2 > -513 || n5 < 512 || ~(Class165.anInt1276 * 512 - 1024) > ~n2 || ~(Class98_Sub10_Sub7.anInt5572 * 512 - 1024) > ~n5) {
                    Class259.anIntArray1957[0] = (Class259.anIntArray1957[1] = -1);
                }
                else {
                    final int n10 = Class98_Sub46_Sub2_Sub2.method1538(n7, n5, n2, 24111) + -n9;
                    if (za_Sub2.aBoolean6079) {
                        Class98_Sub46_Sub14.method1604(true, (byte)88);
                    }
                    else {
                        Class266.aClass111_1986.method2106(n4, 0, 0);
                        Class265.aHa1974.a(Class266.aClass111_1986);
                    }
                    if (Class239.aBoolean1839) {
                        Class265.aHa1974.HA(n2, n10, n5, Class16.anInt197, Class259.anIntArray1957);
                    }
                    else {
                        Class265.aHa1974.da(n2, n10, n5, Class259.anIntArray1957);
                    }
                    if (za_Sub2.aBoolean6079) {
                        Class207.method2765((byte)38);
                    }
                    else {
                        Class266.aClass111_1986.method2106(-n4, 0, 0);
                        Class265.aHa1974.a(Class266.aClass111_1986);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "le.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    static final boolean method2534(final byte b) {
        try {
            if (b > -126) {
                Class168.aClass253_1290 = null;
            }
            return Class257.anInt1948 != 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "le.B(" + b + ')');
        }
    }
    
    static {
        Class168.aClass377_1287 = new Class377(512);
    }
}
