// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.grammars;

import org.apache.xerces.xs.XSModel;

public interface XSGrammar extends Grammar
{
    XSModel toXSModel();
    
    XSModel toXSModel(final XSGrammar[] p0);
}
