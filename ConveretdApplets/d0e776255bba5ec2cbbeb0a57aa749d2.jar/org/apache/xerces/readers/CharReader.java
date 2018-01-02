// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.IOException;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;
import java.io.Reader;

final class CharReader extends AbstractCharReader
{
    private Reader fCharacterStream;
    private boolean fCheckOverflow;
    private char[] fOverflow;
    private int fOverflowOffset;
    private int fOverflowEnd;
    private int fOutputOffset;
    private boolean fSkipLinefeed;
    
    CharReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final Reader fCharacterStream, final StringPool stringPool) throws Exception {
        super(xmlEntityHandler, xmlErrorReporter, b, stringPool);
        this.fCharacterStream = null;
        this.fCheckOverflow = false;
        this.fOverflow = null;
        this.fOverflowOffset = 0;
        this.fOverflowEnd = 0;
        this.fOutputOffset = 0;
        this.fSkipLinefeed = false;
        this.fCharacterStream = fCharacterStream;
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
                this.fOverflow = charArray;
                if (this.fOverflow == null || this.fOverflow.length < 16384) {
                    this.fOverflow = new char[16384];
                }
                else {
                    charArray = null;
                }
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
                    read = this.fCharacterStream.read(this.fOverflow, this.fOverflowEnd, n);
                }
                catch (IOException ex) {
                    read = -1;
                }
                if (read == -1) {
                    this.fCharacterStream.close();
                    this.fCharacterStream = null;
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
    
    private boolean copyNormalize(final char[] array, int n, final char[] array2, int i) throws Exception {
        final int fOverflowEnd = this.fOverflowEnd;
        final int length = array2.length;
        if (n == fOverflowEnd) {
            return true;
        }
        char c = array[n];
        if (this.fSkipLinefeed) {
            this.fSkipLinefeed = false;
            if (c == '\n') {
                if (++n == fOverflowEnd) {
                    return this.exitNormalize(n, i, true);
                }
                c = array[n];
            }
        }
    Label_0280:
        while (i < length) {
            int n2 = fOverflowEnd - n;
            final int n3 = length - i;
            if (n2 > n3) {
                n2 = n3;
            }
            ++n;
            while (true) {
                if (c != '\r') {
                    do {
                        array2[i++] = c;
                        if (--n2 == 0) {
                            break;
                        }
                        c = array[n++];
                    } while (c != '\r');
                    if (n2 != 0) {
                        continue;
                    }
                    if (n == fOverflowEnd) {
                        break Label_0280;
                    }
                    break;
                }
                else {
                    array2[i++] = '\n';
                    if (n == fOverflowEnd) {
                        this.fSkipLinefeed = true;
                        return this.exitNormalize(n, i, true);
                    }
                    c = array[n];
                    if (c == '\n') {
                        if (++n == fOverflowEnd) {
                            return this.exitNormalize(n, i, true);
                        }
                        c = array[n];
                    }
                    if (i == length) {
                        return this.exitNormalize(n, i, false);
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
}
