// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

public interface NFGraphObserver
{
    void graphDrawn(final Graphics p0);
    
    void graphTooSmall(final Dimension p0);
}
