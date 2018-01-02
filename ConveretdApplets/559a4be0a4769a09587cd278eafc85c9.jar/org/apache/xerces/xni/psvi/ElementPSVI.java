// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.psvi;

import org.apache.xerces.impl.xs.psvi.XSModel;
import org.apache.xerces.impl.xs.psvi.XSNotationDeclaration;
import org.apache.xerces.impl.xs.psvi.XSElementDeclaration;

public interface ElementPSVI extends ItemPSVI
{
    XSElementDeclaration getElementDeclaration();
    
    XSNotationDeclaration getNotation();
    
    XSModel getSchemaInformation();
}
