// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider;

import java.nio.charset.Charset;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;

public final class NullMemoryIO extends AbstractMemoryIO
{
    private static final String msg = "Attempted access to a NULL memory address";
    public static final MemoryIO INSTANCE;
    
    private static final NullPointerException npe() {
        return new NullPointerException("Attempted access to a NULL memory address");
    }
    
    public final byte getByte(final long offset) {
        throw npe();
    }
    
    public final short getShort(final long offset) {
        throw npe();
    }
    
    public final int getInt(final long offset) {
        throw npe();
    }
    
    public final long getLong(final long offset) {
        throw npe();
    }
    
    public final float getFloat(final long offset) {
        throw npe();
    }
    
    public final double getDouble(final long offset) {
        throw npe();
    }
    
    public final void putByte(final long offset, final byte value) {
        throw npe();
    }
    
    public final void putShort(final long offset, final short value) {
        throw npe();
    }
    
    public final void putInt(final long offset, final int value) {
        throw npe();
    }
    
    public final void putLong(final long offset, final long value) {
        throw npe();
    }
    
    public final void putFloat(final long offset, final float value) {
        throw npe();
    }
    
    public final void putDouble(final long offset, final double value) {
        throw npe();
    }
    
    public final void get(final long offset, final byte[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void put(final long offset, final byte[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void get(final long offset, final short[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void put(final long offset, final short[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void get(final long offset, final int[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void put(final long offset, final int[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void get(final long offset, final long[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void put(final long offset, final long[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void get(final long offset, final float[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void put(final long offset, final float[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void get(final long offset, final double[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final void put(final long offset, final double[] dst, final int off, final int len) {
        throw npe();
    }
    
    public final MemoryIO getMemoryIO(final long offset) {
        throw npe();
    }
    
    public final MemoryIO getMemoryIO(final long offset, final long size) {
        throw npe();
    }
    
    public final Pointer getPointer(final long offset) {
        throw npe();
    }
    
    public final void putPointer(final long offset, final Pointer value) {
        throw npe();
    }
    
    public String getString(final long offset) {
        throw npe();
    }
    
    public String getString(final long offset, final int maxLength, final Charset cs) {
        throw npe();
    }
    
    public void putString(final long offset, final String string, final int maxLength, final Charset cs) {
        throw npe();
    }
    
    public final int indexOf(final long offset, final byte value, final int maxlen) {
        throw npe();
    }
    
    public final boolean isDirect() {
        return true;
    }
    
    public long address() {
        return 0L;
    }
    
    public final void setMemory(final long offset, final long size, final byte value) {
        throw npe();
    }
    
    static {
        INSTANCE = new NullMemoryIO();
    }
}
