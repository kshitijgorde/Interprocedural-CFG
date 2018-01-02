// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.impl.dtd.DTDGrammar;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;

public abstract class DTDParser extends XMLGrammarParser implements XMLDTDHandler, XMLDTDContentModelHandler
{
    protected XMLDTDScanner fDTDScanner;
    
    public DTDParser(final SymbolTable symbolTable) {
        super(symbolTable);
    }
    
    public DTDGrammar getDTDGrammar() {
        return null;
    }
    
    public void startEntity(final String name, final String publicId, final String systemId, final String encoding) throws XNIException {
    }
    
    public void textDecl(final String version, final String encoding) throws XNIException {
    }
    
    public void startDTD(final XMLLocator locator, final Augmentations augmentations) throws XNIException {
    }
    
    public void comment(final XMLString text, final Augmentations augmentations) throws XNIException {
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augmentations) throws XNIException {
    }
    
    public void startExternalSubset(final XMLResourceIdentifier identifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void endExternalSubset(final Augmentations augmentations) throws XNIException {
    }
    
    public void elementDecl(final String name, final String contentModel, final Augmentations augmentations) throws XNIException {
    }
    
    public void startAttlist(final String elementName, final Augmentations augmentations) throws XNIException {
    }
    
    public void attributeDecl(final String elementName, final String attributeName, final String type, final String[] enumeration, final String defaultType, final XMLString defaultValue, final XMLString nonNormalizedDefaultValue, final Augmentations augmentations) throws XNIException {
    }
    
    public void endAttlist(final Augmentations augmentations) throws XNIException {
    }
    
    public void internalEntityDecl(final String name, final XMLString text, final XMLString nonNormalizedText, final Augmentations augmentations) throws XNIException {
    }
    
    public void externalEntityDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void unparsedEntityDecl(final String name, final XMLResourceIdentifier identifier, final String notation, final Augmentations augmentations) throws XNIException {
    }
    
    public void notationDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void startConditional(final short type, final Augmentations augmentations) throws XNIException {
    }
    
    public void endConditional(final Augmentations augmentations) throws XNIException {
    }
    
    public void endDTD(final Augmentations augmentations) throws XNIException {
    }
    
    public void endEntity(final String name, final Augmentations augmentations) throws XNIException {
    }
    
    public void startContentModel(final String elementName, final short type) throws XNIException {
    }
    
    public void mixedElement(final String elementName) throws XNIException {
    }
    
    public void childrenStartGroup() throws XNIException {
    }
    
    public void childrenElement(final String elementName) throws XNIException {
    }
    
    public void childrenSeparator(final short separator) throws XNIException {
    }
    
    public void childrenOccurrence(final short occurrence) throws XNIException {
    }
    
    public void childrenEndGroup() throws XNIException {
    }
    
    public void endContentModel() throws XNIException {
    }
    
    public abstract void ignoredCharacters(final XMLString p0, final Augmentations p1) throws XNIException;
    
    public abstract void endParameterEntity(final String p0, final Augmentations p1) throws XNIException;
    
    public abstract void textDecl(final String p0, final String p1, final Augmentations p2) throws XNIException;
    
    public abstract void startParameterEntity(final String p0, final XMLResourceIdentifier p1, final String p2, final Augmentations p3) throws XNIException;
    
    public abstract void endContentModel(final Augmentations p0) throws XNIException;
    
    public abstract void endGroup(final Augmentations p0) throws XNIException;
    
    public abstract void occurrence(final short p0, final Augmentations p1) throws XNIException;
    
    public abstract void separator(final short p0, final Augmentations p1) throws XNIException;
    
    public abstract void element(final String p0, final Augmentations p1) throws XNIException;
    
    public abstract void pcdata(final Augmentations p0) throws XNIException;
    
    public abstract void startGroup(final Augmentations p0) throws XNIException;
    
    public abstract void empty(final Augmentations p0) throws XNIException;
    
    public abstract void any(final Augmentations p0) throws XNIException;
    
    public abstract void startContentModel(final String p0, final Augmentations p1) throws XNIException;
}
