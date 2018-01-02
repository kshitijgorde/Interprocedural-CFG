// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

public interface Edge extends Element
{
    Node getSource();
    
    Node getTarget();
    
    void setLabel(final String p0);
    
    String getLabel();
    
    Node getOtherNode(final Node p0);
    
    void reverse();
}
