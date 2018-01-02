// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarPool;
import java.lang.ref.WeakReference;

final class WeakReferenceXMLSchema extends AbstractXMLSchema
{
    private WeakReference fGrammarPool;
    
    public WeakReferenceXMLSchema() {
        this.fGrammarPool = new WeakReference(null);
    }
    
    public synchronized XMLGrammarPool getGrammarPool() {
        XMLGrammarPool xmlGrammarPool = (XMLGrammarPool)this.fGrammarPool.get();
        if (xmlGrammarPool == null) {
            xmlGrammarPool = new SoftReferenceGrammarPool();
            this.fGrammarPool = new WeakReference(xmlGrammarPool);
        }
        return xmlGrammarPool;
    }
    
    public boolean isFullyComposed() {
        return false;
    }
}
