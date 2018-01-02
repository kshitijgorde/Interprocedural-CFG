// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSComplexTypeDefinition extends XSTypeDefinition
{
    public static final short CONTENTTYPE_EMPTY = 0;
    public static final short CONTENTTYPE_SIMPLE = 1;
    public static final short CONTENTTYPE_ELEMENT = 2;
    public static final short CONTENTTYPE_MIXED = 3;
    
    short getDerivationMethod();
    
    boolean getIsAbstract();
    
    XSObjectList getAttributeUses();
    
    XSWildcard getAttributeWildcard();
    
    short getContentType();
    
    XSSimpleTypeDefinition getSimpleType();
    
    XSParticle getParticle();
    
    boolean getIsProhibitedSubstitution(final short p0);
    
    short getProhibitedSubstitutions();
    
    XSObjectList getAnnotations();
}
