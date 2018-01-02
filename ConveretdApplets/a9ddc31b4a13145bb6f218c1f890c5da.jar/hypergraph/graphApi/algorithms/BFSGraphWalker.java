// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.algorithms;

import java.util.Collection;
import java.util.Iterator;
import hypergraph.graphApi.Edge;
import hypergraph.graphApi.Element;
import java.util.HashSet;
import java.util.Set;
import hypergraph.graphApi.Node;

public class BFSGraphWalker extends GraphWalker
{
    private Node startNode;
    private boolean ignoreVisitedNodes;
    private Set visitedNodes;
    private Set visitedEdges;
    private Set currentNodes;
    private Set nextNodes;
    private int iteration;
    
    public BFSGraphWalker() {
        this.ignoreVisitedNodes = true;
        this.visitedNodes = new HashSet();
        this.visitedEdges = new HashSet();
        this.currentNodes = new HashSet();
        this.nextNodes = new HashSet();
    }
    
    public int getIteration() {
        return this.iteration;
    }
    
    public void setStartNode(final Node startNode) {
        this.startNode = startNode;
    }
    
    public void setIgnoreVisited(final boolean ignoreVisitedNodes) {
        this.ignoreVisitedNodes = ignoreVisitedNodes;
    }
    
    public boolean isIgnoreVisited() {
        return this.ignoreVisitedNodes;
    }
    
    public void walk() {
        if (this.startNode == null) {
            return;
        }
        this.iteration = 0;
        this.visitedNodes.clear();
        this.visitedEdges.clear();
        this.currentNodes.clear();
        this.nextNodes.clear();
        this.visitElement(this.startNode);
        this.visitedNodes.add(this.startNode);
        this.currentNodes.add(this.startNode);
        while (!this.currentNodes.isEmpty()) {
            ++this.iteration;
            for (final Node node : this.currentNodes) {
                final Collection edges = this.getGraph().getEdges(node);
                if (edges != null) {
                    for (final Edge edge : edges) {
                        if (this.visitedEdges.contains(edge)) {
                            continue;
                        }
                        this.visitedEdges.add(edge);
                        final Node otherNode = edge.getOtherNode(node);
                        if (this.ignoreVisitedNodes && this.visitedNodes.contains(otherNode)) {
                            continue;
                        }
                        this.visitElement(edge);
                        this.visitElement(otherNode);
                        this.visitedNodes.add(otherNode);
                        this.nextNodes.add(otherNode);
                    }
                }
            }
            this.currentNodes.clear();
            final Set currentNodes = this.currentNodes;
            this.currentNodes = this.nextNodes;
            this.nextNodes = currentNodes;
        }
    }
}
