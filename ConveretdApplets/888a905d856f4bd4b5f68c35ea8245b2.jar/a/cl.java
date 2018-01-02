// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.StringTokenizer;
import java.util.Vector;

public final class cl
{
    public static Vector q;
    public static String q;
    
    public static void q(String s) {
        s = (String)new StringTokenizer(s, ",");
        while (((StringTokenizer)s).hasMoreTokens()) {
            final String nextToken = ((StringTokenizer)s).nextToken();
            try {
                cl.q.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
        }
    }
    
    public static void q() {
        cl.q.removeAllElements();
    }
    
    public static int q(final int n) {
        return cl.q.elementAt(n);
    }
    
    static {
        cl.q = new Vector();
        cl.q = "8397";
    }
}
