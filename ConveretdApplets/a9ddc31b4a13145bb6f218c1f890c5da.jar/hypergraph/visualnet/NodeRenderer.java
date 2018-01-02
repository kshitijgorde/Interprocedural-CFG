// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.graphApi.Node;
import hypergraph.hyperbolic.ModelPoint;
import hypergraph.hyperbolic.Renderer;

public interface NodeRenderer extends Renderer
{
    void configure(final GraphPanel p0, final ModelPoint p1, final Node p2);
}
