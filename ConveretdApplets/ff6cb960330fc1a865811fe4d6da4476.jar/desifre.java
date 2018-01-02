import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.KeyGenerator;

// 
// Decompiled by Procyon v0.5.30
// 

class desifre
{
    public static String desifre(final String fire) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        final SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        final byte[] initVector = { 16, 16, 1, 4, 1, 1, 1, 2 };
        final AlgorithmParameterSpec algParamSpec = new IvParameterSpec(initVector);
        final Cipher m_encrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");
        final Cipher m_decrypter = Cipher.getInstance("DES/CBC/PKCS5Padding");
        m_encrypter.init(1, key, algParamSpec);
        m_decrypter.init(2, key, algParamSpec);
        final byte[] clearText = fire.getBytes();
        final byte[] encryptedText = m_encrypter.doFinal(clearText);
        final byte[] decryptedText = m_decrypter.doFinal(encryptedText);
        System.out.println(new String(clearText));
        System.out.println(new String(encryptedText));
        System.out.println(new String(decryptedText));
        return new String(decryptedText);
    }
}
