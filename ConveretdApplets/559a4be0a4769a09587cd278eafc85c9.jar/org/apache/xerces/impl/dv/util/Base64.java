// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.util;

public final class Base64
{
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 64;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final int EIGHTBIT = 8;
    private static final int SIXTEENBIT = 16;
    private static final int SIXBIT = 6;
    private static final int FOURBYTE = 4;
    private static final int SIGN = -128;
    private static final char PAD = '=';
    private static final boolean fDebug = false;
    private static final byte[] base64Alphabet;
    private static final char[] lookUpBase64Alphabet;
    
    protected static boolean isWhiteSpace(final char octect) {
        return octect == ' ' || octect == '\r' || octect == '\n' || octect == '\t';
    }
    
    protected static boolean isPad(final char octect) {
        return octect == '=';
    }
    
    protected static boolean isData(final char octect) {
        return Base64.base64Alphabet[octect] != -1;
    }
    
    protected static boolean isBase64(final char octect) {
        return isWhiteSpace(octect) || isPad(octect) || isData(octect);
    }
    
    public static String encode(final byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        final int lengthDataBits = binaryData.length * 8;
        if (lengthDataBits == 0) {
            return "";
        }
        final int fewerThan24bits = lengthDataBits % 24;
        final int numberTriplets = lengthDataBits / 24;
        final int numberQuartet = (fewerThan24bits != 0) ? (numberTriplets + 1) : numberTriplets;
        final int numberLines = (numberQuartet - 1) / 19 + 1;
        char[] encodedData = null;
        encodedData = new char[numberQuartet * 4 + numberLines];
        byte k = 0;
        byte l = 0;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        int i = 0;
        for (int line = 0; line < numberLines - 1; ++line) {
            for (int quartet = 0; quartet < 19; ++quartet) {
                b1 = binaryData[dataIndex++];
                b2 = binaryData[dataIndex++];
                b3 = binaryData[dataIndex++];
                l = (byte)(b2 & 0xF);
                k = (byte)(b1 & 0x3);
                final byte val1 = ((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0));
                final byte val2 = ((b2 & 0xFFFFFF80) == 0x0) ? ((byte)(b2 >> 4)) : ((byte)(b2 >> 4 ^ 0xF0));
                final byte val3 = ((b3 & 0xFFFFFF80) == 0x0) ? ((byte)(b3 >> 6)) : ((byte)(b3 >> 6 ^ 0xFC));
                encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val1];
                encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val2 | k << 4];
                encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[l << 2 | val3];
                encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[b3 & 0x3F];
                ++i;
            }
            encodedData[encodedIndex++] = '\n';
        }
        while (i < numberTriplets) {
            b1 = binaryData[dataIndex++];
            b2 = binaryData[dataIndex++];
            b3 = binaryData[dataIndex++];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            final byte val4 = ((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0));
            final byte val5 = ((b2 & 0xFFFFFF80) == 0x0) ? ((byte)(b2 >> 4)) : ((byte)(b2 >> 4 ^ 0xF0));
            final byte val6 = ((b3 & 0xFFFFFF80) == 0x0) ? ((byte)(b3 >> 6)) : ((byte)(b3 >> 6 ^ 0xFC));
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val4];
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val5 | k << 4];
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[l << 2 | val6];
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[b3 & 0x3F];
            ++i;
        }
        if (fewerThan24bits == 8) {
            b1 = binaryData[dataIndex];
            k = (byte)(b1 & 0x3);
            final byte val4 = ((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0));
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val4];
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex++] = '=';
            encodedData[encodedIndex++] = '=';
        }
        else if (fewerThan24bits == 16) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            final byte val4 = ((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0));
            final byte val5 = ((b2 & 0xFFFFFF80) == 0x0) ? ((byte)(b2 >> 4)) : ((byte)(b2 >> 4 ^ 0xF0));
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val4];
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[val5 | k << 4];
            encodedData[encodedIndex++] = Base64.lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex++] = '=';
        }
        encodedData[encodedIndex] = '\n';
        return new String(encodedData);
    }
    
    public static byte[] decode(final String encoded) {
        if (encoded == null) {
            return null;
        }
        final char[] base64Data = encoded.toCharArray();
        final int len = removeWhiteSpace(base64Data);
        if (len % 4 != 0) {
            return null;
        }
        final int numberQuadruple = len / 4;
        if (numberQuadruple == 0) {
            return new byte[0];
        }
        byte[] decodedData = null;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        final byte marker0 = 0;
        final byte marker2 = 0;
        char d1 = '\0';
        char d2 = '\0';
        char d3 = '\0';
        char d4 = '\0';
        int i = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        decodedData = new byte[numberQuadruple * 3];
        while (i < numberQuadruple - 1) {
            if (!isData(d1 = base64Data[dataIndex++]) || !isData(d2 = base64Data[dataIndex++]) || !isData(d3 = base64Data[dataIndex++]) || !isData(d4 = base64Data[dataIndex++])) {
                return null;
            }
            b1 = Base64.base64Alphabet[d1];
            b2 = Base64.base64Alphabet[d2];
            b3 = Base64.base64Alphabet[d3];
            b4 = Base64.base64Alphabet[d4];
            decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
            decodedData[encodedIndex++] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
            decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
            ++i;
        }
        if (!isData(d1 = base64Data[dataIndex++]) || !isData(d2 = base64Data[dataIndex++])) {
            return null;
        }
        b1 = Base64.base64Alphabet[d1];
        b2 = Base64.base64Alphabet[d2];
        d3 = base64Data[dataIndex++];
        d4 = base64Data[dataIndex++];
        if (isData(d3) && isData(d4)) {
            b3 = Base64.base64Alphabet[d3];
            b4 = Base64.base64Alphabet[d4];
            decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
            decodedData[encodedIndex++] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
            decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
            return decodedData;
        }
        if (isPad(d3) && isPad(d4)) {
            if ((b2 & 0xF) != 0x0) {
                return null;
            }
            final byte[] tmp = new byte[i * 3 + 1];
            System.arraycopy(decodedData, 0, tmp, 0, i * 3);
            tmp[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
            return tmp;
        }
        else {
            if (isPad(d3) || !isPad(d4)) {
                return null;
            }
            b3 = Base64.base64Alphabet[d3];
            if ((b3 & 0x3) != 0x0) {
                return null;
            }
            final byte[] tmp = new byte[i * 3 + 2];
            System.arraycopy(decodedData, 0, tmp, 0, i * 3);
            tmp[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
            tmp[encodedIndex] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
            return tmp;
        }
    }
    
    protected static int removeWhiteSpace(final char[] data) {
        if (data == null) {
            return 0;
        }
        int newSize = 0;
        for (int len = data.length, i = 0; i < len; ++i) {
            if (!isWhiteSpace(data[i])) {
                data[newSize++] = data[i];
            }
        }
        return newSize;
    }
    
    static {
        base64Alphabet = new byte[255];
        lookUpBase64Alphabet = new char[64];
        for (int i = 0; i < 255; ++i) {
            Base64.base64Alphabet[i] = -1;
        }
        for (int j = 90; j >= 65; --j) {
            Base64.base64Alphabet[j] = (byte)(j - 65);
        }
        for (int k = 122; k >= 97; --k) {
            Base64.base64Alphabet[k] = (byte)(k - 97 + 26);
        }
        for (int l = 57; l >= 48; --l) {
            Base64.base64Alphabet[l] = (byte)(l - 48 + 52);
        }
        Base64.base64Alphabet[43] = 62;
        Base64.base64Alphabet[47] = 63;
        for (int m = 0; m <= 25; ++m) {
            Base64.lookUpBase64Alphabet[m] = (char)(65 + m);
        }
        for (int i2 = 26, j2 = 0; i2 <= 51; ++i2, ++j2) {
            Base64.lookUpBase64Alphabet[i2] = (char)(97 + j2);
        }
        for (int i3 = 52, j3 = 0; i3 <= 61; ++i3, ++j3) {
            Base64.lookUpBase64Alphabet[i3] = (char)(48 + j3);
        }
        Base64.lookUpBase64Alphabet[62] = '+';
        Base64.lookUpBase64Alphabet[63] = '/';
    }
}
