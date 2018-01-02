// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.data;

import org.apache.activemq.kaha.StoreLocation;

public final class DataItem implements Item, StoreLocation
{
    private int file;
    private long offset;
    private int size;
    
    public DataItem() {
        this.file = -1;
        this.offset = -1L;
    }
    
    DataItem(final DataItem item) {
        this.file = -1;
        this.offset = -1L;
        this.file = item.file;
        this.offset = item.offset;
        this.size = item.size;
    }
    
    boolean isValid() {
        return this.file != -1L;
    }
    
    @Override
    public int getSize() {
        return this.size;
    }
    
    public void setSize(final int size) {
        this.size = size;
    }
    
    @Override
    public long getOffset() {
        return this.offset;
    }
    
    public void setOffset(final long offset) {
        this.offset = offset;
    }
    
    @Override
    public int getFile() {
        return this.file;
    }
    
    public void setFile(final int file) {
        this.file = file;
    }
    
    @Override
    public String toString() {
        final String result = "offset = " + this.offset + ", file = " + this.file + ", size = " + this.size;
        return result;
    }
    
    public DataItem copy() {
        return new DataItem(this);
    }
}
