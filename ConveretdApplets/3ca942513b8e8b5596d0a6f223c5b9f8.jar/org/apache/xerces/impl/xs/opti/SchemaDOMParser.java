// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.opti;

import java.io.IOException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.w3c.dom.Document;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.xni.QName;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;

public class SchemaDOMParser extends DefaultXMLDocumentHandler
{
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    public static final String GENERATE_SYNTHETIC_ANNOTATION = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected XMLLocator fLocator;
    protected NamespaceContext fNamespaceContext;
    SchemaDOM schemaDOM;
    XMLParserConfiguration config;
    private ElementImpl fCurrentAnnotationElement;
    private int fAnnotationDepth;
    private int fInnerAnnotationDepth;
    private int fDepth;
    XMLErrorReporter fErrorReporter;
    private boolean fGenerateSyntheticAnnotation;
    private BooleanStack fHasNonSchemaAttributes;
    private BooleanStack fSawAnnotation;
    private XMLAttributes fEmptyAttr;
    
    public SchemaDOMParser(final XMLParserConfiguration config) {
        this.fNamespaceContext = null;
        this.fAnnotationDepth = -1;
        this.fInnerAnnotationDepth = -1;
        this.fDepth = -1;
        this.fGenerateSyntheticAnnotation = false;
        this.fHasNonSchemaAttributes = new BooleanStack();
        this.fSawAnnotation = new BooleanStack();
        this.fEmptyAttr = new XMLAttributesImpl();
        this.config = config;
    }
    
    public void startDocument(final XMLLocator fLocator, final String s, final NamespaceContext fNamespaceContext, final Augmentations augmentations) throws XNIException {
        this.fErrorReporter = (XMLErrorReporter)this.config.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fGenerateSyntheticAnnotation = this.config.getFeature("http://apache.org/xml/features/generate-synthetic-annotations");
        this.fHasNonSchemaAttributes.clear();
        this.fSawAnnotation.clear();
        this.schemaDOM = new SchemaDOM();
        this.fCurrentAnnotationElement = null;
        this.fAnnotationDepth = -1;
        this.fInnerAnnotationDepth = -1;
        this.fDepth = -1;
        this.fLocator = fLocator;
        this.fNamespaceContext = fNamespaceContext;
        this.schemaDOM.setDocumentURI(fLocator.getExpandedSystemId());
    }
    
    public void endDocument(final Augmentations augmentations) throws XNIException {
    }
    
    public void comment(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            this.schemaDOM.comment(xmlString);
        }
    }
    
    public void processingInstruction(final String s, final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            this.schemaDOM.processingInstruction(s, xmlString.toString());
        }
    }
    
    public void characters(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fInnerAnnotationDepth == -1) {
            for (int i = xmlString.offset; i < xmlString.offset + xmlString.length; ++i) {
                if (!XMLChar.isSpace(xmlString.ch[i])) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/xml-schema-1", "s4s-elt-character", new Object[] { new String(xmlString.ch, i, xmlString.length + xmlString.offset - i) }, (short)1);
                    break;
                }
            }
        }
        else {
            this.schemaDOM.characters(xmlString);
        }
    }
    
    public void startElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        ++this.fDepth;
        if (this.fAnnotationDepth == -1) {
            if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && qName.localpart == SchemaSymbols.ELT_ANNOTATION) {
                if (this.fGenerateSyntheticAnnotation) {
                    if (this.fSawAnnotation.size() > 0) {
                        this.fSawAnnotation.pop();
                    }
                    this.fSawAnnotation.push(true);
                }
                this.fAnnotationDepth = this.fDepth;
                this.schemaDOM.startAnnotation(qName, xmlAttributes, this.fNamespaceContext);
                this.fCurrentAnnotationElement = this.schemaDOM.startElement(qName, xmlAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
                return;
            }
            if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && this.fGenerateSyntheticAnnotation) {
                this.fSawAnnotation.push(false);
                this.fHasNonSchemaAttributes.push(this.hasNonSchemaAttributes(qName, xmlAttributes));
            }
        }
        else {
            if (this.fDepth != this.fAnnotationDepth + 1) {
                this.schemaDOM.startAnnotationElement(qName, xmlAttributes);
                return;
            }
            this.fInnerAnnotationDepth = this.fDepth;
            this.schemaDOM.startAnnotationElement(qName, xmlAttributes);
        }
        this.schemaDOM.startElement(qName, xmlAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
    }
    
    public void emptyElement(final QName qName, final XMLAttributes xmlAttributes, final Augmentations augmentations) throws XNIException {
        if (this.fGenerateSyntheticAnnotation && this.fAnnotationDepth == -1 && qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && qName.localpart != SchemaSymbols.ELT_ANNOTATION && this.hasNonSchemaAttributes(qName, xmlAttributes)) {
            this.schemaDOM.startElement(qName, xmlAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
            xmlAttributes.removeAllAttributes();
            final String prefix = this.fNamespaceContext.getPrefix(SchemaSymbols.URI_SCHEMAFORSCHEMA);
            final QName qName2 = new QName(prefix, SchemaSymbols.ELT_ANNOTATION, prefix + ((prefix.length() == 0) ? "" : ":") + SchemaSymbols.ELT_ANNOTATION, SchemaSymbols.URI_SCHEMAFORSCHEMA);
            this.schemaDOM.startAnnotation(qName2, xmlAttributes, this.fNamespaceContext);
            final QName qName3 = new QName(prefix, SchemaSymbols.ELT_DOCUMENTATION, prefix + ((prefix.length() == 0) ? "" : ":") + SchemaSymbols.ELT_DOCUMENTATION, SchemaSymbols.URI_SCHEMAFORSCHEMA);
            this.schemaDOM.startAnnotationElement(qName3, xmlAttributes);
            this.schemaDOM.characters(new XMLString("SYNTHETIC_ANNOTATION".toCharArray(), 0, 20));
            this.schemaDOM.endSyntheticAnnotationElement(qName3, false);
            this.schemaDOM.endSyntheticAnnotationElement(qName2, true);
            this.schemaDOM.endElement();
            return;
        }
        if (this.fAnnotationDepth == -1) {
            if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && qName.localpart == SchemaSymbols.ELT_ANNOTATION) {
                this.schemaDOM.startAnnotation(qName, xmlAttributes, this.fNamespaceContext);
            }
        }
        else {
            this.schemaDOM.startAnnotationElement(qName, xmlAttributes);
        }
        final ElementImpl emptyElement = this.schemaDOM.emptyElement(qName, xmlAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
        if (this.fAnnotationDepth == -1) {
            if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && qName.localpart == SchemaSymbols.ELT_ANNOTATION) {
                this.schemaDOM.endAnnotation(qName, emptyElement);
            }
        }
        else {
            this.schemaDOM.endAnnotationElement(qName);
        }
    }
    
    public void endElement(final QName qName, final Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            if (this.fInnerAnnotationDepth == this.fDepth) {
                this.fInnerAnnotationDepth = -1;
                this.schemaDOM.endAnnotationElement(qName);
                this.schemaDOM.endElement();
            }
            else if (this.fAnnotationDepth == this.fDepth) {
                this.fAnnotationDepth = -1;
                this.schemaDOM.endAnnotation(qName, this.fCurrentAnnotationElement);
                this.schemaDOM.endElement();
            }
            else {
                this.schemaDOM.endAnnotationElement(qName);
            }
        }
        else {
            if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && this.fGenerateSyntheticAnnotation) {
                final boolean pop = this.fHasNonSchemaAttributes.pop();
                final boolean pop2 = this.fSawAnnotation.pop();
                if (pop && !pop2) {
                    final String prefix = this.fNamespaceContext.getPrefix(SchemaSymbols.URI_SCHEMAFORSCHEMA);
                    final QName qName2 = new QName(prefix, SchemaSymbols.ELT_ANNOTATION, prefix + ((prefix.length() == 0) ? "" : ":") + SchemaSymbols.ELT_ANNOTATION, SchemaSymbols.URI_SCHEMAFORSCHEMA);
                    this.schemaDOM.startAnnotation(qName2, this.fEmptyAttr, this.fNamespaceContext);
                    final QName qName3 = new QName(prefix, SchemaSymbols.ELT_DOCUMENTATION, prefix + ((prefix.length() == 0) ? "" : ":") + SchemaSymbols.ELT_DOCUMENTATION, SchemaSymbols.URI_SCHEMAFORSCHEMA);
                    this.schemaDOM.startAnnotationElement(qName3, this.fEmptyAttr);
                    this.schemaDOM.characters(new XMLString("SYNTHETIC_ANNOTATION".toCharArray(), 0, 20));
                    this.schemaDOM.endSyntheticAnnotationElement(qName3, false);
                    this.schemaDOM.endSyntheticAnnotationElement(qName2, true);
                }
            }
            this.schemaDOM.endElement();
        }
        --this.fDepth;
    }
    
    private boolean hasNonSchemaAttributes(final QName qName, final XMLAttributes xmlAttributes) {
        for (int length = xmlAttributes.getLength(), i = 0; i < length; ++i) {
            final String uri = xmlAttributes.getURI(i);
            if (uri != null && uri != SchemaSymbols.URI_SCHEMAFORSCHEMA && uri != NamespaceContext.XMLNS_URI && (uri != NamespaceContext.XML_URI || xmlAttributes.getQName(i) != SchemaSymbols.ATT_XML_LANG || qName.localpart != SchemaSymbols.ELT_SCHEMA)) {
                return true;
            }
        }
        return false;
    }
    
    public void ignorableWhitespace(final XMLString xmlString, final Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.characters(xmlString);
        }
    }
    
    public void startCDATA(final Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.startAnnotationCDATA();
        }
    }
    
    public void endCDATA(final Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.endAnnotationCDATA();
        }
    }
    
    public Document getDocument() {
        return this.schemaDOM;
    }
    
    public void setFeature(final String s, final boolean b) {
        this.config.setFeature(s, b);
    }
    
    public boolean getFeature(final String s) {
        return this.config.getFeature(s);
    }
    
    public void setProperty(final String s, final Object o) {
        this.config.setProperty(s, o);
    }
    
    public Object getProperty(final String s) {
        return this.config.getProperty(s);
    }
    
    public void setEntityResolver(final XMLEntityResolver entityResolver) {
        this.config.setEntityResolver(entityResolver);
    }
    
    public void parse(final XMLInputSource xmlInputSource) throws IOException {
        this.config.parse(xmlInputSource);
    }
    
    public Document getDocument2() {
        return ((SchemaParsingConfig)this.config).getDocument();
    }
    
    public void reset() {
        ((SchemaParsingConfig)this.config).reset();
    }
    
    public void resetNodePool() {
        ((SchemaParsingConfig)this.config).resetNodePool();
    }
    
    private static final class BooleanStack
    {
        private int fDepth;
        private boolean[] fData;
        
        public int size() {
            return this.fDepth;
        }
        
        public void push(final boolean b) {
            this.ensureCapacity(this.fDepth + 1);
            this.fData[this.fDepth++] = b;
        }
        
        public boolean pop() {
            final boolean[] fData = this.fData;
            final int fDepth = this.fDepth - 1;
            this.fDepth = fDepth;
            return fData[fDepth];
        }
        
        public void clear() {
            this.fDepth = 0;
        }
        
        private void ensureCapacity(final int n) {
            if (this.fData == null) {
                this.fData = new boolean[32];
            }
            else if (this.fData.length <= n) {
                final boolean[] fData = new boolean[this.fData.length * 2];
                System.arraycopy(this.fData, 0, fData, 0, this.fData.length);
                this.fData = fData;
            }
        }
    }
}
