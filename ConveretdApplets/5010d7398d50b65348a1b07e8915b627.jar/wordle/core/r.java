// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import cue.lang.stop.StopWords;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import cue.lang.c;
import java.util.List;

public final class r implements Iterable
{
    private final List a;
    
    public static r a(final c c) {
        final r r = new r();
        for (final Map.Entry<String, V> entry : c.a()) {
            r.a(new C((int)entry.getValue(), entry.getKey()));
        }
        return r;
    }
    
    public r() {
        this.a = new ArrayList();
    }
    
    private r(final List list) {
        this();
        this.a.addAll(list);
    }
    
    public final void a() {
        this.a.clear();
    }
    
    public final r a(final int n) {
        final ArrayList<Object> list;
        Collections.sort(list = new ArrayList<Object>(this.a), C.d);
        return new r(list.subList(0, Math.min(n, list.size())));
    }
    
    public final Collection b(final int n) {
        final ArrayList<String> list = new ArrayList<String>(50);
        final Iterator iterator = this.a(50).iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().b);
        }
        return list;
    }
    
    public final r a(final Set set) {
        final ArrayList<C> list = new ArrayList<C>(this.a.size());
        final Iterator<C> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            final C c;
            if (!set.contains((c = iterator.next()).b)) {
                list.add(c);
            }
        }
        return new r(list);
    }
    
    public final r a(final double n) {
        double max = Double.MIN_VALUE;
        final Iterator<C> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            max = Math.max(max, iterator.next().a);
        }
        final double n2 = n / max;
        final r r = new r();
        final Iterator<C> iterator2 = this.a.iterator();
        while (iterator2.hasNext()) {
            r.a(iterator2.next().a(n2));
        }
        return r;
    }
    
    public final r a(final f f) {
        if (f == null) {
            return this;
        }
        return f.a(this.a);
    }
    
    public final r a(final StopWords stopWords) {
        if (stopWords == null) {
            return this;
        }
        final ArrayList<C> list = new ArrayList<C>(this.a.size());
        for (final C c : this.a) {
            if (!stopWords.a(c.b)) {
                list.add(c);
            }
        }
        return new r(list);
    }
    
    public final void a(final C c) {
        this.a.add(c);
    }
    
    public final void a(final r r) {
        this.a.addAll(r.a);
    }
    
    public final int b() {
        return this.a.size();
    }
    
    public final Iterator iterator() {
        return this.a.iterator();
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<C> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(", ");
        }
        return sb.toString();
    }
    
    public final r a(boolean b2) {
        if (!b) {
            return this;
        }
        b2 = (boolean)new ArrayList(this.a.size());
        for (final C c : this.a) {
            try {
                Double.parseDouble(c.b);
            }
            catch (NumberFormatException ex) {
                ((ArrayList<C>)b2).add(c);
            }
        }
        return new r((List)b2);
    }
}
