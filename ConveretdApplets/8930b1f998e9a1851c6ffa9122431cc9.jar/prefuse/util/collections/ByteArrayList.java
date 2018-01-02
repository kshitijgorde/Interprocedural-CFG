// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayList
{
    private byte[] m_bytes;
    private int m_size;
    
    public ByteArrayList() {
        this(4096);
    }
    
    public ByteArrayList(final int n) {
        this.m_bytes = new byte[n];
        this.m_size = 0;
    }
    
    private void rangeCheck(final int n) {
        if (n < 0 || n >= this.m_size) {
            throw new IndexOutOfBoundsException("Index: " + n + " Size: " + this.m_size);
        }
    }
    
    private void ensureCapacity(final int n) {
        if (this.m_bytes.length < n) {
            final byte[] bytes = new byte[Math.max(3 * this.m_bytes.length / 2 + 1, n)];
            System.arraycopy(this.m_bytes, 0, bytes, 0, this.m_size);
            this.m_bytes = bytes;
        }
    }
    
    public byte get(final int n) {
        this.rangeCheck(n);
        return this.m_bytes[n];
    }
    
    public void set(final int n, final byte b) {
        this.rangeCheck(n);
        this.m_bytes[n] = b;
    }
    
    public int size() {
        return this.m_size;
    }
    
    public void add(final byte b) {
        this.ensureCapacity(this.m_size + 1);
        this.m_bytes[this.m_size++] = b;
    }
    
    public void add(final byte[] array, final int n, final int n2) {
        this.ensureCapacity(this.m_size + n2);
        System.arraycopy(array, n, this.m_bytes, this.m_size, n2);
        this.m_size += n2;
    }
    
    public InputStream getAsInputStream() {
        return new ByteArrayInputStream(this.m_bytes, 0, this.m_size);
    }
    
    public byte[] toArray() {
        final byte[] array = new byte[this.m_size];
        System.arraycopy(this.m_bytes, 0, array, 0, this.m_size);
        return array;
    }
}
