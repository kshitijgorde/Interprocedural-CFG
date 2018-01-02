// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.math.BigInteger;

public class RSAKey implements Key
{
    public BigInteger m1;
    public BigInteger m0;
    
    public RSAKey(final BigInteger m1, final BigInteger m2) {
        this.m1 = m1;
        this.m0 = m2;
    }
    
    public final int m4() {
        return this.m0.bitLength();
    }
    
    public final BigInteger m3() {
        return this.m1;
    }
    
    public final BigInteger m2() {
        return this.m0;
    }
}
