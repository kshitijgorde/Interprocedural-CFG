// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class CharArrayStringBuffer implements XMLStringBuffer
{
    private char[][] fDataPointer;
    private int[] fOffsetPointer;
    private char[] fData;
    private int fOffset;
    
    public CharArrayStringBuffer() {
        this.fDataPointer = new char[1][];
        this.fOffsetPointer = new int[1];
        this.fData = new char[256];
        this.fDataPointer[0] = this.fData;
        this.fOffset = 0;
        this.fData[this.fOffset++] = '\0';
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
        if (n < 65536) {
            if (this.fOffset == this.fData.length) {
                this.grow(1);
            }
            this.fData[this.fOffset++] = (char)n;
        }
        else {
            final int n2 = n - 65536;
            if (this.fOffset + 2 > this.fData.length) {
                this.grow(2);
            }
            this.fData[this.fOffset++] = (char)(55296 + (n2 >> 10));
            this.fData[this.fOffset++] = (char)(56320 + (n2 & 0x3FF));
        }
    }
    
    public void append(final String s) {
        final int length = s.length();
        if (this.fOffset + length > this.fData.length) {
            this.grow(length);
        }
        s.getChars(0, length, this.fData, this.fOffset);
        this.fOffset += length;
    }
    
    private void append(final char[] array, final int n, final int n2) {
        final int n3 = n2 - n;
        if (n3 > this.fData.length - this.fOffset) {
            this.grow(n3);
        }
        System.arraycopy(array, n, this.fData, this.fOffset, n3);
        this.fOffset += n3;
    }
    
    private void append(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        this.fOffsetPointer[0] = this.fOffset;
        encodingSupport.convertBytesToChars(array, n, n2, this.fDataPointer, this.fOffsetPointer);
        this.fData = this.fDataPointer[0];
        this.fOffset = this.fOffsetPointer[0];
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
            char c = this.fData[fOffset + n];
            if (c <= ' ') {
                b = false;
            }
            else {
                while (c > ' ' && ++n < n2) {
                    c = this.fData[fOffset + n];
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
            if (i < n2 && this.fData[fOffset + i] <= ' ') {
                ++i;
            }
            else {
                if (i == n2) {
                    break;
                }
                if (n3 == 0) {
                    this.fData[fOffset2++] = ' ';
                }
                n3 = 0;
                while (i < n2) {
                    final char c2 = this.fData[fOffset + i];
                    if (c2 == ' ') {
                        break;
                    }
                    this.fData[fOffset2++] = c2;
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
        values.setValues(this.fData, fOffset, fOffset2);
        return true;
    }
    
    public void normalizedAppend(final XMLString xmlString) {
        final int fOffset = this.fOffset;
        this.append(xmlString);
        for (int fOffset2 = this.fOffset, i = fOffset; i < fOffset2; ++i) {
            final char c = this.fData[i];
            if (c == '\n' || c == '\t' || c == '\r') {
                this.fData[i] = ' ';
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
        xmlString.setValues(this.fData, n, n2);
    }
    
    private void grow(final int n) {
        int n2;
        for (n2 = this.fData.length << 1; this.fOffset + n > n2; n2 <<= 1) {}
        this.fDataPointer[0] = new char[n2];
        System.arraycopy(this.fData, 0, this.fDataPointer[0], 0, this.fData.length);
        this.fData = this.fDataPointer[0];
    }
}
