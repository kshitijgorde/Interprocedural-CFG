// 
// Decompiled by Procyon v0.5.30
// 

package anon.util.captcha;

import anon.crypto.MyAES;
import java.math.BigInteger;
import org.w3c.dom.NodeList;
import anon.util.ZLibTools;
import anon.util.Base64;
import org.w3c.dom.Element;
import java.awt.Image;

public class ZipBinaryImageCaptchaClient implements IImageEncodedCaptcha
{
    public static final String CAPTCHA_DATA_FORMAT = "ZIP_BINARY_IMAGE";
    private Image m_captchaImage;
    private int m_captchaKeyBits;
    private int m_extraKeyBits;
    private String m_characterSet;
    private int m_characterNumber;
    private byte[] m_encodedData;
    
    public ZipBinaryImageCaptchaClient(final Element element) throws Exception {
        final NodeList elementsByTagName = element.getElementsByTagName("CaptchaDataFormat");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure (CaptchaDataFormat node).");
        }
        if (!"ZIP_BINARY_IMAGE".equals(((Element)elementsByTagName.item(0)).getFirstChild().getNodeValue())) {
            throw new Exception("ZipBinaryImageCaptchaClient: Wrong captcha format.");
        }
        final NodeList elementsByTagName2 = element.getElementsByTagName("CaptchaData");
        if (elementsByTagName2.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure. (CaptchaData node).");
        }
        final byte[] decompress = ZLibTools.decompress(Base64.decode(((Element)elementsByTagName2.item(0)).getFirstChild().getNodeValue()));
        if (decompress == null) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error while decompressing the captcha data.");
        }
        this.m_captchaImage = BinaryImageExtractor.binaryToImage(decompress);
        if (this.m_captchaImage == null) {
            throw new Exception("ZipBinaryImageCaptchaClient: The image is invalid.");
        }
        final NodeList elementsByTagName3 = element.getElementsByTagName("DataCipher");
        if (elementsByTagName3.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure. (DataCipher node).");
        }
        this.m_encodedData = Base64.decode(((Element)elementsByTagName3.item(0)).getFirstChild().getNodeValue());
        final NodeList elementsByTagName4 = element.getElementsByTagName("CaptchaKeyBits");
        if (elementsByTagName4.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure. (CaptchaKeyBits node).");
        }
        this.m_captchaKeyBits = Integer.parseInt(((Element)elementsByTagName4.item(0)).getFirstChild().getNodeValue());
        final NodeList elementsByTagName5 = element.getElementsByTagName("ExtraKeyBits");
        if (elementsByTagName5.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure. (ExtraKeyBits node).");
        }
        this.m_extraKeyBits = Integer.parseInt(((Element)elementsByTagName5.item(0)).getFirstChild().getNodeValue());
        final NodeList elementsByTagName6 = element.getElementsByTagName("CaptchaCharacters");
        if (elementsByTagName6.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure. (CaptchaCharacters node).");
        }
        this.m_characterSet = ((Element)elementsByTagName6.item(0)).getFirstChild().getNodeValue();
        final NodeList elementsByTagName7 = element.getElementsByTagName("CaptchaCharacterNumber");
        if (elementsByTagName7.getLength() == 0) {
            throw new Exception("ZipBinaryImageCaptchaClient: Error in XML structure. (CaptchaCharacterNumber node).");
        }
        this.m_characterNumber = Integer.parseInt(((Element)elementsByTagName7.item(0)).getFirstChild().getNodeValue());
    }
    
    public Image getImage() {
        return this.m_captchaImage;
    }
    
    public String getCharacterSet() {
        return this.m_characterSet;
    }
    
    public int getCharacterNumber() {
        return this.m_characterNumber;
    }
    
    public byte[] solveCaptcha(final String s, final byte[] array) throws Exception {
        if (s.length() != this.m_characterNumber) {
            throw new Exception("ZipBinaryImageCaptchaClient: solveCaptcha: The specified key has an invalid size.");
        }
        final BigInteger bigInteger = new BigInteger(Integer.toString(this.m_characterSet.length()));
        BigInteger add = new BigInteger("0");
        for (int i = 0; i < this.m_characterNumber; ++i) {
            final int index = this.m_characterSet.indexOf(s.substring(i, i + 1));
            if (index == -1) {
                throw new Exception("ZipBinaryImageCaptchaClient: solveCaptcha: The specified key contains invalid characters.");
            }
            add = add.multiply(bigInteger).add(new BigInteger(Integer.toString(index)));
        }
        final byte[] array2 = new byte[this.m_captchaKeyBits / 8];
        for (int j = 0; j < array2.length; ++j) {
            array2[j] = 0;
        }
        final byte[] byteArray = add.toByteArray();
        final int min = Math.min(array2.length, byteArray.length);
        System.arraycopy(byteArray, byteArray.length - min, array2, array2.length - min, min);
        final int n = this.m_extraKeyBits % 8;
        byte[] generateNextKey;
        if (n == 0) {
            generateNextKey = new byte[this.m_extraKeyBits / 8];
        }
        else {
            generateNextKey = new byte[this.m_extraKeyBits / 8 + 1];
        }
        for (int k = 0; k < generateNextKey.length; ++k) {
            generateNextKey[k] = 0;
        }
        boolean b;
        do {
            final byte[] array3 = new byte[16];
            for (int l = 0; l < array3.length; ++l) {
                array3[l] = 0;
            }
            System.arraycopy(array2, 0, array3, array3.length - array2.length, array2.length);
            System.arraycopy(generateNextKey, 0, array3, array3.length - array2.length - generateNextKey.length, generateNextKey.length);
            final MyAES myAES = new MyAES();
            myAES.init(false, array3);
            final int n2 = this.m_encodedData.length / 16;
            final byte[] array4 = new byte[16];
            final byte[] array5 = new byte[16];
            final byte[] array6 = new byte[n2 * 16];
            for (int n3 = 0; n3 < n2; ++n3) {
                System.arraycopy(this.m_encodedData, n3 * 16, array5, 0, 16);
                System.arraycopy(myAES.processBlockECB(array5), 0, array6, n3 * 16, 16);
            }
            b = true;
            for (int n4 = 0; n4 < array.length; ++n4) {
                if (array6[n4] != array[n4]) {
                    b = false;
                }
            }
            if (!b) {
                try {
                    generateNextKey = this.generateNextKey(generateNextKey, n);
                    continue;
                }
                catch (Exception ex) {
                    return null;
                }
            }
            return array6;
        } while (!b);
        return null;
    }
    
    private byte[] generateNextKey(final byte[] array, int n) throws Exception {
        n %= 8;
        final byte[] array2 = new byte[array.length];
        int n2 = 1;
        for (int i = array2.length - 1; i >= 0; --i) {
            byte b = array[i];
            if (n2 == 1) {
                ++b;
                if (i != 0 || n == 0) {
                    if (b != 0) {
                        n2 = 0;
                    }
                }
                else {
                    b &= (byte)(255 >>> 8 - n);
                    if (b != 0) {
                        n2 = 0;
                    }
                }
            }
            array2[i] = b;
        }
        if (n2 == 1) {
            throw new Exception("ZipBinaryImageCaptchaClient: generateNextKey: No more keys available.");
        }
        return array2;
    }
}
