// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.utils.Debug;

public class ByteCollector
{
    byte[] contents;
    int size;
    int pos;
    
    public ByteCollector() {
        this.contents = new byte[100000];
        this.size = 0;
        this.pos = 0;
    }
    
    public ByteCollector(final int n) {
        if (n > 0) {
            this.contents = new byte[n];
        }
        else {
            this.contents = new byte[100000];
        }
        this.size = 0;
        this.pos = 0;
    }
    
    public ByteCollector(final byte[] array) {
        this();
        this.append(array);
    }
    
    public ByteCollector(final byte b) {
        this();
        this.append(b);
    }
    
    public ByteCollector append(final byte b) {
        this.ensureCapacity(this.size + 1);
        this.contents[this.size++] = b;
        return this;
    }
    
    public ByteCollector append(final byte[] array) {
        this.ensureCapacity(this.size + array.length);
        System.arraycopy(array, 0, this.contents, this.size, array.length);
        this.size += array.length;
        return this;
    }
    
    public ByteCollector append(final byte[] array, final int n) {
        return this.append(array, 0, n);
    }
    
    public ByteCollector append(final byte[] array, final int n, final int n2) {
        this.ensureCapacity(this.size + n2);
        System.arraycopy(array, n, this.contents, this.size, n2);
        this.size += n2;
        return this;
    }
    
    public ByteCollector append(final ByteCollector byteCollector) {
        return this.append(byteCollector.toByteArray());
    }
    
    public byte[] toByteArray() {
        final byte[] array = new byte[this.size];
        System.arraycopy(this.contents, 0, array, 0, this.size);
        return array;
    }
    
    public byte[] startToByteArray(final int n) {
        if (this.size < n) {
            final byte[] byteArray = this.toByteArray();
            this.clear();
            return byteArray;
        }
        final byte[] array = new byte[n];
        System.arraycopy(this.contents, 0, array, 0, n);
        System.arraycopy(this.contents, n, this.contents, 0, this.size - n);
        this.size -= n;
        return array;
    }
    
    public int getCurrentSize() {
        return this.size;
    }
    
    public boolean ensureCapacity(final int n) {
        if (this.contents.length < n) {
            int i;
            for (i = this.contents.length; i < n; i = i * 3 / 2 + 1) {}
            final byte[] contents = new byte[i];
            System.arraycopy(this.contents, 0, contents, 0, this.size);
            this.contents = contents;
            return true;
        }
        return false;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public int indexOf(final ByteCollector byteCollector) {
        return this.indexOf(byteCollector.toByteArray());
    }
    
    public int indexOf(final byte b) {
        return this.indexOf(new byte[] { b });
    }
    
    public int indexOf(final byte[] array) {
        int i = 0;
    Label_0002:
        while (i < this.size - array.length + 1) {
            for (int j = 0; j < array.length; ++j) {
                if (this.contents[i + j] != array[j]) {
                    ++i;
                    continue Label_0002;
                }
            }
            return i;
        }
        return -1;
    }
    
    public void clear() {
        this.size = 0;
    }
    
    public void clearAndShorten() {
        this.size = 0;
        this.contents = new byte[80];
    }
    
    public String toString() {
        return new String(this.toByteArray());
    }
    
    public int hashCode() {
        byte b = 0;
        for (int i = 0; i < this.size; ++i) {
            b += (byte)(this.contents[i] * this.contents[i]);
        }
        return b;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof ByteCollector)) {
            return false;
        }
        final ByteCollector byteCollector = (ByteCollector)o;
        if (this.size != byteCollector.size) {
            return false;
        }
        for (int i = 0; i < this.size; ++i) {
            if (this.contents[i] != byteCollector.contents[i]) {
                return false;
            }
        }
        return true;
    }
    
    public byte removeFirst() {
        final byte b = this.contents[0];
        if (this.size == 0) {
            throw new IllegalArgumentException("ByteCollector is empty");
        }
        if (this.size > 1) {
            System.arraycopy(this.contents, 1, this.contents, 0, --this.size);
        }
        else {
            this.size = 0;
        }
        return b;
    }
    
    public int read(final byte[] array, final int n, final int n2) {
        synchronized (this) {
            if (this.pos == this.size) {
                this.pos = 0;
                Debug.log(4, "reading from ByteCollector: reset !!!");
                return -1;
            }
            if (n2 + this.pos > this.size) {
                final int n3 = this.size - this.pos;
                Debug.log(4, "reading from ByteCollector: pos=" + this.pos + " size==" + this.size + " len=" + n3);
                System.arraycopy(this.contents, this.pos, array, n, n3);
                this.pos += n3;
                return n3;
            }
            Debug.log(4, "reading from ByteCollector: pos=" + this.pos + " size==" + this.size + " len=" + n2);
            System.arraycopy(this.contents, this.pos, array, n, n2);
            this.pos += n2;
            return n2;
        }
    }
}
