// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Graphics;
import java.awt.Image;

public interface NFGraphOffscreenObserver
{
    boolean preOffscreenDraw(final Image p0, final Graphics p1);
    
    void postOffscreenDraw(final Image p0, final Graphics p1);
}
