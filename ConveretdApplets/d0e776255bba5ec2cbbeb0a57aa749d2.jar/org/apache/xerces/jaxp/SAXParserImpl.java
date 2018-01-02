// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.DocumentHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.helpers.XMLReaderAdapter;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import java.util.Enumeration;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import java.util.Hashtable;
import org.xml.sax.Parser;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

public class SAXParserImpl extends SAXParser
{
    private SAXParserFactory spf;
    private XMLReader xmlReader;
    private Parser parser;
    private boolean validating;
    private boolean namespaceAware;
    
    SAXParserImpl(final SAXParserFactory saxParserFactory, final Hashtable features) throws SAXException {
        this.spf = null;
        this.parser = null;
        this.validating = false;
        this.namespaceAware = false;
        this.xmlReader = new org.apache.xerces.parsers.SAXParser();
        this.validating = saxParserFactory.isValidating();
        final String s = "http://xml.org/sax/features/validation";
        if (this.validating) {
            this.xmlReader.setErrorHandler(new DefaultValidationErrorHandler());
        }
        this.xmlReader.setFeature(s, this.validating);
        this.namespaceAware = saxParserFactory.isNamespaceAware();
        this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", this.namespaceAware);
        this.setFeatures(features);
    }
    
    private void setFeatures(final Hashtable hashtable) throws SAXNotSupportedException, SAXNotRecognizedException {
        if (hashtable != null) {
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                this.xmlReader.setFeature(s, hashtable.get(s));
            }
        }
    }
    
    public Parser getParser() throws SAXException {
        if (this.parser == null) {
            (this.parser = new XMLReaderAdapter(this.xmlReader)).setDocumentHandler(new HandlerBase());
        }
        return this.parser;
    }
    
    public XMLReader getXMLReader() {
        return this.xmlReader;
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public boolean isValidating() {
        return this.validating;
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.xmlReader.setProperty(s, o);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.xmlReader.getProperty(s);
    }
}
