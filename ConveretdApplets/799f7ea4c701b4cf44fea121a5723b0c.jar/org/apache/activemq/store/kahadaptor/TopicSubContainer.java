// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import java.util.Iterator;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.MapContainer;

public class TopicSubContainer
{
    private transient MapContainer mapContainer;
    private transient StoreEntry batchEntry;
    
    public TopicSubContainer(final MapContainer container) {
        this.mapContainer = container;
    }
    
    public StoreEntry getBatchEntry() {
        return this.batchEntry;
    }
    
    public void setBatchEntry(final String id, final StoreEntry batchEntry) {
        this.batchEntry = batchEntry;
    }
    
    public void reset() {
        this.batchEntry = null;
    }
    
    public boolean isEmpty() {
        return this.mapContainer.isEmpty();
    }
    
    public StoreEntry add(final ConsumerMessageRef ref) {
        return this.mapContainer.place(ref.getMessageId(), ref);
    }
    
    public ConsumerMessageRef remove(final MessageId id) {
        ConsumerMessageRef result = null;
        final StoreEntry entry = this.mapContainer.getEntry(id);
        if (entry != null) {
            result = this.mapContainer.getValue(entry);
            this.mapContainer.remove(entry);
            if (this.batchEntry != null && this.batchEntry.equals(entry)) {
                this.reset();
            }
        }
        if (this.mapContainer.isEmpty()) {
            this.reset();
        }
        return result;
    }
    
    public ConsumerMessageRef get(final StoreEntry entry) {
        return this.mapContainer.getValue(entry);
    }
    
    public StoreEntry getEntry() {
        return this.mapContainer.getFirst();
    }
    
    public StoreEntry refreshEntry(final StoreEntry entry) {
        return this.mapContainer.refresh(entry);
    }
    
    public StoreEntry getNextEntry(final StoreEntry entry) {
        return this.mapContainer.getNext(entry);
    }
    
    public Iterator iterator() {
        return this.mapContainer.values().iterator();
    }
    
    public int size() {
        return this.mapContainer.size();
    }
    
    public void clear() {
        this.reset();
        this.mapContainer.clear();
    }
}
