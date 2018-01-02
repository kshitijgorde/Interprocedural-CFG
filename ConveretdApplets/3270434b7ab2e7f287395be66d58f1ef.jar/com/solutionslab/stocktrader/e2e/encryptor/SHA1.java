// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

public class SHA1
{
    private static final int MAX_HASH_SIZE_IN_BYTES = 20;
    private static final int NUM_OF_BITS_PER_BLOCK = 512;
    private static final int NUM_OF_BITS_PER_BYTE = 8;
    private static final int NUM_OF_BITS_PER_WORD = 32;
    private static final int NUM_OF_BITS_FOR_MSG_LENGTH = 64;
    private static final int MSG_LENGTH_BYTE_ARRAY_OFFSET = 60;
    private static final int NUM_OF_BYTES_PER_BLOCK = 64;
    private static final int NUM_OF_BYTES_PER_WORD = 4;
    private static final int NUM_OF_WORDS_PER_BLOCK = 16;
    private static final int MAX_NUM_OF_PROCESS_STEPS = 80;
    private static final int PROCESSING_STEP_19 = 19;
    private static final int PROCESSING_STEP_39 = 39;
    private static final int PROCESSING_STEP_59 = 59;
    private static final int PROCESSING_STEP_79 = 79;
    private static final int K1 = 1518500249;
    private static final int K2 = 1859775393;
    private static final int K3 = -1894007588;
    private static final int K4 = -899497514;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int H0;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int temp;
    private int[] W;
    private int[] M;
    private int numOfIntegralBlocksInMsg;
    private int numOfPaddedMsgBlocks;
    private byte[] hashByteArray;
    private int[] lastPaddedMsgBlock;
    private int[] secondLastPaddedMsgBlock;
    
    public SHA1() {
        this.W = new int[80];
        this.M = new int[16];
        this.hashByteArray = new byte[20];
        this.lastPaddedMsgBlock = new int[16];
        this.H0 = 1732584193;
        this.H1 = -271733879;
        this.H2 = -1732584194;
        this.H3 = 271733878;
        this.H4 = -1009589776;
    }
    
    public byte[] doHash(final byte[] array, final int n) {
        this.padInputMessage(array, n);
        for (int i = 1; i <= this.numOfPaddedMsgBlocks; ++i) {
            if (i <= this.numOfIntegralBlocksInMsg) {
                this.convertByteArrayToBlock(this.M, array, i - 1);
            }
            else if (i == this.numOfPaddedMsgBlocks) {
                System.arraycopy(this.lastPaddedMsgBlock, 0, this.M, 0, 16);
            }
            else {
                System.arraycopy(this.secondLastPaddedMsgBlock, 0, this.M, 0, 16);
            }
            System.arraycopy(this.M, 0, this.W, 0, 16);
            for (int j = 16; j < 80; ++j) {
                this.W[j] = this.rotateWordLeft(this.W[j - 3] ^ this.W[j - 8] ^ this.W[j - 14] ^ this.W[j - 16], 1);
            }
            this.A = this.H0;
            this.B = this.H1;
            this.C = this.H2;
            this.D = this.H3;
            this.E = this.H4;
            for (int k = 0; k <= 79; ++k) {
                if (k >= 0 && k <= 19) {
                    this.temp = this.rotateWordLeft(this.A, 5) + ((this.B & this.C) | (~this.B & this.D)) + this.E + this.W[k] + 1518500249;
                }
                else if (k > 19 && k <= 39) {
                    this.temp = this.rotateWordLeft(this.A, 5) + (this.B ^ this.C ^ this.D) + this.E + this.W[k] + 1859775393;
                }
                else if (k > 39 && k <= 59) {
                    this.temp = this.rotateWordLeft(this.A, 5) + ((this.B & this.C) | (this.B & this.D) | (this.C & this.D)) + this.E + this.W[k] - 1894007588;
                }
                else {
                    this.temp = this.rotateWordLeft(this.A, 5) + (this.B ^ this.C ^ this.D) + this.E + this.W[k] - 899497514;
                }
                this.E = this.D;
                this.D = this.C;
                this.C = this.rotateWordLeft(this.B, 30);
                this.B = this.A;
                this.A = this.temp;
            }
            this.H0 += this.A;
            this.H1 += this.B;
            this.H2 += this.C;
            this.H3 += this.D;
            this.H4 += this.E;
        }
        this.convertIntToByteArray(this.H0, this.hashByteArray, 0);
        this.convertIntToByteArray(this.H1, this.hashByteArray, 4);
        this.convertIntToByteArray(this.H2, this.hashByteArray, 8);
        this.convertIntToByteArray(this.H3, this.hashByteArray, 12);
        this.convertIntToByteArray(this.H4, this.hashByteArray, 16);
        return this.hashByteArray;
    }
    
    public void padInputMessage(final byte[] array, final int n) {
        final byte[] array2 = new byte[64];
        final int n2 = n * 8;
        this.numOfIntegralBlocksInMsg = n / 64;
        final int n3 = this.numOfIntegralBlocksInMsg * 64;
        final int n4 = n - n3;
        final int n5 = n4 * 8;
        this.numOfPaddedMsgBlocks = this.numOfIntegralBlocksInMsg + 1;
        this.fillByteArray(array2, 0);
        System.arraycopy(array, n3, array2, 0, n4);
        array2[n4] = -128;
        if (n5 > 447) {
            this.convertByteArrayToBlock(this.secondLastPaddedMsgBlock = new int[16], array2, 0);
            this.fillByteArray(array2, 0);
            this.convertIntToByteArray(n2, array2, 60);
            this.convertByteArrayToBlock(this.lastPaddedMsgBlock, array2, 0);
            ++this.numOfPaddedMsgBlocks;
        }
        else {
            this.convertIntToByteArray(n2, array2, 60);
            this.convertByteArrayToBlock(this.lastPaddedMsgBlock, array2, 0);
        }
    }
    
    private int rotateWordLeft(final int n, final int n2) {
        return n << n2 | n >>> 32 - n2;
    }
    
    private void convertByteArrayToBlock(final int[] array, final byte[] array2, final int n) {
        final int n2 = n * 64;
        for (int i = 0; i < 16; ++i) {
            array[i] = this.convertByteArrayToInt(array2, n2 + i * 4);
        }
    }
    
    private int convertByteArrayToInt(final byte[] array, final int n) {
        return (array[n] & 0xFF) << 24 | (array[n + 1] & 0xFF) << 16 | (array[n + 2] & 0xFF) << 8 | (array[n + 3] & 0xFF);
    }
    
    private void convertIntToByteArray(final int n, final byte[] array, final int n2) {
        array[n2] = (byte)(n >>> 24);
        array[n2 + 1] = (byte)(n >>> 16);
        array[n2 + 2] = (byte)(n >>> 8);
        array[n2 + 3] = (byte)n;
    }
    
    private void fillByteArray(final byte[] array, final int n) {
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i] = (byte)n;
        }
    }
}
