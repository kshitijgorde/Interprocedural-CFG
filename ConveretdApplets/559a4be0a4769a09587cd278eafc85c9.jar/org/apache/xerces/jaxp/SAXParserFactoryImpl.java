// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import java.util.Hashtable;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserFactoryImpl extends SAXParserFactory
{
    private Hashtable features;
    
    public SAXParser newSAXParser() throws ParserConfigurationException {
        SAXParser saxParserImpl;
        try {
            saxParserImpl = new SAXParserImpl(this, this.features);
        }
        catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
        return saxParserImpl;
    }
    
    private SAXParserImpl newSAXParserImpl() throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        SAXParserImpl saxParserImpl;
        try {
            saxParserImpl = new SAXParserImpl(this, this.features);
        }
        catch (SAXNotSupportedException e) {
            throw e;
        }
        catch (SAXNotRecognizedException e2) {
            throw e2;
        }
        catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
        return saxParserImpl;
    }
    
    public void setFeature(final String name, final boolean value) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        if (this.features == null) {
            this.features = new Hashtable();
        }
        this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
        try {
            this.newSAXParserImpl();
        }
        catch (SAXNotSupportedException e) {
            this.features.remove(name);
            throw e;
        }
        catch (SAXNotRecognizedException e2) {
            this.features.remove(name);
            throw e2;
        }
    }
    
    public boolean getFeature(final String name) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        return this.newSAXParserImpl().getXMLReader().getFeature(name);
    }
}
