// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.x9.X962NamedCurves;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import java.security.SecureRandom;

public class ECKeyPair extends AsymmetricCryptoKeyPair
{
    ECKeyPair(final IMyPrivateKey myPrivateKey) {
        super(myPrivateKey);
    }
    
    public static ECKeyPair getInstance(final SecureRandom secureRandom) {
        return getInstance(SECObjectIdentifiers.secp160r1, secureRandom);
    }
    
    public static ECKeyPair getInstance(final DERObjectIdentifier derObjectIdentifier, final SecureRandom secureRandom) {
        X9ECParameters x9ECParameters = SECNamedCurves.getByOID(derObjectIdentifier);
        if (x9ECParameters == null) {
            x9ECParameters = X962NamedCurves.getByOID(derObjectIdentifier);
        }
        if (x9ECParameters == null) {
            x9ECParameters = NISTNamedCurves.getByOID(derObjectIdentifier);
        }
        if (x9ECParameters == null) {
            x9ECParameters = TeleTrusTNamedCurves.getByOID(derObjectIdentifier);
        }
        if (x9ECParameters == null) {
            throw new IllegalArgumentException("Unknown Named Curve Identifier!");
        }
        final ECKeyGenerationParameters ecKeyGenerationParameters = new ECKeyGenerationParameters(new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH()), secureRandom);
        final ECKeyPairGenerator ecKeyPairGenerator = new ECKeyPairGenerator();
        ecKeyPairGenerator.init(ecKeyGenerationParameters);
        final AsymmetricCipherKeyPair generateKeyPair = ecKeyPairGenerator.generateKeyPair();
        ECKeyPair ecKeyPair;
        try {
            ecKeyPair = new ECKeyPair(new MyECPrivateKey((ECPrivateKeyParameters)generateKeyPair.getPrivate(), derObjectIdentifier));
        }
        catch (Exception ex) {
            return null;
        }
        if (!AsymmetricCryptoKeyPair.isValidKeyPair(ecKeyPair)) {
            return null;
        }
        return ecKeyPair;
    }
}
