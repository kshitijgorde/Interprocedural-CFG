// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.hash;

import java.io.IOException;
import org.apache.activemq.util.LinkedNode;

class HashPageInfo extends LinkedNode
{
    private HashIndex hashIndex;
    private long id;
    private int size;
    private HashPage page;
    private boolean dirty;
    
    HashPageInfo(final HashIndex index) {
        this.hashIndex = index;
    }
    
    long getId() {
        return this.id;
    }
    
    void setId(final long id) {
        this.id = id;
    }
    
    int size() {
        return this.size;
    }
    
    boolean isEmpty() {
        return this.size <= 0;
    }
    
    void setSize(final int size) {
        this.size = size;
    }
    
    void addHashEntry(final int index, final HashEntry entry) throws IOException {
        this.page.addHashEntry(index, entry);
        this.size = this.page.size();
        this.dirty = true;
    }
    
    HashEntry getHashEntry(final int index) throws IOException {
        return this.page.getHashEntry(index);
    }
    
    HashEntry removeHashEntry(final int index) throws IOException {
        final HashEntry result = this.page.removeHashEntry(index);
        if (result != null) {
            this.size = this.page.size();
            this.dirty = true;
        }
        return result;
    }
    
    String dump() {
        return this.page.dump();
    }
    
    void begin() throws IOException {
        if (this.page == null) {
            this.page = this.hashIndex.lookupPage(this.id);
        }
    }
    
    void end() throws IOException {
        if (this.page != null && this.dirty) {
            this.hashIndex.writeFullPage(this.page);
        }
        this.page = null;
        this.dirty = false;
    }
    
    HashPage getPage() {
        return this.page;
    }
    
    void setPage(final HashPage page) {
        this.page = page;
    }
    
    @Override
    public String toString() {
        return "Page[" + this.id + "] size=" + this.size;
    }
}
