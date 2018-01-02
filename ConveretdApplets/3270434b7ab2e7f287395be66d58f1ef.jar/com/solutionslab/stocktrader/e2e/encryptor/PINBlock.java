// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

public class PINBlock
{
    private static final int PIN_BLOCK_FILL_CHARACTER = 255;
    private static final int FMT_2_CONTROL_BYTE = 2;
    private static final int FMT_12_CONTROL_BYTE = 193;
    private static final int ISO_FORMAT_2_TYPE = 1;
    private static final int ISO_FORMAT_12_TYPE = 2;
    private static final int MAX_PIN_STRING_SIZE = 30;
    private static final int MIN_PIN_STRING_SIZE = 4;
    private static final int MAX_NUMERIC_PIN_STRING_SIZE = 12;
    private static final int MAX_NUMERIC_PIN_BYTE_SIZE = 6;
    private static final int DECIMAL_RADIX = 10;
    private static final int NUM_OF_BYTES_IN_FMT2_PIN_BLOCK = 8;
    private static final int NUM_OF_BYTES_PER_CNTRL_AND_PIN_LENGTH = 2;
    private static final int ERR_INVALID_PIN_LENGTH = 10;
    private static final int ERR_INVALID_PIN = 11;
    private byte[] PINBlockByteArray;
    private int PINLength;
    private int PINBlockType;
    private int numberOfPINBlocks;
    private int PINBlockLength;
    
    public PINBlock(final String s) throws PINBlockException {
        this.ValidateAndCreatePINBlockByteArray(s);
    }
    
    private void ValidateAndCreatePINBlockByteArray(final String s) throws PINBlockException {
        final byte[] array = new byte[30];
        final byte[] array2 = new byte[12];
        final boolean b = false;
        int n = 0;
        if (s == null) {
            throw new PINBlockException("Error no : 11 - Invalid PIN String");
        }
        this.PINLength = s.length();
        if (this.PINLength > 30 || this.PINLength < 4) {
            throw new PINBlockException("Error no : 10 - Invalid PIN length");
        }
        if (this.PINLength <= 12) {
            n = 1;
        }
        for (int i = 0; i < this.PINLength; ++i) {
            final char char1 = s.charAt(i);
            if (n != 0) {
                if (!Character.isDigit(char1)) {
                    n = 0;
                }
                else {
                    array2[i] = (byte)Character.digit(char1, 10);
                }
            }
            array[i] = (byte)char1;
        }
        if (b) {
            throw new PINBlockException("Error no : 11 - Invalid PIN String");
        }
        if (n != 0) {
            this.createFormat2PINBlock(array2);
        }
        else {
            this.createFormat12PINBlock(array);
        }
    }
    
    private void createFormat2PINBlock(final byte[] array) {
        this.PINBlockType = 1;
        this.PINBlockLength = 8;
        Encryptor.fillByteArray(this.PINBlockByteArray = new byte[8], 255);
        this.PINBlockByteArray[0] = (byte)(0x20 | (this.PINLength & 0xFF));
        Encryptor.convertAsciiArrayToHexByteArray(array, this.PINBlockByteArray, 1, this.PINLength);
    }
    
    private void createFormat12PINBlock(final byte[] array) {
        this.PINBlockType = 2;
        if (this.PINLength <= 6) {
            this.numberOfPINBlocks = 1;
        }
        else {
            this.numberOfPINBlocks = 2 + (this.PINLength - 7) / 8;
        }
        this.PINBlockLength = this.numberOfPINBlocks * 8;
        switch (this.numberOfPINBlocks) {
            case 1: {
                this.PINBlockByteArray = new byte[8];
                break;
            }
            case 2: {
                this.PINBlockByteArray = new byte[16];
                break;
            }
            case 3: {
                this.PINBlockByteArray = new byte[24];
                break;
            }
            default: {
                this.PINBlockByteArray = new byte[32];
                break;
            }
        }
        Encryptor.fillByteArray(this.PINBlockByteArray, 255);
        this.PINBlockByteArray[0] = -63;
        this.PINBlockByteArray[1] = (byte)this.PINLength;
        System.arraycopy(array, 0, this.PINBlockByteArray, 2, this.PINLength);
    }
    
    public byte[] getBytes() {
        return this.PINBlockByteArray;
    }
    
    public int length() {
        return this.PINBlockLength;
    }
    
    public int getPINBlockType() {
        return this.PINBlockType;
    }
}
