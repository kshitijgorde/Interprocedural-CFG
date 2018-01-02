// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import com.kenai.jaffl.provider.StringIO;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.provider.NullMemoryIO;
import com.kenai.jaffl.Address;
import com.kenai.jffi.MemoryIO;
import com.kenai.jaffl.provider.AbstractMemoryIO;

class DirectMemoryIO extends AbstractMemoryIO
{
    static final com.kenai.jffi.MemoryIO IO;
    protected final long address;
    
    DirectMemoryIO(final long address) {
        this.address = (address & Address.MASK);
    }
    
    public final long address() {
        return this.address;
    }
    
    public final byte getByte(final long offset) {
        return DirectMemoryIO.IO.getByte(this.address + offset);
    }
    
    public final short getShort(final long offset) {
        return DirectMemoryIO.IO.getShort(this.address + offset);
    }
    
    public final int getInt(final long offset) {
        return DirectMemoryIO.IO.getInt(this.address + offset);
    }
    
    public final long getLong(final long offset) {
        return DirectMemoryIO.IO.getLong(this.address + offset);
    }
    
    public final float getFloat(final long offset) {
        return DirectMemoryIO.IO.getFloat(this.address + offset);
    }
    
    public final double getDouble(final long offset) {
        return DirectMemoryIO.IO.getDouble(this.address + offset);
    }
    
    public final void putByte(final long offset, final byte value) {
        DirectMemoryIO.IO.putByte(this.address + offset, value);
    }
    
    public final void putShort(final long offset, final short value) {
        DirectMemoryIO.IO.putShort(this.address + offset, value);
    }
    
    public final void putInt(final long offset, final int value) {
        DirectMemoryIO.IO.putInt(this.address + offset, value);
    }
    
    public final void putLong(final long offset, final long value) {
        DirectMemoryIO.IO.putLong(this.address + offset, value);
    }
    
    public final void putFloat(final long offset, final float value) {
        DirectMemoryIO.IO.putFloat(this.address + offset, value);
    }
    
    public final void putDouble(final long offset, final double value) {
        DirectMemoryIO.IO.putDouble(this.address + offset, value);
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        DirectMemoryIO.IO.getByteArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final byte[] src, final int off, final int len) {
        DirectMemoryIO.IO.putByteArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        DirectMemoryIO.IO.getShortArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final short[] src, final int off, final int len) {
        DirectMemoryIO.IO.putShortArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        DirectMemoryIO.IO.getIntArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final int[] src, final int off, final int len) {
        DirectMemoryIO.IO.putIntArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        DirectMemoryIO.IO.getLongArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final long[] src, final int off, final int len) {
        DirectMemoryIO.IO.putLongArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        DirectMemoryIO.IO.getFloatArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final float[] src, final int off, final int len) {
        DirectMemoryIO.IO.putFloatArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        DirectMemoryIO.IO.getDoubleArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final double[] src, final int off, final int len) {
        DirectMemoryIO.IO.putDoubleArray(this.address + offset, src, off, len);
    }
    
    public MemoryIO getMemoryIO(final long offset) {
        return MemoryUtil.newMemoryIO(DirectMemoryIO.IO.getAddress(this.address + offset));
    }
    
    public MemoryIO getMemoryIO(final long offset, final long size) {
        final long ptr = DirectMemoryIO.IO.getAddress(this.address + offset);
        return (ptr != 0L) ? new BoundedDirectMemoryIO(new DirectMemoryIO(ptr), 0L, size) : new NullMemoryIO();
    }
    
    public Pointer getPointer(final long offset) {
        return MemoryUtil.newPointer(DirectMemoryIO.IO.getAddress(this.address + offset));
    }
    
    public void putPointer(final long offset, final Pointer value) {
        DirectMemoryIO.IO.putAddress(this.address + offset, value.address());
    }
    
    public String getString(final long offset) {
        final byte[] bytes = DirectMemoryIO.IO.getZeroTerminatedByteArray(this.address + offset);
        return StringIO.getStringIO().fromNative(ByteBuffer.wrap(bytes)).toString();
    }
    
    public String getString(final long offset, final int maxLength, final Charset cs) {
        final byte[] bytes = DirectMemoryIO.IO.getZeroTerminatedByteArray(this.address + offset, maxLength);
        final ByteBuffer buf = ByteBuffer.wrap(bytes);
        return StringIO.getStringIO().fromNative(buf).toString();
    }
    
    public void putString(final long offset, final String string, final int maxLength, final Charset cs) {
        final ByteBuffer buf = StringIO.getStringIO().toNative(string, 0, true);
        DirectMemoryIO.IO.putByteArray(this.address + offset, buf.array(), buf.arrayOffset() + buf.position(), buf.remaining());
    }
    
    public int indexOf(final long offset, final byte value, final int maxlen) {
        return (int)DirectMemoryIO.IO.indexOf(this.address + offset, value, maxlen);
    }
    
    public final boolean isDirect() {
        return true;
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        DirectMemoryIO.IO.setMemory(this.address + offset, size, value);
    }
    
    static {
        IO = com.kenai.jffi.MemoryIO.getInstance();
    }
}
