// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.util.Iterator;
import java.awt.Color;
import medusa.graph.Node;
import medusa.graph.Edge;
import medusa.graph.Graph;

public class DemoTools
{
    public Graph randomGraph(final int n, final int n2) {
        final Graph nodes = new Graph();
        for (int i = 0; i < n2; ++i) {
            nodes.addEdge(new Edge(this.randomNode(n), this.randomNode(n), (float)Math.random(), this.randomInteraction()));
        }
        this.setNodes(nodes);
        return nodes;
    }
    
    private void setNodes(final Graph graph) {
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            final int shape = (int)(Math.random() * 4.0);
            final int n = (int)(Math.random() * 255.0);
            final int n2 = (int)(Math.random() * 255.0);
            final int n3 = (int)(Math.random() * 255.0);
            node.setShape(shape);
            node.setColor(new Color(n, n2, n3));
        }
    }
    
    private String randomNode(final int n) {
        return "n" + (int)(Math.random() * n);
    }
    
    private int randomInteraction() {
        return (int)(Math.random() * 6.0 + 1.0);
    }
    
    public static void main(final String[] array) {
        System.out.println(new DemoTools().randomGraph(10, 10).report());
    }
}
