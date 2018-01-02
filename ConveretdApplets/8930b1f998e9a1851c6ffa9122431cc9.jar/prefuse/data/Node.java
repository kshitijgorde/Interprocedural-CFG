// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import java.util.Iterator;

public interface Node extends Tuple
{
    Graph getGraph();
    
    int getInDegree();
    
    int getOutDegree();
    
    int getDegree();
    
    Iterator inEdges();
    
    Iterator outEdges();
    
    Iterator edges();
    
    Iterator inNeighbors();
    
    Iterator outNeighbors();
    
    Iterator neighbors();
    
    Node getParent();
    
    Edge getParentEdge();
    
    int getDepth();
    
    int getChildCount();
    
    int getChildIndex(final Node p0);
    
    Node getChild(final int p0);
    
    Node getFirstChild();
    
    Node getLastChild();
    
    Node getPreviousSibling();
    
    Node getNextSibling();
    
    Iterator children();
    
    Iterator childEdges();
}
