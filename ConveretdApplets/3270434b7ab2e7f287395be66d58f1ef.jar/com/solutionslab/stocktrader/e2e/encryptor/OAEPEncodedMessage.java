// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

import java.util.Random;

public class OAEPEncodedMessage
{
    private static final int ENCODING_PARAMETER_SIZE_IN_BYTES = 16;
    private static final int SHA1_HASH_SIZE_IN_BYTES = 20;
    private static final int OAEP_SHA1_OFFSET_IN_BYTES = 42;
    private static final int MIN_PIN_MESSAGE_SIZE_IN_BYTES = 17;
    private static final int MAX_PIN_MESSAGE_SIZE_IN_BYTES = 86;
    private static final int ENCODED_MESSAGE_SIZE_IN_BYTES = 127;
    private static final int DATA_BLOCK_SIZE_IN_BYTES = 107;
    private static final int NUM_OF_BYTES_PER_WORD = 4;
    private static final int ERR_INVALID_PIN_MESSAGE = 30;
    private static final int ERR_INVALID_PIN_MESSAGE_LENGTH = 31;
    private byte[] encodedMsgByteArray;
    private int encodedMsgLength;
    private String encodedMessageString;
    private String encodingParameterString;
    private byte[] P;
    
    public OAEPEncodedMessage(final PINMessage pinMessage) throws OAEPEncodedMsgException {
        this.encodedMsgByteArray = new byte[127];
        this.P = new byte[16];
        this.encodedMsgByteArray = this.doOAEPEncoding(pinMessage);
        this.encodedMessageString = Encryptor.convertHexArrayToString(this.encodedMsgByteArray);
        this.encodingParameterString = Encryptor.convertHexArrayToString(this.P);
    }
    
    private byte[] doOAEPEncoding(final PINMessage pinMessage) throws OAEPEncodedMsgException {
        final Byte[] array = new Byte[86];
        final byte[] array2 = new byte[86];
        final byte[] array3 = new byte[20];
        final byte[] array4 = new byte[107];
        final byte[] array5 = new byte[107];
        final byte[] array6 = new byte[107];
        final byte[] array7 = new byte[20];
        final byte[] array8 = new byte[20];
        final byte[] array9 = new byte[20];
        if (pinMessage == null) {
            throw new OAEPEncodedMsgException("Error no : 30 - Invalid PIN Message");
        }
        final int length = pinMessage.length();
        if (length < 17 || length > 86) {
            throw new OAEPEncodedMsgException("Error no : 31 Invalid PIN message length");
        }
        pinMessage.getBytes(array);
        final Random random = new Random();
        this.nextBytes(random, this.P);
        final byte[] doHash = new SHA1().doHash(this.P, 16);
        Encryptor.fillByteArray(array4, 0);
        System.arraycopy(doHash, 0, array4, 0, 20);
        int n = 20 + (87 - length - 1);
        array4[n] = 1;
        ++n;
        this.copyByteArray(array, array2, length);
        System.arraycopy(array2, 0, array4, n, length);
        this.nextBytes(random, array7);
        this.MGF1(array7, array5, 107);
        this.xorByteArrays(array4, array5, array6);
        this.MGF1(array6, array8, 20);
        this.xorByteArrays(array7, array8, array9);
        System.arraycopy(array9, 0, this.encodedMsgByteArray, 0, 20);
        System.arraycopy(array6, 0, this.encodedMsgByteArray, 20, 107);
        return this.encodedMsgByteArray;
    }
    
    private void MGF1(final byte[] array, final byte[] array2, final int n) {
        final byte[] array3 = new byte[4];
        final byte[] array4 = new byte[127];
        final byte[] array5 = new byte[20];
        final int length = array.length;
        int n2 = n / 20;
        final int n3 = n - n2 * 20;
        if (n3 > 0) {
            ++n2;
        }
        int n4 = 20;
        for (int i = 0; i < n2; ++i) {
            this.I2OSP(i, array3, 0);
            System.arraycopy(array, 0, array4, 0, length);
            System.arraycopy(array3, 0, array4, length, 4);
            final byte[] doHash = new SHA1().doHash(array4, length + 4);
            final int n5 = i * 20;
            if (i == n2 - 1 && n3 > 0) {
                n4 = n3;
            }
            System.arraycopy(doHash, 0, array2, n5, n4);
        }
    }
    
    private void xorByteArrays(final byte[] array, final byte[] array2, final byte[] array3) {
        for (int length = array.length, i = 0; i < length; ++i) {
            array3[i] = (byte)(array[i] ^ array2[i]);
        }
    }
    
    private void I2OSP(final int n, final byte[] array, final int n2) {
        array[n2] = (byte)(n >>> 24);
        array[n2 + 1] = (byte)(n >>> 16);
        array[n2 + 2] = (byte)(n >>> 8);
        array[n2 + 3] = (byte)n;
    }
    
    private void copyByteArray(final Byte[] array, final byte[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
    }
    
    public byte[] getBytes() {
        return this.encodedMsgByteArray;
    }
    
    public int length() {
        return this.encodedMsgLength;
    }
    
    public String getEncodingParameter() {
        return this.encodingParameterString;
    }
    
    private void nextBytes(final Random random, final byte[] array) {
        final int length = array.length;
        int n = 0;
        int n2 = 0;
    Block_1:
        while (true) {
            for (int i = 0; i < 4; ++i) {
                if (n == length) {
                    break Block_1;
                }
                n2 = ((i == 0) ? random.nextInt() : (n2 >> 8));
                array[n++] = (byte)n2;
            }
        }
    }
}
