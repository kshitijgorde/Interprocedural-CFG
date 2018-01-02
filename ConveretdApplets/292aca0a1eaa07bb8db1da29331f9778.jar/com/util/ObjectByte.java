// 
// Decompiled by Procyon v0.5.30
// 

package com.util;

public class ObjectByte
{
    private byte[] data;
    private int offset;
    private int length;
    public int index;
    
    public byte[] getData() {
        return this.data;
    }
    
    public void setData(final byte[] data) {
        this.data = data;
    }
    
    public ObjectByte(final byte[] data) {
        this.index = 0;
        this.data = data;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
}
