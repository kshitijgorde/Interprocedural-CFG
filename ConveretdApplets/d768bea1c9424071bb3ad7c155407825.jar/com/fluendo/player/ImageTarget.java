// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.Component;

public interface ImageTarget
{
    Component getComponent();
    
    void setImage(final Object p0, final double p1, final double p2);
    
    void setImage(final ImageProducer p0, final double p1, final double p2);
    
    void setImage(final Image p0, final double p1, final double p2);
    
    void setImageTime(final long p0);
    
    void setHaveBasetime();
    
    void reset();
}
