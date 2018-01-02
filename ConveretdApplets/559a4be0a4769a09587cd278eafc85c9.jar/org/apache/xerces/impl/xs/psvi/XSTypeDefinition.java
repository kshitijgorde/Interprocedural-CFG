// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSTypeDefinition extends XSObject
{
    public static final short COMPLEX_TYPE = 13;
    public static final short SIMPLE_TYPE = 14;
    
    short getTypeCategory();
    
    XSTypeDefinition getBaseType();
    
    boolean getIsFinal(final short p0);
    
    short getFinal();
    
    boolean getIsAnonymous();
}
