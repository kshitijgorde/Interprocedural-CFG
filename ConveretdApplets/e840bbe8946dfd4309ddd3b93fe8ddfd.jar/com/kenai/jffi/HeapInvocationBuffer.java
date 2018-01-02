// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.nio.ByteOrder;
import java.nio.Buffer;

public final class HeapInvocationBuffer implements InvocationBuffer
{
    private static final int FFI_SIZEOF_ARG;
    private static final int PARAM_SIZE = 8;
    private static final Encoder encoder;
    private final CallInfo info;
    private final byte[] buffer;
    private ObjectBuffer objectBuffer;
    private int paramOffset;
    private int paramIndex;
    
    public HeapInvocationBuffer(final CallInfo info) {
        this.objectBuffer = null;
        this.paramOffset = 0;
        this.paramIndex = 0;
        this.info = info;
        this.buffer = new byte[HeapInvocationBuffer.encoder.getBufferSize(info)];
    }
    
    public HeapInvocationBuffer(final Function function) {
        this.objectBuffer = null;
        this.paramOffset = 0;
        this.paramIndex = 0;
        this.info = function;
        this.buffer = new byte[HeapInvocationBuffer.encoder.getBufferSize(function)];
    }
    
    byte[] array() {
        return this.buffer;
    }
    
    ObjectBuffer objectBuffer() {
        return this.objectBuffer;
    }
    
    public final void putByte(final int value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putByte(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    public final void putShort(final int value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putShort(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    public final void putInt(final int value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putInt(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    public final void putLong(final long value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putLong(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    public final void putFloat(final float value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putFloat(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    public final void putDouble(final double value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putDouble(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    public final void putAddress(final long value) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, value);
        ++this.paramIndex;
    }
    
    private final ObjectBuffer getObjectBuffer() {
        if (this.objectBuffer == null) {
            this.objectBuffer = new ObjectBuffer();
        }
        return this.objectBuffer;
    }
    
    public final void putArray(final byte[] array, final int offset, final int length, final int flags) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putArray(this.paramIndex++, array, offset, length, flags);
    }
    
    public final void putArray(final short[] array, final int offset, final int length, final int flags) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putArray(this.paramIndex++, array, offset, length, flags);
    }
    
    public final void putArray(final int[] array, final int offset, final int length, final int flags) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putArray(this.paramIndex++, array, offset, length, flags);
    }
    
    public final void putArray(final long[] array, final int offset, final int length, final int flags) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putArray(this.paramIndex++, array, offset, length, flags);
    }
    
    public final void putArray(final float[] array, final int offset, final int length, final int flags) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putArray(this.paramIndex++, array, offset, length, flags);
    }
    
    public final void putArray(final double[] array, final int offset, final int length, final int flags) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putArray(this.paramIndex++, array, offset, length, flags);
    }
    
    public final void putDirectBuffer(final Buffer value, final int offset, final int length) {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putDirectBuffer(this.paramIndex++, value, offset, length);
    }
    
    public final void putStruct(final byte[] struct, final int offset) {
        final Type type = this.info.getParameterType(this.paramIndex);
        if (HeapInvocationBuffer.encoder.isRaw()) {
            this.paramOffset = FFI_ALIGN(this.paramOffset, type.alignment());
            System.arraycopy(struct, offset, this.buffer, this.paramOffset, type.size());
            this.paramOffset = FFI_ALIGN(this.paramOffset + type.size(), HeapInvocationBuffer.FFI_SIZEOF_ARG);
        }
        else {
            this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
            this.getObjectBuffer().putArray(this.paramIndex, struct, offset, type.size(), 1);
        }
        ++this.paramIndex;
    }
    
    public final void putStruct(final long struct) {
        final Type type = this.info.getParameterType(this.paramIndex);
        if (HeapInvocationBuffer.encoder.isRaw()) {
            this.paramOffset = FFI_ALIGN(this.paramOffset, type.alignment());
            MemoryIO.getInstance().getByteArray(struct, this.buffer, this.paramOffset, type.size());
            this.paramOffset = FFI_ALIGN(this.paramOffset + type.size(), HeapInvocationBuffer.FFI_SIZEOF_ARG);
        }
        else {
            this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, struct);
        }
        ++this.paramIndex;
    }
    
    public final void putJNIEnvironment() {
        this.paramOffset = HeapInvocationBuffer.encoder.putAddress(this.buffer, this.paramOffset, 0L);
        this.getObjectBuffer().putJNI(this.paramIndex++, 16777216);
    }
    
    private static final Encoder getEncoder() {
        if (Platform.getPlatform().getCPU() == Platform.CPU.I386) {
            return Foreign.getInstance().isRawParameterPackingEnabled() ? newI386RawEncoder() : newLE32Encoder();
        }
        if (Platform.getPlatform().addressSize() == 64) {
            return ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? newBE64Encoder() : newLE64Encoder();
        }
        return ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? newBE32Encoder() : newLE32Encoder();
    }
    
    private static final Encoder newI386RawEncoder() {
        return new I386RawEncoder();
    }
    
    private static final Encoder newLE32Encoder() {
        return new DefaultEncoder(LE32ArrayIO.INSTANCE);
    }
    
    private static final Encoder newLE64Encoder() {
        return new DefaultEncoder(LE64ArrayIO.INSTANCE);
    }
    
    private static final Encoder newBE32Encoder() {
        return new DefaultEncoder(BE32ArrayIO.INSTANCE);
    }
    
    private static final Encoder newBE64Encoder() {
        return new DefaultEncoder(BE64ArrayIO.INSTANCE);
    }
    
    private static final int FFI_ALIGN(final int v, final int a) {
        return (v - 1 | a - 1) + 1;
    }
    
    static {
        FFI_SIZEOF_ARG = Platform.getPlatform().addressSize() / 8;
        encoder = getEncoder();
    }
    
    private abstract static class Encoder
    {
        public abstract boolean isRaw();
        
        public abstract int getBufferSize(final CallInfo p0);
        
        public abstract int putByte(final byte[] p0, final int p1, final int p2);
        
        public abstract int putShort(final byte[] p0, final int p1, final int p2);
        
        public abstract int putInt(final byte[] p0, final int p1, final int p2);
        
        public abstract int putLong(final byte[] p0, final int p1, final long p2);
        
        public abstract int putFloat(final byte[] p0, final int p1, final float p2);
        
        public abstract int putDouble(final byte[] p0, final int p1, final double p2);
        
        public abstract int putAddress(final byte[] p0, final int p1, final long p2);
    }
    
    private static final class I386RawEncoder extends Encoder
    {
        private static final ArrayIO IO;
        
        public final boolean isRaw() {
            return true;
        }
        
        public final int getBufferSize(final CallInfo info) {
            return info.getRawParameterSize();
        }
        
        public final int putByte(final byte[] buffer, final int offset, final int value) {
            I386RawEncoder.IO.putByte(buffer, offset, value);
            return offset + 4;
        }
        
        public final int putShort(final byte[] buffer, final int offset, final int value) {
            I386RawEncoder.IO.putShort(buffer, offset, value);
            return offset + 4;
        }
        
        public final int putInt(final byte[] buffer, final int offset, final int value) {
            I386RawEncoder.IO.putInt(buffer, offset, value);
            return offset + 4;
        }
        
        public final int putLong(final byte[] buffer, final int offset, final long value) {
            I386RawEncoder.IO.putLong(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putFloat(final byte[] buffer, final int offset, final float value) {
            I386RawEncoder.IO.putFloat(buffer, offset, value);
            return offset + 4;
        }
        
        public final int putDouble(final byte[] buffer, final int offset, final double value) {
            I386RawEncoder.IO.putDouble(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putAddress(final byte[] buffer, final int offset, final long value) {
            I386RawEncoder.IO.putAddress(buffer, offset, value);
            return offset + 4;
        }
        
        static {
            IO = LE32ArrayIO.INSTANCE;
        }
    }
    
    private static final class DefaultEncoder extends Encoder
    {
        private final ArrayIO io;
        
        public DefaultEncoder(final ArrayIO io) {
            this.io = io;
        }
        
        public final boolean isRaw() {
            return false;
        }
        
        public final int getBufferSize(final CallInfo info) {
            return info.getParameterCount() * 8;
        }
        
        public final int putByte(final byte[] buffer, final int offset, final int value) {
            this.io.putByte(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putShort(final byte[] buffer, final int offset, final int value) {
            this.io.putShort(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putInt(final byte[] buffer, final int offset, final int value) {
            this.io.putInt(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putLong(final byte[] buffer, final int offset, final long value) {
            this.io.putLong(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putFloat(final byte[] buffer, final int offset, final float value) {
            this.io.putFloat(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putDouble(final byte[] buffer, final int offset, final double value) {
            this.io.putDouble(buffer, offset, value);
            return offset + 8;
        }
        
        public final int putAddress(final byte[] buffer, final int offset, final long value) {
            this.io.putAddress(buffer, offset, value);
            return offset + 8;
        }
    }
    
    private abstract static class ArrayIO
    {
        public abstract void putByte(final byte[] p0, final int p1, final int p2);
        
        public abstract void putShort(final byte[] p0, final int p1, final int p2);
        
        public abstract void putInt(final byte[] p0, final int p1, final int p2);
        
        public abstract void putLong(final byte[] p0, final int p1, final long p2);
        
        public final void putFloat(final byte[] buffer, final int offset, final float value) {
            this.putInt(buffer, offset, Float.floatToRawIntBits(value));
        }
        
        public final void putDouble(final byte[] buffer, final int offset, final double value) {
            this.putLong(buffer, offset, Double.doubleToRawLongBits(value));
        }
        
        public abstract void putAddress(final byte[] p0, final int p1, final long p2);
    }
    
    private abstract static class LittleEndianArrayIO extends ArrayIO
    {
        public final void putByte(final byte[] buffer, final int offset, final int value) {
            buffer[offset] = (byte)value;
        }
        
        public final void putShort(final byte[] buffer, final int offset, final int value) {
            buffer[offset] = (byte)value;
            buffer[offset + 1] = (byte)(value >> 8);
        }
        
        public final void putInt(final byte[] buffer, final int offset, final int value) {
            buffer[offset] = (byte)value;
            buffer[offset + 1] = (byte)(value >> 8);
            buffer[offset + 2] = (byte)(value >> 16);
            buffer[offset + 3] = (byte)(value >> 24);
        }
        
        public final void putLong(final byte[] buffer, final int offset, final long value) {
            buffer[offset] = (byte)value;
            buffer[offset + 1] = (byte)(value >> 8);
            buffer[offset + 2] = (byte)(value >> 16);
            buffer[offset + 3] = (byte)(value >> 24);
            buffer[offset + 4] = (byte)(value >> 32);
            buffer[offset + 5] = (byte)(value >> 40);
            buffer[offset + 6] = (byte)(value >> 48);
            buffer[offset + 7] = (byte)(value >> 56);
        }
    }
    
    private static final class LE32ArrayIO extends LittleEndianArrayIO
    {
        static final ArrayIO INSTANCE;
        
        public final void putAddress(final byte[] buffer, final int offset, final long value) {
            buffer[offset] = (byte)value;
            buffer[offset + 1] = (byte)(value >> 8);
            buffer[offset + 2] = (byte)(value >> 16);
            buffer[offset + 3] = (byte)(value >> 24);
        }
        
        static {
            INSTANCE = new LE32ArrayIO();
        }
    }
    
    private static final class LE64ArrayIO extends LittleEndianArrayIO
    {
        static final ArrayIO INSTANCE;
        
        public final void putAddress(final byte[] buffer, final int offset, final long value) {
            this.putLong(buffer, offset, value);
        }
        
        static {
            INSTANCE = new LE64ArrayIO();
        }
    }
    
    private abstract static class BigEndianArrayIO extends ArrayIO
    {
        public final void putByte(final byte[] buffer, final int offset, final int value) {
            buffer[offset] = (byte)value;
        }
        
        public final void putShort(final byte[] buffer, final int offset, final int value) {
            buffer[offset + 0] = (byte)(value >> 8);
            buffer[offset + 1] = (byte)value;
        }
        
        public final void putInt(final byte[] buffer, final int offset, final int value) {
            buffer[offset + 0] = (byte)(value >> 24);
            buffer[offset + 1] = (byte)(value >> 16);
            buffer[offset + 2] = (byte)(value >> 8);
            buffer[offset + 3] = (byte)value;
        }
        
        public final void putLong(final byte[] buffer, final int offset, final long value) {
            buffer[offset + 0] = (byte)(value >> 56);
            buffer[offset + 1] = (byte)(value >> 48);
            buffer[offset + 2] = (byte)(value >> 40);
            buffer[offset + 3] = (byte)(value >> 32);
            buffer[offset + 4] = (byte)(value >> 24);
            buffer[offset + 5] = (byte)(value >> 16);
            buffer[offset + 6] = (byte)(value >> 8);
            buffer[offset + 7] = (byte)value;
        }
    }
    
    private static final class BE32ArrayIO extends BigEndianArrayIO
    {
        static final ArrayIO INSTANCE;
        
        public void putAddress(final byte[] buffer, final int offset, final long value) {
            buffer[offset + 0] = (byte)(value >> 24);
            buffer[offset + 1] = (byte)(value >> 16);
            buffer[offset + 2] = (byte)(value >> 8);
            buffer[offset + 3] = (byte)value;
        }
        
        static {
            INSTANCE = new BE32ArrayIO();
        }
    }
    
    private static final class BE64ArrayIO extends BigEndianArrayIO
    {
        static final ArrayIO INSTANCE;
        
        public void putAddress(final byte[] buffer, final int offset, final long value) {
            this.putLong(buffer, offset, value);
        }
        
        static {
            INSTANCE = new BE64ArrayIO();
        }
    }
}
