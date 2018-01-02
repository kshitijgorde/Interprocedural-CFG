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
        SAXParserImpl saxParserImpl;
        try {
            saxParserImpl = new SAXParserImpl(this, this.features);
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
    
    public void setFeature(final String s, final boolean b) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        if (this.features == null) {
            this.features = new Hashtable();
        }
        this.features.put(s, new Boolean(b));
        this.newSAXParserImpl();
    }
    
    public boolean getFeature(final String s) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        return this.newSAXParserImpl().getXMLReader().getFeature(s);
    }
}
