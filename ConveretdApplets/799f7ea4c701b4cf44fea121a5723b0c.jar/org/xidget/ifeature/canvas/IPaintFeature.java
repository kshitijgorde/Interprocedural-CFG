// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.canvas;

public interface IPaintFeature<T>
{
    void setLayer(final String p0);
    
    String getLayer();
    
    void paint(final T p0);
}
