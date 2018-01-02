// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import java.util.Random;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;

public class RSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator
{
    private static final BigInteger ONE;
    private RSAKeyGenerationParameters param;
    
    public void init(final KeyGenerationParameters keyGenerationParameters) {
        this.param = (RSAKeyGenerationParameters)keyGenerationParameters;
    }
    
    public AsymmetricCipherKeyPair generateKeyPair() {
        final int strength = this.param.getStrength();
        final int n = (strength + 1) / 2;
        final int n2 = strength - n;
        final int n3 = strength / 3;
        final BigInteger publicExponent = this.param.getPublicExponent();
        BigInteger max;
        while (true) {
            max = new BigInteger(n, 1, this.param.getRandom());
            if (max.mod(publicExponent).equals(RSAKeyPairGenerator.ONE)) {
                continue;
            }
            if (!max.isProbablePrime(this.param.getCertainty())) {
                continue;
            }
            if (publicExponent.gcd(max.subtract(RSAKeyPairGenerator.ONE)).equals(RSAKeyPairGenerator.ONE)) {
                break;
            }
        }
        BigInteger bigInteger;
        BigInteger multiply;
        while (true) {
            bigInteger = new BigInteger(n2, 1, this.param.getRandom());
            if (bigInteger.subtract(max).abs().bitLength() < n3) {
                continue;
            }
            if (bigInteger.mod(publicExponent).equals(RSAKeyPairGenerator.ONE)) {
                continue;
            }
            if (!bigInteger.isProbablePrime(this.param.getCertainty())) {
                continue;
            }
            if (!publicExponent.gcd(bigInteger.subtract(RSAKeyPairGenerator.ONE)).equals(RSAKeyPairGenerator.ONE)) {
                continue;
            }
            multiply = max.multiply(bigInteger);
            if (multiply.bitLength() == this.param.getStrength()) {
                break;
            }
            max = max.max(bigInteger);
        }
        if (max.compareTo(bigInteger) < 0) {
            final BigInteger bigInteger2 = max;
            max = bigInteger;
            bigInteger = bigInteger2;
        }
        final BigInteger subtract = max.subtract(RSAKeyPairGenerator.ONE);
        final BigInteger subtract2 = bigInteger.subtract(RSAKeyPairGenerator.ONE);
        final BigInteger modInverse = publicExponent.modInverse(subtract.multiply(subtract2));
        return new AsymmetricCipherKeyPair(new RSAKeyParameters(false, multiply, publicExponent), new RSAPrivateCrtKeyParameters(multiply, publicExponent, modInverse, max, bigInteger, modInverse.remainder(subtract), modInverse.remainder(subtract2), bigInteger.modInverse(max)));
    }
    
    static {
        ONE = BigInteger.valueOf(1L);
    }
}
