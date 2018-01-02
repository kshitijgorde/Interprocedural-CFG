// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.graphApi.Edge;
import hypergraph.hyperbolic.Renderer;

public interface EdgeRenderer extends Renderer
{
    void configure(final GraphPanel p0, final Edge p1);
    
    void setLabelVisible(final boolean p0);
    
    boolean isLabelVisible();
}
