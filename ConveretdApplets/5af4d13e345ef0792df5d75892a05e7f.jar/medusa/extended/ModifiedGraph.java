// 
// Decompiled by Procyon v0.5.30
// 

package medusa.extended;

import java.util.HashMap;
import java.util.ArrayList;
import medusa.graph.Node;
import java.util.Map;
import medusa.graph.UniqueEdge;
import medusa.graph.Edge;
import java.util.List;
import medusa.graph.Graph;

public class ModifiedGraph extends Graph
{
    private List<Edge> edges;
    private List<UniqueEdge> uniqueEdges;
    private Map<String, Node> nodes;
    private String comment;
    
    public ModifiedGraph() {
        this.edges = new ArrayList<Edge>();
        this.uniqueEdges = new ArrayList<UniqueEdge>();
        this.nodes = new HashMap<String, Node>();
    }
    
    public void addEdge(final Edge edge) {
        if (!this.getEdges().contains(edge) & edge != null) {
            this.getEdges().add(edge);
            this.addNode(edge.getFromName());
            this.addNode(edge.getToName());
            final UniqueEdge uniqueEdge = new UniqueEdge(edge);
            if (!this.uniqueEdges.contains(uniqueEdge)) {
                this.uniqueEdges.add(uniqueEdge);
            }
        }
    }
}
