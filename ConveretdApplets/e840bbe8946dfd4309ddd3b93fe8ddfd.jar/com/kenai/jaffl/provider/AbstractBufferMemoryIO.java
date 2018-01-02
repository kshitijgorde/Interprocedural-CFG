// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.nio.charset.Charset;
import com.kenai.jaffl.util.BufferUtil;
import java.nio.ByteBuffer;

public abstract class AbstractBufferMemoryIO extends AbstractMemoryIO
{
    protected final ByteBuffer buffer;
    
    public AbstractBufferMemoryIO(final ByteBuffer buffer) {
        this.buffer = buffer;
    }
    
    public final boolean isDirect() {
        return this.buffer.isDirect();
    }
    
    public long address() {
        throw new UnsupportedOperationException("Not a direct memory object.");
    }
    
    public final ByteBuffer getByteBuffer() {
        return this.buffer;
    }
    
    public byte getByte(final long offset) {
        return this.buffer.get((int)offset);
    }
    
    public short getShort(final long offset) {
        return this.buffer.getShort((int)offset);
    }
    
    public int getInt(final long offset) {
        return this.buffer.getInt((int)offset);
    }
    
    public long getLong(final long offset) {
        return this.buffer.getLong((int)offset);
    }
    
    public float getFloat(final long offset) {
        return this.buffer.getFloat((int)offset);
    }
    
    public double getDouble(final long offset) {
        return this.buffer.getDouble((int)offset);
    }
    
    public void putByte(final long offset, final byte value) {
        this.buffer.put((int)offset, value);
    }
    
    public void putShort(final long offset, final short value) {
        this.buffer.putShort((int)offset, value);
    }
    
    public void putInt(final long offset, final int value) {
        this.buffer.putInt((int)offset, value);
    }
    
    public void putLong(final long offset, final long value) {
        this.buffer.putLong((int)offset, value);
    }
    
    public void putFloat(final long offset, final float value) {
        this.buffer.putFloat((int)offset, value);
    }
    
    public void putDouble(final long offset, final double value) {
        this.buffer.putDouble((int)offset, value);
    }
    
    public String getString(final long offset, final int size) {
        return BufferUtil.getString(BufferUtil.slice(this.buffer, (int)offset), Charset.defaultCharset());
    }
    
    public void putString(final long offset, final String string) {
        BufferUtil.putString(BufferUtil.slice(this.buffer, (int)offset), Charset.defaultCharset(), string);
    }
    
    public void get(final long offset, final byte[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len).get(dst, off, len);
    }
    
    public void get(final long offset, final short[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 16 / 8).asShortBuffer().get(dst, off, len);
    }
    
    public void get(final long offset, final int[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 32 / 8).asIntBuffer().get(dst, off, len);
    }
    
    public void get(final long offset, final long[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 64 / 8).asLongBuffer().get(dst, off, len);
    }
    
    public void get(final long offset, final float[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 32 / 8).asFloatBuffer().get(dst, off, len);
    }
    
    public void get(final long offset, final double[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 64 / 8).asDoubleBuffer().get(dst, off, len);
    }
    
    public void put(final long offset, final byte[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len).put(dst, off, len);
    }
    
    public void put(final long offset, final short[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 16 / 8).asShortBuffer().put(dst, off, len);
    }
    
    public void put(final long offset, final int[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 32 / 8).asIntBuffer().put(dst, off, len);
    }
    
    public void put(final long offset, final long[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 64 / 8).asLongBuffer().put(dst, off, len);
    }
    
    public void put(final long offset, final float[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 32 / 8).asFloatBuffer().put(dst, off, len);
    }
    
    public void put(final long offset, final double[] dst, final int off, final int len) {
        BufferUtil.slice(this.buffer, (int)offset, len * 64 / 8).asDoubleBuffer().put(dst, off, len);
    }
    
    public String getString(final long offset) {
        return BufferUtil.getString(BufferUtil.slice(this.buffer, (int)offset), Charset.defaultCharset());
    }
    
    public String getString(final long offset, final int maxLength, final Charset cs) {
        return BufferUtil.getString(BufferUtil.slice(this.buffer, (int)offset, maxLength), cs);
    }
    
    public void putString(final long offset, final String string, final int maxLength, final Charset cs) {
        BufferUtil.putString(BufferUtil.slice(this.buffer, (int)offset, maxLength), cs, string);
    }
    
    public int indexOf(long offset, final byte value, final int maxlen) {
        while (offset > -1L) {
            if (this.buffer.get((int)offset) == value) {
                return (int)offset;
            }
            ++offset;
        }
        return -1;
    }
    
    public void setMemory(final long offset, final long size, final byte value) {
        for (int i = 0; i < size; ++i) {
            this.buffer.put((int)offset + i, value);
        }
    }
}
