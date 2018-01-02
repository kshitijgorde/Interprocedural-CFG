// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.util;

public class Array
{
    public static final String b2x(final byte[] pSrc) {
        final StringBuffer result = new StringBuffer(pSrc.length * 2);
        for (int i = 0; i < pSrc.length; ++i) {
            result.append(Character.forDigit(pSrc[i] >> 4 & 0xF, 16));
            result.append(Character.forDigit(pSrc[i] & 0xF, 16));
        }
        return result.toString().toUpperCase();
    }
    
    public static final int[] bytesToInts(final byte[] pSrc) {
        final int[] result = new int[pSrc.length];
        copy(pSrc, result);
        return result;
    }
    
    public static final void copy(final byte[] pSrc, final int[] pDst) {
        if (pSrc.length != pDst.length) {
            throw new ArrayIndexOutOfBoundsException("Source and target arrays are not of same size");
        }
        for (int i = 0; i < pSrc.length; ++i) {
            pDst[i] = (pSrc[i] & 0xFF);
        }
    }
    
    public static final void copy(final int[] pSrc, final byte[] pDst) {
        if (pSrc.length != pDst.length) {
            throw new ArrayIndexOutOfBoundsException("Source and target arrays are not of same size");
        }
        for (int i = 0; i < pSrc.length; ++i) {
            pDst[i] = (byte)pSrc[i];
        }
    }
    
    public static final String i2x(final int pSrc, final int pDstByteSize) {
        return b2x(toBytesMsbf(pSrc, pDstByteSize));
    }
    
    public static final byte[] intsToBytes(final int[] pInts) {
        final byte[] result = new byte[pInts.length];
        copy(pInts, result);
        return result;
    }
    
    public static final byte[] subArray(final byte[] pSrc, final int pSrcPos, final int pLen) {
        final byte[] result = new byte[pLen];
        System.arraycopy(pSrc, pSrcPos, result, 0, pLen);
        return result;
    }
    
    public static final void toBytesLsbf(int pSrc, final byte[] pDst, final int pDstPos, final int pDstLen) {
        for (int i = pDstPos; i < pDstPos + pDstLen; ++i) {
            pDst[i] = (byte)pSrc;
            pSrc >>>= 8;
        }
    }
    
    public static final byte[] toBytesLsbf(final int pSrc, final int pDstLen) {
        final byte[] result = new byte[pDstLen];
        toBytesLsbf(pSrc, result, 0, pDstLen);
        return result;
    }
    
    public static final void toBytesMsbf(int pSrc, final byte[] pDst, final int pDstPos, final int pDstLen) {
        for (int i = pDstPos + pDstLen - 1; i >= pDstPos; --i) {
            pDst[i] = (byte)pSrc;
            pSrc >>>= 8;
        }
    }
    
    public static final byte[] toBytesMsbf(final int pSrc, final int pDstLen) {
        final byte[] result = new byte[pDstLen];
        toBytesMsbf(pSrc, result, 0, pDstLen);
        return result;
    }
    
    public static final int toIntLsbf(final byte[] pSrc, final int pSrcPos, final int pSrcLen) {
        int result = 0;
        for (int i = pSrcPos + pSrcLen - 1; i >= pSrcPos; --i) {
            result = (result << 8 | (pSrc[i] & 0xFF));
        }
        return result;
    }
    
    public static final int toIntMsbf(final byte[] pSrc, final int pSrcPos, final int pSrcLen) {
        int result = 0;
        for (int i = pSrcPos; i < pSrcPos + pSrcLen; ++i) {
            result = (result << 8 | (pSrc[i] & 0xFF));
        }
        return result;
    }
    
    public static final byte[] x2b(final String pSrc) {
        if (pSrc.length() % 2 != 0) {
            throw new IllegalArgumentException("Hexadecimal string not even length");
        }
        final byte[] result = new byte[pSrc.length() / 2];
        for (int i = 0; i < result.length; ++i) {
            result[i] = (byte)(Character.getNumericValue(pSrc.charAt(i * 2)) << 4);
            final byte[] array = result;
            final int n = i;
            array[n] |= (byte)Character.getNumericValue(pSrc.charAt(i * 2 + 1));
        }
        return result;
    }
    
    public static final int x2i(final String pSrc) throws NumberFormatException {
        return Integer.parseInt(pSrc, 16);
    }
}
