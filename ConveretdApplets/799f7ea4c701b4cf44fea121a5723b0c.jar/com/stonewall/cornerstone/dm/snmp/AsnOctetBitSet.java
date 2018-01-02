// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

import java.util.Arrays;
import java.util.BitSet;

public class AsnOctetBitSet extends BitSet
{
    static final long serialVersionUID = 0L;
    
    public AsnOctetBitSet() {
    }
    
    public AsnOctetBitSet(final BitSet bitSet) {
        super(bitSet.size());
        this.or(bitSet);
    }
    
    public AsnOctetBitSet(final byte[] bytes) {
        super(bytes.length * 8);
        this.set(bytes);
    }
    
    public byte[] getBytes() {
        final int nbits = this.size();
        int nbytes = nbits / 8;
        if (nbits % 8 != 0) {
            ++nbytes;
        }
        final byte[] bytes = new byte[nbytes];
        Arrays.fill(bytes, (byte)0);
        for (int b = 0; b < this.length(); ++b) {
            if (this.get(b)) {
                final int nbit = 7 - b % 8;
                final int nbyte = b / 8;
                final byte[] array = bytes;
                final int n = nbyte;
                array[n] |= (byte)(1 << nbit);
            }
        }
        return bytes;
    }
    
    public void set(final byte[] bytes) {
        this.clear();
        for (int B = 0; B < bytes.length; ++B) {
            for (byte b = 7; b >= 0; --b) {
                final byte mask = (byte)(1 << b);
                if ((bytes[B] & mask) == mask) {
                    final int nbit = B * 8 + (7 - b);
                    this.set(nbit);
                }
            }
        }
    }
}
