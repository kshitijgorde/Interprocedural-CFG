// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.Edge;
import prefuse.data.Node;
import java.util.Iterator;

public class NeighborIterator implements Iterator
{
    private Iterator m_edges;
    private Node m_node;
    
    public NeighborIterator(final Node node, final Iterator edges) {
        this.m_node = node;
        this.m_edges = edges;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    public boolean hasNext() {
        return this.m_edges.hasNext();
    }
    
    public Object next() {
        return this.m_edges.next().getAdjacentNode(this.m_node);
    }
}
