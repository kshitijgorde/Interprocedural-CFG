// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.StringTokenizer;
import java.util.Vector;

public final class bl
{
    public static Vector q;
    public static String q;
    
    public static void q(String s) {
        s = (String)new StringTokenizer(s, ",");
        while (((StringTokenizer)s).hasMoreTokens()) {
            final String nextToken = ((StringTokenizer)s).nextToken();
            try {
                bl.q.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
        }
    }
    
    public static void q() {
        bl.q.removeAllElements();
    }
    
    public static int q(final int n) {
        return bl.q.elementAt(n);
    }
    
    static {
        bl.q = new Vector();
        bl.q = "8397";
    }
}
