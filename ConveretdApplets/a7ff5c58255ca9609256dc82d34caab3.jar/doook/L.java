// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.StringTokenizer;

public class L
{
    public static int[] a;
    public static int t;
    public static int g;
    public static String g;
    public static String l;
    
    public static void a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        L.a = new int[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                L.a[n] = new Integer(nextToken);
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
            ++n;
        }
    }
    
    public static void f() {
        L.a = new int[0];
    }
    
    public static void l(final int n) {
        final int n2 = L.a[0];
        L.a[0] = L.a[n];
        L.a[n] = n2;
    }
    
    public static int a(final int n) {
        return L.a[n];
    }
    
    static {
        L.t = 11112;
        L.g = 80;
        L.g = "";
        L.l = "8396,58396,110,25,119,443";
    }
}
