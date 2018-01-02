// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.jaxp;

import org.xml.sax.SAXException;
import org.xml.sax.DocumentHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.helpers.XMLReaderAdapter;
import java.util.Enumeration;
import java.util.Hashtable;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.ErrorHandler;
import org.apache.crimson.parser.XMLReaderImpl;
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
    
    SAXParserImpl(final SAXParserFactory spf) throws SAXNotSupportedException, SAXNotRecognizedException {
        this.spf = null;
        this.parser = null;
        this.validating = false;
        this.namespaceAware = false;
        this.spf = spf;
        this.xmlReader = new XMLReaderImpl();
        this.validating = spf.isValidating();
        final String validation = "http://xml.org/sax/features/validation";
        this.xmlReader.setFeature(validation, this.validating);
        if (this.validating) {
            this.xmlReader.setErrorHandler(new DefaultValidationErrorHandler());
        }
        if (spf.isNamespaceAware()) {
            this.namespaceAware = true;
        }
    }
    
    void setFeatures(final Hashtable features) throws SAXNotSupportedException, SAXNotRecognizedException {
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
        if (this.parser == null) {
            (this.parser = new XMLReaderAdapter(this.xmlReader)).setDocumentHandler(new HandlerBase());
        }
        return this.parser;
    }
    
    public XMLReader getXMLReader() throws SAXException {
        return this.xmlReader;
    }
    
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }
    
    public boolean isValidating() {
        return this.validating;
    }
    
    public void setProperty(final String name, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.xmlReader.setProperty(name, value);
    }
    
    public Object getProperty(final String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.xmlReader.getProperty(name);
    }
}
