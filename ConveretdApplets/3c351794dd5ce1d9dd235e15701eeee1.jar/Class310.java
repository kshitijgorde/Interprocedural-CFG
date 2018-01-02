// 
// Decompiled by Procyon v0.5.30
// 

final class Class310
{
    static int anInt2652;
    static boolean[][] aBooleanArrayArray2653;
    
    public static void method3617(final byte b) {
        try {
            if (b >= -11) {
                method3617((byte)(-111));
            }
            Class310.aBooleanArrayArray2653 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tc.A(" + b + ')');
        }
    }
    
    static final void method3618(final int n) {
        try {
            Class356 class356 = null;
            try {
                final Class143 method875 = Class98_Sub43_Sub2.aClass88_5907.method875("", true, n + 27480);
                if (n != -5964) {
                    return;
                }
                while (method875.anInt1163 == 0) {
                    Class246_Sub7.method3131(n + 5964, 1L);
                }
                if (~method875.anInt1163 == 0xFFFFFFFE) {
                    class356 = (Class356)method875.anObject1162;
                    final Class98_Sub22 method876 = Class98_Sub9.aClass98_Sub27_3856.method1288(true);
                    class356.method3882(method876.aByteArray3992, 4657, 0, method876.anInt3991);
                }
            }
            catch (Exception ex2) {}
            try {
                if (class356 != null) {
                    class356.method3880(true);
                }
            }
            catch (Exception ex3) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tc.B(" + n + ')');
        }
    }
    
    static {
        Class310.anInt2652 = -1;
        Class310.aBooleanArrayArray2653 = new boolean[][] { { true, true, true, true, true, true, true, true, true, true, true, true, true }, { true, true, true, false, false, false, true, true, false, false, false, false, true }, { true, false, false, false, false, true, true, true, false, false, false, false, false }, { false, false, true, true, true, true, false, false, false, false, false, false, false }, { true, true, true, true, true, true, false, false, false, false, false, false, false }, { true, true, true, false, false, true, true, true, false, false, false, false, false }, { true, true, false, false, false, true, true, true, false, false, false, false, true }, { true, true, false, false, false, false, false, true, false, false, false, false, false }, { false, true, true, true, true, true, true, true, false, false, false, false, false }, { true, false, false, false, true, true, true, true, true, true, false, false, false }, { true, true, true, true, true, false, false, false, true, true, false, false, false }, { true, true, true, false, false, false, false, false, false, false, true, true, false }, new boolean[13], { true, true, true, true, true, true, true, true, true, true, true, true, true }, new boolean[13] };
    }
}
