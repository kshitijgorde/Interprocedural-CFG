// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class d
{
    public static boolean a;
    private static final String[] b;
    
    public static String a(final long n) {
        return a(n, ".");
    }
    
    public static String a(final long n, final String s) {
        final long n2 = n >>> 56;
        final long n3 = n >>> 48 & 0xFFL;
        final long n4 = n >>> 40 & 0xFFL;
        final long n5 = n >>> 32 & 0xFFL;
        final long n6 = n >>> 28 & 0xFL;
        String s2 = n2 + s + n3 + s + n4 + s + (n & 0xFFFFFL);
        if (n5 != 0L) {
            s2 = s2 + d.b[(int)n6] + n5;
        }
        return s2;
    }
    
    public static String a() {
        return a(360569446240092178L);
    }
    
    static {
        d.a = false;
        b = new String[] { " dev ", " alpha ", " beta ", "fc" };
    }
}
