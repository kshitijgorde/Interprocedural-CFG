// 
// Decompiled by Procyon v0.5.30
// 

package jagex3.jagmisc;

public class jagmisc
{
    private static native int ping0(final byte p0, final byte p1, final byte p2, final byte p3, final long p4);
    
    private static native void Quit0();
    
    public static native long nanoTime();
    
    public static native long getTotalPhysicalMemory();
    
    public static native long getAvailablePhysicalMemory();
    
    public static void quit() {
        try {
            Quit0();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static native boolean init();
    
    public static int ping(final byte b, final byte b2, final byte b3, final byte b4, final long n) throws Throwable {
        try {
            final int ping0 = ping0(b, b2, b3, b4, n);
            if (ping0 < 0) {
                throw new Exception(String.valueOf(ping0));
            }
            return ping0;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
