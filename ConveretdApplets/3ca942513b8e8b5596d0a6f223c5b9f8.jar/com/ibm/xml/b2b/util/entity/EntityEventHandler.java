// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

public interface EntityEventHandler
{
    boolean resolveExternalEntity(final EntityInputSource p0);
    
    boolean scanXMLDecl(final ParsedEntity p0);
    
    boolean scanTextDecl(final ParsedEntity p0);
    
    boolean scanDocument(final ParsedEntity p0);
    
    boolean scanContent(final int p0, final ParsedEntity p1);
    
    boolean scanAttValue(final int p0, final ParsedEntity p1);
    
    void character(final int p0, final int p1, final boolean p2);
    
    void attributeValueCharacter(final int p0, final int p1, final boolean p2);
    
    boolean recursiveReferenceInContent(final int p0, final String p1);
    
    boolean undeclaredEntityInContent(final String p0);
    
    boolean skippedEntityInContent(final int p0);
    
    boolean unparsedEntityInContent(final int p0);
    
    boolean externallyDeclaredEntityInContent(final String p0);
    
    boolean recursiveReferenceInAttValue(final int p0, final String p1);
    
    boolean undeclaredEntityInAttValue(final String p0);
    
    boolean externalEntityInAttValue(final int p0);
    
    boolean externallyDeclaredEntityInAttValue(final String p0);
    
    boolean skippedExternalSubsetEntity();
    
    boolean recursivePEReference(final int p0, final String p1);
    
    boolean undeclaredParameterEntity(final int p0);
    
    boolean recursiveReferenceInDefaultAttValue(final int p0, final String p1);
    
    boolean undeclaredEntityInDefaultAttValue(final int p0);
    
    boolean externalEntityInDefaultAttValue(final int p0);
    
    boolean externallyDeclaredEntityInDefaultAttValue(final String p0);
    
    boolean skippedParameterEntity(final int p0);
}
