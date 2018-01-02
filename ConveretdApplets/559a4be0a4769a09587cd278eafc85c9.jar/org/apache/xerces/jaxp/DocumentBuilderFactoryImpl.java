// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp;

import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilderFactory;

public class DocumentBuilderFactoryImpl extends DocumentBuilderFactory
{
    private Hashtable attributes;
    
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        try {
            return new DocumentBuilderImpl(this, this.attributes);
        }
        catch (SAXException se) {
            throw new ParserConfigurationException(se.getMessage());
        }
    }
    
    public void setAttribute(final String name, final Object value) throws IllegalArgumentException {
        if (value == null) {
            if (this.attributes != null) {
                this.attributes.remove(name);
            }
            return;
        }
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }
        this.attributes.put(name, value);
        try {
            new DocumentBuilderImpl(this, this.attributes);
        }
        catch (Exception e) {
            this.attributes.remove(name);
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    public Object getAttribute(final String name) throws IllegalArgumentException {
        if (this.attributes != null) {
            final Object val = this.attributes.get(name);
            if (val != null) {
                return val;
            }
        }
        DOMParser domParser = null;
        try {
            domParser = new DocumentBuilderImpl(this, this.attributes).getDOMParser();
            return domParser.getProperty(name);
        }
        catch (SAXException se1) {
            try {
                return domParser.getFeature(name) ? Boolean.TRUE : Boolean.FALSE;
            }
            catch (SAXException se2) {
                throw new IllegalArgumentException(se1.getMessage());
            }
        }
    }
}
