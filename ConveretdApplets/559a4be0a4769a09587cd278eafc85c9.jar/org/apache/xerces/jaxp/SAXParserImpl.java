// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.Parser;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import java.util.Enumeration;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import java.util.Hashtable;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;

public class SAXParserImpl extends SAXParser implements JAXPConstants
{
    private XMLReader xmlReader;
    private String schemaLanguage;
    
    SAXParserImpl(final SAXParserFactory spf, final Hashtable features) throws SAXException {
        this.schemaLanguage = null;
        this.xmlReader = new org.apache.xerces.parsers.SAXParser();
        if (spf.isValidating()) {
            this.xmlReader.setErrorHandler(new DefaultValidationErrorHandler());
        }
        this.xmlReader.setFeature("http://xml.org/sax/features/validation", spf.isValidating());
        this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", spf.isNamespaceAware());
        this.xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", !spf.isNamespaceAware());
        this.setFeatures(features);
    }
    
    private void setFeatures(final Hashtable features) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (features != null) {
            final Enumeration e = features.keys();
            while (e.hasMoreElements()) {
                final String feature = e.nextElement();
                final boolean value = features.get(feature);
                this.xmlReader.setFeature(feature, value);
            }
        }
    }
    
    public Parser getParser() throws SAXException {
        return (Parser)this.xmlReader;
    }
    
    public XMLReader getXMLReader() {
        return this.xmlReader;
    }
    
    public boolean isNamespaceAware() {
        try {
            return this.xmlReader.getFeature("http://xml.org/sax/features/namespaces");
        }
        catch (SAXException x) {
            throw new IllegalStateException(x.getMessage());
        }
    }
    
    public boolean isValidating() {
        try {
            return this.xmlReader.getFeature("http://xml.org/sax/features/validation");
        }
        catch (SAXException x) {
            throw new IllegalStateException(x.getMessage());
        }
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(name)) {
            if ("http://www.w3.org/2001/XMLSchema".equals(value)) {
                this.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
                this.xmlReader.setFeature("http://apache.org/xml/features/validation/schema", true);
            }
            else {
                if (value != null) {
                    throw new SAXNotSupportedException("Unsupported schema language");
                }
                this.schemaLanguage = null;
                this.xmlReader.setFeature("http://apache.org/xml/features/validation/schema", false);
            }
        }
        else {
            this.xmlReader.setProperty(name, value);
        }
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if ("http://java.sun.com/xml/jaxp/properties/schemaLanguage".equals(name)) {
            return this.schemaLanguage;
        }
        return this.xmlReader.getProperty(name);
    }
}
