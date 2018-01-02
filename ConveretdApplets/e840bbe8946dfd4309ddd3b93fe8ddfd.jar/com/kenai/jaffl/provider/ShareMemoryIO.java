// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.nio.charset.Charset;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;

public class ShareMemoryIO extends AbstractMemoryIO implements DelegatingMemoryIO
{
    private final MemoryIO io;
    private final long base;
    
    public ShareMemoryIO(final MemoryIO parent, final long offset) {
        this.io = parent;
        this.base = offset;
    }
    
    public final boolean isDirect() {
        return this.io.isDirect();
    }
    
    public long address() {
        return this.io.address() + this.base;
    }
    
    public final MemoryIO getDelegatedMemoryIO() {
        return this.io;
    }
    
    public byte getByte(final long offset) {
        return this.io.getByte(this.base + offset);
    }
    
    public short getShort(final long offset) {
        return this.io.getShort(this.base + offset);
    }
    
    public int getInt(final long offset) {
        return this.io.getInt(this.base + offset);
    }
    
    public long getLong(final long offset) {
        return this.io.getLong(this.base + offset);
    }
    
    public float getFloat(final long offset) {
        return this.io.getFloat(this.base + offset);
    }
    
    public double getDouble(final long offset) {
        return this.io.getDouble(this.base + offset);
    }
    
    public MemoryIO getMemoryIO(final long offset) {
        return this.io.getMemoryIO(this.base + offset);
    }
    
    public MemoryIO getMemoryIO(final long offset, final long size) {
        return this.io.getMemoryIO(this.base + offset, size);
    }
    
    public Pointer getPointer(final long offset) {
        return this.io.getPointer(this.base + offset);
    }
    
    public String getString(final long offset) {
        return this.io.getString(this.base + offset);
    }
    
    public String getString(final long offset, final int maxLength, final Charset cs) {
        return this.io.getString(this.base + offset, maxLength, cs);
    }
    
    public void putByte(final long offset, final byte value) {
        this.io.putByte(this.base + offset, value);
    }
    
    public void putShort(final long offset, final short value) {
        this.io.putShort(this.base + offset, value);
    }
    
    public void putInt(final long offset, final int value) {
        this.io.putInt(this.base + offset, value);
    }
    
    public void putLong(final long offset, final long value) {
        this.io.putLong(this.base + offset, value);
    }
    
    public void putFloat(final long offset, final float value) {
        this.io.putFloat(this.base + offset, value);
    }
    
    public void putDouble(final long offset, final double value) {
        this.io.putDouble(this.base + offset, value);
    }
    
    public void putPointer(final long offset, final Pointer value) {
        this.io.putPointer(this.base + offset, value);
    }
    
    public void putString(final long offset, final String string, final int maxLength, final Charset cs) {
        this.io.putString(this.base + offset, string, maxLength, cs);
    }
    
    public void get(final long offset, final byte[] dst, final int off, final int len) {
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final byte[] dst, final int off, final int len) {
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final short[] dst, final int off, final int len) {
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final short[] dst, final int off, final int len) {
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final int[] dst, final int off, final int len) {
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final int[] dst, final int off, final int len) {
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final long[] dst, final int off, final int len) {
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final long[] dst, final int off, final int len) {
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final float[] dst, final int off, final int len) {
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final float[] dst, final int off, final int len) {
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public void get(final long offset, final double[] dst, final int off, final int len) {
        this.io.get(this.base + offset, dst, off, len);
    }
    
    public void put(final long offset, final double[] dst, final int off, final int len) {
        this.io.put(this.base + offset, dst, off, len);
    }
    
    public int indexOf(final long offset, final byte value, final int maxlen) {
        return this.io.indexOf(this.base + offset, value, maxlen);
    }
    
    public void setMemory(final long offset, final long size, final byte value) {
        this.io.setMemory(this.base + offset, size, value);
    }
}
