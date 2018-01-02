// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.engines.AESFastEngine;

public class SymCipher
{
    AESFastEngine m_aesEngine1;
    AESFastEngine m_aesEngine2;
    byte[] m_iv1;
    byte[] m_iv2;
    byte[] m_aesKeys;
    
    public SymCipher() {
        this.m_iv1 = null;
        this.m_iv2 = null;
        this.m_aesEngine1 = new AESFastEngine();
        this.m_aesEngine2 = new AESFastEngine();
        this.m_aesKeys = null;
        this.m_iv1 = new byte[16];
        for (int i = 0; i < 16; ++i) {
            this.m_iv1[i] = 0;
        }
        this.m_iv2 = new byte[16];
        for (int j = 0; j < 16; ++j) {
            this.m_iv2[j] = 0;
        }
    }
    
    public synchronized int setEncryptionKeyAES(final byte[] array) {
        return this.setEncryptionKeyAES(array, 0, 16);
    }
    
    public synchronized int setEncryptionKeyAES(final byte[] array, final int n, final int n2) {
        try {
            System.arraycopy(array, n, this.m_aesKeys = new byte[16], 0, 16);
            this.m_aesEngine1.init(true, new KeyParameter(this.m_aesKeys));
            this.m_aesEngine2.init(true, new KeyParameter(this.m_aesKeys));
            if (n2 == 16) {
                for (int i = 0; i < 16; ++i) {
                    this.m_iv1[i] = 0;
                    this.m_iv2[i] = 0;
                }
            }
            else {
                for (int j = 0; j < 16; ++j) {
                    this.m_iv1[j] = array[j + 16 + n];
                    this.m_iv2[j] = array[j + 16 + n];
                }
            }
            return 0;
        }
        catch (Exception ex) {
            this.m_aesKeys = null;
            return -1;
        }
    }
    
    public synchronized int setEncryptionKeysAES(final byte[] encryptionKeyAES) {
        try {
            if (encryptionKeyAES.length == 16) {
                return this.setEncryptionKeyAES(encryptionKeyAES);
            }
            System.arraycopy(encryptionKeyAES, 0, this.m_aesKeys = new byte[32], 0, 32);
            this.m_aesEngine1.init(true, new KeyParameter(this.m_aesKeys, 0, 16));
            this.m_aesEngine2.init(true, new KeyParameter(this.m_aesKeys, 16, 16));
            for (int i = 0; i < 16; ++i) {
                this.m_iv1[i] = 0;
                this.m_iv2[i] = 0;
            }
            return 0;
        }
        catch (Exception ex) {
            this.m_aesKeys = null;
            return -1;
        }
    }
    
    public byte[] getKeys() {
        return this.m_aesKeys;
    }
    
    public synchronized void setIV2(final byte[] array) {
        for (int i = 0; i < 16; ++i) {
            this.m_iv2[i] = array[i];
        }
    }
    
    public int encryptAES1(final byte[] array, int i, final byte[] array2, int n, int n2) {
        for (n2 += i; i < n2 - 15; array2[n++] = (byte)(array[i++] ^ this.m_iv1[0]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[1]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[2]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[3]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[4]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[5]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[6]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[7]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[8]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[9]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[10]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[11]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[12]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[13]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[14]), array2[n++] = (byte)(array[i++] ^ this.m_iv1[15])) {
            synchronized (this.m_aesEngine1) {
                this.m_aesEngine1.processBlock(this.m_iv1, 0, this.m_iv1, 0);
            }
        }
        if (i < n2) {
            synchronized (this.m_aesEngine1) {
                this.m_aesEngine1.processBlock(this.m_iv1, 0, this.m_iv1, 0);
            }
            n2 -= i;
            for (int j = 0; j < n2; ++j) {
                array2[n++] = (byte)(array[i++] ^ this.m_iv1[j]);
            }
        }
        return 0;
    }
    
    public int encryptAES2(final byte[] array) {
        int i;
        int length;
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        int n11;
        int n12;
        int n13;
        int n14;
        int n15;
        int n16;
        for (i = 0, length = array.length; i < length - 15; n = i++, array[n] ^= this.m_iv2[0], n2 = i++, array[n2] ^= this.m_iv2[1], n3 = i++, array[n3] ^= this.m_iv2[2], n4 = i++, array[n4] ^= this.m_iv2[3], n5 = i++, array[n5] ^= this.m_iv2[4], n6 = i++, array[n6] ^= this.m_iv2[5], n7 = i++, array[n7] ^= this.m_iv2[6], n8 = i++, array[n8] ^= this.m_iv2[7], n9 = i++, array[n9] ^= this.m_iv2[8], n10 = i++, array[n10] ^= this.m_iv2[9], n11 = i++, array[n11] ^= this.m_iv2[10], n12 = i++, array[n12] ^= this.m_iv2[11], n13 = i++, array[n13] ^= this.m_iv2[12], n14 = i++, array[n14] ^= this.m_iv2[13], n15 = i++, array[n15] ^= this.m_iv2[14], n16 = i++, array[n16] ^= this.m_iv2[15]) {
            synchronized (this.m_aesEngine2) {
                this.m_aesEngine2.processBlock(this.m_iv2, 0, this.m_iv2, 0);
            }
        }
        if (i < length) {
            synchronized (this.m_aesEngine2) {
                this.m_aesEngine2.processBlock(this.m_iv2, 0, this.m_iv2, 0);
            }
            for (int n17 = length - i, j = 0; j < n17; ++j) {
                final int n18 = i++;
                array[n18] ^= this.m_iv2[j];
            }
        }
        return 0;
    }
}
