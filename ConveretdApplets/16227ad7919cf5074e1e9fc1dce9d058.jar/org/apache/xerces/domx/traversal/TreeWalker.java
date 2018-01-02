// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.domx.traversal;

import org.w3c.dom.Node;

public interface TreeWalker
{
    int getWhatToShow();
    
    NodeFilter getFilter();
    
    boolean getExpandEntityReferences();
    
    Node getCurrentNode();
    
    void setCurrentNode(final Node p0);
    
    Node parentNode();
    
    Node firstChild();
    
    Node lastChild();
    
    Node previousSibling();
    
    Node nextSibling();
    
    Node previousNode();
    
    Node nextNode();
}
