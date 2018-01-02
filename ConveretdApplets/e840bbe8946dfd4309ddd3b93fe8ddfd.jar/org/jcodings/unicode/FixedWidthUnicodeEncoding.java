// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.unicode;

import org.jcodings.IntHolder;

public abstract class FixedWidthUnicodeEncoding extends UnicodeEncoding
{
    protected final int shift;
    
    protected FixedWidthUnicodeEncoding(final String name, final int width) {
        super(name, width, width, (int[])null);
        this.shift = log2(width);
    }
    
    public final int length(final byte c) {
        return this.minLength;
    }
    
    public final int length(final byte[] bytes, final int p, final int end) {
        return this.minLength;
    }
    
    public final int strLength(final byte[] bytes, final int p, final int end) {
        return end - p >>> this.shift;
    }
    
    public final int strCodeAt(final byte[] bytes, final int p, final int end, final int index) {
        return this.mbcToCode(bytes, p + (index << this.shift), end);
    }
    
    public final int codeToMbcLength(final int code) {
        return this.minLength;
    }
    
    public final int[] ctypeCodeRange(final int ctype, final IntHolder sbOut) {
        sbOut.value = 0;
        return super.ctypeCodeRange(ctype);
    }
    
    public final int leftAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        if (s <= p) {
            return s;
        }
        return s - (s - p) % this.maxLength;
    }
    
    public final boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        return false;
    }
    
    private static int log2(int n) {
        int log = 0;
        while ((n >>>= 1) != 0) {
            ++log;
        }
        return log;
    }
}
