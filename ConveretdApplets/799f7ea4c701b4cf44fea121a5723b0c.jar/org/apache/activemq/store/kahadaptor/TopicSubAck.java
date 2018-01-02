// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.kaha.StoreEntry;

public class TopicSubAck
{
    private int count;
    private StoreEntry messageEntry;
    
    public int getCount() {
        return this.count;
    }
    
    public void setCount(final int count) {
        this.count = count;
    }
    
    public int decrementCount() {
        return --this.count;
    }
    
    public int incrementCount() {
        return ++this.count;
    }
    
    public StoreEntry getMessageEntry() {
        return this.messageEntry;
    }
    
    public void setMessageEntry(final StoreEntry storeEntry) {
        this.messageEntry = storeEntry;
    }
}
