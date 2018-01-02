// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom;

public interface Element extends Node
{
    String getTagName();
    
    String getAttribute(final String p0);
    
    void setAttribute(final String p0, final String p1) throws DOMException;
    
    void removeAttribute(final String p0) throws DOMException;
    
    Attr getAttributeNode(final String p0);
    
    Attr setAttributeNode(final Attr p0) throws DOMException;
    
    Attr removeAttributeNode(final Attr p0) throws DOMException;
    
    NodeList getElementsByTagName(final String p0);
    
    void normalize();
}
