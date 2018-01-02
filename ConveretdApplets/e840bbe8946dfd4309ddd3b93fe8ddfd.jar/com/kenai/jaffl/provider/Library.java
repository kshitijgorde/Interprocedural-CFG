// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.kenai.jaffl.Platform;
import java.util.Collection;
import java.util.LinkedList;
import java.io.File;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import java.lang.reflect.Method;

public abstract class Library
{
    public abstract Invoker getInvoker(final Method p0, final Map<LibraryOption, ?> p1);
    
    public abstract Object libraryLock();
    
    public static String locateLibrary(final String libraryName) {
        if (new File(libraryName).isAbsolute()) {
            return libraryName;
        }
        final List<String> searchPath = new LinkedList<String>();
        searchPath.addAll(0, com.kenai.jaffl.Library.getLibraryPath(libraryName));
        searchPath.addAll(StaticDataHolder.userLibraryPath);
        final String path = Platform.getPlatform().locateLibrary(libraryName, searchPath);
        return (path != null) ? path : null;
    }
    
    private static final List<String> getPropertyPaths(final String propName) {
        final String value = System.getProperty(propName);
        if (value != null) {
            final String[] paths = value.split(File.pathSeparator);
            return new ArrayList<String>(Arrays.asList(paths));
        }
        return Collections.emptyList();
    }
    
    private static final class StaticDataHolder
    {
        private static final List<String> userLibraryPath;
        
        static {
            (userLibraryPath = new CopyOnWriteArrayList<String>()).addAll(getPropertyPaths("jaffl.library.path"));
            StaticDataHolder.userLibraryPath.addAll(getPropertyPaths("jna.library.path"));
        }
    }
}
