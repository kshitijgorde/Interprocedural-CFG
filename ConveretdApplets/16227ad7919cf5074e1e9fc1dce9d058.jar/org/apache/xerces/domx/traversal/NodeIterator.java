// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx.traversal;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public interface NodeIterator
{
    int getWhatToShow();
    
    NodeFilter getFilter();
    
    boolean getExpandEntityReferences();
    
    Node nextNode() throws DOMException;
    
    Node previousNode() throws DOMException;
    
    void detach();
}
