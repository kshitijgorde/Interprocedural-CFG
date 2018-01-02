// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.StringTokenizer;

public class cB
{
    public static int[] e;
    public static int h;
    public static int i;
    public static String e;
    public static String W;
    
    public static void a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        cB.e = new int[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                cB.e[n] = new Integer(nextToken);
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
            ++n;
        }
    }
    
    public static void a() {
        cB.e = new int[0];
    }
    
    public static void m(final int n) {
        final int n2 = cB.e[0];
        cB.e[0] = cB.e[n];
        cB.e[n] = n2;
    }
    
    public static int a(final int n) {
        return cB.e[n];
    }
    
    static {
        cB.h = 11112;
        cB.i = 80;
        cB.e = "";
        cB.W = "8396,58396,110,25,119,443";
    }
}
