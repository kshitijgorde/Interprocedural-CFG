// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import java.io.IOException;

final class LatinEncodingDeclReader extends EncodingDeclReader
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
    private static final byte[] fgCodePointMapLatin;
    private static final int[] STARTPIXML;
    private static final int[] VERSION;
    private static final int[] VERSION10;
    private static final int[] ENCODING;
    private static final int[] STANDALONE;
    private static final int[] YES;
    private static final int[] NO;
    private static final int[] ENDPI;
    
    protected boolean lookingAtCodePoint(final int n) throws IOException {
        super.fStream.mark(1);
        final int read = super.fStream.read();
        super.fStream.reset();
        return read != -1 && LatinEncodingDeclReader.fgCodePointMapLatin[read] == n;
    }
    
    protected boolean skipCodePoint(final int n) throws IOException {
        super.fStream.mark(1);
        final int read = super.fStream.read();
        if (read == -1) {
            return false;
        }
        if (LatinEncodingDeclReader.fgCodePointMapLatin[read] != n) {
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
                final int[] startpixml = LatinEncodingDeclReader.STARTPIXML;
                super.fStream.mark(6);
                for (int i = 0; i < 5; ++i) {
                    if (super.fStream.read() != startpixml[i]) {
                        super.fStream.reset();
                        return false;
                    }
                }
                final int read = super.fStream.read();
                if (read == -1) {
                    super.fStream.reset();
                    return false;
                }
                if (LatinEncodingDeclReader.fgCodePointMapLatin[read] != 1) {
                    super.fStream.reset();
                    return false;
                }
                return true;
            }
            case 1: {
                array = LatinEncodingDeclReader.VERSION;
                n2 = 7;
                break;
            }
            case 2: {
                array = LatinEncodingDeclReader.VERSION10;
                n2 = 3;
                break;
            }
            case 3: {
                array = LatinEncodingDeclReader.ENCODING;
                n2 = 8;
                break;
            }
            case 4: {
                array = LatinEncodingDeclReader.STANDALONE;
                n2 = 10;
                break;
            }
            case 5: {
                array = LatinEncodingDeclReader.YES;
                n2 = 3;
                break;
            }
            case 6: {
                array = LatinEncodingDeclReader.NO;
                n2 = 2;
                break;
            }
            case 7: {
                array = LatinEncodingDeclReader.ENDPI;
                n2 = 2;
                break;
            }
            default: {
                return false;
            }
        }
        super.fStream.mark(n2);
        for (int j = 0; j < n2; ++j) {
            if (super.fStream.read() != array[j]) {
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
        super.fStream.mark(1);
        final int read = super.fStream.read();
        if (read == -1) {
            super.fStream.reset();
            return false;
        }
        if (LatinEncodingDeclReader.fgCodePointMapLatin[read] != 5) {
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
        array[n2++] = (char)read;
        while (true) {
            super.fStream.mark(1);
            final int read2 = super.fStream.read();
            if (read2 == -1) {
                super.fStream.reset();
                return false;
            }
            final byte b = LatinEncodingDeclReader.fgCodePointMapLatin[read2];
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
                    array[n2++] = (char)read2;
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
        fgCodePointMapLatin = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 7, 9, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 10, 2, 11, 12, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 8, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
