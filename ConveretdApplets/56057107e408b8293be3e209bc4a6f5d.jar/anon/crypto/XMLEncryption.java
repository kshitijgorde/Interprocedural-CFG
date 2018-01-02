// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.KeyParameter;
import anon.util.IMiscPasswordReader;
import anon.util.SingleStringPasswordReader;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.modes.CTSBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.crypto.CipherParameters;
import org.w3c.dom.Document;
import anon.util.Base64;
import java.io.IOException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.security.SecureRandom;
import org.w3c.dom.Element;

public final class XMLEncryption
{
    public static final String XML_ELEMENT_NAME = "EncryptedData";
    private static final int SALT_SIZE = 20;
    private static final int MIN_ITERATIONS = 1000;
    
    public static Element encryptElement(final Element element, final String s) throws Exception {
        final SecureRandom secureRandom = new SecureRandom();
        final byte[] array = new byte[20];
        secureRandom.nextBytes(array);
        byte[] codeDataCTS;
        try {
            final byte[] bytes = XMLUtil.toString(element).getBytes();
            final int length = bytes.length;
            codeDataCTS = codeDataCTS(true, bytes, generatePBEKey(s, array));
        }
        catch (Exception ex) {
            throw new IOException("Exception while encrypting: " + ex.toString());
        }
        final Document ownerDocument = element.getOwnerDocument();
        final Node parentNode = element.getParentNode();
        final Element element2 = ownerDocument.createElement("EncryptedData");
        element2.setAttribute("Type", "http://www.w3.org/2001/04/xmlenc#Element");
        element2.setAttribute("xmlns", "http://www.w3.org/2001/04/xmlenc#");
        final Element element3 = ownerDocument.createElement("EncryptionMethod");
        element3.setAttribute("Algorithm", "aes-cts");
        element2.appendChild(element3);
        final Element element4 = ownerDocument.createElement("ds:KeyInfo");
        element4.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        final Element element5 = ownerDocument.createElement("ds:Salt");
        XMLUtil.setValue(element5, Base64.encodeBytes(array));
        element4.appendChild(element5);
        element2.appendChild(element4);
        final Element element6 = ownerDocument.createElement("CipherData");
        element2.appendChild(element6);
        final Element element7 = ownerDocument.createElement("CipherValue");
        element6.appendChild(element7);
        XMLUtil.setValue(element7, Base64.encodeBytes(codeDataCTS));
        parentNode.removeChild(element);
        parentNode.appendChild(element2);
        return element2;
    }
    
    private static CipherParameters generatePBEKey(final String s, final byte[] array) {
        final PKCS12PBEParams pkcs12PBEParams = new PKCS12PBEParams(array, 1000);
        final PKCS12ParametersGenerator pkcs12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
        pkcs12ParametersGenerator.init(PBEParametersGenerator.PKCS12PasswordToBytes(s.toCharArray()), pkcs12PBEParams.getIV(), pkcs12PBEParams.getIterations().intValue());
        return pkcs12ParametersGenerator.generateDerivedParameters(128);
    }
    
    private static byte[] codeDataCTS(final boolean b, final byte[] array, final CipherParameters cipherParameters) throws Exception {
        final CTSBlockCipher ctsBlockCipher = new CTSBlockCipher(new AESFastEngine());
        ctsBlockCipher.init(b, cipherParameters);
        final byte[] array2 = new byte[ctsBlockCipher.getOutputSize(array.length)];
        int processBytes = 0;
        if (array.length != 0) {
            processBytes = ctsBlockCipher.processBytes(array, 0, array.length, array2, 0);
        }
        ctsBlockCipher.doFinal(array2, processBytes);
        return array2;
    }
    
    private static byte[] codeDataCBCwithHMAC(final boolean b, final byte[] array, final CipherParameters cipherParameters, final CipherParameters cipherParameters2) throws Exception {
        final PaddedBufferedBlockCipher paddedBufferedBlockCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
        paddedBufferedBlockCipher.init(b, cipherParameters);
        byte[] array2 = new byte[paddedBufferedBlockCipher.getOutputSize(array.length)];
        int processBytes = 0;
        if (array.length != 0) {
            processBytes = paddedBufferedBlockCipher.processBytes(array, 0, array.length, array2, 0);
        }
        final int n = processBytes + paddedBufferedBlockCipher.doFinal(array2, processBytes);
        if (!b && n != array2.length) {
            final byte[] array3 = new byte[n];
            System.arraycopy(array2, 0, array3, 0, n);
            array2 = array3;
        }
        return array2;
    }
    
    public static Element decryptElement(final Element element, final String s) throws Exception {
        return decryptElement(element, new SingleStringPasswordReader(s));
    }
    
    public static Element decryptElement(final Element element, IMiscPasswordReader miscPasswordReader) throws Exception {
        final Document ownerDocument = element.getOwnerDocument();
        final Node parentNode = element.getParentNode();
        if (miscPasswordReader == null) {
            miscPasswordReader = new SingleStringPasswordReader("");
        }
        final String attribute = element.getAttribute("Type");
        if (attribute == null || !attribute.equals("http://www.w3.org/2001/04/xmlenc#Element")) {
            throw new IOException("Wrong XML Format");
        }
        final byte[] decode = Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(element, "CipherData"), "CipherValue"), null));
        final byte[] decode2 = Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(element, "ds:KeyInfo"), "ds:Salt"), null));
        Node node = null;
        Throwable t = null;
        String password;
        while ((password = miscPasswordReader.readPassword(null)) != null) {
            try {
                node = XMLUtil.importNode(ownerDocument, XMLUtil.toXMLDocument(codeDataCTS(false, decode, generatePBEKey(password, decode2))).getDocumentElement(), true);
                t = null;
                break;
            }
            catch (Exception ex) {
                t = ex;
            }
        }
        if (t != null) {
            throw new IOException("Exception while decrypting (maybe password wrong): " + t.toString());
        }
        parentNode.removeChild(element);
        parentNode.appendChild(node);
        return (Element)node;
    }
    
    public static boolean encryptElement(final Element element, final MyRSAPublicKey myRSAPublicKey) {
        final byte[] array = new byte[32];
        new SecureRandom().nextBytes(array);
        final ParametersWithIV parametersWithIV = new ParametersWithIV(new KeyParameter(array, 0, 16), array, 16, 16);
        byte[] codeDataCBCwithHMAC;
        try {
            codeDataCBCwithHMAC = codeDataCBCwithHMAC(true, XMLUtil.toString(element).getBytes(), parametersWithIV, null);
        }
        catch (Exception ex) {
            return false;
        }
        final MyRSA myRSA = new MyRSA();
        byte[] processBlockOAEP;
        try {
            myRSA.init(myRSAPublicKey);
            processBlockOAEP = myRSA.processBlockOAEP(array, 0, array.length);
        }
        catch (Exception ex2) {
            return false;
        }
        final Document ownerDocument = element.getOwnerDocument();
        final Node parentNode = element.getParentNode();
        final Element element2 = ownerDocument.createElement("EncryptedData");
        element2.setAttribute("Type", "http://www.w3.org/2001/04/xmlenc#Element");
        element2.setAttribute("xmlns", "http://www.w3.org/2001/04/xmlenc#");
        final Element element3 = ownerDocument.createElement("EncryptionMethod");
        element3.setAttribute("Algorithm", "http://www.w3.org/2001/04/xmlenc#aes128-cbc");
        element2.appendChild(element3);
        final Element element4 = ownerDocument.createElement("ds:KeyInfo");
        element4.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        element2.appendChild(element4);
        final Element element5 = ownerDocument.createElement("EncryptedKey");
        element4.appendChild(element5);
        final Element element6 = ownerDocument.createElement("EncryptionMethod");
        element6.setAttribute("Algorithm", "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p");
        element5.appendChild(element6);
        final Element element7 = ownerDocument.createElement("CipherData");
        element5.appendChild(element7);
        final Element element8 = ownerDocument.createElement("CipherValue");
        element7.appendChild(element8);
        XMLUtil.setValue(element8, Base64.encodeBytes(processBlockOAEP));
        final Element element9 = ownerDocument.createElement("CipherData");
        element2.appendChild(element9);
        final Element element10 = ownerDocument.createElement("CipherValue");
        element9.appendChild(element10);
        XMLUtil.setValue(element10, Base64.encodeBytes(codeDataCBCwithHMAC));
        parentNode.removeChild(element);
        parentNode.appendChild(element2);
        return true;
    }
}
