// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.Platform;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import org.jruby.ext.ffi.NullMemoryIO;
import org.jruby.Ruby;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.MemoryIO;

class NativeMemoryIO implements MemoryIO, DirectMemoryIO
{
    protected static final com.kenai.jffi.MemoryIO IO;
    final NativeMemoryIO parent;
    final long address;
    private final Ruby runtime;
    
    static final DirectMemoryIO wrap(final Ruby runtime, final long address) {
        return (DirectMemoryIO)((address != 0L) ? new NativeMemoryIO(runtime, address) : new NullMemoryIO(runtime));
    }
    
    NativeMemoryIO(final Ruby runtime, final long address) {
        this.runtime = runtime;
        this.address = address;
        this.parent = null;
    }
    
    private NativeMemoryIO(final NativeMemoryIO parent, final long offset) {
        this.parent = parent;
        this.address = parent.address + offset;
        this.runtime = parent.runtime;
    }
    
    public final long getAddress() {
        return this.address;
    }
    
    public NativeMemoryIO slice(final long offset) {
        return (offset == 0L) ? this : new NativeMemoryIO(this, offset);
    }
    
    public DirectMemoryIO slice(final long offset, final long size) {
        return new BoundedNativeMemoryIO(this.runtime, this, offset, size);
    }
    
    public MemoryIO dup() {
        throw this.runtime.newNotImplementedError("cannot duplicate unbounded memory area");
    }
    
    public final ByteBuffer asByteBuffer() {
        return NativeMemoryIO.IO.newDirectByteBuffer(this.address, Integer.MAX_VALUE);
    }
    
    public final boolean equals(final Object obj) {
        return obj instanceof DirectMemoryIO && ((DirectMemoryIO)obj).getAddress() == this.address;
    }
    
    public final int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int)(this.address ^ this.address >>> 32);
        return hash;
    }
    
    public final boolean isNull() {
        return this.address == 0L;
    }
    
    public final boolean isDirect() {
        return true;
    }
    
    public final ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
    
    public final byte getByte(final long offset) {
        return NativeMemoryIO.IO.getByte(this.address + offset);
    }
    
    public final short getShort(final long offset) {
        return NativeMemoryIO.IO.getShort(this.address + offset);
    }
    
    public final int getInt(final long offset) {
        return NativeMemoryIO.IO.getInt(this.address + offset);
    }
    
    public final long getLong(final long offset) {
        return NativeMemoryIO.IO.getLong(this.address + offset);
    }
    
    public final long getNativeLong(final long offset) {
        return (Platform.getPlatform().longSize() == 32) ? NativeMemoryIO.IO.getInt(this.address + offset) : NativeMemoryIO.IO.getLong(this.address + offset);
    }
    
    public final float getFloat(final long offset) {
        return NativeMemoryIO.IO.getFloat(this.address + offset);
    }
    
    public final double getDouble(final long offset) {
        return NativeMemoryIO.IO.getDouble(this.address + offset);
    }
    
    public final long getAddress(final long offset) {
        return NativeMemoryIO.IO.getAddress(this.address + offset);
    }
    
    public final DirectMemoryIO getMemoryIO(final long offset) {
        return wrap(this.runtime, NativeMemoryIO.IO.getAddress(this.address + offset));
    }
    
    public final void putByte(final long offset, final byte value) {
        NativeMemoryIO.IO.putByte(this.address + offset, value);
    }
    
    public final void putShort(final long offset, final short value) {
        NativeMemoryIO.IO.putShort(this.address + offset, value);
    }
    
    public final void putInt(final long offset, final int value) {
        NativeMemoryIO.IO.putInt(this.address + offset, value);
    }
    
    public final void putLong(final long offset, final long value) {
        NativeMemoryIO.IO.putLong(this.address + offset, value);
    }
    
    public final void putNativeLong(final long offset, final long value) {
        if (Platform.getPlatform().longSize() == 32) {
            NativeMemoryIO.IO.putInt(this.address + offset, (int)value);
        }
        else {
            NativeMemoryIO.IO.putLong(this.address + offset, value);
        }
    }
    
    public final void putAddress(final long offset, final long value) {
        NativeMemoryIO.IO.putAddress(this.address + offset, value);
    }
    
    public final void putFloat(final long offset, final float value) {
        NativeMemoryIO.IO.putFloat(this.address + offset, value);
    }
    
    public final void putDouble(final long offset, final double value) {
        NativeMemoryIO.IO.putDouble(this.address + offset, value);
    }
    
    public final void putMemoryIO(final long offset, final MemoryIO value) {
        NativeMemoryIO.IO.putAddress(this.address + offset, ((DirectMemoryIO)value).getAddress());
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        NativeMemoryIO.IO.getByteArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final byte[] src, final int off, final int len) {
        NativeMemoryIO.IO.putByteArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        NativeMemoryIO.IO.getShortArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final short[] src, final int off, final int len) {
        NativeMemoryIO.IO.putShortArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        NativeMemoryIO.IO.getIntArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final int[] src, final int off, final int len) {
        NativeMemoryIO.IO.putIntArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        NativeMemoryIO.IO.getLongArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final long[] src, final int off, final int len) {
        NativeMemoryIO.IO.putLongArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        NativeMemoryIO.IO.getFloatArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final float[] src, final int off, final int len) {
        NativeMemoryIO.IO.putFloatArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        NativeMemoryIO.IO.getDoubleArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final double[] src, final int off, final int len) {
        NativeMemoryIO.IO.putDoubleArray(this.address + offset, src, off, len);
    }
    
    public final int indexOf(final long offset, final byte value) {
        return (value == 0) ? ((int)NativeMemoryIO.IO.getStringLength(this.address + offset)) : ((int)NativeMemoryIO.IO.indexOf(this.address + offset, value));
    }
    
    public final int indexOf(final long offset, final byte value, final int maxlen) {
        return (int)NativeMemoryIO.IO.indexOf(this.address, value, maxlen);
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        NativeMemoryIO.IO.setMemory(this.address + offset, size, value);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long offset) {
        return FFIUtil.getZeroTerminatedByteArray(this.address + offset);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long offset, final int maxlen) {
        return FFIUtil.getZeroTerminatedByteArray(this.address + offset, maxlen);
    }
    
    public void putZeroTerminatedByteArray(final long offset, final byte[] bytes, final int off, final int len) {
        FFIUtil.putZeroTerminatedByteArray(this.address + offset, bytes, off, len);
    }
    
    static {
        IO = com.kenai.jffi.MemoryIO.getInstance();
    }
}
