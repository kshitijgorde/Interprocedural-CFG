// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Document;
import java.security.interfaces.DSAParams;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import anon.util.Base64;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import java.security.InvalidKeyException;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import java.math.BigInteger;
import java.security.interfaces.DSAPrivateKey;

public final class MyDSAPrivateKey extends AbstractPrivateKey implements DSAPrivateKey, IMyPrivateKey
{
    public static final String XML_ELEMENT_NAME = "DSAPrivateKey";
    private BigInteger m_X;
    private MyDSAParams m_params;
    
    public MyDSAPrivateKey(final PrivateKeyInfo privateKeyInfo) throws InvalidKeyException {
        super(privateKeyInfo);
        try {
            final AlgorithmIdentifier algorithmId = privateKeyInfo.getAlgorithmId();
            this.m_X = ((DERInteger)privateKeyInfo.getPrivateKey()).getValue();
            this.m_params = new MyDSAParams(new DSAParameter((ASN1Sequence)algorithmId.getParameters()));
        }
        catch (Exception ex) {
            throw new InvalidKeyException("IOException while decoding private key");
        }
    }
    
    public MyDSAPrivateKey(final Element element) throws InvalidKeyException, XMLParseException {
        if (element == null || !element.getNodeName().equals("DSAPrivateKey")) {
            throw new XMLParseException("DSAPrivateKey", "Element is null or has wrong name!");
        }
        final BigInteger bigInteger = new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "G"), null)));
        final BigInteger bigInteger2 = new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "P"), null)));
        final BigInteger bigInteger3 = new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Q"), null)));
        this.m_X = new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "X"), null)));
        this.m_params = new MyDSAParams(new DSAPrivateKeyParameters(this.m_X, new DSAParameters(bigInteger2, bigInteger3, bigInteger)).getParameters());
    }
    
    public MyDSAPrivateKey(final DSAPrivateKeyParameters dsaPrivateKeyParameters) {
        this.m_X = dsaPrivateKeyParameters.getX();
        this.m_params = new MyDSAParams(dsaPrivateKeyParameters.getParameters());
    }
    
    public IMyPublicKey createPublicKey() {
        return new MyDSAPublicKey(new DSAPublicKeyParameters(this.getParams().getG().modPow(this.getX(), this.getParams().getP()), this.m_params));
    }
    
    public String getAlgorithm() {
        return "DSA";
    }
    
    public String getFormat() {
        return "PKCS#8";
    }
    
    public BigInteger getX() {
        return this.m_X;
    }
    
    public PrivateKeyInfo getAsPrivateKeyInfo() {
        return new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(this.m_params.getP(), this.m_params.getQ(), this.m_params.getG()).getDERObject()), new DERInteger(this.getX()));
    }
    
    public ISignatureCreationAlgorithm getSignatureAlgorithm() {
        try {
            final MyDSASignature myDSASignature = new MyDSASignature();
            myDSASignature.initSign(this);
            return myDSASignature;
        }
        catch (InvalidKeyException ex) {
            return null;
        }
    }
    
    public DSAParams getParams() {
        return this.m_params;
    }
    
    public MyDSAParams getMyDSAParams() {
        return this.m_params;
    }
    
    public DSAPrivateKeyParameters getPrivateParams() {
        return new DSAPrivateKeyParameters(this.m_X, this.m_params);
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("DSAPrivateKey");
        final Element element2 = document.createElement("G");
        element.appendChild(element2);
        XMLUtil.setValue(element2, Base64.encodeBytes(this.m_params.getG().toByteArray()));
        final Element element3 = document.createElement("P");
        element.appendChild(element3);
        XMLUtil.setValue(element3, Base64.encodeBytes(this.m_params.getP().toByteArray()));
        final Element element4 = document.createElement("Q");
        element.appendChild(element4);
        XMLUtil.setValue(element4, Base64.encodeBytes(this.m_params.getQ().toByteArray()));
        final Element element5 = document.createElement("X");
        element.appendChild(element5);
        XMLUtil.setValue(element5, Base64.encodeBytes(this.m_X.toByteArray()));
        return element;
    }
}
