// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public abstract class AbstractMap2D implements Map2D
{
    protected Map map;
    protected int size;
    
    public void clear() {
        if (this.map != null) {
            this.map.clear();
        }
    }
    
    public boolean containsKey(final Object o, final Object o2) {
        return this.map != null && this.map.containsKey(o) && this.map.get(o).containsKey(o2);
    }
    
    public boolean containsKey(final Object o) {
        return this.map != null && this.map.containsKey(o);
    }
    
    public boolean containsValue(final Object o) {
        if (this.map == null) {
            return false;
        }
        synchronized (this.map) {
            final Iterator<Map> iterator = this.map.values().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().containsValue(o)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Set entrySet() {
        final HashSet<Entry> set = new HashSet<Entry>();
        final Set<Object> keySet = this.map.keySet();
        synchronized (keySet) {
            for (final Object next : keySet) {
                for (final Object next2 : keySet) {
                    set.add(new Entry(next, next2, this.get(next, next2)));
                }
            }
        }
        return set;
    }
    
    public Object get(final Object o, final Object o2) {
        final Map value = this.get(o);
        if (value == null) {
            return null;
        }
        return value.get(o2);
    }
    
    public Map get(final Object o) {
        if (this.map == null) {
            return null;
        }
        return this.map.get(o);
    }
    
    public boolean isEmpty() {
        return this.map == null || this.map.isEmpty();
    }
    
    public Set keySet() {
        if (this.map == null) {
            return Collections.EMPTY_SET;
        }
        return this.map.keySet();
    }
    
    protected abstract Map createMap();
    
    public Object put(final Object o, final Object o2, final Object o3) {
        if (this.map == null) {
            this.map = this.createMap();
        }
        Map<Object, Object> map;
        if (!this.map.containsKey(o)) {
            map = (Map<Object, Object>)this.createMap();
            this.map.put(o, map);
        }
        else {
            map = this.map.get(o);
        }
        Map<Object, Object> map2;
        if (o != o2 && !this.map.containsKey(o2)) {
            map2 = (Map<Object, Object>)this.createMap();
            this.map.put(o2, map2);
        }
        else {
            map2 = this.map.get(o2);
        }
        final Object put = map.put(o2, o3);
        map2.put(o, o3);
        if (put == null) {
            ++this.size;
        }
        return put;
    }
    
    public void putAll(final Map2D map2D) {
        for (Entry entry : map2D.entrySet()) {}
    }
    
    public void remove(final Object o, final Object o2) {
        if (this.map == null) {
            return;
        }
        if (!this.map.containsKey(o) || !this.map.containsKey(o2)) {
            return;
        }
        final Map<Object, Object> map = this.map.get(o);
        Map<Object, Object> map2;
        if (o != o2) {
            map2 = this.map.get(o2);
        }
        else {
            map2 = map;
        }
        final Object remove = map.remove(o2);
        map2.remove(o);
        if (remove != null) {
            --this.size;
        }
    }
    
    public void remove(final Object o) {
        if (this.map == null) {
            return;
        }
        if (!this.map.containsKey(o)) {
            return;
        }
        final Iterator<Object> iterator = this.map.get(o).keySet().iterator();
        while (iterator.hasNext()) {
            ((Map)this.map.get(iterator.next())).remove(o);
        }
        this.size -= this.map.get(o).size();
        this.map.remove(o);
    }
    
    public int size() {
        return this.size;
    }
    
    public Collection values() {
        final Collection<Map> values = this.map.values();
        if (values == null) {
            return Collections.EMPTY_SET;
        }
        final HashSet set = new HashSet();
        synchronized (values) {
            final Iterator<Map> iterator = values.iterator();
            while (iterator.hasNext()) {
                final Collection values2 = iterator.next().values();
                synchronized (values2) {
                    set.addAll(values2);
                    continue;
                }
                break;
            }
        }
        return set;
    }
    
    public String toString() {
        return "[AbstractMap2D : " + this.map + " ] ";
    }
    
    private class Entry implements Map2D.Entry
    {
        Object key1;
        Object key2;
        Object value;
        
        public Entry(final Object key1, final Object key2, final Object value) {
            this.key1 = key1;
            this.key2 = key2;
            this.value = value;
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public Object getKey1() {
            return this.key1;
        }
        
        public Object getKey2() {
            return this.key2;
        }
    }
}
