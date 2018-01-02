// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.jruby.Ruby;

public final class SwappedMemoryIO implements MemoryIO, DirectMemoryIO
{
    protected static final int LONG_SIZE;
    protected static final int ADDRESS_SIZE;
    private final Ruby runtime;
    private final MemoryIO io;
    
    SwappedMemoryIO(final Ruby runtime, final MemoryIO io) {
        this.runtime = runtime;
        this.io = io;
    }
    
    public final ByteOrder order() {
        return ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
    }
    
    public final long getAddress() {
        return (this.io instanceof DirectMemoryIO) ? ((DirectMemoryIO)this.io).getAddress() : 0L;
    }
    
    public SwappedMemoryIO slice(final long offset) {
        return (offset == 0L) ? this : new SwappedMemoryIO(this.runtime, this.io.slice(offset));
    }
    
    public SwappedMemoryIO slice(final long offset, final long size) {
        return new SwappedMemoryIO(this.runtime, this.io.slice(offset, size));
    }
    
    public SwappedMemoryIO dup() {
        return new SwappedMemoryIO(this.runtime, this.io.dup());
    }
    
    public final ByteBuffer asByteBuffer() {
        return this.io.asByteBuffer().order(this.order());
    }
    
    Ruby getRuntime() {
        return this.runtime;
    }
    
    public final boolean equals(final Object obj) {
        return obj == this || (obj instanceof SwappedMemoryIO && ((SwappedMemoryIO)obj).io.equals(this.io));
    }
    
    public final int hashCode() {
        return this.io.hashCode();
    }
    
    public final boolean isNull() {
        return this.io.isNull();
    }
    
    public final boolean isDirect() {
        return this.io.isDirect();
    }
    
    public final byte getByte(final long offset) {
        return this.io.getByte(offset);
    }
    
    public final short getShort(final long offset) {
        return Short.reverseBytes(this.io.getShort(offset));
    }
    
    public final int getInt(final long offset) {
        return Integer.reverseBytes(this.io.getInt(offset));
    }
    
    public final long getLong(final long offset) {
        return Long.reverseBytes(this.io.getLong(offset));
    }
    
    public final long getNativeLong(final long offset) {
        return (SwappedMemoryIO.LONG_SIZE == 32) ? this.getInt(offset) : this.getLong(offset);
    }
    
    public final float getFloat(final long offset) {
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(this.io.getFloat(offset))));
    }
    
    public final double getDouble(final long offset) {
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(this.io.getDouble(offset))));
    }
    
    public final long getAddress(final long offset) {
        throw this.runtime.newRuntimeError("cannot get native address values in non-native byte order memory");
    }
    
    public final DirectMemoryIO getMemoryIO(final long offset) {
        throw this.runtime.newRuntimeError("cannot get native address values in non-native byte order memory");
    }
    
    public final void putByte(final long offset, final byte value) {
        this.io.putByte(offset, value);
    }
    
    public final void putShort(final long offset, final short value) {
        this.io.putShort(offset, Short.reverseBytes(value));
    }
    
    public final void putInt(final long offset, final int value) {
        this.io.putInt(offset, Integer.reverseBytes(value));
    }
    
    public final void putLong(final long offset, final long value) {
        this.io.putLong(offset, Long.reverseBytes(value));
    }
    
    public final void putNativeLong(final long offset, final long value) {
        if (SwappedMemoryIO.LONG_SIZE == 32) {
            this.putInt(offset, (int)value);
        }
        else {
            this.putLong(offset, value);
        }
    }
    
    public final void putAddress(final long offset, final long value) {
        throw this.runtime.newRuntimeError("cannot write native address values to non-native byte order memory");
    }
    
    public final void putFloat(final long offset, final float value) {
        this.io.putFloat(offset, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(value))));
    }
    
    public final void putDouble(final long offset, final double value) {
        this.io.putDouble(offset, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(value))));
    }
    
    public final void putMemoryIO(final long offset, final MemoryIO value) {
        throw this.runtime.newRuntimeError("cannot write native address values to non-native byte order memory");
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        this.io.get(offset, dst, off, len);
    }
    
    public final void put(final long offset, final byte[] src, final int off, final int len) {
        this.io.put(offset, src, off, len);
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        this.io.get(offset, dst, off, len);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = Short.reverseBytes(dst[off + i]);
        }
    }
    
    public final void put(final long offset, final short[] src, final int off, final int len) {
        final short[] values = new short[len];
        for (int i = 0; i < len; ++i) {
            values[i] = Short.reverseBytes(src[off + i]);
        }
        this.io.put(offset, values, 0, len);
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        this.io.get(offset, dst, off, len);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = Integer.reverseBytes(dst[off + i]);
        }
    }
    
    public final void put(final long offset, final int[] src, final int off, final int len) {
        final int[] values = new int[len];
        for (int i = 0; i < len; ++i) {
            values[i] = Integer.reverseBytes(src[off + i]);
        }
        this.io.put(offset, values, 0, len);
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        this.io.get(offset, dst, off, len);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = Long.reverseBytes(dst[off + i]);
        }
    }
    
    public final void put(final long offset, final long[] src, final int off, final int len) {
        final long[] values = new long[len];
        for (int i = 0; i < len; ++i) {
            values[i] = Long.reverseBytes(src[off + i]);
        }
        this.io.put(offset, values, 0, len);
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        this.io.get(offset, dst, off, len);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(dst[off + i])));
        }
    }
    
    public final void put(final long offset, final float[] src, final int off, final int len) {
        final int[] values = new int[len];
        for (int i = 0; i < len; ++i) {
            values[i] = Integer.reverseBytes(Float.floatToRawIntBits(src[off + i]));
        }
        this.io.put(offset, values, 0, len);
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        this.io.get(offset, dst, off, len);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dst[off + i])));
        }
    }
    
    public final void put(final long offset, final double[] src, final int off, final int len) {
        final long[] values = new long[len];
        for (int i = 0; i < len; ++i) {
            values[i] = Long.reverseBytes(Double.doubleToRawLongBits(src[off + i]));
        }
        this.io.put(offset, values, 0, len);
    }
    
    public final int indexOf(final long offset, final byte value) {
        return this.io.indexOf(offset, value);
    }
    
    public final int indexOf(final long offset, final byte value, final int maxlen) {
        return this.io.indexOf(offset, value, maxlen);
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        this.io.setMemory(offset, size, value);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long offset) {
        return this.io.getZeroTerminatedByteArray(offset);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long offset, final int maxlen) {
        return this.io.getZeroTerminatedByteArray(offset, maxlen);
    }
    
    public void putZeroTerminatedByteArray(final long offset, final byte[] bytes, final int off, final int len) {
        this.io.putZeroTerminatedByteArray(offset, bytes, off, len);
    }
    
    static {
        LONG_SIZE = Platform.getPlatform().longSize();
        ADDRESS_SIZE = Platform.getPlatform().addressSize();
    }
}
