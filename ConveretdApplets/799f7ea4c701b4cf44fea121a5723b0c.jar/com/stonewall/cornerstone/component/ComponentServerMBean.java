// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

public interface ComponentServerMBean
{
    void halt();
    
    void shutdown();
    
    void restart();
    
    void trace(final String p0);
    
    void setLog(final String p0, final String p1);
}
