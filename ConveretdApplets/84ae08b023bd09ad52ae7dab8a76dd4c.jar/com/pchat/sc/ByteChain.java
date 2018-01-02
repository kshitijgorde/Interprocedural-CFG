// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

public class ByteChain
{
    public static final int MAX_LEN = 2000000000;
    private byte[] byteArray;
    private int arrayCapacity;
    private int arrayLength;
    private int maxLength;
    private boolean arrayFull;
    
    public ByteChain() {
        this.maxLength = 32000;
        this.arrayFull = false;
        this.arrayCapacity = 512;
        this.byteArray = new byte[this.arrayCapacity];
        this.arrayLength = 0;
    }
    
    public ByteChain(final byte[] byteArray) {
        this.maxLength = 32000;
        this.arrayFull = false;
        this.arrayCapacity = byteArray.length;
        this.byteArray = byteArray;
        this.arrayLength = this.arrayCapacity;
    }
    
    public ByteChain(final byte[] byteArray, final int maxLength) {
        this.maxLength = 32000;
        this.arrayFull = false;
        this.arrayCapacity = byteArray.length;
        this.byteArray = byteArray;
        this.arrayLength = this.arrayCapacity;
        this.maxLength = maxLength;
    }
    
    public int length() {
        return this.arrayLength;
    }
    
    public void add(final byte b) {
        if (this.isFull()) {
            return;
        }
        this.checkAllocation(1);
        if (this.isFull()) {
            return;
        }
        this.byteArray[this.arrayLength] = b;
        ++this.arrayLength;
    }
    
    public void add(final byte[] array) {
        if (this.isFull()) {
            return;
        }
        this.checkAllocation(array.length);
        if (this.isFull()) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.byteArray[this.arrayLength + i] = array[i];
        }
        this.arrayLength += array.length;
    }
    
    public void add(final byte[] array, final int n, final int n2) {
        if (this.isFull()) {
            return;
        }
        this.checkAllocation(n2);
        if (this.isFull()) {
            return;
        }
        for (int i = 0; i < n2; ++i) {
            this.byteArray[this.arrayLength + i] = array[n + i];
        }
        this.arrayLength += n2;
    }
    
    public boolean isFull() {
        return this.arrayFull;
    }
    
    public byte[] get() {
        if (this.arrayLength == this.arrayCapacity) {
            return this.byteArray;
        }
        final byte[] array = new byte[this.arrayLength];
        for (int i = 0; i < this.arrayLength; ++i) {
            array[i] = this.byteArray[i];
        }
        return array;
    }
    
    public byte[] getBytes(final int n) {
        final byte[] array = new byte[this.arrayLength - n];
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i] = this.byteArray[i + n];
        }
        return array;
    }
    
    public byte[] getBytes(final int n, final int n2) {
        final byte[] array = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = this.byteArray[i + n];
        }
        return array;
    }
    
    public byte get(final int n) {
        return this.byteArray[n];
    }
    
    public void shiftLeft(final int n) {
        if (n >= this.arrayLength) {
            this.arrayLength = 0;
            return;
        }
        for (int i = n; i < this.arrayLength; ++i) {
            this.byteArray[i - n] = this.byteArray[i];
        }
        this.arrayLength -= n;
    }
    
    private void checkAllocation(final int n) {
        if (n + this.arrayLength < this.arrayCapacity) {
            return;
        }
        final int n2 = n + this.arrayLength;
        if (n2 > this.maxLength) {
            this.arrayFull = true;
            return;
        }
        int maxLength = n2 + 512;
        if (maxLength > this.maxLength) {
            maxLength = this.maxLength;
        }
        this.arrayCapacity = maxLength;
        final byte[] byteArray = new byte[this.arrayCapacity];
        for (int i = 0; i < this.arrayLength; ++i) {
            byteArray[i] = this.byteArray[i];
        }
        this.byteArray = byteArray;
    }
}
