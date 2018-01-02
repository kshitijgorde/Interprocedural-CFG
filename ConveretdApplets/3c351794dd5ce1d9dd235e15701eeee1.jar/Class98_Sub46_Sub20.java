// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub46_Sub20 extends Class98_Sub46
{
    static int[] anIntArray6070;
    Interface20 anInterface20_6071;
    int anInt6072;
    static String[] aStringArray6073;
    static int anInt6074;
    
    abstract Object method1635(final int p0);
    
    public static void method1636(final boolean b) {
        try {
            if (b) {
                Class98_Sub46_Sub20.anIntArray6070 = null;
                Class98_Sub46_Sub20.aStringArray6073 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "naa.C(" + b + ')');
        }
    }
    
    static final int method1637(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n2 >= -39) {
                return 26;
            }
            if (~n3 == ~n4) {
                return n3;
            }
            final int n5 = -n + 128;
            return (n5 * (0x380 & n3) + (0x380 & n4) * n >> 1680000903 & 0x380) | (0xFC00 & (0xFC00 & n4) * n + (n3 & 0xFC00) * n5 >> -239610233) | (n * (0x7F & n4) + n5 * (0x7F & n3) >> 747196583 & 0x7F);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "naa.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    abstract boolean method1638(final int p0);
    
    static final int method1639(final int n, final int n2) {
        if (Class299_Sub2.aByteArrayArray5291 != null) {
            return Class299_Sub2.aByteArrayArray5291[n][n2] & 0xFF;
        }
        return 0;
    }
    
    Class98_Sub46_Sub20(final Interface20 anInterface20_6071, final int anInt6072) {
        try {
            this.anInt6072 = anInt6072;
            this.anInterface20_6071 = anInterface20_6071;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "naa.<init>(" + ((anInterface20_6071 != null) ? "{...}" : "null") + ',' + anInt6072 + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub20.anIntArray6070 = new int[32];
        int n = 2;
        for (int n2 = 0; ~n2 > -33; ++n2) {
            Class98_Sub46_Sub20.anIntArray6070[n2] = -1 + n;
            n += n;
        }
    }
}
