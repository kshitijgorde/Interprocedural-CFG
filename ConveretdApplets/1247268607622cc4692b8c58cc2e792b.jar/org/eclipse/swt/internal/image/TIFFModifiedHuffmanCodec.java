// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

final class TIFFModifiedHuffmanCodec
{
    static final short[][][] BLACK_CODE;
    static final short[][][] WHITE_CODE;
    static final int BLACK_MIN_BITS = 2;
    static final int WHITE_MIN_BITS = 4;
    boolean isWhite;
    int whiteValue;
    int blackValue;
    byte[] src;
    byte[] dest;
    int byteOffsetSrc;
    int bitOffsetSrc;
    int byteOffsetDest;
    int bitOffsetDest;
    int code;
    int nbrBits;
    int rowSize;
    
    static {
        BLACK_CODE = new short[][][] { { { 2, 3 }, { 3, 2 } }, { { 2, 1 }, { 3, 4 } }, { { 2, 6 }, { 3, 5 } }, { { 3, 7 } }, { { 4, 9 }, { 5, 8 } }, { { 4, 10 }, { 5, 11 }, { 7, 12 } }, { { 4, 13 }, { 7, 14 } }, { { 24, 15 } }, { { 8, 18 }, { 15, 64 }, { 23, 16 }, { 24, 17 }, { 55, 0 } }, { { 0, -1 }, { 8, 1792 }, { 23, 24 }, { 24, 25 }, { 40, 23 }, { 55, 22 }, { 103, 19 }, { 104, 20 }, { 108, 21 }, { 12, 1856 }, { 13, 1920 } }, { { 18, 1984 }, { 19, 2048 }, { 20, 2112 }, { 21, 2176 }, { 22, 2240 }, { 23, 2304 }, { 28, 2368 }, { 29, 2432 }, { 30, 2496 }, { 31, 2560 }, { 36, 52 }, { 39, 55 }, { 40, 56 }, { 43, 59 }, { 44, 60 }, { 51, 320 }, { 52, 384 }, { 53, 448 }, { 55, 53 }, { 56, 54 }, { 82, 50 }, { 83, 51 }, { 84, 44 }, { 85, 45 }, { 86, 46 }, { 87, 47 }, { 88, 57 }, { 89, 58 }, { 90, 61 }, { 91, 256 }, { 100, 48 }, { 101, 49 }, { 102, 62 }, { 103, 63 }, { 104, 30 }, { 105, 31 }, { 106, 32 }, { 107, 33 }, { 108, 40 }, { 109, 41 }, { 200, 128 }, { 201, 192 }, { 202, 26 }, { 203, 27 }, { 204, 28 }, { 205, 29 }, { 210, 34 }, { 211, 35 }, { 212, 36 }, { 213, 37 }, { 214, 38 }, { 215, 39 }, { 218, 42 }, { 219, 43 } }, { { 74, 640 }, { 75, 704 }, { 76, 768 }, { 77, 832 }, { 82, 1280 }, { 83, 1344 }, { 84, 1408 }, { 85, 1472 }, { 90, 1536 }, { 91, 1600 }, { 100, 1664 }, { 101, 1728 }, { 108, 512 }, { 109, 576 }, { 114, 896 }, { 115, 960 }, { 116, 1024 }, { 117, 1088 }, { 118, 1152 }, { 119, 1216 } } };
        WHITE_CODE = new short[][][] { { { 7, 2 }, { 8, 3 }, { 11, 4 }, { 12, 5 }, { 14, 6 }, { 15, 7 } }, { { 7, 10 }, { 8, 11 }, { 18, 128 }, { 19, 8 }, { 20, 9 }, { 27, 64 } }, { { 3, 13 }, { 7, 1 }, { 8, 12 }, { 23, 192 }, { 24, 1664 }, { 42, 16 }, { 43, 17 }, { 52, 14 }, { 53, 15 } }, { { 3, 22 }, { 4, 23 }, { 8, 20 }, { 12, 19 }, { 19, 26 }, { 23, 21 }, { 24, 28 }, { 36, 27 }, { 39, 18 }, { 40, 24 }, { 43, 25 }, { 55, 256 } }, { { 2, 29 }, { 3, 30 }, { 4, 45 }, { 5, 46 }, { 10, 47 }, { 11, 48 }, { 18, 33 }, { 19, 34 }, { 20, 35 }, { 21, 36 }, { 22, 37 }, { 23, 38 }, { 26, 31 }, { 27, 32 }, { 36, 53 }, { 37, 54 }, { 40, 39 }, { 41, 40 }, { 42, 41 }, { 43, 42 }, { 44, 43 }, { 45, 44 }, { 50, 61 }, { 51, 62 }, { 52, 63 }, { 53, 0 }, { 54, 320 }, { 55, 384 }, { 74, 59 }, { 75, 60 }, { 82, 49 }, { 83, 50 }, { 84, 51 }, { 85, 52 }, { 88, 55 }, { 89, 56 }, { 90, 57 }, { 91, 58 }, { 100, 448 }, { 101, 512 }, { 103, 640 }, { 104, 576 } }, { { 152, 1472 }, { 153, 1536 }, { 154, 1600 }, { 155, 1728 }, { 204, 704 }, { 205, 768 }, { 210, 832 }, { 211, 896 }, { 212, 960 }, { 213, 1024 }, { 214, 1088 }, { 215, 1152 }, { 216, 1216 }, { 217, 1280 }, { 218, 1344 }, { 219, 1408 } }, new short[0][], { { 8, 1792 }, { 12, 1856 }, { 13, 1920 } }, { { 1, -1 }, { 18, 1984 }, { 19, 2048 }, { 20, 2112 }, { 21, 2176 }, { 22, 2240 }, { 23, 2304 }, { 28, 2368 }, { 29, 2432 }, { 30, 2496 }, { 31, 2560 } } };
    }
    
    TIFFModifiedHuffmanCodec() {
        this.whiteValue = 0;
        this.blackValue = 1;
        this.byteOffsetSrc = 0;
        this.bitOffsetSrc = 0;
        this.byteOffsetDest = 0;
        this.bitOffsetDest = 0;
        this.code = 0;
        this.nbrBits = 0;
    }
    
    public int decode(final byte[] src, final byte[] dest, final int byteOffsetDest, final int rowSize, final int n) {
        this.src = src;
        this.dest = dest;
        this.rowSize = rowSize;
        this.byteOffsetSrc = 0;
        this.bitOffsetSrc = 0;
        this.byteOffsetDest = byteOffsetDest;
        this.bitOffsetDest = 0;
        int n2 = 0;
        while (n2 < n && this.decodeRow()) {
            ++n2;
            if (this.bitOffsetDest > 0) {
                ++this.byteOffsetDest;
                this.bitOffsetDest = 0;
            }
        }
        return this.byteOffsetDest - byteOffsetDest;
    }
    
    boolean decodeRow() {
        this.isWhite = true;
        int i = 0;
        while (i < this.rowSize) {
            final int decodeRunLength = this.decodeRunLength();
            if (decodeRunLength < 0) {
                return false;
            }
            i += decodeRunLength;
            this.setNextBits(this.isWhite ? this.whiteValue : this.blackValue, decodeRunLength);
            this.isWhite = !this.isWhite;
        }
        return true;
    }
    
    int decodeRunLength() {
        short n = 0;
        final short[][][] array = this.isWhite ? TIFFModifiedHuffmanCodec.WHITE_CODE : TIFFModifiedHuffmanCodec.BLACK_CODE;
        while (true) {
            boolean b = false;
            this.nbrBits = (this.isWhite ? 4 : 2);
            this.code = this.getNextBits(this.nbrBits);
            for (int i = 0; i < array.length; ++i) {
                int j = 0;
                while (j < array[i].length) {
                    if (array[i][j][0] == this.code) {
                        b = true;
                        final short n2 = array[i][j][1];
                        if (n2 == -1) {
                            if (this.byteOffsetSrc == this.src.length - 1) {
                                return -1;
                            }
                            break;
                        }
                        else {
                            n += n2;
                            if (n2 < 64) {
                                return n;
                            }
                            break;
                        }
                    }
                    else {
                        ++j;
                    }
                }
                if (b) {
                    break;
                }
                this.code = (this.code << 1 | this.getNextBit());
            }
            if (!b) {
                SWT.error(40);
            }
        }
    }
    
    int getNextBit() {
        final int n = this.src[this.byteOffsetSrc] >>> 7 - this.bitOffsetSrc & 0x1;
        ++this.bitOffsetSrc;
        if (this.bitOffsetSrc > 7) {
            ++this.byteOffsetSrc;
            this.bitOffsetSrc = 0;
        }
        return n;
    }
    
    int getNextBits(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 = (n2 << 1 | this.getNextBit());
        }
        return n2;
    }
    
    void setNextBits(final int n, final int n2) {
        int i;
        for (i = n2; this.bitOffsetDest > 0 && this.bitOffsetDest <= 7 && i > 0; --i, ++this.bitOffsetDest) {
            this.dest[this.byteOffsetDest] = ((n == 1) ? ((byte)(this.dest[this.byteOffsetDest] | 1 << 7 - this.bitOffsetDest)) : ((byte)(this.dest[this.byteOffsetDest] & ~(1 << 7 - this.bitOffsetDest))));
        }
        if (this.bitOffsetDest == 8) {
            ++this.byteOffsetDest;
            this.bitOffsetDest = 0;
        }
        while (i >= 8) {
            this.dest[this.byteOffsetDest++] = (byte)((n == 1) ? 255 : 0);
            i -= 8;
        }
        while (i > 0) {
            this.dest[this.byteOffsetDest] = ((n == 1) ? ((byte)(this.dest[this.byteOffsetDest] | 1 << 7 - this.bitOffsetDest)) : ((byte)(this.dest[this.byteOffsetDest] & ~(1 << 7 - this.bitOffsetDest))));
            --i;
            ++this.bitOffsetDest;
        }
    }
}
