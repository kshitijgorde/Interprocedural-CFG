// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceXMLFilter extends XMLFilterImpl
{
    private String prefix;
    private String newUri;
    
    public NamespaceXMLFilter(final String prefix, final String uri) {
        this.prefix = String.valueOf(prefix) + ":";
        this.newUri = uri;
    }
    
    public NamespaceXMLFilter(final String prefix, final String uri, final XMLReader reader) {
        super(reader);
    }
    
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        super.startElement(this.newUri, localName, String.valueOf(this.prefix) + qName, atts);
    }
    
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        super.endElement(this.newUri, localName, String.valueOf(this.prefix) + qName);
    }
}
