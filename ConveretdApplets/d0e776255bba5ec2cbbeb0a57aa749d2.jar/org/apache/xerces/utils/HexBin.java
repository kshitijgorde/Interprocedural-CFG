// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public final class HexBin
{
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 16;
    private static byte[] hexNumberTable;
    private static byte[] lookUpHexAlphabet;
    
    static boolean isHex(final byte b) {
        return HexBin.hexNumberTable[b] != -1;
    }
    
    static boolean isArrayByteHex(final byte[] array) {
        final int length = array.length;
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (!isHex(array[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isHex(final String s) {
        return isArrayByteHex(s.getBytes());
    }
    
    public static byte[] encode(final byte[] array) {
        final int length = array.length;
        final byte[] array2 = new byte[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = HexBin.lookUpHexAlphabet[array[i]];
        }
        return array2;
    }
    
    public static byte[] decode(final byte[] array) {
        final int length = array.length;
        final byte[] array2 = new byte[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = HexBin.hexNumberTable[array[i]];
        }
        return array2;
    }
    
    static {
        HexBin.hexNumberTable = new byte[255];
        HexBin.lookUpHexAlphabet = new byte[16];
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
        for (int n = 0; n < 10; ++n) {
            HexBin.lookUpHexAlphabet[n] = (byte)(48 + n);
        }
        for (int n2 = 10; n2 <= 15; ++n2) {
            HexBin.lookUpHexAlphabet[n2] = (byte)(65 + n2 - 10);
        }
    }
}
