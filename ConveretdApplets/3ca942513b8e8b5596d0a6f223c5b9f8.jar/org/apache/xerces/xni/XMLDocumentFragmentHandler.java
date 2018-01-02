// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public interface XMLDocumentFragmentHandler
{
    void startDocumentFragment(final XMLLocator p0, final NamespaceContext p1, final Augmentations p2) throws XNIException;
    
    void startGeneralEntity(final String p0, final XMLResourceIdentifier p1, final String p2, final Augmentations p3) throws XNIException;
    
    void textDecl(final String p0, final String p1, final Augmentations p2) throws XNIException;
    
    void endGeneralEntity(final String p0, final Augmentations p1) throws XNIException;
    
    void comment(final XMLString p0, final Augmentations p1) throws XNIException;
    
    void processingInstruction(final String p0, final XMLString p1, final Augmentations p2) throws XNIException;
    
    void startElement(final QName p0, final XMLAttributes p1, final Augmentations p2) throws XNIException;
    
    void emptyElement(final QName p0, final XMLAttributes p1, final Augmentations p2) throws XNIException;
    
    void characters(final XMLString p0, final Augmentations p1) throws XNIException;
    
    void ignorableWhitespace(final XMLString p0, final Augmentations p1) throws XNIException;
    
    void endElement(final QName p0, final Augmentations p1) throws XNIException;
    
    void startCDATA(final Augmentations p0) throws XNIException;
    
    void endCDATA(final Augmentations p0) throws XNIException;
    
    void endDocumentFragment(final Augmentations p0) throws XNIException;
}
