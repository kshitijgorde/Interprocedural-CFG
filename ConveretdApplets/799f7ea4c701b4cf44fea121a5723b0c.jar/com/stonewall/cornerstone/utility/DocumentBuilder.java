// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.xml.sax.XMLFilter;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.JDOMParseException;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.jdom.input.SAXBuilder;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import org.jdom.Document;
import org.xmodel.log.Log;

public class DocumentBuilder
{
    private Validation validation;
    static final Log log;
    
    static {
        log = Log.getLog(DocumentBuilder.class);
    }
    
    public DocumentBuilder() {
    }
    
    public DocumentBuilder(final Validation validation) {
        this.validation = validation;
    }
    
    public Document buildDocument(final String content) throws Exception {
        return this.buildDocument(new StringReader(content));
    }
    
    public Document buildDocument(final InputStream is) throws Exception {
        return this.buildDocument(new InputStreamReader(is));
    }
    
    public Document buildDocument(final Reader reader) throws Exception {
        Document document = null;
        final Validation validationType = this.getValidation();
        if (!validationType.equals(Validation.none)) {
            final long start = System.currentTimeMillis();
            SAXBuilder builder = new SAXBuilder();
            builder.setValidation(true);
            builder.setFeature("http://apache.org/xml/features/validation/schema", true);
            try {
                document = builder.build(reader);
                final long finish = System.currentTimeMillis();
                DocumentBuilder.log.debug("DocumentBuilder - finished with validation: " + (finish - start) + " (ms)");
            }
            catch (JDOMParseException jdome) {
                DocumentBuilder.log.error("builder", (Throwable)jdome);
                builder = new SAXBuilder();
                document = builder.build(reader);
                final XMLOutputter op = new XMLOutputter(Format.getPrettyFormat());
                DocumentBuilder.log.error("Invalid document: " + op.outputString(document));
                if (validationType.equals(Validation.fail)) {
                    document.getRootElement().setAttribute("valid", "false");
                }
            }
        }
        else {
            final long start = System.currentTimeMillis();
            final SAXBuilder builder = new SAXBuilder();
            document = builder.build(reader);
            final long finish = System.currentTimeMillis();
            DocumentBuilder.log.debug("DocumentBuilder - finished without validation: " + (finish - start) + " (ms)");
        }
        reader.close();
        return document;
    }
    
    public Document validateDocument(final Document doc) {
        return this.validateDocument(doc, null);
    }
    
    public Document validateDocument(final Document doc, final String schemaLocation) {
        try {
            if (schemaLocation != null) {
                doc.getRootElement().setAttribute("schemaLocation", schemaLocation, Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance"));
            }
            final XMLOutputter op = new XMLOutputter();
            return this.buildDocument(op.outputString(doc));
        }
        catch (Exception e) {
            DocumentBuilder.log.error(e);
            return null;
        }
    }
    
    public Element addNamespacePrefix(final Element e, final String prefix, final String uri) {
        try {
            final SAXBuilder builder = new SAXBuilder();
            builder.setXMLFilter((XMLFilter)new NamespaceXMLFilter(prefix, uri));
            builder.setFeature("http://xml.org/sax/features/namespaces", true);
            builder.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
            final XMLOutputter op = new XMLOutputter();
            final Document document = builder.build((Reader)new StringReader(op.outputString(e)));
            return document.detachRootElement();
        }
        catch (Exception ex) {
            DocumentBuilder.log.error(e);
            return null;
        }
    }
    
    private Validation getValidation() {
        if (this.validation == null) {
            this.validation = this.getDefaultValidation();
        }
        return this.validation;
    }
    
    private Validation getDefaultValidation() {
        return Validation.valueOf(System.getProperty("cornerstone.document.validation", "process"));
    }
    
    public enum Validation
    {
        none("none", 0), 
        process("process", 1), 
        fail("fail", 2);
        
        private Validation(final String s, final int n) {
        }
    }
}
