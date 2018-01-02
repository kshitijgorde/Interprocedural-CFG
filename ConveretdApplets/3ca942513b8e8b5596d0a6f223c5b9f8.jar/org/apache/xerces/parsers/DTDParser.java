// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import org.apache.xerces.xni.parser.XMLDTDSource;
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
    
    public void startEntity(final String s, final String s2, final String s3, final String s4) throws XNIException {
    }
    
    public void textDecl(final String s, final String s2) throws XNIException {
    }
    
    public void startDTD(final XMLLocator xmlLocator, final Augmentations augmentations) throws XNIException {
    }
    
    public void comment(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
    }
    
    public void processingInstruction(final String s, final XMLString xmlString, final Augmentations augmentations) throws XNIException {
    }
    
    public void startExternalSubset(final XMLResourceIdentifier xmlResourceIdentifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void endExternalSubset(final Augmentations augmentations) throws XNIException {
    }
    
    public void elementDecl(final String s, final String s2, final Augmentations augmentations) throws XNIException {
    }
    
    public void startAttlist(final String s, final Augmentations augmentations) throws XNIException {
    }
    
    public void attributeDecl(final String s, final String s2, final String s3, final String[] array, final String s4, final XMLString xmlString, final XMLString xmlString2, final Augmentations augmentations) throws XNIException {
    }
    
    public void endAttlist(final Augmentations augmentations) throws XNIException {
    }
    
    public void internalEntityDecl(final String s, final XMLString xmlString, final XMLString xmlString2, final Augmentations augmentations) throws XNIException {
    }
    
    public void externalEntityDecl(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void unparsedEntityDecl(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final String s2, final Augmentations augmentations) throws XNIException {
    }
    
    public void notationDecl(final String s, final XMLResourceIdentifier xmlResourceIdentifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void startConditional(final short n, final Augmentations augmentations) throws XNIException {
    }
    
    public void endConditional(final Augmentations augmentations) throws XNIException {
    }
    
    public void endDTD(final Augmentations augmentations) throws XNIException {
    }
    
    public void endEntity(final String s, final Augmentations augmentations) throws XNIException {
    }
    
    public void startContentModel(final String s, final short n) throws XNIException {
    }
    
    public void mixedElement(final String s) throws XNIException {
    }
    
    public void childrenStartGroup() throws XNIException {
    }
    
    public void childrenElement(final String s) throws XNIException {
    }
    
    public void childrenSeparator(final short n) throws XNIException {
    }
    
    public void childrenOccurrence(final short n) throws XNIException {
    }
    
    public void childrenEndGroup() throws XNIException {
    }
    
    public void endContentModel() throws XNIException {
    }
    
    public abstract XMLDTDSource getDTDSource();
    
    public abstract void setDTDSource(final XMLDTDSource p0);
    
    public abstract void ignoredCharacters(final XMLString p0, final Augmentations p1) throws XNIException;
    
    public abstract void endParameterEntity(final String p0, final Augmentations p1) throws XNIException;
    
    public abstract void textDecl(final String p0, final String p1, final Augmentations p2) throws XNIException;
    
    public abstract void startParameterEntity(final String p0, final XMLResourceIdentifier p1, final String p2, final Augmentations p3) throws XNIException;
    
    public abstract XMLDTDContentModelSource getDTDContentModelSource();
    
    public abstract void setDTDContentModelSource(final XMLDTDContentModelSource p0);
    
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
