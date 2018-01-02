// 
// Decompiled by Procyon v0.5.30
// 

package lort;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

class object2 extends HashMap
{
    public Object[] op;
    final HashSet oaob;
    public Object o;
    public static int oaoc;
    final cooter oaod;
    
    @Override
    public Set entrySet() {
        return this.oaob;
    }
    
    object2(final cooter oaod, final HashSet oaob) {
        this.op = new Object[1];
        this.oaod = oaod;
        this.oaob = oaob;
    }
    
    static {
        object2.oaoc = 10;
    }
}
