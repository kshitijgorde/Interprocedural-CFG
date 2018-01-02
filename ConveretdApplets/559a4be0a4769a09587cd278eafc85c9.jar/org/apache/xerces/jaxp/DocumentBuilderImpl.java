// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import java.util.Enumeration;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import javax.xml.parsers.DocumentBuilder;

public class DocumentBuilderImpl extends DocumentBuilder implements JAXPConstants
{
    private EntityResolver er;
    private ErrorHandler eh;
    private DOMParser domParser;
    
    DocumentBuilderImpl(final DocumentBuilderFactory dbf, final Hashtable dbfAttrs) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.er = null;
        this.eh = null;
        this.domParser = null;
        this.domParser = new DOMParser();
        if (dbf.isValidating()) {
            this.setErrorHandler(new DefaultValidationErrorHandler());
        }
        this.domParser.setFeature("http://xml.org/sax/features/validation", dbf.isValidating());
        this.domParser.setFeature("http://xml.org/sax/features/namespaces", dbf.isNamespaceAware());
        this.domParser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", !dbf.isIgnoringElementContentWhitespace());
        this.domParser.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", !dbf.isExpandEntityReferences());
        this.domParser.setFeature("http://apache.org/xml/features/include-comments", !dbf.isIgnoringComments());
        this.domParser.setFeature("http://apache.org/xml/features/create-cdata-nodes", !dbf.isCoalescing());
        this.setDocumentBuilderFactoryAttributes(dbfAttrs);
    }
    
    private void setDocumentBuilderFactoryAttributes(final Hashtable dbfAttrs) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (dbfAttrs == null) {
            return;
        }
        final Enumeration e = dbfAttrs.keys();
        while (e.hasMoreElements()) {
            final String name = e.nextElement();
            final Object val = dbfAttrs.get(name);
            if (val instanceof Boolean) {
                this.domParser.setFeature(name, (boolean)val);
            }
            else if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(name)) {
                if (!"http://www.w3.org/2001/XMLSchema".equals(val)) {
                    continue;
                }
                this.domParser.setFeature("http://apache.org/xml/features/validation/schema", true);
            }
            else {
                this.domParser.setProperty(name, val);
            }
        }
    }
    
    public Document newDocument() {
        return new DocumentImpl();
    }
    
    public DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public Document parse(final InputSource is) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        if (this.er != null) {
            this.domParser.setEntityResolver(this.er);
        }
        if (this.eh != null) {
            this.domParser.setErrorHandler(this.eh);
        }
        this.domParser.parse(is);
        return this.domParser.getDocument();
    }
    
    public boolean isNamespaceAware() {
        try {
            return this.domParser.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (SAXException x) {
            throw new IllegalStateException(x.getMessage());
        }
    }
    
    public boolean isValidating() {
        try {
            return this.domParser.getFeature("http://xml.org/sax/features/validation");
        }
        catch (SAXException x) {
            throw new IllegalStateException(x.getMessage());
        }
    }
    
    public void setEntityResolver(final EntityResolver er) {
        this.er = er;
    }
    
    public void setErrorHandler(final ErrorHandler eh) {
        this.eh = ((eh == null) ? new DefaultHandler() : eh);
    }
    
    DOMParser getDOMParser() {
        return this.domParser;
    }
}
