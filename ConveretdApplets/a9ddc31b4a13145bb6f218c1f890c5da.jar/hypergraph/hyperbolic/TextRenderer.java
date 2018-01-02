// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;

public interface TextRenderer extends Renderer
{
    void configure(final ModelPanel p0, final ModelPoint p1, final String p2);
    
    Font getScaledFont(final Point2D p0);
    
    void setColor(final Color p0);
    
    void setBackground(final Color p0);
}
