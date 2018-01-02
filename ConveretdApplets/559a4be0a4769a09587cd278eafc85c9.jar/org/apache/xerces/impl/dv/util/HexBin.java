// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.util;

public final class HexBin
{
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 16;
    private static final byte[] hexNumberTable;
    private static final char[] lookUpHexAlphabet;
    
    public static String encode(final byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        final int lengthData = binaryData.length;
        final int lengthEncode = lengthData * 2;
        final char[] encodedData = new char[lengthEncode];
        for (int i = 0; i < lengthData; ++i) {
            int temp = binaryData[i];
            if (temp < 0) {
                temp += 256;
            }
            encodedData[i * 2] = HexBin.lookUpHexAlphabet[temp >> 4];
            encodedData[i * 2 + 1] = HexBin.lookUpHexAlphabet[temp & 0xF];
        }
        return new String(encodedData);
    }
    
    public static byte[] decode(final String encoded) {
        if (encoded == null) {
            return null;
        }
        final int lengthData = encoded.length();
        if (lengthData % 2 != 0) {
            return null;
        }
        final char[] binaryData = encoded.toCharArray();
        final int lengthDecode = lengthData / 2;
        final byte[] decodedData = new byte[lengthDecode];
        for (int i = 0; i < lengthDecode; ++i) {
            final byte temp1 = HexBin.hexNumberTable[binaryData[i * 2]];
            if (temp1 == -1) {
                return null;
            }
            final byte temp2 = HexBin.hexNumberTable[binaryData[i * 2 + 1]];
            if (temp2 == -1) {
                return null;
            }
            decodedData[i] = (byte)(temp1 << 4 | temp2);
        }
        return decodedData;
    }
    
    static {
        hexNumberTable = new byte[255];
        lookUpHexAlphabet = new char[16];
        for (int i = 0; i < 255; ++i) {
            HexBin.hexNumberTable[i] = -1;
        }
        for (int j = 57; j >= 48; --j) {
            HexBin.hexNumberTable[j] = (byte)(j - 48);
        }
        for (int k = 70; k >= 65; --k) {
            HexBin.hexNumberTable[k] = (byte)(k - 65 + 10);
        }
        for (int l = 102; l >= 97; --l) {
            HexBin.hexNumberTable[l] = (byte)(l - 97 + 10);
        }
        for (int m = 0; m < 10; ++m) {
            HexBin.lookUpHexAlphabet[m] = (char)(48 + m);
        }
        for (int i2 = 10; i2 <= 15; ++i2) {
            HexBin.lookUpHexAlphabet[i2] = (char)(65 + i2 - 10);
        }
    }
}
