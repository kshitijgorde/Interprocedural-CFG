// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

public interface ProfilerMBean
{
    String showReport();
    
    void traceReport();
    
    void clear();
    
    boolean getEnabled();
    
    void setEnabled(final boolean p0);
}
