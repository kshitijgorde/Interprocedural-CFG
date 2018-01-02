// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.signers;

import org.bouncycastle.crypto.params.DSAParameters;
import java.util.Random;
import java.math.BigInteger;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.CipherParameters;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.DSAKeyParameters;
import org.bouncycastle.crypto.DSA;

public class DSASigner implements DSA
{
    DSAKeyParameters key;
    SecureRandom random;
    
    public void init(final boolean b, final CipherParameters cipherParameters) {
        if (b) {
            if (cipherParameters instanceof ParametersWithRandom) {
                final ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (DSAPrivateKeyParameters)parametersWithRandom.getParameters();
            }
            else {
                this.random = new SecureRandom();
                this.key = (DSAPrivateKeyParameters)cipherParameters;
            }
        }
        else {
            this.key = (DSAPublicKeyParameters)cipherParameters;
        }
    }
    
    public BigInteger[] generateSignature(final byte[] array) {
        final DSAParameters parameters = this.key.getParameters();
        final BigInteger calculateE = this.calculateE(parameters.getQ(), array);
        final int bitLength = parameters.getQ().bitLength();
        BigInteger bigInteger;
        do {
            bigInteger = new BigInteger(bitLength, this.random);
        } while (bigInteger.compareTo(parameters.getQ()) >= 0);
        final BigInteger mod = parameters.getG().modPow(bigInteger, parameters.getP()).mod(parameters.getQ());
        return new BigInteger[] { mod, bigInteger.modInverse(parameters.getQ()).multiply(calculateE.add(((DSAPrivateKeyParameters)this.key).getX().multiply(mod))).mod(parameters.getQ()) };
    }
    
    public boolean verifySignature(final byte[] array, final BigInteger bigInteger, final BigInteger bigInteger2) {
        final DSAParameters parameters = this.key.getParameters();
        final BigInteger calculateE = this.calculateE(parameters.getQ(), array);
        final BigInteger value = BigInteger.valueOf(0L);
        if (value.compareTo(bigInteger) >= 0 || parameters.getQ().compareTo(bigInteger) <= 0) {
            return false;
        }
        if (value.compareTo(bigInteger2) >= 0 || parameters.getQ().compareTo(bigInteger2) <= 0) {
            return false;
        }
        final BigInteger modInverse = bigInteger2.modInverse(parameters.getQ());
        return parameters.getG().modPow(calculateE.multiply(modInverse).mod(parameters.getQ()), parameters.getP()).multiply(((DSAPublicKeyParameters)this.key).getY().modPow(bigInteger.multiply(modInverse).mod(parameters.getQ()), parameters.getP())).mod(parameters.getP()).mod(parameters.getQ()).equals(bigInteger);
    }
    
    private BigInteger calculateE(final BigInteger bigInteger, final byte[] array) {
        if (bigInteger.bitLength() >= array.length * 8) {
            return new BigInteger(1, array);
        }
        final byte[] array2 = new byte[bigInteger.bitLength() / 8];
        System.arraycopy(array, 0, array2, 0, array2.length);
        return new BigInteger(1, array2);
    }
}
