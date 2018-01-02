// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.HashMap;

public class LRUCachePolicy implements CachePolicy
{
    protected HashMap m_map;
    protected LRUList m_list;
    protected int m_maxCapacity;
    protected int m_minCapacity;
    
    public LRUCachePolicy() {
    }
    
    public LRUCachePolicy(final int min, final int max) {
        if (min < 2 || min > max) {
            throw new IllegalArgumentException("Illegal cache capacities");
        }
        this.m_minCapacity = min;
        this.m_maxCapacity = max;
    }
    
    public void create() {
        this.m_map = new HashMap();
        this.m_list = this.createList();
        this.m_list.m_maxCapacity = this.m_maxCapacity;
        this.m_list.m_minCapacity = this.m_minCapacity;
        this.m_list.m_capacity = this.m_maxCapacity;
    }
    
    public void start() {
    }
    
    public void stop() {
        if (this.m_list != null) {
            this.flush();
        }
    }
    
    public void destroy() {
        if (this.m_map != null) {
            this.m_map.clear();
        }
        if (this.m_list != null) {
            this.m_list.clear();
        }
    }
    
    public Object get(final Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Requesting an object using a null key");
        }
        final LRUCacheEntry value = this.m_map.get(key);
        if (value != null) {
            this.m_list.promote(value);
            return value.m_object;
        }
        this.cacheMiss();
        return null;
    }
    
    public Object peek(final Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Requesting an object using a null key");
        }
        final LRUCacheEntry value = this.m_map.get(key);
        if (value == null) {
            return null;
        }
        return value.m_object;
    }
    
    public void insert(final Object key, final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot insert a null object in the cache");
        }
        if (key == null) {
            throw new IllegalArgumentException("Cannot insert an object in the cache with null key");
        }
        if (this.m_map.containsKey(key)) {
            throw new IllegalStateException("Attempt to put in the cache an object that is already there");
        }
        this.m_list.demote();
        final LRUCacheEntry entry = this.createCacheEntry(key, o);
        this.m_map.put(key, entry);
        this.m_list.promote(entry);
    }
    
    public void remove(final Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Removing an object using a null key");
        }
        final Object value = this.m_map.remove(key);
        if (value != null) {
            this.m_list.remove((LRUCacheEntry)value);
        }
    }
    
    public void flush() {
        LRUCacheEntry entry = null;
        while ((entry = this.m_list.m_tail) != null) {
            this.ageOut(entry);
        }
    }
    
    public int size() {
        return this.m_list.m_count;
    }
    
    protected LRUList createList() {
        return new LRUList();
    }
    
    protected void ageOut(final LRUCacheEntry entry) {
        this.remove(entry.m_key);
    }
    
    protected void cacheMiss() {
    }
    
    protected LRUCacheEntry createCacheEntry(final Object key, final Object value) {
        return new LRUCacheEntry(key, value);
    }
    
    public class LRUList
    {
        public int m_maxCapacity;
        public int m_minCapacity;
        public int m_capacity;
        public int m_count;
        public LRUCacheEntry m_head;
        public LRUCacheEntry m_tail;
        public int m_cacheMiss;
        
        protected LRUList() {
            this.m_head = null;
            this.m_tail = null;
            this.m_count = 0;
        }
        
        protected void promote(final LRUCacheEntry entry) {
            if (entry == null) {
                throw new IllegalArgumentException("Trying to promote a null object");
            }
            if (this.m_capacity < 1) {
                throw new IllegalStateException("Can't work with capacity < 1");
            }
            this.entryPromotion(entry);
            entry.m_time = System.currentTimeMillis();
            if (entry.m_prev == null) {
                if (entry.m_next == null) {
                    if (this.m_count == 0) {
                        this.m_head = entry;
                        this.m_tail = entry;
                        ++this.m_count;
                        this.entryAdded(entry);
                    }
                    else if (this.m_count != 1 || this.m_head != entry) {
                        if (this.m_count < this.m_capacity) {
                            entry.m_prev = null;
                            entry.m_next = this.m_head;
                            this.m_head.m_prev = entry;
                            this.m_head = entry;
                            ++this.m_count;
                            this.entryAdded(entry);
                        }
                        else {
                            if (this.m_count >= this.m_maxCapacity) {
                                throw new IllegalStateException("Attempt to put a new cache entry on a full cache");
                            }
                            entry.m_prev = null;
                            entry.m_next = this.m_head;
                            this.m_head.m_prev = entry;
                            this.m_head = entry;
                            ++this.m_count;
                            final int oldCapacity = this.m_capacity;
                            ++this.m_capacity;
                            this.entryAdded(entry);
                            this.capacityChanged(oldCapacity);
                        }
                    }
                }
            }
            else if (entry.m_next == null) {
                final LRUCacheEntry beforeLast = entry.m_prev;
                beforeLast.m_next = null;
                entry.m_prev = null;
                entry.m_next = this.m_head;
                this.m_head.m_prev = entry;
                this.m_head = entry;
                this.m_tail = beforeLast;
            }
            else {
                final LRUCacheEntry previous = entry.m_prev;
                previous.m_next = entry.m_next;
                entry.m_next.m_prev = previous;
                entry.m_prev = null;
                entry.m_next = this.m_head;
                this.m_head.m_prev = entry;
                this.m_head = entry;
            }
        }
        
        protected void demote() {
            if (this.m_capacity < 1) {
                throw new IllegalStateException("Can't work with capacity < 1");
            }
            if (this.m_capacity <= this.m_maxCapacity && this.m_count > this.m_maxCapacity) {
                throw new IllegalStateException("Cache list entries number (" + this.m_count + ") > than the maximum allowed (" + this.m_maxCapacity + ")");
            }
            if (this.m_count >= this.m_maxCapacity) {
                final LRUCacheEntry entry = this.m_tail;
                LRUCachePolicy.this.ageOut(entry);
            }
        }
        
        protected void remove(final LRUCacheEntry entry) {
            if (entry == null) {
                throw new IllegalArgumentException("Cannot remove a null entry from the cache");
            }
            if (this.m_count < 1) {
                throw new IllegalStateException("Trying to remove an entry from an empty cache");
            }
            final Object o = null;
            entry.m_object = o;
            entry.m_key = o;
            if (this.m_count == 1) {
                final LRUCacheEntry lruCacheEntry = null;
                this.m_tail = lruCacheEntry;
                this.m_head = lruCacheEntry;
            }
            else if (entry.m_prev == null) {
                this.m_head = entry.m_next;
                this.m_head.m_prev = null;
                entry.m_next = null;
            }
            else if (entry.m_next == null) {
                this.m_tail = entry.m_prev;
                this.m_tail.m_next = null;
                entry.m_prev = null;
            }
            else {
                entry.m_next.m_prev = entry.m_prev;
                entry.m_prev.m_next = entry.m_next;
                entry.m_prev = null;
                entry.m_next = null;
            }
            --this.m_count;
            this.entryRemoved(entry);
        }
        
        protected void entryPromotion(final LRUCacheEntry entry) {
        }
        
        protected void entryAdded(final LRUCacheEntry entry) {
        }
        
        protected void entryRemoved(final LRUCacheEntry entry) {
        }
        
        protected void capacityChanged(final int oldCapacity) {
        }
        
        protected void clear() {
            LRUCacheEntry entry = this.m_head;
            this.m_head = null;
            this.m_tail = null;
            this.m_count = 0;
            while (entry != null) {
                this.entryRemoved(entry);
                entry = entry.m_next;
            }
        }
        
        public String toString() {
            String s = Integer.toHexString(super.hashCode());
            s = s + " size: " + this.m_count;
            for (LRUCacheEntry entry = this.m_head; entry != null; entry = entry.m_next) {
                s = s + "\n" + entry;
            }
            return s;
        }
    }
    
    public class LRUCacheEntry
    {
        public LRUCacheEntry m_next;
        public LRUCacheEntry m_prev;
        public Object m_key;
        public Object m_object;
        public long m_time;
        
        protected LRUCacheEntry(final Object key, final Object object) {
            this.m_key = key;
            this.m_object = object;
            this.m_next = null;
            this.m_prev = null;
            this.m_time = 0L;
        }
        
        public String toString() {
            return "key: " + this.m_key + ", object: " + ((this.m_object == null) ? "null" : Integer.toHexString(this.m_object.hashCode())) + ", entry: " + Integer.toHexString(super.hashCode());
        }
    }
}
