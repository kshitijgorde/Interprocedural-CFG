// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class DiskChannel
{
    String filename;
    byte[] data;
    boolean open;
    int pos;
    int chID;
    
    public DiskChannel(final int chID) {
        this.chID = chID;
    }
    
    public void setFilename(final String filename) {
        this.filename = filename;
    }
    
    public void setData(final byte[] data) {
        this.data = data;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public int readChar() {
        if (this.pos >= this.data.length) {
            return -1;
        }
        return this.data[this.pos++] & 0xFF;
    }
    
    public void open() {
        this.open = true;
        this.pos = 0;
    }
    
    public void close() {
        this.open = false;
    }
}
