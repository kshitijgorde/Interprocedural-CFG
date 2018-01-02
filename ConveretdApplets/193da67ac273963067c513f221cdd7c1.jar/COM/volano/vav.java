// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.security.SecureRandom;

public class vav extends SecureRandom
{
    public static final byte[] a;
    private static int b;
    
    private static void a(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = vav.a[vav.b % vav.a.length];
            vav.b += 4;
        }
    }
    
    public static byte[] getSeed(final int n) {
        final byte[] array = new byte[n];
        a(array);
        return array;
    }
    
    public vav() {
        super(getSeed(20));
    }
    
    public void setSeed(final byte[] array) {
    }
    
    public void setSeed(final long n) {
    }
    
    public synchronized void nextBytes(final byte[] array) {
        a(array);
    }
    
    static {
        a = new byte[] { 101, -115, -53, -120, 11, -119, -76, 5, 92, -45, -73, 72, -78, 68, -101, 33, 42, 86, 42, 58, -13, 15, -35, 74, -38, -88, 110, -13, 32, 123, 4, 42, -51, 116, -34, 117, 2, 36, -113, 45, 15, 123, 51, -49, -128, 78, 45, 73, 55, 58, -109, -14, 107, 8, -6, -93, 27, 17, -115, -6, 118, -47, -13, 49, -103, 22, -51, 107, -92, 59, -11, -124, 66, -39, 96, -108, -72, 5, -65, -48, -80, 93, 66, 56, 92, 32, -47, 50, -53, 16, -122, 71, 40, -80, 45, 109, 74, -117, 69, -114 };
    }
}
