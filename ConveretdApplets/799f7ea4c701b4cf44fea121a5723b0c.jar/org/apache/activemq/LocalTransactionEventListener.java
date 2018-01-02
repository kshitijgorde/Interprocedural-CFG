// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

public interface LocalTransactionEventListener
{
    void beginEvent();
    
    void commitEvent();
    
    void rollbackEvent();
}
