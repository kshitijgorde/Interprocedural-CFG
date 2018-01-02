import java.io.IOException;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class375
{
    static Class207 aClass207_3167;
    static int anInt3168;
    static int anInt3169;
    static boolean aBoolean3170;
    
    static final boolean method3986(final int n, final byte b) {
        try {
            if (b != -108) {
                method3988(null, (byte)(-13), -123);
            }
            return ~n == 0xFFFFFFFD || ~n == 0xFFFFFFFC;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wp.B(" + n + ',' + b + ')');
        }
    }
    
    public static void method3987(final byte b) {
        try {
            if (b != -73) {
                Class375.anInt3168 = -23;
            }
            Class375.aClass207_3167 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wp.C(" + b + ')');
        }
    }
    
    static final byte[] method3988(final File file, final byte b, final int n) {
        try {
            if (b != 78) {
                Class375.aBoolean3170 = true;
            }
            try {
                final byte[] array = new byte[n];
                Class261.method3211(array, 124, n, file);
                return array;
            }
            catch (IOException ex2) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wp.A(" + ((file != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static {
        Class375.anInt3168 = 328;
        Class375.aBoolean3170 = false;
    }
}
