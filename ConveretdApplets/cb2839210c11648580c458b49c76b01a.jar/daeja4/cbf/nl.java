// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

import java.util.Vector;

public class nl
{
    public static Vector a(final ni ni) {
        final Vector vector = new Vector();
        a(vector, ni);
        return vector;
    }
    
    private static void a(final Vector vector, final ni ni) {
        if (ni.o != null) {
            a(vector, ni.o);
        }
        vector.add(ni);
        if (ni.p != null) {
            a(vector, ni.p);
        }
    }
}
