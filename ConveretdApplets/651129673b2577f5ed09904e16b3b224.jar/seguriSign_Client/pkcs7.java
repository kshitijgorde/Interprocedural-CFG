// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKey;

public class pkcs7
{
    private static final int SIGNED = 0;
    private static final int SIGNED_AND_ENVELOPE = 1;
    private static int tipo_pkcs7;
    private static SecretKey simetric_key;
    private static IvParameterSpec iv;
    
    private static byte[] version() {
        return new byte[] { 2, 1, 1 };
    }
    
    private static byte[] digestAlgorithms(final String s) {
        final int[] array = { 49, 14, 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 2, 5, 0 };
        final int[] array2 = { 49, 14, 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 5, 5, 0 };
        final int[] array3 = { 49, 11, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0 };
        final byte[] arrayIntToByte = ASN1.ArrayIntToByte(array);
        final byte[] arrayIntToByte2 = ASN1.ArrayIntToByte(array2);
        final byte[] arrayIntToByte3 = ASN1.ArrayIntToByte(array3);
        if (s.equalsIgnoreCase("MD5withRSA")) {
            return arrayIntToByte2;
        }
        if (s.equalsIgnoreCase("MD2withRSA")) {
            return arrayIntToByte;
        }
        return arrayIntToByte3;
    }
    
    private static byte[] build_contentInfo(final byte[] array, final boolean b) {
        final byte[] arrayIntToByte = ASN1.ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 1 });
        byte[] build_content = new byte[0];
        if (!b) {
            build_content = build_content(array);
        }
        return ASN1.encode(new byte[][] { arrayIntToByte, build_content }, "SEQUENCE");
    }
    
    private static byte[] build_content(final byte[] array) {
        return ASN1.encode(ASN1.encode(array, "OCTET STRING"), 160);
    }
    
    private static byte[] build_certificates(final X509Certificate x509Certificate) throws Exception {
        return ASN1.encode(x509Certificate.getEncoded(), 160);
    }
    
    private static byte[] build_certificates(final String s, final String s2) throws Exception {
        return build_certificates(Utilities.getCertificateFromPFX(s, s2.toCharArray()));
    }
    
    private static byte[] build_signerInfos(final X509Certificate x509Certificate, final PrivateKey privateKey, final String s, final byte[] array, final boolean b) throws Exception {
        return ASN1.encode(build_signerInfo(x509Certificate, privateKey, s, array, b), "SET");
    }
    
    private static byte[] build_signerInfos(final String s, final String s2, final String s3, final byte[] array, final boolean b) throws Exception {
        return build_signerInfos(Utilities.getCertificateFromPFX(s, s2.toCharArray()), Utilities.getPrivateKeyFromPFX(s, s2.toCharArray()), s3, array, b);
    }
    
    private static byte[] build_signerInfo(final String s, final String s2, final String s3, final byte[] array, final boolean b) throws Exception {
        return build_signerInfo(Utilities.getCertificateFromPFX(s, s2.toCharArray()), Utilities.getPrivateKeyFromPFX(s, s2.toCharArray()), s3, array, b);
    }
    
    private static byte[] build_signerInfo(final X509Certificate x509Certificate, final PrivateKey privateKey, final String s, final byte[] array, final boolean b) throws Exception {
        final byte[] array2 = { 2, 1, 1 };
        final byte[] encode = ASN1.encode(new byte[][] { x509Certificate.getIssuerX500Principal().getEncoded(), ASN1.encode(x509Certificate.getSerialNumber().toByteArray(), "INTEGER") }, "SEQUENCE");
        final int[] array3 = { 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 2, 5, 0 };
        final int[] array4 = { 48, 12, 6, 8, 42, 134, 72, 134, 247, 13, 2, 5, 5, 0 };
        final int[] array5 = { 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0 };
        final byte[] arrayIntToByte = ASN1.ArrayIntToByte(array3);
        final byte[] arrayIntToByte2 = ASN1.ArrayIntToByte(array4);
        final byte[] arrayIntToByte3 = ASN1.ArrayIntToByte(array5);
        byte[] array6;
        if (s.equalsIgnoreCase("MD5withRSA")) {
            array6 = arrayIntToByte2;
        }
        else if (s.equalsIgnoreCase("MD2withRSA")) {
            array6 = arrayIntToByte;
        }
        else {
            array6 = arrayIntToByte3;
        }
        byte[] authenticated_attributes = new byte[0];
        if (b && pkcs7.tipo_pkcs7 != 1) {
            authenticated_attributes = authenticated_attributes(array);
        }
        final byte[] arrayIntToByte4 = ASN1.ArrayIntToByte(new int[] { 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 1, 5, 0 });
        final byte[] array7 = new byte[0];
        byte[] array9;
        if (b && pkcs7.tipo_pkcs7 != 1) {
            final byte[] array8 = new byte[authenticated_attributes.length];
            for (int i = 0; i < authenticated_attributes.length; ++i) {
                array8[i] = authenticated_attributes[i];
            }
            array8[0] = 49;
            array9 = encrypted_digest(privateKey, array8, s);
        }
        else {
            array9 = encrypted_digest(privateKey, array, s);
        }
        final byte[] array10 = new byte[0];
        byte[] doFinal;
        if (pkcs7.tipo_pkcs7 == 1) {
            final Cipher instance = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            instance.init(1, pkcs7.simetric_key, pkcs7.iv);
            doFinal = instance.doFinal(array9);
        }
        else {
            doFinal = array9;
        }
        return ASN1.encode(new byte[][] { array2, encode, array6, authenticated_attributes, arrayIntToByte4, ASN1.encode(doFinal, "OCTET STRING") }, "SEQUENCE");
    }
    
    private static byte[] encrypted_digest(final PrivateKey privateKey, final byte[] array, final String s) throws Exception {
        final byte[] array2 = new byte[0];
        final Signature instance = Signature.getInstance(s);
        instance.initSign(privateKey);
        instance.update(array);
        return instance.sign();
    }
    
    private static byte[] encrypted_digest(final String s, final String s2, final byte[] array, final String s3) throws Exception {
        return encrypted_digest(Utilities.getPrivateKeyFromPFX(s, s2.toCharArray()), array, s3);
    }
    
    private static byte[] authenticated_attributes(final byte[] array) {
        return ASN1.encode(new byte[][] { ASN1.ArrayIntToByte(new int[] { 48, 24, 6, 9, 42, 134, 72, 134, 247, 13, 1, 9, 3, 49, 11, 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 1 }), ASN1.encode(new byte[][] { ASN1.ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 9, 4 }), ASN1.encode(ASN1.encode(array, "OCTET STRING"), "SET") }, "SEQUENCE") }, 160);
    }
    
    private static byte[] build_recipientInfos(final X509Certificate x509Certificate) throws Exception {
        return ASN1.encode(build_recipientInfo(x509Certificate), "SET");
    }
    
    private static byte[] build_recipientInfo(final X509Certificate x509Certificate) throws Exception {
        final byte[] array = { 2, 1, 0 };
        final byte[] encode = ASN1.encode(new byte[][] { x509Certificate.getIssuerX500Principal().getEncoded(), ASN1.encode(x509Certificate.getSerialNumber().toByteArray(), "INTEGER") }, "SEQUENCE");
        final byte[] encode2 = ASN1.encode(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(x509Certificate.getTBSCertificate()).value).remaining).remaining).remaining).remaining).remaining).remaining).value).value, "SEQUENCE");
        final KeyGenerator instance = KeyGenerator.getInstance("DESede");
        instance.init(new SecureRandom());
        pkcs7.simetric_key = instance.generateKey();
        final ASN1 asn1 = new ASN1(new ASN1(new ASN1(x509Certificate.getPublicKey().getEncoded()).value).remaining);
        final byte[] array2 = new byte[asn1.value.length - 1];
        for (int i = 0; i < asn1.value.length - 1; ++i) {
            array2[i] = asn1.value[i + 1];
        }
        final ASN1 asn2 = new ASN1(new ASN1(array2).value);
        final ASN1 asn3 = new ASN1(asn2.remaining);
        int length = asn2.value.length;
        if (asn2.value[0] == 0) {
            --length;
        }
        final byte[] encoded = pkcs7.simetric_key.getEncoded();
        final int n = length - 3 - encoded.length;
        final byte[] array3 = new byte[n];
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(array3);
        final byte[] array4 = new byte[n];
        secureRandom.nextBytes(array4);
        int j = n;
        while (j > 0) {
            j = n;
            for (int k = 0; k < n; ++k) {
                if (array3[k] == 0) {
                    array3[k] = array4[secureRandom.nextInt(n)];
                }
                else {
                    --j;
                }
            }
        }
        final byte[] array5 = new byte[length];
        array5[0] = 0;
        array5[1] = 2;
        for (int l = 0; l < n; ++l) {
            array5[l + 2] = array3[l];
        }
        array5[n + 2] = 0;
        for (int n2 = 0; n2 < encoded.length; ++n2) {
            array5[n2 + 2 + array3.length + 1] = encoded[n2];
        }
        final byte[] byteArray = new BigInteger(array5).modPow(new BigInteger(asn3.value), new BigInteger(asn2.value)).toByteArray();
        final byte[] array6 = new byte[length];
        if (byteArray[0] == 0 && byteArray.length > length) {
            for (int n3 = 0; n3 < length; ++n3) {
                array6[n3] = byteArray[n3 + 1];
            }
        }
        else if (byteArray.length == length) {
            for (int n4 = 0; n4 < length; ++n4) {
                array6[n4] = byteArray[n4];
            }
        }
        else {
            final int n5 = length - byteArray.length;
            for (int n6 = 0; n6 < n5; ++n6) {
                array6[n6] = 0;
            }
            for (int n7 = n5; n7 < length; ++n7) {
                array6[n7] = byteArray[n7 - n5];
            }
        }
        return ASN1.encode(new byte[][] { array, encode, encode2, ASN1.encode(array6, "OCTET STRING") }, "SEQUENCE");
    }
    
    private static byte[] build_encrypted_content_info(final byte[] array) throws Exception {
        final byte[] arrayIntToByte = ASN1.ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 1 });
        final byte[] arrayIntToByte2 = ASN1.ArrayIntToByte(new int[] { 6, 8, 42, 134, 72, 134, 247, 13, 3, 7 });
        final Cipher instance = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        instance.init(1, pkcs7.simetric_key);
        final byte[] encoded = instance.getParameters().getEncoded();
        final byte[] array2 = new byte[8];
        for (int i = 0; i < 8; ++i) {
            array2[i] = encoded[i + 2];
        }
        pkcs7.iv = new IvParameterSpec(array2);
        return ASN1.encode(new byte[][] { arrayIntToByte, ASN1.encode(new byte[][] { arrayIntToByte2, encoded }, "SEQUENCE"), ASN1.encode(ASN1.encode(instance.doFinal(array), "OCTET STRING"), 160) }, "SEQUENCE");
    }
    
    private static byte[] build_SignedAndEnvelpedData(final String s, final byte[] array, final String s2, final String s3, final boolean b, final X509Certificate x509Certificate) throws Exception {
        pkcs7.tipo_pkcs7 = 1;
        return ASN1.encode(new byte[][] { version(), build_recipientInfos(x509Certificate), digestAlgorithms(s), build_encrypted_content_info(array), build_certificates(s2, s3), build_signerInfos(s2, s3, s, array, b) }, "SEQUENCE");
    }
    
    private static byte[] build_SignedAndEnvelpedData(final String s, final byte[] array, final PrivateKey privateKey, final X509Certificate x509Certificate, final boolean b, final X509Certificate x509Certificate2) throws Exception {
        pkcs7.tipo_pkcs7 = 1;
        return ASN1.encode(new byte[][] { version(), build_recipientInfos(x509Certificate2), digestAlgorithms(s), build_encrypted_content_info(array), build_certificates(x509Certificate), build_signerInfos(x509Certificate, privateKey, s, array, b) }, "SEQUENCE");
    }
    
    private static byte[] build_SignedData(final String s, final byte[] array, final X509Certificate x509Certificate, final PrivateKey privateKey, final boolean b) throws Exception {
        pkcs7.tipo_pkcs7 = 0;
        return ASN1.encode(new byte[][] { version(), digestAlgorithms(s), build_contentInfo(array, b), build_certificates(x509Certificate), build_signerInfos(x509Certificate, privateKey, s, array, b) }, "SEQUENCE");
    }
    
    private static byte[] build_SignedData(final String s, final byte[] array, final String s2, final String s3, final boolean b) throws Exception {
        return build_SignedData(s, array, Utilities.getCertificateFromPFX(s2, s3.toCharArray()), Utilities.getPrivateKeyFromPFX(s2, s3.toCharArray()), b);
    }
    
    public static byte[] build_pkcs7(final String s, final byte[] array, final X509Certificate x509Certificate, final PrivateKey privateKey, final boolean b) throws Exception {
        return ASN1.encode(new byte[][] { ASN1.ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 2 }), ASN1.encode(build_SignedData(s, array, x509Certificate, privateKey, b), 160) }, "SEQUENCE");
    }
    
    public static byte[] build_pkcs7(final String s, final byte[] array, final String s2, final String s3, final boolean b) throws Exception {
        final X509Certificate certificateFromPFX = Utilities.getCertificateFromPFX(s2, s3.toCharArray());
        final PrivateKey privateKeyFromPFX = Utilities.getPrivateKeyFromPFX(s2, s3.toCharArray());
        if (certificateFromPFX == null) {
            throw new Exception("Could not decode your PKCS12 file (" + s2 + ")");
        }
        return build_pkcs7(s, array, certificateFromPFX, privateKeyFromPFX, b);
    }
    
    public static byte[] build_pkcs7(final String s, final byte[] array, final String s2, final String s3, final boolean b, final X509Certificate x509Certificate) throws Exception {
        return ASN1.encode(new byte[][] { ASN1.ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 4 }), ASN1.encode(build_SignedAndEnvelpedData(s, array, s2, s3, b, x509Certificate), 160) }, "SEQUENCE");
    }
    
    public static byte[] build_pkcs7(final String s, final byte[] array, final PrivateKey privateKey, final X509Certificate x509Certificate, final boolean b, final X509Certificate x509Certificate2) throws Exception {
        return ASN1.encode(new byte[][] { ASN1.ArrayIntToByte(new int[] { 6, 9, 42, 134, 72, 134, 247, 13, 1, 7, 4 }), ASN1.encode(build_SignedAndEnvelpedData(s, array, privateKey, x509Certificate, b, x509Certificate2), 160) }, "SEQUENCE");
    }
    
    private static byte[] get_private_exponent() throws Exception {
        final String s = "segurisign.key";
        final String s2 = "12121212qw";
        final ASN1 asn1 = new ASN1(new ASN1(Utilities.getBytesFromFile(new File(s))).value);
        final ASN1 asn2 = new ASN1(asn1.value);
        final ASN1 asn3 = new ASN1(asn1.remaining);
        final ASN1 asn4 = new ASN1(new ASN1(asn2.remaining).value);
        final ASN1 asn5 = new ASN1(asn4.remaining);
        if (asn5.value.length > 1) {
            System.out.println("ALERTA: No se calculara bien el numero de iteraciones !!! \n\n");
        }
        final Cipher instance = Cipher.getInstance("PBEWithMD5AndDES");
        instance.init(2, SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(s2.toCharArray())), new PBEParameterSpec(asn4.value, asn5.value[0]));
        final ASN1 asn6 = new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(instance.doFinal(asn3.value))).getEncoded()).value).remaining).remaining).value).value).remaining).remaining).remaining);
        final ASN1 asn7 = new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(asn6.remaining).remaining).remaining).remaining).remaining);
        return asn6.value;
    }
}
