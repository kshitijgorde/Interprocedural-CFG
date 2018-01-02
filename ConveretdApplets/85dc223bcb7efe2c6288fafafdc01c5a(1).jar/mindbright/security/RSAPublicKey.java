// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.math.BigInteger;

public class RSAPublicKey extends RSAKey implements PublicKey
{
    public RSAPublicKey(final BigInteger e, final BigInteger n) {
        super(e, n);
    }
}
