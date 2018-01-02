// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import java.util.StringTokenizer;
import java.util.Vector;

public class p
{
    public static Vector a;
    public static int b;
    public static int c;
    public static String d;
    public static String e;
    
    public static void a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                p.a.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println(String.valueOf("Invalid port number: ").concat(String.valueOf(nextToken)));
            }
        }
    }
    
    public static void a() {
        p.a.removeAllElements();
    }
    
    public static int a(final int n) {
        return p.a.elementAt(n);
    }
    
    static {
        p.a = new Vector();
        p.b = 11112;
        p.c = 80;
        p.d = "";
        p.e = "8396,58396,110,25,119,443";
    }
}
