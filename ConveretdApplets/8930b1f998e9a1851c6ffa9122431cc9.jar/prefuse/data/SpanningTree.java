// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import java.util.Iterator;
import java.util.BitSet;
import java.util.LinkedList;
import prefuse.data.tuple.TupleManager;
import prefuse.visual.tuple.TableEdgeItem;

public class SpanningTree extends Tree
{
    public static final String SOURCE_EDGE = "source";
    protected static final Schema EDGE_SCHEMA;
    protected Graph m_backing;
    
    public SpanningTree(final Graph backing, final Node node) {
        super(backing.getNodeTable(), SpanningTree.EDGE_SCHEMA.instantiate());
        this.m_backing = backing;
        final TupleManager tupleManager = new TupleManager(this.getEdgeTable(), null, TableEdgeItem.class) {
            public Tuple getTuple(final int n) {
                return SpanningTree.this.m_backing.getEdge(this.m_table.getInt(n, "source"));
            }
        };
        this.getEdgeTable().setTupleManager(tupleManager);
        super.setTupleManagers(backing.m_nodeTuples, tupleManager);
        this.buildSpanningTree(node);
    }
    
    public void buildSpanningTree(final Node root) {
        super.clearEdges();
        super.setRoot(root);
        final LinkedList<Node> list = new LinkedList<Node>();
        final BitSet set = new BitSet();
        list.add(root);
        set.set(root.getRow());
        final Table edgeTable = this.getEdgeTable();
        while (!list.isEmpty()) {
            final Node node = list.removeFirst();
            final Iterator edges = node.edges();
            while (edges.hasNext()) {
                final Edge edge = edges.next();
                final Node adjacentNode = edge.getAdjacentNode(node);
                if (!set.get(adjacentNode.getRow())) {
                    list.add(adjacentNode);
                    set.set(adjacentNode.getRow());
                    edgeTable.setInt(super.addChildEdge(node.getRow(), adjacentNode.getRow()), "source", edge.getRow());
                }
            }
        }
    }
    
    public int addChild(final int n) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public Node addChild(final Node node) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public int addChildEdge(final int n, final int n2) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public Edge addChildEdge(final Node node, final Node node2) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public Node addRoot() {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public int addRootRow() {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public boolean removeChild(final int n) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public boolean removeChild(final Node node) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public boolean removeChildEdge(final Edge edge) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public boolean removeChildEdge(final int n) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    void setRoot(final Node node) {
        throw new UnsupportedOperationException("Changes to tree structure not allowed for spanning trees.");
    }
    
    public int addEdge(final int n, final int n2) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public Edge addEdge(final Node node, final Node node2) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public Node addNode() {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public int addNodeRow() {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public void clear() {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public boolean removeEdge(final Edge edge) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public boolean removeEdge(final int n) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public boolean removeNode(final int n) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public boolean removeNode(final Node node) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public boolean removeTuple(final Tuple tuple) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public void setEdgeTable(final Table table) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    public void setTupleManagers(final TupleManager tupleManager, final TupleManager tupleManager2) {
        throw new UnsupportedOperationException("Changes to graph structure not allowed for spanning trees.");
    }
    
    static {
        (EDGE_SCHEMA = new Schema()).addColumn(SpanningTree.DEFAULT_SOURCE_KEY, Integer.TYPE, new Integer(-1));
        SpanningTree.EDGE_SCHEMA.addColumn(SpanningTree.DEFAULT_TARGET_KEY, Integer.TYPE, new Integer(-1));
        SpanningTree.EDGE_SCHEMA.addColumn("source", Integer.TYPE);
    }
}
