// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Component;
import java.awt.Rectangle;

public interface LayeredContainer extends DragContainer
{
    Rectangle getArea();
    
    void setMain(final Component p0);
    
    void setBar(final MenuBar p0);
    
    MenuBar getBar();
    
    void removeBar();
    
    void setPopup(final MenuPopup p0);
    
    void removePopup();
    
    void setDirect(final boolean p0);
    
    Component add(final Component p0, final int p1);
    
    void remove(final Component p0);
}
