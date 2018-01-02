// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.util;

public class ConversionUtil
{
    private static ConversionUtil instance;
    private static char[] hexChar;
    
    public static ConversionUtil getInstance() {
        if (ConversionUtil.instance == null) {
            try {
                ConversionUtil.instance = new ConversionUtil();
            }
            catch (Exception ex) {}
        }
        return ConversionUtil.instance;
    }
    
    public static final String bytesToHex(final byte[] bytes) {
        final StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(ConversionUtil.hexChar[(bytes[i] & 0xF0) >>> 4]);
            sb.append(ConversionUtil.hexChar[bytes[i] & 0xF]);
        }
        return sb.toString();
    }
    
    public static final byte[] hexToBytes(final String hex) {
        final int stringLength = hex.length();
        if ((stringLength & 0x1) != 0x0) {
            throw new IllegalArgumentException("fromHexString requires an even number of hex characters");
        }
        final byte[] b = new byte[stringLength / 2];
        for (int i = 0, j = 0; i < stringLength; i += 2, ++j) {
            final int high = charToNibble(hex.charAt(i));
            final int low = charToNibble(hex.charAt(i + 1));
            b[j] = (byte)(high << 4 | low);
        }
        return b;
    }
    
    private static int charToNibble(final char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        if ('a' <= c && c <= 'f') {
            return c - 'a' + '\n';
        }
        if ('A' <= c && c <= 'F') {
            return c - 'A' + '\n';
        }
        throw new IllegalArgumentException("Invalid hex character: " + c);
    }
    
    static {
        ConversionUtil.instance = null;
        ConversionUtil.hexChar = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
