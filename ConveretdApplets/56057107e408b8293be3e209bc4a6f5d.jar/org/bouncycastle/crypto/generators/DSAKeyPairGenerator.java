// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.generators;

import java.security.SecureRandom;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import java.util.Random;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;

public class DSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator
{
    private static final BigInteger ZERO;
    private DSAKeyGenerationParameters param;
    
    public void init(final KeyGenerationParameters keyGenerationParameters) {
        this.param = (DSAKeyGenerationParameters)keyGenerationParameters;
    }
    
    public AsymmetricCipherKeyPair generateKeyPair() {
        final DSAParameters parameters = this.param.getParameters();
        final SecureRandom random = this.param.getRandom();
        final BigInteger q = parameters.getQ();
        final BigInteger p = parameters.getP();
        final BigInteger g = parameters.getG();
        BigInteger bigInteger;
        do {
            bigInteger = new BigInteger(160, random);
        } while (bigInteger.equals(DSAKeyPairGenerator.ZERO) || bigInteger.compareTo(q) >= 0);
        return new AsymmetricCipherKeyPair(new DSAPublicKeyParameters(g.modPow(bigInteger, p), parameters), new DSAPrivateKeyParameters(bigInteger, parameters));
    }
    
    static {
        ZERO = BigInteger.valueOf(0L);
    }
}
