// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.canvas;

import org.xidget.IXidget;

public interface ICanvasFeature
{
    void addLayer(final String p0, final int p1);
    
    void removeLayer(final String p0);
    
    void addChild(final IXidget p0);
    
    void removeChild(final IXidget p0);
    
    int getWidth();
    
    int getHeight();
    
    void repaint();
    
    void repaint(final int p0, final int p1, final int p2, final int p3);
}
