// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.jaxp;

import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import javax.xml.parsers.SAXParser;
import java.util.Hashtable;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserFactoryImpl extends SAXParserFactory
{
    private Hashtable features;
    
    private SAXParser newSAXParser0() throws SAXNotSupportedException, SAXNotRecognizedException {
        final SAXParserImpl saxParserImpl = new SAXParserImpl(this);
        saxParserImpl.setFeatures(this.features);
        return saxParserImpl;
    }
    
    public SAXParser newSAXParser() throws SAXException, ParserConfigurationException {
        SAXParser saxParserImpl;
        try {
            saxParserImpl = this.newSAXParser0();
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
        this.features.put(name, new Boolean(value));
        this.newSAXParser0();
    }
    
    public boolean getFeature(final String name) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        XMLReader xmlReader;
        try {
            xmlReader = this.newSAXParser0().getXMLReader();
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
        return xmlReader.getFeature(name);
    }
}
