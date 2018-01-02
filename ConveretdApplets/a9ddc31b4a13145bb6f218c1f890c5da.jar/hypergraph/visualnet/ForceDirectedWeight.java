// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.graphApi.GraphEvent;
import java.util.Iterator;
import hypergraph.graphApi.algorithms.GraphUtilities;
import hypergraph.graphApi.Node;
import java.util.Set;
import hypergraph.graphApi.Graph;
import hypergraph.graphApi.GraphListener;

public class ForceDirectedWeight implements GenericMDSLayout.MDSWeight, GraphListener
{
    private double connectedDisparity;
    private double repulsingForce;
    private double repulsingForceCutOff;
    private Graph graph;
    private Set connectedComponents;
    private double cut;
    
    public ForceDirectedWeight() {
        this.cut = 1.0;
    }
    
    public void setGraph(final Graph graph) {
        (this.graph = graph).addGraphListener(this);
    }
    
    private Set getConnectedComponent(final Node node) {
        if (this.connectedComponents == null) {
            this.connectedComponents = GraphUtilities.getConnectedComponents(this.graph);
        }
        for (final Set set : this.connectedComponents) {
            if (set.contains(node)) {
                return set;
            }
        }
        return null;
    }
    
    public double getWeight(final Node node) {
        return 1.0;
    }
    
    public double getWeight(final Node node, final Node node2, final double n) {
        if (this.graph.isConnected(node, node2)) {
            return (n - this.getConnectedDisparity()) * (n - this.getConnectedDisparity());
        }
        if (this.getConnectedComponent(node).contains(node2)) {
            if (n < this.getRepulsingForceCutOff()) {
                return this.getRepulsingForce() / n - this.getRepulsingForce() / this.getRepulsingForceCutOff();
            }
        }
        else if (n < this.cut) {
            return this.getRepulsingForce() / n - this.getRepulsingForce() / this.cut;
        }
        return 0.0;
    }
    
    public double getWeightDerivative(final Node node, final Node node2, final double n) {
        if (this.graph.isConnected(node, node2)) {
            return 2.0 * (n - this.getConnectedDisparity());
        }
        if (this.getConnectedComponent(node).contains(node2)) {
            if (n < this.getRepulsingForceCutOff()) {
                return -this.getRepulsingForce() / (n * n);
            }
        }
        else if (n < this.cut) {
            return -this.getRepulsingForce() / (n * n);
        }
        return 0.0;
    }
    
    public double getRepulsingForce() {
        return this.repulsingForce;
    }
    
    public void setRepulsingForce(final double repulsingForce) {
        this.repulsingForce = repulsingForce;
    }
    
    public double getRepulsingForceCutOff() {
        return this.repulsingForceCutOff;
    }
    
    public void setRepulsingForceCutOff(final double repulsingForceCutOff) {
        this.repulsingForceCutOff = repulsingForceCutOff;
    }
    
    public double getConnectedDisparity() {
        return this.connectedDisparity;
    }
    
    public void setConnectedDisparity(final double connectedDisparity) {
        this.connectedDisparity = connectedDisparity;
    }
    
    public void elementsAdded(final GraphEvent graphEvent) {
        this.connectedComponents = null;
    }
    
    public void elementsRemoved(final GraphEvent graphEvent) {
        this.connectedComponents = null;
    }
    
    public void structureChanged(final GraphEvent graphEvent) {
        this.connectedComponents = null;
    }
}
