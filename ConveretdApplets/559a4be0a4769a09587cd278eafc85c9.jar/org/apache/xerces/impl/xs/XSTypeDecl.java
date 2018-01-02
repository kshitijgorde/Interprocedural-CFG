// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;

public interface XSTypeDecl extends XSTypeDefinition
{
    boolean derivedFrom(final XSTypeDefinition p0);
    
    boolean derivedFrom(final String p0, final String p1);
}
