// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.util.Arrays;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import com.kenai.jaffl.Platform;
import java.nio.ByteOrder;

public abstract class AbstractArrayMemoryIO extends AbstractMemoryIO
{
    protected static final ArrayIO IO;
    protected static final int LONG_SIZE;
    protected final byte[] buffer;
    protected final int offset;
    protected final int length;
    
    public AbstractArrayMemoryIO(final byte[] buffer, final int offset, final int length) {
        this.buffer = buffer;
        this.offset = offset;
        this.length = length;
    }
    
    public AbstractArrayMemoryIO(final byte[] buffer) {
        this(buffer, 0, buffer.length);
    }
    
    public AbstractArrayMemoryIO(final int size) {
        this(new byte[size], 0, size);
    }
    
    public final byte[] array() {
        return this.buffer;
    }
    
    public final int offset() {
        return this.offset;
    }
    
    public final int length() {
        return this.length;
    }
    
    public final boolean isDirect() {
        return false;
    }
    
    public long address() {
        throw new UnsupportedOperationException("Not a direct memory object");
    }
    
    private static final ArrayIO getArrayIO() {
        if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            return (Platform.getPlatform().addressSize() == 64) ? newBE64ArrayIO() : newBE32ArrayIO();
        }
        return (Platform.getPlatform().addressSize() == 64) ? newLE64ArrayIO() : newLE32ArrayIO();
    }
    
    private static final ArrayIO newBE64ArrayIO() {
        return new BE64ArrayIO();
    }
    
    private static final ArrayIO newBE32ArrayIO() {
        return new BE32ArrayIO();
    }
    
    private static final ArrayIO newLE64ArrayIO() {
        return new LE64ArrayIO();
    }
    
    private static final ArrayIO newLE32ArrayIO() {
        return new LE32ArrayIO();
    }
    
    protected final int index(final long off) {
        return this.offset + (int)off;
    }
    
    public final boolean isNull() {
        return false;
    }
    
    public String getString(final long offset) {
        final ByteBuffer tmp = ByteBuffer.wrap(this.buffer, this.index(offset), this.length - (int)offset);
        return StringIO.getStringIO().fromNative(tmp, this.length - (int)offset).toString();
    }
    
    public String getString(final long offset, final int maxLength, final Charset cs) {
        final ByteBuffer tmp = ByteBuffer.wrap(this.buffer, this.index(offset), this.length - (int)offset);
        return StringIO.getStringIO().fromNative(tmp, Math.min(maxLength, this.length - (int)offset)).toString();
    }
    
    public void putString(final long offset, final String string, final int maxLength, final Charset cs) {
        StringIO.getStringIO().toNative(string, ByteBuffer.wrap(this.buffer, this.index(offset), Math.min(maxLength, this.length - (int)offset)));
    }
    
    public final byte getByte(final long offset) {
        return (byte)(this.buffer[this.index(offset)] & 0xFF);
    }
    
    public final short getShort(final long offset) {
        return AbstractArrayMemoryIO.IO.getInt16(this.buffer, this.index(offset));
    }
    
    public final int getInt(final long offset) {
        return AbstractArrayMemoryIO.IO.getInt32(this.buffer, this.index(offset));
    }
    
    public final long getLong(final long offset) {
        return AbstractArrayMemoryIO.IO.getInt64(this.buffer, this.index(offset));
    }
    
    public final long getAddress(final long offset) {
        return AbstractArrayMemoryIO.IO.getAddress(this.buffer, this.index(offset));
    }
    
    public final float getFloat(final long offset) {
        return AbstractArrayMemoryIO.IO.getFloat32(this.buffer, this.index(offset));
    }
    
    public final double getDouble(final long offset) {
        return AbstractArrayMemoryIO.IO.getFloat64(this.buffer, this.index(offset));
    }
    
    public final void putByte(final long offset, final byte value) {
        this.buffer[this.index(offset)] = value;
    }
    
    public final void putShort(final long offset, final short value) {
        AbstractArrayMemoryIO.IO.putInt16(this.buffer, this.index(offset), value);
    }
    
    public final void putInt(final long offset, final int value) {
        AbstractArrayMemoryIO.IO.putInt32(this.buffer, this.index(offset), value);
    }
    
    public final void putLong(final long offset, final long value) {
        AbstractArrayMemoryIO.IO.putInt64(this.buffer, this.index(offset), value);
    }
    
    public final void putAddress(final long offset, final long value) {
        AbstractArrayMemoryIO.IO.putAddress(this.buffer, this.index(offset), value);
    }
    
    public final void putFloat(final long offset, final float value) {
        AbstractArrayMemoryIO.IO.putFloat32(this.buffer, this.index(offset), value);
    }
    
    public final void putDouble(final long offset, final double value) {
        AbstractArrayMemoryIO.IO.putFloat64(this.buffer, this.index(offset), value);
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        System.arraycopy(this.buffer, this.index(offset), dst, off, len);
    }
    
    public final void put(final long offset, final byte[] src, final int off, final int len) {
        System.arraycopy(src, off, this.buffer, this.index(offset), len);
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = AbstractArrayMemoryIO.IO.getInt16(this.buffer, begin + (i << 1));
        }
    }
    
    public final void put(final long offset, final short[] src, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            AbstractArrayMemoryIO.IO.putInt16(this.buffer, begin + (i << 1), src[off + i]);
        }
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = AbstractArrayMemoryIO.IO.getInt32(this.buffer, begin + (i << 2));
        }
    }
    
    public final void put(final long offset, final int[] src, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            AbstractArrayMemoryIO.IO.putInt32(this.buffer, begin + (i << 2), src[off + i]);
        }
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = AbstractArrayMemoryIO.IO.getInt64(this.buffer, begin + (i << 3));
        }
    }
    
    public final void put(final long offset, final long[] src, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            AbstractArrayMemoryIO.IO.putInt64(this.buffer, begin + (i << 3), src[off + i]);
        }
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = AbstractArrayMemoryIO.IO.getFloat32(this.buffer, begin + (i << 2));
        }
    }
    
    public final void put(final long offset, final float[] src, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            AbstractArrayMemoryIO.IO.putFloat32(this.buffer, begin + (i << 2), src[off + i]);
        }
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            dst[off + i] = AbstractArrayMemoryIO.IO.getFloat64(this.buffer, begin + (i << 3));
        }
    }
    
    public final void put(final long offset, final double[] src, final int off, final int len) {
        final int begin = this.index(offset);
        for (int i = 0; i < len; ++i) {
            AbstractArrayMemoryIO.IO.putFloat64(this.buffer, begin + (i << 3), src[off + i]);
        }
    }
    
    public final int indexOf(final long offset, final byte value) {
        final int off = this.index(offset);
        for (int i = 0; i < this.length; ++i) {
            if (this.buffer[off + i] == value) {
                return i;
            }
        }
        return -1;
    }
    
    public final int indexOf(final long offset, final byte value, final int maxlen) {
        final int off = this.index(offset);
        for (int i = 0; i < Math.min(this.length, maxlen); ++i) {
            if (this.buffer[off + i] == value) {
                return i;
            }
        }
        return -1;
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        Arrays.fill(this.buffer, this.index(offset), (int)size, value);
    }
    
    public final void clear() {
        Arrays.fill(this.buffer, this.offset, this.length, (byte)0);
    }
    
    static {
        IO = getArrayIO();
        LONG_SIZE = Platform.getPlatform().longSize();
    }
    
    protected abstract static class ArrayIO
    {
        public abstract short getInt16(final byte[] p0, final int p1);
        
        public abstract int getInt32(final byte[] p0, final int p1);
        
        public abstract long getInt64(final byte[] p0, final int p1);
        
        public abstract long getAddress(final byte[] p0, final int p1);
        
        public abstract void putInt16(final byte[] p0, final int p1, final int p2);
        
        public abstract void putInt32(final byte[] p0, final int p1, final int p2);
        
        public abstract void putInt64(final byte[] p0, final int p1, final long p2);
        
        public abstract void putAddress(final byte[] p0, final int p1, final long p2);
        
        public final float getFloat32(final byte[] buffer, final int offset) {
            return Float.intBitsToFloat(this.getInt32(buffer, offset));
        }
        
        public final void putFloat32(final byte[] buffer, final int offset, final float value) {
            this.putInt32(buffer, offset, Float.floatToRawIntBits(value));
        }
        
        public final double getFloat64(final byte[] buffer, final int offset) {
            return Double.longBitsToDouble(this.getInt64(buffer, offset));
        }
        
        public final void putFloat64(final byte[] buffer, final int offset, final double value) {
            this.putInt64(buffer, offset, Double.doubleToRawLongBits(value));
        }
    }
    
    private abstract static class LittleEndianArrayIO extends ArrayIO
    {
        public final short getInt16(final byte[] array, final int offset) {
            return (short)((array[offset] & 0xFF) | (array[offset + 1] & 0xFF) << 8);
        }
        
        public final int getInt32(final byte[] array, final int offset) {
            return (array[offset + 0] & 0xFF) << 0 | (array[offset + 1] & 0xFF) << 8 | (array[offset + 2] & 0xFF) << 16 | (array[offset + 3] & 0xFF) << 24;
        }
        
        public final long getInt64(final byte[] array, final int offset) {
            return (array[offset + 0] & 0xFFL) << 0 | (array[offset + 1] & 0xFFL) << 8 | (array[offset + 2] & 0xFFL) << 16 | (array[offset + 3] & 0xFFL) << 24 | (array[offset + 4] & 0xFFL) << 32 | (array[offset + 5] & 0xFFL) << 40 | (array[offset + 6] & 0xFFL) << 48 | (array[offset + 7] & 0xFFL) << 56;
        }
        
        public final void putInt16(final byte[] buffer, final int offset, final int value) {
            buffer[offset + 0] = (byte)(value >> 0);
            buffer[offset + 1] = (byte)(value >> 8);
        }
        
        public final void putInt32(final byte[] buffer, final int offset, final int value) {
            buffer[offset + 0] = (byte)(value >> 0);
            buffer[offset + 1] = (byte)(value >> 8);
            buffer[offset + 2] = (byte)(value >> 16);
            buffer[offset + 3] = (byte)(value >> 24);
        }
        
        public final void putInt64(final byte[] buffer, final int offset, final long value) {
            buffer[offset + 0] = (byte)(value >> 0);
            buffer[offset + 1] = (byte)(value >> 8);
            buffer[offset + 2] = (byte)(value >> 16);
            buffer[offset + 3] = (byte)(value >> 24);
            buffer[offset + 4] = (byte)(value >> 32);
            buffer[offset + 5] = (byte)(value >> 40);
            buffer[offset + 6] = (byte)(value >> 48);
            buffer[offset + 7] = (byte)(value >> 56);
        }
    }
    
    private abstract static class BigEndianArrayIO extends ArrayIO
    {
        public short getInt16(final byte[] array, final int offset) {
            return (short)((array[offset + 0] & 0xFF) << 8 | (array[offset + 1] & 0xFF));
        }
        
        public int getInt32(final byte[] array, final int offset) {
            return (array[offset + 0] & 0xFF) << 24 | (array[offset + 1] & 0xFF) << 16 | (array[offset + 2] & 0xFF) << 8 | (array[offset + 3] & 0xFF) << 0;
        }
        
        public long getInt64(final byte[] array, final int offset) {
            return (array[offset + 0] & 0xFFL) << 56 | (array[offset + 1] & 0xFFL) << 48 | (array[offset + 2] & 0xFFL) << 40 | (array[offset + 3] & 0xFFL) << 32 | (array[offset + 4] & 0xFFL) << 24 | (array[offset + 5] & 0xFFL) << 16 | (array[offset + 6] & 0xFFL) << 8 | (array[offset + 7] & 0xFFL) << 0;
        }
        
        public final void putInt16(final byte[] buffer, final int offset, final int value) {
            buffer[offset + 0] = (byte)(value >> 8);
            buffer[offset + 1] = (byte)(value >> 0);
        }
        
        public final void putInt32(final byte[] buffer, final int offset, final int value) {
            buffer[offset + 0] = (byte)(value >> 24);
            buffer[offset + 1] = (byte)(value >> 16);
            buffer[offset + 2] = (byte)(value >> 8);
            buffer[offset + 3] = (byte)(value >> 0);
        }
        
        public final void putInt64(final byte[] buffer, final int offset, final long value) {
            buffer[offset + 0] = (byte)(value >> 56);
            buffer[offset + 1] = (byte)(value >> 48);
            buffer[offset + 2] = (byte)(value >> 40);
            buffer[offset + 3] = (byte)(value >> 32);
            buffer[offset + 4] = (byte)(value >> 24);
            buffer[offset + 5] = (byte)(value >> 16);
            buffer[offset + 6] = (byte)(value >> 8);
            buffer[offset + 7] = (byte)(value >> 0);
        }
    }
    
    private static final class LE32ArrayIO extends LittleEndianArrayIO
    {
        public final long getAddress(final byte[] buffer, final int offset) {
            return this.getInt32(buffer, offset) & 0xFFFFFFFFL;
        }
        
        public final void putAddress(final byte[] buffer, final int offset, final long value) {
            this.putInt32(buffer, offset, (int)value);
        }
    }
    
    private static final class LE64ArrayIO extends LittleEndianArrayIO
    {
        public final long getAddress(final byte[] buffer, final int offset) {
            return this.getInt64(buffer, offset);
        }
        
        public final void putAddress(final byte[] buffer, final int offset, final long value) {
            this.putInt64(buffer, offset, value);
        }
    }
    
    private static final class BE32ArrayIO extends BigEndianArrayIO
    {
        public final long getAddress(final byte[] buffer, final int offset) {
            return this.getInt32(buffer, offset) & 0xFFFFFFFFL;
        }
        
        public final void putAddress(final byte[] buffer, final int offset, final long value) {
            this.putInt32(buffer, offset, (int)value);
        }
    }
    
    private static final class BE64ArrayIO extends BigEndianArrayIO
    {
        public final long getAddress(final byte[] buffer, final int offset) {
            return this.getInt64(buffer, offset);
        }
        
        public final void putAddress(final byte[] buffer, final int offset, final long value) {
            this.putInt64(buffer, offset, value);
        }
    }
}
