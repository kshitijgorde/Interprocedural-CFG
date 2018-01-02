// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSAttributeUse extends XSObject
{
    boolean getIsRequired();
    
    XSAttributeDeclaration getAttrDeclaration();
    
    short getConstraintType();
    
    String getConstraintValue();
}
