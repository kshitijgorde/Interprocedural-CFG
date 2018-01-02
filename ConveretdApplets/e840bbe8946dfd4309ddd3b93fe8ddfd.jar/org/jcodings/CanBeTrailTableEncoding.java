// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public abstract class CanBeTrailTableEncoding extends MultiByteEncoding
{
    protected final boolean[] CanBeTrailTable;
    
    protected CanBeTrailTableEncoding(final String name, final int minLength, final int maxLength, final int[] EncLen, final int[][] Trans, final short[] CTypeTable, final boolean[] CanBeTrailTable) {
        super(name, minLength, maxLength, EncLen, Trans, CTypeTable);
        this.CanBeTrailTable = CanBeTrailTable;
    }
    
    public int leftAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        if (s <= p) {
            return s;
        }
        int p_ = s;
        if (this.CanBeTrailTable[bytes[p_] & 0xFF]) {
            while (p_ > p) {
                if (this.EncLen[bytes[--p_] & 0xFF] <= 1) {
                    ++p_;
                    break;
                }
            }
        }
        final int len = this.length(bytes, p_, end);
        if (p_ + len > s) {
            return p_;
        }
        p_ += len;
        return p_ + (s - p_ & 0xFFFFFFFE);
    }
    
    public boolean isReverseMatchAllowed(final byte[] bytes, final int p, final int end) {
        return !this.CanBeTrailTable[bytes[p] & 0xFF];
    }
}
