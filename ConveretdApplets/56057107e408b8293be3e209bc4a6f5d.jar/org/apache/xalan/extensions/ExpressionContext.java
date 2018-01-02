// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;

public interface ExpressionContext
{
    Node getContextNode();
    
    NodeIterator getContextNodes();
    
    double toNumber(final Node p0);
    
    String toString(final Node p0);
}
