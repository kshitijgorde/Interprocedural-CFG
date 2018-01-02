// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public abstract class EucEncoding extends MultiByteEncoding
{
    protected EucEncoding(final String name, final int minLength, final int maxLength, final int[] EncLen, final int[][] Trans, final short[] CTypeTable) {
        super(name, minLength, maxLength, EncLen, Trans, CTypeTable);
    }
    
    protected abstract boolean isLead(final int p0);
    
    public int leftAdjustCharHead(final byte[] bytes, final int p, final int s, final int end) {
        if (s <= p) {
            return s;
        }
        int p_;
        for (p_ = s; !this.isLead(bytes[p_] & 0xFF) && p_ > p; --p_) {}
        final int len = this.length(bytes, p_, end);
        if (p_ + len > s) {
            return p_;
        }
        p_ += len;
        return p_ + (s - p_ & 0xFFFFFFFE);
    }
}
