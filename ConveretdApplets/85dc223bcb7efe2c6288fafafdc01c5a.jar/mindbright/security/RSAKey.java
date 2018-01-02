// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.math.BigInteger;

public class RSAKey implements Key
{
    private BigInteger e;
    private BigInteger n;
    
    protected RSAKey(final BigInteger e, final BigInteger n) {
        this.e = e;
        this.n = n;
    }
    
    public String getAlgorithm() {
        return "RSA";
    }
    
    public byte[] getEncoded() {
        return null;
    }
    
    public String getFormat() {
        return null;
    }
    
    public int bitLength() {
        return this.n.bitLength();
    }
    
    public BigInteger getE() {
        return this.e;
    }
    
    public BigInteger getN() {
        return this.n;
    }
}
