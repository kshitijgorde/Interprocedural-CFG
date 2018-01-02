// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

public interface JITCompilerMBean
{
    long getSuccessCount();
    
    long getCompileCount();
    
    long getFailCount();
    
    long getAbandonCount();
    
    long getCompileTime();
    
    long getCodeSize();
    
    long getAverageCodeSize();
    
    long getAverageCompileTime();
    
    long getLargestCodeSize();
}
