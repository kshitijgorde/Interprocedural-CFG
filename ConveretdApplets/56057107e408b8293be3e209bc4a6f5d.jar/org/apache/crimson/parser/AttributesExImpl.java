// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

import java.util.Vector;
import org.xml.sax.helpers.AttributesImpl;

final class AttributesExImpl extends AttributesImpl implements AttributesEx
{
    private Vector specified;
    private Vector defaults;
    private String idAttributeName;
    
    AttributesExImpl() {
        this.specified = new Vector();
        this.defaults = new Vector();
    }
    
    public void clear() {
        super.clear();
        this.specified.removeAllElements();
        this.defaults.removeAllElements();
        this.idAttributeName = null;
    }
    
    public void addAttribute(final String uri, final String localName, final String qName, final String type, final String value, final String defaultValue, final boolean isSpecified) {
        super.addAttribute(uri, localName, qName, type, value);
        this.defaults.addElement(defaultValue);
        this.specified.addElement(isSpecified ? Boolean.TRUE : null);
    }
    
    public boolean isSpecified(final int i) {
        final Object value = this.specified.elementAt(i);
        return value == Boolean.TRUE;
    }
    
    public String getDefault(final int i) {
        try {
            if (i < 0) {
                return null;
            }
            return this.defaults.elementAt(i);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public String getIdAttributeName() {
        return this.idAttributeName;
    }
    
    void setIdAttributeName(final String name) {
        this.idAttributeName = name;
    }
}
