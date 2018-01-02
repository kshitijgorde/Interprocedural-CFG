// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.nio.charset.Charset;
import com.kenai.jaffl.Address;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;

public class BoundedMemoryIO extends AbstractMemoryIO implements DelegatingMemoryIO
{
    private final long base;
    private final long size;
    private final MemoryIO io;
    
    public BoundedMemoryIO(final MemoryIO parent, final long offset, final long size) {
        this.io = parent;
        this.base = offset;
        this.size = size;
    }
    
    public boolean isDirect() {
        return this.getDelegatedMemoryIO().isDirect();
    }
    
    public long address() {
        return this.getDelegatedMemoryIO().address();
    }
    
    public MemoryIO getDelegatedMemoryIO() {
        return this.io;
    }
    
    public byte getByte(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, 1L);
        return this.io.getByte(this.base + offset);
    }
    
    public short getShort(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, 2L);
        return this.io.getShort(this.base + offset);
    }
    
    public int getInt(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, 4L);
        return this.io.getInt(this.base + offset);
    }
    
    public long getLong(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, 8L);
        return this.io.getLong(this.base + offset);
    }
    
    public float getFloat(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, 4L);
        return this.io.getFloat(this.base + offset);
    }
    
    public double getDouble(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, 8L);
        return this.io.getDouble(this.base + offset);
    }
    
    public Pointer getPointer(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, Address.SIZE / 8);
        return this.io.getPointer(this.base + offset);
    }
    
    public MemoryIO getMemoryIO(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, this.base + offset, Address.SIZE / 8);
        return this.io.getMemoryIO(this.base + offset);
    }
    
    public MemoryIO getMemoryIO(final long offset, final long size) {
        AbstractMemoryIO.checkBounds(this.size, this.base + offset, Address.SIZE / 8);
        return this.io.getMemoryIO(this.base + offset, size);
    }
    
    public void putByte(final long offset, final byte value) {
        AbstractMemoryIO.checkBounds(this.size, offset, 1L);
        this.io.putByte(this.base + offset, value);
    }
    
    public void putShort(final long offset, final short value) {
        AbstractMemoryIO.checkBounds(this.size, offset, 2L);
        this.io.putShort(this.base + offset, value);
    }
    
    public void putInt(final long offset, final int value) {
        AbstractMemoryIO.checkBounds(this.size, offset, 4L);
        this.io.putInt(this.base + offset, value);
    }
    
    public void putLong(final long offset, final long value) {
        AbstractMemoryIO.checkBounds(this.size, offset, 8L);
        this.io.putLong(this.base + offset, value);
    }
    
    public void putFloat(final long offset, final float value) {
        AbstractMemoryIO.checkBounds(this.size, offset, 4L);
        this.io.putFloat(this.base + offset, value);
    }
    
    public void putDouble(final long offset, final double value) {
        AbstractMemoryIO.checkBounds(this.size, offset, 8L);
        this.io.putDouble(this.base + offset, value);
    }
    
    public void putPointer(final long offset, final Pointer value) {
        AbstractMemoryIO.checkBounds(this.size, offset, Address.SIZE / 8);
        this.io.putPointer(this.base + offset, value);
    }
    
    public void get(final long offset, final byte[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len);
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final byte[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len);
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final short[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 16 / 8);
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final short[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 16 / 8);
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final int[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 32 / 8);
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final int[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 32 / 8);
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final long[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 64 / 8);
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final long[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 64 / 8);
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final float[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 32 / 8);
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final float[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 32 / 8);
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final double[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 64 / 8);
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final double[] dst, final int off, final int len) {
        AbstractMemoryIO.checkBounds(this.size, offset, len * 64 / 8);
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public long getAddress(final long offset) {
        AbstractMemoryIO.checkBounds(this.size, offset, Address.SIZE >> 3);
        return this.io.getAddress(this.base + offset);
    }
    
    public String getString(final long offset, final int maxLength, final Charset cs) {
        AbstractMemoryIO.checkBounds(this.size, offset, maxLength);
        return this.io.getString(this.base + offset, maxLength, cs);
    }
    
    public String getString(final long offset) {
        return this.io.getString(this.base + offset, (int)this.size, Charset.defaultCharset());
    }
    
    public void putAddress(final long offset, final long value) {
        AbstractMemoryIO.checkBounds(this.size, offset, Address.SIZE >> 3);
        this.io.putAddress(this.base + offset, value);
    }
    
    public void putAddress(final long offset, final Address value) {
        AbstractMemoryIO.checkBounds(this.size, offset, Address.SIZE >> 3);
        this.io.putAddress(this.base + offset, value);
    }
    
    public void putString(final long offset, final String string, final int maxLength, final Charset cs) {
        AbstractMemoryIO.checkBounds(this.size, offset, maxLength);
        this.io.putString(this.base + offset, string, maxLength, cs);
    }
    
    public int indexOf(final long offset, final byte value) {
        return this.io.indexOf(this.base + offset, value, (int)this.size);
    }
    
    public int indexOf(final long offset, final byte value, final int maxlen) {
        AbstractMemoryIO.checkBounds(this.size, offset, maxlen);
        return this.io.indexOf(this.base + offset, value, maxlen);
    }
    
    public void setMemory(final long offset, final long size, final byte value) {
        AbstractMemoryIO.checkBounds(this.size, this.base + offset, size);
        this.io.setMemory(this.base + offset, size, value);
    }
}
