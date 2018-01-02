// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.security.ProtectionDomain;
import java.lang.ref.WeakReference;
import org.jruby.RubyInstanceConfig;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassCache<T>
{
    private final AtomicInteger classLoadCount;
    private final AtomicInteger classReuseCount;
    private final ReferenceQueue referenceQueue;
    private final Map<Object, KeyedClassReference> cache;
    private final ClassLoader classLoader;
    private final int max;
    
    public ClassCache(final ClassLoader classLoader, final int max) {
        this.classLoadCount = new AtomicInteger(0);
        this.classReuseCount = new AtomicInteger(0);
        this.referenceQueue = new ReferenceQueue();
        this.cache = new ConcurrentHashMap<Object, KeyedClassReference>();
        assert classLoader != null : "Null classloader provided for ClassCache";
        this.classLoader = classLoader;
        this.max = max;
    }
    
    public ClassCache(final ClassLoader classLoader) {
        this(classLoader, -1);
    }
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public Class<T> cacheClassByKey(final Object key, final ClassGenerator classGenerator) throws ClassNotFoundException {
        Class<T> contents = null;
        if (RubyInstanceConfig.JIT_CACHE_ENABLED) {
            final WeakReference<Class<T>> weakRef = (WeakReference<Class<T>>)this.cache.get(key);
            if (weakRef != null) {
                contents = weakRef.get();
            }
            if (weakRef == null || contents == null) {
                if (this.isFull()) {
                    return null;
                }
                contents = this.defineClass(classGenerator);
                this.cleanup();
                this.cache.put(key, new KeyedClassReference(key, contents, this.referenceQueue));
            }
            else {
                this.classReuseCount.incrementAndGet();
            }
        }
        else {
            contents = this.defineClass(classGenerator);
        }
        return contents;
    }
    
    protected Class<T> defineClass(final ClassGenerator classGenerator) {
        final String className = classGenerator.name();
        Class contents = null;
        try {
            contents = this.getClassLoader().loadClass(className);
            if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                System.err.println("found jitted code in classloader: " + className);
            }
        }
        catch (ClassNotFoundException cnfe) {
            if (RubyInstanceConfig.JIT_LOADING_DEBUG) {
                System.err.println("no jitted code in classloader for method " + classGenerator + " at class: " + className);
            }
        }
        final OneShotClassLoader oneShotCL = new OneShotClassLoader(this.getClassLoader());
        classGenerator.generate();
        contents = oneShotCL.defineClass(classGenerator.name(), classGenerator.bytecode());
        this.classLoadCount.incrementAndGet();
        return (Class<T>)contents;
    }
    
    public void flush() {
        this.cache.clear();
    }
    
    public boolean isFull() {
        this.cleanup();
        return this.max > 0 && this.cache.size() >= this.max;
    }
    
    public int getClassLoadCount() {
        return this.classLoadCount.get();
    }
    
    public int getLiveClassCount() {
        this.cleanup();
        return this.cache.size();
    }
    
    public int getClassReuseCount() {
        return this.classReuseCount.get();
    }
    
    private void cleanup() {
        KeyedClassReference reference;
        while ((reference = (KeyedClassReference)this.referenceQueue.poll()) != null) {
            this.cache.remove(reference.getKey());
        }
    }
    
    private static class KeyedClassReference<T> extends WeakReference<Class<T>>
    {
        private final Object key;
        
        public KeyedClassReference(final Object key, final Class<T> referent, final ReferenceQueue<Class<T>> referenceQueue) {
            super(referent, referenceQueue);
            this.key = key;
        }
        
        public Object getKey() {
            return this.key;
        }
    }
    
    public static class OneShotClassLoader extends ClassLoader implements ClassDefiningClassLoader
    {
        private static final ProtectionDomain DEFAULT_DOMAIN;
        
        public OneShotClassLoader(final ClassLoader parent) {
            super(parent);
        }
        
        public Class<?> defineClass(final String name, final byte[] bytes) {
            return super.defineClass(name, bytes, 0, bytes.length, OneShotClassLoader.DEFAULT_DOMAIN);
        }
        
        static {
            DEFAULT_DOMAIN = JRubyClassLoader.class.getProtectionDomain();
        }
    }
    
    public interface ClassGenerator
    {
        void generate();
        
        byte[] bytecode();
        
        String name();
    }
}
