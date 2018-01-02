// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ao
{
    public static boolean h;
    private static final String[] c;
    
    public static String a(final long n) {
        final long n2 = n >>> 56;
        final long n3 = n >>> 48 & 0xFFL;
        final long n4 = n >>> 40 & 0xFFL;
        final long n5 = n >>> 32 & 0xFFL;
        final long n6 = n >>> 28 & 0xFL;
        String s = n2 + "." + n3 + "." + n4 + "." + (n & 0xFFFFFL);
        if (n5 != 0L) {
            s = s + ao.c[(int)n6] + n5;
        }
        return s;
    }
    
    public static String b() {
        return a(288233675760336897L);
    }
    
    static {
        ao.h = false;
        c = new String[] { " dev ", " alpha ", " beta ", "fc" };
    }
}
