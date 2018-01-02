// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.PublicKey;
import anon.util.XMLUtil;
import anon.util.Base64;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.DERObjectIdentifier;
import java.security.InvalidKeyException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import java.io.IOException;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.RSAPublicKeyStructure;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.CipherParameters;
import java.math.BigInteger;

public final class MyRSAPublicKey extends AbstractPublicKey implements IMyPublicKey
{
    private MyRSASignature m_algorithm;
    private BigInteger m_n;
    private BigInteger m_e;
    private long m_hashValue;
    private int m_keyLength;
    
    public MyRSAPublicKey(final BigInteger n, final BigInteger e) {
        this.m_algorithm = new MyRSASignature();
        this.m_hashValue = 0L;
        this.m_keyLength = 0;
        this.m_n = n;
        this.m_e = e;
    }
    
    public MyRSAPublicKey(final CipherParameters cipherParameters) throws Exception {
        this.m_algorithm = new MyRSASignature();
        this.m_hashValue = 0L;
        this.m_keyLength = 0;
        final RSAKeyParameters rsaKeyParameters = (RSAKeyParameters)cipherParameters;
        this.m_n = rsaKeyParameters.getModulus();
        this.m_e = rsaKeyParameters.getExponent();
    }
    
    public MyRSAPublicKey(final RSAPublicKeyStructure rsaPublicKeyStructure) throws IllegalArgumentException {
        this.m_algorithm = new MyRSASignature();
        this.m_hashValue = 0L;
        this.m_keyLength = 0;
        try {
            this.m_n = rsaPublicKeyStructure.getModulus();
            this.m_e = rsaPublicKeyStructure.getPublicExponent();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("invalid info structure in RSA public key");
        }
    }
    
    public MyRSAPublicKey(final SubjectPublicKeyInfo subjectPublicKeyInfo) throws IllegalArgumentException {
        this.m_algorithm = new MyRSASignature();
        this.m_hashValue = 0L;
        this.m_keyLength = 0;
        try {
            final RSAPublicKeyStructure instance = RSAPublicKeyStructure.getInstance(subjectPublicKeyInfo.getPublicKey());
            this.m_n = instance.getModulus();
            this.m_e = instance.getPublicExponent();
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("invalid info structure in RSA public key");
        }
    }
    
    public static MyRSAPublicKey getInstance(final byte[] array) {
        try {
            return new MyRSAPublicKey(new RSAPublicKeyStructure((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(array)).readObject()));
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public ISignatureVerificationAlgorithm getSignatureAlgorithm() {
        try {
            this.m_algorithm.initVerify(this);
        }
        catch (InvalidKeyException ex) {}
        return this.m_algorithm;
    }
    
    public BigInteger getModulus() {
        return this.m_n;
    }
    
    public BigInteger getPublicExponent() {
        return this.m_e;
    }
    
    public String getAlgorithm() {
        return "RSA";
    }
    
    public String getFormat() {
        return "X.509";
    }
    
    public int getKeyLength() {
        return this.getModulus().bitLength();
    }
    
    public SubjectPublicKeyInfo getAsSubjectPublicKeyInfo() {
        return new SubjectPublicKeyInfo(new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.1.1")), new RSAPublicKeyStructure(this.m_n, this.m_e).getDERObject());
    }
    
    public CipherParameters getParams() {
        return new RSAKeyParameters(false, this.m_n, this.m_e);
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("RSAKeyValue");
        final Element element2 = document.createElement("Modulus");
        element.appendChild(element2);
        XMLUtil.setValue(element2, Base64.encodeBytes(this.m_n.toByteArray()));
        final Element element3 = document.createElement("Exponent");
        element.appendChild(element3);
        XMLUtil.setValue(element3, Base64.encodeBytes(this.m_e.toByteArray()));
        return element;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof PublicKey)) {
            return false;
        }
        if (!(o instanceof MyRSAPublicKey)) {
            return false;
        }
        final MyRSAPublicKey myRSAPublicKey = (MyRSAPublicKey)o;
        return myRSAPublicKey.getModulus().equals(this.m_n) && myRSAPublicKey.getPublicExponent().equals(this.m_e);
    }
    
    public int hashCode() {
        if (this.m_hashValue == 0L) {
            this.m_hashValue = this.m_n.longValue() + this.m_e.longValue();
        }
        return (int)this.m_hashValue;
    }
    
    public String toString() {
        return "e=" + ((this.m_e == null) ? "(not set)" : this.m_e.toString()) + " ; n=" + ((this.m_n == null) ? "(not set)" : this.m_n.toString());
    }
}
