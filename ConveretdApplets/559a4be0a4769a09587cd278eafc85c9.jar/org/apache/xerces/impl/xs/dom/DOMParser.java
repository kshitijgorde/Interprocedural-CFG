// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.dom;

import org.w3c.dom.Element;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.parsers.NonValidatingConfiguration;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.xni.XMLLocator;

public class DOMParser extends org.apache.xerces.parsers.DOMParser
{
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String DOCUMENT_CLASS = "http://apache.org/xml/properties/dom/document-class-name";
    protected static final String DEFER_EXPANSION = "http://apache.org/xml/features/dom/defer-node-expansion";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected XMLLocator fLocator;
    public DocumentImpl fDocumentImpl;
    private DOMNodePool fNodePool;
    private int fAnnotationDepth;
    private int fDepth;
    XMLErrorReporter fErrorReporter;
    
    public DOMParser() {
        super(new NonValidatingConfiguration());
        this.fAnnotationDepth = -1;
        this.fDepth = -1;
        try {
            this.setProperty("http://apache.org/xml/properties/dom/document-class-name", "org.apache.xerces.impl.xs.dom.DocumentImpl");
            this.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        }
        catch (Exception ex) {}
    }
    
    public void setPool(final DOMNodePool nodePool) {
        this.fNodePool = nodePool;
    }
    
    public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException {
        super.startDocument(locator, encoding, namespaceContext, augs);
        this.fDocumentImpl = (DocumentImpl)super.fDocumentImpl;
        this.fDocumentImpl.fNodePool = this.fNodePool;
        this.fLocator = locator;
    }
    
    public void startElement(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        super.startElement(element, attributes, augs);
        ++this.fDepth;
        if (this.fAnnotationDepth == -1 && element.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && (element.localpart == SchemaSymbols.ELT_APPINFO || element.localpart == SchemaSymbols.ELT_DOCUMENTATION)) {
            this.fAnnotationDepth = this.fDepth;
        }
    }
    
    public void characters(final XMLString text, final Augmentations augs) throws XNIException {
        if (this.fAnnotationDepth == -1) {
            for (int i = text.offset; i < text.offset + text.length; ++i) {
                if (!XMLChar.isSpace(text.ch[i])) {
                    if (this.fErrorReporter == null) {
                        try {
                            this.fErrorReporter = (XMLErrorReporter)this.getProperty("http://apache.org/xml/properties/internal/error-reporter");
                        }
                        catch (Exception ex) {}
                        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
                            final XSMessageFormatter xmft = new XSMessageFormatter();
                            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", xmft);
                        }
                    }
                    final String txt = new String(text.ch, i, text.length + text.offset - i);
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "s4s-elt-character", new Object[] { txt }, (short)1);
                    break;
                }
            }
        }
        else {
            super.characters(text, augs);
        }
    }
    
    public void endElement(final QName element, final Augmentations augs) throws XNIException {
        super.endElement(element, augs);
        if (this.fAnnotationDepth == this.fDepth) {
            this.fAnnotationDepth = -1;
        }
        --this.fDepth;
    }
    
    protected Element createElementNode(final QName element) {
        return this.fDocumentImpl.createElementNS(element.uri, element.rawname, element.localpart, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber());
    }
}
