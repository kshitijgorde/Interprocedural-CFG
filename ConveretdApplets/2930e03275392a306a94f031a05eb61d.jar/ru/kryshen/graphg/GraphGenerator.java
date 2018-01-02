// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.awt.Point;
import java.util.Enumeration;

public class GraphGenerator
{
    private final int nnodes = 60;
    private final int nodeInterval = 10;
    private final int width = 150;
    private final int height = 100;
    private final int irv = 3;
    int minEdgesForNode;
    int maxEdgesForNode;
    private int[][] nodes;
    private Graph gg;
    
    public GraphGenerator() {
        this.minEdgesForNode = 3;
        this.maxEdgesForNode = 4;
        this.gg = new Graph();
        final Graph gg = this.gg;
        this.getClass();
        gg.width = 150;
        final Graph gg2 = this.gg;
        this.getClass();
        gg2.height = 100;
    }
    
    private void createNodes() {
        this.nodes = new int[150][100];
        this.gg.nodes.removeAllElements();
        int i = 0;
        this.gg.start = this.addNode(rnd(3, 20), rnd(3, 20));
        this.gg.finish = this.addNode(rnd(147, 130), rnd(97, 80));
        while (i < 58) {
            final int rnd = rnd(0, 149);
            final int rnd2 = rnd(0, 99);
            if (this.nodes[rnd][rnd2] == 0) {
                this.addNode(rnd, rnd2);
                ++i;
            }
        }
    }
    
    private Node addNode(final int n, final int n2) {
        int n3 = n - 10;
        if (n3 < 0) {
            n3 = 0;
        }
        int n4 = n2 - 10;
        if (n4 < 0) {
            n4 = 0;
        }
        int n5 = n + 10;
        if (n5 > 149) {
            n5 = 149;
        }
        int n6 = n2 + 10;
        if (n6 > 99) {
            n6 = 99;
        }
        for (int i = n3; i <= n5; ++i) {
            for (int j = n4; j <= n6; ++j) {
                this.nodes[i][j] = -1;
            }
        }
        this.nodes[n][n2] = 1;
        final Node node = new Node(n, n2, this.gg);
        this.gg.addNode(node);
        return node;
    }
    
    private boolean createEdges() {
        this.gg.edges.removeAllElements();
        final Enumeration<Node> elements = this.gg.nodes.elements();
        while (elements.hasMoreElements()) {
            if (this.createEdgesFor(elements.nextElement()) < 2) {
                return false;
            }
        }
        return true;
    }
    
    private int createEdgesFor(final Node node) {
        final int rnd = rnd(this.minEdgesForNode, this.maxEdgesForNode);
        int size = node.getEdges().size();
        final Node[] array = new Node[this.gg.nodes.size()];
        final Enumeration<Node> elements = (Enumeration<Node>)this.gg.nodes.elements();
        int n = -1;
        while (elements.hasMoreElements()) {
            final Node node2 = elements.nextElement();
            if (node2.getEdges().size() < this.maxEdgesForNode) {
                ++n;
                array[n] = node2;
            }
        }
        this.QuickSortNodes(array, 0, n, node);
        for (int i = 1; i <= n; ++i) {
            final Edge edge = new Edge(node, array[i]);
            if (this.IsValidEdge(edge)) {
                if (this.gg.addEdge(edge)) {
                    ++size;
                }
                if (size >= rnd) {
                    return size;
                }
            }
        }
        return size;
    }
    
    private boolean IsValidEdge(final Edge edge) {
        final Enumeration<Edge> elements = this.gg.edges.elements();
        while (elements.hasMoreElements()) {
            final Edge edge2 = elements.nextElement();
            if ((!edge.node1.equals(edge2.node1) & !edge.node1.equals(edge2.node2) & !edge.node2.equals(edge2.node1) & !edge.node2.equals(edge2.node2)) && edge.crosses(edge2)) {
                return false;
            }
        }
        final Enumeration<Node> elements2 = this.gg.nodes.elements();
        while (elements2.hasMoreElements()) {
            final Node node = elements2.nextElement();
            if (!node.equals(edge.node1) & !node.equals(edge.node2)) {
                if (edge.crosses(new Edge(new Node(node.x - 3, node.y - 3), new Node(node.x + 3, node.y - 3)))) {
                    return false;
                }
                if (edge.crosses(new Edge(new Node(node.x - 3, node.y + 3), new Node(node.x + 3, node.y + 3)))) {
                    return false;
                }
                if (edge.crosses(new Edge(new Node(node.x - 3, node.y - 3), new Node(node.x - 3, node.y + 3)))) {
                    return false;
                }
                if (edge.crosses(new Edge(new Node(node.x + 3, node.y - 3), new Node(node.x + 3, node.y + 3)))) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    public static int limit(int n, final int n2, final int n3) {
        if (n < n2) {
            n = n2;
        }
        if (n > n3) {
            n = n3;
        }
        return n;
    }
    
    public static Point limitPoint(final Point point, final int n, final int n2) {
        return new Point(limit(point.x, 0, n), limit(point.y, 0, n2));
    }
    
    public Graph generateGraph() {
        final long currentTimeMillis = System.currentTimeMillis();
        boolean edges = false;
        System.out.println(this + ":");
        System.out.println("|-- Making new graph:");
        while (!edges) {
            System.out.print("    |-- Creating nodes...");
            this.createNodes();
            System.out.println("OK");
            System.out.print("    |-- Creating links...");
            edges = this.createEdges();
            System.out.println("OK");
            if (!edges) {
                System.out.println("    |-- Bad graph. Retrying...");
            }
        }
        System.out.println("Done (" + (System.currentTimeMillis() - currentTimeMillis) / 1000.0 + " s)");
        return this.gg;
    }
    
    private Graph getGraph() {
        return this.gg;
    }
    
    static int rnd(final int n, final int n2) {
        return (int)((n2 - n + 1) * Math.random() + n);
    }
    
    void QuickSortNodes(final Node[] array, final int n, final int n2, final Node node) {
        int i = n;
        int n3 = n2;
        if (n2 > n) {
            final int distTo = array[(n + n2) / 2].distTo(node);
            while (i <= n3) {
                while (i < n2) {
                    if (array[i].distTo(node) >= distTo) {
                        break;
                    }
                    ++i;
                }
                while (n3 > n && array[n3].distTo(node) > distTo) {
                    --n3;
                }
                if (i <= n3) {
                    this.swap(array, i, n3);
                    ++i;
                    --n3;
                }
            }
            if (n < n3) {
                this.QuickSortNodes(array, n, n3, node);
            }
            if (i < n2) {
                this.QuickSortNodes(array, i, n2, node);
            }
        }
    }
    
    private void swap(final Object[] array, final int n, final int n2) {
        final Object o = array[n];
        array[n] = array[n2];
        array[n2] = o;
    }
}
