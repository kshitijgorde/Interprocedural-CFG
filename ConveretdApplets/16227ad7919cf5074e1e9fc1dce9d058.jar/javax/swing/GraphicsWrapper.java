// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Graphics;
import java.awt.Rectangle;

interface GraphicsWrapper
{
    int getClipHeight();
    
    int getClipWidth();
    
    int getClipX();
    
    int getClipY();
    
    boolean isClipIntersecting(final Rectangle p0);
    
    Graphics subGraphics();
}
