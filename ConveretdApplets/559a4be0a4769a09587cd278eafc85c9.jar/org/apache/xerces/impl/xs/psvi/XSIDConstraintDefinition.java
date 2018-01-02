// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSIDConstraintDefinition extends XSObject
{
    public static final short IC_KEY = 1;
    public static final short IC_KEYREF = 2;
    public static final short IC_UNIQUE = 3;
    
    short getCategory();
    
    String getSelectorStr();
    
    StringList getFieldStrs();
    
    XSIDConstraintDefinition getRefKey();
    
    XSAnnotation getAnnotation();
}
