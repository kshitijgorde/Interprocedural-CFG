// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.StringTokenizer;
import java.util.Vector;

public final class cE
{
    public static Vector q;
    public static String q;
    public static String w;
    
    public static void q(String s) {
        s = (String)new StringTokenizer(s, ",");
        while (((StringTokenizer)s).hasMoreTokens()) {
            final String nextToken = ((StringTokenizer)s).nextToken();
            try {
                cE.q.addElement(new Integer(nextToken));
            }
            catch (NumberFormatException ex) {
                System.err.println("Invalid port number: " + nextToken);
            }
        }
    }
    
    public static void q() {
        cE.q.removeAllElements();
    }
    
    public static int q(final int n) {
        return cE.q.elementAt(n);
    }
    
    static {
        cE.q = new Vector();
        cE.w = "8397";
    }
}
