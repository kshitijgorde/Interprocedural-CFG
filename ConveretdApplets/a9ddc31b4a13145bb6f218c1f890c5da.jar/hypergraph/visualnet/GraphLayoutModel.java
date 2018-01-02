// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.hyperbolic.ModelPoint;
import hypergraph.graphApi.Node;

public interface GraphLayoutModel
{
    void addLayoutEventListener(final GraphLayoutListener p0);
    
    void removeLayoutEventListener(final GraphLayoutListener p0);
    
    void fireLayoutChanged();
    
    void clearNodePositions();
    
    void setNodePosition(final Node p0, final ModelPoint p1);
    
    ModelPoint getNodePosition(final Node p0);
    
    void setValid(final boolean p0);
    
    boolean isValid();
}
