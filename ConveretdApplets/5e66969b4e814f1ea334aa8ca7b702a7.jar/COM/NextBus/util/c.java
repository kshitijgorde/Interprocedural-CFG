// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.util;

public final class c
{
    private static long a;
    
    public static final void a(final long n) {
        c.a = n - System.currentTimeMillis();
    }
    
    public static final long a() {
        return System.currentTimeMillis() + c.a;
    }
    
    public static final long b() {
        return (System.currentTimeMillis() + c.a) / 1000L * 1000L;
    }
    
    static {
        c.a = 0L;
    }
}
