// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class d
{
    public static boolean a;
    private static final String[] b;
    
    public static String a(final long n) {
        final long n2 = n >>> 56;
        final long n3 = n >>> 48 & 0xFFL;
        final long n4 = n >>> 40 & 0xFFL;
        final long n5 = n >>> 32 & 0xFFL;
        final long n6 = n >>> 28 & 0xFL;
        String s = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(n2).concat(String.valueOf("."))).concat(String.valueOf(n3))).concat(String.valueOf("."))).concat(String.valueOf(n4))).concat(String.valueOf("."))).concat(String.valueOf(n & 0xFFFFFL));
        if (n5 != 0L) {
            s = String.valueOf(s).concat(String.valueOf(String.valueOf(d.b[(int)n6]).concat(String.valueOf(n5))));
        }
        return s;
    }
    
    public static String a() {
        return a(288233675760336897L);
    }
    
    static {
        d.a = false;
        b = new String[] { " dev ", " alpha ", " beta ", "fc" };
    }
}
