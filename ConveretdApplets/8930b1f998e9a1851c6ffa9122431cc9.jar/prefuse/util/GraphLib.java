// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import prefuse.data.Tree;
import java.util.ArrayList;
import prefuse.data.Node;
import prefuse.data.Graph;
import prefuse.data.Schema;

public class GraphLib
{
    public static final String LABEL = "label";
    public static final Schema LABEL_SCHEMA;
    
    public static Graph getNodes(final int n) {
        final Graph graph = new Graph();
        graph.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        for (int i = 0; i < n; ++i) {
            graph.addNode().setString("label", String.valueOf(i));
        }
        return graph;
    }
    
    public static Graph getStar(final int n) {
        final Graph graph = new Graph();
        graph.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node addNode = graph.addNode();
        addNode.setString("label", "0");
        for (int i = 1; i <= n; ++i) {
            final Node addNode2 = graph.addNode();
            addNode2.setString("label", String.valueOf(i));
            graph.addEdge(addNode, addNode2);
        }
        return graph;
    }
    
    public static Graph getClique(final int n) {
        final Graph graph = new Graph();
        graph.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node[] array = new Node[n];
        for (int i = 0; i < n; ++i) {
            (array[i] = graph.addNode()).setString("label", String.valueOf(i));
        }
        for (int j = 0; j < n; ++j) {
            for (int k = j; k < n; ++k) {
                if (j != k) {
                    graph.addEdge(array[j], array[k]);
                }
            }
        }
        return graph;
    }
    
    public static Graph getGrid(final int n, final int n2) {
        final Graph graph = new Graph();
        graph.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node[] array = new Node[n * n2];
        for (int i = 0; i < n * n2; ++i) {
            (array[i] = graph.addNode()).setString("label", String.valueOf(i));
            if (i >= n2) {
                graph.addEdge(array[i - n2], array[i]);
            }
            if (i % n2 != 0) {
                graph.addEdge(array[i - 1], array[i]);
            }
        }
        return graph;
    }
    
    public static Graph getHoneycomb(final int n) {
        final Graph graph = new Graph();
        graph.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final ArrayList halfcomb = halfcomb(graph, n);
        final ArrayList halfcomb2 = halfcomb(graph, n);
        for (int i = 0; i < n << 1; ++i) {
            graph.addEdge(halfcomb.get(i), halfcomb2.get(i));
        }
        return graph;
    }
    
    private static ArrayList halfcomb(final Graph graph, final int n) {
        final ArrayList<Node> list = new ArrayList<Node>();
        final ArrayList<Node> list2 = new ArrayList<Node>();
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final Node addNode = graph.addNode();
            addNode.setString("label", String.valueOf(n2++));
            list.add(addNode);
        }
        for (int j = 0; j < n; ++j) {
            Node node = null;
            for (int k = 0; k < list.size(); ++k) {
                final Node node2 = list.get(k);
                if (node == null) {
                    node = graph.addNode();
                    node.setString("label", String.valueOf(n2++));
                    list2.add(node);
                }
                graph.addEdge(node2, node);
                node = graph.addNode();
                node.setString("label", String.valueOf(n2++));
                list2.add(node);
                graph.addEdge(node2, node);
            }
            if (j == n - 1) {
                return list2;
            }
            list.clear();
            for (int l = 0; l < list2.size(); ++l) {
                final Node node3 = list2.get(l);
                final Node addNode2 = graph.addNode();
                addNode2.setString("label", String.valueOf(n2++));
                list.add(addNode2);
                graph.addEdge(node3, addNode2);
            }
            list2.clear();
        }
        return list;
    }
    
    public static Tree getBalancedTree(final int n, final int n2) {
        final Tree tree = new Tree();
        tree.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node addRoot = tree.addRoot();
        addRoot.setString("label", "0,0");
        if (n2 > 0) {
            balancedHelper(tree, addRoot, n, n2 - 1);
        }
        return tree;
    }
    
    private static void balancedHelper(final Tree tree, final Node node, final int n, final int n2) {
        for (int i = 0; i < n; ++i) {
            final Node addChild = tree.addChild(node);
            addChild.setString("label", i + "," + addChild.getDepth());
            if (n2 > 0) {
                balancedHelper(tree, addChild, n, n2 - 1);
            }
        }
    }
    
    public static Tree getLeftDeepTree(final int n) {
        final Tree tree = new Tree();
        tree.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node addRoot = tree.addRoot();
        addRoot.setString("label", "0,0");
        deepHelper(tree, addRoot, 2, n, true);
        return tree;
    }
    
    public static Tree getRightDeepTree(final int n) {
        final Tree tree = new Tree();
        tree.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node addRoot = tree.addRoot();
        addRoot.setString("label", "0,0");
        deepHelper(tree, addRoot, 2, n, false);
        return tree;
    }
    
    public static Tree getDiamondTree(final int n, final int n2, final int n3) {
        final Tree tree = new Tree();
        tree.getNodeTable().addColumns(GraphLib.LABEL_SCHEMA);
        final Node addRoot = tree.addRoot();
        addRoot.setString("label", "0,0");
        Node node = tree.addChild(addRoot);
        node.setString("label", "1,0");
        Node node2 = tree.addChild(addRoot);
        node2.setString("label", "1,1");
        deepHelper(tree, node, n, n2 - 2, true);
        deepHelper(tree, node2, n, n2 - 2, false);
        while (node.getFirstChild() != null) {
            node = node.getFirstChild();
        }
        while (node2.getLastChild() != null) {
            node2 = node2.getLastChild();
        }
        deepHelper(tree, node, n, n3 - 1, false);
        deepHelper(tree, node2, n, n3 - 1, true);
        return tree;
    }
    
    private static void deepHelper(final Tree tree, final Node node, final int n, final int n2, final boolean b) {
        Node node2 = tree.addChild(node);
        node2.setString("label", "0," + node2.getDepth());
        if (b && n2 > 0) {
            deepHelper(tree, node2, n, n2 - 1, b);
        }
        for (int i = 1; i < n; ++i) {
            node2 = tree.addChild(node);
            node2.setString("label", i + "," + node2.getDepth());
        }
        if (!b && n2 > 0) {
            deepHelper(tree, node2, n, n2 - 1, b);
        }
    }
    
    static {
        (LABEL_SCHEMA = new Schema()).addColumn("label", String.class, "");
    }
}
