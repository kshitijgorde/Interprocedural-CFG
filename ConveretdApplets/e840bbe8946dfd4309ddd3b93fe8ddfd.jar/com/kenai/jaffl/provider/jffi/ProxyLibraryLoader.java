// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.provider.NativeInvocationHandler;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;

class ProxyLibraryLoader extends LibraryLoader
{
    private static final LibraryLoader INSTANCE;
    
    static final LibraryLoader getInstance() {
        return ProxyLibraryLoader.INSTANCE;
    }
    
     <T> T loadLibrary(final Library library, final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        return interfaceClass.cast(NativeInvocationHandler.wrapInterface(library, interfaceClass, libraryOptions));
    }
    
    boolean isInterfaceSupported(final Class interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        return true;
    }
    
    static {
        INSTANCE = new ProxyLibraryLoader();
    }
}
