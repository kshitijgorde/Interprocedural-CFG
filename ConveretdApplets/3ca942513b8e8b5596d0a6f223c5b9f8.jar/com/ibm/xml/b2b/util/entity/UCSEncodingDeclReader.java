// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import java.io.IOException;

final class UCSEncodingDeclReader extends EncodingDeclReader
{
    private static final byte ___ = 0;
    private static final byte WSP = 1;
    private static final byte PER = 9;
    private static final byte LSS = 10;
    private static final byte HYP = 7;
    private static final byte USC = 8;
    private static final byte GTR = 11;
    private static final byte QST = 12;
    private static final byte SQT = 3;
    private static final byte EQL = 2;
    private static final byte DQT = 4;
    private static final byte LTR = 5;
    private static final byte DIG = 6;
    private static final byte[] fgCodePointMapUCS;
    private static final int[] STARTPIXML;
    private static final int[] VERSION;
    private static final int[] VERSION10;
    private static final int[] ENCODING;
    private static final int[] STANDALONE;
    private static final int[] YES;
    private static final int[] NO;
    private static final int[] ENDPI;
    private int fBytesPerChar;
    private boolean fIsBigEndian;
    
    public void setState(final int fBytesPerChar, final boolean fIsBigEndian) {
        this.fBytesPerChar = fBytesPerChar;
        this.fIsBigEndian = fIsBigEndian;
    }
    
    private int readChar() throws IOException {
        final int read = super.fStream.read();
        if (read == -1) {
            return -1;
        }
        final int read2 = super.fStream.read();
        if (read2 == -1) {
            return -1;
        }
        if (this.fBytesPerChar == 2) {
            if (this.fIsBigEndian) {
                return (read << 8) + read2;
            }
            return (read2 << 8) + read;
        }
        else {
            final int read3 = super.fStream.read();
            if (read3 == -1) {
                return -1;
            }
            final int read4 = super.fStream.read();
            if (read4 == -1) {
                return -1;
            }
            if (this.fIsBigEndian) {
                return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
            }
            return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
        }
    }
    
    protected boolean lookingAtCodePoint(final int n) throws IOException {
        super.fStream.mark(this.fBytesPerChar);
        final int char1 = this.readChar();
        super.fStream.reset();
        return char1 != -1 && UCSEncodingDeclReader.fgCodePointMapUCS[char1] == n;
    }
    
    protected boolean skipCodePoint(final int n) throws IOException {
        super.fStream.mark(this.fBytesPerChar);
        final int char1 = this.readChar();
        if (char1 == -1) {
            return false;
        }
        if (UCSEncodingDeclReader.fgCodePointMapUCS[char1] != n) {
            super.fStream.reset();
            return false;
        }
        return true;
    }
    
    protected boolean skipLiteral(final int n) throws IOException {
        int[] array = null;
        int n2 = 0;
        switch (n) {
            case 0: {
                final int[] startpixml = UCSEncodingDeclReader.STARTPIXML;
                super.fStream.mark(this.fBytesPerChar * 6);
                for (int i = 0; i < 5; ++i) {
                    if (this.readChar() != startpixml[i]) {
                        super.fStream.reset();
                        return false;
                    }
                }
                final int char1 = this.readChar();
                if (char1 == -1) {
                    super.fStream.reset();
                    return false;
                }
                if (UCSEncodingDeclReader.fgCodePointMapUCS[char1] != 1) {
                    super.fStream.reset();
                    return false;
                }
                return true;
            }
            case 1: {
                array = UCSEncodingDeclReader.VERSION;
                n2 = 7;
                break;
            }
            case 2: {
                array = UCSEncodingDeclReader.VERSION10;
                n2 = 3;
                break;
            }
            case 3: {
                array = UCSEncodingDeclReader.ENCODING;
                n2 = 8;
                break;
            }
            case 4: {
                array = UCSEncodingDeclReader.STANDALONE;
                n2 = 10;
                break;
            }
            case 5: {
                array = UCSEncodingDeclReader.YES;
                n2 = 3;
                break;
            }
            case 6: {
                array = UCSEncodingDeclReader.NO;
                n2 = 2;
                break;
            }
            case 7: {
                array = UCSEncodingDeclReader.ENDPI;
                n2 = 2;
                break;
            }
            default: {
                return false;
            }
        }
        super.fStream.mark(this.fBytesPerChar * n2);
        for (int j = 0; j < n2; ++j) {
            if (this.readChar() != array[j]) {
                super.fStream.reset();
                return false;
            }
        }
        return true;
    }
    
    protected boolean skipSpaces() throws IOException {
        boolean b = false;
        while (this.skipCodePoint(1)) {
            b = true;
        }
        return b;
    }
    
    protected boolean scanEncodingName(final int n) throws IOException {
        super.fStream.mark(this.fBytesPerChar);
        final int char1 = this.readChar();
        if (char1 == -1) {
            super.fStream.reset();
            return false;
        }
        if (UCSEncodingDeclReader.fgCodePointMapUCS[char1] != 5) {
            super.fStream.reset();
            return false;
        }
        char[] array = super.fEncodingNameBuffer;
        if (array == null) {
            final char[] fEncodingNameBuffer = new char[64];
            super.fEncodingNameBuffer = fEncodingNameBuffer;
            array = fEncodingNameBuffer;
        }
        int n2 = 0;
        array[n2++] = (char)char1;
        while (true) {
            super.fStream.mark(this.fBytesPerChar);
            final int char2 = this.readChar();
            if (char2 == -1) {
                super.fStream.reset();
                return false;
            }
            final byte b = UCSEncodingDeclReader.fgCodePointMapUCS[char2];
            if (b == n) {
                super.fStream.reset();
                super.fEncodingName = new String(array, 0, n2);
                return true;
            }
            switch (b) {
                case 5:
                case 6:
                case 7:
                case 8:
                case 9: {
                    if (n2 == array.length) {
                        System.arraycopy(array, 0, super.fEncodingNameBuffer = new char[n2 << 1], 0, n2);
                        array = super.fEncodingNameBuffer;
                    }
                    array[n2++] = (char)char2;
                    continue;
                }
                default: {
                    super.fStream.reset();
                    return false;
                }
            }
        }
    }
    
    static {
        fgCodePointMapUCS = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 7, 9, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 10, 2, 11, 12, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 8, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0 };
        STARTPIXML = new int[] { 60, 63, 120, 109, 108 };
        VERSION = new int[] { 118, 101, 114, 115, 105, 111, 110 };
        VERSION10 = new int[] { 49, 46, 48 };
        ENCODING = new int[] { 101, 110, 99, 111, 100, 105, 110, 103 };
        STANDALONE = new int[] { 115, 116, 97, 110, 100, 97, 108, 111, 110, 101 };
        YES = new int[] { 121, 101, 115 };
        NO = new int[] { 110, 111 };
        ENDPI = new int[] { 63, 62 };
    }
}
