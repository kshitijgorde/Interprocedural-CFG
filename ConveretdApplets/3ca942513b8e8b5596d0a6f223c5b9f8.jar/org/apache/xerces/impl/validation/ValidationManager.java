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
    
    public final void addValidationState(final ValidationState validationState) {
        this.fVSs.addElement(validationState);
    }
    
    public final void setEntityState(final EntityState entityState) {
        for (int i = this.fVSs.size() - 1; i >= 0; --i) {
            ((ValidationState)this.fVSs.elementAt(i)).setEntityState(entityState);
        }
    }
    
    public final void setGrammarFound(final boolean fGrammarFound) {
        this.fGrammarFound = fGrammarFound;
    }
    
    public final boolean isGrammarFound() {
        return this.fGrammarFound;
    }
    
    public final void setCachedDTD(final boolean fCachedDTD) {
        this.fCachedDTD = fCachedDTD;
    }
    
    public final boolean isCachedDTD() {
        return this.fCachedDTD;
    }
    
    public final void reset() {
        this.fVSs.removeAllElements();
        this.fGrammarFound = false;
        this.fCachedDTD = false;
    }
}
