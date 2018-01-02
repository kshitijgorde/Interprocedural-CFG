// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.StringTokenizer;
import java.util.Vector;

public final class br
{
    public static Vector q;
    public static String q;
    public static String w;
    
    public static void q(String s) {
        s = (String)new StringTokenizer(s, ",");
        while (((StringTokenizer)s).hasMoreTokens()) {
            final String nextToken = ((StringTokenizer)s).nextToken();
            try {
                br.q.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
        }
    }
    
    public static void q() {
        br.q.removeAllElements();
    }
    
    public static int q(final int n) {
        return br.q.elementAt(n);
    }
    
    static {
        br.q = new Vector();
        br.w = "8397";
    }
}
