// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;
import java.io.InputStream;

public final class UTF8EncodingSupport implements EncodingSupport
{
    private static final int[] fgMultiByteLength;
    private static final int[] fgFirstByteValueMask;
    private static final EncodingSupport fgSingleton;
    
    public static EncodingSupport getInstance() {
        return UTF8EncodingSupport.fgSingleton;
    }
    
    public boolean isASCIITransparent() {
        return true;
    }
    
    public boolean isSingleByte() {
        return false;
    }
    
    public static void normalizeCharsToBytes(final char[] array, int i, final int n, final byte[][] array2, final int[] array3, final int[] array4, final boolean[] array5) {
        byte[] array6 = array2[0];
        int n2 = array3[0];
        int length = array6.length;
        boolean b = array5[0];
        int n3 = array4[0];
        if (n3 != -1) {
            if (i == n) {
                CharConversionError.missingSecondHalfOfSurrogatePair();
            }
            array4[0] = -1;
            final char c = array[i++];
            if (c >= '\udc00' && c < '\ue000') {
                n3 = 65536 + (n3 - 55296 << 10) + (c - '\udc00');
            }
            else {
                CharConversionError.invalidSecondHalfOfSurrogatePair();
            }
            if (n2 + 4 > length) {
                array6 = (array2[0] = resize(array6));
                length <<= 1;
            }
            array6[n2++] = (byte)(0xF0 | n3 >> 18);
            array6[n2++] = (byte)(0x80 | (0x3F & n3 >> 12));
            array6[n2++] = (byte)(0x80 | (0x3F & n3 >> 6));
            array6[n2++] = (byte)(0x80 | (0x3F & n3));
        }
        while (i < n) {
            int n4 = array[i++];
            if (b) {
                b = false;
                if (n4 == 10) {
                    continue;
                }
            }
            if (n4 < 128) {
                if (n4 == 13) {
                    n4 = 10;
                    b = true;
                }
                if (n2 == length) {
                    array6 = (array2[0] = resize(array6));
                    length <<= 1;
                }
                array6[n2++] = (byte)n4;
            }
            else if (n4 < 2048) {
                if (n2 + 2 > length) {
                    array6 = (array2[0] = resize(array6));
                    length <<= 1;
                }
                array6[n2++] = (byte)(0xC0 | n4 >> 6);
                array6[n2++] = (byte)(0x80 | (0x3F & n4));
            }
            else if (n4 < 55296) {
                if (n2 + 3 > length) {
                    array6 = (array2[0] = resize(array6));
                    length <<= 1;
                }
                array6[n2++] = (byte)(0xE0 | n4 >> 12);
                array6[n2++] = (byte)(0x80 | (0x3F & n4 >> 6));
                array6[n2++] = (byte)(0x80 | (0x3F & n4));
            }
            else if (n4 < 56320) {
                if (i == n) {
                    array4[0] = n4;
                    break;
                }
                final char c2 = array[i++];
                if (c2 >= '\udc00' && c2 < '\ue000') {
                    n4 = 65536 + (n4 - 55296 << 10) + (c2 - '\udc00');
                }
                else {
                    CharConversionError.invalidSecondHalfOfSurrogatePair();
                }
                if (n2 + 4 > length) {
                    array6 = (array2[0] = resize(array6));
                    length <<= 1;
                }
                array6[n2++] = (byte)(0xF0 | n4 >> 18);
                array6[n2++] = (byte)(0x80 | (0x3F & n4 >> 12));
                array6[n2++] = (byte)(0x80 | (0x3F & n4 >> 6));
                array6[n2++] = (byte)(0x80 | (0x3F & n4));
            }
            else if (n4 < 57344) {
                CharConversionError.invalidFirstHalfOfSurrogatePair();
            }
            else {
                if (n2 + 3 > length) {
                    array6 = (array2[0] = resize(array6));
                    length <<= 1;
                }
                array6[n2++] = (byte)(0xE0 | n4 >> 12);
                array6[n2++] = (byte)(0x80 | (0x3F & n4 >> 6));
                array6[n2++] = (byte)(0x80 | (0x3F & n4));
            }
        }
        array3[0] = n2;
        array5[0] = b;
    }
    
    public void convertCharsToBytes(final char[] array, final int n, final int n2, final byte[][] array2, final int[] array3) {
        int i = n;
        byte[] array4 = array2[0];
        int n3 = array3[0];
        int length = array4.length;
        final int n4 = length - n3;
        int n5;
        if (n2 - i <= n4) {
            n5 = n2;
        }
        else {
            n5 = i + n4;
        }
        while (i < n5) {
            final char c = array[i++];
            if (c >= '\u0080') {
                --i;
                break;
            }
            array4[n3++] = (byte)c;
        }
        if (i == n2) {
            array3[0] = n3;
            return;
        }
        while (i < n2) {
            int n6 = array[i++];
            if (n6 < 128) {
                if (n3 == length) {
                    array4 = (array2[0] = resize(array4));
                    length <<= 1;
                }
                array4[n3++] = (byte)n6;
            }
            else if (n6 < 2048) {
                if (n3 + 2 > length) {
                    array4 = (array2[0] = resize(array4));
                    length <<= 1;
                }
                array4[n3++] = (byte)(0xC0 | n6 >> 6);
                array4[n3++] = (byte)(0x80 | (0x3F & n6));
            }
            else if (n6 < 55296) {
                if (n3 + 3 > length) {
                    array4 = (array2[0] = resize(array4));
                    length <<= 1;
                }
                array4[n3++] = (byte)(0xE0 | n6 >> 12);
                array4[n3++] = (byte)(0x80 | (0x3F & n6 >> 6));
                array4[n3++] = (byte)(0x80 | (0x3F & n6));
            }
            else if (n6 < 56320) {
                if (i == n2) {
                    CharConversionError.missingSecondHalfOfSurrogatePair();
                }
                final char c2 = array[i++];
                if (c2 >= '\udc00' && c2 < '\ue000') {
                    n6 = 65536 + (n6 - 55296 << 10) + (c2 - '\udc00');
                }
                else {
                    CharConversionError.invalidSecondHalfOfSurrogatePair();
                }
                if (n3 + 4 > length) {
                    array4 = (array2[0] = resize(array4));
                    length <<= 1;
                }
                array4[n3++] = (byte)(0xF0 | n6 >> 18);
                array4[n3++] = (byte)(0x80 | (0x3F & n6 >> 12));
                array4[n3++] = (byte)(0x80 | (0x3F & n6 >> 6));
                array4[n3++] = (byte)(0x80 | (0x3F & n6));
            }
            else if (n6 < 57344) {
                CharConversionError.invalidFirstHalfOfSurrogatePair();
            }
            else {
                if (n3 + 3 > length) {
                    array4 = (array2[0] = resize(array4));
                    length <<= 1;
                }
                array4[n3++] = (byte)(0xE0 | n6 >> 12);
                array4[n3++] = (byte)(0x80 | (0x3F & n6 >> 6));
                array4[n3++] = (byte)(0x80 | (0x3F & n6));
            }
        }
        array3[0] = n3;
    }
    
    public int encodeCharacter(final int n, final byte[][] array, int n2) {
        byte[] array2 = array[0];
        final int length = array2.length;
        if (n < 128) {
            if (n2 == length) {
                array2 = (array[0] = resize(array2));
            }
            array2[n2++] = (byte)n;
        }
        else if (n < 2048) {
            if (n2 + 2 > length) {
                array2 = (array[0] = resize(array2));
            }
            array2[n2++] = (byte)(0xC0 | n >> 6);
            array2[n2++] = (byte)(0x80 | (0x3F & n));
        }
        else if (n < 65536) {
            if (n2 + 3 > length) {
                array2 = (array[0] = resize(array2));
            }
            array2[n2++] = (byte)(0xE0 | n >> 12);
            array2[n2++] = (byte)(0x80 | (0x3F & n >> 6));
            array2[n2++] = (byte)(0x80 | (0x3F & n));
        }
        else {
            if (n2 + 4 > length) {
                array2 = (array[0] = resize(array2));
            }
            array2[n2++] = (byte)(0xF0 | n >> 18);
            array2[n2++] = (byte)(0x80 | (0x3F & n >> 12));
            array2[n2++] = (byte)(0x80 | (0x3F & n >> 6));
            array2[n2++] = (byte)(0x80 | (0x3F & n));
        }
        return n2;
    }
    
    public int normalizeLineBreaks(final byte[] array, final int n, final int n2, final boolean[] array2) {
        int n3 = n;
        boolean b = array2[0];
        if (!b && n3 < n2) {
            while (array[n3] != 13 && ++n3 < n2) {}
            if (n3 == n2) {
                return n3;
            }
            b = true;
            array[n3++] = 10;
        }
        int i = n3;
        while (i < n2) {
            byte b2 = array[i++];
            if (b) {
                b = false;
                if (b2 == 10) {
                    continue;
                }
            }
            if (b2 == 13) {
                b2 = 10;
                b = true;
            }
            array[n3++] = b2;
        }
        array2[0] = b;
        return n3;
    }
    
    public void readCharacters(final InputStream inputStream, final char[][] array, final int[] array2, final boolean[] array3, final byte[] array4, final boolean b) {
        int n = 0;
        try {
            boolean b2 = false;
            int i = b ? checkUTF8BOM(inputStream, array4) : 0;
            if (i < 0) {
                i += 3;
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
    
    private static int checkUTF8BOM(final InputStream inputStream, final byte[] array) throws IOException {
        int n = 0;
        final int read = inputStream.read();
        if (read == 239) {
            final int read2 = inputStream.read();
            if (read2 == 187) {
                final int read3 = inputStream.read();
                if (read3 == 191) {
                    return 0;
                }
                array[n++] = -17;
                array[n++] = -69;
                if (read3 != -1) {
                    array[n++] = (byte)read3;
                    return n;
                }
            }
            else {
                array[n++] = -17;
                if (read2 != -1) {
                    array[n++] = (byte)read2;
                    return n;
                }
            }
        }
        else if (read != -1) {
            array[n++] = (byte)read;
            return n;
        }
        return n - 3;
    }
    
    public int lengthAsCharacters(final byte[] array, final int n, final int n2) {
        int i = n;
        int n3 = 0;
        while (i < n2) {
            final byte b = array[i++];
            ++n3;
            if (b >= 0) {
                continue;
            }
            final int n4 = b & 0xFF;
            switch (UTF8EncodingSupport.fgMultiByteLength[n4 >> 3]) {
                default: {
                    this.charConversionFailure(1, n4, 0, 0, 0);
                    continue;
                }
                case 1: {
                    continue;
                }
                case 2: {
                    ++i;
                    continue;
                }
                case 3: {
                    i += 2;
                    continue;
                }
                case 4: {
                    i += 3;
                    continue;
                }
            }
        }
        return n3;
    }
    
    public void convertBytesToChars(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        if (this.convertBytesToCharsPartial(array, n, n2, array2, array3) != n2) {
            CharConversionError.partialMultiPartCharacterSequence();
        }
    }
    
    private int convertBytesToCharsPartial(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3) {
        int i = n;
        char[] array4 = array2[0];
        int n3 = array3[0];
        int length = array4.length;
        final int n4 = length - n3;
        int n5;
        if (n2 - i <= n4) {
            n5 = n2;
        }
        else {
            n5 = i + n4;
        }
        while (i < n5) {
            final byte b = array[i++];
            if (b < 0) {
                --i;
                break;
            }
            array4[n3++] = (char)b;
        }
        if (i == n2) {
            array3[0] = n3;
            return i;
        }
    Label_0574:
        while (i < n2) {
            do {
                if (n3 == length) {
                    array4 = (array2[0] = resize(array4));
                    length <<= 1;
                }
                final byte b2 = array[i++];
                if (b2 < 0) {
                    final int n6 = b2 & 0xFF;
                    final int n7 = UTF8EncodingSupport.fgMultiByteLength[n6 >> 3];
                    if (n7 == 0) {
                        this.charConversionFailure(1, n6, 0, 0, 0);
                    }
                    if (i - 1 + n7 > n2) {
                        array3[0] = n3;
                        return i - 1;
                    }
                    final int n8 = n6 & UTF8EncodingSupport.fgFirstByteValueMask[n7];
                    final int n9 = array[i++] & 0xFF;
                    if ((0xC0 & n9) != 0x80) {
                        this.charConversionFailure(2, n6, n9, 0, 0);
                    }
                    if (n7 == 2) {
                        array4[n3++] = (char)((n8 << 6) + (0x3F & n9));
                        continue Label_0574;
                    }
                    final int n10 = array[i++] & 0xFF;
                    if ((0xC0 & n10) != 0x80) {
                        this.charConversionFailure(3, n6, n9, n10, 0);
                    }
                    if (n7 == 3) {
                        array4[n3++] = (char)((n8 << 12) + ((0x3F & n9) << 6) + (0x3F & n10));
                        continue Label_0574;
                    }
                    final int n11 = array[i++] & 0xFF;
                    if ((0xC0 & n11) != 0x80) {
                        this.charConversionFailure(4, n6, n9, n10, n11);
                    }
                    final int n12 = (n8 << 8) + ((0x30 & n9) << 2);
                    if (n12 > 1024 || n12 == 0) {
                        CharConversionError.invalidUTF8SurrogateEncoding();
                    }
                    final int n13 = 55296 + (n12 - 64) + ((0xF & n9) << 2) + ((0x30 & n10) >> 4);
                    final int n14 = 56320 + ((0xF & n10) << 6) + (0x3F & n11);
                    array4[n3++] = (char)n13;
                    if (n3 == length) {
                        array4 = (array2[0] = resize(array4));
                        length <<= 1;
                    }
                    array4[n3++] = (char)n14;
                    continue Label_0574;
                }
                else {
                    array4[n3++] = (char)b2;
                }
            } while (i != n2);
            array3[0] = n3;
            return i;
        }
        array3[0] = n3;
        return i;
    }
    
    public int decodeCharacter(final byte[] array, int n, final int n2, final int[] array2) {
        if (n >= n2) {
            CharConversionError.insufficientInputToDecodeCharacter();
            return -1;
        }
        final byte b = array[n++];
        if (b >= 0) {
            if (array2 != null) {
                array2[0] = 1;
            }
            return b;
        }
        final int n3 = b & 0xFF;
        final int n4 = UTF8EncodingSupport.fgMultiByteLength[n3 >> 3];
        if (n4 <= 0) {
            this.charConversionFailure(1, n3, 0, 0, 0);
            return -1;
        }
        if (array2 != null) {
            array2[0] = n4;
        }
        if (n - 1 + n4 > n2) {
            CharConversionError.insufficientInputToDecodeCharacter();
        }
        final int n5 = n3 & UTF8EncodingSupport.fgFirstByteValueMask[n4];
        final int n6 = array[n++] & 0xFF;
        if ((0xC0 & n6) != 0x80) {
            this.charConversionFailure(2, n3, n6, 0, 0);
        }
        if (n4 == 2) {
            return (n5 << 6) + (0x3F & n6);
        }
        final int n7 = array[n++] & 0xFF;
        if ((0xC0 & n7) != 0x80) {
            this.charConversionFailure(3, n3, n6, n7, 0);
        }
        if (n4 == 3) {
            return (n5 << 12) + ((0x3F & n6) << 6) + (0x3F & n7);
        }
        final int n8 = array[n++] & 0xFF;
        if ((0xC0 & n8) != 0x80) {
            this.charConversionFailure(4, n3, n6, n7, n8);
        }
        return (n5 << 18) + ((0x3F & n6) << 12) + ((0x3F & n7) << 6) + (0x3F & n8);
    }
    
    private static byte[] resize(final byte[] array) {
        final int length = array.length;
        final byte[] array2 = new byte[length << 1];
        System.arraycopy(array, 0, array2, 0, length);
        return array2;
    }
    
    private static char[] resize(final char[] array) {
        final int length = array.length;
        final char[] array2 = new char[length << 1];
        System.arraycopy(array, 0, array2, 0, length);
        return array2;
    }
    
    private void charConversionFailure(final int n, final int n2, final int n3, final int n4, final int n5) {
        CharConversionError.invalidUTF8CharacterEncoding(n, n2, n3, n4, n5);
    }
    
    private int normalizeBytesToChars(final byte[] array, final int n, final int n2, final char[][] array2, final int[] array3, final boolean[] array4) {
        int i = n;
        char[] array5 = array2[0];
        int n3 = array3[0];
        int length = array5.length;
        final int n4 = length - n3;
        boolean b = array4[0];
        int n5;
        if (n2 - i <= n4) {
            n5 = n2;
        }
        else {
            n5 = i + n4;
        }
        while (i < n5) {
            int n6 = array[i++];
            if (!b) {
                if (n6 < 0) {
                    --i;
                    break;
                }
                if (n6 != 13) {
                    array5[n3++] = (char)n6;
                }
                else {
                    b = true;
                    array5[n3++] = '\n';
                }
            }
            else {
                b = false;
                if (n6 == 10) {
                    continue;
                }
                if (n6 < 0) {
                    --i;
                    break;
                }
                if (n6 == 13) {
                    n6 = 10;
                    b = true;
                }
                array5[n3++] = (char)n6;
            }
        }
        if (i == n2) {
            array3[0] = n3;
            array4[0] = b;
            return i;
        }
    Label_0732:
        while (i < n2) {
            do {
                if (n3 == length) {
                    array5 = (array2[0] = resize(array5));
                    length <<= 1;
                }
                int n7 = array[i++];
                if (n7 < 0) {
                    final int n8 = n7 & 0xFF;
                    final int n9 = UTF8EncodingSupport.fgMultiByteLength[n8 >> 3];
                    if (n9 == 0) {
                        this.charConversionFailure(1, n8, 0, 0, 0);
                    }
                    if (i - 1 + n9 > n2) {
                        --i;
                        break Label_0732;
                    }
                    final int n10 = n8 & UTF8EncodingSupport.fgFirstByteValueMask[n9];
                    final int n11 = array[i++] & 0xFF;
                    if ((0xC0 & n11) != 0x80) {
                        this.charConversionFailure(2, n8, n11, 0, 0);
                    }
                    if (n9 == 2) {
                        array5[n3++] = (char)((n10 << 6) + (0x3F & n11));
                        continue Label_0732;
                    }
                    final int n12 = array[i++] & 0xFF;
                    if ((0xC0 & n12) != 0x80) {
                        this.charConversionFailure(3, n8, n11, n12, 0);
                    }
                    if (n9 == 3) {
                        final int n13 = (n10 << 12) + ((0x3F & n11) << 6) + (0x3F & n12);
                        if (n13 >= 55296 && n13 < 57344) {
                            this.charConversionFailure(1, n8, n11, n12, 0);
                        }
                        array5[n3++] = (char)n13;
                        continue Label_0732;
                    }
                    final int n14 = array[i++] & 0xFF;
                    if ((0xC0 & n14) != 0x80) {
                        this.charConversionFailure(4, n8, n11, n12, n14);
                    }
                    final int n15 = (n10 << 8) + ((0x30 & n11) << 2);
                    if (n15 > 1024 || n15 == 0) {
                        CharConversionError.invalidUTF8SurrogateEncoding();
                    }
                    final int n16 = 55296 + (n15 - 64) + ((0xF & n11) << 2) + ((0x30 & n12) >> 4);
                    final int n17 = 56320 + ((0xF & n12) << 6) + (0x3F & n14);
                    array5[n3++] = (char)n16;
                    if (n3 == length) {
                        array5 = (array2[0] = resize(array5));
                        length <<= 1;
                    }
                    array5[n3++] = (char)n17;
                    continue Label_0732;
                }
                else {
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
                    array5[n3++] = (char)n7;
                }
            } while (i != n2);
            array3[0] = n3;
            array4[0] = b;
            return i;
        }
        array3[0] = n3;
        array4[0] = b;
        return i;
    }
    
    static {
        fgMultiByteLength = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 3, 3, 4, 0 };
        fgFirstByteValueMask = new int[] { 0, 0, 31, 15, 7 };
        fgSingleton = new UTF8EncodingSupport();
    }
}
