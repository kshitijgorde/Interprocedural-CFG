// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.hash;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;

class HashEntry implements Comparable
{
    static final int NOT_SET = -1;
    private Comparable key;
    private long indexOffset;
    
    @Override
    public int compareTo(final Object o) {
        if (o instanceof HashEntry) {
            final HashEntry other = (HashEntry)o;
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
        return "HashEntry(" + this.key + "," + this.indexOffset + ")";
    }
    
    HashEntry copy() {
        final HashEntry copy = new HashEntry();
        copy.key = this.key;
        copy.indexOffset = this.indexOffset;
        return copy;
    }
    
    Comparable getKey() {
        return this.key;
    }
    
    void setKey(final Comparable key) {
        this.key = key;
    }
    
    long getIndexOffset() {
        return this.indexOffset;
    }
    
    void setIndexOffset(final long indexOffset) {
        this.indexOffset = indexOffset;
    }
    
    void write(final Marshaller keyMarshaller, final DataOutput dataOut) throws IOException {
        dataOut.writeLong(this.indexOffset);
        keyMarshaller.writePayload(this.key, dataOut);
    }
    
    void read(final Marshaller keyMarshaller, final DataInput dataIn) throws IOException {
        this.indexOffset = dataIn.readLong();
        this.key = keyMarshaller.readPayload(dataIn);
    }
}
