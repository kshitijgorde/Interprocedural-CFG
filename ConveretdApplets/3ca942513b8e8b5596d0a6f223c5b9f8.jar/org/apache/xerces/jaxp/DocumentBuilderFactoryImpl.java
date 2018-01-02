// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.validation.Schema;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilderFactory;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory
{
    private Hashtable attributes;
    private Hashtable features;
    private Schema fSchema;
    private boolean fXInclude;
    private boolean fSecureProcess;
    private boolean doNotSetSchemaLanguageProperty;
    
    public DocumentBuilderFactoryImpl() {
        this.fSchema = null;
        this.fXInclude = false;
        this.fSecureProcess = false;
        this.doNotSetSchemaLanguageProperty = true;
    }
    
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        if ((this.getAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource") != null && this.fSchema != null) || (this.getAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage") != null && this.fSchema != null)) {
            throw new ParserConfigurationException("schemaSource and/or schemaLanguage properties should not be used in conjunction with a Schema object!");
        }
        try {
            return new DocumentBuilderImpl(this, this.attributes, this.fSecureProcess, !this.doNotSetSchemaLanguageProperty);
        }
        catch (SAXException ex) {
            throw new ParserConfigurationException(ex.getMessage());
        }
    }
    
    public void setAttribute(final String s, final Object o) throws IllegalArgumentException {
        if (o == null) {
            if (this.attributes != null) {
                this.attributes.remove(s);
            }
            return;
        }
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }
        this.attributes.put(s, o);
        try {
            new DocumentBuilderImpl(this, this.attributes, this.doNotSetSchemaLanguageProperty);
        }
        catch (Exception ex) {
            this.attributes.remove(s);
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public Object getAttribute(final String s) throws IllegalArgumentException {
        if (this.attributes != null) {
            final Object value = this.attributes.get(s);
            if (value != null) {
                return value;
            }
        }
        DOMParser domParser = null;
        try {
            domParser = new DocumentBuilderImpl(this, this.attributes, this.doNotSetSchemaLanguageProperty).getDOMParser();
            return domParser.getProperty(s);
        }
        catch (SAXException ex) {
            try {
                return domParser.getFeature(s) ? Boolean.TRUE : Boolean.FALSE;
            }
            catch (SAXException ex2) {
                throw new IllegalArgumentException(ex.getMessage());
            }
        }
    }
    
    public void setFeature(final String s, final boolean fSecureProcess) throws ParserConfigurationException {
        if (s == null) {
            throw new NullPointerException("DocumentBuilderFactory: setFeatures(String name, boolean value) received null name parameter!");
        }
        if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this.fSecureProcess = fSecureProcess;
            return;
        }
        if (this.features == null) {
            this.features = new Hashtable();
        }
        final Boolean b = new Boolean(fSecureProcess);
        final Object value = this.features.get(s);
        this.features.put(s, b);
        try {
            this.setAttribute(s, b);
        }
        catch (IllegalArgumentException ex) {
            if (value == null) {
                this.features.remove(s);
            }
            else {
                this.features.put(s, value);
            }
            throw new ParserConfigurationException(ex.getMessage());
        }
    }
    
    public boolean getFeature(final String s) throws ParserConfigurationException {
        if (s == null) {
            throw new NullPointerException();
        }
        if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return this.fSecureProcess;
        }
        if (this.features != null) {
            final Boolean value = this.features.get(s);
            if (value != null) {
                return value;
            }
        }
        try {
            return new DocumentBuilderImpl(this, this.attributes, this.doNotSetSchemaLanguageProperty).getDOMParser().getFeature(s);
        }
        catch (SAXNotRecognizedException ex) {
            throw new ParserConfigurationException(ex.getMessage());
        }
        catch (SAXNotSupportedException ex2) {
            throw new ParserConfigurationException(ex2.getMessage());
        }
    }
    
    public Schema getSchema() {
        return this.fSchema;
    }
    
    public void setSchema(final Schema fSchema) {
        this.fSchema = fSchema;
    }
    
    public void setXIncludeAware(final boolean fxInclude) {
        this.fXInclude = fxInclude;
    }
    
    public boolean isXIncludeAware() {
        return this.fXInclude;
    }
}
