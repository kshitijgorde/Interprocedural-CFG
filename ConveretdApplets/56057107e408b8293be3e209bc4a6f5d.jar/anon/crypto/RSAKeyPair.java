// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import java.security.SecureRandom;
import java.math.BigInteger;

public class RSAKeyPair extends AsymmetricCryptoKeyPair
{
    public static final int KEY_LENGTH_2048 = 2048;
    
    public RSAKeyPair(final MyRSAPrivateKey myRSAPrivateKey) {
        super(myRSAPrivateKey);
    }
    
    public static RSAKeyPair getInstance(final BigInteger bigInteger, final SecureRandom secureRandom, final int n, final int n2) {
        final RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
        rsaKeyPairGenerator.init(new RSAKeyGenerationParameters(bigInteger, secureRandom, n, n2));
        final AsymmetricCipherKeyPair generateKeyPair = rsaKeyPairGenerator.generateKeyPair();
        RSAKeyPair rsaKeyPair;
        try {
            rsaKeyPair = new RSAKeyPair(new MyRSAPrivateKey(generateKeyPair.getPrivate()));
        }
        catch (Exception ex) {
            rsaKeyPair = null;
        }
        if (!AsymmetricCryptoKeyPair.isValidKeyPair(rsaKeyPair)) {
            return null;
        }
        return rsaKeyPair;
    }
    
    public static RSAKeyPair getInstance(final SecureRandom secureRandom, final int n, final int n2) {
        return getInstance(new BigInteger("65537"), secureRandom, n, n2);
    }
}
