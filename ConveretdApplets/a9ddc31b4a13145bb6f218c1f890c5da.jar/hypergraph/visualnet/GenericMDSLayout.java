// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import hypergraph.hyperbolic.PoincareModel;
import hypergraph.graphApi.GraphEvent;
import hypergraph.hyperbolic.ModelVector;
import hypergraph.hyperbolic.ModelPoint;
import java.util.Iterator;
import hypergraph.graphApi.Edge;
import hypergraph.graphApi.Node;
import hypergraph.hyperbolic.PropertyManager;
import hypergraph.graphApi.Graph;
import hypergraph.hyperbolic.Model;
import hypergraph.hyperbolic.Isometry;
import hypergraph.hyperbolic.ComplexVector;
import hypergraph.hyperbolic.Complex;

public class GenericMDSLayout extends IteratingGraphLayout
{
    NodeIndex nodeIndex;
    Complex[] nodePositions;
    Complex[] tempPositions;
    ComplexVector[] gradient;
    ComplexVector[] previousGradient;
    double[][] distances;
    private double stepWidth;
    private double gradientNorm2;
    private double previousGradientNorm2;
    private double energy;
    private double lastEnergy;
    private Complex z1;
    private Complex z2;
    private Complex z3;
    private Complex z4;
    private static final Complex zero;
    private ComplexVector v1;
    private ComplexVector v2;
    private Isometry isom1;
    private MDSWeight weight;
    
    public GenericMDSLayout(MDSWeight weight, final Model model, final Graph graph, final PropertyManager properties) {
        this.stepWidth = 1.0;
        this.gradientNorm2 = 0.0;
        this.previousGradientNorm2 = 0.0;
        this.energy = 0.0;
        this.lastEnergy = 0.0;
        this.z1 = new Complex();
        this.z2 = new Complex();
        this.z3 = new Complex();
        this.z4 = new Complex();
        this.v1 = new ComplexVector();
        this.v2 = new ComplexVector();
        this.setModel(model);
        this.setProperties(properties);
        if (weight == null) {
            weight = new ForceDirectedWeight();
            ((ForceDirectedWeight)weight).setRepulsingForce(this.getProperties().getDouble("visualnet.GenericMDSLayout.repulsingForce", new Double(0.01)));
            ((ForceDirectedWeight)weight).setRepulsingForceCutOff(this.getProperties().getDouble("visualnet.GenericMDSLayout.repulsingForceCutOff", new Double(100.0)));
            ((ForceDirectedWeight)weight).setConnectedDisparity(this.getProperties().getDouble("visualnet.GenericMDSLayout.connectedDisparity", new Double(0.2)));
        }
        this.weight = weight;
        this.setGraph(graph);
    }
    
    public void setGraph(final Graph graph) {
        this.weight.setGraph(graph);
        this.nodeIndex = new NodeIndex(graph);
        super.setGraph(graph);
    }
    
    public Graph getGraph() {
        return super.getGraph();
    }
    
    public void setWeight(final MDSWeight weight) {
        this.weight = weight;
    }
    
    private Complex getBarycenter(final Node node) {
        final Complex complex = new Complex();
        double n = 0.0;
        final Iterator<Edge> iterator = this.getGraph().getEdges(node).iterator();
        while (iterator.hasNext()) {
            complex.add((Complex)this.getGraphLayoutModel().getNodePosition(iterator.next().getOtherNode(node)));
            ++n;
        }
        if (n == 0.0) {
            return (Complex)this.getGraphLayoutModel().getNodePosition(node);
        }
        complex.multiply(1.0 / n);
        return complex;
    }
    
    private void translateRandomly(final Complex[] array, final int n) {
        this.translateRandomly(array[n], 0.01, 0.05);
    }
    
    private void translateRandomly(final Complex base, final double n, final double n2) {
        final double n3 = 6.283185307179586 * Math.random();
        final double n4 = n + (n2 - n) * Math.random();
        final ComplexVector complexVector = new ComplexVector();
        complexVector.setBase(base);
        complexVector.v.setReal(n4 * Math.cos(n3));
        complexVector.v.setImag(n4 * Math.sin(n3));
        this.getModel().getTranslation(complexVector, n4).apply(base);
    }
    
    public synchronized void elementsAdded(final GraphEvent graphEvent) {
        if (graphEvent.getElement().getElementType() == 1) {
            final Complex[] nodePositions = new Complex[this.getGraph().getNodes().size()];
            for (int i = 0; i < this.nodePositions.length; ++i) {
                nodePositions[i] = this.nodePositions[i];
            }
            this.translateRandomly(nodePositions[this.nodePositions.length] = new Complex(), 0.1, 1.0);
            this.nodePositions = nodePositions;
            this.nodeIndex.addNode((Node)graphEvent.getElement());
        }
        if (graphEvent.getElement().getElementType() == 2) {
            final Edge edge = (Edge)graphEvent.getElement();
            Node node = edge.getSource();
            if (this.getGraph().getEdges(node).size() != 1) {
                node = edge.getTarget();
                if (this.getGraph().getEdges(node).size() != 1) {
                    return;
                }
            }
            final int index = this.nodeIndex.getIndex(node);
            this.nodePositions[index].setTo(this.nodePositions[this.nodeIndex.getIndex(edge.getOtherNode(node))]);
            this.translateRandomly(this.nodePositions[index], 0.01, 0.05);
        }
        this.invalidate();
    }
    
    public synchronized void elementsRemoved(final GraphEvent graphEvent) {
        if (graphEvent.getElement().getElementType() == 1) {
            final NodeIndex nodeIndex = new NodeIndex(this.getGraph());
            final Complex[] nodePositions = new Complex[this.getGraph().getNodes().size()];
            for (final Node node : this.getGraph().getNodes()) {
                nodePositions[nodeIndex.getIndex(node)] = this.nodePositions[this.nodeIndex.getIndex(node)];
            }
            this.nodePositions = nodePositions;
            this.nodeIndex = nodeIndex;
        }
        this.invalidate();
    }
    
    public void invalidate() {
        super.invalidate();
        this.distances = null;
        this.gradient = null;
        this.previousGradient = null;
        this.tempPositions = null;
        this.stepWidth = 0.05;
    }
    
    public void initializeRandomly(final Complex[] array) {
        for (final Node node : this.getGraph().getNodes()) {
            final Complex complex = new Complex();
            this.translateRandomly(complex, 0.0, 5.0);
            array[this.nodeIndex.getIndex(node)] = complex;
        }
    }
    
    private void setLayoutModelPositions(final Complex[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.getGraphLayoutModel().setNodePosition(this.nodeIndex.getNode(i), array[i]);
        }
    }
    
    private double getEnergy(final double[][] array) {
        double n = 0.0;
        for (int i = 0; i < this.nodeIndex.getSize(); ++i) {
            for (int j = i + 1; j < this.nodeIndex.getSize(); ++j) {
                n += this.weight.getWeight(this.nodeIndex.getNode(j), this.nodeIndex.getNode(i), this.getDistance(i, j));
            }
        }
        return n;
    }
    
    private void computeDistances(final Complex[] array) {
        for (int i = 0; i < this.nodeIndex.getSize(); ++i) {
            for (int j = i + 1; j < this.nodeIndex.getSize(); ++j) {
                this.distances[i][j] = ((PoincareModel)this.getModel()).dist(array[i], array[j]);
            }
        }
    }
    
    private double getDistance(final int n, final int n2) {
        if (n2 < n) {
            return this.distances[n2][n];
        }
        return this.distances[n][n2];
    }
    
    private double computeGradient(final Complex[] array, final ComplexVector[] array2) {
        double n = 0.0;
        for (int i = 0; i < this.nodeIndex.getSize(); ++i) {
            array2[i].scale(0.0);
            for (int j = 0; j < this.nodeIndex.getSize(); ++j) {
                if (i != j) {
                    final double weightDerivative = this.weight.getWeightDerivative(this.nodeIndex.getNode(i), this.nodeIndex.getNode(j), this.getDistance(j, i));
                    if (Math.abs(weightDerivative) > 0.001) {
                        this.getModel().distanceGradient(array[i], array[j], this.v1);
                        this.v1.v.multiply(weightDerivative);
                        array2[i].v.add(this.v1.v);
                    }
                }
            }
            n += this.getModel().length2(array2[i]);
        }
        return n;
    }
    
    protected void iteration(final IterationThread iterationThread) {
        if (this.getGraph().getNodes().size() == 0) {
            iterationThread.stopIterating();
            return;
        }
        if (this.isom1 == null) {
            this.isom1 = this.getModel().getIdentity();
        }
        if (this.gradient == null) {
            this.gradient = new ComplexVector[this.nodeIndex.getSize()];
            for (int i = 0; i < this.nodeIndex.getSize(); ++i) {
                this.gradient[i] = new ComplexVector(this.nodePositions[i], new Complex());
            }
        }
        if (this.tempPositions == null) {
            this.tempPositions = new Complex[this.nodeIndex.getSize()];
            for (int j = 0; j < this.nodeIndex.getSize(); ++j) {
                this.tempPositions[j] = new Complex();
            }
        }
        if (this.distances == null) {
            this.distances = new double[this.nodeIndex.getSize()][this.nodeIndex.getSize()];
        }
        for (int k = 0; k < 10; ++k) {
            this.computeDistances(this.nodePositions);
            this.gradientNorm2 = this.computeGradient(this.nodePositions, this.gradient);
            if (this.previousGradient == null) {
                this.previousGradient = new ComplexVector[this.nodeIndex.getSize()];
                for (int l = 0; l < this.nodeIndex.getSize(); ++l) {
                    this.previousGradient[l] = (ComplexVector)this.gradient[l].clone();
                }
                this.previousGradientNorm2 = this.gradientNorm2;
            }
            double n = 0.0;
            for (int n2 = 0; n2 < this.gradient.length; ++n2) {
                n += this.getModel().product(this.gradient[n2], this.previousGradient[n2]);
            }
            final double n3 = n / Math.sqrt(this.gradientNorm2 * this.previousGradientNorm2);
            if (n3 > 0.9) {
                this.stepWidth *= 1.1;
            }
            else if (n3 > 0.8) {
                this.stepWidth *= 1.05;
            }
            else if (n3 > 0.5) {
                this.stepWidth *= 1.0;
            }
            else if (n3 > 0.3) {
                this.stepWidth *= 0.7;
            }
            else if (n3 > 0.2) {
                this.stepWidth *= 0.4;
            }
            else if (n3 > 0.1) {
                this.stepWidth *= 0.2;
            }
            else {
                this.stepWidth *= 0.1;
            }
            if (this.stepWidth < 1.0E-9) {
                iterationThread.stopIterating();
            }
            for (int n4 = 0; n4 < this.nodeIndex.getSize(); ++n4) {
                double n5 = this.stepWidth * this.getModel().length(this.gradient[n4]);
                if (n5 > 0.2) {
                    n5 = 0.2;
                }
                this.tempPositions[n4].setTo(this.nodePositions[n4]);
                this.getModel().getTranslation(this.isom1, this.gradient[n4], -n5);
                this.isom1.apply(this.tempPositions[n4]);
                this.isom1.apply(this.gradient[n4]);
                this.previousGradient[n4].setTo(this.gradient[n4]);
                this.nodePositions[n4].setTo(this.tempPositions[n4]);
            }
            this.energy = this.getEnergy(this.distances);
            if (this.energy < 0.01 || Math.abs((this.energy - this.lastEnergy) / this.energy) < 1.0E-9) {
                iterationThread.stopIterating();
            }
            this.previousGradientNorm2 = this.gradientNorm2;
            this.lastEnergy = this.energy;
        }
        this.setLayoutModelPositions(this.nodePositions);
    }
    
    public synchronized void layout() {
        synchronized (this.getGraph()) {
            if (this.getGraphLayoutModel() == null) {
                this.setGraphLayoutModel(new DefaultGraphLayoutModel());
            }
            if (this.nodePositions == null) {
                this.initializeRandomly(this.nodePositions = new Complex[this.nodeIndex.getSize()]);
                this.setLayoutModelPositions(this.nodePositions);
            }
            super.layout();
        }
    }
    
    public boolean isExpandingEnabled() {
        return false;
    }
    
    static {
        zero = new Complex();
    }
    
    public interface MDSWeight
    {
        void setGraph(final Graph p0);
        
        double getWeight(final Node p0);
        
        double getWeight(final Node p0, final Node p1, final double p2);
        
        double getWeightDerivative(final Node p0, final Node p1, final double p2);
    }
    
    public static class NodeIndex
    {
        private List list;
        private Map map;
        private Graph graph;
        
        NodeIndex(final Graph graph) {
            this.graph = graph;
            this.buildIndex();
        }
        
        void buildIndex() {
            this.list = new ArrayList();
            this.map = new HashMap();
            int n = 0;
            for (final Node node : this.graph.getNodes()) {
                this.list.add(node);
                this.map.put(node, new Integer(n));
                ++n;
            }
        }
        
        void addNode(final Node node) {
            this.list.add(node);
            this.map.put(node, new Integer(this.list.size() - 1));
        }
        
        void removeNode(final Node node) {
            this.buildIndex();
        }
        
        int getSize() {
            return this.list.size();
        }
        
        int getIndex(final Node node) {
            final Integer n = this.map.get(node);
            if (n == null) {
                return -1;
            }
            return n;
        }
        
        Node getNode(final int n) {
            return this.list.get(n);
        }
    }
}
