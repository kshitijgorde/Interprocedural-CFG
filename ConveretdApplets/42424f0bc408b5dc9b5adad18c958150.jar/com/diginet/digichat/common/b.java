// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import java.util.StringTokenizer;
import java.util.Vector;

public class b
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
                com.diginet.digichat.common.b.a.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
        }
    }
    
    public static void a() {
        com.diginet.digichat.common.b.a.removeAllElements();
    }
    
    public static int a(final int n) {
        return com.diginet.digichat.common.b.a.elementAt(n);
    }
    
    static {
        com.diginet.digichat.common.b.a = new Vector();
        com.diginet.digichat.common.b.b = 11112;
        com.diginet.digichat.common.b.c = 80;
        com.diginet.digichat.common.b.d = "";
        com.diginet.digichat.common.b.e = "8396,58396,110,25,119,443";
    }
}
