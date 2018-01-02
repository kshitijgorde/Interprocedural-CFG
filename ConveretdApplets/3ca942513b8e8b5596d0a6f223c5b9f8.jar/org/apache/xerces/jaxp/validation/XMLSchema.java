// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;

final class XMLSchema extends AbstractXMLSchema
{
    private final XMLGrammarPool fGrammarPool;
    
    public XMLSchema(final XMLGrammarPool fGrammarPool) {
        this.fGrammarPool = fGrammarPool;
    }
    
    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }
    
    public boolean isFullyComposed() {
        return true;
    }
}
