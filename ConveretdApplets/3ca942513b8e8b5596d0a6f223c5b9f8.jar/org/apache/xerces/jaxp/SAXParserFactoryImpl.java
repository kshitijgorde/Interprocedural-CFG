// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.validation.Schema;
import java.util.Hashtable;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserFactoryImpl extends SAXParserFactory
{
    private Hashtable features;
    private Schema fSchema;
    private boolean fXIncludeAware;
    private boolean fSecureProcess;
    
    public SAXParserFactoryImpl() {
        this.fSchema = null;
        this.fXIncludeAware = false;
        this.fSecureProcess = false;
    }
    
    public SAXParser newSAXParser() throws ParserConfigurationException {
        SAXParserImpl saxParserImpl;
        try {
            saxParserImpl = new SAXParserImpl(this, this.features, this.fSecureProcess);
        }
        catch (SAXException ex) {
            throw new ParserConfigurationException(ex.getMessage());
        }
        return saxParserImpl;
    }
    
    private SAXParserImpl newSAXParserImpl() throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        SAXParserImpl saxParserImpl;
        try {
            saxParserImpl = new SAXParserImpl(this, this.features);
        }
        catch (SAXNotSupportedException ex) {
            throw ex;
        }
        catch (SAXNotRecognizedException ex2) {
            throw ex2;
        }
        catch (SAXException ex3) {
            throw new ParserConfigurationException(ex3.getMessage());
        }
        return saxParserImpl;
    }
    
    public void setFeature(final String s, final boolean fSecureProcess) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this.fSecureProcess = fSecureProcess;
            return;
        }
        if (this.features == null) {
            this.features = new Hashtable();
        }
        this.features.put(s, fSecureProcess ? Boolean.TRUE : Boolean.FALSE);
        try {
            this.newSAXParserImpl();
        }
        catch (SAXNotSupportedException ex) {
            this.features.remove(s);
            throw ex;
        }
        catch (SAXNotRecognizedException ex2) {
            this.features.remove(s);
            throw ex2;
        }
    }
    
    public boolean getFeature(final String s) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return this.fSecureProcess;
        }
        return this.newSAXParserImpl().getXMLReader().getFeature(s);
    }
    
    public Schema getSchema() {
        return this.fSchema;
    }
    
    public void setSchema(final Schema fSchema) {
        this.fSchema = fSchema;
    }
    
    public boolean isXIncludeAware() {
        return this.fXIncludeAware;
    }
    
    public void setXIncludeAware(final boolean fxIncludeAware) {
        this.fXIncludeAware = fxIncludeAware;
    }
}
