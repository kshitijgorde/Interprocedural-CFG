// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.security.SecureRandom;

public class mat extends SecureRandom
{
    public static final byte[] a;
    private static int b;
    
    private static void a(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = mat.a[mat.b % mat.a.length];
            mat.b += 5;
        }
    }
    
    public static byte[] getSeed(final int n) {
        final byte[] array = new byte[n];
        a(array);
        return array;
    }
    
    public mat() {
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
        a = new byte[] { 63, 121, -62, -124, -64, -18, 2, 5, -112, -111, -87, 66, 91, -103, 102, 101, 46, 94, -114, 4, -3, -47, -86, -31, -55, 2, -66, 89, 105, 23, -40, 49, 121, 41, -57, 32, 85, 126, -96, -78, -15, 67, -68, -35, 90, 124, 125, 125, 57, 10, 88, -35, 97, -55, -47, -5, -108, 13, 53, 19, 100, -33, -8, -11, 66, -83, -127, 96, -86, -26, 91, -76, 48, 36, 107, 75, -8, 44, 93, 31, -33, 17, 123, 21, 72, -80, 15, -78, 7, -98, 25, -76, 1, 90, -58, 37, -63, 117, -2, -30 };
    }
}
