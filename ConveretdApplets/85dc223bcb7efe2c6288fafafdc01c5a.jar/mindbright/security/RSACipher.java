// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.io.IOException;
import java.math.BigInteger;

public class RSACipher
{
    public KeyPair keys;
    
    public RSACipher(final KeyPair keys) {
        this.keys = keys;
    }
    
    public BigInteger doPublic(final BigInteger input) {
        final RSAPublicKey pubKey = (RSAPublicKey)this.keys.getPublic();
        final BigInteger result = input.modPow(pubKey.getE(), pubKey.getN());
        return result;
    }
    
    public BigInteger doPrivate(final BigInteger input) {
        final BigInteger one = BigInteger.valueOf(1L);
        final RSAPrivateKey privKey = (RSAPrivateKey)this.keys.getPrivate();
        BigInteger dp = privKey.getP().subtract(one);
        dp = privKey.getD().mod(dp);
        BigInteger dq = privKey.getQ().subtract(one);
        dq = privKey.getD().mod(dq);
        BigInteger p2 = input.mod(privKey.getP());
        p2 = p2.modPow(dp, privKey.getP());
        BigInteger q2 = input.mod(privKey.getQ());
        q2 = q2.modPow(dq, privKey.getQ());
        if (p2.compareTo(q2) == 0) {
            return p2;
        }
        BigInteger k = q2.subtract(p2).mod(privKey.getQ());
        k = k.multiply(privKey.getU());
        k = k.mod(privKey.getQ());
        BigInteger result = k.multiply(privKey.getP());
        result = result.add(p2);
        return result;
    }
    
    public static BigInteger stripPad(final BigInteger input) throws IOException {
        final byte[] strip = input.toByteArray();
        if (strip[0] != 2) {
            throw new IOException("Invalid strip-data");
        }
        int i;
        for (i = 0; i < strip.length && strip[i] != 0; ++i) {}
        if (i == strip.length) {
            throw new IOException("Invalid strip-data");
        }
        final byte[] val = new byte[strip.length - i];
        System.arraycopy(strip, i, val, 0, val.length);
        return new BigInteger(val);
    }
    
    public static BigInteger doPad(final BigInteger input, final int padLen, final SecureRandom rand) throws IOException {
        final int inByteLen = (input.bitLength() + 7) / 8;
        final int padByteLen = (padLen + 7) / 8;
        if (inByteLen > padByteLen - 3) {
            throw new IOException("rsaPad: Input too long to pad");
        }
        final byte[] ranBytes = new byte[padByteLen - inByteLen - 3 + 1];
        rand.nextBytes(ranBytes);
        ranBytes[0] = 0;
        for (int i = 1; i < padByteLen - inByteLen - 3 + 1; ++i) {
            if (ranBytes[i] == 0) {
                ranBytes[i] = 23;
            }
        }
        BigInteger rndInt = new BigInteger(ranBytes);
        rndInt = rndInt.shiftLeft((inByteLen + 1) * 8);
        BigInteger result = new BigInteger("2");
        result = result.shiftLeft((padByteLen - 2) * 8);
        result = result.or(rndInt);
        result = result.or(input);
        return result;
    }
}
