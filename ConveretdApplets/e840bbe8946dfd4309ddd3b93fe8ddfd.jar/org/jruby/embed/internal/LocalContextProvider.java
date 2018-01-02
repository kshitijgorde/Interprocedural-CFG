// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.util.Map;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.util.ClassCache;
import java.util.List;

public interface LocalContextProvider
{
    @Deprecated
    void setLoadPaths(final List p0);
    
    @Deprecated
    void setClassCache(final ClassCache p0);
    
    RubyInstanceConfig getRubyInstanceConfig();
    
    boolean isRuntimeInitialized();
    
    Ruby getRuntime();
    
    LocalVariableBehavior getLocalVariableBehavior();
    
    BiVariableMap getVarMap();
    
    Map getAttributeMap();
    
    void terminate();
}
