// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.cipher;

import java.util.Random;

public class CipherByteArray extends CipherObject
{
    private static final CipherByteArray cipherObject;
    private byte[] value;
    
    public CipherByteArray() {
        this(new byte[0]);
    }
    
    public CipherByteArray(final byte[] value) {
        this.setValue(value);
    }
    
    public static byte[] cipher(final byte[] array) {
        return cipher(array, 0);
    }
    
    public static byte[] cipher(final byte[] array, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)(array[i] ^ (CipherObject.staticState[(i + abs) % 256] & 0x7F));
        }
        return array2;
    }
    
    public static byte[] cipher(final byte[] array, int abs, final long n) {
        final byte[] array2 = new byte[256];
        final byte[] array3 = new byte[256];
        new Random(n).nextBytes(array3);
        for (int i = 0; i < 256; ++i) {
            array2[i] = (byte)i;
        }
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < 256; ++j) {
            final byte b = array2[j];
            n2 = (n2 + array3[n3] + b & 0xFF);
            final byte b2 = array2[n2];
            array2[n2] = (byte)(b & 0xFF);
            array2[j] = (byte)(b2 & 0xFF);
            if (++n3 >= array3.length) {
                n3 = 0;
            }
        }
        if (abs < 0) {
            abs = Math.abs(abs);
        }
        final byte[] array4 = new byte[array.length];
        for (int k = 0; k < array.length; ++k) {
            array4[k] = (byte)(array[k] ^ (array2[(k + abs) % 256] & 0x7F));
        }
        return array4;
    }
    
    public byte[] getValue() {
        final byte[] array = new byte[this.value.length];
        for (int i = 0; i < this.value.length; ++i) {
            array[i] = (byte)(this.value[i] ^ super.state[(super.currentCryptIndex + i) % 256]);
        }
        return array;
    }
    
    public void setValue(final byte[] array) {
        super.currentCryptIndex = (int)(super.random.nextFloat() * 256.0f);
        this.value = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.value[i] = (byte)(array[i] ^ super.state[(super.currentCryptIndex + i) % 256]);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    static {
        cipherObject = new CipherByteArray();
    }
}
