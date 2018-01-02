// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSAttributeUse extends XSObject
{
    boolean getRequired();
    
    XSAttributeDeclaration getAttrDeclaration();
    
    short getConstraintType();
    
    String getConstraintValue();
    
    Object getActualVC() throws XSException;
    
    short getActualVCType() throws XSException;
    
    ShortList getItemValueTypes() throws XSException;
}
