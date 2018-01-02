// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.jaxp;

import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.apache.crimson.tree.DOMImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import org.apache.crimson.parser.XMLReaderImpl;
import org.apache.crimson.tree.XmlDocumentBuilder;
import org.xml.sax.XMLReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.EntityResolver;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

public class DocumentBuilderImpl extends DocumentBuilder
{
    private DocumentBuilderFactory dbf;
    private EntityResolver er;
    private ErrorHandler eh;
    private XMLReader xmlReader;
    private XmlDocumentBuilder builder;
    private boolean namespaceAware;
    private boolean validating;
    
    DocumentBuilderImpl(final DocumentBuilderFactory dbf) throws ParserConfigurationException {
        this.er = null;
        this.eh = null;
        this.xmlReader = null;
        this.builder = null;
        this.namespaceAware = false;
        this.validating = false;
        this.dbf = dbf;
        this.xmlReader = new XMLReaderImpl();
        try {
            this.validating = dbf.isValidating();
            final String validation = "http://xml.org/sax/features/validation";
            this.xmlReader.setFeature(validation, this.validating);
            if (this.validating) {
                this.setErrorHandler(new DefaultValidationErrorHandler());
            }
            final String namespaces = "http://xml.org/sax/features/namespaces";
            this.xmlReader.setFeature(namespaces, true);
            final String nsPrefixes = "http://xml.org/sax/features/namespace-prefixes";
            this.xmlReader.setFeature(nsPrefixes, true);
            this.builder = new XmlDocumentBuilder();
            this.xmlReader.setContentHandler(this.builder);
            final String lexHandler = "http://xml.org/sax/properties/lexical-handler";
            this.xmlReader.setProperty(lexHandler, this.builder);
            final String declHandler = "http://xml.org/sax/properties/declaration-handler";
            this.xmlReader.setProperty(declHandler, this.builder);
            this.xmlReader.setDTDHandler(this.builder);
        }
        catch (SAXException e) {
            throw new ParserConfigurationException(e.getMessage());
        }
        this.namespaceAware = dbf.isNamespaceAware();
        this.builder.setDisableNamespaces(!this.namespaceAware);
        this.builder.setIgnoreWhitespace(dbf.isIgnoringElementContentWhitespace());
        this.builder.setExpandEntityReferences(dbf.isExpandEntityReferences());
        this.builder.setIgnoreComments(dbf.isIgnoringComments());
        this.builder.setPutCDATAIntoText(dbf.isCoalescing());
    }
    
    public Document newDocument() {
        return new XmlDocument();
    }
    
    public DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public Document parse(final InputSource is) throws SAXException, IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputSource cannot be null");
        }
        if (this.er != null) {
            this.xmlReader.setEntityResolver(this.er);
        }
        if (this.eh != null) {
            this.xmlReader.setErrorHandler(this.eh);
        }
        this.xmlReader.parse(is);
        return this.builder.getDocument();
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
    
    public void setErrorHandler(final ErrorHandler eh) {
        this.eh = ((eh == null) ? new DefaultHandler() : eh);
    }
}
