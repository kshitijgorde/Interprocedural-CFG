// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.util.XMLGrammarPoolImpl;

final class LockedGrammarPool extends XMLGrammarPoolImpl
{
    public LockedGrammarPool() {
    }
    
    public LockedGrammarPool(final int n) {
        super(n);
    }
    
    public void unlockPool() {
    }
    
    public void clear() {
        if (super.fPoolIsLocked) {
            return;
        }
        super.clear();
    }
    
    public Grammar removeGrammar(final XMLGrammarDescription xmlGrammarDescription) {
        if (super.fPoolIsLocked) {
            return this.getGrammar(xmlGrammarDescription);
        }
        return super.removeGrammar(xmlGrammarDescription);
    }
    
    int getGrammarCount() {
        return super.fGrammarCount;
    }
}
