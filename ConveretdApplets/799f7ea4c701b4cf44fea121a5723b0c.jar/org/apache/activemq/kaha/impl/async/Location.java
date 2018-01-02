// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;
import java.util.concurrent.CountDownLatch;

public final class Location implements Comparable<Location>
{
    public static final byte MARK_TYPE = -1;
    public static final byte USER_TYPE = 1;
    public static final byte NOT_SET_TYPE = 0;
    public static final int NOT_SET = -1;
    private int dataFileId;
    private int offset;
    private int size;
    private byte type;
    private CountDownLatch latch;
    
    public Location() {
        this.dataFileId = -1;
        this.offset = -1;
        this.size = -1;
        this.type = 0;
    }
    
    Location(final Location item) {
        this.dataFileId = -1;
        this.offset = -1;
        this.size = -1;
        this.type = 0;
        this.dataFileId = item.dataFileId;
        this.offset = item.offset;
        this.size = item.size;
        this.type = item.type;
    }
    
    boolean isValid() {
        return this.dataFileId != -1;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setSize(final int size) {
        this.size = size;
    }
    
    public int getPaylodSize() {
        return this.size - 32;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    public int getDataFileId() {
        return this.dataFileId;
    }
    
    public void setDataFileId(final int file) {
        this.dataFileId = file;
    }
    
    public byte getType() {
        return this.type;
    }
    
    public void setType(final byte type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        final String result = "offset = " + this.offset + ", file = " + this.dataFileId + ", size = " + this.size + ", type = " + this.type;
        return result;
    }
    
    public void writeExternal(final DataOutput dos) throws IOException {
        dos.writeInt(this.dataFileId);
        dos.writeInt(this.offset);
        dos.writeInt(this.size);
        dos.writeByte(this.type);
    }
    
    public void readExternal(final DataInput dis) throws IOException {
        this.dataFileId = dis.readInt();
        this.offset = dis.readInt();
        this.size = dis.readInt();
        this.type = dis.readByte();
    }
    
    public CountDownLatch getLatch() {
        return this.latch;
    }
    
    public void setLatch(final CountDownLatch latch) {
        this.latch = latch;
    }
    
    @Override
    public int compareTo(final Location o) {
        if (this.dataFileId == o.dataFileId) {
            final int rc = this.offset - o.offset;
            return rc;
        }
        return this.dataFileId - o.dataFileId;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean result = false;
        if (o instanceof Location) {
            result = (this.compareTo((Location)o) == 0);
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return this.dataFileId ^ this.offset;
    }
}
