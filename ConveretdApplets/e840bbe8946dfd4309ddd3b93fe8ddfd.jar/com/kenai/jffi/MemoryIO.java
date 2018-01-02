// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public abstract class MemoryIO
{
    final Foreign foreign;
    private static final long ADDRESS_MASK;
    
    public static MemoryIO getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private MemoryIO() {
        this.foreign = Foreign.getInstance();
    }
    
    private static final MemoryIO newMemoryIO() {
        try {
            return (!Boolean.getBoolean("jffi.unsafe.disabled") && isUnsafeAvailable()) ? newUnsafeImpl() : newNativeImpl();
        }
        catch (Throwable t) {
            return newNativeImpl();
        }
    }
    
    private static final MemoryIO newNativeImpl() {
        return (Platform.getPlatform().addressSize() == 32) ? newNativeImpl32() : newNativeImpl64();
    }
    
    private static final MemoryIO newNativeImpl32() {
        return new NativeImpl32();
    }
    
    private static final MemoryIO newNativeImpl64() {
        return new NativeImpl64();
    }
    
    private static final MemoryIO newUnsafeImpl() {
        return (Platform.getPlatform().addressSize() == 32) ? newUnsafeImpl32() : newUnsafeImpl64();
    }
    
    private static final MemoryIO newUnsafeImpl32() {
        return new UnsafeImpl32();
    }
    
    private static final MemoryIO newUnsafeImpl64() {
        return new UnsafeImpl64();
    }
    
    public abstract byte getByte(final long p0);
    
    public abstract short getShort(final long p0);
    
    public abstract int getInt(final long p0);
    
    public abstract long getLong(final long p0);
    
    public abstract float getFloat(final long p0);
    
    public abstract double getDouble(final long p0);
    
    public abstract long getAddress(final long p0);
    
    public abstract void putByte(final long p0, final byte p1);
    
    public abstract void putShort(final long p0, final short p1);
    
    public abstract void putInt(final long p0, final int p1);
    
    public abstract void putLong(final long p0, final long p1);
    
    public abstract void putFloat(final long p0, final float p1);
    
    public abstract void putDouble(final long p0, final double p1);
    
    public abstract void putAddress(final long p0, final long p1);
    
    public final void copyMemory(final long src, final long dst, final long size) {
        if (dst + size < src || src + size < dst) {
            this._copyMemory(src, dst, size);
        }
        else {
            this.foreign.memmove(dst, src, size);
        }
    }
    
    abstract void _copyMemory(final long p0, final long p1, final long p2);
    
    public abstract void setMemory(final long p0, final long p1, final byte p2);
    
    public final void memcpy(final long dst, final long src, final long size) {
        this._copyMemory(src, dst, size);
    }
    
    public final void memmove(final long dst, final long src, final long size) {
        this.foreign.memmove(dst, src, size);
    }
    
    public final void memset(final long address, final int value, final long size) {
        this.setMemory(address, size, (byte)value);
    }
    
    public final void putByteArray(final long address, final byte[] data, final int offset, final int length) {
        this.foreign.putByteArray(address, data, offset, length);
    }
    
    public final void getByteArray(final long address, final byte[] data, final int offset, final int length) {
        this.foreign.getByteArray(address, data, offset, length);
    }
    
    public final void putCharArray(final long address, final char[] data, final int offset, final int length) {
        this.foreign.putCharArray(address, data, offset, length);
    }
    
    public final void getCharArray(final long address, final char[] data, final int offset, final int length) {
        this.foreign.getCharArray(address, data, offset, length);
    }
    
    public final void putShortArray(final long address, final short[] data, final int offset, final int length) {
        this.foreign.putShortArray(address, data, offset, length);
    }
    
    public final void getShortArray(final long address, final short[] data, final int offset, final int length) {
        this.foreign.getShortArray(address, data, offset, length);
    }
    
    public final void putIntArray(final long address, final int[] data, final int offset, final int length) {
        this.foreign.putIntArray(address, data, offset, length);
    }
    
    public final void getIntArray(final long address, final int[] data, final int offset, final int length) {
        this.foreign.getIntArray(address, data, offset, length);
    }
    
    public final void putLongArray(final long address, final long[] data, final int offset, final int length) {
        this.foreign.putLongArray(address, data, offset, length);
    }
    
    public final void getLongArray(final long address, final long[] data, final int offset, final int length) {
        this.foreign.getLongArray(address, data, offset, length);
    }
    
    public final void putFloatArray(final long address, final float[] data, final int offset, final int length) {
        this.foreign.putFloatArray(address, data, offset, length);
    }
    
    public final void getFloatArray(final long address, final float[] data, final int offset, final int length) {
        this.foreign.getFloatArray(address, data, offset, length);
    }
    
    public final void putDoubleArray(final long address, final double[] data, final int offset, final int length) {
        this.foreign.putDoubleArray(address, data, offset, length);
    }
    
    public final void getDoubleArray(final long address, final double[] data, final int offset, final int length) {
        this.foreign.getDoubleArray(address, data, offset, length);
    }
    
    public final long allocateMemory(final long size, final boolean clear) {
        return this.foreign.allocateMemory(size, clear) & MemoryIO.ADDRESS_MASK;
    }
    
    public final void freeMemory(final long address) {
        this.foreign.freeMemory(address);
    }
    
    public final long getStringLength(final long address) {
        return this.foreign.strlen(address);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long address) {
        return this.foreign.getZeroTerminatedByteArray(address);
    }
    
    public final byte[] getZeroTerminatedByteArray(final long address, final int maxlen) {
        return this.foreign.getZeroTerminatedByteArray(address, maxlen);
    }
    
    @Deprecated
    public final byte[] getZeroTerminatedByteArray(final long address, final long maxlen) {
        return this.foreign.getZeroTerminatedByteArray(address, (int)maxlen);
    }
    
    public final void putZeroTerminatedByteArray(final long address, final byte[] data, final int offset, final int length) {
        this.foreign.putZeroTerminatedByteArray(address, data, offset, length);
    }
    
    public final long indexOf(final long address, final byte value) {
        final long location = this.foreign.memchr(address, value, 2147483647L);
        return (location != 0L) ? (location - address) : -1L;
    }
    
    public final long indexOf(final long address, final byte value, final int maxlen) {
        final long location = this.foreign.memchr(address, value, maxlen);
        return (location != 0L) ? (location - address) : -1L;
    }
    
    public final ByteBuffer newDirectByteBuffer(final long address, final int capacity) {
        return this.foreign.newDirectByteBuffer(address, capacity);
    }
    
    public final long getDirectBufferAddress(final Buffer buffer) {
        return this.foreign.getDirectBufferAddress(buffer);
    }
    
    private static final void verifyAccessor(final Class unsafeClass, final Class primitive) throws NoSuchMethodException {
        final String primitiveName = primitive.getSimpleName();
        final String typeName = primitiveName.substring(0, 1).toUpperCase() + primitiveName.substring(1);
        final Method get = unsafeClass.getDeclaredMethod("get" + typeName, Long.TYPE);
        if (!get.getReturnType().equals(primitive)) {
            throw new NoSuchMethodException("Incorrect return type for " + get.getName());
        }
        unsafeClass.getDeclaredMethod("put" + typeName, Long.TYPE, primitive);
    }
    
    static final boolean isUnsafeAvailable() {
        try {
            final Class sunClass = Class.forName("sun.misc.Unsafe");
            final Class[] arr$;
            final Class[] primitiveTypes = arr$ = new Class[] { Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE };
            for (final Class type : arr$) {
                verifyAccessor(sunClass, type);
            }
            sunClass.getDeclaredMethod("getAddress", Long.TYPE);
            sunClass.getDeclaredMethod("putAddress", Long.TYPE, Long.TYPE);
            sunClass.getDeclaredMethod("allocateMemory", Long.TYPE);
            sunClass.getDeclaredMethod("freeMemory", Long.TYPE);
            return true;
        }
        catch (Throwable ex) {
            return false;
        }
    }
    
    static {
        ADDRESS_MASK = Platform.getPlatform().addressMask();
    }
    
    private static final class SingletonHolder
    {
        private static final MemoryIO INSTANCE;
        
        static {
            INSTANCE = newMemoryIO();
        }
    }
    
    private abstract static class NativeImpl extends MemoryIO
    {
        private NativeImpl() {
            super(null);
        }
        
        public final byte getByte(final long address) {
            return this.foreign.getByte(address);
        }
        
        public final short getShort(final long address) {
            return this.foreign.getShort(address);
        }
        
        public final int getInt(final long address) {
            return this.foreign.getInt(address);
        }
        
        public final long getLong(final long address) {
            return this.foreign.getLong(address);
        }
        
        public final float getFloat(final long address) {
            return this.foreign.getFloat(address);
        }
        
        public final double getDouble(final long address) {
            return this.foreign.getDouble(address);
        }
        
        public final void putByte(final long address, final byte value) {
            this.foreign.putByte(address, value);
        }
        
        public final void putShort(final long address, final short value) {
            this.foreign.putShort(address, value);
        }
        
        public final void putInt(final long address, final int value) {
            this.foreign.putInt(address, value);
        }
        
        public final void putLong(final long address, final long value) {
            this.foreign.putLong(address, value);
        }
        
        public final void putFloat(final long address, final float value) {
            this.foreign.putFloat(address, value);
        }
        
        public final void putDouble(final long address, final double value) {
            this.foreign.putDouble(address, value);
        }
        
        public final void setMemory(final long address, final long size, final byte value) {
            this.foreign.setMemory(address, size, value);
        }
        
        public final void _copyMemory(final long src, final long dst, final long size) {
            this.foreign.copyMemory(src, dst, size);
        }
    }
    
    private static final class NativeImpl32 extends NativeImpl
    {
        public final long getAddress(final long address) {
            return this.foreign.getInt(address) & MemoryIO.ADDRESS_MASK;
        }
        
        public final void putAddress(final long address, final long value) {
            this.foreign.putInt(address, (int)value);
        }
    }
    
    private static final class NativeImpl64 extends NativeImpl
    {
        public final long getAddress(final long address) {
            return this.foreign.getLong(address);
        }
        
        public final void putAddress(final long address, final long value) {
            this.foreign.putLong(address, value);
        }
    }
    
    private abstract static class UnsafeImpl extends MemoryIO
    {
        protected static Unsafe unsafe;
        
        private UnsafeImpl() {
            super(null);
        }
        
        private static final Object getUnsafe() {
            try {
                final Class sunUnsafe = Class.forName("sun.misc.Unsafe");
                final Field f = sunUnsafe.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                return f.get(sunUnsafe);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        
        public final byte getByte(final long address) {
            return UnsafeImpl.unsafe.getByte(address);
        }
        
        public final short getShort(final long address) {
            return UnsafeImpl.unsafe.getShort(address);
        }
        
        public final int getInt(final long address) {
            return UnsafeImpl.unsafe.getInt(address);
        }
        
        public final long getLong(final long address) {
            return UnsafeImpl.unsafe.getLong(address);
        }
        
        public final float getFloat(final long address) {
            return UnsafeImpl.unsafe.getFloat(address);
        }
        
        public final double getDouble(final long address) {
            return UnsafeImpl.unsafe.getDouble(address);
        }
        
        public final void putByte(final long address, final byte value) {
            UnsafeImpl.unsafe.putByte(address, value);
        }
        
        public final void putShort(final long address, final short value) {
            UnsafeImpl.unsafe.putShort(address, value);
        }
        
        public final void putInt(final long address, final int value) {
            UnsafeImpl.unsafe.putInt(address, value);
        }
        
        public final void putLong(final long address, final long value) {
            UnsafeImpl.unsafe.putLong(address, value);
        }
        
        public final void putFloat(final long address, final float value) {
            UnsafeImpl.unsafe.putFloat(address, value);
        }
        
        public final void putDouble(final long address, final double value) {
            UnsafeImpl.unsafe.putDouble(address, value);
        }
        
        public final void _copyMemory(final long src, final long dst, final long size) {
            UnsafeImpl.unsafe.copyMemory(src, dst, size);
        }
        
        public final void setMemory(final long src, final long size, final byte value) {
            UnsafeImpl.unsafe.setMemory(src, size, value);
        }
        
        static {
            UnsafeImpl.unsafe = Unsafe.class.cast(getUnsafe());
        }
    }
    
    private static final class UnsafeImpl32 extends UnsafeImpl
    {
        public final long getAddress(final long address) {
            return UnsafeImpl32.unsafe.getInt(address) & MemoryIO.ADDRESS_MASK;
        }
        
        public final void putAddress(final long address, final long value) {
            UnsafeImpl32.unsafe.putInt(address, (int)value);
        }
    }
    
    private static final class UnsafeImpl64 extends UnsafeImpl
    {
        public final long getAddress(final long address) {
            return UnsafeImpl64.unsafe.getLong(address);
        }
        
        public final void putAddress(final long address, final long value) {
            UnsafeImpl64.unsafe.putLong(address, value);
        }
    }
}
