// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.parser;

class ElementDecl
{
    String name;
    String id;
    String contentType;
    ElementValidator validator;
    ContentModel model;
    boolean ignoreWhitespace;
    boolean isFromInternalSubset;
    SimpleHashtable attributes;
    
    ElementDecl(final String s) {
        this.attributes = new SimpleHashtable();
        this.name = s;
    }
}
