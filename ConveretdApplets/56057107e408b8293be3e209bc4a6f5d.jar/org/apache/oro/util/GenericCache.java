// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.oro.util;

import java.util.Iterator;
import java.util.HashMap;
import java.io.Serializable;

public abstract class GenericCache implements Cache, Serializable
{
    public static final int DEFAULT_CAPACITY = 20;
    int _numEntries;
    GenericCacheEntry[] _cache;
    HashMap _table;
    
    GenericCache(int n) {
        this._numEntries = 0;
        this._table = new HashMap(n);
        this._cache = new GenericCacheEntry[n];
        while (--n >= 0) {
            this._cache[n] = new GenericCacheEntry(n);
        }
    }
    
    public abstract void addElement(final Object p0, final Object p1);
    
    public synchronized Object getElement(final Object o) {
        final GenericCacheEntry value = this._table.get(o);
        if (value != null) {
            return value._value;
        }
        return null;
    }
    
    public final Iterator keys() {
        return this._table.keySet().iterator();
    }
    
    public final int size() {
        return this._numEntries;
    }
    
    public final int capacity() {
        return this._cache.length;
    }
    
    public final boolean isFull() {
        return this._numEntries >= this._cache.length;
    }
}
