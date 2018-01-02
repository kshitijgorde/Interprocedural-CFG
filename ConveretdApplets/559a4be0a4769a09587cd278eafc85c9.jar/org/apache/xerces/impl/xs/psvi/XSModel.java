// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSModel
{
    StringList getNamespaces();
    
    ObjectList getNamespaceItems();
    
    XSNamedMap getComponents(final short p0);
    
    XSNamedMap getComponentsByNamespace(final short p0, final String p1);
    
    XSTypeDefinition getTypeDefinition(final String p0, final String p1);
    
    XSAttributeDeclaration getAttributeDecl(final String p0, final String p1);
    
    XSElementDeclaration getElementDecl(final String p0, final String p1);
    
    XSAttributeGroupDefinition getAttributeGroup(final String p0, final String p1);
    
    XSModelGroupDefinition getModelGroupDefinition(final String p0, final String p1);
    
    XSNotationDeclaration getNotationDecl(final String p0, final String p1);
    
    XSObjectList getAnnotations();
}
