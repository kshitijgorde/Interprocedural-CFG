// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import com.stonewall.cornerstone.utility.Transaction;
import java.util.concurrent.locks.ReentrantLock;
import com.stonewall.cornerstone.entity.Entity;
import java.util.Map;

public class EntityCache
{
    private String key;
    private Map<String, Entity> map;
    private static final ReentrantLock lock;
    
    static {
        lock = new ReentrantLock();
    }
    
    public static EntityCache getCache(final String key) throws CacheException {
        final Transaction tran = Transaction.getCurrent();
        if (tran == null) {
            throw new CacheException("Cannot locate current transaction");
        }
        final EntityCache cache = tran.userData().get(key);
        if (cache == null) {
            throw new CacheException("Cannot locate cache with key:" + key);
        }
        return cache;
    }
    
    public EntityCache() {
        this.map = new HashMap<String, Entity>();
    }
    
    public void register(final String key) throws CacheException {
        EntityCache.lock.lock();
        try {
            final Transaction tran = Transaction.getCurrent();
            if (tran == null) {
                throw new CacheException("Cannot locate a transaction.");
            }
            final EntityCache cache = tran.userData().get(key);
            if (cache != null) {
                throw new CacheException("Cache with key:" + key + " already exists");
            }
            this.key = key;
            final Map<String, Object> data = tran.userData();
            data.put(key, this);
        }
        finally {
            EntityCache.lock.unlock();
        }
        EntityCache.lock.unlock();
    }
    
    public void clearCache() {
        this.map = new HashMap<String, Entity>();
    }
    
    public void cache(final Collection<? extends Entity> entities) {
        for (final Entity entity : entities) {
            this.map.put(entity.getId(), entity);
        }
    }
    
    public void cache(final Entity entity) {
        this.map.put(entity.getId(), entity);
    }
    
    public <T> T get(final String id) {
        return (T)this.map.get(id);
    }
    
    public <T> Iterator<T> iterator() {
        final Collection<T> values = (Collection<T>)this.map.values();
        return values.iterator();
    }
    
    public void dispose() {
        final Transaction tran = Transaction.getCurrent();
        if (tran == null) {
            return;
        }
        final Map<String, Object> data = tran.userData();
        data.remove(this.key);
    }
}
