// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;

public class PngHuffmanTable
{
    CodeLengthInfo[] codeLengthInfo;
    int[] codeValues;
    static final int MAX_CODE_LENGTH = 15;
    static final int BAD_CODE = 268435455;
    static final int[] incs;
    
    static {
        incs = new int[] { 1391376, 463792, 198768, 86961, 33936, 13776, 4592, 1968, 861, 336, 112, 48, 21, 7, 3, 1 };
    }
    
    PngHuffmanTable(final int[] array) {
        this.initialize(array);
        this.generateTable(array);
    }
    
    private void initialize(final int[] array) {
        this.codeValues = new int[array.length];
        for (int i = 0; i < this.codeValues.length; ++i) {
            this.codeValues[i] = i;
        }
        this.codeLengthInfo = new CodeLengthInfo[15];
        for (int j = 0; j < 15; ++j) {
            this.codeLengthInfo[j] = new CodeLengthInfo();
            this.codeLengthInfo[j].length = j;
            this.codeLengthInfo[j].baseIndex = 0;
            this.codeLengthInfo[j].min = 268435455;
            this.codeLengthInfo[j].max = -1;
        }
    }
    
    private void generateTable(final int[] array) {
        for (int i = 0; i < 16; ++i) {
            int j;
            for (int n = j = PngHuffmanTable.incs[i]; j < array.length; ++j) {
                int n2;
                int n3;
                int n4;
                for (n2 = array[j], n3 = this.codeValues[j], n4 = j; n4 >= n && (array[n4 - n] > n2 || (array[n4 - n] == n2 && this.codeValues[n4 - n] > n3)); n4 -= n) {
                    array[n4] = array[n4 - n];
                    this.codeValues[n4] = this.codeValues[n4 - n];
                }
                array[n4] = n2;
                this.codeValues[n4] = n3;
            }
        }
        final int[] array2 = new int[array.length];
        int k = 0;
        int n5 = 0;
        for (int l = 0; l < array.length; ++l) {
            while (k != array[l]) {
                ++k;
                n5 <<= 1;
            }
            if (k != 0) {
                array2[l] = n5;
                ++n5;
            }
        }
        int n6 = 0;
        for (int baseIndex = 0; baseIndex < array.length; ++baseIndex) {
            if (n6 != array[baseIndex]) {
                n6 = array[baseIndex];
                this.codeLengthInfo[n6 - 1].baseIndex = baseIndex;
                this.codeLengthInfo[n6 - 1].min = array2[baseIndex];
            }
            if (n6 != 0) {
                this.codeLengthInfo[n6 - 1].max = array2[baseIndex];
            }
        }
    }
    
    int getNextValue(final PngDecodingDataStream pngDecodingDataStream) throws IOException {
        int nextIdatBit;
        int n;
        for (nextIdatBit = pngDecodingDataStream.getNextIdatBit(), n = 0; n < 15 && nextIdatBit > this.codeLengthInfo[n].max; nextIdatBit = (nextIdatBit << 1 | pngDecodingDataStream.getNextIdatBit()), ++n) {}
        if (n >= 15) {
            pngDecodingDataStream.error();
        }
        return this.codeValues[this.codeLengthInfo[n].baseIndex + (nextIdatBit - this.codeLengthInfo[n].min)];
    }
    
    static class CodeLengthInfo
    {
        int length;
        int max;
        int min;
        int baseIndex;
    }
}
