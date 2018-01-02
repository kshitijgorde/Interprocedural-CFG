// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi.jffi;

import org.jruby.ext.ffi.Platform;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import org.jruby.ext.ffi.Util;
import org.jruby.Ruby;
import org.jruby.ext.ffi.DirectMemoryIO;
import org.jruby.ext.ffi.MemoryIO;

class BoundedNativeMemoryIO implements MemoryIO, DirectMemoryIO
{
    protected static final com.kenai.jffi.MemoryIO IO;
    protected static final int LONG_SIZE;
    protected static final int ADDRESS_SIZE;
    private final Ruby runtime;
    final long address;
    final long size;
    final DirectMemoryIO parent;
    
    BoundedNativeMemoryIO(final Ruby runtime, final long address, final int size) {
        this.runtime = runtime;
        this.address = address;
        this.size = size;
        this.parent = null;
    }
    
    BoundedNativeMemoryIO(final BoundedNativeMemoryIO parent, final long offset) {
        this.runtime = parent.runtime;
        this.address = parent.getAddress() + offset;
        this.size = parent.size - offset;
        this.parent = parent;
    }
    
    BoundedNativeMemoryIO(final Ruby runtime, final DirectMemoryIO parent, final long offset, final long size) {
        this.runtime = runtime;
        this.address = parent.getAddress() + offset;
        this.size = size;
        this.parent = parent;
    }
    
    private final void checkBounds(final long off, final long len) {
        Util.checkBounds(this.runtime, this.size, off, len);
    }
    
    public final long getAddress() {
        return this.address;
    }
    
    public BoundedNativeMemoryIO slice(final long offset) {
        this.checkBounds(offset, 1L);
        return (offset == 0L) ? this : new BoundedNativeMemoryIO(this, offset);
    }
    
    public BoundedNativeMemoryIO slice(final long offset, final long size) {
        this.checkBounds(offset, size);
        return (offset == 0L && size == this.size) ? this : new BoundedNativeMemoryIO(this.runtime, this, offset, size);
    }
    
    public MemoryIO dup() {
        final AllocatedNativeMemoryIO tmp = AllocatedNativeMemoryIO.allocate(this.runtime, (int)this.size, false);
        BoundedNativeMemoryIO.IO.memcpy(tmp.address, this.address, this.size);
        return tmp;
    }
    
    public final ByteBuffer asByteBuffer() {
        return BoundedNativeMemoryIO.IO.newDirectByteBuffer(this.address, (int)this.size);
    }
    
    Ruby getRuntime() {
        return this.runtime;
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
        this.checkBounds(offset, 1L);
        return BoundedNativeMemoryIO.IO.getByte(this.address + offset);
    }
    
    public final short getShort(final long offset) {
        this.checkBounds(offset, 2L);
        return BoundedNativeMemoryIO.IO.getShort(this.address + offset);
    }
    
    public final int getInt(final long offset) {
        this.checkBounds(offset, 4L);
        return BoundedNativeMemoryIO.IO.getInt(this.address + offset);
    }
    
    public final long getLong(final long offset) {
        this.checkBounds(offset, 8L);
        return BoundedNativeMemoryIO.IO.getLong(this.address + offset);
    }
    
    public final long getNativeLong(final long offset) {
        return (BoundedNativeMemoryIO.LONG_SIZE == 32) ? this.getInt(offset) : this.getLong(offset);
    }
    
    public final float getFloat(final long offset) {
        this.checkBounds(offset, 4L);
        return BoundedNativeMemoryIO.IO.getFloat(this.address + offset);
    }
    
    public final double getDouble(final long offset) {
        this.checkBounds(offset, 8L);
        return BoundedNativeMemoryIO.IO.getDouble(this.address + offset);
    }
    
    public final long getAddress(final long offset) {
        this.checkBounds(offset, BoundedNativeMemoryIO.ADDRESS_SIZE >> 3);
        return BoundedNativeMemoryIO.IO.getAddress(this.address + offset);
    }
    
    public final DirectMemoryIO getMemoryIO(final long offset) {
        this.checkBounds(offset, BoundedNativeMemoryIO.ADDRESS_SIZE >> 3);
        return NativeMemoryIO.wrap(this.runtime, BoundedNativeMemoryIO.IO.getAddress(this.address + offset));
    }
    
    public final void putByte(final long offset, final byte value) {
        this.checkBounds(offset, 1L);
        BoundedNativeMemoryIO.IO.putByte(this.address + offset, value);
    }
    
    public final void putShort(final long offset, final short value) {
        this.checkBounds(offset, 2L);
        BoundedNativeMemoryIO.IO.putShort(this.address + offset, value);
    }
    
    public final void putInt(final long offset, final int value) {
        this.checkBounds(offset, 4L);
        BoundedNativeMemoryIO.IO.putInt(this.address + offset, value);
    }
    
    public final void putLong(final long offset, final long value) {
        this.checkBounds(offset, 8L);
        BoundedNativeMemoryIO.IO.putLong(this.address + offset, value);
    }
    
    public final void putNativeLong(final long offset, final long value) {
        if (BoundedNativeMemoryIO.LONG_SIZE == 32) {
            this.putInt(offset, (int)value);
        }
        else {
            this.putLong(offset, value);
        }
    }
    
    public final void putAddress(final long offset, final long value) {
        this.checkBounds(offset, BoundedNativeMemoryIO.ADDRESS_SIZE >> 3);
        BoundedNativeMemoryIO.IO.putAddress(this.address + offset, value);
    }
    
    public final void putFloat(final long offset, final float value) {
        this.checkBounds(offset, 4L);
        BoundedNativeMemoryIO.IO.putFloat(this.address + offset, value);
    }
    
    public final void putDouble(final long offset, final double value) {
        this.checkBounds(offset, 8L);
        BoundedNativeMemoryIO.IO.putDouble(this.address + offset, value);
    }
    
    public final void putMemoryIO(final long offset, final MemoryIO value) {
        this.checkBounds(offset, BoundedNativeMemoryIO.ADDRESS_SIZE >> 3);
        BoundedNativeMemoryIO.IO.putAddress(this.address + offset, ((DirectMemoryIO)value).getAddress());
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        this.checkBounds(offset, len);
        BoundedNativeMemoryIO.IO.getByteArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final byte[] src, final int off, final int len) {
        this.checkBounds(offset, len);
        BoundedNativeMemoryIO.IO.putByteArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        this.checkBounds(offset, len << 1);
        BoundedNativeMemoryIO.IO.getShortArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final short[] src, final int off, final int len) {
        this.checkBounds(offset, len << 1);
        BoundedNativeMemoryIO.IO.putShortArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        this.checkBounds(offset, len << 2);
        BoundedNativeMemoryIO.IO.getIntArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final int[] src, final int off, final int len) {
        this.checkBounds(offset, len << 2);
        BoundedNativeMemoryIO.IO.putIntArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        this.checkBounds(offset, len << 3);
        BoundedNativeMemoryIO.IO.getLongArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final long[] src, final int off, final int len) {
        this.checkBounds(offset, len << 3);
        BoundedNativeMemoryIO.IO.putLongArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        this.checkBounds(offset, len << 2);
        BoundedNativeMemoryIO.IO.getFloatArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final float[] src, final int off, final int len) {
        this.checkBounds(offset, len << 2);
        BoundedNativeMemoryIO.IO.putFloatArray(this.address + offset, src, off, len);
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        this.checkBounds(offset, len << 3);
        BoundedNativeMemoryIO.IO.getDoubleArray(this.address + offset, dst, off, len);
    }
    
    public final void put(final long offset, final double[] src, final int off, final int len) {
        this.checkBounds(offset, len << 3);
        BoundedNativeMemoryIO.IO.putDoubleArray(this.address + offset, src, off, len);
    }
    
    public final int indexOf(final long offset, final byte value) {
        return (value == 0) ? ((int)BoundedNativeMemoryIO.IO.getStringLength(this.address + offset)) : ((int)BoundedNativeMemoryIO.IO.indexOf(this.address + offset, value));
    }
    
    public final int indexOf(final long offset, final byte value, final int maxlen) {
        return (int)BoundedNativeMemoryIO.IO.indexOf(this.address, value, maxlen);
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        this.checkBounds(offset, size);
        BoundedNativeMemoryIO.IO.setMemory(this.address + offset, size, value);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long offset) {
        this.checkBounds(offset, 1L);
        return FFIUtil.getZeroTerminatedByteArray(this.address + offset);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long offset, final int maxlen) {
        this.checkBounds(offset, 1L);
        return FFIUtil.getZeroTerminatedByteArray(this.address + offset, Math.min(maxlen, (int)(this.size - offset)));
    }
    
    public void putZeroTerminatedByteArray(final long offset, final byte[] bytes, final int off, final int len) {
        this.checkBounds(offset, len + 1);
        FFIUtil.putZeroTerminatedByteArray(this.address + offset, bytes, off, len);
    }
    
    static {
        IO = com.kenai.jffi.MemoryIO.getInstance();
        LONG_SIZE = Platform.getPlatform().longSize();
        ADDRESS_SIZE = Platform.getPlatform().addressSize();
    }
}
