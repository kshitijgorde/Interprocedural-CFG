// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

public interface VDUDisplay
{
    void redraw();
    
    void updateScrollBar();
    
    void setVDUBuffer(final VDUBuffer p0);
    
    VDUBuffer getVDUBuffer();
}
