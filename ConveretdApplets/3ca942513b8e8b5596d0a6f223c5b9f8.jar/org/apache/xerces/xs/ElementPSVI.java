// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface ElementPSVI extends ItemPSVI
{
    XSElementDeclaration getElementDeclaration();
    
    XSNotationDeclaration getNotation();
    
    boolean getNil();
    
    XSModel getSchemaInformation();
}
