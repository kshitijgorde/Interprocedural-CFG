// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

import java.util.Collection;

public interface Graph extends Element, GraphListener
{
    Node createNode();
    
    Node createNode(final String p0) throws GraphException;
    
    Group createGroup();
    
    Group createGroup(final String p0) throws GraphException;
    
    Edge createEdge(final Node p0, final Node p1);
    
    Edge createEdge(final String p0, final Node p1, final Node p2) throws GraphException;
    
    Element getElement(final String p0);
    
    Collection getEdges();
    
    Collection getEdges(final Node p0);
    
    Collection getNodes();
    
    void addElement(final Element p0) throws GraphException;
    
    void removeElement(final Element p0);
    
    void removeAll();
    
    boolean isConnected(final Node p0, final Node p1);
    
    GraphSystem getGraphSystem();
    
    AttributeManager getAttributeManager();
    
    Graph getSpanningTree();
    
    Graph getSpanningTree(final Node p0);
    
    void addGraphListener(final GraphListener p0);
    
    void removeGraphListener(final GraphListener p0);
}
