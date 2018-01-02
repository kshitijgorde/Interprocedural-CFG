// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.tuple;

import prefuse.data.Edge;
import java.util.Iterator;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Graph;
import prefuse.visual.NodeItem;

public class TableNodeItem extends TableVisualItem implements NodeItem
{
    protected Graph m_graph;
    
    protected void init(final Table table, final Graph graph, final int n) {
        this.m_table = table;
        this.m_graph = graph;
        this.m_row = (this.m_table.isValidRow(n) ? n : -1);
    }
    
    public Graph getGraph() {
        return this.m_graph;
    }
    
    public int getInDegree() {
        return this.m_graph.getInDegree(this);
    }
    
    public int getOutDegree() {
        return this.m_graph.getOutDegree(this);
    }
    
    public int getDegree() {
        return this.m_graph.getDegree(this);
    }
    
    public Iterator inEdges() {
        return this.m_graph.inEdges(this);
    }
    
    public Iterator outEdges() {
        return this.m_graph.outEdges(this);
    }
    
    public Iterator edges() {
        return this.m_graph.edges(this);
    }
    
    public Iterator inNeighbors() {
        return this.m_graph.inNeighbors(this);
    }
    
    public Iterator outNeighbors() {
        return this.m_graph.outNeighbors(this);
    }
    
    public Iterator neighbors() {
        return this.m_graph.neighbors(this);
    }
    
    public Node getParent() {
        return this.m_graph.getSpanningTree().getParent(this);
    }
    
    public Edge getParentEdge() {
        return this.m_graph.getSpanningTree().getParentEdge(this);
    }
    
    public int getChildCount() {
        return this.m_graph.getSpanningTree().getChildCount(this.m_row);
    }
    
    public int getChildIndex(final Node node) {
        return this.m_graph.getSpanningTree().getChildIndex(this, node);
    }
    
    public Node getChild(final int n) {
        return this.m_graph.getSpanningTree().getChild(this, n);
    }
    
    public Node getFirstChild() {
        return this.m_graph.getSpanningTree().getFirstChild(this);
    }
    
    public Node getLastChild() {
        return this.m_graph.getSpanningTree().getLastChild(this);
    }
    
    public Node getPreviousSibling() {
        return this.m_graph.getSpanningTree().getPreviousSibling(this);
    }
    
    public Node getNextSibling() {
        return this.m_graph.getSpanningTree().getNextSibling(this);
    }
    
    public Iterator children() {
        return this.m_graph.getSpanningTree().children(this);
    }
    
    public Iterator childEdges() {
        return this.m_graph.getSpanningTree().childEdges(this);
    }
    
    public int getDepth() {
        return this.m_graph.getSpanningTree().getDepth(this.m_row);
    }
}
