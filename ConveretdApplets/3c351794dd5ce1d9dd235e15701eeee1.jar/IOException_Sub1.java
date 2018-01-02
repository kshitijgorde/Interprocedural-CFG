import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class IOException_Sub1 extends IOException
{
    static Class148 aClass148_30;
    static float aFloat31;
    
    public static void method126(final int n) {
        try {
            if (n == 65535) {
                IOException_Sub1.aClass148_30 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wh.A(" + n + ')');
        }
    }
    
    IOException_Sub1(final String s) {
        super(s);
    }
    
    static final int method127(final int n, final int n2) {
        if (Class304.aShortArrayArray2534 != null) {
            return Class304.aShortArrayArray2534[n][n2] & 0xFFFF;
        }
        return 0;
    }
    
    static {
        IOException_Sub1.aClass148_30 = new Class148();
    }
}
