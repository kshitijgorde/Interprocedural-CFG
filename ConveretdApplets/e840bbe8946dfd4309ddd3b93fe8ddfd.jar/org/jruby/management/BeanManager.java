// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

import org.jruby.compiler.JITCompilerMBean;

public interface BeanManager
{
    void register(final JITCompilerMBean p0);
    
    void register(final ConfigMBean p0);
    
    void register(final ParserStatsMBean p0);
    
    void register(final MethodCacheMBean p0);
    
    void register(final ClassCacheMBean p0);
    
    void register(final Runtime p0);
    
    void unregisterClassCache();
    
    void unregisterCompiler();
    
    void unregisterConfig();
    
    void unregisterMethodCache();
    
    void unregisterParserStats();
    
    void unregisterRuntime();
}
