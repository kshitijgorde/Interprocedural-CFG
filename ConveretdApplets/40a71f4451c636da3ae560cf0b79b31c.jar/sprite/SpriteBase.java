// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;

public interface SpriteBase
{
    void drawSprite(final Graphics p0);
    
    Rectangle getBounding();
    
    boolean nextFrame(final int p0, final Rectangle p1);
    
    boolean hittest(final int p0, final int p1);
    
    boolean visible();
    
    void show(final boolean p0);
    
    void setPosition(final int p0, final int p1);
    
    boolean init(final Image[] p0, final int[] p1);
    
    void movePosition(final int p0, final int p1);
}
