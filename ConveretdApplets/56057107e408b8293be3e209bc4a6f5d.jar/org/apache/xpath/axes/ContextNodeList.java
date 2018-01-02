// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

public interface ContextNodeList
{
    Object clone() throws CloneNotSupportedException;
    
    NodeIterator cloneWithReset() throws CloneNotSupportedException;
    
    Node getCurrentNode();
    
    int getCurrentPos();
    
    int getLast();
    
    boolean isFresh();
    
    void reset();
    
    void runTo(final int p0);
    
    void setCurrentPos(final int p0);
    
    void setLast(final int p0);
    
    void setShouldCacheNodes(final boolean p0);
    
    int size();
}
