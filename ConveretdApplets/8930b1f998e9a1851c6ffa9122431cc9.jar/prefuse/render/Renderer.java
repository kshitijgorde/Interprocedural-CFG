// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import java.awt.image.BufferedImage;
import java.awt.geom.Point2D;
import prefuse.visual.VisualItem;
import java.awt.Graphics2D;

public interface Renderer
{
    public static final Graphics2D DEFAULT_GRAPHICS = (Graphics2D)new BufferedImage(1, 1, 2).getGraphics();
    
    void render(final Graphics2D p0, final VisualItem p1);
    
    boolean locatePoint(final Point2D p0, final VisualItem p1);
    
    void setBounds(final VisualItem p0);
}
