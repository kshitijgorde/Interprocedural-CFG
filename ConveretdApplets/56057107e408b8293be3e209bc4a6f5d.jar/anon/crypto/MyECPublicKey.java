// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.security.InvalidKeyException;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.asn1.x9.X9ECPoint;

public final class MyECPublicKey extends AbstractPublicKey implements IMyPublicKey
{
    private X9ECPoint m_Q;
    private MyECParams m_params;
    
    public MyECPublicKey(final ECPublicKeyParameters ecPublicKeyParameters) {
        this.m_Q = new X9ECPoint(ecPublicKeyParameters.getQ());
        this.m_params = new MyECParams(ecPublicKeyParameters.getParameters());
    }
    
    public MyECPublicKey(final SubjectPublicKeyInfo subjectPublicKeyInfo) throws IllegalArgumentException {
        try {
            final DEROctetString derOctetString = new DEROctetString(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            this.m_params = new MyECParams(X962Parameters.getInstance(subjectPublicKeyInfo.getAlgorithmId().getParameters()));
            this.m_Q = new X9ECPoint(this.m_params.getECDomainParams().getCurve(), derOctetString);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("invalid info structure in ECDSA public key");
        }
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ECPublicKeyParameters)) {
            return false;
        }
        final ECPublicKeyParameters ecPublicKeyParameters = (ECPublicKeyParameters)o;
        return !ecPublicKeyParameters.getQ().equals(this.m_Q.getPoint()) && this.m_params.equals(ecPublicKeyParameters.getParameters());
    }
    
    public int hashCode() {
        return 0;
    }
    
    public SubjectPublicKeyInfo getAsSubjectPublicKeyInfo() {
        return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, this.m_params.getX962Params().toASN1Object()), this.m_Q.getPoint().getEncoded());
    }
    
    public int getKeyLength() {
        return this.m_params.getECDomainParams().getN().bitLength() - 1;
    }
    
    public ISignatureVerificationAlgorithm getSignatureAlgorithm() {
        try {
            final MyECDSASignature myECDSASignature = new MyECDSASignature();
            myECDSASignature.initVerify(this);
            return myECDSASignature;
        }
        catch (InvalidKeyException ex) {
            return null;
        }
    }
    
    public ECPublicKeyParameters getPublicParams() {
        return new ECPublicKeyParameters(this.m_Q.getPoint(), this.m_params.getECDomainParams());
    }
    
    public String getAlgorithm() {
        return "Elliptic Curve Cryptography";
    }
    
    public String getFormat() {
        return "X509";
    }
    
    public Element toXmlElement(final Document document) {
        return null;
    }
    
    protected void setNamedCurveID(final DERObjectIdentifier namedCurveID) {
        this.m_params.setNamedCurveID(namedCurveID);
    }
}
