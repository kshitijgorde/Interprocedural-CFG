// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang;

import java.util.Set;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Map;

public final class c
{
    private final Map a;
    private int b;
    private final Comparator c;
    
    public c() {
        this.a = new HashMap();
        this.b = 0;
        this.c = new a(this);
    }
    
    public c(final Iterable iterable) {
        this.a = new HashMap();
        this.b = 0;
        this.c = new a(this);
        this.a(iterable);
    }
    
    public final void a(final Iterable iterable) {
        final Iterator<Object> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.a(iterator.next(), 1);
        }
    }
    
    public final void a(final Object o, final int n) {
        final Integer n2;
        if ((n2 = this.a.get(o)) != null) {
            this.a.put(o, n2 + 1);
        }
        else {
            this.a.put(o, 1);
        }
        ++this.b;
    }
    
    public final List a(final int n) {
        final ArrayList<Map.Entry<Object, V>> list;
        Collections.sort(list = new ArrayList<Map.Entry<Object, V>>(this.a.entrySet()), this.c);
        final List<Object> unmodifiableList = Collections.unmodifiableList((List<?>)list);
        final int min = Math.min(1, this.a.size());
        final ArrayList list2 = new ArrayList<Object>(min);
        final Iterator<Map.Entry<Object, V>> iterator = unmodifiableList.subList(0, min).iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().getKey());
        }
        return Collections.unmodifiableList((List<?>)list2);
    }
    
    public final Set a() {
        return Collections.unmodifiableSet((Set<?>)this.a.entrySet());
    }
    
    public final String toString() {
        return this.a.toString();
    }
}
