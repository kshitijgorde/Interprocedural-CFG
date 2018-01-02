// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola;

import java.net.URL;
import java.util.Vector;
import com.charliemouse.cambozola.shared.CamStream;
import java.awt.Dimension;

public interface ViewerAttributeInterface
{
    Dimension getSize();
    
    PercentArea getViewArea();
    
    CamStream getStream();
    
    boolean isStandalone();
    
    Vector getAccessories();
    
    Vector getAlternateURLs();
    
    void setAlternateURLs(final Vector p0);
    
    void setCurrentURL(final URL p0);
    
    void displayURL(final URL p0, final String p1);
    
    void repaint();
}
