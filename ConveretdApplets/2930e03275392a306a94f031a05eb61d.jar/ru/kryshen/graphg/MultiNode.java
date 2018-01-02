// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Enumeration;
import java.util.Vector;

public class MultiNode
{
    public Vector nodes;
    public Vector edges;
    public Graph graph;
    int mark;
    Edge pathEdge;
    int l1;
    int l2;
    
    public MultiNode(final Graph graph) {
        this.nodes = new Vector();
        this.edges = new Vector();
        this.l1 = Integer.MAX_VALUE;
        this.l2 = Integer.MAX_VALUE;
        this.graph = graph;
    }
    
    public void connect(final MultiNode multiNode) {
        if (this.equals(multiNode)) {
            return;
        }
        final Enumeration<Node> elements = multiNode.nodes.elements();
        while (elements.hasMoreElements()) {
            final Node node = elements.nextElement();
            this.nodes.addElement(node);
            node.multiNode = this;
        }
        final Enumeration<Edge> elements2 = multiNode.edges.elements();
        while (elements2.hasMoreElements()) {
            final Edge edge = elements2.nextElement();
            if (!this.edges.contains(edge)) {
                this.edges.addElement(edge);
            }
        }
        this.graph.multiNodes.removeElement(multiNode);
    }
}
