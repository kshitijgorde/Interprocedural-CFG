// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;

public class PngHuffmanTables
{
    PngHuffmanTable literalTable;
    PngHuffmanTable distanceTable;
    static PngHuffmanTable FixedLiteralTable;
    static PngHuffmanTable FixedDistanceTable;
    static final int LiteralTableSize = 288;
    static final int[] FixedLiteralLengths;
    static final int DistanceTableSize = 32;
    static final int[] FixedDistanceLengths;
    static final int LengthCodeTableSize = 19;
    static final int[] LengthCodeOrder;
    
    static {
        FixedLiteralLengths = new int[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8 };
        FixedDistanceLengths = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
        LengthCodeOrder = new int[] { 16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15 };
    }
    
    static PngHuffmanTables getDynamicTables(final PngDecodingDataStream pngDecodingDataStream) throws IOException {
        return new PngHuffmanTables(pngDecodingDataStream);
    }
    
    static PngHuffmanTables getFixedTables() {
        return new PngHuffmanTables();
    }
    
    private PngHuffmanTable getFixedLiteralTable() {
        if (PngHuffmanTables.FixedLiteralTable == null) {
            PngHuffmanTables.FixedLiteralTable = new PngHuffmanTable(PngHuffmanTables.FixedLiteralLengths);
        }
        return PngHuffmanTables.FixedLiteralTable;
    }
    
    private PngHuffmanTable getFixedDistanceTable() {
        if (PngHuffmanTables.FixedDistanceTable == null) {
            PngHuffmanTables.FixedDistanceTable = new PngHuffmanTable(PngHuffmanTables.FixedDistanceLengths);
        }
        return PngHuffmanTables.FixedDistanceTable;
    }
    
    private PngHuffmanTables() {
        this.literalTable = this.getFixedLiteralTable();
        this.distanceTable = this.getFixedDistanceTable();
    }
    
    private PngHuffmanTables(final PngDecodingDataStream pngDecodingDataStream) throws IOException {
        final int n = 257 + pngDecodingDataStream.getNextIdatBits(5);
        final int n2 = 1 + pngDecodingDataStream.getNextIdatBits(5);
        final int n3 = 4 + pngDecodingDataStream.getNextIdatBits(4);
        if (n3 > 19) {
            pngDecodingDataStream.error();
        }
        final int[] array = new int[19];
        for (int i = 0; i < n3; ++i) {
            array[PngHuffmanTables.LengthCodeOrder[i]] = pngDecodingDataStream.getNextIdatBits(3);
        }
        final PngHuffmanTable pngHuffmanTable = new PngHuffmanTable(array);
        final int[] lengths = this.readLengths(pngDecodingDataStream, n, pngHuffmanTable, 288);
        final int[] lengths2 = this.readLengths(pngDecodingDataStream, n2, pngHuffmanTable, 32);
        this.literalTable = new PngHuffmanTable(lengths);
        this.distanceTable = new PngHuffmanTable(lengths2);
    }
    
    private int[] readLengths(final PngDecodingDataStream pngDecodingDataStream, final int n, final PngHuffmanTable pngHuffmanTable, final int n2) throws IOException {
        final int[] array = new int[n2];
        int i = 0;
        while (i < n) {
            final int nextValue = pngHuffmanTable.getNextValue(pngDecodingDataStream);
            if (nextValue < 16) {
                array[i] = nextValue;
                ++i;
            }
            else if (nextValue == 16) {
                for (int n3 = pngDecodingDataStream.getNextIdatBits(2) + 3, j = 0; j < n3; ++j) {
                    array[i] = array[i - 1];
                    ++i;
                }
            }
            else if (nextValue == 17) {
                for (int n4 = pngDecodingDataStream.getNextIdatBits(3) + 3, k = 0; k < n4; ++k) {
                    array[i] = 0;
                    ++i;
                }
            }
            else if (nextValue == 18) {
                for (int n5 = pngDecodingDataStream.getNextIdatBits(7) + 11, l = 0; l < n5; ++l) {
                    array[i] = 0;
                    ++i;
                }
            }
            else {
                pngDecodingDataStream.error();
            }
        }
        return array;
    }
    
    int getNextLiteralValue(final PngDecodingDataStream pngDecodingDataStream) throws IOException {
        return this.literalTable.getNextValue(pngDecodingDataStream);
    }
    
    int getNextDistanceValue(final PngDecodingDataStream pngDecodingDataStream) throws IOException {
        return this.distanceTable.getNextValue(pngDecodingDataStream);
    }
}
