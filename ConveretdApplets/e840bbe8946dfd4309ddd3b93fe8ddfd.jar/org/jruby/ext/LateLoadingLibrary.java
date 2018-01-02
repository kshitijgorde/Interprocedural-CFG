// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import java.io.IOException;
import org.jruby.runtime.load.LoadService;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class LateLoadingLibrary implements Library
{
    private final String libraryName;
    private final String className;
    private ClassLoader classLoader;
    
    public LateLoadingLibrary(final String libraryName, final String className, final ClassLoader classLoader) {
        this.libraryName = libraryName;
        this.className = className;
        this.classLoader = classLoader;
    }
    
    public synchronized void load(final Ruby runtime, final boolean wrap) throws IOException {
        LoadService.reflectedLoad(runtime, this.libraryName, this.className, this.classLoader, wrap);
    }
}
