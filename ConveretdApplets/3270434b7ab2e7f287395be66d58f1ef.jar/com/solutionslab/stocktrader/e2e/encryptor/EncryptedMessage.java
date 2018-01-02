// 
// Decompiled by Procyon v0.5.30
// 

package com.solutionslab.stocktrader.e2e.encryptor;

import java.math.BigInteger;

public class EncryptedMessage
{
    private static final int HEX_RADIX = 16;
    private static final int ERR_INVALID_ENCODED_MSG_LENGTH = 40;
    private static final int ERR_INVALID_RSA_KEY_LENGTH = 41;
    private static final int ERR_INVALID_RSA_KEY = 42;
    private static final int RSA_MODULUS_SIZE_IN_BITS = 1024;
    public static final int RSA_MODULUS_SIZE_IN_BYTES = 128;
    private String publicExponentString;
    private String modulusString;
    private BigInteger modulus;
    private BigInteger publicExponent;
    private int modulusSizeInBits;
    private int maxInputDataSizeInBytes;
    private int maxOutputDataSizeInBytes;
    private byte[] encMsgByteArray;
    
    public EncryptedMessage(final OAEPEncodedMessage oaepEncodedMessage) throws EncryptedMessageException {
        this.publicExponentString = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001";
        this.modulusString = "C9582D2B0A741733D525F7182A9BE6693D7BC70001EAE4E5E09EFFE57C819F9F36EF7494D4A814F6A10F37341F1FC1C53F194B9B9CA47ECB8E6318FF8C13CEF670A74614031579001C5F64652C46F28A23A5CEACE39CA960F9BD24029B07DE58BD2F574D42A31046C6D36BE623720E8B47C93A43D5D2D7831854F132BC95EF17";
        if (this.modulusString == null || this.publicExponentString == null) {
            throw new EncryptedMessageException("Error no : 41 Invalid RSA key length");
        }
        if (oaepEncodedMessage == null) {
            throw new EncryptedMessageException("Error no : 40 Invalid OAEP-encoded message length");
        }
        try {
            this.modulus = new BigInteger(this.modulusString, 16);
            this.publicExponent = new BigInteger(this.publicExponentString, 16);
            this.validateRSAEncInputData(oaepEncodedMessage.length());
            this.encMsgByteArray = this.doRSAEncryption(oaepEncodedMessage.getBytes());
        }
        catch (NumberFormatException ex) {
            throw new EncryptedMessageException("Error no : 42 Invalid RSA key length");
        }
    }
    
    private void validateRSAEncInputData(final int n) throws EncryptedMessageException {
        final int n2 = (this.modulusString.length() + 1) / 2;
        final int n3 = (this.publicExponentString.length() + 1) / 2;
        if (n2 != 128 || n3 != 128) {
            throw new EncryptedMessageException("Error no : 41 Invalid RSA key length");
        }
        this.modulusSizeInBits = n2 * 8;
        this.maxOutputDataSizeInBytes = (this.modulusSizeInBits + 7) / 8;
        this.maxInputDataSizeInBytes = this.maxOutputDataSizeInBytes - 1;
        if (n > this.maxInputDataSizeInBytes + 1) {
            throw new EncryptedMessageException("Error no : 40 input data too large for RSA encryption");
        }
    }
    
    private byte[] doRSAEncryption(final byte[] array) {
        return this.adjustEncryptedDataLength(new BigInteger(1, array).modPow(this.publicExponent, this.modulus).toByteArray());
    }
    
    private byte[] adjustEncryptedDataLength(final byte[] array) {
        final int length = array.length;
        if (array[0] == 0 && length > this.maxOutputDataSizeInBytes) {
            final int n = length - 1;
            final byte[] array2 = new byte[n];
            System.arraycopy(array, 1, array2, 0, n);
            return array2;
        }
        if (length < this.maxOutputDataSizeInBytes) {
            final byte[] array3 = new byte[this.maxOutputDataSizeInBytes];
            System.arraycopy(array, 0, array3, this.maxOutputDataSizeInBytes - length, length);
            return array3;
        }
        return array;
    }
    
    public byte[] getBytes() {
        return this.encMsgByteArray;
    }
    
    public int length() {
        return this.encMsgByteArray.length;
    }
}
