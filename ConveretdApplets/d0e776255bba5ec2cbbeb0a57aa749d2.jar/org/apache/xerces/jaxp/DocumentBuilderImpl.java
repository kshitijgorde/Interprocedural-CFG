// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

public class DocumentBuilderImpl extends DocumentBuilder
{
    static final String XERCES_FEATURE_PREFIX = "http://apache.org/xml/features/";
    static final String CREATE_ENTITY_REF_NODES_FEATURE = "dom/create-entity-ref-nodes";
    static final String INCLUDE_IGNORABLE_WHITESPACE = "dom/include-ignorable-whitespace";
    private DocumentBuilderFactory dbf;
    private EntityResolver er;
    private ErrorHandler eh;
    private DOMParser domParser;
    private boolean namespaceAware;
    private boolean validating;
    
    DocumentBuilderImpl(final DocumentBuilderFactory dbf) throws ParserConfigurationException {
        this.er = null;
        this.eh = null;
        this.domParser = null;
        this.namespaceAware = false;
        this.validating = false;
        this.dbf = dbf;
        this.domParser = new DOMParser();
        try {
            this.validating = dbf.isValidating();
            this.domParser.setFeature("http://xml.org/sax/features/validation", this.validating);
            if (this.validating) {
                this.setErrorHandler(new DefaultValidationErrorHandler());
            }
            this.namespaceAware = dbf.isNamespaceAware();
            this.domParser.setFeature("http://xml.org/sax/features/namespaces", this.namespaceAware);
            this.domParser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", !dbf.isIgnoringElementContentWhitespace());
            this.domParser.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", !dbf.isExpandEntityReferences());
        }
        catch (SAXException ex) {
            throw new ParserConfigurationException(ex.getMessage());
        }
    }
    
    public Document newDocument() {
        return new DocumentImpl();
    }
    
    public DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public Document parse(final InputSource inputSource) throws SAXException, IOException {
        if (inputSource == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        if (this.er != null) {
            this.domParser.setEntityResolver(this.er);
        }
        if (this.eh != null) {
            this.domParser.setErrorHandler(this.eh);
        }
        this.domParser.parse(inputSource);
        return this.domParser.getDocument();
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public boolean isValidating() {
        return this.validating;
    }
    
    public void setEntityResolver(final EntityResolver er) {
        this.er = er;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.eh = ((errorHandler == null) ? new DefaultHandler() : errorHandler);
    }
}
