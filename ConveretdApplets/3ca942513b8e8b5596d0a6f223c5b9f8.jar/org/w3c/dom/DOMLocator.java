// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom;

public interface DOMLocator
{
    int getLineNumber();
    
    int getColumnNumber();
    
    int getByteOffset();
    
    int getUtf16Offset();
    
    Node getRelatedNode();
    
    String getUri();
}
