// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Component;

public interface DragContainer
{
    void addDragListener(final DragListener p0);
    
    void removeDragListener(final DragListener p0);
    
    void addLayer(final Component p0, final int p1, final int p2);
    
    void dragLayer(final Component p0, final int p1, final int p2);
    
    void removeLayer(final Component p0);
    
    void setInfo(final Component p0, final String p1);
}
