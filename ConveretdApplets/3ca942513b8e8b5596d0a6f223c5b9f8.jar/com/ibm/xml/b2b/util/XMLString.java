// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public class XMLString
{
    public static final String EMPTY_STRING;
    private static final boolean RETAIN_CHAR_CONVERSION_BUFFER = true;
    public byte[] bytes;
    public char[] chars;
    public int offset;
    public int endOffset;
    public EncodingSupport encoding;
    public String str;
    public int handle;
    public int flags;
    public static final int FLAGS_NOTWHITESPACE = 1;
    public static final int FLAGS_ALLWHITESPACE = 2;
    protected char[][] fConvertedChars;
    protected int[] fConvertedCharsOffset;
    
    public XMLString() {
        this.handle = -1;
    }
    
    public XMLString(final XMLString xmlString) {
        this.bytes = xmlString.bytes;
        this.chars = xmlString.chars;
        this.offset = xmlString.offset;
        this.endOffset = xmlString.endOffset;
        this.encoding = xmlString.encoding;
        this.str = xmlString.str;
        this.handle = xmlString.handle;
        this.flags = xmlString.flags;
    }
    
    public XMLString(final String str) {
        this.str = str;
        this.handle = -1;
    }
    
    public void clear() {
        this.bytes = null;
        this.chars = null;
        this.encoding = null;
        this.str = null;
        this.handle = -1;
        this.flags = 0;
        if (this.fConvertedChars != null) {}
    }
    
    public void setValues(final byte[] bytes, final int offset, final int endOffset, final EncodingSupport encoding) {
        this.bytes = bytes;
        this.chars = null;
        this.offset = offset;
        this.endOffset = endOffset;
        this.encoding = encoding;
        this.str = null;
        this.handle = -1;
        this.flags = 0;
    }
    
    public void setValues(final char[] chars, final int offset, final int endOffset) {
        this.bytes = null;
        this.chars = chars;
        this.offset = offset;
        this.endOffset = endOffset;
        this.encoding = null;
        this.str = null;
        this.handle = -1;
        this.flags = 0;
    }
    
    public void setValues(final XMLString xmlString) {
        if (xmlString == null) {
            this.clear();
        }
        else {
            this.bytes = xmlString.bytes;
            this.chars = xmlString.chars;
            this.offset = xmlString.offset;
            this.endOffset = xmlString.endOffset;
            this.encoding = xmlString.encoding;
            this.str = xmlString.str;
            this.handle = xmlString.handle;
            this.flags = xmlString.flags;
        }
    }
    
    public String toString() {
        if (this.str == null) {
            if (this.chars == null) {
                if (this.encoding != null) {
                    this.convertToChars();
                    this.str = new String(this.fConvertedChars[0], 0, this.fConvertedCharsOffset[0]);
                }
            }
            else {
                this.str = new String(this.chars, this.offset, this.endOffset - this.offset);
            }
        }
        return this.str;
    }
    
    public int charLength() {
        if (this.chars != null) {
            return this.endOffset - this.offset;
        }
        if (this.str == null) {
            return this.encoding.lengthAsCharacters(this.bytes, this.offset, this.endOffset);
        }
        return this.str.length();
    }
    
    public void getChars(final char[][] array, final int[] array2) {
        if (this.chars == null) {
            if (this.str == null) {
                this.encoding.convertBytesToChars(this.bytes, this.offset, this.endOffset, array, array2);
            }
            else {
                final int length = this.str.length();
                char[] array3 = array[0];
                final int n = array2[0];
                if (n + length > array3.length) {
                    int n2;
                    for (n2 = array3.length << 1; n + length > n2; n2 <<= 1) {}
                    array3 = new char[n2];
                    System.arraycopy(array[0], 0, array3, 0, n);
                    array[0] = array3;
                }
                this.str.getChars(0, length, array3, n);
                array2[0] = n + length;
            }
        }
        else {
            final int n3 = this.endOffset - this.offset;
            char[] array4 = array[0];
            final int n4 = array2[0];
            final int i = n4 + n3;
            if (i > array4.length) {
                int n5;
                for (n5 = array4.length << 1; i > n5; n5 <<= 1) {}
                array4 = new char[n5];
                System.arraycopy(array[0], 0, array4, 0, n4);
                array[0] = array4;
            }
            System.arraycopy(this.chars, this.offset, array4, n4, n3);
            array2[0] = i;
        }
    }
    
    protected final void convertToChars() {
        if (this.fConvertedChars == null) {
            int i;
            for (i = 8; i < this.endOffset - this.offset; i <<= 1) {}
            (this.fConvertedChars = new char[1][])[0] = new char[i];
            this.fConvertedCharsOffset = new int[1];
        }
        this.fConvertedCharsOffset[0] = 0;
        this.encoding.convertBytesToChars(this.bytes, this.offset, this.endOffset, this.fConvertedChars, this.fConvertedCharsOffset);
    }
    
    static {
        EMPTY_STRING = "".intern();
    }
}
