// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

public class ByteSequence
{
    public byte[] data;
    public int offset;
    public int length;
    
    public ByteSequence(final byte[] data) {
        this.data = data;
        this.offset = 0;
        this.length = data.length;
    }
    
    public ByteSequence(final byte[] data, final int offset, final int length) {
        this.data = data;
        this.offset = offset;
        this.length = length;
    }
    
    public byte[] getData() {
        return this.data;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    public void setData(final byte[] data) {
        this.data = data;
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
    
    public void compact() {
        if (this.length != this.data.length) {
            final byte[] t = new byte[this.length];
            System.arraycopy(this.data, this.offset, t, 0, this.length);
            this.data = t;
            this.offset = 0;
        }
    }
}
