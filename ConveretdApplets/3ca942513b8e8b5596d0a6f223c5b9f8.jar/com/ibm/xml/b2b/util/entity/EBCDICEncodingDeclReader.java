// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import java.io.IOException;

final class EBCDICEncodingDeclReader extends EncodingDeclReader
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
    private static final byte[] fgCodePointMapEBCDIC;
    private static final char ____ = '\0';
    private static final char[] fgEncodingNameMapEBCDIC;
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
        return read != -1 && EBCDICEncodingDeclReader.fgCodePointMapEBCDIC[read] == n;
    }
    
    protected boolean skipCodePoint(final int n) throws IOException {
        super.fStream.mark(1);
        final int read = super.fStream.read();
        if (read == -1) {
            return false;
        }
        if (EBCDICEncodingDeclReader.fgCodePointMapEBCDIC[read] != n) {
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
                final int[] startpixml = EBCDICEncodingDeclReader.STARTPIXML;
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
                if (EBCDICEncodingDeclReader.fgCodePointMapEBCDIC[read] != 1) {
                    super.fStream.reset();
                    return false;
                }
                return true;
            }
            case 1: {
                array = EBCDICEncodingDeclReader.VERSION;
                n2 = 7;
                break;
            }
            case 2: {
                array = EBCDICEncodingDeclReader.VERSION10;
                n2 = 3;
                break;
            }
            case 3: {
                array = EBCDICEncodingDeclReader.ENCODING;
                n2 = 8;
                break;
            }
            case 4: {
                array = EBCDICEncodingDeclReader.STANDALONE;
                n2 = 10;
                break;
            }
            case 5: {
                array = EBCDICEncodingDeclReader.YES;
                n2 = 3;
                break;
            }
            case 6: {
                array = EBCDICEncodingDeclReader.NO;
                n2 = 2;
                break;
            }
            case 7: {
                array = EBCDICEncodingDeclReader.ENDPI;
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
        if (EBCDICEncodingDeclReader.fgCodePointMapEBCDIC[read] != 5) {
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
        array[n2++] = EBCDICEncodingDeclReader.fgEncodingNameMapEBCDIC[read];
        while (true) {
            super.fStream.mark(1);
            final int read2 = super.fStream.read();
            if (read2 == -1) {
                super.fStream.reset();
                return false;
            }
            final byte b = EBCDICEncodingDeclReader.fgCodePointMapEBCDIC[read2];
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
                    array[n2++] = EBCDICEncodingDeclReader.fgEncodingNameMapEBCDIC[read2];
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
        fgCodePointMapEBCDIC = new byte[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 11, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 4, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0 };
        fgEncodingNameMapEBCDIC = new char[] { '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '.', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '-', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '\0', '\0', '\0', '\0', '\0', '\0', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '\0', '\0', '\0', '\0', '\0', '\0' };
        STARTPIXML = new int[] { 76, 111, 167, 148, 147 };
        VERSION = new int[] { 165, 133, 153, 162, 137, 150, 149 };
        VERSION10 = new int[] { 241, 75, 240 };
        ENCODING = new int[] { 133, 149, 131, 150, 132, 137, 149, 135 };
        STANDALONE = new int[] { 162, 163, 129, 149, 132, 129, 147, 150, 149, 133 };
        YES = new int[] { 168, 133, 162 };
        NO = new int[] { 149, 150 };
        ENDPI = new int[] { 111, 110 };
    }
}
