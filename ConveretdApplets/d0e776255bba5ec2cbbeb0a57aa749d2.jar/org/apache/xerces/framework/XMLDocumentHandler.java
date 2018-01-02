// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.apache.xerces.utils.QName;

public interface XMLDocumentHandler
{
    void startDocument() throws Exception;
    
    void endDocument() throws Exception;
    
    void xmlDecl(final int p0, final int p1, final int p2) throws Exception;
    
    void textDecl(final int p0, final int p1) throws Exception;
    
    void startNamespaceDeclScope(final int p0, final int p1) throws Exception;
    
    void endNamespaceDeclScope(final int p0) throws Exception;
    
    void startElement(final QName p0, final XMLAttrList p1, final int p2) throws Exception;
    
    void endElement(final QName p0) throws Exception;
    
    void startEntityReference(final int p0, final int p1, final int p2) throws Exception;
    
    void endEntityReference(final int p0, final int p1, final int p2) throws Exception;
    
    void processingInstruction(final int p0, final int p1) throws Exception;
    
    void comment(final int p0) throws Exception;
    
    void characters(final int p0) throws Exception;
    
    void characters(final char[] p0, final int p1, final int p2) throws Exception;
    
    void ignorableWhitespace(final int p0) throws Exception;
    
    void ignorableWhitespace(final char[] p0, final int p1, final int p2) throws Exception;
    
    void startCDATA() throws Exception;
    
    void endCDATA() throws Exception;
    
    public interface DTDHandler
    {
        void startDTD(final QName p0, final int p1, final int p2) throws Exception;
        
        void internalSubset(final int p0) throws Exception;
        
        void textDecl(final int p0, final int p1) throws Exception;
        
        void endDTD() throws Exception;
        
        void elementDecl(final QName p0, final int p1, final int p2, final XMLContentSpec.Provider p3) throws Exception;
        
        void attlistDecl(final QName p0, final QName p1, final int p2, final boolean p3, final String p4, final int p5, final int p6) throws Exception;
        
        void internalPEDecl(final int p0, final int p1) throws Exception;
        
        void externalPEDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void internalEntityDecl(final int p0, final int p1) throws Exception;
        
        void externalEntityDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void unparsedEntityDecl(final int p0, final int p1, final int p2, final int p3) throws Exception;
        
        void notationDecl(final int p0, final int p1, final int p2) throws Exception;
        
        void processingInstruction(final int p0, final int p1) throws Exception;
        
        void comment(final int p0) throws Exception;
    }
}
