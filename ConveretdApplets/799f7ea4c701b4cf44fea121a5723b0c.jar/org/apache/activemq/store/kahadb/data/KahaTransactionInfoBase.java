// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.data;

import org.apache.activemq.protobuf.BaseMessage;

abstract class KahaTransactionInfoBase<T> extends BaseMessage<T>
{
    private KahaLocalTransactionId f_localTransacitonId;
    private KahaXATransactionId f_xaTransacitonId;
    private KahaLocation f_previousEntry;
    
    KahaTransactionInfoBase() {
        this.f_localTransacitonId = null;
        this.f_xaTransacitonId = null;
        this.f_previousEntry = null;
    }
    
    public boolean hasLocalTransacitonId() {
        return this.f_localTransacitonId != null;
    }
    
    public KahaLocalTransactionId getLocalTransacitonId() {
        if (this.f_localTransacitonId == null) {
            this.f_localTransacitonId = new KahaLocalTransactionId();
        }
        return this.f_localTransacitonId;
    }
    
    public T setLocalTransacitonId(final KahaLocalTransactionId localTransacitonId) {
        this.loadAndClear();
        this.f_localTransacitonId = localTransacitonId;
        return (T)this;
    }
    
    public void clearLocalTransacitonId() {
        this.loadAndClear();
        this.f_localTransacitonId = null;
    }
    
    public boolean hasXaTransacitonId() {
        return this.f_xaTransacitonId != null;
    }
    
    public KahaXATransactionId getXaTransacitonId() {
        if (this.f_xaTransacitonId == null) {
            this.f_xaTransacitonId = new KahaXATransactionId();
        }
        return this.f_xaTransacitonId;
    }
    
    public T setXaTransacitonId(final KahaXATransactionId xaTransacitonId) {
        this.loadAndClear();
        this.f_xaTransacitonId = xaTransacitonId;
        return (T)this;
    }
    
    public void clearXaTransacitonId() {
        this.loadAndClear();
        this.f_xaTransacitonId = null;
    }
    
    public boolean hasPreviousEntry() {
        return this.f_previousEntry != null;
    }
    
    public KahaLocation getPreviousEntry() {
        if (this.f_previousEntry == null) {
            this.f_previousEntry = new KahaLocation();
        }
        return this.f_previousEntry;
    }
    
    public T setPreviousEntry(final KahaLocation previousEntry) {
        this.loadAndClear();
        this.f_previousEntry = previousEntry;
        return (T)this;
    }
    
    public void clearPreviousEntry() {
        this.loadAndClear();
        this.f_previousEntry = null;
    }
}
