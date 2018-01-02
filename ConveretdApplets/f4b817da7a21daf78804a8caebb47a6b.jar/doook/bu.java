// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.StringTokenizer;

public class bu
{
    public static int[] c;
    public static int f;
    public static int a;
    public static String v;
    public static String t;
    
    public static void a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        bu.c = new int[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                bu.c[n] = new Integer(nextToken);
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
            ++n;
        }
    }
    
    public static void c() {
        bu.c = new int[0];
    }
    
    public static void b(final int n) {
        final int n2 = bu.c[0];
        bu.c[0] = bu.c[n];
        bu.c[n] = n2;
    }
    
    public static int a(final int n) {
        return bu.c[n];
    }
    
    static {
        bu.f = 11112;
        bu.a = 80;
        bu.v = "";
        bu.t = "8396,58396,110,25,119,443";
    }
}
