// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import hypergraph.graphApi.Graph;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.GraphEvent;
import java.util.EventObject;

public class GraphEventImpl extends EventObject implements GraphEvent
{
    private Element element;
    
    public GraphEventImpl(final Graph graph) {
        super(graph);
    }
    
    public GraphEventImpl(final Graph graph, final Element element) {
        this(graph);
        this.element = element;
    }
    
    public Element getElement() {
        return this.element;
    }
}
