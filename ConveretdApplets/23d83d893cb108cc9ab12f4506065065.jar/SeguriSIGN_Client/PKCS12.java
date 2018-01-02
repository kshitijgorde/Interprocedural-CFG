// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.security.InvalidAlgorithmParameterException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.SecretKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import java.security.spec.KeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.Key;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class PKCS12
{
    public byte[] encoded;
    
    public PKCS12(final byte[] array) {
        this.encoded = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.encoded[i] = array[i];
        }
    }
    
    public PKCS12() {
    }
    
    public byte[] decode(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        final byte[] array5 = new byte[20];
        for (int i = 0; i < array2.length; ++i) {
            array5[i] = array2[i];
        }
        for (int j = array2.length; j < 20; ++j) {
            array5[j] = 0;
        }
        final MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.reset();
        for (int k = 0; k < array3.length; ++k) {
            instance.update(array3[k]);
        }
        for (int l = 0; l < array.length; ++l) {
            instance.update(array[l]);
        }
        final byte[] digest = instance.digest();
        final MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
        instance2.reset();
        for (int n = 0; n < digest.length; ++n) {
            instance2.update(digest[n]);
        }
        for (int n2 = 0; n2 < array2.length; ++n2) {
            instance2.update(array2[n2]);
        }
        final SecretKeySpec secretKeySpec = new SecretKeySpec(instance2.digest(), "HmacSHA1");
        final Mac instance3 = Mac.getInstance("HmacSHA1");
        instance3.init(secretKeySpec);
        for (int n3 = 0; n3 < array5.length; ++n3) {
            instance3.update(array5[n3]);
        }
        for (int n4 = 0; n4 < array2.length; ++n4) {
            instance3.update(array2[n4]);
        }
        final byte[] doFinal = instance3.doFinal();
        final Mac instance4 = Mac.getInstance("HmacSHA1");
        instance4.init(secretKeySpec);
        for (int n5 = 0; n5 < array5.length; ++n5) {
            instance4.update(array5[n5]);
        }
        final byte[] doFinal2 = instance4.doFinal();
        final Mac instance5 = Mac.getInstance("HmacSHA1");
        instance5.init(secretKeySpec);
        for (int n6 = 0; n6 < doFinal2.length; ++n6) {
            instance5.update(doFinal2[n6]);
        }
        for (int n7 = 0; n7 < array2.length; ++n7) {
            instance5.update(array2[n7]);
        }
        final byte[] doFinal3 = instance5.doFinal();
        final byte[] array6 = new byte[doFinal.length + doFinal3.length];
        for (int n8 = 0; n8 < doFinal.length; ++n8) {
            array6[n8] = doFinal[n8];
        }
        for (int n9 = 0; n9 < doFinal3.length; ++n9) {
            array6[n9 + doFinal.length] = doFinal3[n9];
        }
        final byte[] array7 = new byte[24];
        for (int n10 = 0; n10 < 24; ++n10) {
            array7[n10] = array6[n10];
        }
        final byte[] array8 = new byte[8];
        for (int n11 = 0; n11 < 8; ++n11) {
            array8[n11] = array6[n11 + 32];
        }
        final SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(array6));
        final Cipher instance6 = Cipher.getInstance("DESede/CBC/NoPadding");
        instance6.init(2, generateSecret, new IvParameterSpec(array8));
        return instance6.doFinal(array4);
    }
    
    public void print() {
        for (int i = 0; i < this.encoded.length; ++i) {
            if (i % 16 == 0) {
                System.out.println();
            }
            System.out.print(Integer.toHexString(byte2Int(this.encoded[i])) + " ");
        }
    }
    
    public static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
    
    public byte[] getEncryptedPrivateKey() {
        final byte[] array = new byte[640];
        for (int i = 0; i < 640; ++i) {
            array[i] = this.encoded[i + 38];
        }
        return array;
    }
}
