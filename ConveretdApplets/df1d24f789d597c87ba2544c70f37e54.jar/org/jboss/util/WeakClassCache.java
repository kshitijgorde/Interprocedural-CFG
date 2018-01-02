// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.Map;

public abstract class WeakClassCache
{
    protected Map cache;
    
    public WeakClassCache() {
        this.cache = new WeakHashMap();
    }
    
    public Object get(final Class clazz) {
        final Map classLoaderCache = this.getClassLoaderCache(clazz.getClassLoader());
        WeakReference weak = classLoaderCache.get(clazz.getName());
        if (weak != null) {
            final Object result = weak.get();
            if (result != null) {
                return result;
            }
        }
        final Object result = this.instantiate(clazz);
        weak = new WeakReference((T)result);
        classLoaderCache.put(clazz.getName(), weak);
        this.generate(clazz, result);
        return result;
    }
    
    public Object get(final String name, final ClassLoader cl) throws ClassNotFoundException {
        final Class clazz = cl.loadClass(name);
        return this.get(clazz);
    }
    
    protected abstract Object instantiate(final Class p0);
    
    protected abstract void generate(final Class p0, final Object p1);
    
    protected Map getClassLoaderCache(final ClassLoader cl) {
        synchronized (this.cache) {
            Map result = this.cache.get(cl);
            if (result == null) {
                result = CollectionsFactory.createConcurrentReaderMap();
                this.cache.put(cl, result);
            }
            return result;
        }
    }
}
