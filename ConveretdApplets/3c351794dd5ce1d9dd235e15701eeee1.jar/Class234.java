// 
// Decompiled by Procyon v0.5.30
// 

final class Class234
{
    static OutgoingOpcode aClass171_1747;
    static Class207 aClass207_1748;
    static float aFloat1749;
    static boolean aBoolean1750;
    static Class207 aClass207_1751;
    
    public static void method2885(final byte b) {
        try {
            Class234.aClass207_1751 = null;
            Class234.aClass207_1748 = null;
            Class234.aClass171_1747 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ot.C(" + b + ')');
        }
    }
    
    static final int method2886(final int n, final int n2) {
        try {
            if (n2 > -101) {
                return -25;
            }
            return n >>> -2139410934;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ot.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method2887(final int n, final byte b, final int n2) {
        try {
            if (b != 46) {
                method2886(36, 120);
            }
            return (n2 & 0xC580) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ot.A(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static {
        Class234.aClass171_1747 = new OutgoingOpcode(15, 8);
        Class234.aBoolean1750 = false;
    }
}
