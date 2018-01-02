// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.IdentityHashMap;

public class IdentityMultimap extends IdentityHashMap
{
    public void a(final Object o, final Object o2) {
        List<Object> list = super.get(o);
        if (list == null) {
            list = new ArrayList<Object>(2);
            this.put(o, list);
        }
        list.add(o2);
    }
    
    public List a(final Object o) {
        final List list = super.get(o);
        return (list == null) ? Collections.EMPTY_LIST : list;
    }
    
    public int b(final Object o) {
        final List list = super.get(o);
        return (list == null) ? 0 : list.size();
    }
}
