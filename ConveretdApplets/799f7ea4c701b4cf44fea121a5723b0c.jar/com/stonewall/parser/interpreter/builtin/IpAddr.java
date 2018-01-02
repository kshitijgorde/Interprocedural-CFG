// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import java.util.BitSet;

class IpAddr
{
    int netbits;
    String[] address;
    
    IpAddr(final String a) {
        this.address = a.split("\\.");
        this.netbits = this.bits();
    }
    
    IpAddr(final String[] a) {
        this.address = a[0].split("\\.");
        this.netbits = ((a.length == 2) ? Integer.valueOf(a[1]) : this.bits());
    }
    
    int netbits() {
        return this.netbits;
    }
    
    boolean contains(final IpAddr rhs) {
        final BitSet a = this.bitset();
        final BitSet b = rhs.bitset();
        return a.equals(b);
    }
    
    int bits() {
        return this.bits(this.address);
    }
    
    private int bits(final String[] address) {
        int result = 0;
        for (int i = 0; i < address.length; ++i) {
            result += Integer.bitCount(new Integer(address[i]));
        }
        return result;
    }
    
    private short[] octets() {
        final short[] octets = new short[4];
        for (int i = 0; i < 4; ++i) {
            octets[i] = Short.valueOf(this.address[i]);
        }
        return octets;
    }
    
    BitSet bitset() {
        return this.bitset(32);
    }
    
    BitSet bitset(final int limit) {
        final short[] octets = this.octets();
        final BitSet bits = new BitSet();
        for (int i = 0; i < limit && i < octets.length * 8; ++i) {
            if ((octets[i / 8] & 1 << i % 8) > 0) {
                bits.set(i);
            }
        }
        return bits;
    }
}
