// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.graphApi.Element;
import java.util.ArrayList;
import hypergraph.graphApi.algorithms.BFSGraphWalker;
import java.util.List;
import hypergraph.graphApi.algorithms.GraphWalkerListener;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.GraphEvent;
import hypergraph.graphApi.Graph;
import hypergraph.graphApi.GraphListener;

public class MDSWeight implements GenericMDSLayout.MDSWeight, GraphListener
{
    private Graph graph;
    private DistanceArray distances;
    
    public void setGraph(final Graph graph) {
        (this.graph = graph).addGraphListener(this);
        this.distances = new DistanceArray(this.graph);
    }
    
    public void elementsAdded(final GraphEvent graphEvent) {
        this.distances = new DistanceArray(this.graph);
    }
    
    public void elementsRemoved(final GraphEvent graphEvent) {
        this.distances = new DistanceArray(this.graph);
    }
    
    public void structureChanged(final GraphEvent graphEvent) {
        this.distances = new DistanceArray(this.graph);
    }
    
    public double getWeight(final Node node) {
        return this.distances.getSphereSize(node, 2);
    }
    
    public double getWeight(final Node node, final Node node2, final double n) {
        double n2 = 0.0;
        if (n < 20.0) {
            n2 += 0.5 / n - 0.025;
        }
        final double n3 = this.distances.getDistance(node, node2);
        if (n3 > 0.0 && n3 < 2.0) {
            n2 += (n - n3 * 0.15) * (n - n3 * 0.15) / n3;
        }
        return n2;
    }
    
    public double getWeightDerivative(final Node node, final Node node2, final double n) {
        double n2 = 0.0;
        if (n < 20.0) {
            n2 += -0.5 / (n * n);
        }
        final double n3 = this.distances.getDistance(node, node2);
        if (n3 > 0.0 && n3 < 2.0) {
            n2 += (n - n3 * 0.15) / n3;
        }
        return n2;
    }
    
    public class DistanceArray implements GraphWalkerListener
    {
        private Graph graph;
        private int[][] distances;
        private List spheres;
        private GenericMDSLayout.NodeIndex nodeIndex;
        private int currentNode;
        private BFSGraphWalker gw;
        private int c;
        private int d;
        
        public DistanceArray(final Graph graph) {
            this.graph = graph;
            this.initializeDistances();
        }
        
        public int getSphereSize(final Node node, final int n) {
            return ((int[])this.spheres.get(n))[this.nodeIndex.getIndex(node)];
        }
        
        public int getDistance(final Node node, final Node node2) {
            return this.distances[this.nodeIndex.getIndex(node)][this.nodeIndex.getIndex(node2)];
        }
        
        private void initializeDistances() {
            this.nodeIndex = new GenericMDSLayout.NodeIndex(this.graph);
            this.distances = new int[this.nodeIndex.getSize()][this.nodeIndex.getSize()];
            this.spheres = new ArrayList();
            (this.gw = new BFSGraphWalker()).setGraph(this.graph);
            this.gw.addListener(this);
            this.currentNode = 0;
            while (this.currentNode < this.nodeIndex.getSize()) {
                this.gw.setStartNode(this.nodeIndex.getNode(this.currentNode));
                final boolean b = false;
                this.d = (b ? 1 : 0);
                this.c = (b ? 1 : 0);
                this.gw.walk();
                this.setSphereSize(this.currentNode, this.d, this.c);
                ++this.currentNode;
            }
        }
        
        private void setSphereSize(final int n, final int n2, final int n3) {
            int[] array;
            if (n2 >= this.spheres.size()) {
                array = new int[this.nodeIndex.getSize()];
                this.spheres.add(n2, array);
            }
            else {
                array = this.spheres.get(n2);
            }
            array[n] = n3;
        }
        
        public void visitElement(final Element element) {
            if (element.getElementType() == 1) {
                final int index = this.nodeIndex.getIndex((Node)element);
                this.distances[this.currentNode][index] = this.gw.getIteration();
                this.distances[index][this.currentNode] = this.gw.getIteration();
                if (this.gw.getIteration() != this.d) {
                    this.setSphereSize(this.currentNode, this.d, this.c);
                    this.d = this.gw.getIteration();
                    this.c = 0;
                }
                ++this.c;
            }
        }
    }
}
