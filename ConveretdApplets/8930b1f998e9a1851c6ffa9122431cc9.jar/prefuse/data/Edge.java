// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

public interface Edge extends Tuple
{
    Graph getGraph();
    
    boolean isDirected();
    
    Node getSourceNode();
    
    Node getTargetNode();
    
    Node getAdjacentNode(final Node p0);
}
