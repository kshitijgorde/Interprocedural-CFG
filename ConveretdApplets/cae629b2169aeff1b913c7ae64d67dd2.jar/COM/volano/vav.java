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
            ++vav.b;
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
        a = new byte[] { -82, -15, 69, 67, 6, -127, -122, 54, 99, 99, -108, 95, -101, 76, -83, -4, 73, -42, 88, -22, -57, -123, 80, 49, 126, -7, 98, 74, -100, 48, -124, -74, -16, -49, -17, -82, 15, -31, -86, 28, -54, 61, 11, -43, 25, -118, -110, 95, 80, 124, 53, -69, -115, -67, 72, -114, -50, 40, 66, -15, 43, 56, 115, 106, 117, -105, 70, 86, -72, 20, -58, 50, 104, -63, -46, 36, 36, -78, 9, 107, 72, 29, 51, 102, 2, -116, -17, -119, -15, 3, -28, 72, -29, -55, 31, -94, 88, 127, 49, -100 };
    }
}
