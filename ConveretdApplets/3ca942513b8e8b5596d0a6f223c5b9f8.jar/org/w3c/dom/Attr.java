// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom;

public interface Attr extends Node
{
    String getName();
    
    boolean getSpecified();
    
    String getValue();
    
    void setValue(final String p0) throws DOMException;
    
    Element getOwnerElement();
    
    TypeInfo getSchemaTypeInfo();
    
    boolean isId();
}
