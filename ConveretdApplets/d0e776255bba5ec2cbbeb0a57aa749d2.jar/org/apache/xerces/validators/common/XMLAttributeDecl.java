// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.validators.datatype.DatatypeValidator;
import org.apache.xerces.utils.QName;

public class XMLAttributeDecl
{
    public static final int TYPE_CDATA = 0;
    public static final int TYPE_ENTITY = 1;
    public static final int TYPE_ENUMERATION = 2;
    public static final int TYPE_ID = 3;
    public static final int TYPE_IDREF = 4;
    public static final int TYPE_NMTOKEN = 5;
    public static final int TYPE_NOTATION = 6;
    public static final int TYPE_SIMPLE = 7;
    public static final int TYPE_ANY_ANY = 8;
    public static final int TYPE_ANY_OTHER = 9;
    public static final int TYPE_ANY_LOCAL = 10;
    public static final int TYPE_ANY_LIST = 11;
    public static final int DEFAULT_TYPE_IMPLIED = 0;
    public static final int DEFAULT_TYPE_FIXED = 1;
    public static final int DEFAULT_TYPE_REQUIRED = 2;
    public static final int DEFAULT_TYPE_DEFAULT = 3;
    public static final int DEFAULT_TYPE_PROHIBITED = 7;
    public static final int DEFAULT_TYPE_REQUIRED_AND_FIXED = 8;
    public static final int PROCESSCONTENTS_STRICT = 4;
    public static final int PROCESSCONTENTS_LAX = 5;
    public static final int PROCESSCONTENTS_SKIP = 6;
    public QName name;
    public DatatypeValidator datatypeValidator;
    public int type;
    public boolean list;
    public int enumeration;
    public int defaultType;
    public String defaultValue;
    
    public XMLAttributeDecl() {
        this.name = new QName();
        this.clear();
    }
    
    public XMLAttributeDecl(final XMLAttributeDecl values) {
        this.name = new QName();
        this.setValues(values);
    }
    
    public void clear() {
        this.name.clear();
        this.datatypeValidator = null;
        this.type = -1;
        this.list = false;
        this.enumeration = -1;
        this.defaultType = 0;
        this.defaultValue = null;
    }
    
    public void setValues(final XMLAttributeDecl xmlAttributeDecl) {
        this.name.setValues(xmlAttributeDecl.name);
        this.datatypeValidator = xmlAttributeDecl.datatypeValidator;
        this.type = xmlAttributeDecl.type;
        this.list = xmlAttributeDecl.list;
        this.enumeration = xmlAttributeDecl.enumeration;
        this.defaultType = xmlAttributeDecl.defaultType;
        this.defaultValue = xmlAttributeDecl.defaultValue;
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    public boolean equals(final Object o) {
        return super.equals(o);
    }
}
