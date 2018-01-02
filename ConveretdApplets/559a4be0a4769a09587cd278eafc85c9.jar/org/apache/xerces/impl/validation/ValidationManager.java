// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.validation;

import java.util.Vector;

public class ValidationManager
{
    protected final Vector fVSs;
    protected boolean fGrammarFound;
    protected boolean fCachedDTD;
    
    public ValidationManager() {
        this.fVSs = new Vector();
        this.fGrammarFound = false;
        this.fCachedDTD = false;
    }
    
    public void addValidationState(final ValidationState vs) {
        this.fVSs.addElement(vs);
    }
    
    public void setEntityState(final EntityState state) {
        for (int i = this.fVSs.size() - 1; i >= 0; --i) {
            this.fVSs.elementAt(i).setEntityState(state);
        }
    }
    
    public void setGrammarFound(final boolean grammar) {
        this.fGrammarFound = grammar;
    }
    
    public boolean isGrammarFound() {
        return this.fGrammarFound;
    }
    
    public void setCachedDTD(final boolean cachedDTD) {
        this.fCachedDTD = cachedDTD;
    }
    
    public boolean isCachedDTD() {
        return this.fCachedDTD;
    }
    
    public void reset() {
        this.fVSs.removeAllElements();
        this.fGrammarFound = false;
        this.fCachedDTD = false;
    }
}
