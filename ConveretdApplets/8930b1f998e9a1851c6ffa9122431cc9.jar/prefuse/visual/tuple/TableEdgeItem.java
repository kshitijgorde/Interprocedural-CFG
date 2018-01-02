// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.tuple;

import prefuse.visual.NodeItem;
import prefuse.data.Edge;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Graph;
import prefuse.visual.EdgeItem;

public class TableEdgeItem extends TableVisualItem implements EdgeItem
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
    
    public boolean isDirected() {
        return this.m_graph.isDirected();
    }
    
    public Node getSourceNode() {
        return this.m_graph.getSourceNode(this);
    }
    
    public Node getTargetNode() {
        return this.m_graph.getTargetNode(this);
    }
    
    public Node getAdjacentNode(final Node node) {
        return this.m_graph.getAdjacentNode(this, node);
    }
    
    public NodeItem getSourceItem() {
        return (NodeItem)this.getSourceNode();
    }
    
    public NodeItem getTargetItem() {
        return (NodeItem)this.getTargetNode();
    }
    
    public NodeItem getAdjacentItem(final NodeItem nodeItem) {
        return (NodeItem)this.getAdjacentNode(nodeItem);
    }
}
