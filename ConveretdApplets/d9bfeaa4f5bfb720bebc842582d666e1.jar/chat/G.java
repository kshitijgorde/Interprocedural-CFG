// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.StringTokenizer;
import java.util.Vector;

public final class G
{
    public Vector a;
    public int a;
    public String a;
    
    public final void a(String s) {
        synchronized (this.a) {
            final Vector vector = new Vector<Integer>();
            s = (String)new StringTokenizer(s, ",");
            while (((StringTokenizer)s).hasMoreTokens()) {
                final String nextToken = ((StringTokenizer)s).nextToken();
                try {
                    vector.addElement(new Integer(nextToken));
                }
                catch (NumberFormatException ex) {
                    System.err.println("Invalid port number: " + nextToken);
                }
            }
            if (this.a.size() != vector.size()) {
                for (int i = 0; i < vector.size(); ++i) {
                    int n;
                    do {
                        n = (int)(Math.random() * vector.size());
                        if (!this.a.contains(vector.elementAt(n))) {
                            this.a.addElement(vector.elementAt(n));
                            break;
                        }
                    } while (this.a.contains(vector.elementAt(n)));
                }
            }
        }
    }
    
    public final int a(final int n) {
        return this.a.elementAt(n);
    }
    
    public G() {
        this.a = new Vector();
        this.a = 80;
        this.a = "6023,6024,6025,6026";
    }
}
