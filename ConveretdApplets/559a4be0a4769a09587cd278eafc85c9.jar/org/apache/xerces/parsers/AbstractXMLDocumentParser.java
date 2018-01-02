// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLDocumentHandler;

public abstract class AbstractXMLDocumentParser extends XMLParser implements XMLDocumentHandler, XMLDTDHandler, XMLDTDContentModelHandler
{
    protected boolean fInDTD;
    protected XMLDocumentSource fDocumentSource;
    
    protected AbstractXMLDocumentParser(final XMLParserConfiguration config) {
        super(config);
        config.setDocumentHandler(this);
        config.setDTDHandler(this);
        config.setDTDContentModelHandler(this);
    }
    
    public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException {
    }
    
    public void xmlDecl(final String version, final String encoding, final String standalone, final Augmentations augs) throws XNIException {
    }
    
    public void doctypeDecl(final String rootElement, final String publicId, final String systemId, final Augmentations augs) throws XNIException {
    }
    
    public void startPrefixMapping(final String prefix, final String uri, final Augmentations augs) throws XNIException {
    }
    
    public void startElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
    }
    
    public void emptyElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        this.startElement(element, attributes, augs);
        this.endElement(element, augs);
    }
    
    public void characters(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void ignorableWhitespace(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
    }
    
    public void endPrefixMapping(final String prefix, final Augmentations augs) throws XNIException {
    }
    
    public void startCDATA(final Augmentations augs) throws XNIException {
    }
    
    public void endCDATA(final Augmentations augs) throws XNIException {
    }
    
    public void endDocument(final Augmentations augs) throws XNIException {
    }
    
    public void startGeneralEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
    }
    
    public void textDecl(final String version, final String encoding, final Augmentations augs) throws XNIException {
    }
    
    public void endGeneralEntity(final String name, final Augmentations augs) throws XNIException {
    }
    
    public void comment(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void processingInstruction(final String target, final XMLString data, final Augmentations augs) throws XNIException {
    }
    
    public void setDocumentSource(final XMLDocumentSource source) {
        this.fDocumentSource = source;
    }
    
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }
    
    public void startDTD(final XMLLocator locator, final Augmentations augs) throws XNIException {
        this.fInDTD = true;
    }
    
    public void startExternalSubset(final XMLResourceIdentifier identifier, final Augmentations augmentations) throws XNIException {
    }
    
    public void endExternalSubset(final Augmentations augmentations) throws XNIException {
    }
    
    public void startParameterEntity(final String name, final XMLResourceIdentifier identifier, final String encoding, final Augmentations augs) throws XNIException {
    }
    
    public void endParameterEntity(final String name, final Augmentations augs) throws XNIException {
    }
    
    public void ignoredCharacters(final XMLString text, final Augmentations augs) throws XNIException {
    }
    
    public void elementDecl(final String name, final String contentModel, final Augmentations augs) throws XNIException {
    }
    
    public void startAttlist(final String elementName, final Augmentations augs) throws XNIException {
    }
    
    public void attributeDecl(final String elementName, final String attributeName, final String type, final String[] enumeration, final String defaultType, final XMLString defaultValue, final XMLString nonNormalizedDefaultValue, final Augmentations augs) throws XNIException {
    }
    
    public void endAttlist(final Augmentations augs) throws XNIException {
    }
    
    public void internalEntityDecl(final String name, final XMLString text, final XMLString nonNormalizedText, final Augmentations augs) throws XNIException {
    }
    
    public void externalEntityDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
    }
    
    public void unparsedEntityDecl(final String name, final XMLResourceIdentifier identifier, final String notation, final Augmentations augs) throws XNIException {
    }
    
    public void notationDecl(final String name, final XMLResourceIdentifier identifier, final Augmentations augs) throws XNIException {
    }
    
    public void startConditional(final short type, final Augmentations augs) throws XNIException {
    }
    
    public void endConditional(final Augmentations augs) throws XNIException {
    }
    
    public void endDTD(final Augmentations augs) throws XNIException {
        this.fInDTD = false;
    }
    
    public void startContentModel(final String elementName, final Augmentations augs) throws XNIException {
    }
    
    public void any(final Augmentations augs) throws XNIException {
    }
    
    public void empty(final Augmentations augs) throws XNIException {
    }
    
    public void startGroup(final Augmentations augs) throws XNIException {
    }
    
    public void pcdata(final Augmentations augs) throws XNIException {
    }
    
    public void element(final String elementName, final Augmentations augs) throws XNIException {
    }
    
    public void separator(final short separator, final Augmentations augs) throws XNIException {
    }
    
    public void occurrence(final short occurrence, final Augmentations augs) throws XNIException {
    }
    
    public void endGroup(final Augmentations augs) throws XNIException {
    }
    
    public void endContentModel(final Augmentations augs) throws XNIException {
    }
    
    protected void reset() throws XNIException {
        super.reset();
        this.fInDTD = false;
    }
}
