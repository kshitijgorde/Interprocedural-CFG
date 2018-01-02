// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

public class Huffman
{
    public static final int NUM_HUFF_TABLES = 80;
    public static final int DC_HUFF_OFFSET = 0;
    public static final int AC_HUFF_OFFSET = 16;
    public static final int AC_TABLE_2_THRESH = 5;
    public static final int AC_TABLE_3_THRESH = 14;
    public static final int AC_TABLE_4_THRESH = 27;
    public static final int DC_HUFF_CHOICES = 16;
    public static final int DC_HUFF_CHOICE_BITS = 4;
    public static final int AC_HUFF_CHOICES = 16;
    public static final int AC_HUFF_CHOICE_BITS = 4;
    public static final int MAX_SINGLE_TOKEN_VALUE = 6;
    public static final int DCT_VAL_CAT2_MIN = 3;
    public static final int DCT_VAL_CAT3_MIN = 7;
    public static final int DCT_VAL_CAT4_MIN = 9;
    public static final int DCT_VAL_CAT5_MIN = 13;
    public static final int DCT_VAL_CAT6_MIN = 21;
    public static final int DCT_VAL_CAT7_MIN = 37;
    public static final int DCT_VAL_CAT8_MIN = 69;
    public static final int DCT_EOB_TOKEN = 0;
    public static final int DCT_EOB_PAIR_TOKEN = 1;
    public static final int DCT_EOB_TRIPLE_TOKEN = 2;
    public static final int DCT_REPEAT_RUN_TOKEN = 3;
    public static final int DCT_REPEAT_RUN2_TOKEN = 4;
    public static final int DCT_REPEAT_RUN3_TOKEN = 5;
    public static final int DCT_REPEAT_RUN4_TOKEN = 6;
    public static final int DCT_SHORT_ZRL_TOKEN = 7;
    public static final int DCT_ZRL_TOKEN = 8;
    public static final int ONE_TOKEN = 9;
    public static final int MINUS_ONE_TOKEN = 10;
    public static final int TWO_TOKEN = 11;
    public static final int MINUS_TWO_TOKEN = 12;
    public static final int LOW_VAL_TOKENS = 13;
    public static final int DCT_VAL_CATEGORY3 = 17;
    public static final int DCT_VAL_CATEGORY4 = 18;
    public static final int DCT_VAL_CATEGORY5 = 19;
    public static final int DCT_VAL_CATEGORY6 = 20;
    public static final int DCT_VAL_CATEGORY7 = 21;
    public static final int DCT_VAL_CATEGORY8 = 22;
    public static final int DCT_RUN_CATEGORY1 = 23;
    public static final int DCT_RUN_CATEGORY1B = 28;
    public static final int DCT_RUN_CATEGORY1C = 29;
    public static final int DCT_RUN_CATEGORY2 = 30;
    public static final int MAX_ENTROPY_TOKENS = 32;
    
    private static void createHuffmanList(final HuffEntry[] huffRoot, final int hIndex, final short[] freqList) {
        huffRoot[hIndex] = new HuffEntry();
        huffRoot[hIndex].previous = null;
        huffRoot[hIndex].next = null;
        huffRoot[hIndex].Child[0] = null;
        huffRoot[hIndex].Child[1] = null;
        huffRoot[hIndex].value = 0;
        huffRoot[hIndex].frequency = freqList[0];
        if (huffRoot[hIndex].frequency == 0) {
            huffRoot[hIndex].frequency = 1;
        }
        for (int i = 1; i < 32; ++i) {
            final HuffEntry entry_ptr = new HuffEntry();
            entry_ptr.value = i;
            entry_ptr.frequency = freqList[i];
            entry_ptr.Child[0] = null;
            entry_ptr.Child[1] = null;
            if (entry_ptr.frequency == 0) {
                entry_ptr.frequency = 1;
            }
            if (entry_ptr.frequency <= huffRoot[hIndex].frequency) {
                entry_ptr.next = huffRoot[hIndex];
                huffRoot[hIndex].previous = entry_ptr;
                entry_ptr.previous = null;
                huffRoot[hIndex] = entry_ptr;
            }
            else {
                HuffEntry search_ptr;
                for (search_ptr = huffRoot[hIndex]; search_ptr.next != null && search_ptr.frequency < entry_ptr.frequency; search_ptr = search_ptr.next) {}
                if (search_ptr.frequency < entry_ptr.frequency) {
                    entry_ptr.next = null;
                    entry_ptr.previous = search_ptr;
                    search_ptr.next = entry_ptr;
                }
                else {
                    entry_ptr.next = search_ptr;
                    entry_ptr.previous = search_ptr.previous;
                    search_ptr.previous.next = entry_ptr;
                    search_ptr.previous = entry_ptr;
                }
            }
        }
    }
    
    private static void createCodeArray(final HuffEntry huffRoot, final int[] huffCodeArray, final byte[] huffCodeLengthArray, final int codeValue, byte codeLength) {
        if (huffRoot.Child[0] == null && huffRoot.Child[1] == null) {
            huffCodeArray[huffRoot.value] = codeValue;
            huffCodeLengthArray[huffRoot.value] = codeLength;
        }
        else {
            ++codeLength;
            createCodeArray(huffRoot.Child[0], huffCodeArray, huffCodeLengthArray, (codeValue << 1) + 0, codeLength);
            createCodeArray(huffRoot.Child[1], huffCodeArray, huffCodeLengthArray, (codeValue << 1) + 1, codeLength);
        }
    }
    
    public static void buildHuffmanTree(final HuffEntry[] huffRoot, final int[] huffCodeArray, final byte[] huffCodeLengthArray, final int hIndex, final short[] freqList) {
        createHuffmanList(huffRoot, hIndex, freqList);
        while (huffRoot[hIndex].next != null) {
            final HuffEntry entry_ptr = new HuffEntry();
            entry_ptr.value = -1;
            entry_ptr.frequency = huffRoot[hIndex].frequency + huffRoot[hIndex].next.frequency;
            entry_ptr.Child[0] = huffRoot[hIndex];
            entry_ptr.Child[1] = huffRoot[hIndex].next;
            if (entry_ptr.Child[1].next != null) {
                huffRoot[hIndex] = entry_ptr.Child[1].next;
                huffRoot[hIndex].previous = null;
                if (entry_ptr.frequency <= huffRoot[hIndex].frequency) {
                    entry_ptr.next = huffRoot[hIndex];
                    huffRoot[hIndex].previous = entry_ptr;
                    entry_ptr.previous = null;
                    huffRoot[hIndex] = entry_ptr;
                }
                else {
                    HuffEntry search_ptr;
                    for (search_ptr = huffRoot[hIndex]; search_ptr.next != null && search_ptr.frequency < entry_ptr.frequency; search_ptr = search_ptr.next) {}
                    if (search_ptr.frequency < entry_ptr.frequency) {
                        entry_ptr.next = null;
                        entry_ptr.previous = search_ptr;
                        search_ptr.next = entry_ptr;
                    }
                    else {
                        entry_ptr.next = search_ptr;
                        entry_ptr.previous = search_ptr.previous;
                        search_ptr.previous.next = entry_ptr;
                        search_ptr.previous = entry_ptr;
                    }
                }
            }
            else {
                entry_ptr.next = null;
                entry_ptr.previous = null;
                huffRoot[hIndex] = entry_ptr;
            }
            entry_ptr.Child[0].next = null;
            entry_ptr.Child[0].previous = null;
            entry_ptr.Child[1].next = null;
            entry_ptr.Child[1].previous = null;
        }
        createCodeArray(huffRoot[hIndex], huffCodeArray, huffCodeLengthArray, 0, (byte)0);
    }
    
    public static int readHuffmanTrees(final HuffEntry[] huffRoot, final Buffer opb) {
        for (int i = 0; i < 80; ++i) {
            huffRoot[i] = new HuffEntry();
            final int ret = huffRoot[i].read(0, opb);
            if (ret != 0) {
                return ret;
            }
        }
        return 0;
    }
    
    public static void clearHuffmanTrees(final HuffEntry[] huffRoot) {
        for (int i = 0; i < 80; ++i) {
            huffRoot[i] = null;
        }
    }
}
