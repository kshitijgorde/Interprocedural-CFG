// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.util.Iterator;
import hypergraph.graphApi.Element;
import hypergraph.hyperbolic.ModelPoint;
import hypergraph.hyperbolic.Complex;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.GraphEvent;
import hypergraph.graphApi.Graph;

public class RandomLayout extends AbstractGraphLayout
{
    public RandomLayout() {
    }
    
    public RandomLayout(final Graph graph) {
        this.setGraph(graph);
    }
    
    public void elementsAdded(final GraphEvent graphEvent) {
        this.invalidate();
        final Element element = graphEvent.getElement();
        if (element instanceof Node) {
            this.getGraphLayoutModel().setNodePosition((Node)element, new Complex(Math.random() * 1.4 - 0.7, Math.random() * 1.4 - 0.7));
        }
    }
    
    public void layout() {
        if (this.getGraphLayoutModel() == null) {
            this.setGraphLayoutModel(new DefaultGraphLayoutModel());
        }
        final Iterator<Node> iterator = this.getGraph().getNodes().iterator();
        while (iterator.hasNext()) {
            this.getGraphLayoutModel().setNodePosition(iterator.next(), new Complex(Math.random() * 1.4 - 0.7, Math.random() * 1.4 - 0.7));
        }
        this.getGraphLayoutModel().setValid(true);
    }
    
    public boolean isExpandingEnabled() {
        return false;
    }
}
