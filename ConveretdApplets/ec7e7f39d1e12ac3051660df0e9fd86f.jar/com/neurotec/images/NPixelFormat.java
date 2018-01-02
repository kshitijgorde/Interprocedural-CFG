// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public final class NPixelFormat
{
    private int value;
    static final NPixelFormatLibrary LIBRARY;
    public static final NPixelFormat MONOCHROME;
    public static final NPixelFormat GRAYSCALE;
    static final NPixelFormat GRAYSCALE_W;
    public static final NPixelFormat RGB;
    static final NPixelFormat RGB_A;
    static final NPixelFormat RGB_PA;
    static final NPixelFormat INDEXED_1;
    static final NPixelFormat INDEXED_4;
    static final NPixelFormat INDEXED_8;
    
    public NPixelFormat(final int value) {
        this.value = value;
    }
    
    public static int calcRowSize(final int bitCount, final int length, final int alignment) {
        if (bitCount < 0) {
            throw new IllegalArgumentException("bitCount is less than 0");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length is less than 0");
        }
        if (alignment < 0) {
            throw new IllegalArgumentException("alignment is less than 0");
        }
        return (bitCount * length + (alignment << 3) - 1) / (alignment << 3) * alignment;
    }
    
    public static long calcRowLongSize(final int bitCount, final int length, final int alignment) {
        if (bitCount < 0) {
            throw new IllegalArgumentException("bitCount is less than 0");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length is less than 0");
        }
        if (alignment < 0) {
            throw new IllegalArgumentException("alignment is less than 0");
        }
        return (bitCount * length + (alignment << 3) - 1L) / (alignment << 3) * alignment;
    }
    
    public static int calcRowSize(final int bitCount, final int length) {
        return calcRowSize(bitCount, length, 1);
    }
    
    public static long calcRowLongSize(final int bitCount, final int length) {
        return calcRowLongSize(bitCount, length, 1);
    }
    
    public static boolean isValid(final NPixelFormat value) {
        return NPixelFormat.LIBRARY.NPixelFormatIsValid(value.value) != 0;
    }
    
    private int getBitsPerIndexRaw() {
        return this.value >> 24 & 0xF;
    }
    
    public int getRowSize(final int length, final int alignment) {
        return calcRowSize(this.getBitsPerPixel(), length, alignment);
    }
    
    public long getRowLongSize(final int length, final int alignment) {
        return calcRowLongSize(this.getBitsPerPixel(), length, alignment);
    }
    
    public int getRowSize(final int length) {
        return this.getRowSize(length, 1);
    }
    
    public long getRowLongSize(final int length) {
        return this.getRowLongSize(length, 1);
    }
    
    public int getBitsPerPixel() {
        return NPixelFormat.LIBRARY.NPixelFormatGetBitsPerPixelFunc(this.value);
    }
    
    boolean isIndexed() {
        return this.getBitsPerIndexRaw() != 0;
    }
    
    public final int getValue() {
        return this.value;
    }
    
    public int hashCode() {
        return this.value;
    }
    
    public boolean equals(final Object obj) {
        if (obj instanceof NPixelFormat) {
            final NPixelFormat other = (NPixelFormat)obj;
            if (this.value == other.value) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        return "NPixelFormat [value=" + this.value + "]";
    }
    
    static {
        LIBRARY = (NPixelFormatLibrary)Native.loadLibrary("NImages", NPixelFormatLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        MONOCHROME = new NPixelFormat(4097);
        GRAYSCALE = new NPixelFormat(3149825);
        GRAYSCALE_W = new NPixelFormat(4198401);
        RGB = new NPixelFormat(3158019);
        RGB_A = new NPixelFormat(3162371);
        RGB_PA = new NPixelFormat(3162627);
        INDEXED_1 = new NPixelFormat(19935235);
        INDEXED_4 = new NPixelFormat(53489667);
        INDEXED_8 = new NPixelFormat(70266883);
    }
    
    interface NPixelFormatLibrary extends NLibrary
    {
        int NPixelFormatGetBitsPerPixelFunc(final int p0);
        
        int NPixelFormatIsValid(final int p0);
    }
}
