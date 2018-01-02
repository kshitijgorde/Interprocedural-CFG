// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.trigger;

import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;

public abstract class AbstractTrigger implements ITrigger
{
    protected XActionDocument document;
    
    @Override
    public void configure(final XActionDocument document) {
        this.document = document;
    }
    
    @Override
    public XActionDocument getDocument() {
        return this.document;
    }
    
    @Override
    public String toString() {
        return Xlate.get(this.document.getRoot(), "name", (String)null);
    }
}
