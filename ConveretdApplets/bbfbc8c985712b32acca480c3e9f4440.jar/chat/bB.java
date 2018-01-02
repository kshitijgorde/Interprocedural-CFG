// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bB
{
    public static String a(final long n) {
        return (n >>> 56) + "." + (n >>> 48 & 0xFFL) + "." + (n >>> 40 & 0xFFL) + "." + (n & 0xFFFFFL);
    }
    
    public static long a() {
        return 504403159339237384L;
    }
    
    public static String a() {
        return a(504403159339237384L);
    }
}
