// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import java.awt.geom.Point2D;
import prefuse.visual.VisualItem;
import java.awt.Graphics2D;

public class NullRenderer implements Renderer
{
    public void render(final Graphics2D graphics2D, final VisualItem visualItem) {
    }
    
    public boolean locatePoint(final Point2D point2D, final VisualItem visualItem) {
        return false;
    }
    
    public void setBounds(final VisualItem visualItem) {
        visualItem.setBounds(visualItem.getX(), visualItem.getY(), 0.0, 0.0);
    }
}
