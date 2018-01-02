// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

class AttributeDecl
{
    String name;
    String type;
    String[] values;
    String defaultValue;
    boolean isRequired;
    boolean isFixed;
    boolean isFromInternalSubset;
    static final String CDATA = "CDATA";
    static final String ID = "ID";
    static final String IDREF = "IDREF";
    static final String IDREFS = "IDREFS";
    static final String ENTITY = "ENTITY";
    static final String ENTITIES = "ENTITIES";
    static final String NMTOKEN = "NMTOKEN";
    static final String NMTOKENS = "NMTOKENS";
    static final String NOTATION = "NOTATION";
    static final String ENUMERATION = "ENUMERATION";
    String valueDefault;
    static final String IMPLIED = "#IMPLIED";
    static final String REQUIRED = "#REQUIRED";
    static final String FIXED = "#FIXED";
    
    AttributeDecl(final String s) {
        this.name = s;
    }
}
