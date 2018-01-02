// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Enumeration;
import java.util.Vector;
import java.awt.Point;

class Node extends Point
{
    Vector edges;
    Vector connectedEdges;
    Graph graph;
    MultiNode multiNode;
    
    Node(final int n, final int n2, final Graph graph) {
        super(n, n2);
        this.edges = new Vector();
        this.connectedEdges = new Vector();
        this.graph = null;
        this.graph = graph;
        this.multiNode = new MultiNode(graph);
        this.multiNode.nodes.addElement(this);
    }
    
    Node(final int n, final int n2) {
        this(n, n2, null);
    }
    
    public boolean equals(final Object o) {
        if (o instanceof Node) {
            final Node node = (Node)o;
            if (super.x == node.x & super.y == node.y) {
                return true;
            }
        }
        return false;
    }
    
    Vector getEdges() {
        return this.edges;
    }
    
    public int distTo(final Node node) {
        final int n = node.x - super.x;
        final int n2 = n * n;
        final int n3 = node.y - super.y;
        return Math.round((float)Math.sqrt(n2 + n3 * n3));
    }
    
    public void addLinkedNodes(final Vector vector) {
        vector.addElement(this);
        final Enumeration<Edge> elements = this.getEdges().elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement().getAnotherNode(this));
        }
    }
    
    public void addConnectedNodes(final Vector vector) {
        vector.addElement(this);
        final Enumeration<Edge> elements = this.connectedEdges.elements();
        while (elements.hasMoreElements()) {
            final Node anotherNode = elements.nextElement().getAnotherNode(this);
            if (!vector.contains(anotherNode)) {
                anotherNode.addConnectedNodes(vector);
            }
        }
    }
    
    public void addConnectedLinkedNodes(final Vector vector) {
        final Vector<Node> vector2 = new Vector<Node>();
        this.addConnectedNodes(vector2);
        final Enumeration<Node> elements = vector2.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().addLinkedNodes(vector);
        }
        vector.removeElement(this);
    }
}
