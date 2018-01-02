// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.data;

import org.apache.activemq.protobuf.BaseMessage;

abstract class KahaLocalTransactionIdBase<T> extends BaseMessage<T>
{
    private String f_connectionId;
    private boolean b_connectionId;
    private long f_transacitonId;
    private boolean b_transacitonId;
    
    KahaLocalTransactionIdBase() {
        this.f_connectionId = null;
        this.f_transacitonId = 0L;
    }
    
    public boolean hasConnectionId() {
        return this.b_connectionId;
    }
    
    public String getConnectionId() {
        return this.f_connectionId;
    }
    
    public T setConnectionId(final String connectionId) {
        this.loadAndClear();
        this.b_connectionId = true;
        this.f_connectionId = connectionId;
        return (T)this;
    }
    
    public void clearConnectionId() {
        this.loadAndClear();
        this.b_connectionId = false;
        this.f_connectionId = null;
    }
    
    public boolean hasTransacitonId() {
        return this.b_transacitonId;
    }
    
    public long getTransacitonId() {
        return this.f_transacitonId;
    }
    
    public T setTransacitonId(final long transacitonId) {
        this.loadAndClear();
        this.b_transacitonId = true;
        this.f_transacitonId = transacitonId;
        return (T)this;
    }
    
    public void clearTransacitonId() {
        this.loadAndClear();
        this.b_transacitonId = false;
        this.f_transacitonId = 0L;
    }
}
