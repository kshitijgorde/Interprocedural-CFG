// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Graph;
import prefuse.data.Edge;

public class TableEdge extends TableTuple implements Edge
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
}
