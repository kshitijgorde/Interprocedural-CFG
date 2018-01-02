// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.algorithms;

import hypergraph.graphApi.Element;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import hypergraph.graphApi.Edge;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.GraphException;
import hypergraph.graphApi.Graph;
import hypergraph.graphApi.GraphSystem;

public final class GraphUtilities
{
    public static Graph createGrid2(final GraphSystem graphSystem, final int n, final int n2) {
        final Graph graph = graphSystem.createGraph();
        Node node = null;
        for (int i = 1; i <= n2; ++i) {
            for (int j = 1; j <= n; ++j) {
                try {
                    node = graph.createNode(Integer.toString(i) + "/" + Integer.toString(j));
                }
                catch (GraphException ex) {
                    System.out.println(ex);
                }
                if (i > 1) {
                    graph.createEdge((Node)graph.getElement(Integer.toString(i - 1) + "/" + Integer.toString(j)), node);
                }
                if (j > 1) {
                    graph.createEdge((Node)graph.getElement(Integer.toString(i) + "/" + Integer.toString(j - 1)), node);
                }
            }
        }
        return graph;
    }
    
    public static Graph createDiscreteGraph(final GraphSystem graphSystem, final int n) {
        final Graph graph = graphSystem.createGraph();
        for (int i = 1; i <= n; ++i) {
            graph.createNode();
        }
        return graph;
    }
    
    public static Graph createCompleteGraph(final GraphSystem graphSystem, final int n) {
        final Graph graph = graphSystem.createGraph();
        for (int i = 1; i <= n; ++i) {
            graph.createNode().setLabel(Integer.toString(i));
        }
        for (final Node node : graph.getNodes()) {
            for (final Node node2 : graph.getNodes()) {
                if (node != node2) {
                    graph.createEdge(node, node2);
                }
            }
        }
        return graph;
    }
    
    public static Set getConnectedComponents(final Graph graph) {
        final HashSet<HashSet<Node>> set = new HashSet<HashSet<Node>>();
        final HashSet set2 = new HashSet<Node>();
        set2.addAll(graph.getNodes());
        while (!set2.isEmpty()) {
            final Node node = set2.iterator().next();
            final HashSet<Node> set3 = new HashSet<Node>();
            set.add(set3);
            set3.add(node);
            set2.remove(node);
            final HashSet<Edge> set4 = (HashSet<Edge>)new HashSet<Object>();
            set4.addAll((Collection<?>)graph.getEdges(node));
            while (!set4.isEmpty()) {
                final Edge edge = set4.iterator().next();
                if (set2.remove(edge.getSource())) {
                    set3.add(edge.getSource());
                    set4.addAll((Collection<?>)graph.getEdges(edge.getSource()));
                }
                if (set2.remove(edge.getTarget())) {
                    set3.add(edge.getTarget());
                    set4.addAll((Collection<?>)graph.getEdges(edge.getTarget()));
                }
                set4.remove(edge);
            }
        }
        return set;
    }
    
    private static Graph createSubTree(final Graph graph, final Node node, final int n, final int n2, final int n3, final long n4) {
        try {
            Thread.sleep(n4);
        }
        catch (InterruptedException ex) {}
        if (n == n2) {
            return null;
        }
        for (int i = 1; i <= n3; ++i) {
            final Node node2 = graph.createNode();
            synchronized (graph) {
                graph.createEdge(node, node2);
            }
            createSubTree(graph, node2, n, n2 + 1, n3, n4);
        }
        return graph;
    }
    
    public static Graph createTree(final GraphSystem graphSystem, final int n, final int n2) {
        final Graph graph = graphSystem.createGraph();
        createSubTree(graph, graph.createNode(), n, 0, n2, 0L);
        return graph;
    }
    
    public static Graph createTreeDelayed(final GraphSystem graphSystem, final int n, final int n2, final long n3) {
        final Graph graph = graphSystem.createGraph();
        new CreateTreeThread(graph, n, n2, n3).start();
        return graph;
    }
    
    public static Graph createRandomGraph(final GraphSystem graphSystem, final int n, final double n2, final double n3, final double n4, final double n5, final long n6) {
        final Graph graph = graphSystem.createGraph();
        new CreateRandomGraphThread(graph, n, n2, n3, n4, n5, n6).start();
        return graph;
    }
    
    public static boolean isAcyclic(final Graph graph) {
        return getTopologicalOrdering(graph).size() == graph.getNodes().size();
    }
    
    public static List getTopologicalOrdering(final Graph graph) {
        final ArrayList list = new ArrayList<Object>();
        final HashMap<Node, Object> hashMap = new HashMap<Node, Object>();
        final LinkedList<Node> list2 = new LinkedList<Node>();
        for (final Edge edge : graph.getEdges()) {
            final Integer n = hashMap.get(edge.getTarget());
            if (n == null) {
                hashMap.put(edge.getTarget(), new Integer(1));
            }
            else {
                hashMap.put(edge.getTarget(), new Integer(n + 1));
            }
        }
        for (final Node node : graph.getNodes()) {
            if (!hashMap.keySet().contains(node)) {
                hashMap.put(node, new Integer(0));
                list2.add(node);
            }
        }
        while (!list2.isEmpty()) {
            final Node node2 = list2.remove(0);
            for (final Edge edge2 : graph.getEdges(node2)) {
                if (edge2.getSource().equals(node2)) {
                    final Integer n2 = hashMap.get(edge2.getTarget());
                    if (n2 == 1) {
                        list2.add(edge2.getTarget());
                    }
                    hashMap.put(edge2.getTarget(), new Integer(n2 - 1));
                }
            }
            list.add(node2);
        }
        for (int i = 0; i < list.size(); ++i) {
            final Node node3 = list.get(i);
            node3.setLabel(node3.getLabel() + " / " + i);
        }
        return list;
    }
    
    public static Set makeAcyclic(final Graph graph) {
        final HashSet set = new HashSet<Edge>();
        for (final Node node : graph.getNodes()) {
            final Collection edges = graph.getEdges(node);
            int n = 0;
            int n2 = 0;
            final Iterator<Edge> iterator2 = edges.iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().getSource().equals(node)) {
                    ++n2;
                }
                else {
                    ++n;
                }
            }
            for (final Edge edge : edges) {
                if (((n > n2 && edge.getSource().equals(node)) || (n <= n2 && edge.getTarget().equals(node))) && !set.contains(edge)) {
                    set.add(edge);
                    edge.reverse();
                }
            }
        }
        return set;
    }
    
    private static class CreateRandomGraphThread extends Thread
    {
        Graph graph;
        Node root;
        int size;
        double addNodeProb;
        double removeNodeProb;
        double addEdgeProb;
        double removeEdgeProb;
        long delay;
        
        public CreateRandomGraphThread(final Graph graph, final int size, final double addNodeProb, final double removeNodeProb, final double addEdgeProb, final double removeEdgeProb, final long delay) {
            this.graph = graph;
            this.size = size;
            this.addNodeProb = addNodeProb;
            this.removeNodeProb = removeNodeProb;
            this.addEdgeProb = addEdgeProb;
            this.removeEdgeProb = removeEdgeProb;
            this.delay = delay;
            this.root = graph.createNode();
        }
        
        private Node getRandomNode() {
            Node node = null;
            final Iterator<Node> iterator = this.graph.getNodes().iterator();
            while (iterator.hasNext()) {
                node = iterator.next();
                if (Math.random() < 1.0 / this.graph.getNodes().size()) {
                    return node;
                }
            }
            return node;
        }
        
        private Edge getRandomEdge() {
            Edge edge = null;
            final Iterator<Edge> iterator = this.graph.getEdges().iterator();
            while (iterator.hasNext()) {
                edge = iterator.next();
                if (Math.random() < 1.0 / this.graph.getEdges().size()) {
                    return edge;
                }
            }
            return edge;
        }
        
        public void run() {
            while (this.graph.getNodes().size() < this.size) {
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex) {}
                System.out.println("==============================================");
                System.out.println(this.graph);
                final Node randomNode = this.getRandomNode();
                final double random = Math.random();
                if (random < this.addNodeProb) {
                    this.graph.createNode();
                }
                else if (random < this.addNodeProb + this.addEdgeProb) {
                    this.graph.createEdge(randomNode, this.getRandomNode());
                }
                else if (random < this.addNodeProb + this.addEdgeProb + this.removeNodeProb) {
                    this.graph.removeElement(randomNode);
                }
                else if (random < this.addNodeProb + this.addEdgeProb + this.removeNodeProb + this.removeEdgeProb) {
                    this.graph.removeElement(this.getRandomEdge());
                }
                System.out.println(this.graph);
            }
        }
    }
    
    private static class CreateTreeThread extends Thread
    {
        Graph graph;
        Node root;
        int generations;
        int branchFactor;
        long delay;
        
        public CreateTreeThread(final Graph graph, final int generations, final int branchFactor, final long delay) {
            this.graph = graph;
            this.generations = generations;
            this.branchFactor = branchFactor;
            this.delay = delay;
            this.root = graph.createNode();
        }
        
        public void run() {
            createSubTree(this.graph, this.root, this.generations, 0, this.branchFactor, this.delay);
        }
    }
}
