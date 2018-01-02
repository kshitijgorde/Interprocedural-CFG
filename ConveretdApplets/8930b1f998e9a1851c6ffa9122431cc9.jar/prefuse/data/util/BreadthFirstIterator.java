// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.Tuple;
import prefuse.data.Edge;
import prefuse.data.Node;
import prefuse.util.collections.Queue;
import java.util.Iterator;

public class BreadthFirstIterator implements Iterator
{
    protected Queue m_queue;
    protected int m_depth;
    protected int m_traversal;
    protected boolean m_includeNodes;
    protected boolean m_includeEdges;
    
    public BreadthFirstIterator() {
        this.m_queue = new Queue();
    }
    
    public BreadthFirstIterator(final Node node, final int n, final int n2) {
        this.m_queue = new Queue();
        this.init(new Node[] { node }, n, n2);
    }
    
    public BreadthFirstIterator(final Iterator iterator, final int n, final int n2) {
        this.m_queue = new Queue();
        this.init(iterator, n, n2);
    }
    
    public void init(final Object o, final int depth, final int traversal) {
        this.m_queue.clear();
        this.m_depth = depth;
        if (traversal < 0 || traversal >= 3) {
            throw new IllegalArgumentException("Unrecognized traversal type: " + traversal);
        }
        this.m_traversal = traversal;
        this.m_includeNodes = (traversal == 0 || traversal == 2);
        this.m_includeEdges = (traversal == 1 || traversal == 2);
        if (this.m_includeNodes) {
            if (o instanceof Node) {
                this.m_queue.add(o, 0);
            }
            else {
                final Iterator iterator = (Iterator)o;
                while (iterator.hasNext()) {
                    this.m_queue.add(iterator.next(), 0);
                }
            }
        }
        else if (o instanceof Node) {
            final Node node = (Node)o;
            this.m_queue.visit(node, 0);
            final Iterator edges = this.getEdges(node);
            while (edges.hasNext()) {
                final Edge edge = edges.next();
                this.m_queue.visit(edge.getAdjacentNode(node), 1);
                if (this.m_queue.getDepth(edge) < 0) {
                    this.m_queue.add(edge, 1);
                }
            }
        }
        else {
            final Iterator iterator2 = (Iterator)o;
            while (iterator2.hasNext()) {
                final Node node2 = iterator2.next();
                this.m_queue.visit(node2, 0);
                final Iterator edges2 = this.getEdges(node2);
                while (edges2.hasNext()) {
                    final Edge edge2 = edges2.next();
                    this.m_queue.visit(edge2.getAdjacentNode(node2), 1);
                    if (this.m_queue.getDepth(edge2) < 0) {
                        this.m_queue.add(edge2, 1);
                    }
                }
            }
        }
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    public boolean hasNext() {
        return !this.m_queue.isEmpty();
    }
    
    protected Iterator getEdges(final Node node) {
        return node.edges();
    }
    
    public int getDepth(final Tuple tuple) {
        return this.m_queue.getDepth(tuple);
    }
    
    public Object next() {
        final Tuple tuple = (Tuple)this.m_queue.removeFirst();
        switch (this.m_traversal) {
            case 0:
            case 2: {
                if (tuple instanceof Edge) {
                    return tuple;
                }
                final Node node = (Node)tuple;
                final int depth = this.m_queue.getDepth(node);
                if (depth < this.m_depth) {
                    final int n = depth + 1;
                    final Iterator edges = this.getEdges(node);
                    while (edges.hasNext()) {
                        final Edge edge = edges.next();
                        final Node adjacentNode = edge.getAdjacentNode(node);
                        if (this.m_includeEdges && this.m_queue.getDepth(edge) < 0) {
                            this.m_queue.add(edge, n);
                        }
                        if (this.m_queue.getDepth(adjacentNode) < 0) {
                            this.m_queue.add(adjacentNode, n);
                        }
                    }
                }
                else if (this.m_includeEdges && depth == this.m_depth) {
                    final Iterator edges2 = this.getEdges(node);
                    while (edges2.hasNext()) {
                        final Edge edge2 = edges2.next();
                        final int depth2 = this.m_queue.getDepth(edge2.getAdjacentNode(node));
                        if (depth2 > 0 && this.m_queue.getDepth(edge2) < 0) {
                            this.m_queue.add(edge2, Math.min(depth, depth2));
                        }
                    }
                }
                return node;
            }
            case 1: {
                final Edge edge3 = (Edge)tuple;
                final Node sourceNode = edge3.getSourceNode();
                final Node targetNode = edge3.getTargetNode();
                final int depth3 = this.m_queue.getDepth(sourceNode);
                final int depth4 = this.m_queue.getDepth(targetNode);
                if (depth3 != depth4) {
                    final Node node2 = (depth4 > depth3) ? targetNode : sourceNode;
                    final int max = Math.max(depth3, depth4);
                    if (max < this.m_depth) {
                        final int n2 = max + 1;
                        final Iterator edges3 = this.getEdges(node2);
                        while (edges3.hasNext()) {
                            final Edge edge4 = edges3.next();
                            if (this.m_queue.getDepth(edge4) >= 0) {
                                continue;
                            }
                            this.m_queue.visit(edge4.getAdjacentNode(node2), n2);
                            this.m_queue.add(edge4, n2);
                        }
                    }
                }
                return edge3;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }
}
