// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import java.util.ArrayList;
import java.util.Iterator;
import com.kenai.jaffl.provider.Invoker;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class Library extends com.kenai.jaffl.provider.Library
{
    private final String[] libraryNames;
    private volatile List<com.kenai.jffi.Library> nativeLibraries;
    
    Library(final String name) {
        this.nativeLibraries = (List<com.kenai.jffi.Library>)Collections.EMPTY_LIST;
        this.libraryNames = new String[] { name };
    }
    
    Library(final String... names) {
        this.nativeLibraries = (List<com.kenai.jffi.Library>)Collections.EMPTY_LIST;
        this.libraryNames = names.clone();
    }
    
    public Invoker getInvoker(final Method method, final Map<LibraryOption, ?> options) {
        return DefaultInvokerFactory.getInstance().createInvoker(method, this, options);
    }
    
    public Object libraryLock() {
        return this;
    }
    
    long getSymbolAddress(final String name) {
        for (final com.kenai.jffi.Library l : this.getNativeLibraries()) {
            final long address = l.getSymbolAddress(name);
            if (address != 0L) {
                return address;
            }
        }
        return 0L;
    }
    
    long findSymbolAddress(final String name) {
        final long address = this.getSymbolAddress(name);
        if (address == 0L) {
            throw new SymbolNotFoundError(com.kenai.jffi.Library.getLastError());
        }
        return address;
    }
    
    private synchronized List<com.kenai.jffi.Library> getNativeLibraries() {
        if (!this.nativeLibraries.isEmpty()) {
            return this.nativeLibraries;
        }
        return this.nativeLibraries = this.loadNativeLibraries();
    }
    
    private synchronized List<com.kenai.jffi.Library> loadNativeLibraries() {
        final List<com.kenai.jffi.Library> libs = new ArrayList<com.kenai.jffi.Library>();
        final List<String> errors = new ArrayList<String>(0);
        for (final String libraryName : this.libraryNames) {
            com.kenai.jffi.Library lib = com.kenai.jffi.Library.getCachedInstance(libraryName, 1);
            final String path;
            if (lib == null && libraryName != null && (path = com.kenai.jaffl.provider.Library.locateLibrary(libraryName)) != null && !libraryName.equals(path)) {
                lib = com.kenai.jffi.Library.getCachedInstance(path, 1);
            }
            if (lib == null) {
                throw new UnsatisfiedLinkError(com.kenai.jffi.Library.getLastError());
            }
            libs.add(lib);
        }
        return Collections.unmodifiableList((List<? extends com.kenai.jffi.Library>)libs);
    }
}
