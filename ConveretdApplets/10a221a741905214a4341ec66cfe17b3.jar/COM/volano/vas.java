// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.security.SecureRandom;

public class vas extends SecureRandom
{
    public static final byte[] a;
    private static int b;
    
    private static void a(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = vas.a[vas.b % vas.a.length];
            vas.b += 5;
        }
    }
    
    public static byte[] getSeed(final int n) {
        final byte[] array = new byte[n];
        a(array);
        return array;
    }
    
    public vas() {
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
        a = new byte[] { 79, -10, 49, -113, 47, 100, 22, 61, -29, 41, 108, -49, 72, -110, 29, 97, 50, -35, -113, 4, 125, 8, -101, 52, 117, 17, 76, 14, -75, -43, -119, -91, 85, 45, 92, -38, -17, 33, 62, 97, -97, 16, -121, -64, -117, -77, 91, -25, 59, 71, -49, -89, -114, -113, 3, 27, 34, 110, -31, 54, 6, 100, 9, -15, 99, 78, -39, 84, -1, 35, -96, 84, 126, 37, -108, 105, -103, 78, 57, -14, -104, -41, -113, 118, 76, 87, 60, 14, 94, -81, -104, -103, 87, -101, 14, -90, -121, -88, -105, 40 };
    }
}
