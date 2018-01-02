// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

import java.util.Vector;

public class PINMessage
{
    private static final byte ONE_PIN_BLOCK_IN_MESSAGE = 1;
    private static final byte TWO_PIN_BLOCKS_IN_MESSAGE = 2;
    private static final int OAEP_SHA1_OFFSET_IN_BYTES = 42;
    private static final int MAX_MESSAGE_SIZE_IN_BYTES = 86;
    private static final int MIN_PIN_BLOCK_SIZE = 8;
    private static final int PIN_MESSAGE_TYPE_SIZE = 1;
    private static final int NUM_OF_NIBBLES_PER_BYTE = 2;
    private static final int MIN_RANDOM_NUMBER_STRING_LENGTH = 16;
    private static final int MAX_RANDOM_NUMBER_SIZE_IN_BYTES = 77;
    private static final int MAX_RANDOM_NUMBER_STRING_LENGTH = 154;
    private static final int ERR_INVALID_PIN_BLOCK = 20;
    private static final int ERR_INVALID_RANDOM_NUMBER_LENGTH = 21;
    private static final int ERR_INVALID_RANDOM_NUMBER = 22;
    private byte[] pinMessageArray;
    private Vector pinMessageVector;
    private int pinMessageLength;
    private byte pinMessageType;
    
    public PINMessage(final PINBlock pinBlock, final String s) throws PINMessageException {
        this.pinMessageArray = new byte[86];
        this.pinMessageType = 1;
        this.pinMessageArray[0] = this.pinMessageType;
        this.pinMessageLength = 1;
        this.addPinBlockToMessageArray(pinBlock);
        this.addRandomStringToMessageArray(s);
        this.pinMessageVector = new Vector(this.pinMessageLength);
        this.copyByteArrayIntoVector(this.pinMessageArray, this.pinMessageVector, this.pinMessageLength);
    }
    
    public PINMessage(final PINBlock pinBlock, final PINBlock pinBlock2, final String s) throws PINMessageException {
        this.pinMessageArray = new byte[86];
        this.pinMessageType = 2;
        this.pinMessageArray[0] = this.pinMessageType;
        this.pinMessageLength = 1;
        this.addPinBlockToMessageArray(pinBlock);
        this.addPinBlockToMessageArray(pinBlock2);
        this.addRandomStringToMessageArray(s);
        this.pinMessageVector = new Vector(this.pinMessageLength);
        this.copyByteArrayIntoVector(this.pinMessageArray, this.pinMessageVector, this.pinMessageLength);
    }
    
    private void addPinBlockToMessageArray(final PINBlock pinBlock) throws PINMessageException {
        if (pinBlock == null) {
            throw new PINMessageException("Error no : 20 - Invalid PIN Block");
        }
        final int length = pinBlock.length();
        System.arraycopy(pinBlock.getBytes(), 0, this.pinMessageArray, this.pinMessageLength, length);
        this.pinMessageLength += length;
    }
    
    private void addRandomStringToMessageArray(final String s) throws PINMessageException {
        if (s == null) {
            throw new PINMessageException("Error no : 22 - Invalid Random Number String");
        }
        final int length = s.length();
        final int n = (length + 1) / 2;
        final int n2 = (86 - this.pinMessageLength) * 2;
        if (length < 16 || length > n2 || length != n * 2) {
            throw new PINMessageException("Error no : 21 - Invalid Random Number String length");
        }
        if (Encryptor.convertStringToPackedHexByteArray(s, this.pinMessageArray, this.pinMessageLength) != 0) {
            throw new PINMessageException("Error no : 22 - Invalid Random Number String");
        }
        this.pinMessageLength += n;
    }
    
    private void copyByteArrayIntoVector(final byte[] array, final Vector vector, final int n) {
        for (int i = 0; i < n; ++i) {
            vector.addElement(new Byte(array[i]));
        }
    }
    
    public Vector getMsgVector() {
        return this.pinMessageVector;
    }
    
    public void getBytes(final Byte[] array) {
        for (int size = this.pinMessageVector.size(), i = 0; i < size; ++i) {
            array[i] = (Byte)this.pinMessageVector.elementAt(i);
        }
    }
    
    public int length() {
        return this.pinMessageLength;
    }
}
