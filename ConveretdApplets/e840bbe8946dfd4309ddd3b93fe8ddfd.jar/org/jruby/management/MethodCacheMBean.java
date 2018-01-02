// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

public interface MethodCacheMBean
{
    int getAddCount();
    
    int getRemoveCount();
    
    int getModuleIncludeCount();
    
    int getModuleTriggeredRemoveCount();
    
    int getFlushCount();
    
    int getCallSiteCount();
    
    int getFailedCallSiteCount();
    
    void flush();
}
