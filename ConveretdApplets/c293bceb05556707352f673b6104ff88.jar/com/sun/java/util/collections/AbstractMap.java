// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public abstract class AbstractMap implements Map
{
    private transient Set keySet;
    private transient Collection values;
    
    public int size() {
        return this.entrySet().size();
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public boolean containsValue(final Object o) {
        final Iterator iterator = this.entrySet().iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (((Entry)iterator.next()).getValue() == null) {
                    return true;
                }
            }
        }
        else {
            while (iterator.hasNext()) {
                if (o.equals(((Entry)iterator.next()).getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean containsKey(final Object o) {
        final Iterator iterator = this.entrySet().iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (((Entry)iterator.next()).getKey() == null) {
                    return true;
                }
            }
        }
        else {
            while (iterator.hasNext()) {
                if (o.equals(((Entry)iterator.next()).getKey())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Object get(final Object o) {
        final Iterator iterator = this.entrySet().iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                final Entry entry = (Entry)iterator.next();
                if (entry.getKey() == null) {
                    return entry.getValue();
                }
            }
        }
        else {
            while (iterator.hasNext()) {
                final Entry entry2 = (Entry)iterator.next();
                if (o.equals(entry2.getKey())) {
                    return entry2.getValue();
                }
            }
        }
        return null;
    }
    
    public Object put(final Object o, final Object o2) {
        throw new UnsupportedOperationException();
    }
    
    public Object remove(final Object o) {
        final Iterator iterator = this.entrySet().iterator();
        Object o2 = null;
        if (o == null) {
            while (o2 == null) {
                if (!iterator.hasNext()) {
                    break;
                }
                final Entry entry = (Entry)iterator.next();
                if (entry.getKey() != null) {
                    continue;
                }
                o2 = entry;
            }
        }
        else {
            while (o2 == null && iterator.hasNext()) {
                final Entry entry2 = (Entry)iterator.next();
                if (o.equals(entry2.getKey())) {
                    o2 = entry2;
                }
            }
        }
        Object value = null;
        if (o2 != null) {
            value = ((Entry)o2).getValue();
            iterator.remove();
        }
        return value;
    }
    
    public void putAll(final Map map) {
        for (final Entry entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    
    public void clear() {
        this.entrySet().clear();
    }
    
    public Set keySet() {
        if (this.keySet == null) {
            this.keySet = (Set)new AbstractMap.AbstractMap$1(this);
        }
        return this.keySet;
    }
    
    public Collection values() {
        if (this.values == null) {
            this.values = (Collection)new AbstractMap.AbstractMap$3(this);
        }
        return this.values;
    }
    
    public abstract Set entrySet();
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Map)) {
            return false;
        }
        final Map map = (Map)o;
        if (map.size() != this.size()) {
            return false;
        }
        for (final Entry entry : this.entrySet()) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            if (value == null) {
                if (map.get(key) != null || !map.containsKey(key)) {
                    return false;
                }
                continue;
            }
            else {
                if (!value.equals(map.get(key))) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int n = 0;
        final Iterator iterator = this.entrySet().iterator();
        while (iterator.hasNext()) {
            n += iterator.next().hashCode();
        }
        return n;
    }
    
    public String toString() {
        final int n = this.size() - 1;
        final StringBuffer sb = new StringBuffer();
        final Iterator iterator = this.entrySet().iterator();
        sb.append("{");
        for (int i = 0; i <= n; ++i) {
            final Entry entry = (Entry)iterator.next();
            sb.append(String.valueOf(entry.getKey()) + "=" + entry.getValue());
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
