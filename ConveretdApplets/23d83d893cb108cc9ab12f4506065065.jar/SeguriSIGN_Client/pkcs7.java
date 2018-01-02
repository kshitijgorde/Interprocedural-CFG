// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.util.Enumeration;
import java.io.InputStream;
import java.security.KeyStore;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.io.IOException;
import java.security.UnrecoverableKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public class pkcs7
{
    public static byte[] build_pkcs7(final String s, final byte[] array, final String s2, final String s3, final boolean b) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IOException, CertificateException {
        final byte[] arrayIntToByte = ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 2 });
        final byte[] arrayIntToByte2 = ArrayIntToByte(new int[] { 160 });
        final byte[] signedData = SignedData(s, array, s2, s3, b);
        final byte[] arrayIntToByte3 = ArrayIntToByte(calcula_longitud(signedData.length));
        final byte[] array2 = new byte[signedData.length + arrayIntToByte3.length + 1];
        array2[0] = arrayIntToByte2[0];
        for (int i = 0; i < arrayIntToByte3.length; ++i) {
            array2[i + 1] = arrayIntToByte3[i];
        }
        for (int j = 0; j < signedData.length; ++j) {
            array2[j + 1 + arrayIntToByte3.length] = signedData[j];
        }
        final byte[] arrayIntToByte4 = ArrayIntToByte(calcula_longitud(arrayIntToByte.length + array2.length));
        final byte[] array3 = new byte[arrayIntToByte.length + array2.length + arrayIntToByte4.length + 1];
        array3[0] = 48;
        for (int k = 0; k < arrayIntToByte4.length; ++k) {
            array3[k + 1] = arrayIntToByte4[k];
        }
        for (int l = 0; l < arrayIntToByte.length; ++l) {
            array3[l + 1 + arrayIntToByte4.length] = arrayIntToByte[l];
        }
        for (int n = 0; n < array2.length; ++n) {
            array3[n + 1 + arrayIntToByte4.length + arrayIntToByte.length] = array2[n];
        }
        return array3;
    }
    
    private static byte[] SignedData(final String s, final byte[] array, final String s2, final String s3, final boolean b) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IOException, CertificateException {
        final byte[] version = version();
        final byte[] digestAlgorithms = digestAlgorithms(s);
        final byte[] contentInfo = contentInfo(array, b);
        final byte[] certificates = certificates(s2, s3);
        final byte[] signerInfos = signerInfos(s2, s3, s, array, b);
        final byte b2 = 48;
        final int n = version.length + digestAlgorithms.length + contentInfo.length + certificates.length + signerInfos.length;
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(n));
        final byte[] array2 = new byte[n + arrayIntToByte.length + 1];
        array2[0] = b2;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array2[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < version.length; ++j) {
            array2[j + 1 + arrayIntToByte.length] = version[j];
        }
        for (int k = 0; k < digestAlgorithms.length; ++k) {
            array2[k + 1 + arrayIntToByte.length + version.length] = digestAlgorithms[k];
        }
        for (int l = 0; l < contentInfo.length; ++l) {
            array2[l + 1 + arrayIntToByte.length + version.length + digestAlgorithms.length] = contentInfo[l];
        }
        for (int n2 = 0; n2 < certificates.length; ++n2) {
            array2[n2 + 1 + arrayIntToByte.length + version.length + digestAlgorithms.length + contentInfo.length] = certificates[n2];
        }
        for (int n3 = 0; n3 < signerInfos.length; ++n3) {
            array2[n3 + 1 + arrayIntToByte.length + version.length + digestAlgorithms.length + contentInfo.length + certificates.length] = signerInfos[n3];
        }
        return array2;
    }
    
    private static byte[] version() {
        return new byte[] { 2, 1, 1 };
    }
    
    private static byte[] digestAlgorithms(final String s) {
        final int[] array = { 49, 14, 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 2, 5, 0 };
        final int[] array2 = { 49, 14, 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 5, 5, 0 };
        final int[] array3 = { 49, 11, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0 };
        final byte[] arrayIntToByte = ArrayIntToByte(array);
        final byte[] arrayIntToByte2 = ArrayIntToByte(array2);
        final byte[] arrayIntToByte3 = ArrayIntToByte(array3);
        if (s.equalsIgnoreCase("MD5withRSA")) {
            return arrayIntToByte2;
        }
        if (s.equalsIgnoreCase("MD2withRSA")) {
            return arrayIntToByte;
        }
        return arrayIntToByte3;
    }
    
    private static byte[] contentInfo(final byte[] array, final boolean b) {
        final byte[] arrayIntToByte = ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 1 });
        byte[] content = new byte[0];
        if (!b) {
            content = content(array);
        }
        final byte[] arrayIntToByte2 = ArrayIntToByte(calcula_longitud(arrayIntToByte.length + content.length));
        final byte[] array2 = new byte[arrayIntToByte.length + content.length + arrayIntToByte2.length + 1];
        array2[0] = 48;
        for (int i = 0; i < arrayIntToByte2.length; ++i) {
            array2[i + 1] = arrayIntToByte2[i];
        }
        for (int j = 0; j < arrayIntToByte.length; ++j) {
            array2[j + 1 + arrayIntToByte2.length] = arrayIntToByte[j];
        }
        for (int k = 0; k < content.length; ++k) {
            array2[k + 1 + arrayIntToByte2.length + arrayIntToByte.length] = content[k];
        }
        return array2;
    }
    
    private static byte[] content(final byte[] array) {
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(array.length));
        final byte b = 4;
        final byte[] array2 = new byte[array.length + arrayIntToByte.length + 1];
        array2[0] = b;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array2[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < array.length; ++j) {
            array2[j + 1 + arrayIntToByte.length] = array[j];
        }
        final byte[] arrayIntToByte2 = ArrayIntToByte(calcula_longitud(array2.length));
        final byte[] arrayIntToByte3 = ArrayIntToByte(new int[] { 160 });
        final byte[] array3 = new byte[array2.length + arrayIntToByte2.length + 1];
        array3[0] = arrayIntToByte3[0];
        for (int k = 0; k < arrayIntToByte2.length; ++k) {
            array3[k + 1] = arrayIntToByte2[k];
        }
        for (int l = 0; l < array2.length; ++l) {
            array3[l + 1 + arrayIntToByte2.length] = array2[l];
        }
        return array3;
    }
    
    private static byte[] certificates(final String s, final String s2) throws CertificateEncodingException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        final byte[] array = new byte[0];
        final byte[] encoded = getCertificateFromPFX(s, s2.toCharArray()).getEncoded();
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(encoded.length));
        final byte[] arrayIntToByte2 = ArrayIntToByte(new int[] { 160 });
        final byte[] array2 = new byte[encoded.length + arrayIntToByte.length + 1];
        array2[0] = arrayIntToByte2[0];
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array2[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < encoded.length; ++j) {
            array2[j + 1 + arrayIntToByte.length] = encoded[j];
        }
        return array2;
    }
    
    private static byte[] signerInfos(final String s, final String s2, final String s3, final byte[] array, final boolean b) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IOException, CertificateException {
        final byte[] signerInfo = signerInfo(s, s2, s3, array, b);
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(signerInfo.length));
        final byte[] array2 = new byte[signerInfo.length + arrayIntToByte.length + 1];
        array2[0] = 49;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array2[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < signerInfo.length; ++j) {
            array2[j + 1 + arrayIntToByte.length] = signerInfo[j];
        }
        return array2;
    }
    
    private static byte[] authenticated_attributes(final byte[] array) {
        final byte[] arrayIntToByte = ArrayIntToByte(new int[] { 48, 24, 6, 9, 42, 134, 72, 134, 247, 13, 1, 9, 3, 49, 11, 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 1 });
        final byte[] arrayIntToByte2 = ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 9, 4 });
        final byte b = 4;
        final byte[] arrayIntToByte3 = ArrayIntToByte(calcula_longitud(array.length));
        final byte[] array2 = new byte[array.length + arrayIntToByte3.length + 1];
        array2[0] = b;
        for (int i = 0; i < arrayIntToByte3.length; ++i) {
            array2[i + 1] = arrayIntToByte3[i];
        }
        for (int j = 0; j < array.length; ++j) {
            array2[j + 1 + arrayIntToByte3.length] = array[j];
        }
        final byte b2 = 49;
        final byte[] arrayIntToByte4 = ArrayIntToByte(calcula_longitud(array2.length));
        final byte[] array3 = new byte[array2.length + arrayIntToByte4.length + 1];
        array3[0] = b2;
        for (int k = 0; k < arrayIntToByte4.length; ++k) {
            array3[k + 1] = arrayIntToByte4[k];
        }
        for (int l = 0; l < array2.length; ++l) {
            array3[l + 1 + arrayIntToByte4.length] = array2[l];
        }
        final byte b3 = 48;
        final byte[] arrayIntToByte5 = ArrayIntToByte(calcula_longitud(array3.length + arrayIntToByte2.length));
        final byte[] array4 = new byte[array3.length + arrayIntToByte2.length + arrayIntToByte5.length + 1];
        array4[0] = b3;
        for (int n = 0; n < arrayIntToByte5.length; ++n) {
            array4[n + 1] = arrayIntToByte5[n];
        }
        for (int n2 = 0; n2 < arrayIntToByte2.length; ++n2) {
            array4[n2 + 1 + arrayIntToByte5.length] = arrayIntToByte2[n2];
        }
        for (int n3 = 0; n3 < array3.length; ++n3) {
            array4[n3 + 1 + arrayIntToByte5.length + arrayIntToByte2.length] = array3[n3];
        }
        final byte[] arrayIntToByte6 = ArrayIntToByte(new int[] { 160 });
        final byte[] arrayIntToByte7 = ArrayIntToByte(calcula_longitud(arrayIntToByte.length + array4.length));
        final byte[] array5 = new byte[arrayIntToByte.length + array4.length + arrayIntToByte7.length + 1];
        array5[0] = arrayIntToByte6[0];
        for (int n4 = 0; n4 < arrayIntToByte7.length; ++n4) {
            array5[n4 + 1] = arrayIntToByte7[n4];
        }
        for (int n5 = 0; n5 < arrayIntToByte.length; ++n5) {
            array5[n5 + 1 + arrayIntToByte7.length] = arrayIntToByte[n5];
        }
        for (int n6 = 0; n6 < array4.length; ++n6) {
            array5[n6 + 1 + arrayIntToByte7.length + arrayIntToByte.length] = array4[n6];
        }
        return array5;
    }
    
    private static byte[] signerInfo(final String s, final String s2, final String s3, final byte[] array, final boolean b) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IOException, CertificateException {
        final byte[] array2 = { 2, 1, 1 };
        final X509Certificate certificateFromPFX = getCertificateFromPFX(s, s2.toCharArray());
        final byte[] encoded = certificateFromPFX.getIssuerX500Principal().getEncoded();
        final byte[] byteArray = certificateFromPFX.getSerialNumber().toByteArray();
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(byteArray.length));
        final byte b2 = 2;
        final byte[] array3 = new byte[byteArray.length + arrayIntToByte.length + 1];
        array3[0] = b2;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array3[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < byteArray.length; ++j) {
            array3[j + 1 + arrayIntToByte.length] = byteArray[j];
        }
        final byte[] arrayIntToByte2 = ArrayIntToByte(calcula_longitud(encoded.length + array3.length));
        final byte b3 = 48;
        final byte[] array4 = new byte[encoded.length + array3.length + arrayIntToByte2.length + 1];
        array4[0] = b3;
        for (int k = 0; k < arrayIntToByte2.length; ++k) {
            array4[k + 1] = arrayIntToByte2[k];
        }
        for (int l = 0; l < encoded.length; ++l) {
            array4[l + 1 + arrayIntToByte2.length] = encoded[l];
        }
        for (int n = 0; n < array3.length; ++n) {
            array4[n + 1 + arrayIntToByte2.length + encoded.length] = array3[n];
        }
        final int[] array5 = { 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 2, 5, 0 };
        final int[] array6 = { 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 5, 5, 0 };
        final int[] array7 = { 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0 };
        final byte[] arrayIntToByte3 = ArrayIntToByte(array5);
        final byte[] arrayIntToByte4 = ArrayIntToByte(array6);
        final byte[] arrayIntToByte5 = ArrayIntToByte(array7);
        byte[] array8;
        if (s3.equalsIgnoreCase("MD5withRSA")) {
            array8 = arrayIntToByte4;
        }
        else if (s3.equalsIgnoreCase("MD2withRSA")) {
            array8 = arrayIntToByte3;
        }
        else {
            array8 = arrayIntToByte5;
        }
        byte[] authenticated_attributes = new byte[0];
        if (b) {
            authenticated_attributes = authenticated_attributes(array);
        }
        final byte[] arrayIntToByte6 = ArrayIntToByte(new int[] { 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 1, 5, 0 });
        final byte[] array9 = new byte[0];
        byte[] array11;
        if (b) {
            final byte[] array10 = new byte[authenticated_attributes.length];
            for (int n2 = 0; n2 < authenticated_attributes.length; ++n2) {
                array10[n2] = authenticated_attributes[n2];
            }
            array10[0] = 49;
            array11 = encrypted_digest(s, s2, array10, s3);
        }
        else {
            array11 = encrypted_digest(s, s2, array, s3);
        }
        final byte b4 = 4;
        final byte[] arrayIntToByte7 = ArrayIntToByte(calcula_longitud(array11.length));
        final byte[] array12 = new byte[array11.length + arrayIntToByte7.length + 1];
        array12[0] = b4;
        for (int n3 = 0; n3 < arrayIntToByte7.length; ++n3) {
            array12[n3 + 1] = arrayIntToByte7[n3];
        }
        for (int n4 = 0; n4 < array11.length; ++n4) {
            array12[n4 + 1 + arrayIntToByte7.length] = array11[n4];
        }
        final int n5 = array2.length + array4.length + array8.length + authenticated_attributes.length + arrayIntToByte6.length + array12.length;
        final byte[] arrayIntToByte8 = ArrayIntToByte(calcula_longitud(n5));
        final byte[] array13 = new byte[n5 + arrayIntToByte8.length + 1];
        array13[0] = b3;
        for (int n6 = 0; n6 < arrayIntToByte8.length; ++n6) {
            array13[n6 + 1] = arrayIntToByte8[n6];
        }
        for (int n7 = 0; n7 < array2.length; ++n7) {
            array13[n7 + 1 + arrayIntToByte8.length] = array2[n7];
        }
        for (int n8 = 0; n8 < array4.length; ++n8) {
            array13[n8 + 1 + arrayIntToByte8.length + array2.length] = array4[n8];
        }
        for (int n9 = 0; n9 < array8.length; ++n9) {
            array13[n9 + 1 + arrayIntToByte8.length + array2.length + array4.length] = array8[n9];
        }
        for (int n10 = 0; n10 < authenticated_attributes.length; ++n10) {
            array13[n10 + 1 + arrayIntToByte8.length + array2.length + array4.length + array8.length] = authenticated_attributes[n10];
        }
        for (int n11 = 0; n11 < arrayIntToByte6.length; ++n11) {
            array13[n11 + 1 + arrayIntToByte8.length + array2.length + array4.length + array8.length + authenticated_attributes.length] = arrayIntToByte6[n11];
        }
        for (int n12 = 0; n12 < array12.length; ++n12) {
            array13[n12 + 1 + arrayIntToByte8.length + array2.length + array4.length + array8.length + authenticated_attributes.length + arrayIntToByte6.length] = array12[n12];
        }
        return array13;
    }
    
    private static int[] calcula_longitud(int n) {
        final int[] array = { 0 };
        if (n < 128) {
            return new int[] { n };
        }
        if (n < 256) {
            return new int[] { 129, n };
        }
        if (n < 65536) {
            final int[] array2 = { 0, 0, n & 0xFF };
            n >>>= 8;
            array2[1] = (n & 0xFF);
            array2[0] = 130;
            return array2;
        }
        if (n < 16777216) {
            final int[] array3 = { 0, 0, 0, n & 0xFFFF };
            n >>>= 8;
            array3[2] = (n & 0xFFFF);
            n >>>= 8;
            array3[1] = (n & 0xFFFF);
            array3[0] = 131;
            return array3;
        }
        if (n <= Integer.MAX_VALUE) {
            final int[] array4 = { 0, 0, 0, 0, n & 0xFFFFFF };
            n >>>= 8;
            array4[3] = (n & 0xFFFFFF);
            n >>>= 8;
            array4[2] = (n & 0xFFFFFF);
            n >>>= 8;
            array4[1] = (n & 0xFFFFFF);
            array4[0] = 132;
            return array4;
        }
        System.out.println("No se programo el algoritmo para calcular una longitud tan grande");
        return array;
    }
    
    private static void Print_Array(final String s, final byte[] array) {
        System.out.print(s + "\t La longitud del arreglo es: " + array.length + "\t\n ");
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            if (n < 16) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(n) + " ");
        }
        System.out.println(" \n\n");
    }
    
    private static byte[] encrypted_digest(final String s, final String s2, final byte[] array, final String s3) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, IOException, CertificateException {
        final byte[] array2 = new byte[0];
        final PrivateKey privateKeyFromPFX = getPrivateKeyFromPFX(s, s2.toCharArray());
        final Signature instance = Signature.getInstance(s3);
        instance.initSign(privateKeyFromPFX);
        instance.update(array);
        return instance.sign();
    }
    
    private static PrivateKey getPrivateKeyFromPFX(final String s, final char[] array) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, IOException, CertificateException {
        final FileInputStream fileInputStream = new FileInputStream(s);
        final KeyStore instance = KeyStore.getInstance("PKCS12");
        instance.load(fileInputStream, array);
        final Enumeration<String> aliases = instance.aliases();
        while (aliases.hasMoreElements()) {
            final String s2 = aliases.nextElement();
            if (instance.isKeyEntry(s2)) {
                return (PrivateKey)instance.getKey(s2, array);
            }
        }
        return null;
    }
    
    private static X509Certificate getCertificateFromPFX(final String s, final char[] array) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        final FileInputStream fileInputStream = new FileInputStream(s);
        final KeyStore instance = KeyStore.getInstance("PKCS12");
        instance.load(fileInputStream, array);
        final Enumeration<String> aliases = instance.aliases();
        while (aliases.hasMoreElements()) {
            final String s2 = aliases.nextElement();
            if (instance.isKeyEntry(s2)) {
                return (X509Certificate)instance.getCertificate(s2);
            }
        }
        return null;
    }
    
    private static byte[] ArrayIntToByte(final int[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)(array[i] & 0xFF);
        }
        return array2;
    }
}
