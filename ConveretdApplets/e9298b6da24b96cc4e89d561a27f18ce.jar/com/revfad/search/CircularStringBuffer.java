// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

public class CircularStringBuffer
{
    private int insertionIndex;
    private int size;
    private int bufferSize;
    private char[] contents;
    
    public CircularStringBuffer(final int bufferSize) {
        if (bufferSize < 0) {
            throw new IllegalArgumentException("bufferSize < 0");
        }
        this.bufferSize = bufferSize;
        this.contents = new char[bufferSize];
        this.size = 0;
        this.insertionIndex = 0;
    }
    
    public void reset() {
        this.size = 0;
        this.insertionIndex = 0;
    }
    
    public void append(final char c) {
        if (this.bufferSize != 0) {
            this.contents[this.insertionIndex] = c;
            if (this.size < this.bufferSize) {
                ++this.size;
            }
            ++this.insertionIndex;
            this.insertionIndex %= this.bufferSize;
        }
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public String toString() {
        if (this.bufferSize == 0) {
            return "";
        }
        if (this.size < this.bufferSize || this.insertionIndex == 0) {
            return new String(this.contents, 0, this.size);
        }
        final StringBuffer sb = new StringBuffer(this.bufferSize);
        sb.append(this.contents, this.insertionIndex, this.bufferSize - this.insertionIndex);
        sb.append(this.contents, 0, this.insertionIndex);
        return sb.toString();
    }
}
