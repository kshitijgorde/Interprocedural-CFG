// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.Map;

public final class Library
{
    private static final Map<String, WeakReference<Library>> cache;
    private static final Object lock;
    private static final ThreadLocal<String> lastError;
    public static final int LAZY = 1;
    public static final int NOW = 2;
    public static final int LOCAL = 4;
    public static final int GLOBAL = 8;
    private final long handle;
    private final String name;
    private final Foreign foreign;
    
    private static long dlopen(final Foreign foreign, final String name, final int flags) {
        try {
            return foreign.dlopen(name, flags);
        }
        catch (UnsatisfiedLinkError ex) {
            Library.lastError.set(ex.getMessage());
            return 0L;
        }
    }
    
    public static final Library getDefault() {
        return DefaultLibrary.INSTANCE;
    }
    
    public static final Library getCachedInstance(final String name, final int flags) {
        if (name == null) {
            return getDefault();
        }
        final WeakReference<Library> ref = Library.cache.get(name);
        Library lib = (ref != null) ? ref.get() : null;
        if (lib != null) {
            return lib;
        }
        lib = openLibrary(name, flags);
        if (lib == null) {
            return null;
        }
        Library.cache.put(name, new WeakReference<Library>(lib));
        return lib;
    }
    
    public static final Library openLibrary(final String name, int flags) {
        if (flags == 0) {
            flags = 5;
        }
        final Foreign foreign = Foreign.getInstance();
        final long address = dlopen(foreign, name, flags);
        return (address != 0L) ? new Library(foreign, name, address) : null;
    }
    
    private Library(final Foreign foreign, final String name, final long address) {
        this.foreign = foreign;
        this.name = name;
        this.handle = address;
    }
    
    public final long getSymbolAddress(final String name) {
        try {
            return this.foreign.dlsym(this.handle, name);
        }
        catch (UnsatisfiedLinkError ex) {
            Library.lastError.set(this.foreign.dlerror());
            return 0L;
        }
    }
    
    public static final String getLastError() {
        final String error = Library.lastError.get();
        return (error != null) ? error : "unknown";
    }
    
    protected void finalize() throws Throwable {
        try {
            if (this.handle != 0L) {
                this.foreign.dlclose(this.handle);
            }
        }
        finally {
            super.finalize();
        }
    }
    
    static {
        cache = new ConcurrentHashMap<String, WeakReference<Library>>();
        lock = new Object();
        lastError = new ThreadLocal<String>();
    }
    
    private static final class DefaultLibrary
    {
        private static final Library INSTANCE;
        
        static {
            INSTANCE = Library.openLibrary(null, 9);
        }
    }
}
