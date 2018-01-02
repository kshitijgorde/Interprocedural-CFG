// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Library
{
    private static final Map<String, List<String>> customSearchPaths;
    private final String name;
    
    private Library(final String libraryName) {
        this.name = libraryName;
    }
    
    public static <T> T loadLibrary(final String libraryName, final Class<T> interfaceClass) {
        final Map<LibraryOption, ?> options = Collections.emptyMap();
        return loadLibrary(libraryName, interfaceClass, options);
    }
    
    public static <T> T loadLibrary(final Class<T> interfaceClass, final String... libraryNames) {
        final Map<LibraryOption, ?> options = Collections.emptyMap();
        return loadLibrary(interfaceClass, options, libraryNames);
    }
    
    public static <T> T loadLibrary(final String libraryName, final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions) {
        return FFIProvider.getProvider().loadLibrary(libraryName, interfaceClass, libraryOptions);
    }
    
    public static <T> T loadLibrary(final Class<T> interfaceClass, final Map<LibraryOption, ?> libraryOptions, final String... libraryNames) {
        return FFIProvider.getProvider().loadLibrary(interfaceClass, libraryOptions, libraryNames);
    }
    
    public static final synchronized void addLibraryPath(final String libraryName, final File path) {
        List<String> customPaths = Library.customSearchPaths.get(libraryName);
        if (customPaths == null) {
            customPaths = new CopyOnWriteArrayList<String>();
            Library.customSearchPaths.put(libraryName, customPaths);
        }
        customPaths.add(path.getAbsolutePath());
    }
    
    public static List<String> getLibraryPath(final String libraryName) {
        final List<String> customPaths = Library.customSearchPaths.get(libraryName);
        if (customPaths != null) {
            return customPaths;
        }
        return Collections.emptyList();
    }
    
    public static final Library getInstance(final String libraryName) {
        return new Library(libraryName);
    }
    
    public boolean hasFunction(final String function) {
        return false;
    }
    
    public String getName() {
        return this.name;
    }
    
    static {
        customSearchPaths = new ConcurrentHashMap<String, List<String>>();
    }
}
