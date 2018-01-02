// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.store.ReferenceStore;

public class ReferenceRecord
{
    private String messageId;
    private ReferenceStore.ReferenceData data;
    
    public ReferenceRecord() {
    }
    
    public ReferenceRecord(final String messageId, final ReferenceStore.ReferenceData data) {
        this.messageId = messageId;
        this.data = data;
    }
    
    public ReferenceStore.ReferenceData getData() {
        return this.data;
    }
    
    public void setData(final ReferenceStore.ReferenceData data) {
        this.data = data;
    }
    
    public String getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }
    
    @Override
    public String toString() {
        return "ReferenceRecord(id=" + this.messageId + ",data=" + this.data + ")";
    }
}
