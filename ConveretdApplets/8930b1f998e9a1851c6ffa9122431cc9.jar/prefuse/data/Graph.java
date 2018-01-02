// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import prefuse.data.column.Column;
import prefuse.data.event.ColumnListener;
import prefuse.util.PrefuseConfig;
import prefuse.data.event.GraphListener;
import prefuse.util.collections.CompositeIterator;
import prefuse.data.expression.Predicate;
import prefuse.data.util.NeighborIterator;
import java.util.Iterator;
import prefuse.util.collections.CompositeIntIterator;
import prefuse.util.collections.IntArrayIterator;
import prefuse.util.collections.IntIterator;
import prefuse.data.event.TableListener;
import prefuse.data.tuple.TableEdge;
import prefuse.data.tuple.TableNode;
import prefuse.data.tuple.TupleSet;
import prefuse.util.TypeLib;
import prefuse.util.collections.CopyOnWriteArrayList;
import prefuse.data.util.Index;
import prefuse.data.tuple.TupleManager;
import prefuse.data.tuple.CompositeTupleSet;

public class Graph extends CompositeTupleSet
{
    public static final int INEDGES = 0;
    public static final int OUTEDGES = 1;
    public static final int UNDIRECTED = 2;
    public static final String DEFAULT_NODE_KEY;
    public static final String DEFAULT_SOURCE_KEY;
    public static final String DEFAULT_TARGET_KEY;
    public static final String NODES;
    public static final String EDGES;
    protected Table m_links;
    protected TupleManager m_nodeTuples;
    protected TupleManager m_edgeTuples;
    protected boolean m_directed;
    protected SpanningTree m_spanning;
    protected String m_nkey;
    protected String m_skey;
    protected String m_tkey;
    protected Index m_nidx;
    protected boolean m_longKey;
    private Listener m_listener;
    private CopyOnWriteArrayList m_listeners;
    protected static final String INDEGREE = "_indegree";
    protected static final String OUTDEGREE = "_outdegree";
    protected static final String INLINKS = "_inlinks";
    protected static final String OUTLINKS = "_outlinks";
    protected static final Schema LINKS_SCHEMA;
    
    public Graph() {
        this(false);
    }
    
    public Graph(final boolean b) {
        this(new Table(), b);
    }
    
    public Graph(final Table table, final boolean b) {
        this(table, b, Graph.DEFAULT_NODE_KEY, Graph.DEFAULT_SOURCE_KEY, Graph.DEFAULT_TARGET_KEY);
    }
    
    public Graph(final Table table, final boolean b, final String s, final String s2, final String s3) {
        this.m_directed = false;
        this.m_spanning = null;
        this.m_longKey = false;
        this.m_listeners = new CopyOnWriteArrayList();
        final Table table2 = new Table();
        table2.addColumn(s2, Integer.TYPE, new Integer(-1));
        table2.addColumn(s3, Integer.TYPE, new Integer(-1));
        this.init(table, table2, b, s, s2, s3);
    }
    
    public Graph(final Table table, final Table table2, final boolean b) {
        this(table, table2, b, Graph.DEFAULT_NODE_KEY, Graph.DEFAULT_SOURCE_KEY, Graph.DEFAULT_TARGET_KEY);
    }
    
    public Graph(final Table table, final Table table2, final boolean b, final String s, final String s2) {
        this.m_directed = false;
        this.m_spanning = null;
        this.m_longKey = false;
        this.m_listeners = new CopyOnWriteArrayList();
        this.init(table, table2, b, Graph.DEFAULT_NODE_KEY, s, s2);
    }
    
    public Graph(final Table table, final Table table2, final boolean b, final String s, final String s2, final String s3) {
        this.m_directed = false;
        this.m_spanning = null;
        this.m_longKey = false;
        this.m_listeners = new CopyOnWriteArrayList();
        this.init(table, table2, b, s, s2, s3);
    }
    
    protected void init(final Table table, final Table edgeTable, final boolean directed, final String nkey, final String skey, final String tkey) {
        if ((nkey != null && !TypeLib.isIntegerType(table.getColumnType(nkey))) || !TypeLib.isIntegerType(edgeTable.getColumnType(skey)) || !TypeLib.isIntegerType(edgeTable.getColumnType(tkey))) {
            throw new IllegalArgumentException("Incompatible column types for graph keys");
        }
        this.removeAllSets();
        super.addSet(Graph.EDGES, edgeTable);
        super.addSet(Graph.NODES, table);
        this.m_directed = directed;
        this.m_nkey = nkey;
        this.m_skey = skey;
        this.m_tkey = tkey;
        if (nkey != null) {
            if (table.getColumnType(nkey) == Long.TYPE) {
                this.m_longKey = true;
            }
            table.index(nkey);
            this.m_nidx = table.getIndex(nkey);
        }
        if (this.m_nodeTuples == null) {
            this.m_nodeTuples = new TupleManager(table, this, TableNode.class);
        }
        this.m_edgeTuples = new TupleManager(edgeTable, this, TableEdge.class);
        this.initLinkTable();
        if (this.m_listener == null) {
            this.m_listener = new Listener();
        }
        table.addTableListener(this.m_listener);
        edgeTable.addTableListener(this.m_listener);
        this.m_listener.setEdgeTable(edgeTable);
    }
    
    public void setTupleManagers(final TupleManager nodeTuples, final TupleManager edgeTuples) {
        if (!Node.class.isAssignableFrom(nodeTuples.getTupleType())) {
            throw new IllegalArgumentException("The provided node TupleManager must generate tuples that implement the Node interface.");
        }
        if (!Edge.class.isAssignableFrom(edgeTuples.getTupleType())) {
            throw new IllegalArgumentException("The provided edge TupleManager must generate tuples that implement the Edge interface.");
        }
        this.m_nodeTuples = nodeTuples;
        this.m_edgeTuples = edgeTuples;
    }
    
    public void dispose() {
        this.getNodeTable().removeTableListener(this.m_listener);
        this.getEdgeTable().removeTableListener(this.m_listener);
    }
    
    public void setEdgeTable(final Table table) {
        this.getEdgeTable().removeTableListener(this.m_listener);
        this.m_edgeTuples.invalidateAll();
        this.m_links.clear();
        this.init(this.getNodeTable(), table, this.m_directed, this.m_nkey, this.m_skey, this.m_tkey);
    }
    
    protected void initLinkTable() {
        this.m_links = this.createLinkTable();
        final IntIterator rows = this.getEdgeTable().rows();
        while (rows.hasNext()) {
            this.updateDegrees(rows.nextInt(), 1);
        }
    }
    
    protected Table createLinkTable() {
        return Graph.LINKS_SCHEMA.instantiate(this.getNodeTable().getMaximumRow() + 1);
    }
    
    protected void updateDegrees(final int n, final int n2) {
        if (!this.getEdgeTable().isValidRow(n)) {
            return;
        }
        final int sourceNode = this.getSourceNode(n);
        final int targetNode = this.getTargetNode(n);
        if (sourceNode < 0 || targetNode < 0) {
            return;
        }
        this.updateDegrees(n, sourceNode, targetNode, n2);
        if (n2 < 0) {
            this.m_edgeTuples.invalidate(n);
        }
    }
    
    protected void updateDegrees(final int n, final int n2, final int n3, final int n4) {
        final int int1 = this.m_links.getInt(n2, "_outdegree");
        final int int2 = this.m_links.getInt(n3, "_indegree");
        if (n4 > 0) {
            this.addLink("_outlinks", int1, n2, n);
            this.addLink("_inlinks", int2, n3, n);
        }
        else if (n4 < 0) {
            this.remLink("_outlinks", int1, n2, n);
            this.remLink("_inlinks", int2, n3, n);
        }
        this.m_links.setInt(n2, "_outdegree", int1 + n4);
        this.m_links.setInt(n3, "_indegree", int2 + n4);
        this.m_spanning = null;
    }
    
    protected void addLink(final String s, final int n, final int n2, final int n3) {
        int[] array = (int[])this.m_links.get(n2, s);
        if (array == null) {
            this.m_links.set(n2, s, new int[] { n3 });
            return;
        }
        if (n == array.length) {
            final int[] array2 = new int[Math.max(3 * array.length / 2, n + 1)];
            System.arraycopy(array, 0, array2, 0, array.length);
            array = array2;
            this.m_links.set(n2, s, array);
        }
        array[n] = n3;
    }
    
    protected boolean remLink(final String s, final int n, final int n2, final int n3) {
        final int[] array = (int[])this.m_links.get(n2, s);
        for (int i = 0; i < n; ++i) {
            if (array[i] == n3) {
                System.arraycopy(array, i + 1, array, i, n - i - 1);
                return true;
            }
        }
        return false;
    }
    
    protected void updateNodeData(final int n, final boolean b) {
        if (b) {
            this.m_links.addRow();
        }
        else {
            this.m_nodeTuples.invalidate(n);
            this.m_links.removeRow(n);
        }
    }
    
    public String getNodeKeyField() {
        return this.m_nkey;
    }
    
    public String getEdgeSourceField() {
        return this.m_skey;
    }
    
    public String getEdgeTargetField() {
        return this.m_tkey;
    }
    
    public long getKey(final int n) {
        return (this.m_nkey == null) ? n : this.getNodeTable().getLong(n, this.m_nkey);
    }
    
    public int getNodeIndex(final long n) {
        if (this.m_nidx == null) {
            return (int)n;
        }
        final int n2 = this.m_longKey ? this.m_nidx.get(n) : this.m_nidx.get((int)n);
        return (n2 < 0) ? -1 : n2;
    }
    
    public int addNodeRow() {
        return this.getNodeTable().addRow();
    }
    
    public Node addNode() {
        return (Node)this.m_nodeTuples.getTuple(this.addNodeRow());
    }
    
    public int addEdge(final int n, final int n2) {
        final long key = this.getKey(n);
        final long key2 = this.getKey(n2);
        final Table edgeTable = this.getEdgeTable();
        final int addRow = edgeTable.addRow();
        if (this.m_longKey) {
            edgeTable.setLong(addRow, this.m_skey, key);
            edgeTable.setLong(addRow, this.m_tkey, key2);
        }
        else {
            edgeTable.setInt(addRow, this.m_skey, (int)key);
            edgeTable.setInt(addRow, this.m_tkey, (int)key2);
        }
        return addRow;
    }
    
    public Edge addEdge(final Node node, final Node node2) {
        this.nodeCheck(node, true);
        this.nodeCheck(node2, true);
        return this.getEdge(this.addEdge(node.getRow(), node2.getRow()));
    }
    
    public boolean removeNode(final int n) {
        final Table nodeTable = this.getNodeTable();
        if (nodeTable.isValidRow(n)) {
            final int inDegree = this.getInDegree(n);
            if (inDegree > 0) {
                final int[] array = (int[])this.m_links.get(n, "_inlinks");
                int n2 = inDegree;
                while (--n2 >= 0) {
                    this.removeEdge(array[n2]);
                }
            }
            final int outDegree = this.getOutDegree(n);
            if (outDegree > 0) {
                final int[] array2 = (int[])this.m_links.get(n, "_outlinks");
                int n3 = outDegree;
                while (--n3 >= 0) {
                    this.removeEdge(array2[n3]);
                }
            }
        }
        return nodeTable.removeRow(n);
    }
    
    public boolean removeNode(final Node node) {
        this.nodeCheck(node, true);
        return this.removeNode(node.getRow());
    }
    
    public boolean removeEdge(final int n) {
        return this.getEdgeTable().removeRow(n);
    }
    
    public boolean removeEdge(final Edge edge) {
        this.edgeCheck(edge, true);
        return this.removeEdge(edge.getRow());
    }
    
    protected void clearEdges() {
        this.getEdgeTable().clear();
    }
    
    protected boolean nodeCheck(final Node node, final boolean b) {
        if (!node.isValid()) {
            if (b) {
                throw new IllegalArgumentException("Node must be valid.");
            }
            return false;
        }
        else {
            final Graph graph = node.getGraph();
            if (graph == this || graph.m_spanning == this) {
                return true;
            }
            if (b) {
                throw new IllegalArgumentException("Node must be part of this Graph.");
            }
            return false;
        }
    }
    
    public TupleSet getNodes() {
        return this.getSet(Graph.NODES);
    }
    
    public Table getNodeTable() {
        return (Table)this.getSet(Graph.NODES);
    }
    
    public int getNodeCount() {
        return this.getNodeTable().getRowCount();
    }
    
    public Node getNode(final int n) {
        return (Node)this.m_nodeTuples.getTuple(n);
    }
    
    public Node getNodeFromKey(final long n) {
        final int nodeIndex = this.getNodeIndex(n);
        return (nodeIndex < 0) ? null : this.getNode(nodeIndex);
    }
    
    public int getInDegree(final int n) {
        return this.m_links.getInt(n, "_indegree");
    }
    
    public int getInDegree(final Node node) {
        this.nodeCheck(node, true);
        return this.getInDegree(node.getRow());
    }
    
    public int getOutDegree(final int n) {
        return this.m_links.getInt(n, "_outdegree");
    }
    
    public int getOutDegree(final Node node) {
        this.nodeCheck(node, true);
        return this.getOutDegree(node.getRow());
    }
    
    public int getDegree(final int n) {
        return this.getInDegree(n) + this.getOutDegree(n);
    }
    
    public int getDegree(final Node node) {
        this.nodeCheck(node, true);
        return this.getDegree(node.getRow());
    }
    
    public boolean isDirected() {
        return this.m_directed;
    }
    
    protected boolean edgeCheck(final Edge edge, final boolean b) {
        if (!edge.isValid()) {
            if (b) {
                throw new IllegalArgumentException("Edge must be valid.");
            }
            return false;
        }
        else {
            if (edge.getGraph() == this) {
                return true;
            }
            if (b) {
                throw new IllegalArgumentException("Edge must be part of this Graph.");
            }
            return false;
        }
    }
    
    public TupleSet getEdges() {
        return this.getSet(Graph.EDGES);
    }
    
    public Table getEdgeTable() {
        return (Table)this.getSet(Graph.EDGES);
    }
    
    public int getEdgeCount() {
        return this.getEdgeTable().getRowCount();
    }
    
    public Edge getEdge(final int n) {
        return (n < 0) ? null : ((Edge)this.m_edgeTuples.getTuple(n));
    }
    
    public int getEdge(final int n, final int n2) {
        final int outDegree = this.getOutDegree(n);
        if (outDegree > 0) {
            final int[] array = (int[])this.m_links.get(n, "_outlinks");
            for (int i = 0; i < outDegree; ++i) {
                if (this.getTargetNode(array[i]) == n2) {
                    return array[i];
                }
            }
        }
        return -1;
    }
    
    public Edge getEdge(final Node node, final Node node2) {
        this.nodeCheck(node, true);
        this.nodeCheck(node2, true);
        return this.getEdge(this.getEdge(node.getRow(), node2.getRow()));
    }
    
    public int getSourceNode(final int n) {
        return this.getNodeIndex(this.getEdgeTable().getLong(n, this.m_skey));
    }
    
    public Node getSourceNode(final Edge edge) {
        this.edgeCheck(edge, true);
        return this.getNode(this.getSourceNode(edge.getRow()));
    }
    
    public int getTargetNode(final int n) {
        return this.getNodeIndex(this.getEdgeTable().getLong(n, this.m_tkey));
    }
    
    public Node getTargetNode(final Edge edge) {
        this.edgeCheck(edge, true);
        return this.getNode(this.getTargetNode(edge.getRow()));
    }
    
    public int getAdjacentNode(final int n, final int n2) {
        final int sourceNode = this.getSourceNode(n);
        final int targetNode = this.getTargetNode(n);
        if (sourceNode == n2) {
            return targetNode;
        }
        if (targetNode == n2) {
            return sourceNode;
        }
        throw new IllegalArgumentException("Edge is not incident on the input node.");
    }
    
    public Node getAdjacentNode(final Edge edge, final Node node) {
        this.edgeCheck(edge, true);
        this.nodeCheck(node, true);
        return this.getNode(this.getAdjacentNode(edge.getRow(), node.getRow()));
    }
    
    public IntIterator nodeRows() {
        return this.getNodeTable().rows();
    }
    
    public IntIterator edgeRows() {
        return this.getEdgeTable().rows();
    }
    
    public IntIterator edgeRows(final int n) {
        return this.edgeRows(n, 2);
    }
    
    public IntIterator edgeRows(final int n, final int n2) {
        if (n2 == 1) {
            return new IntArrayIterator((int[])this.m_links.get(n, "_outlinks"), 0, this.getOutDegree(n));
        }
        if (n2 == 0) {
            return new IntArrayIterator((int[])this.m_links.get(n, "_inlinks"), 0, this.getInDegree(n));
        }
        if (n2 == 2) {
            return new CompositeIntIterator(this.edgeRows(n, 1), this.edgeRows(n, 0));
        }
        throw new IllegalArgumentException("Unrecognized edge type: " + n2 + ". Type should be one of Graph.OUTEDGES, " + "Graoh.INEDGES, or Graph.ALL");
    }
    
    public IntIterator inEdgeRows(final int n) {
        return this.edgeRows(n, 0);
    }
    
    public IntIterator outEdgeRows(final int n) {
        return this.edgeRows(n, 1);
    }
    
    public Iterator nodes() {
        return this.m_nodeTuples.iterator(this.nodeRows());
    }
    
    public Iterator neighbors(final Node node) {
        return new NeighborIterator(node, this.edges(node));
    }
    
    public Iterator inNeighbors(final Node node) {
        return new NeighborIterator(node, this.inEdges(node));
    }
    
    public Iterator outNeighbors(final Node node) {
        return new NeighborIterator(node, this.outEdges(node));
    }
    
    public Iterator edges() {
        return this.m_edgeTuples.iterator(this.edgeRows());
    }
    
    public Iterator edges(final Node node) {
        this.nodeCheck(node, true);
        return this.m_edgeTuples.iterator(this.edgeRows(node.getRow(), 2));
    }
    
    public Iterator inEdges(final Node node) {
        this.nodeCheck(node, true);
        return this.m_edgeTuples.iterator(this.inEdgeRows(node.getRow()));
    }
    
    public Iterator outEdges(final Node node) {
        this.nodeCheck(node, true);
        return this.m_edgeTuples.iterator(this.outEdgeRows(node.getRow()));
    }
    
    public void clear() {
        this.m_nodeTuples.invalidateAll();
        this.m_edgeTuples.invalidateAll();
        super.clear();
        this.m_links.clear();
    }
    
    public boolean removeTuple(final Tuple tuple) {
        if (tuple instanceof Node) {
            return this.removeNode((Node)tuple);
        }
        if (tuple instanceof Edge) {
            return this.removeEdge((Edge)tuple);
        }
        throw new IllegalArgumentException("Input tuple must be part of this graph");
    }
    
    public Iterator tuples(final Predicate predicate) {
        if (predicate == null) {
            return this.tuples();
        }
        return new CompositeIterator(this.m_edgeTuples.iterator(this.getEdgeTable().rows(predicate)), this.m_nodeTuples.iterator(this.getNodeTable().rows(predicate)));
    }
    
    public Iterator tuples() {
        return new CompositeIterator(this.edges(), this.nodes());
    }
    
    public Tree getSpanningTree() {
        if (this.m_spanning == null) {
            return this.getSpanningTree(this.nodes().next());
        }
        return this.m_spanning;
    }
    
    public Tree getSpanningTree(final Node node) {
        this.nodeCheck(node, true);
        if (this.m_spanning == null) {
            this.m_spanning = new SpanningTree(this, node);
        }
        else if (this.m_spanning.getRoot() != node) {
            this.m_spanning.buildSpanningTree(node);
        }
        return this.m_spanning;
    }
    
    public void clearSpanningTree() {
        this.m_spanning = null;
    }
    
    public void addGraphModelListener(final GraphListener graphListener) {
        if (!this.m_listeners.contains(graphListener)) {
            this.m_listeners.add(graphListener);
        }
    }
    
    public void removeGraphModelListener(final GraphListener graphListener) {
        this.m_listeners.remove(graphListener);
    }
    
    protected void fireGraphEvent(final Table table, final int n, final int n2, final int n3, final int n4) {
        final String s = (table == this.getNodeTable()) ? Graph.NODES : Graph.EDGES;
        if (n4 != 0) {
            this.fireTupleEvent(table, n, n2, n4);
        }
        if (!this.m_listeners.isEmpty()) {
            final Object[] array = this.m_listeners.getArray();
            for (int i = 0; i < array.length; ++i) {
                ((GraphListener)array[i]).graphChanged(this, s, n, n2, n3, n4);
            }
        }
    }
    
    static {
        DEFAULT_NODE_KEY = PrefuseConfig.get("data.graph.nodeKey");
        DEFAULT_SOURCE_KEY = PrefuseConfig.get("data.graph.sourceKey");
        DEFAULT_TARGET_KEY = PrefuseConfig.get("data.graph.targetKey");
        NODES = PrefuseConfig.get("data.graph.nodeGroup");
        EDGES = PrefuseConfig.get("data.graph.edgeGroup");
        LINKS_SCHEMA = new Schema();
        final Integer n = new Integer(0);
        Graph.LINKS_SCHEMA.addColumn("_indegree", Integer.TYPE, n);
        Graph.LINKS_SCHEMA.addColumn("_outdegree", Integer.TYPE, n);
        Graph.LINKS_SCHEMA.addColumn("_inlinks", int[].class);
        Graph.LINKS_SCHEMA.addColumn("_outlinks", int[].class);
        Graph.LINKS_SCHEMA.lockSchema();
    }
    
    protected class Listener implements TableListener, ColumnListener
    {
        private Table m_edges;
        private Column m_scol;
        private Column m_tcol;
        private int m_sidx;
        private int m_tidx;
        
        public void setEdgeTable(final Table edges) {
            if (this.m_scol != null) {
                this.m_scol.removeColumnListener(this);
            }
            if (this.m_tcol != null) {
                this.m_tcol.removeColumnListener(this);
            }
            final Column column = null;
            this.m_tcol = column;
            this.m_scol = column;
            final int n = -1;
            this.m_tidx = n;
            this.m_sidx = n;
            this.m_edges = edges;
            if (this.m_edges != null) {
                this.m_sidx = edges.getColumnNumber(Graph.this.m_skey);
                this.m_tidx = edges.getColumnNumber(Graph.this.m_tkey);
                this.m_scol = edges.getColumn(this.m_sidx);
                this.m_tcol = edges.getColumn(this.m_tidx);
                this.m_scol.addColumnListener(this);
                this.m_tcol.addColumnListener(this);
            }
        }
        
        public void tableChanged(final Table table, final int n, final int n2, final int n3, final int n4) {
            if (!Graph.this.containsSet(table)) {
                throw new IllegalStateException("Graph shouldn't be listening to an unrelated table");
            }
            if (n4 != 0) {
                if (table == Graph.this.getNodeTable()) {
                    if (n3 == -1) {
                        final boolean b = n4 == 1;
                        for (int i = n; i <= n2; ++i) {
                            Graph.this.updateNodeData(i, b);
                        }
                    }
                }
                else if (n3 == -1) {
                    final boolean b2 = n4 == 1;
                    for (int j = n; j <= n2; ++j) {
                        Graph.this.updateDegrees(n, b2 ? 1 : -1);
                    }
                }
                Graph.this.m_spanning = null;
            }
            Graph.this.fireGraphEvent(table, n, n2, n3, n4);
        }
        
        public void columnChanged(final Column column, final int n, final int n2) {
            this.columnChanged(column, n, (long)n2);
        }
        
        public void columnChanged(final Column column, final int n, final long n2) {
            if (column != this.m_scol && column != this.m_tcol) {
                throw new IllegalStateException();
            }
            final boolean b = column == this.m_scol;
            final int tableRow = this.m_edges.getTableRow(n, b ? this.m_sidx : this.m_tidx);
            if (tableRow == -1) {
                return;
            }
            final int sourceNode = Graph.this.getSourceNode(tableRow);
            final int targetNode = Graph.this.getTargetNode(tableRow);
            final int nodeIndex = Graph.this.getNodeIndex(n2);
            if (nodeIndex > -1 && ((b && targetNode > -1) || (!b && sourceNode > -1))) {
                Graph.this.updateDegrees(tableRow, b ? nodeIndex : sourceNode, b ? targetNode : nodeIndex, -1);
            }
            if (sourceNode > -1 && targetNode > -1) {
                Graph.this.updateDegrees(tableRow, sourceNode, targetNode, 1);
            }
        }
        
        public void columnChanged(final Column column, final int n, final int n2, final int n3) {
            throw new IllegalStateException();
        }
        
        public void columnChanged(final Column column, final int n, final float n2) {
            throw new IllegalStateException();
        }
        
        public void columnChanged(final Column column, final int n, final double n2) {
            throw new IllegalStateException();
        }
        
        public void columnChanged(final Column column, final int n, final boolean b) {
            throw new IllegalStateException();
        }
        
        public void columnChanged(final Column column, final int n, final Object o) {
            throw new IllegalStateException();
        }
    }
}
