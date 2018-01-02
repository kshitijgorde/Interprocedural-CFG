// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;
import java.io.InputStream;

public abstract class SingleByteEncodingSupport implements EncodingSupport
{
    public char[] byteToCharMap;
    public byte[] contentMap;
    public byte[] nameCharMap;
    
    public SingleByteEncodingSupport(final char[] byteToCharMap, final byte[] contentMap, final byte[] nameCharMap) {
        this.byteToCharMap = byteToCharMap;
        this.contentMap = contentMap;
        this.nameCharMap = nameCharMap;
    }
    
    public boolean isSingleByte() {
        return true;
    }
    
    public void readCharacters(final InputStream inputStream, final char[][] array, final int[] array2, final boolean[] array3, final byte[] array4, final boolean b) {
        try {
            while (true) {
                final int read = inputStream.read(array4, 0, array4.length);
                if (read == -1) {
                    break;
                }
                if (array3 != null) {
                    this.normalizeBytesToChars(array4, 0, read, array, array2, array3);
                }
                else {
                    this.convertBytesToChars(array4, 0, read, array, array2);
                }
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
    }
    
    public int lengthAsCharacters(final byte[] array, final int n, final int n2) {
        return n2 - n;
    }
    
    public void convertBytesToChars(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        final char[] byteToCharMap = this.byteToCharMap;
        int n3 = n;
        char[] array4 = array2[0];
        int i = array3[0];
        final int j = i + (n2 - n3);
        if (j > array4.length) {
            int n4;
            for (n4 = array4.length << 1; j > n4; n4 <<= 1) {}
            array4 = new char[n4];
            System.arraycopy(array2[0], 0, array4, 0, i);
            array2[0] = array4;
        }
        while (i < j) {
            array4[i++] = byteToCharMap[array[n3++] & 0xFF];
        }
        array3[0] = i;
    }
    
    public int decodeCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        if (n < n2) {
            final int n3 = array[n] & 0xFF;
            if (array2 != null) {
                array2[0] = 1;
            }
            return this.byteToCharMap[n3];
        }
        CharConversionError.insufficientInputToDecodeCharacter();
        return -1;
    }
    
    protected int normalizeBytesToChars(final byte[] array, int n, final int n2, final char[][] array2, final int[] array3, final boolean[] array4) {
        final char[] byteToCharMap = this.byteToCharMap;
        int n4;
        final int n3 = n4 = n2 - n;
        char[] array5 = array2[0];
        int n5 = array3[0];
        boolean b = array4[0];
        if (n5 + n3 > array5.length) {
            int n6;
            for (n6 = array5.length << 1; n5 + n3 > n6; n6 <<= 1) {}
            array5 = new char[n6];
            System.arraycopy(array2[0], 0, array5, 0, n5);
            array2[0] = array5;
        }
        while (n4-- > 0) {
            char c = byteToCharMap[array[n++] & 0xFF];
            if (b) {
                b = false;
                if (c == '\n') {
                    continue;
                }
                if (c == '\u0085') {
                    continue;
                }
            }
            if (c == '\r') {
                c = '\n';
                b = true;
            }
            else if (c == '\u0085' || c == '\u2028') {
                c = '\n';
            }
            array5[n5++] = c;
        }
        array3[0] = n5;
        array4[0] = b;
        return n3;
    }
    
    public abstract int normalizeLineBreaks(final byte[] p0, final int p1, final int p2, final boolean[] p3);
    
    public abstract int encodeCharacter(final int p0, final byte[][] p1, final int p2);
    
    public abstract void convertCharsToBytes(final char[] p0, final int p1, final int p2, final byte[][] p3, final int[] p4);
    
    public abstract boolean isASCIITransparent();
}
