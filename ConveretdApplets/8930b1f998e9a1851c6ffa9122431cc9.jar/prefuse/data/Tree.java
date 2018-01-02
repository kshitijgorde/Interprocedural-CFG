// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import prefuse.util.PrefuseConfig;
import java.util.Iterator;
import prefuse.util.collections.IntIterator;
import java.util.logging.Logger;

public class Tree extends Graph
{
    private static final Logger s_logger;
    public static final String DEFAULT_SOURCE_KEY;
    public static final String DEFAULT_TARGET_KEY;
    protected int m_root;
    protected static final String CHILDINDEX = "_childIndex";
    protected static final Schema TREE_LINKS_SCHEMA;
    
    public Tree() {
        super(new Table(), false);
        this.m_root = -1;
    }
    
    public Tree(final Table table, final Table table2) {
        this(table, table2, Tree.DEFAULT_SOURCE_KEY, Tree.DEFAULT_TARGET_KEY);
    }
    
    public Tree(final Table table, final Table table2, final String s, final String s2) {
        this(table, table2, Tree.DEFAULT_NODE_KEY, s, s2);
    }
    
    public Tree(final Table table, final Table table2, final String s, final String s2, final String s3) {
        super(table, table2, false, s, s2, s3);
        this.m_root = -1;
        final IntIterator rows = table.rows();
        while (rows.hasNext()) {
            final int nextInt = rows.nextInt();
            if (this.getParent(nextInt) < 0) {
                this.m_root = nextInt;
                break;
            }
        }
    }
    
    void setRoot(final Node node) {
        this.m_root = node.getRow();
    }
    
    protected Table createLinkTable() {
        final Table linkTable = super.createLinkTable();
        linkTable.addColumns(Tree.TREE_LINKS_SCHEMA);
        return linkTable;
    }
    
    protected void updateDegrees(final int n, final int n2, final int n3, final int n4) {
        super.updateDegrees(n, n2, n3, n4);
        final int outDegree = this.getOutDegree(n2);
        if (n4 > 0) {
            this.m_links.setInt(n3, "_childIndex", outDegree - 1);
        }
        else if (n4 < 0) {
            final int[] array = (int[])this.m_links.get(n2, "_outlinks");
            for (int i = 0; i < outDegree; ++i) {
                this.m_links.setInt(this.getTargetNode(array[i]), "_childIndex", i);
            }
            this.m_links.setInt(n3, "_childIndex", -1);
        }
    }
    
    public int addRootRow() {
        if (this.getNodeCount() != 0) {
            throw new IllegalStateException("Can only add a root node to an empty tree");
        }
        return this.m_root = this.addNodeRow();
    }
    
    public Node addRoot() {
        return this.getNode(this.addRootRow());
    }
    
    public int addChild(final int n) {
        final int addNodeRow = super.addNodeRow();
        this.addChildEdge(n, addNodeRow);
        return addNodeRow;
    }
    
    public Node addChild(final Node node) {
        this.nodeCheck(node, true);
        return this.getNode(this.addChild(node.getRow()));
    }
    
    public int addChildEdge(final int n, final int n2) {
        return super.addEdge(n, n2);
    }
    
    public Edge addChildEdge(final Node node, final Node node2) {
        this.nodeCheck(node, true);
        this.nodeCheck(node2, true);
        return this.getEdge(this.addChildEdge(node.getRow(), node2.getRow()));
    }
    
    public boolean removeChildEdge(final int n) {
        return this.removeChild(this.getTargetNode(n));
    }
    
    public boolean removeChildEdge(final Edge edge) {
        this.edgeCheck(edge, true);
        return this.removeChild(this.getTargetNode(edge.getRow()));
    }
    
    public boolean removeChild(final int n) {
        while (this.getChildCount(n) > 0) {
            this.removeChild(this.getLastChildRow(n));
        }
        return this.removeNode(n);
    }
    
    public boolean removeChild(final Node node) {
        this.nodeCheck(node, true);
        return this.removeChild(node.getRow());
    }
    
    public int getRootRow() {
        return this.m_root;
    }
    
    public Node getRoot() {
        return (Node)this.m_nodeTuples.getTuple(this.m_root);
    }
    
    public int getChildRow(final int n, final int n2) {
        final int childCount = this.getChildCount(n);
        if (n2 < 0 || n2 >= childCount) {
            return -1;
        }
        return this.getTargetNode(((int[])this.m_links.get(n, "_outlinks"))[n2]);
    }
    
    public Node getChild(final Node node, final int n) {
        final int childRow = this.getChildRow(node.getRow(), n);
        return (childRow < 0) ? null : this.getNode(childRow);
    }
    
    public int getChildIndex(final int n, final int n2) {
        if (this.getParent(n2) != n) {
            return -1;
        }
        return this.m_links.getInt(n2, "_childIndex");
    }
    
    public int getChildIndex(final Node node, final Node node2) {
        return this.getChildIndex(node.getRow(), node2.getRow());
    }
    
    public int getFirstChildRow(final int n) {
        return this.getChildRow(n, 0);
    }
    
    public Node getFirstChild(final Node node) {
        return this.getChild(node, 0);
    }
    
    public int getLastChildRow(final int n) {
        return this.getChildRow(n, this.getChildCount(n) - 1);
    }
    
    public Node getLastChild(final Node node) {
        return this.getChild(node, node.getChildCount() - 1);
    }
    
    public int getPreviousSiblingRow(final int n) {
        final int parent = this.getParent(n);
        if (parent < 0) {
            return -1;
        }
        final int[] array = (int[])this.m_links.get(parent, "_outlinks");
        final int int1 = this.m_links.getInt(n, "_childIndex");
        return (int1 <= 0) ? -1 : this.getTargetNode(array[int1 - 1]);
    }
    
    public Node getPreviousSibling(final Node node) {
        final int previousSiblingRow = this.getPreviousSiblingRow(node.getRow());
        return (previousSiblingRow < 0) ? null : this.getNode(previousSiblingRow);
    }
    
    public int getNextSiblingRow(final int n) {
        final int parent = this.getParent(n);
        if (parent < 0) {
            return -1;
        }
        final int[] array = (int[])this.m_links.get(parent, "_outlinks");
        final int int1 = this.m_links.getInt(n, "_childIndex");
        final int n2 = this.getChildCount(parent) - 1;
        return (int1 < 0 || int1 >= n2) ? -1 : this.getTargetNode(array[int1 + 1]);
    }
    
    public Node getNextSibling(final Node node) {
        final int nextSiblingRow = this.getNextSiblingRow(node.getRow());
        return (nextSiblingRow < 0) ? null : this.getNode(nextSiblingRow);
    }
    
    public int getDepth(final int n) {
        if (!this.getNodeTable().isValidRow(n)) {
            return -1;
        }
        int n2 = 0;
        for (int parent = n; parent != this.m_root && parent >= 0; parent = this.getParent(parent)) {
            ++n2;
        }
        return n2;
    }
    
    public int getChildCount(final int n) {
        return this.getOutDegree(n);
    }
    
    public int getParentEdge(final int n) {
        if (this.getInDegree(n) > 0) {
            return ((int[])this.m_links.get(n, "_inlinks"))[0];
        }
        return -1;
    }
    
    public Edge getParentEdge(final Node node) {
        this.nodeCheck(node, true);
        final int parentEdge = this.getParentEdge(node.getRow());
        return (parentEdge < 0) ? null : this.getEdge(parentEdge);
    }
    
    public int getParent(final int n) {
        final int parentEdge = this.getParentEdge(n);
        return (parentEdge < 0) ? -1 : this.getSourceNode(parentEdge);
    }
    
    public Node getParent(final Node node) {
        final int parent = this.getParent(node.getRow());
        return (parent < 0) ? null : this.getNode(parent);
    }
    
    public IntIterator childEdgeRows(final int n) {
        return super.outEdgeRows(n);
    }
    
    public Iterator childEdges(final Node node) {
        return super.outEdges(node);
    }
    
    public Iterator children(final Node node) {
        return super.outNeighbors(node);
    }
    
    public boolean isValidTree() {
        final int nodeCount = this.getNodeCount();
        final int edgeCount = this.getEdgeCount();
        if (nodeCount != edgeCount + 1) {
            Tree.s_logger.warning("Node/edge counts incorrect.");
            return false;
        }
        final int rootRow = this.getRootRow();
        final IntIterator rows = this.getNodeTable().rows();
        while (rows.hasNext()) {
            final int nextInt = rows.nextInt();
            final int inDegree = this.getInDegree(nextInt);
            if (nextInt == rootRow && inDegree > 0) {
                Tree.s_logger.warning("Root node has a parent.");
                return false;
            }
            if (inDegree > 1) {
                Tree.s_logger.warning("Node " + nextInt + " has multiple parents.");
                return false;
            }
        }
        final int[] array = { 0, edgeCount };
        this.isValidHelper(this.getRootRow(), array);
        if (array[0] > edgeCount) {
            Tree.s_logger.warning("The tree has non-tree edges in it.");
            return false;
        }
        if (array[0] < edgeCount) {
            Tree.s_logger.warning("Not all of the tree was visited. Only " + array[0] + "/" + edgeCount + " edges encountered");
            return false;
        }
        return true;
    }
    
    private void isValidHelper(final int n, final int[] array) {
        final IntIterator childEdgeRows = this.childEdgeRows(n);
        int n2 = 0;
        while (childEdgeRows.hasNext()) {
            final int nextInt = childEdgeRows.nextInt();
            ++n2;
            final int n3 = 0;
            ++array[n3];
            this.isValidHelper(this.getAdjacentNode(nextInt, n), array);
            if (array[0] > array[1]) {
                return;
            }
        }
    }
    
    public Tree getSpanningTree() {
        return (this.m_spanning == null) ? this : this.m_spanning;
    }
    
    public Tree getSpanningTree(final Node node) {
        this.nodeCheck(node, true);
        if (this.m_spanning == null) {
            if (this.m_root == node.getRow()) {
                return this;
            }
            this.m_spanning = new SpanningTree(this, node);
        }
        else if (this.m_spanning.getRoot() != node) {
            this.m_spanning.buildSpanningTree(node);
        }
        return this.m_spanning;
    }
    
    static {
        s_logger = Logger.getLogger(Tree.class.getName());
        DEFAULT_SOURCE_KEY = PrefuseConfig.get("data.tree.sourceKey");
        DEFAULT_TARGET_KEY = PrefuseConfig.get("data.tree.targetKey");
        (TREE_LINKS_SCHEMA = new Schema()).addColumn("_childIndex", Integer.TYPE, new Integer(-1));
        Tree.TREE_LINKS_SCHEMA.lockSchema();
    }
}
