// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Document;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import java.security.InvalidKeyException;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.sec.ECPrivateKeyStructure;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import java.math.BigInteger;

public final class MyECPrivateKey extends AbstractPrivateKey implements IMyPrivateKey
{
    private BigInteger m_D;
    private MyECParams m_params;
    
    public MyECPrivateKey(final ECPrivateKeyParameters ecPrivateKeyParameters, final DERObjectIdentifier namedCurveID) {
        this.m_D = ecPrivateKeyParameters.getD();
        (this.m_params = new MyECParams(ecPrivateKeyParameters.getParameters())).setNamedCurveID(namedCurveID);
    }
    
    public MyECPrivateKey(final PrivateKeyInfo privateKeyInfo) throws InvalidKeyException {
        super(privateKeyInfo);
        try {
            final AlgorithmIdentifier algorithmId = privateKeyInfo.getAlgorithmId();
            this.m_D = new ECPrivateKeyStructure((ASN1Sequence)privateKeyInfo.getPrivateKey()).getKey();
            this.m_params = new MyECParams(X962Parameters.getInstance(algorithmId.getParameters()));
        }
        catch (Exception ex) {
            throw new InvalidKeyException("IOException while decoding private key");
        }
    }
    
    public MyECPrivateKey(final Element element) throws InvalidKeyException, XMLParseException {
    }
    
    public IMyPublicKey createPublicKey() {
        final MyECPublicKey myECPublicKey = new MyECPublicKey(new ECPublicKeyParameters(this.m_params.getECDomainParams().getG().multiply(this.m_D), this.m_params.getECDomainParams()));
        myECPublicKey.setNamedCurveID(this.m_params.getCurveID());
        return myECPublicKey;
    }
    
    public PrivateKeyInfo getAsPrivateKeyInfo() {
        return new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, this.m_params.getX962Params().toASN1Object()), new ECPrivateKeyStructure(this.m_D).getDERObject());
    }
    
    public ISignatureCreationAlgorithm getSignatureAlgorithm() {
        try {
            final MyECDSASignature myECDSASignature = new MyECDSASignature();
            myECDSASignature.initSign(this);
            return myECDSASignature;
        }
        catch (InvalidKeyException ex) {
            return null;
        }
    }
    
    public String getAlgorithm() {
        return "Elliptic Curve Cryptography";
    }
    
    public String getFormat() {
        return "PKCS#8";
    }
    
    public Element toXmlElement(final Document document) {
        return null;
    }
    
    public ECPrivateKeyParameters getPrivateParams() {
        return new ECPrivateKeyParameters(this.m_D, this.m_params.getECDomainParams());
    }
}
