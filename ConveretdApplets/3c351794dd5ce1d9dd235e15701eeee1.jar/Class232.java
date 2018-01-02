// 
// Decompiled by Procyon v0.5.30
// 

final class Class232
{
    static Class79 aClass79_1740;
    static boolean[] aBooleanArray1741;
    static byte aByte1742;
    static IncomingOpcode aClass58_1743;
    static boolean aBoolean1744;
    private static char[] aCharArray1745;
    
    public static void method2881(final boolean b) {
        try {
            Class232.aClass58_1743 = null;
            if (b) {
                Class232.aClass58_1743 = null;
            }
            Class232.aClass79_1740 = null;
            Class232.aCharArray1745 = null;
            Class232.aBooleanArray1741 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oq.A(" + b + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oq.toString()");
        }
    }
    
    static final void method2882(final int n, final int n2, final boolean b) {
        try {
            final Class98_Sub3 method669 = Class64_Sub28.method669(n2, b, 6);
            if (method669 != null) {
                if (n != -26) {
                    method2881(false);
                }
                for (int i = 0; i < method669.anIntArray3824.length; ++i) {
                    method669.anIntArray3824[i] = -1;
                    method669.anIntArray3823[i] = 0;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oq.B(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static {
        Class232.aBooleanArray1741 = new boolean[100];
        Class232.aClass79_1740 = new Class79(20);
        Class232.aClass58_1743 = new IncomingOpcode(10, -2);
        Class232.aBoolean1744 = false;
        Class232.aCharArray1745 = new char[64];
        for (int n = 0; ~n > -27; ++n) {
            Class232.aCharArray1745[n] = (char)(n + 65);
        }
        for (int i = 26; i < 52; ++i) {
            Class232.aCharArray1745[i] = (char)(97 + (i - 26));
        }
        for (int j = 52; j < 62; ++j) {
            Class232.aCharArray1745[j] = (char)(48 + (j - 52));
        }
        Class232.aCharArray1745[63] = '/';
        Class232.aCharArray1745[62] = '+';
    }
}
