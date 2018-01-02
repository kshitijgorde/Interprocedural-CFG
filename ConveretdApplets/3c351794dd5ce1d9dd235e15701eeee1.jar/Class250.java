// 
// Decompiled by Procyon v0.5.30
// 

final class Class250
{
    float[][] aFloatArrayArray1910;
    int[] anIntArray1911;
    int[] anIntArray1912;
    static OutgoingOpcode aClass171_1913;
    static Class354 aClass354_1914;
    int[] anIntArray1915;
    
    public static void method3165(final int n) {
        try {
            Class250.aClass171_1913 = null;
            if (n != 76) {
                method3165(-70);
            }
            Class250.aClass354_1914 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pl.B(" + n + ')');
        }
    }
    
    static final int method3166(final int n, final int n2, int n3, final byte b) {
        try {
            n3 &= 0x3;
            if (n3 == 0) {
                return n2;
            }
            if (n3 == 1) {
                return -n + 7;
            }
            if (n3 == 2) {
                return -n2 + 7;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pl.C(" + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    Class250(final int[] anIntArray1911, final int[] anIntArray1912, final int[] anIntArray1913, final float[][] aFloatArrayArray1910) {
        try {
            this.anIntArray1915 = anIntArray1912;
            this.anIntArray1911 = anIntArray1911;
            this.anIntArray1912 = anIntArray1913;
            this.aFloatArrayArray1910 = aFloatArrayArray1910;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pl.<init>(" + ((anIntArray1911 != null) ? "{...}" : "null") + ',' + ((anIntArray1912 != null) ? "{...}" : "null") + ',' + ((anIntArray1913 != null) ? "{...}" : "null") + ',' + ((aFloatArrayArray1910 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3167(final byte b, final Class98_Sub31 class98_Sub31) {
        try {
            if (class98_Sub31.aClass98_Sub24_4104 != null) {
                class98_Sub31.aClass98_Sub24_4104.anInt4008 = 0;
            }
            if (b == -32) {
                class98_Sub31.aBoolean4102 = false;
                for (Class98_Sub31 class98_Sub32 = class98_Sub31.method1322(); class98_Sub32 != null; class98_Sub32 = class98_Sub31.method1327()) {
                    method3167((byte)(-32), class98_Sub32);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pl.D(" + b + ',' + ((class98_Sub31 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3168(final Class207 aClass207_5773, final boolean b, final d ad5578) {
        try {
            Class98_Sub10_Sub39.aClass207_5773 = aClass207_5773;
            Class98_Sub10_Sub8.aD5578 = ad5578;
            if (!b) {
                method3166(-101, -39, 69, (byte)(-47));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pl.A(" + ((aClass207_5773 != null) ? "{...}" : "null") + ',' + b + ',' + ((ad5578 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class250.aClass171_1913 = new OutgoingOpcode(76, 7);
    }
}
