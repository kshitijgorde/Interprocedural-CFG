// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import java.util.Vector;
import org.xml.sax.AttributeList;

public class AttributeListImpl implements AttributeList
{
    Vector names;
    Vector types;
    Vector values;
    
    public AttributeListImpl() {
        this.names = new Vector();
        this.types = new Vector();
        this.values = new Vector();
    }
    
    public AttributeListImpl(final AttributeList atts) {
        this.names = new Vector();
        this.types = new Vector();
        this.values = new Vector();
        this.setAttributeList(atts);
    }
    
    public void setAttributeList(final AttributeList atts) {
        final int count = atts.getLength();
        this.clear();
        for (int i = 0; i < count; ++i) {
            this.addAttribute(atts.getName(i), atts.getType(i), atts.getValue(i));
        }
    }
    
    public void addAttribute(final String name, final String type, final String value) {
        this.names.addElement(name);
        this.types.addElement(type);
        this.values.addElement(value);
    }
    
    public void removeAttribute(final String name) {
        final int i = this.names.indexOf(name);
        if (i >= 0) {
            this.names.removeElementAt(i);
            this.types.removeElementAt(i);
            this.values.removeElementAt(i);
        }
    }
    
    public void clear() {
        this.names.removeAllElements();
        this.types.removeAllElements();
        this.values.removeAllElements();
    }
    
    public int getLength() {
        return this.names.size();
    }
    
    public String getName(final int i) {
        if (i < 0) {
            return null;
        }
        try {
            return this.names.elementAt(i);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public String getType(final int i) {
        if (i < 0) {
            return null;
        }
        try {
            return this.types.elementAt(i);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public String getValue(final int i) {
        if (i < 0) {
            return null;
        }
        try {
            return this.values.elementAt(i);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public String getType(final String name) {
        return this.getType(this.names.indexOf(name));
    }
    
    public String getValue(final String name) {
        return this.getValue(this.names.indexOf(name));
    }
}
