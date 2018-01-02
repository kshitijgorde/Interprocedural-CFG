// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class SingleByteStringBuffer implements XMLStringBuffer
{
    private SingleByteEncodingSupport fEncoding;
    private final char[] fByteToCharMap;
    private byte[] fData;
    private int fOffset;
    private byte[][] fDataPointer;
    private int[] fIntPointer;
    private byte fSpaceByteValue;
    
    public SingleByteStringBuffer(final SingleByteEncodingSupport fEncoding) {
        this.fEncoding = fEncoding;
        this.fByteToCharMap = fEncoding.byteToCharMap;
        this.fData = new byte[256];
        this.fDataPointer = new byte[1][];
        this.fIntPointer = new int[1];
        this.fData[0] = 0;
        this.fOffset = 1;
        this.fDataPointer[0] = this.fData;
        this.fEncoding.encodeCharacter(32, this.fDataPointer, this.fOffset);
        this.fSpaceByteValue = this.fData[1];
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
        this.fOffset = this.fEncoding.encodeCharacter(n, this.fDataPointer, this.fOffset);
        this.fData = this.fDataPointer[0];
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
        else {
            while (i < n) {
                final int decodeCharacter = encodingSupport.decodeCharacter(array, i, n, this.fIntPointer);
                i += this.fIntPointer[0];
                this.append(decodeCharacter);
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
        if (xmlString.bytes != null) {
            this.append(xmlString.bytes, xmlString.offset, xmlString.endOffset, xmlString.encoding);
        }
        else {
            this.append(xmlString.chars, xmlString.offset, xmlString.endOffset);
        }
    }
    
    public boolean normalizeTextValue(final XMLString values, final XMLString xmlString) {
        final int fOffset = this.fOffset;
        int n = 0;
        boolean b = true;
        this.append(values);
        final int n2 = this.fOffset - fOffset;
        while (b && n < n2) {
            char c = this.fByteToCharMap[this.fData[fOffset + n] & 0xFF];
            if (c <= ' ') {
                b = false;
            }
            else {
                while (c > ' ' && ++n < n2) {
                    c = this.fByteToCharMap[this.fData[fOffset + n] & 0xFF];
                }
                if (n >= n2 || (c >= ' ' && ++n < n2)) {
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
            if (i < n2 && this.fByteToCharMap[this.fData[fOffset + i] & 0xFF] <= ' ') {
                ++i;
            }
            else {
                if (i == n2) {
                    break;
                }
                if (n3 == 0) {
                    this.fData[fOffset2++] = this.fSpaceByteValue;
                }
                n3 = 0;
                while (i < n2) {
                    final byte b2 = this.fData[fOffset + i];
                    if (this.fByteToCharMap[b2 & 0xFF] == ' ') {
                        break;
                    }
                    this.fData[fOffset2++] = b2;
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
            final char c = this.fByteToCharMap[this.fData[i] & 0xFF];
            if (c == '\n' || c == '\t' || c == '\r') {
                this.fData[i] = this.fSpaceByteValue;
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
        this.fDataPointer[0] = new byte[n2];
        System.arraycopy(this.fData, 0, this.fDataPointer[0], 0, this.fData.length);
        this.fData = this.fDataPointer[0];
    }
}
