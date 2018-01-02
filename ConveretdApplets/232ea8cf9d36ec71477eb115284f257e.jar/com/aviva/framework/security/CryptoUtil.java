// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.security;

import com.aviva.framework.util.ConversionUtil;
import java.security.Key;
import javax.crypto.Cipher;
import java.security.spec.KeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;

public class CryptoUtil
{
    private SecretKey key;
    public static final String DECRYPTION_FAILED = "";
    public static final String ENCRYPTION_FAILED = "";
    private static final String cipherMode = "DESede/ECB/PKCS5Padding";
    private static final String keyAlgo = "DESede";
    
    private String fixLength(final String str, final int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        }
        final StringBuffer buf = new StringBuffer(str);
        for (int i = str.length(); i <= length; ++i) {
            buf.append('0');
        }
        return buf.toString();
    }
    
    public CryptoUtil(final String randomKey) {
        try {
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            final DESedeKeySpec dkSpec = new DESedeKeySpec(this.fixLength(randomKey, 24).getBytes());
            this.key = keyFactory.generateSecret(dkSpec);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String encrypt(final String message) throws CryptoException {
        try {
            final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(1, this.key);
            final byte[] stringBytes = message.getBytes("UTF8");
            final byte[] raw = cipher.doFinal(stringBytes);
            return ConversionUtil.bytesToHex(raw);
        }
        catch (Exception ce) {
            throw new CryptoException("Failed to decrypt", "", ce);
        }
    }
    
    public String decrypt(final String encrypted) throws CryptoException {
        try {
            final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(2, this.key);
            final byte[] raw = ConversionUtil.hexToBytes(encrypted);
            final byte[] stringBytes = cipher.doFinal(raw);
            final String clear = new String(stringBytes, "UTF8");
            return clear;
        }
        catch (Exception ce) {
            throw new CryptoException("Failed to decrypt", "", ce);
        }
    }
    
    public static void main(final String[] args) {
        try {
            final CryptoUtil b = new CryptoUtil("test44444kfgfsgsfgfgfgsfgdfsgey");
            final String encResult = b.encrypt("This is my test message");
            final String clearResult = b.decrypt(encResult);
            System.out.println("Encrypted value: " + encResult);
            System.out.println("Decrypted value: " + clearResult);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
