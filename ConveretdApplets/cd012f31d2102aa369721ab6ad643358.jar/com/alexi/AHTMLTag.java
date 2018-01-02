// 
// Decompiled by Procyon v0.5.30
// 

package com.alexi;

import java.util.Vector;

public class AHTMLTag
{
    public String tagName;
    public int noAttributes;
    public Vector attributeNamesVector;
    public Vector attributeValuesVector;
    
    public AHTMLTag() {
        this.tagName = null;
        this.noAttributes = 0;
        this.attributeNamesVector = null;
        this.attributeValuesVector = null;
        this.attributeNamesVector = new Vector();
        this.attributeValuesVector = new Vector();
    }
    
    public void addAttribute(final String s, String s2) {
        if (s2.charAt(0) == '\"') {
            s2 = s2.substring(1);
        }
        if (s2.charAt(s2.length() - 1) == '\"') {
            s2 = s2.substring(0, s2.length() - 1);
        }
        this.attributeNamesVector.addElement(s);
        this.attributeValuesVector.addElement(s2);
        ++this.noAttributes;
    }
    
    public String getAttributeName(final int n) {
        return this.attributeNamesVector.elementAt(n);
    }
    
    public String getAttributeValue(final int n) {
        return this.attributeValuesVector.elementAt(n);
    }
    
    public int getNoOfAttributes() {
        return this.noAttributes;
    }
    
    public boolean is(final String s) {
        return this.tagName.equalsIgnoreCase(s);
    }
    
    public void setName(final String tagName) {
        this.tagName = tagName;
    }
}
