// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.security;

import java.math.BigInteger;

public class RSA
{
    public static final String Ident = "$Id: RSA.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private BigInteger modulus;
    private BigInteger exponent;
    private boolean forEncryption;
    
    public int getInputBlockSize() {
        final int bitLength = this.modulus.bitLength();
        if (this.forEncryption) {
            return (bitLength + 7) / 8 - 1;
        }
        return (bitLength + 7) / 8;
    }
    
    public byte[] processBlock(final byte[] array, final int n, final int n2) throws Exception {
        if (n2 > this.getInputBlockSize() + 1) {
            throw new Exception("input too large for RSA cipher.");
        }
        if (n2 == this.getInputBlockSize() + 1 && (array[n] & 0x80) != 0x0) {
            throw new Exception("input too large for RSA cipher.");
        }
        byte[] array2;
        if (n != 0 || n2 != array.length) {
            array2 = new byte[n2];
            System.arraycopy(array, n, array2, 0, n2);
        }
        else {
            array2 = array;
        }
        final byte[] byteArray = new BigInteger(1, array2).modPow(this.exponent, this.modulus).toByteArray();
        if (this.forEncryption) {
            if (byteArray[0] == 0 && byteArray.length > this.getOutputBlockSize()) {
                final byte[] array3 = new byte[byteArray.length - 1];
                System.arraycopy(byteArray, 1, array3, 0, array3.length);
                return array3;
            }
            if (byteArray.length < this.getOutputBlockSize()) {
                final byte[] array4 = new byte[this.getOutputBlockSize()];
                System.arraycopy(byteArray, 0, array4, array4.length - byteArray.length, byteArray.length);
                return array4;
            }
        }
        else if (byteArray[0] == 0) {
            final byte[] array5 = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, array5, 0, array5.length);
            return array5;
        }
        return byteArray;
    }
    
    public int getOutputBlockSize() {
        final int bitLength = this.modulus.bitLength();
        if (this.forEncryption) {
            return (bitLength + 7) / 8;
        }
        return (bitLength + 7) / 8 - 1;
    }
    
    public void init(final boolean forEncryption, final BigInteger modulus, final BigInteger exponent) {
        this.forEncryption = forEncryption;
        this.modulus = modulus;
        this.exponent = exponent;
    }
}
