// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;
import java.io.InputStream;

public final class UCSEncodingSupport implements EncodingSupport
{
    public static final int UTF_16BE = 0;
    public static final int UTF_16LE = 1;
    public static final int UTF_32BE = 2;
    public static final int UTF_32LE = 3;
    public static final int UTF_16 = 4;
    public static final int ISO_10646_UCS_2 = 5;
    public static final int ISO_10646_UCS_4 = 6;
    private static UCSEncodingSupport[] fgSingletons;
    private int fOriginalEncodingType;
    private int fEncodingType;
    private boolean fPermitBOM;
    private int fWidth;
    private int fWidthShift;
    
    public static EncodingSupport getInstance(final int n) {
        if (n <= 3) {
            if (UCSEncodingSupport.fgSingletons[n] == null) {
                UCSEncodingSupport.fgSingletons[n] = new UCSEncodingSupport(n);
            }
            return UCSEncodingSupport.fgSingletons[n];
        }
        return new UCSEncodingSupport(n);
    }
    
    public boolean isASCIITransparent() {
        return false;
    }
    
    public boolean isSingleByte() {
        return false;
    }
    
    public void convertCharsToBytes(final char[] array, final int n, final int n2, final byte[][] array2, final int[] array3) {
        int i = n;
        byte[] array4 = array2[0];
        int j = array3[0];
        int k = j + (n2 - i << this.fWidthShift);
        if (k > array4.length) {
            int n3;
            for (n3 = array4.length << 1; k > n3; n3 <<= 1) {}
            array4 = new byte[n3];
            System.arraycopy(array2[0], 0, array4, 0, j);
            array2[0] = array4;
        }
        switch (this.fEncodingType) {
            case 0: {
                while (i < n2) {
                    final char c = array[i++];
                    array4[j++] = (byte)(c >> 8);
                    array4[j++] = (byte)c;
                }
                break;
            }
            case 1: {
                while (i < n2) {
                    final char c2 = array[i++];
                    array4[j++] = (byte)c2;
                    array4[j++] = (byte)(c2 >> 8);
                }
                break;
            }
            case 2: {
                while (j < k) {
                    int n4 = array[i++];
                    if (n4 >= 55296) {
                        if (n4 < 56320) {
                            if (i < n2) {
                                final char c3 = array[i++];
                                if (c3 >= '\udc00' && c3 < '\ue000') {
                                    n4 = 65536 + (n4 - 55296 << 10) + (c3 - '\udc00');
                                }
                                else {
                                    CharConversionError.invalidSecondHalfOfSurrogatePair();
                                }
                            }
                            else {
                                CharConversionError.missingSecondHalfOfSurrogatePair();
                            }
                            k -= this.fWidth;
                        }
                        else if (n4 < 57344) {
                            CharConversionError.invalidFirstHalfOfSurrogatePair();
                        }
                    }
                    array4[j++] = (byte)(n4 >> 24 & 0xFF);
                    array4[j++] = (byte)(n4 >> 16 & 0xFF);
                    array4[j++] = (byte)(n4 >> 8 & 0xFF);
                    array4[j++] = (byte)(n4 & 0xFF);
                }
                break;
            }
            case 3: {
                while (j < k) {
                    int n5 = array[i++];
                    if (n5 >= 55296) {
                        if (n5 < 56320) {
                            if (i < n2) {
                                final char c4 = array[i++];
                                if (c4 >= '\udc00' && c4 < '\ue000') {
                                    n5 = 65536 + (n5 - 55296 << 10) + (c4 - '\udc00');
                                }
                                else {
                                    CharConversionError.invalidSecondHalfOfSurrogatePair();
                                }
                            }
                            else {
                                CharConversionError.missingSecondHalfOfSurrogatePair();
                            }
                            k -= this.fWidth;
                        }
                        else if (n5 < 57344) {
                            CharConversionError.invalidFirstHalfOfSurrogatePair();
                        }
                    }
                    array4[j++] = (byte)(n5 & 0xFF);
                    array4[j++] = (byte)(n5 >> 8 & 0xFF);
                    array4[j++] = (byte)(n5 >> 16 & 0xFF);
                    array4[j++] = (byte)(n5 >> 24 & 0xFF);
                }
                break;
            }
            default: {
                return;
            }
        }
        array3[0] = j;
    }
    
    public int encodeCharacter(final int n, final byte[][] array, final int n2) {
        throw new RuntimeException("UCSEncodingSupport#encodeCharacter()");
    }
    
    public int normalizeLineBreaks(final byte[] array, final int n, final int n2, final boolean[] array2) {
        throw new RuntimeException("UCSEncodingSupport#normalizeLineBreaks()");
    }
    
    public void readCharacters(final InputStream inputStream, final char[][] array, final int[] array2, final boolean[] array3, final byte[] array4, final boolean b) {
        int n = 0;
        try {
            boolean b2 = false;
            int i = 0;
            if (b && this.fPermitBOM) {
                i = this.checkForUCSBOM(inputStream, array4);
            }
            if (i < 0) {
                i += this.fWidth;
                b2 = true;
            }
            do {
                final int n2 = i + n;
                int n3;
                if (array3 != null) {
                    n3 = this.normalizeBytesToChars(array4, 0, n2, array, array2, array3);
                }
                else {
                    n3 = this.convertBytesToCharsPartial(array4, 0, n2, array, array2);
                }
                n = n2 - n3;
                if (n > 0) {
                    int j = 0;
                    do {
                        array4[j++] = array4[n3++];
                    } while (j < n);
                }
                if (b2) {
                    break;
                }
                i = inputStream.read(array4, n, array4.length - n);
            } while (i >= 0);
            if (n != 0) {
                CharConversionError.partialMultiPartCharacterSequence();
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
    }
    
    private int checkForUCSBOM(final InputStream inputStream, final byte[] array) throws IOException {
        int n = 0;
        final int read = inputStream.read();
        if (read == 254) {
            final int read2 = inputStream.read();
            if (read2 == 255) {
                return 0;
            }
            if (this.fOriginalEncodingType == 4) {
                CharConversionError.byteOrderMarkRequired();
            }
            else {
                array[n++] = -2;
                if (read2 != -1) {
                    array[n++] = (byte)read2;
                    return n;
                }
            }
        }
        else if (read == 255) {
            final int read3 = inputStream.read();
            if (read3 == 254) {
                if (this.fWidth == 2) {
                    this.fEncodingType = 1;
                    return 0;
                }
                final int read4 = inputStream.read();
                if (read4 == 0) {
                    final int read5 = inputStream.read();
                    if (read5 == 0) {
                        this.fEncodingType = 3;
                        return 0;
                    }
                    array[n++] = -1;
                    array[n++] = -2;
                    array[n++] = 0;
                    if (read5 != -1) {
                        array[n++] = (byte)read5;
                        return n;
                    }
                }
                else {
                    array[n++] = -1;
                    array[n++] = -2;
                    if (read4 != -1) {
                        array[n++] = (byte)read4;
                        return n;
                    }
                }
            }
            else if (this.fOriginalEncodingType == 4) {
                CharConversionError.byteOrderMarkRequired();
            }
            else {
                array[n++] = -2;
                if (read3 != -1) {
                    array[n++] = (byte)read3;
                    return n;
                }
            }
        }
        else if (this.fOriginalEncodingType == 4) {
            CharConversionError.byteOrderMarkRequired();
        }
        else if (read == 0 && this.fWidth == 4) {
            final int read6 = inputStream.read();
            if (read6 == 0) {
                final int read7 = inputStream.read();
                if (read7 == 254) {
                    final int read8 = inputStream.read();
                    if (read8 == 255) {
                        return 0;
                    }
                    array[n++] = 0;
                    array[n++] = 0;
                    array[n++] = -2;
                    if (read8 != -1) {
                        array[n++] = (byte)read8;
                        return n;
                    }
                }
                else {
                    array[n++] = 0;
                    array[n++] = 0;
                    if (read7 != -1) {
                        array[n++] = (byte)read7;
                        return n;
                    }
                }
            }
            else {
                array[n++] = 0;
                if (read6 != -1) {
                    array[n++] = (byte)read6;
                    return n;
                }
            }
        }
        else if (read != -1) {
            array[n++] = (byte)read;
            return n;
        }
        return n - this.fWidth;
    }
    
    public int lengthAsCharacters(final byte[] array, final int n, final int n2) {
        final int n3 = n2 - n;
        if ((n3 & this.fWidth - 1) != 0x0) {
            CharConversionError.partialMultiPartCharacterSequence();
        }
        return n3 >> this.fWidthShift;
    }
    
    public void convertBytesToChars(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        if (this.convertBytesToCharsPartial(array, n, n2, array2, array3) != n2) {
            CharConversionError.partialMultiPartCharacterSequence();
        }
    }
    
    private int convertBytesToCharsPartial(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        int i = n;
        final int n3 = n2 - i >> this.fWidthShift;
        final int n4 = i + (n3 << this.fWidthShift);
        char[] array4 = array2[0];
        int n5 = array3[0];
        int j = n5 + n3;
        if (j > array4.length) {
            int n6;
            for (n6 = array4.length << 1; j > n6; n6 <<= 1) {}
            array4 = new char[n6];
            System.arraycopy(array2[0], 0, array4, 0, n5);
            array2[0] = array4;
        }
        switch (this.fEncodingType) {
            case 0: {
                while (i < n4) {
                    array4[n5++] = (char)(((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF));
                }
                break;
            }
            case 1: {
                while (i < n4) {
                    array4[n5++] = (char)(((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF));
                }
                break;
            }
            case 2: {
                while (i < n4) {
                    final int n7 = ((array[i++] & 0xFF) << 24) + ((array[i++] & 0xFF) << 16) + ((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF);
                    if (n7 < 65536) {
                        array4[n5++] = (char)n7;
                    }
                    else {
                        final int n8 = n7 - 65536;
                        if (++j > array4.length) {
                            int n9;
                            for (n9 = array4.length << 1; j > n9; n9 <<= 1) {}
                            array4 = new char[n9];
                            System.arraycopy(array2[0], 0, array4, 0, n5);
                            array2[0] = array4;
                        }
                        array4[n5++] = (char)(55296 + (n8 >> 10));
                        array4[n5++] = (char)(56320 + (n8 & 0x3FF));
                    }
                }
                break;
            }
            case 3: {
                while (i < n4) {
                    final int n10 = ((array[i++] & 0xFF) << 24) + ((array[i++] & 0xFF) << 16) + ((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF);
                    if (n10 < 65536) {
                        array4[n5++] = (char)n10;
                    }
                    else {
                        final int n11 = n10 - 65536;
                        if (++j > array4.length) {
                            int n12;
                            for (n12 = array4.length << 1; j > n12; n12 <<= 1) {}
                            array4 = new char[n12];
                            System.arraycopy(array2[0], 0, array4, 0, n5);
                            array2[0] = array4;
                        }
                        array4[n5++] = (char)(55296 + (n11 >> 10));
                        array4[n5++] = (char)(56320 + (n11 & 0x3FF));
                    }
                }
                break;
            }
            default: {
                return -1;
            }
        }
        array3[0] = n5;
        return i;
    }
    
    public int decodeCharacter(final byte[] array, int n, final int n2, final int[] array2) {
        if (n + this.fWidth <= n2) {
            final int n3 = array[n++] & 0xFF;
            final int n4 = array[n++] & 0xFF;
            int n5 = 0;
            switch (this.fEncodingType) {
                case 0: {
                    n5 = (n3 << 8) + n4;
                    break;
                }
                case 1: {
                    n5 = (n4 << 8) + n3;
                    break;
                }
                case 2: {
                    n5 = (n3 << 24) + (n4 << 16) + ((array[n++] & 0xFF) << 8) + (array[n++] & 0xFF);
                    break;
                }
                case 3: {
                    n5 = ((array[n++] & 0xFF) << 24) + ((array[n++] & 0xFF) << 16) + (n4 << 8) + n3;
                    break;
                }
                default: {
                    return -1;
                }
            }
            if (array2 != null) {
                array2[0] = this.fWidth;
            }
            return n5;
        }
        CharConversionError.insufficientInputToDecodeCharacter();
        return -1;
    }
    
    private UCSEncodingSupport(final int n) {
        this.fOriginalEncodingType = n;
        switch (this.fEncodingType = n) {
            case 0: {
                this.fWidth = 2;
                this.fWidthShift = 1;
                break;
            }
            case 1: {
                this.fWidth = 2;
                this.fWidthShift = 1;
                break;
            }
            case 2: {
                this.fWidth = 4;
                this.fWidthShift = 2;
                break;
            }
            case 3: {
                this.fWidth = 4;
                this.fWidthShift = 2;
                break;
            }
            case 4:
            case 5: {
                this.fEncodingType = 0;
                this.fPermitBOM = true;
                this.fWidth = 2;
                this.fWidthShift = 1;
                break;
            }
            case 6: {
                this.fEncodingType = 2;
                this.fPermitBOM = true;
                this.fWidth = 4;
                this.fWidthShift = 2;
                break;
            }
        }
    }
    
    private int normalizeBytesToChars(final byte[] array, int i, final int n, final char[][] array2, final int[] array3, final boolean[] array4) {
        final int n2 = n - i >> this.fWidthShift;
        final int n3 = i + (n2 << this.fWidthShift);
        char[] array5 = array2[0];
        int n4 = array3[0];
        int j = n4 + n2;
        boolean b = array4[0];
        if (j > array5.length) {
            int n5;
            for (n5 = array5.length << 1; j > n5; n5 <<= 1) {}
            array5 = new char[n5];
            System.arraycopy(array2[0], 0, array5, 0, n4);
            array2[0] = array5;
        }
        switch (this.fEncodingType) {
            case 0: {
                while (i < n3) {
                    int n6 = ((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF);
                    if (b) {
                        b = false;
                        if (n6 == 10) {
                            continue;
                        }
                    }
                    if (n6 == 13) {
                        n6 = 10;
                        b = true;
                    }
                    array5[n4++] = (char)n6;
                }
                break;
            }
            case 1: {
                while (i < n3) {
                    int n7 = ((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF);
                    if (b) {
                        b = false;
                        if (n7 == 10) {
                            continue;
                        }
                    }
                    if (n7 == 13) {
                        n7 = 10;
                        b = true;
                    }
                    array5[n4++] = (char)n7;
                }
                break;
            }
            case 2: {
                while (i < n3) {
                    int n8 = ((array[i++] & 0xFF) << 24) + ((array[i++] & 0xFF) << 16) + ((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF);
                    if (n8 < 65536) {
                        if (b) {
                            b = false;
                            if (n8 == 10) {
                                continue;
                            }
                        }
                        if (n8 == 13) {
                            n8 = 10;
                            b = true;
                        }
                        array5[n4++] = (char)n8;
                    }
                    else {
                        final int n9 = n8 - 65536;
                        if (++j > array5.length) {
                            int n10;
                            for (n10 = array5.length << 1; j > n10; n10 <<= 1) {}
                            array5 = new char[n10];
                            System.arraycopy(array2[0], 0, array5, 0, n4);
                            array2[0] = array5;
                        }
                        array5[n4++] = (char)(55296 + (n9 >> 10));
                        array5[n4++] = (char)(56320 + (n9 & 0x3FF));
                    }
                }
                break;
            }
            case 3: {
                while (i < n3) {
                    int n11 = ((array[i++] & 0xFF) << 24) + ((array[i++] & 0xFF) << 16) + ((array[i++] & 0xFF) << 8) + (array[i++] & 0xFF);
                    if (n11 < 65536) {
                        if (b) {
                            b = false;
                            if (n11 == 10) {
                                continue;
                            }
                        }
                        if (n11 == 13) {
                            n11 = 10;
                            b = true;
                        }
                        array5[n4++] = (char)n11;
                    }
                    else {
                        final int n12 = n11 - 65536;
                        if (++j > array5.length) {
                            int n13;
                            for (n13 = array5.length << 1; j > n13; n13 <<= 1) {}
                            array5 = new char[n13];
                            System.arraycopy(array2[0], 0, array5, 0, n4);
                            array2[0] = array5;
                        }
                        array5[n4++] = (char)(55296 + (n12 >> 10));
                        array5[n4++] = (char)(56320 + (n12 & 0x3FF));
                    }
                }
                break;
            }
            default: {
                return -1;
            }
        }
        array3[0] = n4;
        array4[0] = b;
        return i;
    }
    
    static {
        UCSEncodingSupport.fgSingletons = new UCSEncodingSupport[4];
    }
}
