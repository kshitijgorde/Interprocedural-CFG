// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.InvalidKeyException;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import java.security.spec.KeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.PrivateKey;

public class pkcs5v2
{
    static int hLen;
    
    public PrivateKey decryptKey(final byte[] array, final String s) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, Exception {
        final ASN1 asn1 = new ASN1(new ASN1(array).value);
        final byte[] value = new ASN1(asn1.remaining).value;
        final ASN1 asn2 = new ASN1(asn1.value);
        String decodeOID;
        try {
            decodeOID = this.decodeOID(asn2.value);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error parsing private key");
        }
        byte[] value2 = new byte[0];
        int intValue = 0;
        int intValue2 = 0;
        byte[] array2 = new byte[0];
        int n = 0;
        final byte[] array3 = new byte[0];
        String string = null;
        byte[] array4 = null;
        Label_1213: {
            if (decodeOID.equals("1.2.840.113549.1.5.13")) {
                final ASN1 asn3 = new ASN1(new ASN1(asn2.remaining).value);
                final ASN1 asn4 = new ASN1(asn3.value);
                String decodeOID2;
                try {
                    decodeOID2 = this.decodeOID(asn4.value);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    throw new Exception("Error parsing private key");
                }
                if (decodeOID2.equals("1.2.840.113549.1.5.12")) {
                    final ASN1 asn5 = new ASN1(new ASN1(asn4.remaining).value);
                    value2 = asn5.value;
                    final ASN1 asn6 = new ASN1(asn5.remaining);
                    intValue = new BigInteger(1, asn6.value).intValue();
                    if (asn6.remaining.length > 0) {
                        intValue2 = new BigInteger(1, new ASN1(asn6.remaining).value).intValue();
                    }
                }
                final ASN1 asn7 = new ASN1(new ASN1(asn3.remaining).value);
                String decodeOID3;
                try {
                    decodeOID3 = this.decodeOID(asn7.value);
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                    throw new Exception("Error parsing private key");
                }
                if (decodeOID3.equals("1.2.840.113549.3.2")) {
                    string = "RC2";
                }
                else if (decodeOID3.equals("1.3.6.1.4.1.188.7.1.1.2")) {
                    string = "IDEA";
                }
                else if (decodeOID3.equals("1.3.14.3.2.7")) {
                    string = "DES";
                    intValue2 = 8;
                }
                else if (decodeOID3.equals("1.2.840.113549.3.7")) {
                    string = "TripleDES";
                    intValue2 = 24;
                }
                else {
                    string = "desconocido (" + decodeOID3 + ")";
                }
                if (string.equals("DES") || string.equals("TripleDES")) {
                    array2 = new ASN1(asn7.remaining).value;
                }
                if (string.equals("RC2")) {
                    final ASN1 asn8 = new ASN1(new ASN1(asn7.remaining).value);
                    if (asn8.tag.equalsIgnoreCase("INTEGER")) {
                        final int intValue3 = new BigInteger(1, asn8.value).intValue();
                        array2 = new ASN1(asn8.remaining).value;
                        switch (intValue3) {
                            case 160: {
                                n = 40;
                                break;
                            }
                            case 120: {
                                n = 64;
                                break;
                            }
                            case 58: {
                                n = 128;
                                break;
                            }
                        }
                        if (intValue3 > 255) {
                            n = intValue3;
                        }
                    }
                    else {
                        array2 = asn8.value;
                    }
                }
                if (intValue2 == 0) {
                    throw new NoSuchAlgorithmException("Algorithm " + decodeOID3 + " not supported");
                }
                try {
                    array4 = this.PBKDF2(s, value2, intValue, intValue2);
                    break Label_1213;
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                    throw new Exception("Error decoding private key");
                }
            }
            String s2;
            int n2;
            if (decodeOID.equals("1.2.840.113549.1.5.3")) {
                s2 = "MD5";
                string = "DES";
                n2 = 16;
            }
            else if (decodeOID.equals("1.2.840.113549.1.5.1")) {
                s2 = "MD2";
                string = "DES";
                n2 = 16;
            }
            else if (decodeOID.equals("1.2.840.113549.1.5.10")) {
                s2 = "SHA1";
                string = "DES";
                n2 = 20;
            }
            else if (decodeOID.equals("1.2.840.113549.1.12.1.3")) {
                s2 = "SHA1";
                string = "TripleDES";
                n2 = 20;
            }
            else if (decodeOID.equals("1.2.840.113549.1.5.4")) {
                s2 = "MD2";
                string = "RC2";
                n2 = 16;
            }
            else if (decodeOID.equals("1.2.840.113549.1.5.6")) {
                s2 = "MD5";
                string = "RC2";
                n2 = 16;
            }
            else {
                if (!decodeOID.equals("1.2.840.113549.1.5.11")) {
                    System.out.println("Algorithm " + decodeOID + " not supported.");
                    return null;
                }
                s2 = "SHA1";
                string = "RC2";
                n2 = 20;
            }
            final ASN1 asn9 = new ASN1(new ASN1(asn2.remaining).value);
            final byte[] value3 = asn9.value;
            final int intValue4 = new BigInteger(1, new ASN1(asn9.remaining).value).intValue();
            if (string.equalsIgnoreCase("DES")) {
                final byte[] pbkdf1 = this.PBKDF1(s, value3, intValue4, 16, s2);
                final byte[] array5 = new byte[8];
                array2 = new byte[8];
                System.arraycopy(pbkdf1, 0, array5, 0, 8);
                System.arraycopy(pbkdf1, 8, array2, 0, 8);
            }
            if (string.equalsIgnoreCase("RC2")) {
                final byte[] pbkdf2 = this.PBKDF1(s, value3, intValue4, 16, s2);
                array4 = new byte[8];
                array2 = new byte[8];
                System.arraycopy(pbkdf2, 0, array4, 0, 8);
                System.arraycopy(pbkdf2, 8, array2, 0, 8);
            }
            else {
                array4 = this.PBKDF1(s, value3, intValue4, n2, s2);
            }
        }
        PrivateKey privateKey;
        if (string.equals("TripleDES")) {
            final SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(array4));
            final Cipher instance = Cipher.getInstance("DESede/CBC/NoPadding");
            instance.init(2, generateSecret, new IvParameterSpec(array2));
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(instance.doFinal(value)));
        }
        else if (string.equals("DES")) {
            final SecretKey generateSecret2 = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(array4));
            final Cipher instance2 = Cipher.getInstance("DES/CBC/NoPadding");
            instance2.init(2, generateSecret2, new IvParameterSpec(array2));
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(instance2.doFinal(value)));
        }
        else {
            if (!string.equals("RC2")) {
                throw new NoSuchAlgorithmException("Algorithm " + string + " not supported");
            }
            final RC2 rc2 = new RC2();
            if (n != 0) {
                rc2.keyExpansion(array4, n);
            }
            else {
                rc2.keyExpansion(array4, 64);
            }
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(rc2.rc2DecryptCBC(value, array2)));
        }
        return privateKey;
    }
    
    private String decodeOID(final byte[] array) throws Exception {
        if (array.length == 0) {
            throw new Exception("Cannot decode an empty OID object.");
        }
        final String s = "";
        final int intValue = new Byte(array[0]);
        String s2 = s + intValue / 40 + "." + intValue % 40;
        final int[] array2 = { 0, 0, 0 };
        for (int i = 1; i < array.length; ++i) {
            int intValue2 = new Byte(array[i]);
            if (intValue2 < 0) {
                intValue2 &= 0xFF;
            }
            if (intValue2 > 127) {
                if (array2[0] == 0) {
                    array2[0] = intValue2 - 128;
                }
                else if (array2[1] == 0) {
                    array2[1] = intValue2 - 128;
                }
                else {
                    array2[2] = intValue2 - 128;
                }
            }
            else {
                if (array2[0] == 0) {
                    array2[0] = intValue2;
                }
                else if (array2[1] == 0) {
                    array2[1] = intValue2;
                }
                else {
                    array2[2] = intValue2;
                }
                int n;
                if (array2[0] != 0 && array2[1] != 0 && array2[2] != 0) {
                    n = array2[0] * 16384 + array2[1] * 128 + array2[2];
                }
                else if (array2[0] != 0 && array2[1] != 0) {
                    n = array2[0] * 128 + array2[1];
                }
                else {
                    n = array2[0];
                }
                s2 = s2 + "." + n;
                array2[0] = 0;
                array2[2] = (array2[1] = 0);
            }
        }
        return s2;
    }
    
    private byte[] PBKDF2(final String s, final byte[] array, final int n, final int n2) throws Exception {
        final byte[] array2 = new byte[n2];
        if (n2 <= 0) {
            throw new Exception("cannot derived a key with length " + n2);
        }
        if (n2 > Integer.MAX_VALUE) {
            throw new Exception("derived key too long");
        }
        final int n3 = (int)Math.ceil(n2 / pkcs5v2.hLen);
        final int n4 = n2 - (n3 - 1) * pkcs5v2.hLen;
        final byte[] array3 = new byte[n3];
        for (int i = 1; i < n3; ++i) {
            System.arraycopy(this.F(s, array, n, i), 0, array2, pkcs5v2.hLen * (i - 1), pkcs5v2.hLen);
        }
        System.arraycopy(this.F(s, array, n, n3), 0, array2, pkcs5v2.hLen * (n3 - 1), n4);
        return array2;
    }
    
    private byte[] F(final String s, final byte[] array, final int n, final int n2) throws Exception {
        byte[] xor = new byte[20];
        final byte[] array2 = new byte[4];
        if (n2 < 256) {
            array2[3] = (byte)n2;
        }
        final byte[] array3 = new byte[array.length + 4];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, 4);
        final SecretKeySpec secretKeySpec = new SecretKeySpec(s.getBytes(), "HmacSHA1");
        final Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(secretKeySpec);
        instance.update(array3);
        final byte[] doFinal = instance.doFinal();
        System.arraycopy(doFinal, 0, xor, 0, pkcs5v2.hLen);
        final byte[] array4 = new byte[20];
        System.arraycopy(doFinal, 0, array4, 0, pkcs5v2.hLen);
        for (int i = 1; i < n; ++i) {
            instance.reset();
            instance.update(array4);
            final byte[] doFinal2 = instance.doFinal();
            xor = this.XOR(xor, doFinal2);
            System.arraycopy(doFinal2, 0, array4, 0, pkcs5v2.hLen);
        }
        return xor;
    }
    
    private byte[] XOR(final byte[] array, final byte[] array2) throws Exception {
        final int length = array.length;
        if (length != array2.length) {
            throw new Exception("Cannot XOR arrays with different lengths");
        }
        final byte[] array3 = new byte[length];
        for (int i = 0; i < length; ++i) {
            array3[i] = (byte)(array[i] ^ array2[i]);
        }
        return array3;
    }
    
    private byte[] PBKDF1(final String s, final byte[] array, final int n, final int n2, final String s2) throws Exception {
        if (s2.equalsIgnoreCase("SHA1") && n2 > 20) {
            throw new Exception("derived key too long");
        }
        if ((s2.equalsIgnoreCase("MD5") || s2.equalsIgnoreCase("MD2")) && n2 > 16) {
            throw new Exception("derived key too long");
        }
        final byte[] bytes = s.getBytes();
        final int length = bytes.length;
        final int length2 = array.length;
        byte[] digest = new byte[length + length2];
        System.arraycopy(bytes, 0, digest, 0, length);
        System.arraycopy(array, 0, digest, length, length2);
        final MessageDigest instance = MessageDigest.getInstance(s2);
        final byte[] array2 = new byte[n2];
        for (int i = 0; i < n; ++i) {
            instance.update(digest);
            digest = instance.digest();
            instance.reset();
        }
        return digest;
    }
    
    static {
        pkcs5v2.hLen = 20;
    }
}
