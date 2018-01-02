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
    
    private static void createHuffmanList(final HuffEntry[] array, final int n, final short[] array2) {
        array[n] = new HuffEntry();
        array[n].previous = null;
        array[n].next = null;
        array[n].Child[0] = null;
        array[n].Child[1] = null;
        array[n].value = 0;
        array[n].frequency = array2[0];
        if (array[n].frequency == 0) {
            array[n].frequency = 1;
        }
        for (int i = 1; i < 32; ++i) {
            final HuffEntry huffEntry = new HuffEntry();
            huffEntry.value = i;
            huffEntry.frequency = array2[i];
            huffEntry.Child[0] = null;
            huffEntry.Child[1] = null;
            if (huffEntry.frequency == 0) {
                huffEntry.frequency = 1;
            }
            if (huffEntry.frequency <= array[n].frequency) {
                huffEntry.next = array[n];
                array[n].previous = huffEntry;
                huffEntry.previous = null;
                array[n] = huffEntry;
            }
            else {
                HuffEntry next;
                for (next = array[n]; next.next != null && next.frequency < huffEntry.frequency; next = next.next) {}
                if (next.frequency < huffEntry.frequency) {
                    huffEntry.next = null;
                    huffEntry.previous = next;
                    next.next = huffEntry;
                }
                else {
                    huffEntry.next = next;
                    huffEntry.previous = next.previous;
                    next.previous.next = huffEntry;
                    next.previous = huffEntry;
                }
            }
        }
    }
    
    private static void createCodeArray(final HuffEntry huffEntry, final int[] array, final byte[] array2, final int n, final byte b) {
        if (huffEntry.Child[0] == null && huffEntry.Child[1] == null) {
            array[huffEntry.value] = n;
            array2[huffEntry.value] = b;
        }
        else {
            final byte b2 = (byte)(b + 1);
            createCodeArray(huffEntry.Child[0], array, array2, (n << 1) + 0, b2);
            createCodeArray(huffEntry.Child[1], array, array2, (n << 1) + 1, b2);
        }
    }
    
    public static void buildHuffmanTree(final HuffEntry[] array, final int[] array2, final byte[] array3, final int n, final short[] array4) {
        createHuffmanList(array, n, array4);
        while (array[n].next != null) {
            final HuffEntry huffEntry = new HuffEntry();
            huffEntry.value = -1;
            huffEntry.frequency = array[n].frequency + array[n].next.frequency;
            huffEntry.Child[0] = array[n];
            huffEntry.Child[1] = array[n].next;
            if (huffEntry.Child[1].next != null) {
                array[n] = huffEntry.Child[1].next;
                array[n].previous = null;
                if (huffEntry.frequency <= array[n].frequency) {
                    huffEntry.next = array[n];
                    array[n].previous = huffEntry;
                    huffEntry.previous = null;
                    array[n] = huffEntry;
                }
                else {
                    HuffEntry next;
                    for (next = array[n]; next.next != null && next.frequency < huffEntry.frequency; next = next.next) {}
                    if (next.frequency < huffEntry.frequency) {
                        huffEntry.next = null;
                        huffEntry.previous = next;
                        next.next = huffEntry;
                    }
                    else {
                        huffEntry.next = next;
                        huffEntry.previous = next.previous;
                        next.previous.next = huffEntry;
                        next.previous = huffEntry;
                    }
                }
            }
            else {
                huffEntry.next = null;
                huffEntry.previous = null;
                array[n] = huffEntry;
            }
            huffEntry.Child[0].next = null;
            huffEntry.Child[0].previous = null;
            huffEntry.Child[1].next = null;
            huffEntry.Child[1].previous = null;
        }
        createCodeArray(array[n], array2, array3, 0, (byte)0);
    }
    
    public static int readHuffmanTrees(final HuffEntry[] array, final Buffer buffer) {
        for (int i = 0; i < 80; ++i) {
            array[i] = new HuffEntry();
            final int read = array[i].read(0, buffer);
            if (read != 0) {
                return read;
            }
        }
        return 0;
    }
    
    public static void clearHuffmanTrees(final HuffEntry[] array) {
        for (int i = 0; i < 80; ++i) {
            array[i] = null;
        }
    }
}
