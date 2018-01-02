// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import java.util.BitSet;

class IpNet extends IpAddr
{
    IpNet(final String a) {
        super(a);
    }
    
    IpNet(final String[] a) {
        super(a);
    }
    
    @Override
    boolean contains(final IpAddr rhs) {
        final BitSet a = this.bitset(this.netbits);
        final BitSet b = rhs.bitset(this.netbits);
        return a.equals(b);
    }
}
