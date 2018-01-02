// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyClass;
import org.jruby.Ruby;

public abstract class InvalidMemoryIO implements MemoryIO
{
    protected final Ruby runtime;
    private final String message;
    
    public InvalidMemoryIO(final Ruby runtime) {
        this(runtime, "Invalid memory access");
    }
    
    public InvalidMemoryIO(final Ruby runtime, final String message) {
        this.runtime = runtime;
        this.message = message;
    }
    
    protected RubyClass getErrorClass(final Ruby runtime) {
        return runtime.getRuntimeError();
    }
    
    protected RaiseException ex() {
        return new RaiseException(this.runtime, this.getErrorClass(this.runtime), this.message, true);
    }
    
    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
    
    public MemoryIO slice(final long offset) {
        return this;
    }
    
    public MemoryIO slice(final long offset, final long size) {
        return this;
    }
    
    public MemoryIO dup() {
        return this;
    }
    
    public ByteBuffer asByteBuffer() {
        throw this.ex();
    }
    
    public final byte getByte(final long offset) {
        throw this.ex();
    }
    
    public final short getShort(final long offset) {
        throw this.ex();
    }
    
    public final int getInt(final long offset) {
        throw this.ex();
    }
    
    public final long getLong(final long offset) {
        throw this.ex();
    }
    
    public final long getNativeLong(final long offset) {
        throw this.ex();
    }
    
    public final float getFloat(final long offset) {
        throw this.ex();
    }
    
    public final double getDouble(final long offset) {
        throw this.ex();
    }
    
    public final DirectMemoryIO getMemoryIO(final long offset) {
        throw this.ex();
    }
    
    public final long getAddress(final long offset) {
        throw this.ex();
    }
    
    public final void putByte(final long offset, final byte value) {
        throw this.ex();
    }
    
    public final void putShort(final long offset, final short value) {
        throw this.ex();
    }
    
    public final void putInt(final long offset, final int value) {
        throw this.ex();
    }
    
    public final void putLong(final long offset, final long value) {
        throw this.ex();
    }
    
    public final void putNativeLong(final long offset, final long value) {
        throw this.ex();
    }
    
    public final void putFloat(final long offset, final float value) {
        throw this.ex();
    }
    
    public final void putDouble(final long offset, final double value) {
        throw this.ex();
    }
    
    public final void putMemoryIO(final long offset, final MemoryIO value) {
        throw this.ex();
    }
    
    public final void putAddress(final long offset, final long value) {
        throw this.ex();
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        throw this.ex();
    }
    
    public final void put(final long offset, final byte[] src, final int off, final int len) {
        throw this.ex();
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        throw this.ex();
    }
    
    public final void put(final long offset, final short[] src, final int off, final int len) {
        throw this.ex();
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        throw this.ex();
    }
    
    public final void put(final long offset, final int[] src, final int off, final int len) {
        throw this.ex();
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        throw this.ex();
    }
    
    public final void put(final long offset, final long[] src, final int off, final int len) {
        throw this.ex();
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        throw this.ex();
    }
    
    public final void put(final long offset, final float[] src, final int off, final int len) {
        throw this.ex();
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        throw this.ex();
    }
    
    public final void put(final long offset, final double[] src, final int off, final int len) {
        throw this.ex();
    }
    
    public final int indexOf(final long offset, final byte value) {
        throw this.ex();
    }
    
    public final int indexOf(final long offset, final byte value, final int maxlen) {
        throw this.ex();
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        throw this.ex();
    }
    
    public final void clear() {
        throw this.ex();
    }
    
    public byte[] getZeroTerminatedByteArray(final long offset) {
        throw this.ex();
    }
    
    public byte[] getZeroTerminatedByteArray(final long offset, final int maxlen) {
        throw this.ex();
    }
    
    public void putZeroTerminatedByteArray(final long offset, final byte[] bytes, final int off, final int len) {
        throw this.ex();
    }
}
