// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import java.util.Hashtable;

public class DTDGrammarBucket
{
    protected Hashtable fGrammars;
    protected DTDGrammar fActiveGrammar;
    protected boolean fIsStandalone;
    
    public DTDGrammarBucket() {
        this.fGrammars = new Hashtable();
    }
    
    public void putGrammar(final DTDGrammar grammar) {
        final XMLDTDDescription desc = (XMLDTDDescription)grammar.getGrammarDescription();
        this.fGrammars.put(desc, grammar);
    }
    
    public DTDGrammar getGrammar(final XMLGrammarDescription desc) {
        return this.fGrammars.get(desc);
    }
    
    public void clear() {
        this.fGrammars.clear();
        this.fActiveGrammar = null;
        this.fIsStandalone = false;
    }
    
    void setStandalone(final boolean standalone) {
        this.fIsStandalone = standalone;
    }
    
    boolean getStandalone() {
        return this.fIsStandalone;
    }
    
    void setActiveGrammar(final DTDGrammar grammar) {
        this.fActiveGrammar = grammar;
    }
    
    DTDGrammar getActiveGrammar() {
        return this.fActiveGrammar;
    }
}
