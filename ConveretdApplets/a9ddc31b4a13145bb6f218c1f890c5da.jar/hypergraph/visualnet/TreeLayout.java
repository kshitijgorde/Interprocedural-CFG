// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.util.HashMap;
import hypergraph.graphApi.Element;
import hypergraph.hyperbolic.Isometry;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import hypergraph.hyperbolic.ModelPoint;
import hypergraph.hyperbolic.ModelVector;
import java.util.Iterator;
import java.util.Collection;
import hypergraph.graphApi.Edge;
import hypergraph.hyperbolic.Functions;
import hypergraph.hyperbolic.PropertyManager;
import hypergraph.hyperbolic.Model;
import hypergraph.graphApi.Graph;
import hypergraph.graphApi.Node;
import java.util.Map;

public class TreeLayout extends AbstractGraphLayout
{
    private double maxAngle;
    private double minDistance;
    private double defaultSize;
    private double rootAngle;
    Map properties;
    Node root;
    private Boolean expandingEnabled;
    
    public TreeLayout(final Graph graph, final Model model, final PropertyManager properties) {
        this.rootAngle = 3.141592653589793;
        this.setGraphLayoutModel(new DefaultGraphLayoutModel());
        this.setGraph(graph);
        this.setModel(model);
        this.setProperties(properties);
        this.setRootAngle(3.141592653589793);
        this.setRoot(this.root);
    }
    
    public void setProperties(final PropertyManager properties) {
        super.setProperties(properties);
        this.setMaxAngle(properties.getDouble("hypergraph.visualnet.TreeLayout.maxangle", new Double(180.0)) * 3.141592653589793 / 360.0);
        this.setMinDistance(properties.getDouble("hypergraph.visualnet.TreeLayout.mindistance", new Double(0.3)));
        this.setDefaultSize(properties.getDouble("hypergraph.visualnet.TreeLayout.defaultSize", new Double(0.3)));
        this.invalidate();
    }
    
    private double getDistance(final double n, final double n2, final double n3) {
        final double arsinh = Functions.arsinh(Functions.sinh(n3) / Math.sin(n));
        if (Math.abs(n2) < 0.001) {
            return arsinh;
        }
        return Math.max(arsinh, Functions.arcosh((1.0 + Math.cos(n) * Math.cos(3.141592653589793 - n2)) / (Math.sin(n) * Math.sin(3.141592653589793 - n2))));
    }
    
    private double getAngle(final double n, final double n2, final double n3) {
        return Math.max(Math.asin(Functions.sinh(n3) / Functions.sinh(n)), Math.asin(Math.sin(3.141592653589793 - n2) / (Functions.cosh(n) - Functions.sinh(n) * Math.cos(3.141592653589793 - n2))));
    }
    
    private double getSize(final Node node) {
        return this.defaultSize;
    }
    
    private void computeNodeProperties(final Node node) {
        final NodeProperties nodeProperties = new NodeProperties();
        this.properties.put(node, nodeProperties);
        nodeProperties.angle2 = 0.0;
        final Collection edges = this.getGraph().getSpanningTree().getEdges(node);
        if (edges.size() == 0) {
            return;
        }
        int n = 0;
        for (final Edge edge : edges) {
            if (node != edge.getSource()) {
                continue;
            }
            ++n;
            final Node target = edge.getTarget();
            this.computeNodeProperties(target);
            final NodeProperties nodeProperties2 = this.properties.get(target);
            nodeProperties2.distance = Math.max(this.minDistance, this.getSize(node) + this.getSize(target));
            nodeProperties2.angle1 = this.getAngle(nodeProperties2.distance, nodeProperties2.angle2, this.getSize(target));
            final NodeProperties nodeProperties3 = nodeProperties;
            nodeProperties3.angle2 += nodeProperties2.angle1;
        }
        double n2;
        if (node == this.root && n > 1) {
            n2 = this.rootAngle / nodeProperties.angle2;
        }
        else {
            if (nodeProperties.angle2 < this.maxAngle) {
                return;
            }
            n2 = this.maxAngle / nodeProperties.angle2;
            nodeProperties.angle2 = this.maxAngle;
        }
        for (final Edge edge2 : edges) {
            if (node != edge2.getSource()) {
                continue;
            }
            final Node target2 = edge2.getTarget();
            final NodeProperties nodeProperties5;
            final NodeProperties nodeProperties4 = nodeProperties5 = this.properties.get(target2);
            nodeProperties5.angle1 *= n2;
            nodeProperties4.distance = this.getDistance(nodeProperties4.angle1, nodeProperties4.angle2, this.getSize(target2));
            nodeProperties4.distance = Math.max(this.minDistance, nodeProperties4.distance);
        }
    }
    
    protected void layoutSubTree(final Node node, final ModelVector modelVector) {
        this.getGraphLayoutModel().setNodePosition(node, (ModelPoint)modelVector.getBase().clone());
        final NodeProperties nodeProperties = this.properties.get(node);
        final Collection edges = this.getGraph().getSpanningTree().getEdges(node);
        if (edges.size() == 0) {
            return;
        }
        final ArrayList<Node> list = new ArrayList<Node>();
        for (final Edge edge : edges) {
            if (node == edge.getSource()) {
                list.add(edge.getTarget());
            }
        }
        Collections.sort((List<Object>)list, new Comparator() {
            public int compare(final Object o, final Object o2) {
                return ((Node)o).getName().compareTo(((Node)o2).getName());
            }
        });
        this.getModel().getRotation(modelVector.getBase(), -nodeProperties.angle2).apply(modelVector);
        for (final Node node2 : list) {
            final NodeProperties nodeProperties2 = this.properties.get(node2);
            final Isometry rotation = this.getModel().getRotation(modelVector.getBase(), nodeProperties2.angle1);
            rotation.apply(modelVector);
            final ModelVector modelVector2 = (ModelVector)modelVector.clone();
            this.getModel().getTranslation(modelVector, nodeProperties2.distance).apply(modelVector2);
            this.layoutSubTree(node2, modelVector2);
            rotation.apply(modelVector);
        }
    }
    
    public void layout() {
        this.getGraphLayoutModel().clearNodePositions();
        final ModelVector defaultVector = this.getModel().getDefaultVector();
        if (this.root == null) {
            final Graph spanningTree = this.getGraph().getSpanningTree();
            this.root = (Node)spanningTree.getAttributeManager().getAttribute("GRAPH_ROOT", spanningTree);
        }
        this.getGraph().getSpanningTree(this.root);
        synchronized (this.getGraph()) {
            this.properties = new HashMap();
            this.computeNodeProperties(this.root);
            this.layoutSubTree(this.root, defaultVector);
        }
        this.getGraphLayoutModel().setValid(true);
    }
    
    public void setExpandingEnabled(final boolean b) {
        this.expandingEnabled = new Boolean(b);
    }
    
    public boolean isExpandingEnabled() {
        if (this.expandingEnabled != null) {
            return this.expandingEnabled;
        }
        final String string = this.getProperties().getString("visualnet.layout.expandingEnabled");
        return string != null && string.equalsIgnoreCase("true");
    }
    
    public double getMaxAngle() {
        return this.maxAngle;
    }
    
    public double getMinDistance() {
        return this.minDistance;
    }
    
    public double getRootAngle() {
        return this.rootAngle;
    }
    
    public void setMaxAngle(final double maxAngle) {
        this.maxAngle = maxAngle;
    }
    
    public void setMinDistance(final double minDistance) {
        this.minDistance = minDistance;
    }
    
    public void setRootAngle(final double rootAngle) {
        this.rootAngle = rootAngle;
    }
    
    public double getDefaultSize() {
        return this.defaultSize;
    }
    
    public void setDefaultSize(final double defaultSize) {
        this.defaultSize = defaultSize;
    }
    
    public Node getRoot() {
        return this.root;
    }
    
    public void setRoot(final Node root) {
        this.root = root;
    }
    
    private class EdgeComparator implements Comparator
    {
        public int compare(final Object o, final Object o2) {
            if (o == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            final Edge edge = (Edge)o;
            final Edge edge2 = (Edge)o2;
            final int compareTo = edge.getSource().getName().compareTo(edge2.getSource().getName());
            if (compareTo != 0) {
                return compareTo;
            }
            return edge.getTarget().getName().compareTo(edge2.getTarget().getName());
        }
    }
    
    protected class NodeProperties
    {
        double distance;
        double angle1;
        double angle2;
        
        public String toString() {
            return "[ TreeLayout.NodeProperties : \n angle1    = " + this.angle1 + " \n" + "angle2    = " + this.angle2 + " \n" + "distance    = " + this.distance + "]\n";
        }
    }
}
