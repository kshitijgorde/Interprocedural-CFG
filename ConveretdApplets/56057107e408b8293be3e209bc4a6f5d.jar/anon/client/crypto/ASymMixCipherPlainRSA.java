// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import anon.util.Base64;
import org.w3c.dom.Text;
import org.w3c.dom.Element;
import java.math.BigInteger;
import anon.crypto.MyRSAPublicKey;
import anon.crypto.MyRSA;

public class ASymMixCipherPlainRSA implements IASymMixCipher
{
    MyRSA m_RSA;
    private MyRSAPublicKey m_PublicKey;
    
    public ASymMixCipherPlainRSA() {
        this.m_RSA = new MyRSA();
        this.m_PublicKey = null;
    }
    
    public int encrypt(final byte[] array, final int n, final byte[] array2, final int n2) {
        byte[] processBlock;
        try {
            processBlock = this.m_RSA.processBlock(array, n, 128);
        }
        catch (Exception ex) {
            return -1;
        }
        if (processBlock.length == 128) {
            System.arraycopy(processBlock, 0, array2, n2, 128);
        }
        else if (processBlock.length == 129) {
            System.arraycopy(processBlock, 1, array2, n2, 128);
        }
        else {
            for (int i = 0; i < 128 - processBlock.length; ++i) {
                array2[n2 + i] = 0;
            }
            System.arraycopy(processBlock, 0, array2, n2 + 128 - processBlock.length, processBlock.length);
        }
        return 128;
    }
    
    public int getOutputBlockSize() {
        return 128;
    }
    
    public int getInputBlockSize() {
        return 128;
    }
    
    public int getPaddingSize() {
        return 0;
    }
    
    public int setPublicKey(final BigInteger bigInteger, final BigInteger bigInteger2) {
        if (bigInteger == null || bigInteger2 == null) {
            return -1;
        }
        this.m_PublicKey = new MyRSAPublicKey(bigInteger, bigInteger2);
        try {
            this.m_RSA.init(this.m_PublicKey);
        }
        catch (Exception ex) {
            return -21;
        }
        return 0;
    }
    
    public int setPublicKey(final Element element) {
        try {
            final Element element2 = (Element)element.getElementsByTagName("RSAKeyValue").item(0);
            return this.setPublicKey(this.getBigIntegerFromXml(element2, "Modulus"), this.getBigIntegerFromXml(element2, "Exponent"));
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public MyRSAPublicKey getPublicKey() {
        return this.m_PublicKey;
    }
    
    private BigInteger getBigIntegerFromXml(final Element element, final String s) {
        try {
            return new BigInteger(1, Base64.decode(((Text)((Element)element.getElementsByTagName(s).item(0)).getFirstChild()).getNodeValue()));
        }
        catch (Exception ex) {
            return null;
        }
    }
}
