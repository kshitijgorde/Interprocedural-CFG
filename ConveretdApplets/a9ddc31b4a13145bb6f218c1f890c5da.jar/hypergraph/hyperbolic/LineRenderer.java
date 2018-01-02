// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.Color;

public interface LineRenderer extends Renderer
{
    void configure(final ModelPanel p0, final ModelPoint p1, final ModelPoint p2);
    
    void setColor(final Color p0);
}
