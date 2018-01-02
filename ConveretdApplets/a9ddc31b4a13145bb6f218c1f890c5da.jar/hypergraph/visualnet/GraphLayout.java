// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.hyperbolic.PropertyManager;
import hypergraph.hyperbolic.Model;
import hypergraph.graphApi.Graph;
import hypergraph.graphApi.GraphListener;

public interface GraphLayout extends GraphListener
{
    void setGraph(final Graph p0);
    
    Graph getGraph();
    
    Model getModel();
    
    void invalidate();
    
    boolean isValid();
    
    void layout();
    
    void setGraphLayoutModel(final GraphLayoutModel p0);
    
    GraphLayoutModel getGraphLayoutModel();
    
    void setProperties(final PropertyManager p0);
    
    boolean isExpandingEnabled();
}
