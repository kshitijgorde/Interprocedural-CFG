// 
// Decompiled by Procyon v0.5.30
// 

final class Class238
{
    byte aByte1816;
    boolean aBoolean1817;
    int anInt1818;
    boolean aBoolean1819;
    byte aByte1820;
    int anInt1821;
    boolean aBoolean1822;
    byte aByte1823;
    boolean aBoolean1824;
    boolean aBoolean1825;
    boolean aBoolean1826;
    boolean aBoolean1827;
    static Class43 aClass43_1828;
    byte aByte1829;
    byte aByte1830;
    short aShort1831;
    byte aByte1832;
    boolean aBoolean1833;
    static Class348 aClass348_1834;
    int anInt1835;
    static int[] anIntArray1836;
    byte aByte1837;
    
    static final int method2917(final int n, final Class98_Sub22_Sub1 class98_Sub22_Sub1) {
        try {
            if (n != 0) {
                return 89;
            }
            final int bits = class98_Sub22_Sub1.readBits((byte)(-115), 2);
            int n2;
            if (~bits != -1) {
                if (~bits != 0xFFFFFFFE) {
                    if (~bits == 0xFFFFFFFD) {
                        n2 = class98_Sub22_Sub1.readBits((byte)(-58), 8);
                    }
                    else {
                        n2 = class98_Sub22_Sub1.readBits((byte)(-110), 11);
                    }
                }
                else {
                    n2 = class98_Sub22_Sub1.readBits((byte)(-84), 5);
                }
            }
            else {
                n2 = 0;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pba.A(" + n + ',' + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2918(final int n) {
        try {
            Class238.aClass348_1834 = null;
            if (n >= -28) {
                Class238.anIntArray1836 = null;
            }
            Class238.anIntArray1836 = null;
            Class238.aClass43_1828 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pba.C(" + n + ')');
        }
    }
    
    static final boolean method2919(final int n, final int n2, final int n3) {
        try {
            if (n > -49) {
                method2917(-89, null);
            }
            return (IncomingOpcode.method523(n3, -1, n2) | Class105.method1715(true, n2, n3) | Class76_Sub9.method766(-99, n3, n2)) & Class93.method901(n3, n2, -122);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pba.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2920(final int n) {
        try {
            for (int n2 = 0; ~n2 > ~Class281.aByteArrayArrayArray2117.length; ++n2) {
                for (int i = 0; i < Class281.aByteArrayArrayArray2117[0].length; ++i) {
                    for (int n3 = 0; ~n3 > ~Class281.aByteArrayArrayArray2117[0][0].length; ++n3) {
                        Class281.aByteArrayArrayArray2117[n2][i][n3] = 0;
                    }
                }
            }
            if (n >= -123) {
                method2920(65);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pba.B(" + n + ')');
        }
    }
    
    static {
        Class238.anIntArray1836 = new int[2];
        Class238.aClass348_1834 = new Class348(15, 0, 1, 0);
    }
}
