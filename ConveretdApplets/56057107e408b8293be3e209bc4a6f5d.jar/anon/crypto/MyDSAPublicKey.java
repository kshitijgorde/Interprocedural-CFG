// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.Base64;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.DSAParameters;
import java.security.interfaces.DSAParams;
import java.security.InvalidKeyException;
import java.io.IOException;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import java.math.BigInteger;
import java.security.interfaces.DSAPublicKey;

public final class MyDSAPublicKey extends AbstractPublicKey implements DSAPublicKey, IMyPublicKey
{
    private BigInteger m_Y;
    private MyDSAParams m_params;
    private long m_hashValue;
    
    public MyDSAPublicKey(final DSAPublicKeyParameters dsaPublicKeyParameters) {
        this.m_hashValue = 0L;
        this.m_Y = dsaPublicKeyParameters.getY();
        this.m_params = new MyDSAParams(dsaPublicKeyParameters.getParameters());
    }
    
    public MyDSAPublicKey(final SubjectPublicKeyInfo subjectPublicKeyInfo) throws IllegalArgumentException {
        this.m_hashValue = 0L;
        try {
            final DSAParameter dsaParameter = new DSAParameter((ASN1Sequence)subjectPublicKeyInfo.getAlgorithmId().getParameters());
            this.m_Y = ((DERInteger)subjectPublicKeyInfo.getPublicKey()).getValue();
            this.m_params = new MyDSAParams(dsaParameter);
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
    }
    
    public ISignatureVerificationAlgorithm getSignatureAlgorithm() {
        try {
            final MyDSASignature myDSASignature = new MyDSASignature();
            myDSASignature.initVerify(this);
            return myDSASignature;
        }
        catch (InvalidKeyException ex) {
            return null;
        }
    }
    
    public BigInteger getY() {
        return this.m_Y;
    }
    
    public DSAParams getParams() {
        return this.m_params;
    }
    
    public DSAPublicKeyParameters getPublicParams() {
        return new DSAPublicKeyParameters(this.m_Y, this.m_params);
    }
    
    public MyDSAParams getMyDASParams() {
        return this.m_params;
    }
    
    public String getAlgorithm() {
        return "DSA";
    }
    
    public String getFormat() {
        return "X.509";
    }
    
    public SubjectPublicKeyInfo getAsSubjectPublicKeyInfo() {
        return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(this.m_params.getP(), this.m_params.getQ(), this.m_params.getG()).getDERObject()), new DERInteger(this.getY()));
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("DSAKeyValue");
        final Element element2 = document.createElement("Y");
        XMLUtil.setValue(element2, Base64.encodeBytes(this.m_Y.toByteArray()));
        element.appendChild(element2);
        final Element element3 = document.createElement("P");
        XMLUtil.setValue(element3, Base64.encodeBytes(this.m_params.getP().toByteArray()));
        element.appendChild(element3);
        final Element element4 = document.createElement("Q");
        XMLUtil.setValue(element4, Base64.encodeBytes(this.m_params.getQ().toByteArray()));
        element.appendChild(element4);
        final Element element5 = document.createElement("G");
        XMLUtil.setValue(element5, Base64.encodeBytes(this.m_params.getG().toByteArray()));
        element.appendChild(element5);
        return element;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof DSAPublicKey)) {
            return false;
        }
        final DSAPublicKey dsaPublicKey = (DSAPublicKey)o;
        return dsaPublicKey.getY().equals(this.m_Y) && dsaPublicKey.getParams().equals(this.m_params);
    }
    
    public int hashCode() {
        if (this.m_hashValue == 0L) {
            this.m_hashValue = this.m_Y.longValue() + this.m_params.getG().longValue() + this.m_params.getP().longValue() + this.m_params.getQ().longValue();
        }
        return (int)this.m_hashValue;
    }
    
    public int getKeyLength() {
        final int n = this.m_Y.toByteArray().length * 8;
        return n - n % 64;
    }
    
    public int getParameterLength() {
        return (this.m_params.getG().toByteArray().length + this.m_params.getP().toByteArray().length + this.m_params.getQ().toByteArray().length) * 8;
    }
}
