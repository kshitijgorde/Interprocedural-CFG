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
    
    public AttributeListImpl(final AttributeList attributeList) {
        this.names = new Vector();
        this.types = new Vector();
        this.values = new Vector();
        this.setAttributeList(attributeList);
    }
    
    public void setAttributeList(final AttributeList list) {
        final int length = list.getLength();
        this.clear();
        for (int i = 0; i < length; ++i) {
            this.addAttribute(list.getName(i), list.getType(i), list.getValue(i));
        }
    }
    
    public void addAttribute(final String s, final String s2, final String s3) {
        this.names.addElement(s);
        this.types.addElement(s2);
        this.values.addElement(s3);
    }
    
    public void removeAttribute(final String s) {
        final int index = this.names.indexOf(s);
        if (index >= 0) {
            this.names.removeElementAt(index);
            this.types.removeElementAt(index);
            this.values.removeElementAt(index);
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
    
    public String getName(final int n) {
        if (n < 0) {
            return null;
        }
        try {
            return this.names.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public String getType(final int n) {
        if (n < 0) {
            return null;
        }
        try {
            return this.types.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public String getValue(final int n) {
        if (n < 0) {
            return null;
        }
        try {
            return this.values.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public String getType(final String s) {
        return this.getType(this.names.indexOf(s));
    }
    
    public String getValue(final String s) {
        return this.getValue(this.names.indexOf(s));
    }
}
