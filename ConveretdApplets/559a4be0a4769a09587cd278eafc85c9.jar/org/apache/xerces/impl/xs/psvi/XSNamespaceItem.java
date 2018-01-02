// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSNamespaceItem
{
    String getSchemaNamespace();
    
    XSNamedMap getComponents(final short p0);
    
    XSTypeDefinition getTypeDefinition(final String p0);
    
    XSAttributeDeclaration getAttributeDecl(final String p0);
    
    XSElementDeclaration getElementDecl(final String p0);
    
    XSAttributeGroupDefinition getAttributeGroup(final String p0);
    
    XSModelGroupDefinition getModelGroupDefinition(final String p0);
    
    XSNotationDeclaration getNotationDecl(final String p0);
    
    ObjectList getDocuments();
    
    StringList getDocumentLocations();
}
