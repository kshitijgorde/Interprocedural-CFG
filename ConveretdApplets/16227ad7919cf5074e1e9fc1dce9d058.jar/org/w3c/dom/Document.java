// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom;

public interface Document extends Node
{
    DocumentType getDoctype();
    
    DOMImplementation getImplementation();
    
    Element getDocumentElement();
    
    Element createElement(final String p0) throws DOMException;
    
    DocumentFragment createDocumentFragment();
    
    Text createTextNode(final String p0);
    
    Comment createComment(final String p0);
    
    CDATASection createCDATASection(final String p0) throws DOMException;
    
    ProcessingInstruction createProcessingInstruction(final String p0, final String p1) throws DOMException;
    
    Attr createAttribute(final String p0) throws DOMException;
    
    EntityReference createEntityReference(final String p0) throws DOMException;
    
    NodeList getElementsByTagName(final String p0);
}
