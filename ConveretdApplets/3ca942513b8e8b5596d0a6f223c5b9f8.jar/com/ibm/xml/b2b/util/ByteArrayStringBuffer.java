// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class ByteArrayStringBuffer implements XMLStringBuffer
{
    private byte[] fData;
    private int fOffset;
    private EncodingSupport fEncoding;
    private byte[][] fDataPointer;
    private int[] fIntPointer;
    
    public ByteArrayStringBuffer() {
        this.fData = new byte[256];
        this.fDataPointer = new byte[1][];
        this.fIntPointer = new int[1];
        this.fOffset = 0;
        this.fEncoding = UTF8EncodingSupport.getInstance();
        this.fData[this.fOffset++] = 0;
    }
    
    public void reset(final boolean b) {
        this.fOffset = 1;
    }
    
    public void setOffset(final int fOffset) {
        this.fOffset = fOffset;
    }
    
    public int getOffset() {
        return this.fOffset;
    }
    
    public void append(final int n) {
        if (n < 128) {
            if (this.fOffset == this.fData.length) {
                this.grow(1);
            }
            this.fData[this.fOffset++] = (byte)n;
        }
        else {
            this.fDataPointer[0] = this.fData;
            this.fOffset = this.fEncoding.encodeCharacter(n, this.fDataPointer, this.fOffset);
            this.fData = this.fDataPointer[0];
        }
    }
    
    public void append(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            int char1 = s.charAt(i);
            if (char1 >= 55296) {
                if (char1 < 56320) {
                    if (++i == length) {
                        CharConversionError.missingSecondHalfOfSurrogatePair();
                    }
                    final char char2 = s.charAt(i);
                    if (char2 < '\udc00' || char2 >= '\ue000') {
                        CharConversionError.invalidSecondHalfOfSurrogatePair();
                    }
                    char1 = 65536 + (char1 - 55296 << 10) + (char2 - '\udc00');
                }
                else if (char1 < 57344) {
                    CharConversionError.invalidFirstHalfOfSurrogatePair();
                }
            }
            this.append(char1);
        }
    }
    
    private void append(final byte[] array, int i, final int n, final EncodingSupport encodingSupport) {
        if (encodingSupport == this.fEncoding) {
            final int n2 = n - i;
            if (this.fOffset + n2 > this.fData.length) {
                this.grow(n2);
            }
            System.arraycopy(array, i, this.fData, this.fOffset, n2);
            this.fOffset += n2;
        }
        else if (!encodingSupport.isASCIITransparent()) {
            while (i < n) {
                final int decodeCharacter = encodingSupport.decodeCharacter(array, i, n, this.fIntPointer);
                i += this.fIntPointer[0];
                this.append(decodeCharacter);
            }
        }
        else {
            if (n - i > this.fData.length - this.fOffset) {
                this.grow(n - i);
            }
            while (i < n) {
                final byte b = array[i++];
                if (b >= 0) {
                    this.fData[this.fOffset++] = b;
                }
                else {
                    final int decodeCharacter2 = encodingSupport.decodeCharacter(array, i - 1, n, this.fIntPointer);
                    i += this.fIntPointer[0] - 1;
                    this.append(decodeCharacter2);
                    if (n - i <= this.fData.length - this.fOffset) {
                        continue;
                    }
                    this.grow(n - i);
                }
            }
        }
    }
    
    private void append(final char[] array, int i, final int n) {
        while (i < n) {
            int n2 = array[i++];
            if (n2 >= 55296) {
                if (n2 < 56320) {
                    if (i == n) {
                        CharConversionError.missingSecondHalfOfSurrogatePair();
                    }
                    final char c = array[i++];
                    if (c < '\udc00' || c >= '\ue000') {
                        CharConversionError.invalidSecondHalfOfSurrogatePair();
                    }
                    n2 = 65536 + (n2 - 55296 << 10) + (c - '\udc00');
                }
                else if (n2 < 57344) {
                    CharConversionError.invalidFirstHalfOfSurrogatePair();
                }
            }
            this.append(n2);
        }
    }
    
    public void append(final XMLString xmlString) {
        if (xmlString.chars != null) {
            this.append(xmlString.chars, xmlString.offset, xmlString.endOffset);
        }
        else {
            this.append(xmlString.bytes, xmlString.offset, xmlString.endOffset, xmlString.encoding);
        }
    }
    
    public boolean normalizeTextValue(final XMLString values, final XMLString xmlString) {
        final int fOffset = this.fOffset;
        int n = 0;
        boolean b = true;
        this.append(values);
        final int n2 = this.fOffset - fOffset;
        while (b && n < n2) {
            byte b2 = this.fData[fOffset + n];
            if (b2 <= 32) {
                b = false;
            }
            else {
                while (b2 > 32 && ++n < n2) {
                    b2 = this.fData[fOffset + n];
                }
                if (n >= n2 || (b2 >= 32 && ++n < n2)) {
                    continue;
                }
                b = false;
            }
        }
        if (b) {
            this.fOffset = fOffset;
            return false;
        }
        int fOffset2 = fOffset;
        final int fOffset3 = this.fOffset;
        int n3 = 1;
        int i = 0;
        while (true) {
            if (i < n2 && this.fData[fOffset + i] <= 32) {
                ++i;
            }
            else {
                if (i == n2) {
                    break;
                }
                if (n3 == 0) {
                    this.fData[fOffset2++] = 32;
                }
                n3 = 0;
                while (i < n2) {
                    final byte b3 = this.fData[fOffset + i];
                    if (b3 == 32) {
                        break;
                    }
                    this.fData[fOffset2++] = b3;
                    ++i;
                }
            }
        }
        if (this.fOffset == fOffset3) {
            this.fOffset = fOffset2;
        }
        if (xmlString != null) {
            xmlString.setValues(values);
        }
        values.setValues(this.fData, fOffset, fOffset2, this.fEncoding);
        return true;
    }
    
    public void normalizedAppend(final XMLString xmlString) {
        final int fOffset = this.fOffset;
        this.append(xmlString);
        for (int fOffset2 = this.fOffset, i = fOffset; i < fOffset2; ++i) {
            final byte b = this.fData[i];
            if (b == 10 || b == 9 || b == 13) {
                this.fData[i] = 32;
            }
        }
    }
    
    public void normalizePublicID(XMLString xmlString) {
        int n = xmlString.offset;
        int n2 = xmlString.endOffset;
        XMLString xmlString2 = null;
        if (n == n2) {
            return;
        }
        if (xmlString.bytes != null && !xmlString.encoding.isASCIITransparent()) {
            final char[][] array = new char[1][xmlString.charLength()];
            final int[] array2 = { 0 };
            xmlString.getChars(array, array2);
            xmlString2 = xmlString;
            xmlString = new XMLString();
            xmlString.setValues(array[0], 0, array2[0]);
            n = xmlString.offset;
            n2 = xmlString.endOffset;
        }
        final int n3 = n;
        int n4 = 1;
        int n6;
        if (xmlString.bytes != null) {
            final byte[] bytes = xmlString.bytes;
            while (n4 != 0 && n < n2) {
                byte b = bytes[n];
                if (b <= 32) {
                    n4 = 0;
                }
                else {
                    while (b > 32 && ++n < n2) {
                        b = bytes[n];
                    }
                    if (n >= n2 || (b >= 32 && ++n != n2)) {
                        continue;
                    }
                    n4 = 0;
                }
            }
            if (n4 != 0) {
                return;
            }
            int i = n3;
            int n5 = 1;
            n6 = this.getOffset();
            while (true) {
                if (i < n2 && bytes[i] <= 32) {
                    ++i;
                }
                else {
                    if (i == n2) {
                        break;
                    }
                    if (n5 == 0) {
                        this.append(32);
                    }
                    n5 = 0;
                    while (i < n2) {
                        final byte b2 = bytes[i];
                        if (b2 <= 32) {
                            break;
                        }
                        this.append(b2);
                        ++i;
                    }
                }
            }
        }
        else {
            final char[] chars = xmlString.chars;
            while (n4 != 0 && n < n2) {
                char c = chars[n];
                if (c <= ' ') {
                    n4 = 0;
                }
                else {
                    while (c > ' ' && ++n < n2) {
                        c = chars[n];
                    }
                    if (n >= n2 || (c >= ' ' && ++n != n2)) {
                        continue;
                    }
                    n4 = 0;
                }
            }
            if (n4 != 0) {
                return;
            }
            int j = n3;
            int n7 = 1;
            n6 = this.getOffset();
            while (true) {
                if (j < n2 && chars[j] <= ' ') {
                    ++j;
                }
                else {
                    if (j == n2) {
                        break;
                    }
                    if (n7 == 0) {
                        this.append(32);
                    }
                    n7 = 0;
                    while (j < n2) {
                        final char c2 = chars[j];
                        if (c2 <= ' ') {
                            break;
                        }
                        this.append(c2);
                        ++j;
                    }
                }
            }
            if (xmlString2 != null) {
                xmlString = xmlString2;
            }
        }
        this.setStringValues(n6, this.getOffset(), xmlString);
    }
    
    public void setStringValues(final int n, final int n2, final XMLString xmlString) {
        xmlString.setValues(this.fData, n, n2, this.fEncoding);
    }
    
    private void grow(final int n) {
        int n2;
        for (n2 = this.fData.length << 1; this.fOffset + n > n2; n2 <<= 1) {}
        final byte[] fData = new byte[n2];
        System.arraycopy(this.fData, 0, fData, 0, this.fData.length);
        this.fData = fData;
    }
}
