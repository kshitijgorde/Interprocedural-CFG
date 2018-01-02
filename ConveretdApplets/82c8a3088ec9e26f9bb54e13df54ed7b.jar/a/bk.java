// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.StringTokenizer;
import java.util.Vector;

public final class bk
{
    public static Vector q;
    public static String q;
    
    public static void q(String s) {
        s = (String)new StringTokenizer(s, ",");
        while (((StringTokenizer)s).hasMoreTokens()) {
            final String nextToken = ((StringTokenizer)s).nextToken();
            try {
                bk.q.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
        }
    }
    
    public static void q() {
        bk.q.removeAllElements();
    }
    
    public static int q(final int n) {
        return bk.q.elementAt(n);
    }
    
    static {
        bk.q = new Vector();
        bk.q = "8397";
    }
}
