// 
// Decompiled by Procyon v0.5.30
// 

final class Class161
{
    static boolean[][] aBooleanArrayArray1260;
    static Class109 aClass109_1261;
    
    static final boolean method2514(final int n, final int n2, final int n3) {
        try {
            if (n2 != 16) {
                Class161.aBooleanArrayArray1260 = null;
            }
            return (0x10 & n) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kp.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method2515(final int n) {
        try {
            Class161.aClass109_1261 = null;
            if (n != 1) {
                Class161.aBooleanArrayArray1260 = null;
            }
            Class161.aBooleanArrayArray1260 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kp.A(" + n + ')');
        }
    }
    
    static {
        Class161.aBooleanArrayArray1260 = new boolean[][] { new boolean[4], { false, true, true, false }, { true, false, true, false }, { true, false, true, false }, { false, false, true, false }, { false, false, true, false }, { true, false, true, false }, { true, false, false, true }, { true, false, false, true }, { true, true, false, false }, new boolean[4], { false, true, false, true }, new boolean[4] };
    }
}
