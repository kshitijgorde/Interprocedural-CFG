// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graph;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class BasicGraph
{
    private List<Edge> edges;
    private List<UniqueEdge> uniqueEdges;
    private Map<String, Node> nodes;
    private String comment;
    
    public final void setComment(final String comment) {
        this.comment = comment;
    }
    
    public BasicGraph() {
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
    
    public void addEdge(final Node node, final Node node2, final float n, final int n2) {
        final Edge edge = new Edge(node.getLabel(), node2.getLabel(), n, n2);
        if (!this.getEdges().contains(edge) & edge != null) {
            this.getEdges().add(edge);
            this.addNode(node);
            this.addNode(node2);
            final UniqueEdge uniqueEdge = new UniqueEdge(edge);
            if (!this.uniqueEdges.contains(uniqueEdge)) {
                this.uniqueEdges.add(uniqueEdge);
            }
        }
    }
    
    public void addNode(final Node node) {
        if (!this.nodes.containsKey(node.getLabel())) {
            node.addConnection();
            this.nodes.put(node.getLabel(), node);
        }
        else {
            this.getNode(node.getLabel()).addConnection();
            this.nodes.put(node.getLabel(), node);
        }
    }
    
    public Node getNode(final String s) {
        return this.nodes.get(s);
    }
    
    public void addNode(final String s) {
        final Node node = new Node(s);
        node.addConnection();
        if (!this.nodes.containsKey(s)) {
            this.nodes.put(s, node);
        }
        else {
            final Node node2 = this.getNode(s);
            node2.addConnection();
            this.nodes.put(s, node2);
        }
    }
    
    public boolean hasNode(final String s) {
        return this.nodes.containsKey(s);
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void clear() {
        this.getEdges().clear();
        this.nodes.clear();
        this.uniqueEdges.clear();
        this.comment = "";
    }
    
    public void addComment(final String comment) {
        if (comment == null) {
            return;
        }
        if (comment.length() > 1) {
            this.comment = comment;
        }
    }
    
    public void removeEdge(final Edge edge) {
        if (!this.getEdges().contains(edge)) {
            return;
        }
        this.getEdges().remove(edge);
        this.removeNode(edge.getFromName());
        this.removeNode(edge.getToName());
        final UniqueEdge uniqueEdge = new UniqueEdge(edge);
        if (!this.getEdges().contains(uniqueEdge)) {
            this.uniqueEdges.remove(uniqueEdge);
        }
    }
    
    private void removeNode(final String s) {
        final Node node = this.getNode(s);
        this.nodes.remove(s);
        if (node.removeConnection()) {
            this.nodes.put(s, node);
        }
    }
    
    private void removeNode(final Node node) {
        this.removeNode(node.getLabel());
    }
    
    public Iterator<Node> nodesIterator() {
        return this.nodes.values().iterator();
    }
    
    public Iterator<Edge> edgesIterator() {
        return this.getEdges().iterator();
    }
    
    public void copyNodeSettings(final BasicGraph basicGraph) {
        if (basicGraph == null) {
            return;
        }
        final Iterator<Node> nodesIterator = basicGraph.nodesIterator();
        while (nodesIterator.hasNext()) {
            this.setNode(nodesIterator.next());
        }
    }
    
    public void setNode(final Node node) {
        if (this.nodes.containsKey(node.getLabel())) {
            final Node node2 = this.getNode(node.getLabel());
            node2.setColor(node.getColor());
            node2.setShape(node.getShape());
            node2.setExpand(node.getExpand());
            node2.setX(node.getX());
            node2.setY(node.getY());
            node2.setAnnotation(node.getAnnotation());
        }
    }
    
    public Iterator<UniqueEdge> uniqueEdgesIterator() {
        return this.uniqueEdges.iterator();
    }
    
    public List<Edge> getEdges() {
        return this.edges;
    }
    
    public int getNodeSize() {
        return this.nodes.size();
    }
    
    public int getEdgeSize() {
        return this.getEdges().size();
    }
    
    public int getUniqueEdgeSize() {
        return this.uniqueEdges.size();
    }
}
