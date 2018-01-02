// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.IOException;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;
import java.io.InputStream;

final class UTF8CharReader extends AbstractCharReader
{
    private InputStream fInputStream;
    private boolean fCheckOverflow;
    private byte[] fOverflow;
    private int fOverflowOffset;
    private int fOverflowEnd;
    private int fOutputOffset;
    private boolean fSkipLinefeed;
    private int fPartialMultiByteIn;
    private byte[] fPartialMultiByteChar;
    private int fPartialSurrogatePair;
    private boolean fPartialMultiByteResult;
    
    UTF8CharReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final InputStream fInputStream, final StringPool stringPool) throws Exception {
        super(xmlEntityHandler, xmlErrorReporter, b, stringPool);
        this.fInputStream = null;
        this.fCheckOverflow = false;
        this.fOverflow = null;
        this.fOverflowOffset = 0;
        this.fOverflowEnd = 0;
        this.fOutputOffset = 0;
        this.fSkipLinefeed = false;
        this.fPartialMultiByteIn = 0;
        this.fPartialMultiByteChar = new byte[3];
        this.fPartialSurrogatePair = 0;
        this.fPartialMultiByteResult = false;
        this.fInputStream = fInputStream;
        this.fillCurrentChunk();
    }
    
    protected int fillCurrentChunk() throws Exception {
        char[] charArray = super.fCurrentChunk.toCharArray();
        this.fOutputOffset = 0;
        if (this.fCheckOverflow) {
            super.fMostRecentData = charArray;
            if (this.fOverflowEnd < 16384) {
                if (this.fOverflowEnd > 0) {
                    if (super.fMostRecentData == null || super.fMostRecentData.length < 1 + this.fOverflowEnd - this.fOverflowOffset) {
                        super.fMostRecentData = new char[1 + this.fOverflowEnd - this.fOverflowOffset];
                    }
                    this.copyNormalize(this.fOverflow, this.fOverflowOffset, super.fMostRecentData, this.fOutputOffset);
                }
                else if (super.fMostRecentData == null) {
                    super.fMostRecentData = new char[1];
                }
                super.fMostRecentData[this.fOutputOffset] = '\0';
                this.fOverflow = null;
                super.fLength += this.fOutputOffset;
                super.fCurrentIndex = 0;
                super.fCurrentChunk.setCharArray(super.fMostRecentData);
                return super.fMostRecentChar = super.fMostRecentData[0];
            }
            if (super.fMostRecentData == null || super.fMostRecentData.length < 16384) {
                super.fMostRecentData = new char[16384];
            }
            else {
                charArray = null;
            }
            this.copyNormalize(this.fOverflow, this.fOverflowOffset, super.fMostRecentData, this.fOutputOffset);
            this.fCheckOverflow = false;
        }
        else {
            if (this.fOverflow == null) {
                this.fOverflow = new byte[16384];
            }
            super.fMostRecentData = null;
        }
        while (true) {
            this.fOverflowOffset = 0;
            this.fOverflowEnd = 0;
            int n = 16384;
            int read;
            while (true) {
                try {
                    read = this.fInputStream.read(this.fOverflow, this.fOverflowEnd, n);
                }
                catch (IOException ex) {
                    read = -1;
                }
                if (read == -1) {
                    this.fInputStream.close();
                    this.fInputStream = null;
                    if (super.fMostRecentData == null) {
                        super.fMostRecentData = charArray;
                        if (super.fMostRecentData == null || super.fMostRecentData.length < 1 + this.fOverflowEnd) {
                            super.fMostRecentData = new char[1 + this.fOverflowEnd];
                        }
                        else {
                            charArray = null;
                        }
                        this.copyNormalize(this.fOverflow, this.fOverflowOffset, super.fMostRecentData, this.fOutputOffset);
                        this.fOverflow = null;
                        super.fMostRecentData[this.fOutputOffset] = '\0';
                        break;
                    }
                    if (!this.copyNormalize(this.fOverflow, this.fOverflowOffset, super.fMostRecentData, this.fOutputOffset)) {
                        this.fCheckOverflow = true;
                        break;
                    }
                    if (this.fOverflowEnd == 16384) {
                        this.fCheckOverflow = true;
                        this.fOverflowOffset = 0;
                        this.fOverflowEnd = 0;
                        break;
                    }
                    this.fOverflow = null;
                    super.fMostRecentData[this.fOutputOffset] = '\0';
                    break;
                }
                else {
                    if (read > 0) {
                        this.fOverflowEnd += read;
                        n -= read;
                    }
                    if (n <= 0) {
                        break;
                    }
                    continue;
                }
            }
            if (read == -1) {
                break;
            }
            if (super.fMostRecentData != null) {
                final boolean copyNormalize = this.copyNormalize(this.fOverflow, this.fOverflowOffset, super.fMostRecentData, this.fOutputOffset);
                if (this.fOutputOffset != 16384) {
                    continue;
                }
                if (!copyNormalize) {
                    this.fCheckOverflow = true;
                    break;
                }
                break;
            }
            else {
                super.fMostRecentData = charArray;
                if (super.fMostRecentData == null || super.fMostRecentData.length < 16384) {
                    super.fMostRecentData = new char[16384];
                }
                else {
                    charArray = null;
                }
                this.copyNormalize(this.fOverflow, this.fOverflowOffset, super.fMostRecentData, this.fOutputOffset);
                if (this.fOutputOffset == 16384) {
                    break;
                }
                continue;
            }
        }
        super.fLength += this.fOutputOffset;
        super.fCurrentIndex = 0;
        super.fCurrentChunk.setCharArray(super.fMostRecentData);
        return super.fMostRecentChar = super.fMostRecentData[0];
    }
    
    private boolean copyNormalize(final byte[] array, int n, final char[] array2, int i) throws Exception {
        final int fOverflowEnd = this.fOverflowEnd;
        final int length = array2.length;
        if (n == fOverflowEnd) {
            return true;
        }
        byte b = array[n];
        if (this.fSkipLinefeed) {
            this.fSkipLinefeed = false;
            if (b == 10) {
                if (++n == fOverflowEnd) {
                    return this.exitNormalize(n, i, true);
                }
                b = array[n];
            }
        }
        else if (this.fPartialMultiByteIn > 0) {
            if (!this.handlePartialMultiByteChar(b, array, n, fOverflowEnd, array2, i, length)) {
                return this.fPartialMultiByteResult;
            }
            n = this.fOverflowOffset;
            i = this.fOutputOffset;
            b = array[n];
        }
    Label_0386:
        while (i < length) {
            int n2 = fOverflowEnd - n;
            final int n3 = length - i;
            if (n2 > n3) {
                n2 = n3;
            }
            ++n;
            while (true) {
                if (b != 13 && b >= 0) {
                    do {
                        array2[i++] = (char)b;
                        if (--n2 == 0) {
                            break;
                        }
                        b = array[n++];
                    } while (b != 13 && b >= 0);
                    if (n2 != 0) {
                        continue;
                    }
                    if (n == fOverflowEnd) {
                        break Label_0386;
                    }
                    break;
                }
                else {
                    if (b == 13) {
                        array2[i++] = '\n';
                        if (n == fOverflowEnd) {
                            this.fSkipLinefeed = true;
                            return this.exitNormalize(n, i, true);
                        }
                        b = array[n];
                        if (b == 10) {
                            if (++n == fOverflowEnd) {
                                return this.exitNormalize(n, i, true);
                            }
                            b = array[n];
                        }
                        if (i == length) {
                            return this.exitNormalize(n, i, false);
                        }
                    }
                    else {
                        if (!this.handleMultiByteChar(b, array, n, fOverflowEnd, array2, i, length)) {
                            return this.fPartialMultiByteResult;
                        }
                        n = this.fOverflowOffset;
                        i = this.fOutputOffset;
                        b = array[n];
                    }
                    n2 = fOverflowEnd - n;
                    final int n4 = length - i;
                    if (n2 > n4) {
                        n2 = n4;
                    }
                    ++n;
                }
            }
        }
        return this.exitNormalize(n, i, n == fOverflowEnd);
    }
    
    private boolean exitNormalize(final int fOverflowOffset, final int fOutputOffset, final boolean b) {
        this.fOverflowOffset = fOverflowOffset;
        this.fOutputOffset = fOutputOffset;
        return b;
    }
    
    private void savePartialMultiByte(int fPartialMultiByteIn, final byte b, final byte b2, final byte b3) {
        this.fPartialMultiByteIn = fPartialMultiByteIn;
        this.fPartialMultiByteChar[--fPartialMultiByteIn] = b;
        this.fPartialMultiByteChar[--fPartialMultiByteIn] = b2;
        this.fPartialMultiByteChar[--fPartialMultiByteIn] = b3;
    }
    
    private void savePartialMultiByte(int fPartialMultiByteIn, final byte b, final byte b2) {
        this.fPartialMultiByteIn = fPartialMultiByteIn;
        this.fPartialMultiByteChar[--fPartialMultiByteIn] = b;
        this.fPartialMultiByteChar[--fPartialMultiByteIn] = b2;
    }
    
    private void savePartialMultiByte(int fPartialMultiByteIn, final byte b) {
        this.fPartialMultiByteIn = fPartialMultiByteIn;
        this.fPartialMultiByteChar[--fPartialMultiByteIn] = b;
    }
    
    private boolean handleMultiByteChar(final byte b, final byte[] array, int n, final int n2, final char[] array2, int n3, final int n4) throws Exception {
        if (n == n2) {
            this.savePartialMultiByte(1, b);
            this.fPartialMultiByteResult = this.exitNormalize(n, n3, true);
            return false;
        }
        final byte b2 = array[n++];
        if ((b2 & 0xC0) != 0x80) {
            this.deferException(2, new Object[] { Integer.toHexString(b & 0xFF), Integer.toHexString(b2 & 0xFF) }, n3);
            array2[n3++] = '\0';
            return this.exitNormalize(n, n3, true);
        }
        if ((b & 0xE0) == 0xC0) {
            array2[n3++] = (char)(((0x1F & b) << 6) + (0x3F & b2));
            if (n == n2 || n3 == n4) {
                this.fPartialMultiByteResult = this.exitNormalize(n, n3, n == n2);
                return false;
            }
        }
        else {
            if (n == n2) {
                this.savePartialMultiByte(2, b2, b);
                this.fPartialMultiByteResult = this.exitNormalize(n, n3, true);
                return false;
            }
            final byte b3 = array[n++];
            if ((b3 & 0xC0) != 0x80) {
                this.deferException(3, new Object[] { Integer.toHexString(b & 0xFF), Integer.toHexString(b2 & 0xFF), Integer.toHexString(b3 & 0xFF) }, n3);
                array2[n3++] = '\0';
                return this.exitNormalize(n, n3, true);
            }
            if ((b & 0xF0) == 0xE0) {
                array2[n3++] = (char)(((0xF & b) << 12) + ((0x3F & b2) << 6) + (0x3F & b3));
                if (n == n2 || n3 == n4) {
                    this.fPartialMultiByteResult = this.exitNormalize(n, n3, n == n2);
                    return false;
                }
            }
            else {
                if ((b & 0xF8) != 0xF0) {
                    this.deferException(1, new Object[] { Integer.toHexString(b & 0xFF) }, n3);
                    array2[n3++] = '\0';
                    return this.exitNormalize(n, n3, true);
                }
                if (n == n2) {
                    this.savePartialMultiByte(3, b3, b2, b);
                    this.fPartialMultiByteResult = this.exitNormalize(n, n3, true);
                    return false;
                }
                final byte b4 = array[n++];
                if ((b4 & 0xC0) != 0x80) {
                    this.deferException(4, new Object[] { Integer.toHexString(b & 0xFF), Integer.toHexString(b2 & 0xFF), Integer.toHexString(b3 & 0xFF), Integer.toHexString(b4 & 0xFF) }, n3);
                    array2[n3++] = '\0';
                    return this.exitNormalize(n, n3, true);
                }
                int fPartialSurrogatePair = ((0xF & b) << 18) + ((0x3F & b2) << 12) + ((0x3F & b3) << 6) + (0x3F & b4);
                if (fPartialSurrogatePair >= 65536) {
                    array2[n3++] = (char)((fPartialSurrogatePair - 65536 >> 10) + 55296);
                    fPartialSurrogatePair = (fPartialSurrogatePair - 65536 & 0x3FF) + 56320;
                    if (n3 == n4) {
                        this.fPartialSurrogatePair = fPartialSurrogatePair;
                        this.fPartialMultiByteResult = this.exitNormalize(n, n3, n == n2);
                        return false;
                    }
                }
                array2[n3++] = (char)fPartialSurrogatePair;
                if (n == n2 || n3 == n4) {
                    this.fPartialMultiByteResult = this.exitNormalize(n, n3, n == n2);
                    return false;
                }
            }
        }
        return this.exitNormalize(n, n3, true);
    }
    
    private boolean handlePartialMultiByteChar(byte b, final byte[] array, int n, final int n2, final char[] array2, int fOutputOffset, final int n3) throws Exception {
        if (fOutputOffset == n3) {
            this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, n == n2);
            return false;
        }
        if (this.fPartialMultiByteIn != 4) {
            final int fPartialMultiByteIn = this.fPartialMultiByteIn;
            this.fPartialMultiByteIn = 0;
            byte b2 = 0;
            byte b3 = 0;
            int n4 = 0;
            switch (fPartialMultiByteIn) {
                case 1: {
                    b2 = b;
                    break;
                }
                case 2: {
                    b3 = b;
                    break;
                }
                case 3: {
                    n4 = b;
                    break;
                }
            }
            int n5 = fPartialMultiByteIn;
            switch (fPartialMultiByteIn) {
                case 3: {
                    b3 = this.fPartialMultiByteChar[--n5];
                }
                case 2: {
                    b2 = this.fPartialMultiByteChar[--n5];
                }
                case 1: {
                    b = this.fPartialMultiByteChar[--n5];
                    break;
                }
            }
            Label_0484: {
                switch (fPartialMultiByteIn) {
                    case 1: {
                        if ((b2 & 0xC0) != 0x80) {
                            this.deferException(2, new Object[] { Integer.toHexString(b), Integer.toHexString(b2) }, fOutputOffset);
                            array2[fOutputOffset++] = '\0';
                            break;
                        }
                    }
                    case 2: {
                        if ((b & 0xE0) == 0xC0) {
                            array2[fOutputOffset++] = (char)(((0x1F & b) << 6) + (0x3F & b2));
                            if (fOutputOffset == n3) {
                                this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, false);
                                return false;
                            }
                            if (fPartialMultiByteIn < 2 && ++n == n2) {
                                this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, true);
                                return false;
                            }
                            break;
                        }
                        else {
                            if (fPartialMultiByteIn < 2) {
                                if (++n == n2) {
                                    this.savePartialMultiByte(2, b2);
                                    this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, true);
                                    return false;
                                }
                                b3 = array[n];
                            }
                            if ((b3 & 0xC0) != 0x80) {
                                this.deferException(3, new Object[] { Integer.toHexString(b), Integer.toHexString(b2), Integer.toHexString(b3) }, fOutputOffset);
                                array2[fOutputOffset++] = '\0';
                                break;
                            }
                            break Label_0484;
                        }
                        break;
                    }
                    case 3: {
                        if ((b & 0xF0) == 0xE0) {
                            array2[fOutputOffset++] = (char)(((0xF & b) << 12) + ((0x3F & b2) << 6) + (0x3F & b3));
                            if (fOutputOffset == n3) {
                                this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, false);
                                return false;
                            }
                            if (fPartialMultiByteIn < 3 && ++n == n2) {
                                this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, true);
                                return false;
                            }
                            break;
                        }
                        else {
                            if (fPartialMultiByteIn < 3) {
                                if ((b & 0xF8) != 0xF0) {
                                    this.deferException(1, new Object[] { Integer.toHexString(b) }, fOutputOffset);
                                    array2[fOutputOffset++] = '\0';
                                    break;
                                }
                                if (++n == n2) {
                                    this.savePartialMultiByte(3, b3, b2);
                                    this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, true);
                                    return false;
                                }
                                n4 = array[n];
                            }
                            if ((n4 & 0xC0) != 0x80) {
                                this.deferException(4, new Object[] { Integer.toHexString(b), Integer.toHexString(b2), Integer.toHexString(b3), Integer.toHexString(n4) }, fOutputOffset);
                                array2[fOutputOffset++] = '\0';
                                break;
                            }
                            int fPartialSurrogatePair = ((0xF & b) << 18) + ((0x3F & b2) << 12) + ((0x3F & b3) << 6) + (0x3F & n4);
                            if (fPartialSurrogatePair >= 65536) {
                                array2[fOutputOffset++] = (char)((fPartialSurrogatePair - 65536 >> 10) + 55296);
                                fPartialSurrogatePair = (fPartialSurrogatePair - 65536 & 0x3FF) + 56320;
                                if (fOutputOffset == n3) {
                                    this.fPartialSurrogatePair = fPartialSurrogatePair;
                                    this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, false);
                                    return false;
                                }
                            }
                            array2[fOutputOffset++] = (char)fPartialSurrogatePair;
                            if (fOutputOffset == n3) {
                                this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, false);
                                return false;
                            }
                            if (++n == n2) {
                                this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, true);
                                return false;
                            }
                            break;
                        }
                        break;
                    }
                }
            }
            return this.exitNormalize(n, fOutputOffset, true);
        }
        array2[fOutputOffset++] = (char)this.fPartialSurrogatePair;
        if (fOutputOffset == n3) {
            this.fPartialMultiByteResult = this.exitNormalize(n, fOutputOffset, false);
            return false;
        }
        this.fOutputOffset = fOutputOffset;
        return true;
    }
}
