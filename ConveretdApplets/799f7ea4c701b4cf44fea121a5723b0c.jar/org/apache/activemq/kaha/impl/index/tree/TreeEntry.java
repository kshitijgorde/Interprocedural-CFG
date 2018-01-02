// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.tree;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;

class TreeEntry implements Comparable
{
    static final int NOT_SET = -1;
    private Comparable key;
    private long indexOffset;
    private long prevPageId;
    private long nextPageId;
    
    TreeEntry() {
        this.prevPageId = -1L;
        this.nextPageId = -1L;
    }
    
    @Override
    public int compareTo(final Object o) {
        if (o instanceof TreeEntry) {
            final TreeEntry other = (TreeEntry)o;
            return this.key.compareTo(other.key);
        }
        return this.key.compareTo(o);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.compareTo(o) == 0;
    }
    
    @Override
    public int hashCode() {
        return this.key.hashCode();
    }
    
    @Override
    public String toString() {
        return "TreeEntry(" + this.key + "," + this.indexOffset + ")prev=" + this.prevPageId + ",next=" + this.nextPageId;
    }
    
    void reset() {
        this.prevPageId = -1L;
        this.nextPageId = -1L;
    }
    
    TreeEntry copy() {
        final TreeEntry copy = new TreeEntry();
        copy.key = this.key;
        copy.indexOffset = this.indexOffset;
        copy.prevPageId = this.prevPageId;
        copy.nextPageId = this.nextPageId;
        return copy;
    }
    
    Comparable getKey() {
        return this.key;
    }
    
    void setKey(final Comparable key) {
        this.key = key;
    }
    
    long getNextPageId() {
        return this.nextPageId;
    }
    
    void setNextPageId(final long nextPageId) {
        this.nextPageId = nextPageId;
    }
    
    long getPrevPageId() {
        return this.prevPageId;
    }
    
    void setPrevPageId(final long prevPageId) {
        this.prevPageId = prevPageId;
    }
    
    long getIndexOffset() {
        return this.indexOffset;
    }
    
    void setIndexOffset(final long indexOffset) {
        this.indexOffset = indexOffset;
    }
    
    boolean hasChildPagesReferences() {
        return this.prevPageId != -1L || this.nextPageId != -1L;
    }
    
    void write(final Marshaller keyMarshaller, final DataOutput dataOut) throws IOException {
        keyMarshaller.writePayload(this.key, dataOut);
        dataOut.writeLong(this.indexOffset);
        dataOut.writeLong(this.nextPageId);
        dataOut.writeLong(this.prevPageId);
    }
    
    void read(final Marshaller keyMarshaller, final DataInput dataIn) throws IOException {
        this.key = keyMarshaller.readPayload(dataIn);
        this.indexOffset = dataIn.readLong();
        this.nextPageId = dataIn.readLong();
        this.prevPageId = dataIn.readLong();
    }
}
