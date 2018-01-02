// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSElementDeclaration extends XSTerm
{
    XSTypeDefinition getTypeDefinition();
    
    short getScope();
    
    XSComplexTypeDefinition getEnclosingCTDefinition();
    
    short getConstraintType();
    
    String getConstraintValue();
    
    boolean getIsNillable();
    
    XSNamedMap getIdentityConstraints();
    
    XSElementDeclaration getSubstitutionGroupAffiliation();
    
    boolean getIsSubstitutionGroupExclusion(final short p0);
    
    short getSubstitutionGroupExclusions();
    
    boolean getIsDisallowedSubstition(final short p0);
    
    short getDisallowedSubstitutions();
    
    boolean getIsAbstract();
    
    XSAnnotation getAnnotation();
}
