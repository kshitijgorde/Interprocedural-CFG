// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public final class JavaEncodingSupport implements EncodingSupport
{
    private String fEncName;
    
    public static EncodingSupport getInstance(final String s) {
        return new JavaEncodingSupport(s);
    }
    
    public JavaEncodingSupport(final String fEncName) {
        this.fEncName = fEncName;
    }
    
    private String createString(final byte[] array, final int n, final int n2) {
        try {
            return new String(array, n, n2 - n, this.fEncName);
        }
        catch (UnsupportedEncodingException ex) {
            throw new IOExceptionWrapper(ex);
        }
    }
    
    public boolean isASCIITransparent() {
        return false;
    }
    
    public boolean isSingleByte() {
        return false;
    }
    
    public void convertCharsToBytes(final char[] array, final int n, final int n2, final byte[][] array2, final int[] array3) {
        byte[] array4 = array2[0];
        final int n3 = array3[0];
        final String s = new String(array, n, n2 - n);
        byte[] bytes;
        try {
            bytes = s.getBytes(this.fEncName);
        }
        catch (UnsupportedEncodingException ex) {
            throw new IOExceptionWrapper(ex);
        }
        final int length = bytes.length;
        if (n3 + length > array4.length) {
            int n4;
            for (n4 = array4.length << 1; n3 + length > n4; n4 <<= 1) {}
            array4 = new byte[n4];
            array2[0] = array4;
        }
        System.arraycopy(bytes, 0, array4, n3, length);
        array3[0] = n3 + length;
    }
    
    public int encodeCharacter(final int n, final byte[][] array, final int n2) {
        throw new RuntimeException("JavaEncodingSupport#encodeCharacter()");
    }
    
    public int normalizeLineBreaks(final byte[] array, final int n, final int n2, final boolean[] array2) {
        throw new RuntimeException("JavaEncodingSupport#normalizeLineBreaks()");
    }
    
    public void readCharacters(final InputStream inputStream, final char[][] array, final int[] array2, final boolean[] array3, final byte[] array4, final boolean b) {
        char[] array5 = array[0];
        int n = array2[0];
        int n2 = array3[0] ? 1 : 0;
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, this.fEncName);
            while (true) {
                int n3 = array5.length - n;
                if (n3 == 0) {
                    final int length = array5.length;
                    array5 = new char[length << 1];
                    System.arraycopy(array[0], 0, array5, 0, n);
                    array[0] = array5;
                    n3 += length;
                }
                final int read = inputStreamReader.read(array5, n, n3);
                if (read == -1) {
                    break;
                }
                if (array3 != null) {
                    for (int n4 = n + read, i = n; i < n4; ++i) {
                        char c = array5[i];
                        if (n2 != 0) {
                            n2 = 0;
                            if (c == '\n') {
                                continue;
                            }
                        }
                        if (c == '\r') {
                            c = '\n';
                            n2 = 1;
                        }
                        array5[n++] = c;
                    }
                }
                else {
                    n += read;
                }
            }
            array2[0] = n;
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
    }
    
    public int lengthAsCharacters(final byte[] array, final int n, final int n2) {
        throw new RuntimeException("JavaEncodingSupport#lengthAsCharacters()");
    }
    
    public void convertBytesToChars(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        final String string = this.createString(array, n, n2);
        final int length = string.length();
        char[] array4 = array2[0];
        final int n3 = array3[0];
        final int i = n3 + length;
        if (i > array4.length) {
            int n4;
            for (n4 = array4.length << 1; i > n4; n4 <<= 1) {}
            array4 = new char[n4];
            System.arraycopy(array2[0], 0, array4, 0, n3);
            array2[0] = array4;
        }
        string.getChars(0, length, array4, n3);
        array3[0] = i;
    }
    
    public int decodeCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        throw new RuntimeException("JavaEncodingSupport#decodeCharacter()");
    }
    
    public boolean isValidCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        throw new RuntimeException("JavaEncodingSupport.isValidCharacter() should not be called");
    }
    
    public boolean isInitialNameCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        throw new RuntimeException("JavaEncodingSupport.isInitialNameCharacter() should not be called");
    }
    
    public boolean isNameCharacter(final byte[] array, final int n, final int n2, final int[] array2) {
        throw new RuntimeException("JavaEncodingSupport.isNameCharacter() should not be called");
    }
}
