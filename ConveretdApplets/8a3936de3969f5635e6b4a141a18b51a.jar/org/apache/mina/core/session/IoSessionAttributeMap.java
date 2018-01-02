// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class IoSessionAttributeMap
{
    private final Map<Object, Object> attributes;
    
    public Object getAttribute$1ce32dd9(Object value, final Object o) {
        if (value == null) {
            throw new IllegalArgumentException("key");
        }
        if ((value = this.attributes.get(value)) == null) {
            return o;
        }
        return value;
    }
    
    public Object setAttribute$1ce32dd9(final Object o, final Object o2) {
        if (o == null) {
            throw new IllegalArgumentException("key");
        }
        if (o2 == null) {
            return this.attributes.remove(o);
        }
        return this.attributes.put(o, o2);
    }
    
    public Object setAttributeIfAbsent$1ce32dd9(final Object o, final Object o2) {
        if (o == null) {
            throw new IllegalArgumentException("key");
        }
        if (o2 == null) {
            return null;
        }
        final Object value;
        synchronized (this.attributes) {
            if ((value = this.attributes.get(o)) == null) {
                this.attributes.put(o, o2);
            }
        }
        return value;
    }
    
    public Object removeAttribute$518970c3(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("key");
        }
        return this.attributes.remove(o);
    }
    
    public boolean containsAttribute$6f142e53(final Object o) {
        return this.attributes.containsKey(o);
    }
    
    public IoSessionAttributeMap() {
        this.attributes = new ConcurrentHashMap<Object, Object>(4);
    }
}
