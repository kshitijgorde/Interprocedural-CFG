// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.algorithms;

import java.util.Iterator;
import java.util.List;
import hypergraph.graphApi.AttributeManager;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.Edge;
import hypergraph.graphApi.Node;
import java.util.Set;
import hypergraph.graphApi.Graph;

public class LayerAssignment
{
    public static final String LAYER = "layer";
    private boolean createDummy;
    private Class layerImpl;
    private Graph graph;
    static /* synthetic */ Class class$hypergraph$graphApi$algorithms$LayerAssignment$Layer;
    
    public void setLayerClass(final Class layerImpl) {
        if (layerImpl.isAssignableFrom((LayerAssignment.class$hypergraph$graphApi$algorithms$LayerAssignment$Layer == null) ? (LayerAssignment.class$hypergraph$graphApi$algorithms$LayerAssignment$Layer = class$("hypergraph.graphApi.algorithms.LayerAssignment$Layer")) : LayerAssignment.class$hypergraph$graphApi$algorithms$LayerAssignment$Layer)) {
            this.layerImpl = layerImpl;
        }
    }
    
    public void setCreateDummy(final boolean createDummy) {
        this.createDummy = createDummy;
    }
    
    public void setGraph(final Graph graph) {
        this.graph = graph;
    }
    
    public void assignLayers(final Set set, Set set2) {
        final AttributeManager attributeManager = this.graph.getAttributeManager();
        List list = GraphUtilities.getTopologicalOrdering(this.graph);
        if (list.size() != this.graph.getNodes().size()) {
            GraphUtilities.makeAcyclic(this.graph);
            list = GraphUtilities.getTopologicalOrdering(this.graph);
        }
        for (final Node node : list) {
            long max = 0L;
            for (final Edge edge : this.graph.getEdges(node)) {
                if (edge.getTarget().equals(node)) {
                    max = Math.max(max, ((Layer)attributeManager.getAttribute("layer", edge.getSource())).getLayer());
                }
            }
            attributeManager.setAttribute("layer", node, this.createLayer(max + 1L));
            node.setLabel(node.getLabel() + " layer = " + (max + 1L));
        }
        if (this.createDummy) {
            if (set2 == null) {
                set2 = new HashSet<Edge>();
            }
            final ArrayList<Edge> list2 = new ArrayList<Edge>();
            list2.addAll((Collection<?>)this.graph.getEdges());
            for (final Edge edge2 : list2) {
                final long layer = ((Layer)attributeManager.getAttribute("layer", edge2.getSource())).getLayer();
                final long layer2 = ((Layer)attributeManager.getAttribute("layer", edge2.getTarget())).getLayer();
                if (layer2 > layer + 1L) {
                    set2.add(edge2);
                    Node source = edge2.getSource();
                    for (long n = layer + 1L; n < layer2; ++n) {
                        final Node node2 = this.graph.createNode();
                        attributeManager.setAttribute("layer", node2, this.createLayer(n));
                        this.graph.createEdge(source, node2);
                        source = node2;
                        if (set != null) {
                            set.add(node2);
                        }
                    }
                    this.graph.createEdge(source, edge2.getTarget());
                }
            }
            final Iterator<Object> iterator4 = set2.iterator();
            while (iterator4.hasNext()) {
                this.graph.removeElement(iterator4.next());
            }
        }
    }
    
    private Layer createLayer(final long layer) {
        Layer layer2;
        try {
            layer2 = this.layerImpl.newInstance();
        }
        catch (Exception ex) {
            layer2 = new LayerImpl();
        }
        layer2.setLayer(layer);
        return layer2;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private class LayerImpl implements Layer
    {
        private long layer;
        
        public void setLayer(final long layer) {
            this.layer = layer;
        }
        
        public long getLayer() {
            return this.layer;
        }
    }
    
    public interface Layer
    {
        long getLayer();
        
        void setLayer(final long p0);
    }
}
