// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import org.xml.sax.SAXException;
import org.apache.xml.serializer.EmptySerializer;

public final class StringValueHandler extends EmptySerializer
{
    private StringBuffer _buffer;
    private String _str;
    private static final String EMPTY_STR = "";
    private boolean m_escaping;
    private int _nestedLevel;
    
    public StringValueHandler() {
        this._buffer = new StringBuffer();
        this._str = null;
        this.m_escaping = false;
        this._nestedLevel = 0;
    }
    
    public void characters(final char[] ch, final int off, final int len) throws SAXException {
        if (this._nestedLevel > 0) {
            return;
        }
        if (this._str != null) {
            this._buffer.append(this._str);
            this._str = null;
        }
        this._buffer.append(ch, off, len);
    }
    
    public String getValue() {
        if (this._buffer.length() != 0) {
            final String result = this._buffer.toString();
            this._buffer.setLength(0);
            return result;
        }
        final String result = this._str;
        this._str = null;
        return (result != null) ? result : "";
    }
    
    public void characters(final String characters) throws SAXException {
        if (this._nestedLevel > 0) {
            return;
        }
        if (this._str == null && this._buffer.length() == 0) {
            this._str = characters;
        }
        else {
            if (this._str != null) {
                this._buffer.append(this._str);
                this._str = null;
            }
            this._buffer.append(characters);
        }
    }
    
    public void startElement(final String qname) throws SAXException {
        ++this._nestedLevel;
    }
    
    public void endElement(final String qname) throws SAXException {
        --this._nestedLevel;
    }
    
    public boolean setEscaping(final boolean bool) {
        final boolean oldEscaping = this.m_escaping;
        return this.m_escaping = bool;
    }
    
    public String getValueOfPI() {
        final String value = this.getValue();
        if (value.indexOf("?>") > 0) {
            final int n = value.length();
            final StringBuffer valueOfPI = new StringBuffer();
            int i = 0;
            while (i < n) {
                final char ch = value.charAt(i++);
                if (ch == '?' && value.charAt(i) == '>') {
                    valueOfPI.append("? >");
                    ++i;
                }
                else {
                    valueOfPI.append(ch);
                }
            }
            return valueOfPI.toString();
        }
        return value;
    }
}
