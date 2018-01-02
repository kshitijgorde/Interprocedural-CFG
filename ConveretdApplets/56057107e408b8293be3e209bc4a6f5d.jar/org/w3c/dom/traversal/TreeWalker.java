// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.traversal;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public interface TreeWalker
{
    Node firstChild();
    
    Node getCurrentNode();
    
    boolean getExpandEntityReferences();
    
    NodeFilter getFilter();
    
    Node getRoot();
    
    int getWhatToShow();
    
    Node lastChild();
    
    Node nextNode();
    
    Node nextSibling();
    
    Node parentNode();
    
    Node previousNode();
    
    Node previousSibling();
    
    void setCurrentNode(final Node p0) throws DOMException;
}
