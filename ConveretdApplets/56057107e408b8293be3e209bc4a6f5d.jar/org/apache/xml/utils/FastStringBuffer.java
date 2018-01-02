// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class FastStringBuffer
{
    public int m_blocksize;
    public char[] m_map;
    public int m_firstFree;
    public int m_mapSize;
    
    public FastStringBuffer() {
        this.m_firstFree = 0;
        this.m_blocksize = 1024;
        this.m_mapSize = 1024;
        this.m_map = new char[1024];
    }
    
    public FastStringBuffer(final int blocksize) {
        this.m_firstFree = 0;
        this.m_blocksize = blocksize;
        this.m_mapSize = blocksize;
        this.m_map = new char[blocksize];
    }
    
    public final void append(final char value) {
        this.ensureFreeSpace(1);
        this.m_map[this.m_firstFree] = value;
        ++this.m_firstFree;
    }
    
    public final void append(final String value) {
        final int len = value.length();
        this.ensureFreeSpace(len);
        value.getChars(0, len, this.m_map, this.m_firstFree);
        this.m_firstFree += len;
    }
    
    public final void append(final StringBuffer value) {
        final int len = value.length();
        this.ensureFreeSpace(len);
        value.getChars(0, len, this.m_map, this.m_firstFree);
        this.m_firstFree += len;
    }
    
    public final void append(final FastStringBuffer value) {
        final int length = value.m_firstFree;
        this.ensureFreeSpace(length);
        System.arraycopy(value.m_map, 0, this.m_map, this.m_firstFree, length);
        this.m_firstFree += length;
    }
    
    public final void append(final char[] chars, final int start, final int length) {
        this.ensureFreeSpace(length);
        System.arraycopy(chars, start, this.m_map, this.m_firstFree, length);
        this.m_firstFree += length;
    }
    
    private final void ensureFreeSpace(final int newSize) {
        if (this.m_firstFree + newSize >= this.m_mapSize) {
            this.m_mapSize += newSize + this.m_blocksize;
            final char[] newMap = new char[this.m_mapSize];
            System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
            this.m_map = newMap;
        }
    }
    
    public final int length() {
        return this.m_firstFree;
    }
    
    public final void reset() {
        this.m_firstFree = 0;
    }
    
    public final void setLength(final int l) {
        this.m_firstFree = l;
    }
    
    public final int size() {
        return this.m_firstFree;
    }
    
    public final String toString() {
        return new String(this.m_map, 0, this.m_firstFree);
    }
}
