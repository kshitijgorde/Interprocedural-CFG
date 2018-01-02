// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Dimension;
import java.util.Hashtable;

public final class aA
{
    private static Hashtable a;
    
    public static void a(final String s, final Dimension dimension) {
        aA.a.put(s, dimension);
    }
    
    public static Dimension a(final String s) {
        if (!aA.a.containsKey(s)) {
            System.out.println("Photo size unknown: " + s);
            return new Dimension(3000, 2000);
        }
        return aA.a.get(s);
    }
    
    static {
        aA.a = new Hashtable();
    }
}
