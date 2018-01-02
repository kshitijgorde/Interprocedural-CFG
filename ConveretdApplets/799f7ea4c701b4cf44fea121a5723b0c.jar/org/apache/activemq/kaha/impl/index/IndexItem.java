// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.kaha.impl.data.DataItem;
import org.apache.activemq.kaha.StoreLocation;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.data.Item;

public class IndexItem implements Item, StoreEntry
{
    public static final int INDEX_SIZE = 51;
    public static final int INDEXES_ONLY_SIZE = 19;
    protected long offset;
    IndexItem next;
    IndexItem prev;
    private long previousItem;
    private long nextItem;
    private boolean active;
    private long keyOffset;
    private int keyFile;
    private int keySize;
    private long valueOffset;
    private int valueFile;
    private int valueSize;
    
    public IndexItem() {
        this.offset = -1L;
        this.previousItem = -1L;
        this.nextItem = -1L;
        this.active = true;
        this.keyOffset = -1L;
        this.keyFile = -1;
        this.valueOffset = -1L;
        this.valueFile = -1;
    }
    
    void reset() {
        this.previousItem = -1L;
        this.nextItem = -1L;
        this.keyOffset = -1L;
        this.keyFile = -1;
        this.keySize = 0;
        this.valueOffset = -1L;
        this.valueFile = -1;
        this.valueSize = 0;
        this.active = true;
    }
    
    @Override
    public StoreLocation getKeyDataItem() {
        final DataItem result = new DataItem();
        result.setOffset(this.keyOffset);
        result.setFile(this.keyFile);
        result.setSize(this.keySize);
        return result;
    }
    
    @Override
    public StoreLocation getValueDataItem() {
        final DataItem result = new DataItem();
        result.setOffset(this.valueOffset);
        result.setFile(this.valueFile);
        result.setSize(this.valueSize);
        return result;
    }
    
    public void setValueData(final StoreLocation item) {
        this.valueOffset = item.getOffset();
        this.valueFile = item.getFile();
        this.valueSize = item.getSize();
    }
    
    public void setKeyData(final StoreLocation item) {
        this.keyOffset = item.getOffset();
        this.keyFile = item.getFile();
        this.keySize = item.getSize();
    }
    
    public void write(final DataOutput dataOut) throws IOException {
        dataOut.writeShort(31317);
        dataOut.writeBoolean(this.active);
        dataOut.writeLong(this.previousItem);
        dataOut.writeLong(this.nextItem);
        dataOut.writeInt(this.keyFile);
        dataOut.writeLong(this.keyOffset);
        dataOut.writeInt(this.keySize);
        dataOut.writeInt(this.valueFile);
        dataOut.writeLong(this.valueOffset);
        dataOut.writeInt(this.valueSize);
    }
    
    void updateIndexes(final DataOutput dataOut) throws IOException {
        dataOut.writeShort(31317);
        dataOut.writeBoolean(this.active);
        dataOut.writeLong(this.previousItem);
        dataOut.writeLong(this.nextItem);
    }
    
    public void read(final DataInput dataIn) throws IOException {
        if (dataIn.readShort() != 31317) {
            throw new BadMagicException();
        }
        this.active = dataIn.readBoolean();
        this.previousItem = dataIn.readLong();
        this.nextItem = dataIn.readLong();
        this.keyFile = dataIn.readInt();
        this.keyOffset = dataIn.readLong();
        this.keySize = dataIn.readInt();
        this.valueFile = dataIn.readInt();
        this.valueOffset = dataIn.readLong();
        this.valueSize = dataIn.readInt();
    }
    
    void readIndexes(final DataInput dataIn) throws IOException {
        if (dataIn.readShort() != 31317) {
            throw new BadMagicException();
        }
        this.active = dataIn.readBoolean();
        this.previousItem = dataIn.readLong();
        this.nextItem = dataIn.readLong();
    }
    
    public void setPreviousItem(final long newPrevEntry) {
        this.previousItem = newPrevEntry;
    }
    
    long getPreviousItem() {
        return this.previousItem;
    }
    
    public void setNextItem(final long newNextEntry) {
        this.nextItem = newNextEntry;
    }
    
    @Override
    public long getNextItem() {
        return this.nextItem;
    }
    
    void setKeyOffset(final long newObjectOffset) {
        this.keyOffset = newObjectOffset;
    }
    
    long getKeyOffset() {
        return this.keyOffset;
    }
    
    @Override
    public int getKeyFile() {
        return this.keyFile;
    }
    
    void setKeyFile(final int keyFile) {
        this.keyFile = keyFile;
    }
    
    @Override
    public int getValueFile() {
        return this.valueFile;
    }
    
    void setValueFile(final int valueFile) {
        this.valueFile = valueFile;
    }
    
    @Override
    public long getValueOffset() {
        return this.valueOffset;
    }
    
    public void setValueOffset(final long valueOffset) {
        this.valueOffset = valueOffset;
    }
    
    boolean isActive() {
        return this.active;
    }
    
    void setActive(final boolean active) {
        this.active = active;
    }
    
    @Override
    public long getOffset() {
        return this.offset;
    }
    
    public void setOffset(final long offset) {
        this.offset = offset;
    }
    
    @Override
    public int getKeySize() {
        return this.keySize;
    }
    
    public void setKeySize(final int keySize) {
        this.keySize = keySize;
    }
    
    @Override
    public int getValueSize() {
        return this.valueSize;
    }
    
    public void setValueSize(final int valueSize) {
        this.valueSize = valueSize;
    }
    
    void copyIndex(final IndexItem other) {
        this.offset = other.offset;
        this.active = other.active;
        this.previousItem = other.previousItem;
        this.nextItem = other.nextItem;
    }
    
    @Override
    public String toString() {
        final String result = "offset=" + this.offset + ", key=(" + this.keyFile + ", " + this.keyOffset + ", " + this.keySize + ")" + ", value=(" + this.valueFile + ", " + this.valueOffset + ", " + this.valueSize + ")" + ", previousItem=" + this.previousItem + ", nextItem=" + this.nextItem;
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        boolean result = obj == this;
        if (!result && obj != null && obj instanceof IndexItem) {
            final IndexItem other = (IndexItem)obj;
            result = (other.offset == this.offset);
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return (int)this.offset;
    }
}
