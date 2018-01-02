// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PartialKeyMap<T> implements Map<String, T>
{
    private List<Entry<String, T>> A;
    
    @Override
    public void clear() {
        this.A = null;
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.A(o.toString()) != -1;
    }
    
    @Override
    public boolean containsValue(final Object o) {
        if (this.A != null) {
            final Iterator<Entry<String, T>> iterator = this.A.iterator();
            while (iterator.hasNext()) {
                final Object value = ((Entry<K, Object>)iterator.next()).getValue();
                if (value == null && o == null) {
                    return true;
                }
                if (value != null && o != null && value.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Set<Entry<String, T>> entrySet() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Entry<String, T>>();
        set.addAll(this.A);
        return (Set<Entry<String, T>>)set;
    }
    
    @Override
    public T get(final Object o) {
        final int a = this.A(o);
        return (T)((a < 0) ? null : this.A.get(a).getValue());
    }
    
    @Override
    public boolean isEmpty() {
        return this.A == null;
    }
    
    @Override
    public Set<String> keySet() {
        if (this.A == null) {
            return Collections.emptySet();
        }
        final HashSet<String> set = new HashSet<String>();
        final Iterator<Entry<String, T>> iterator = this.A.iterator();
        while (iterator.hasNext()) {
            set.add(((Entry<String, V>)iterator.next()).getKey());
        }
        return set;
    }
    
    @Override
    public T put(final String s, final T t) {
        if (this.A == null) {
            this.A = new ArrayList<Entry<String, T>>();
        }
        final int b = this.B(s);
        if (b < 0) {
            this.A.add(new _A<Object, T>(s, t));
            return null;
        }
        return (T)this.A.set(b, new _A<Object, T>(s, t)).getValue();
    }
    
    @Override
    public void putAll(final Map<? extends String, ? extends T> map) {
        if (this.A == null) {
            this.A = new ArrayList<Entry<String, T>>();
        }
        for (final Entry<? extends String, ? extends T> entry : map.entrySet()) {
            this.A.add(new _A<Object, T>((String)entry.getKey(), entry.getValue()));
        }
    }
    
    @Override
    public T remove(final Object o) {
        final int b = this.B(o);
        if (b < 0) {
            return null;
        }
        return (T)this.A.remove(b).getValue();
    }
    
    @Override
    public int size() {
        return (this.A != null) ? this.A.size() : 0;
    }
    
    @Override
    public Collection<T> values() {
        if (this.A == null) {
            return (Collection<T>)Collections.emptySet();
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<T>();
        final Iterator<Entry<String, T>> iterator = this.A.iterator();
        while (iterator.hasNext()) {
            set.add(((Entry<K, Object>)iterator.next()).getValue());
        }
        return (Collection<T>)set;
    }
    
    private int A(final Object o) {
        int n = -1;
        int length = 0;
        final String s = (o != null) ? o.toString() : null;
        if (this.A != null) {
            for (int i = 0; i < this.A.size(); ++i) {
                final String s2 = this.A.get(i).getKey();
                if (s == null && s2 == null) {
                    return i;
                }
                if (s != null && s2 != null && s.startsWith(s2) && length < s2.length()) {
                    n = i;
                    length = s2.length();
                }
            }
        }
        return n;
    }
    
    private int B(final Object o) {
        final String s = (o != null) ? o.toString() : null;
        if (this.A != null) {
            for (int i = 0; i < this.A.size(); ++i) {
                final String s2 = this.A.get(i).getKey();
                if (s == null && s2 == null) {
                    return i;
                }
                if (s != null && s2 != null && s.equals(s2)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.A != null) {
            for (final Entry<String, V> entry : this.A) {
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(entry.getValue());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    private static class _A<K, T> implements Entry<String, T>
    {
        private String A;
        private T B;
        
        public _A(final String a, final T b) {
            this.A = a;
            this.B = b;
        }
        
        public String A() {
            return this.A;
        }
        
        @Override
        public T getValue() {
            return this.B;
        }
        
        @Override
        public T setValue(final T b) {
            final T b2 = this.B;
            this.B = b;
            return b2;
        }
    }
}
