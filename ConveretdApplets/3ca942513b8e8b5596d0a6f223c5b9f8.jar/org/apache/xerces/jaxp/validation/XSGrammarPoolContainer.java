// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;

public interface XSGrammarPoolContainer
{
    XMLGrammarPool getGrammarPool();
    
    boolean isFullyComposed();
}
