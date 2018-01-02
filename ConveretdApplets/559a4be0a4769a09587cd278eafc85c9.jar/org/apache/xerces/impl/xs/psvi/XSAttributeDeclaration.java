// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSAttributeDeclaration extends XSObject
{
    XSSimpleTypeDefinition getTypeDefinition();
    
    short getScope();
    
    XSComplexTypeDefinition getEnclosingCTDefinition();
    
    short getConstraintType();
    
    String getConstraintValue();
    
    XSAnnotation getAnnotation();
}
