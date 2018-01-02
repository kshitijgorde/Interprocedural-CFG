// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb.plist;

import org.apache.kahadb.util.ByteSequence;

public class PListEntry
{
    private final ByteSequence byteSequence;
    private final EntryLocation entry;
    
    PListEntry(final EntryLocation entry, final ByteSequence bs) {
        this.entry = entry;
        this.byteSequence = bs;
    }
    
    public ByteSequence getByteSequence() {
        return this.byteSequence;
    }
    
    public String getId() {
        return this.entry.getId();
    }
    
    EntryLocation getEntry() {
        return this.entry;
    }
    
    public PListEntry copy() {
        return new PListEntry(this.entry, this.byteSequence);
    }
    
    @Override
    public String toString() {
        return this.entry.getId() + "[pageId=" + this.entry.getPage().getPageId() + ",next=" + this.entry.getNext() + "]";
    }
}
