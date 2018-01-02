// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom3;

import org.w3c.dom.Node;

public interface DOMLocator
{
    int getLineNumber();
    
    int getColumnNumber();
    
    int getOffset();
    
    Node getErrorNode();
    
    String getUri();
}
