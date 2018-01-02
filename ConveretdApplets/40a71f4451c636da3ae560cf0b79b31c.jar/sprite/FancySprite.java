// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Rectangle;

public interface FancySprite
{
    void setColorBalance(final int p0, final int p1, final int p2, final int p3);
    
    boolean setShadowOffset(final int p0, final int p1, final boolean p2);
    
    boolean drawFancy(final int[] p0, final int p1, final int p2, final Rectangle p3);
}
