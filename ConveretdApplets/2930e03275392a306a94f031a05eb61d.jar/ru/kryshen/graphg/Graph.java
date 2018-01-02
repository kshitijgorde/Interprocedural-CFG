// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Enumeration;
import java.util.Vector;

public class Graph
{
    public Vector nodes;
    public Vector multiNodes;
    public Vector edges;
    public int width;
    public int height;
    public Node start;
    public Node finish;
    
    public Graph() {
        this.nodes = new Vector();
        this.multiNodes = new Vector();
        this.edges = new Vector();
        this.width = 100;
        this.height = 100;
        this.start = null;
        this.finish = null;
    }
    
    public void removeEdge(final Edge edge) {
        if (this.edges.removeElement(edge)) {
            edge.node1.edges.removeElement(edge);
            edge.node1.multiNode.edges.removeElement(edge);
            edge.node2.edges.removeElement(edge);
            edge.node2.multiNode.edges.removeElement(edge);
        }
    }
    
    public boolean addEdge(final Edge edge) {
        if (this.edges.contains(edge)) {
            return false;
        }
        this.edges.addElement(edge);
        edge.node1.edges.addElement(edge);
        edge.node1.multiNode.edges.addElement(edge);
        edge.node2.edges.addElement(edge);
        if (!edge.node1.multiNode.equals(edge.node2.multiNode)) {
            edge.node2.multiNode.edges.addElement(edge);
        }
        return true;
    }
    
    public void addNode(final Node node) {
        if (this.nodes.contains(node)) {
            this.nodes.removeElement(node);
        }
        this.nodes.addElement(node);
        if (!this.multiNodes.contains(node.multiNode)) {
            this.multiNodes.addElement(node.multiNode);
        }
    }
    
    public Path shortestPath(final MultiNode multiNode, final MultiNode multiNode2) {
        final Enumeration<MultiNode> elements = this.multiNodes.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().mark = Integer.MAX_VALUE;
        }
        Vector<MultiNode> vector = new Vector<MultiNode>(this.multiNodes.size());
        Vector<MultiNode> vector2 = new Vector<MultiNode>(this.multiNodes.size());
        multiNode.mark = 0;
        vector.addElement(multiNode);
        do {
            final Enumeration<MultiNode> elements2 = vector.elements();
            while (elements2.hasMoreElements()) {
                final MultiNode multiNode3 = elements2.nextElement();
                final Enumeration elements3 = multiNode3.edges.elements();
                while (elements3.hasMoreElements()) {
                    final Edge pathEdge = elements3.nextElement();
                    if (pathEdge.enabled) {
                        MultiNode multiNode4 = pathEdge.getAnotherNode(multiNode3);
                        if (multiNode4.mark <= multiNode3.mark + 1) {
                            continue;
                        }
                        multiNode4.mark = multiNode3.mark + 1;
                        multiNode4.pathEdge = pathEdge;
                        if (multiNode4 == multiNode2) {
                            final Path path = new Path(multiNode4.mark);
                            while (multiNode4 != multiNode) {
                                path.addElement(multiNode4.pathEdge);
                                multiNode4 = multiNode4.pathEdge.getAnotherNode(multiNode4);
                            }
                            return path;
                        }
                        vector2.addElement(multiNode4);
                    }
                }
            }
            final Vector<MultiNode> vector3 = vector;
            vector = vector2;
            vector2 = vector3;
            vector2.removeAllElements();
        } while (vector.size() > 0);
        return null;
    }
}
