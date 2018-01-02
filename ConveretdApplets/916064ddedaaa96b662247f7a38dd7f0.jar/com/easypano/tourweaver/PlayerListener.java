// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

public interface PlayerListener
{
    void sceneSwitching(final String p0);
    
    void sceneSwitched(final String p0);
    
    void movieSwitching(final String p0);
    
    void mapSwitching(final String p0);
    
    void mapSwitched(final String p0);
    
    void moviePaused(final String p0);
    
    void movieStoped(final String p0);
}
