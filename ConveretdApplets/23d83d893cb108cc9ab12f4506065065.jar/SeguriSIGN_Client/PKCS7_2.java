// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class PKCS7_2
{
    public static byte[] build_PKCS7(final String s, final byte[] array, final X509Certificate x509Certificate, final PrivateKey privateKey) throws SignatureException, InvalidKeyException, CertificateEncodingException, NoSuchAlgorithmException {
        final byte[] arrayIntToByte = ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 2 });
        final byte[] arrayIntToByte2 = ArrayIntToByte(new int[] { 160 });
        final byte[] build_SignedData = build_SignedData(s, array, x509Certificate, privateKey);
        final byte[] arrayIntToByte3 = ArrayIntToByte(calcula_longitud(build_SignedData.length));
        final byte[] array2 = new byte[build_SignedData.length + arrayIntToByte3.length + 1];
        array2[0] = arrayIntToByte2[0];
        for (int i = 0; i < arrayIntToByte3.length; ++i) {
            array2[i + 1] = arrayIntToByte3[i];
        }
        for (int j = 0; j < build_SignedData.length; ++j) {
            array2[j + 1 + arrayIntToByte3.length] = build_SignedData[j];
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
    
    public static byte[] build_SignedData(final String s, final byte[] array, final X509Certificate x509Certificate, final PrivateKey privateKey) throws SignatureException, InvalidKeyException, CertificateEncodingException, NoSuchAlgorithmException {
        final byte[] build_version = build_version();
        final byte[] build_digestAlgorithms = build_digestAlgorithms(s);
        final byte[] build_contentInfo = build_contentInfo(array);
        final byte[] build_certificates = build_certificates(x509Certificate);
        final byte[] build_signerInfos = build_signerInfos(x509Certificate, privateKey, s, array);
        final byte b = 48;
        final int n = build_version.length + build_digestAlgorithms.length + build_contentInfo.length + build_certificates.length + build_signerInfos.length;
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(n));
        final byte[] array2 = new byte[n + arrayIntToByte.length + 1];
        array2[0] = b;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array2[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < build_version.length; ++j) {
            array2[j + 1 + arrayIntToByte.length] = build_version[j];
        }
        for (int k = 0; k < build_digestAlgorithms.length; ++k) {
            array2[k + 1 + arrayIntToByte.length + build_version.length] = build_digestAlgorithms[k];
        }
        for (int l = 0; l < build_contentInfo.length; ++l) {
            array2[l + 1 + arrayIntToByte.length + build_version.length + build_digestAlgorithms.length] = build_contentInfo[l];
        }
        for (int n2 = 0; n2 < build_certificates.length; ++n2) {
            array2[n2 + 1 + arrayIntToByte.length + build_version.length + build_digestAlgorithms.length + build_contentInfo.length] = build_certificates[n2];
        }
        for (int n3 = 0; n3 < build_signerInfos.length; ++n3) {
            array2[n3 + 1 + arrayIntToByte.length + build_version.length + build_digestAlgorithms.length + build_contentInfo.length + build_certificates.length] = build_signerInfos[n3];
        }
        return array2;
    }
    
    public static byte[] build_version() {
        return new byte[] { 2, 1, 1 };
    }
    
    public static byte[] build_digestAlgorithms(final String s) {
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
    
    public static byte[] build_contentInfo(final byte[] array) {
        final byte[] arrayIntToByte = ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 1 });
        final byte[] build_content = build_content(array);
        final byte[] arrayIntToByte2 = ArrayIntToByte(calcula_longitud(arrayIntToByte.length + build_content.length));
        final byte[] array2 = new byte[arrayIntToByte.length + build_content.length + arrayIntToByte2.length + 1];
        array2[0] = 48;
        for (int i = 0; i < arrayIntToByte2.length; ++i) {
            array2[i + 1] = arrayIntToByte2[i];
        }
        for (int j = 0; j < arrayIntToByte.length; ++j) {
            array2[j + 1 + arrayIntToByte2.length] = arrayIntToByte[j];
        }
        for (int k = 0; k < build_content.length; ++k) {
            array2[k + 1 + arrayIntToByte2.length + arrayIntToByte.length] = build_content[k];
        }
        return array2;
    }
    
    public static byte[] build_content(final byte[] array) {
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
    
    public static byte[] build_certificates(final X509Certificate x509Certificate) throws CertificateEncodingException {
        final byte[] array = new byte[0];
        final byte[] encoded = x509Certificate.getEncoded();
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
    
    public static byte[] build_signerInfos(final X509Certificate x509Certificate, final PrivateKey privateKey, final String s, final byte[] array) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        final byte[] build_signerInfo = build_signerInfo(x509Certificate, privateKey, s, array);
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(build_signerInfo.length));
        final byte[] array2 = new byte[build_signerInfo.length + arrayIntToByte.length + 1];
        array2[0] = 49;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array2[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < build_signerInfo.length; ++j) {
            array2[j + 1 + arrayIntToByte.length] = build_signerInfo[j];
        }
        return array2;
    }
    
    public static byte[] build_signerInfo(final X509Certificate x509Certificate, final PrivateKey privateKey, final String s, final byte[] array) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        final byte[] array2 = { 2, 1, 1 };
        final byte[] encoded = x509Certificate.getIssuerX500Principal().getEncoded();
        final byte[] byteArray = x509Certificate.getSerialNumber().toByteArray();
        final byte[] arrayIntToByte = ArrayIntToByte(calcula_longitud(byteArray.length));
        final byte b = 2;
        final byte[] array3 = new byte[byteArray.length + arrayIntToByte.length + 1];
        array3[0] = b;
        for (int i = 0; i < arrayIntToByte.length; ++i) {
            array3[i + 1] = arrayIntToByte[i];
        }
        for (int j = 0; j < byteArray.length; ++j) {
            array3[j + 1 + arrayIntToByte.length] = byteArray[j];
        }
        final byte[] arrayIntToByte2 = ArrayIntToByte(calcula_longitud(encoded.length + array3.length));
        final byte b2 = 48;
        final byte[] array4 = new byte[encoded.length + array3.length + arrayIntToByte2.length + 1];
        array4[0] = b2;
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
        if (s.equalsIgnoreCase("MD5withRSA")) {
            array8 = arrayIntToByte4;
        }
        else if (s.equalsIgnoreCase("MD2withRSA")) {
            array8 = arrayIntToByte3;
        }
        else {
            array8 = arrayIntToByte5;
        }
        final byte[] arrayIntToByte6 = ArrayIntToByte(new int[] { 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 1, 5, 0 });
        final byte[] firma_archivo = firma_archivo(privateKey, array, s);
        final byte b3 = 4;
        final byte[] arrayIntToByte7 = ArrayIntToByte(calcula_longitud(firma_archivo.length));
        final byte[] array9 = new byte[firma_archivo.length + arrayIntToByte7.length + 1];
        array9[0] = b3;
        for (int n2 = 0; n2 < arrayIntToByte7.length; ++n2) {
            array9[n2 + 1] = arrayIntToByte7[n2];
        }
        for (int n3 = 0; n3 < firma_archivo.length; ++n3) {
            array9[n3 + 1 + arrayIntToByte7.length] = firma_archivo[n3];
        }
        final int n4 = array2.length + array4.length + array8.length + arrayIntToByte6.length + array9.length;
        final byte[] arrayIntToByte8 = ArrayIntToByte(calcula_longitud(n4));
        final byte[] array10 = new byte[n4 + arrayIntToByte8.length + 1];
        array10[0] = b2;
        for (int n5 = 0; n5 < arrayIntToByte8.length; ++n5) {
            array10[n5 + 1] = arrayIntToByte8[n5];
        }
        for (int n6 = 0; n6 < array2.length; ++n6) {
            array10[n6 + 1 + arrayIntToByte8.length] = array2[n6];
        }
        for (int n7 = 0; n7 < array4.length; ++n7) {
            array10[n7 + 1 + arrayIntToByte8.length + array2.length] = array4[n7];
        }
        for (int n8 = 0; n8 < array8.length; ++n8) {
            array10[n8 + 1 + arrayIntToByte8.length + array2.length + array4.length] = array8[n8];
        }
        for (int n9 = 0; n9 < arrayIntToByte6.length; ++n9) {
            array10[n9 + 1 + arrayIntToByte8.length + array2.length + array4.length + array8.length] = arrayIntToByte6[n9];
        }
        for (int n10 = 0; n10 < array9.length; ++n10) {
            array10[n10 + 1 + arrayIntToByte8.length + array2.length + array4.length + array8.length + arrayIntToByte6.length] = array9[n10];
        }
        return array10;
    }
    
    public static int[] calcula_longitud(int n) {
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
        System.out.println("No se programo el algoritmo para calcular una longitud tan grande");
        return array;
    }
    
    public static void Print_Array(final String s, final byte[] array) {
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
    
    public static byte[] firma_archivo(final PrivateKey privateKey, final byte[] array, final String s) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        final byte[] array2 = new byte[0];
        final Signature instance = Signature.getInstance(s);
        instance.initSign(privateKey);
        instance.update(array);
        return instance.sign();
    }
    
    public static byte[] ArrayIntToByte(final int[] array) {
        final byte[] array2 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (byte)(array[i] & 0xFF);
        }
        return array2;
    }
}
