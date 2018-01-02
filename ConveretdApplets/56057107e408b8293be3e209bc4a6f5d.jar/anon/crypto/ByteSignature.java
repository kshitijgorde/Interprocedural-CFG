// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import logging.LogHolder;
import logging.LogType;

public final class ByteSignature
{
    public static boolean verify(final byte[] array, final byte[] array2, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair) {
        return verify(array, array2, asymmetricCryptoKeyPair.getPublic());
    }
    
    public static boolean verify(final byte[] array, final byte[] array2, final IMyPublicKey myPublicKey) {
        if (myPublicKey == null) {
            LogHolder.log(7, LogType.CRYPTO, "key==null!");
            return false;
        }
        final ISignatureVerificationAlgorithm signatureAlgorithm = myPublicKey.getSignatureAlgorithm();
        if (signatureAlgorithm != null) {
            return signatureAlgorithm.verify(array, array2);
        }
        LogHolder.log(2, LogType.CRYPTO, "Try to verify a message - unknown algorithm!");
        return false;
    }
    
    public static byte[] sign(final byte[] array, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair) {
        return sign(array, asymmetricCryptoKeyPair.getPrivate());
    }
    
    public static byte[] sign(final byte[] array, final IMyPrivateKey myPrivateKey) {
        if (myPrivateKey == null) {
            return null;
        }
        return myPrivateKey.getSignatureAlgorithm().sign(array);
    }
    
    public static String toHexString(final byte[] array) {
        return toHexString(array, ":");
    }
    
    public static String toHexString(final byte[] array, final String s) {
        String s2 = "";
        if (array == null || array.length == 0) {
            return s2;
        }
        String trim;
        if (s == null || (trim = s.trim()).length() == 0) {
            trim = "";
        }
        for (int i = 0; i < array.length; ++i) {
            String s3 = Integer.toHexString(0xFF & array[i]).toUpperCase();
            if (s3.length() == 1) {
                s3 = "0" + s3;
            }
            s2 += s3;
            if (i + 1 < array.length) {
                s2 += trim;
            }
        }
        return s2;
    }
}
