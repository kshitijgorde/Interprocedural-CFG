// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.graphApi.GraphEvent;
import hypergraph.graphApi.GraphListener;
import hypergraph.hyperbolic.Model;
import hypergraph.hyperbolic.PropertyManager;
import hypergraph.graphApi.Graph;

public abstract class AbstractGraphLayout implements GraphLayout
{
    private Graph graph;
    private GraphLayoutModel graphLayoutModel;
    private PropertyManager properties;
    private Model model;
    
    public void setGraph(final Graph graph) {
        if (this.graph != null) {
            this.graph.removeGraphListener(this);
        }
        this.graph = graph;
        if (this.graph != null) {
            this.graph.addGraphListener(this);
        }
        this.invalidate();
    }
    
    public Model getModel() {
        return this.model;
    }
    
    protected void setModel(final Model model) {
        this.model = model;
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public void invalidate() {
        if (this.graphLayoutModel != null) {
            this.graphLayoutModel.setValid(false);
        }
    }
    
    public boolean isValid() {
        return this.graphLayoutModel != null && this.graphLayoutModel.isValid();
    }
    
    public void setGraphLayoutModel(final GraphLayoutModel graphLayoutModel) {
        this.graphLayoutModel = graphLayoutModel;
    }
    
    public GraphLayoutModel getGraphLayoutModel() {
        return this.graphLayoutModel;
    }
    
    public void setProperties(final PropertyManager properties) {
        this.properties = properties;
    }
    
    protected PropertyManager getProperties() {
        return this.properties;
    }
    
    public void elementsAdded(final GraphEvent graphEvent) {
        this.invalidate();
    }
    
    public void elementsRemoved(final GraphEvent graphEvent) {
        this.invalidate();
    }
    
    public void structureChanged(final GraphEvent graphEvent) {
        this.invalidate();
    }
}
