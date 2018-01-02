// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graph;

import java.util.Map;
import java.awt.Color;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

public class Graph extends BasicGraph implements Serializable, Cloneable
{
    final int MAX_PARALLEL = 20;
    
    public void addGraph(final Graph graph) {
        final Iterator<Edge> edgesIterator = graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            this.addEdge(edgesIterator.next());
        }
    }
    
    public void addGraph(final BasicGraph basicGraph) {
        this.addGraph((Graph)basicGraph);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Graph graph = new Graph();
        graph.addGraph(this);
        graph.copyNodeSettings(this);
        return graph;
    }
    
    public Iterator<Edge> edgesIterator(final String s) {
        final ArrayList<Edge> list = new ArrayList<Edge>();
        final Iterator<Edge> edgesIterator = this.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            if (edge.contains(s)) {
                list.add(edge);
            }
        }
        return list.iterator();
    }
    
    public ArrayList<Node> getFixed() {
        final ArrayList<Node> list = new ArrayList<Node>();
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (node.isFixed()) {
                list.add(node);
            }
        }
        return list;
    }
    
    public void removeFixed() {
        final Iterator<Node> iterator = this.getFixed().iterator();
        while (iterator.hasNext()) {
            this.removeEdgeByLabel(iterator.next().getLabel());
        }
    }
    
    public void rescaleNodes(final int n) {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            nodesIterator.next().rescale(n);
        }
    }
    
    public void rescaleNodes(final int n, final int n2) {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            nodesIterator.next().rescale(n, n2);
        }
    }
    
    public HashMap<String, Integer> nodesMap() {
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        int n = 0;
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            hashMap.put(nodesIterator.next().getLabel(), new Integer(n));
            ++n;
        }
        return hashMap;
    }
    
    public void divideNodePosition(final double n) {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            node.setX(node.getX() / n);
            node.setY(node.getY() / n);
        }
    }
    
    private Edge[] edgeArray() {
        return this.getEdges().toArray(new Edge[0]);
    }
    
    public void subtractGraph(final Graph graph) {
        final Iterator<Edge> edgesIterator = graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            this.removeEdge(edgesIterator.next());
        }
    }
    
    public List<Edge> cloneEdges() {
        final ArrayList<Edge> list = new ArrayList<Edge>();
        final Iterator<Edge> edgesIterator = this.edgesIterator();
        while (edgesIterator.hasNext()) {
            list.add(new Edge(edgesIterator.next()));
        }
        return list;
    }
    
    public void cropEdges(final double n) {
        for (final Edge edge : this.cloneEdges()) {
            if (edge.getConf() < n) {
                this.removeEdge(edge);
            }
        }
    }
    
    public void removeEdgeByLabel(final String s) {
        for (final Edge edge : this.cloneEdges()) {
            if (edge.contains(s)) {
                this.removeEdge(edge);
            }
        }
    }
    
    public void tracePath(final ArrayList list, final Node node) {
        Edge adjacent;
        while ((adjacent = this.getAdjacent(list, node)) != null) {
            list.remove(adjacent);
            final Node complement = adjacent.getComplement(node.getLabel());
            this.tracePath(list, complement);
            System.out.print(complement.getLabel() + "-");
        }
    }
    
    public Edge getAdjacent(final ArrayList list, final Node node) {
        for (final Edge edge : list) {
            if (edge.getComplement(node.getLabel()) != null) {
                return edge;
            }
        }
        return null;
    }
    
    public String reportUnique() {
        final StringBuffer sb = new StringBuffer();
        final Iterator<UniqueEdge> uniqueEdgesIterator = this.uniqueEdgesIterator();
        while (uniqueEdgesIterator.hasNext()) {
            final UniqueEdge uniqueEdge = uniqueEdgesIterator.next();
            sb.append("\n");
            sb.append(uniqueEdge.getFromName());
            sb.append("\t");
            sb.append(uniqueEdge.getToName());
        }
        return sb.toString();
    }
    
    public String report() {
        final StringBuffer sb = new StringBuffer();
        if (this.getComment() != null) {
            sb.append(this.getComment());
            sb.append("\n");
        }
        sb.append("*edges");
        final Iterator<Edge> edgesIterator = this.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            sb.append("\n");
            sb.append(edge.report());
        }
        sb.append("\n*nodes\n");
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            sb.append(nodesIterator.next().report());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public void defaultGraph() {
        final Edge edge = new Edge("n1", "n2", 4);
        final Edge edge2 = new Edge("n2", "n3", 5);
        final Edge edge3 = new Edge("n1", "n2", 5);
        final Edge edge4 = new Edge("n1", "n4", 1);
        final Edge edge5 = new Edge("n2", "n3", 1);
        this.addEdge(edge);
        this.addEdge(edge2);
        this.addEdge(edge3);
        this.addEdge(edge4);
        this.addEdge(edge5);
    }
    
    public void setNodeColor(final Color color) {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            nodesIterator.next().setColor(color);
        }
    }
    
    public void setNodeShape(final int shape) {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            nodesIterator.next().setShape(shape);
        }
    }
    
    public void setNodeAnnotation(final String s, final String annotation) {
        final Node node = this.getNode(s);
        if (node != null) {
            node.setAnnotation(annotation);
        }
    }
    
    public void printNodeAnnotation() {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            System.out.println(nodesIterator.next().getAnnotation());
        }
    }
    
    public StringBuffer getNodeList() {
        final StringBuffer sb = new StringBuffer();
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            sb.append(nodesIterator.next().getLabel());
            sb.append("\n");
        }
        return sb;
    }
    
    public Graph subGraph(final Node node) {
        final Graph graph = new Graph();
        final Iterator<Edge> edgesIterator = this.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            if (edge.containsNode(node)) {
                graph.addEdge(edge);
                edge.getComplement(node);
            }
        }
        return graph;
    }
    
    public void rescaleConfidence() {
        float conf = 0.0f;
        final Iterator<Edge> edgesIterator = this.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            if (edge.getConf() > conf) {
                conf = edge.getConf();
            }
        }
        final Iterator<Edge> edgesIterator2 = this.edgesIterator();
        while (edgesIterator2.hasNext()) {
            final Edge edge2 = edgesIterator2.next();
            edge2.setConf(edge2.getConf() / conf);
        }
    }
    
    public void rescaleNodePercentage() {
        final float n = 100.0f;
        final Iterator<Edge> edgesIterator = this.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            edge.setConf(edge.getConf() / n);
        }
    }
    
    public void autoFixOrientation() {
        final Edge[] edgeArray = this.edgeArray();
        final int length = edgeArray.length;
        final boolean[] array = new boolean[length];
        for (int i = 0; i < length; ++i) {
            array[i] = false;
        }
        final int[] array2 = new int[20];
        for (int j = 0; j < 20; ++j) {
            array2[j] = 0;
        }
        final double n = 1.0;
        for (int k = 0; k < length; ++k) {
            if (!array[k]) {
                int n2 = 0;
                array2[0] = k;
                for (int l = 0; l < length; ++l) {
                    if (k != l) {
                        if (edgeArray[k].sameName(edgeArray[l])) {
                            ++n2;
                            array[array2[n2] = l] = true;
                        }
                    }
                }
                if (n2 == 0) {
                    edgeArray[k].setOrientation(0.0);
                }
                else {
                    double n3;
                    if (n2 % 2 == 1) {
                        n3 = n2 * -n / 2.0;
                    }
                    else {
                        n3 = n2 * -n / 2.0;
                    }
                    for (int n4 = 0; n4 <= n2; ++n4) {
                        edgeArray[array2[n4]].setOrientation(this.isOppositeEdge(edgeArray[array2[n4]], edgeArray[k]) * (n3 + n4 * n));
                    }
                }
            }
        }
    }
    
    public double isOppositeEdge(final Edge edge, final Edge edge2) {
        if (edge.oppositeName(edge2)) {
            return -1.0;
        }
        return 1.0;
    }
    
    public void rescaleNodes(final double n) {
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            node.setX(node.getX() * n);
            node.setY(node.getY() * n);
        }
    }
    
    public StringBuffer nodesConnectivityReport() {
        final StringBuffer sb = new StringBuffer();
        final Iterator<Node> nodesIterator = this.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            sb.append(node.getLabel()).append("\t");
            sb.append(node.getConnections());
            sb.append("\n");
        }
        return sb;
    }
    
    public StringBuffer generalStats() {
        this.connectionDegree();
        final StringBuffer sb = new StringBuffer();
        sb.append("Connection degree: " + this.connectionDegree());
        return sb;
    }
    
    private double connectionDegree() {
        final int uniqueEdgeSize = this.getUniqueEdgeSize();
        final int nodeSize = this.getNodeSize();
        int n = 0;
        for (int i = 1; i < nodeSize; ++i) {
            n += i - 1;
        }
        return uniqueEdgeSize / n;
    }
    
    public void assignColorByMap(final Map<String, Color> map) {
        for (final String s : map.keySet()) {
            this.getNode(s).setColor(map.get(s));
        }
    }
}
