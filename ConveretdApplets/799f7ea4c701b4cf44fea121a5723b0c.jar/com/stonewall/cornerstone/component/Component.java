// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

public interface Component
{
    void init(final ComponentServer p0) throws Exception;
    
    void shutdown();
    
    void trace();
}
