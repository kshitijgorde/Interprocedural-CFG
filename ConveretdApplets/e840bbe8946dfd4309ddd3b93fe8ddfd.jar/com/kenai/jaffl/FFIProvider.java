// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

import com.kenai.jaffl.provider.NativeType;
import java.util.Map;
import com.kenai.jaffl.provider.MemoryManager;

public abstract class FFIProvider
{
    public static final FFIProvider getProvider() {
        return SingletonHolder.INSTANCE;
    }
    
    public abstract MemoryManager getMemoryManager();
    
    public abstract <T> T loadLibrary(final String p0, final Class<T> p1, final Map<LibraryOption, ?> p2);
    
    public abstract <T> T loadLibrary(final Class<T> p0, final Map<LibraryOption, ?> p1, final String... p2);
    
    public abstract int getLastError();
    
    public abstract void setLastError(final int p0);
    
    public abstract Type getType(final NativeType p0);
    
    private static final class SingletonHolder
    {
        private static final FFIProvider INSTANCE;
        
        private static final FFIProvider getInstance() {
            final boolean useJNA = Boolean.getBoolean("jaffl.usejna");
            FFIProvider provider = null;
            final String prefix = FFIProvider.class.getPackage().getName() + ".provider";
            if (!useJNA) {
                try {
                    provider = (FFIProvider)Class.forName(prefix + ".jffi.Provider").newInstance();
                }
                catch (Throwable t) {}
            }
            if (provider == null) {
                try {
                    provider = (FFIProvider)Class.forName(prefix + ".jna.JNAProvider").newInstance();
                }
                catch (Throwable ex) {
                    throw new RuntimeException("Could not load FFI provider", ex);
                }
            }
            return provider;
        }
        
        static {
            INSTANCE = getInstance();
        }
    }
}
