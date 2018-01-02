// 
// Decompiled by Procyon v0.5.30
// 

final class Class275
{
    static Class79 aClass79_2046;
    static int anInt2047;
    static int[] anIntArray2048;
    
    public static void method3282(final byte b) {
        try {
            Class275.anIntArray2048 = null;
            if (b != -53) {
                method3282((byte)8);
            }
            Class275.aClass79_2046 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rc.A(" + b + ')');
        }
    }
    
    static final Class53_Sub1 method3283(final byte b, final int n) {
        try {
            if (!Class98_Sub17.aBoolean3944 || ~n > ~Class164.anInt1274 || n > Class101.anInt854) {
                return null;
            }
            if (b <= 112) {
                Class275.anInt2047 = 95;
            }
            return Class98_Sub20.aClass53_Sub1Array3967[-Class164.anInt1274 + n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rc.B(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class275.aClass79_2046 = new Class79(4);
        Class275.anIntArray2048 = new int[13];
    }
}
