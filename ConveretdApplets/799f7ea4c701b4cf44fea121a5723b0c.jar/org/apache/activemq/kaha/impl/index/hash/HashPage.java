// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.hash;

import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.io.DataInput;
import java.io.IOException;
import java.util.Iterator;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

class HashPage
{
    static final int PAGE_HEADER_SIZE = 17;
    private static final transient Logger LOG;
    private int maximumEntries;
    private long id;
    private int binId;
    private List<HashEntry> hashIndexEntries;
    private int persistedSize;
    private long nextFreePageId;
    private boolean active;
    
    public HashPage(final int maximumEntries) {
        this.nextFreePageId = -1L;
        this.active = true;
        this.maximumEntries = maximumEntries;
        this.hashIndexEntries = new ArrayList<HashEntry>(maximumEntries);
    }
    
    @Override
    public String toString() {
        return "HashPage[" + this.getId() + ":" + this.binId + ":" + this.id + "] size = " + this.persistedSize;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean result = false;
        if (o instanceof HashPage) {
            final HashPage other = (HashPage)o;
            result = (other.id == this.id);
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return (int)this.id;
    }
    
    boolean isActive() {
        return this.active;
    }
    
    void setActive(final boolean active) {
        this.active = active;
    }
    
    long getId() {
        return this.id;
    }
    
    void setId(final long id) {
        this.id = id;
    }
    
    int getPersistedSize() {
        return this.persistedSize;
    }
    
    void write(final Marshaller keyMarshaller, final DataOutput dataOut) throws IOException {
        this.persistedSize = this.hashIndexEntries.size();
        this.writeHeader(dataOut);
        dataOut.writeInt(this.persistedSize);
        for (final HashEntry entry : this.hashIndexEntries) {
            entry.write(keyMarshaller, dataOut);
        }
    }
    
    void read(final Marshaller keyMarshaller, final DataInput dataIn) throws IOException {
        this.readHeader(dataIn);
        dataIn.readInt();
        final int size = this.persistedSize;
        this.hashIndexEntries.clear();
        for (int i = 0; i < size; ++i) {
            final HashEntry entry = new HashEntry();
            entry.read(keyMarshaller, dataIn);
            this.hashIndexEntries.add(entry);
        }
    }
    
    void readHeader(final DataInput dataIn) throws IOException {
        this.active = dataIn.readBoolean();
        this.nextFreePageId = dataIn.readLong();
        this.binId = dataIn.readInt();
        this.persistedSize = dataIn.readInt();
    }
    
    void writeHeader(final DataOutput dataOut) throws IOException {
        dataOut.writeBoolean(this.isActive());
        dataOut.writeLong(this.nextFreePageId);
        dataOut.writeInt(this.binId);
        dataOut.writeInt(this.persistedSize = this.hashIndexEntries.size());
    }
    
    boolean isEmpty() {
        return this.hashIndexEntries.isEmpty();
    }
    
    boolean isFull() {
        return this.hashIndexEntries.size() >= this.maximumEntries;
    }
    
    boolean isUnderflowed() {
        return this.hashIndexEntries.size() < this.maximumEntries / 2;
    }
    
    boolean isOverflowed() {
        return this.hashIndexEntries.size() > this.maximumEntries;
    }
    
    List<HashEntry> getEntries() {
        return this.hashIndexEntries;
    }
    
    void setEntries(final List<HashEntry> newEntries) {
        this.hashIndexEntries = newEntries;
    }
    
    int getMaximumEntries() {
        return this.maximumEntries;
    }
    
    void setMaximumEntries(final int maximumEntries) {
        this.maximumEntries = maximumEntries;
    }
    
    int size() {
        return this.hashIndexEntries.size();
    }
    
    void reset() throws IOException {
        this.hashIndexEntries.clear();
        this.persistedSize = 0;
    }
    
    void addHashEntry(final int index, final HashEntry entry) throws IOException {
        this.hashIndexEntries.add(index, entry);
    }
    
    HashEntry getHashEntry(final int index) {
        final HashEntry result = this.hashIndexEntries.get(index);
        return result;
    }
    
    HashEntry removeHashEntry(final int index) throws IOException {
        final HashEntry result = this.hashIndexEntries.remove(index);
        return result;
    }
    
    void removeAllTreeEntries(final List<HashEntry> c) {
        this.hashIndexEntries.removeAll(c);
    }
    
    List<HashEntry> getSubList(final int from, final int to) {
        return new ArrayList<HashEntry>(this.hashIndexEntries.subList(from, to));
    }
    
    int getBinId() {
        return this.binId;
    }
    
    void setBinId(final int binId) {
        this.binId = binId;
    }
    
    String dump() {
        final StringBuffer str = new StringBuffer(32);
        str.append(this.toString());
        str.append(": ");
        for (final HashEntry entry : this.hashIndexEntries) {
            str.append(entry);
            str.append(",");
        }
        return str.toString();
    }
    
    static {
        LOG = LoggerFactory.getLogger(HashPage.class);
    }
}
