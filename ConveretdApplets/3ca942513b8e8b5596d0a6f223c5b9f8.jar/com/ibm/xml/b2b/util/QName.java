// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public class QName extends XMLName
{
    public String namespaceURI;
    public String localPart;
    public String prefix;
    public int nsHandle;
    public int localHandle;
    public int prefixHandle;
    public int sepOffset;
    
    public QName() {
        this.namespaceURI = XMLString.EMPTY_STRING;
        this.localPart = XMLString.EMPTY_STRING;
        this.prefix = XMLString.EMPTY_STRING;
        this.sepOffset = -1;
    }
    
    public QName(final QName qName) {
        super(qName);
        this.namespaceURI = qName.namespaceURI;
        this.localPart = qName.localPart;
        this.prefix = qName.prefix;
        this.nsHandle = qName.nsHandle;
        this.localHandle = qName.localHandle;
        this.prefixHandle = qName.prefixHandle;
        this.sepOffset = qName.sepOffset;
    }
    
    public void clear() {
        super.clear();
        this.namespaceURI = XMLString.EMPTY_STRING;
        this.localPart = XMLString.EMPTY_STRING;
        this.prefix = XMLString.EMPTY_STRING;
        this.nsHandle = 0;
        this.localHandle = 0;
        this.prefixHandle = 0;
        this.sepOffset = -1;
    }
    
    public void setValues(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        super.setValues(array, n, n2, encodingSupport);
        this.namespaceURI = XMLString.EMPTY_STRING;
        this.localPart = XMLString.EMPTY_STRING;
        this.prefix = XMLString.EMPTY_STRING;
        this.nsHandle = 0;
        this.localHandle = 0;
        this.prefixHandle = 0;
        this.sepOffset = -1;
    }
    
    public void setValues(final char[] array, final int n, final int n2) {
        super.setValues(array, n, n2);
        this.namespaceURI = XMLString.EMPTY_STRING;
        this.localPart = XMLString.EMPTY_STRING;
        this.prefix = XMLString.EMPTY_STRING;
        this.nsHandle = 0;
        this.localHandle = 0;
        this.prefixHandle = 0;
        this.sepOffset = -1;
    }
    
    public void setValues(final XMLString values) {
        super.setValues(values);
        this.namespaceURI = XMLString.EMPTY_STRING;
        this.localPart = XMLString.EMPTY_STRING;
        this.prefix = XMLString.EMPTY_STRING;
        this.nsHandle = 0;
        this.localHandle = 0;
        this.prefixHandle = 0;
        this.sepOffset = -1;
    }
    
    public void setValues(final QName values) {
        super.setValues(values);
        this.namespaceURI = values.namespaceURI;
        this.localPart = values.localPart;
        this.prefix = values.prefix;
        this.nsHandle = values.nsHandle;
        this.localHandle = values.localHandle;
        this.prefixHandle = values.prefixHandle;
        this.sepOffset = values.sepOffset;
    }
}
