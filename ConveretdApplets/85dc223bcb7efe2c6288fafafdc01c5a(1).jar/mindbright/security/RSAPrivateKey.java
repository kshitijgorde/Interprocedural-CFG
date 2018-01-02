// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.math.BigInteger;

public class RSAPrivateKey extends RSAKey implements PrivateKey
{
    private BigInteger d;
    private BigInteger u;
    private BigInteger p;
    private BigInteger q;
    
    public RSAPrivateKey(final BigInteger e, final BigInteger n, final BigInteger d, final BigInteger u, final BigInteger p, final BigInteger q) {
        super(e, n);
        this.d = d;
        this.u = u;
        this.p = p;
        this.q = q;
    }
    
    public BigInteger getD() {
        return this.d;
    }
    
    public BigInteger getU() {
        return this.u;
    }
    
    public BigInteger getP() {
        return this.p;
    }
    
    public BigInteger getQ() {
        return this.q;
    }
}
