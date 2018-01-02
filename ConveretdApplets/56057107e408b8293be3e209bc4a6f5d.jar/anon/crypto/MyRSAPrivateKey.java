// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Document;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.DERObjectIdentifier;
import java.security.InvalidKeyException;
import java.math.BigInteger;
import anon.util.Base64;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

public final class MyRSAPrivateKey extends AbstractPrivateKey implements IMyPrivateKey
{
    public static final String XML_ELEMENT_NAME = "RSAPrivateKey";
    private MyRSASignature m_algorithm;
    private RSAPrivateCrtKeyParameters m_Params;
    
    public MyRSAPrivateKey(final CipherParameters cipherParameters) throws Exception {
        this.m_algorithm = new MyRSASignature();
        this.m_Params = (RSAPrivateCrtKeyParameters)cipherParameters;
    }
    
    public MyRSAPrivateKey(final PrivateKeyInfo privateKeyInfo) throws Exception {
        super(privateKeyInfo);
        this.m_algorithm = new MyRSASignature();
        final RSAPrivateKeyStructure rsaPrivateKeyStructure = new RSAPrivateKeyStructure((ASN1Sequence)privateKeyInfo.getPrivateKey());
        this.m_Params = new RSAPrivateCrtKeyParameters(rsaPrivateKeyStructure.getModulus(), rsaPrivateKeyStructure.getPublicExponent(), rsaPrivateKeyStructure.getPrivateExponent(), rsaPrivateKeyStructure.getPrime1(), rsaPrivateKeyStructure.getPrime2(), rsaPrivateKeyStructure.getExponent1(), rsaPrivateKeyStructure.getExponent2(), rsaPrivateKeyStructure.getCoefficient());
    }
    
    public MyRSAPrivateKey(final Element element) throws Exception {
        this.m_algorithm = new MyRSASignature();
        if (element == null || !element.getNodeName().equals("RSAPrivateKey")) {
            throw new XMLParseException("RSAPrivateKey", "Element is null or has wrong name!");
        }
        this.m_Params = new RSAPrivateCrtKeyParameters(new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Modulus"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "PublicExponent"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "PrivateExponent"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "P"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Q"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "dP"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "dQ"), null))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "QInv"), null))));
    }
    
    public MyRSAPrivateKey(final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3, final BigInteger bigInteger4, final BigInteger bigInteger5, final BigInteger bigInteger6, final BigInteger bigInteger7, final BigInteger bigInteger8) throws Exception {
        this.m_algorithm = new MyRSASignature();
        this.m_Params = new RSAPrivateCrtKeyParameters(bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5, bigInteger6, bigInteger7, bigInteger8);
    }
    
    public ISignatureCreationAlgorithm getSignatureAlgorithm() {
        try {
            this.m_algorithm.initSign(this);
        }
        catch (InvalidKeyException ex) {}
        return this.m_algorithm;
    }
    
    public IMyPublicKey createPublicKey() {
        return new MyRSAPublicKey(this.getModulus(), this.getPublicExponent());
    }
    
    public CipherParameters getParams() {
        return this.m_Params;
    }
    
    public BigInteger getModulus() {
        return this.m_Params.getModulus();
    }
    
    public BigInteger getPrivateExponent() {
        return this.m_Params.getExponent();
    }
    
    public BigInteger getP() {
        return this.m_Params.getP();
    }
    
    public BigInteger getQ() {
        return this.m_Params.getQ();
    }
    
    public BigInteger getDP() {
        return this.m_Params.getDP();
    }
    
    public BigInteger getDQ() {
        return this.m_Params.getDQ();
    }
    
    public BigInteger getQInv() {
        return this.m_Params.getQInv();
    }
    
    public BigInteger getPublicExponent() {
        return this.m_Params.getPublicExponent();
    }
    
    public String getAlgorithm() {
        return "RSA";
    }
    
    public String getFormat() {
        return "PKCS#8";
    }
    
    public PrivateKeyInfo getAsPrivateKeyInfo() {
        return new PrivateKeyInfo(new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.1.1")), new RSAPrivateKeyStructure(this.m_Params.getModulus(), this.m_Params.getPublicExponent(), this.m_Params.getExponent(), this.m_Params.getP(), this.m_Params.getQ(), this.m_Params.getDP(), this.m_Params.getDQ(), this.m_Params.getQInv()).getDERObject());
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("RSAPrivateKey");
        final Element element2 = document.createElement("Modulus");
        element.appendChild(element2);
        XMLUtil.setValue(element2, Base64.encodeBytes(this.m_Params.getModulus().toByteArray()));
        final Element element3 = document.createElement("PublicExponent");
        element.appendChild(element3);
        XMLUtil.setValue(element3, Base64.encodeBytes(this.m_Params.getPublicExponent().toByteArray()));
        final Element element4 = document.createElement("PrivateExponent");
        element.appendChild(element4);
        XMLUtil.setValue(element4, Base64.encodeBytes(this.m_Params.getExponent().toByteArray()));
        final Element element5 = document.createElement("P");
        element.appendChild(element5);
        XMLUtil.setValue(element5, Base64.encodeBytes(this.m_Params.getP().toByteArray()));
        final Element element6 = document.createElement("Q");
        element.appendChild(element6);
        XMLUtil.setValue(element6, Base64.encodeBytes(this.m_Params.getQ().toByteArray()));
        final Element element7 = document.createElement("dP");
        element.appendChild(element7);
        XMLUtil.setValue(element7, Base64.encodeBytes(this.m_Params.getDP().toByteArray()));
        final Element element8 = document.createElement("dQ");
        element.appendChild(element8);
        XMLUtil.setValue(element8, Base64.encodeBytes(this.m_Params.getDQ().toByteArray()));
        final Element element9 = document.createElement("QInv");
        element.appendChild(element9);
        XMLUtil.setValue(element9, Base64.encodeBytes(this.m_Params.getQInv().toByteArray()));
        return element;
    }
}
