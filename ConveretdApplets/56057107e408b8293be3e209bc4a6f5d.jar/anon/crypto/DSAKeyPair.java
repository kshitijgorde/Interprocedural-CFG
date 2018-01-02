// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import logging.LogHolder;
import logging.LogType;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import org.bouncycastle.crypto.generators.DSAKeyPairGenerator;
import org.bouncycastle.crypto.generators.DSAParametersGenerator;
import java.security.SecureRandom;

public class DSAKeyPair extends AsymmetricCryptoKeyPair
{
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$security$SecureRandom;
    
    public DSAKeyPair(final MyDSAPrivateKey myDSAPrivateKey) {
        super(myDSAPrivateKey);
    }
    
    public static DSAKeyPair getInstance(final SecureRandom secureRandom, final int n, final int n2) {
        DSAKeyPair instanceJCE = getInstanceJCE(secureRandom, n, n2);
        if (instanceJCE == null) {
            final DSAParametersGenerator dsaParametersGenerator = new DSAParametersGenerator();
            dsaParametersGenerator.init(n, n2, secureRandom);
            final DSAKeyPairGenerator dsaKeyPairGenerator = new DSAKeyPairGenerator();
            dsaKeyPairGenerator.init(new DSAKeyGenerationParameters(secureRandom, dsaParametersGenerator.generateParameters()));
            final AsymmetricCipherKeyPair generateKeyPair = dsaKeyPairGenerator.generateKeyPair();
            try {
                instanceJCE = new DSAKeyPair(new MyDSAPrivateKey((DSAPrivateKeyParameters)generateKeyPair.getPrivate()));
            }
            catch (Exception ex) {
                instanceJCE = null;
            }
        }
        if (!AsymmetricCryptoKeyPair.isValidKeyPair(instanceJCE)) {
            return null;
        }
        return instanceJCE;
    }
    
    private static DSAKeyPair getInstanceJCE(final SecureRandom secureRandom, final int n, final int n2) {
        AsymmetricCryptoKeyPair asymmetricCryptoKeyPair;
        try {
            final Class<?> forName = Class.forName("java.security.KeyPairGenerator");
            final Class<?> forName2 = Class.forName("java.security.KeyPair");
            final Class<?> forName3 = Class.forName("org.bouncycastle.jce.provider.DSAUtil");
            final Class<?> forName4 = Class.forName("java.security.PrivateKey");
            final Object invoke = forName.getMethod("getInstance", (DSAKeyPair.class$java$lang$String == null) ? (DSAKeyPair.class$java$lang$String = class$("java.lang.String")) : DSAKeyPair.class$java$lang$String).invoke(forName, "DSA");
            forName.getMethod("initialize", Integer.TYPE, (DSAKeyPair.class$java$security$SecureRandom == null) ? (DSAKeyPair.class$java$security$SecureRandom = class$("java.security.SecureRandom")) : DSAKeyPair.class$java$security$SecureRandom).invoke(invoke, new Integer(n), secureRandom);
            final DSAPrivateKeyParameters dsaPrivateKeyParameters = (DSAPrivateKeyParameters)forName3.getMethod("generatePrivateKeyParameter", forName4).invoke(forName3, forName2.getMethod("getPrivate", (Class[])null).invoke(forName.getMethod("generateKeyPair", (Class[])null).invoke(invoke, (Object[])null), (Object[])null));
            try {
                asymmetricCryptoKeyPair = new DSAKeyPair(new MyDSAPrivateKey(dsaPrivateKeyParameters));
                LogHolder.log(6, LogType.CRYPTO, "Used JCE for creating DSA key pair.");
            }
            catch (Exception ex4) {
                asymmetricCryptoKeyPair = null;
            }
            if (asymmetricCryptoKeyPair != null && !AsymmetricCryptoKeyPair.isValidKeyPair(asymmetricCryptoKeyPair)) {
                LogHolder.log(3, LogType.CRYPTO, "Created illegal DSA certificate with JCE!");
                asymmetricCryptoKeyPair = null;
            }
        }
        catch (ClassNotFoundException ex) {
            LogHolder.log(7, LogType.CRYPTO, ex);
            asymmetricCryptoKeyPair = null;
        }
        catch (NoSuchMethodException ex2) {
            LogHolder.log(7, LogType.CRYPTO, ex2);
            asymmetricCryptoKeyPair = null;
        }
        catch (Exception ex3) {
            LogHolder.log(3, LogType.CRYPTO, "Could not create DSA certificate with JCE!", ex3);
            asymmetricCryptoKeyPair = null;
        }
        return (DSAKeyPair)asymmetricCryptoKeyPair;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
