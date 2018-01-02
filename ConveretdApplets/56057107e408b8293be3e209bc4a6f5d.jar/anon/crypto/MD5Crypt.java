// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import anon.util.Base64;
import java.security.MessageDigest;

public class MD5Crypt implements ICrypt
{
    private static final String magic = "$1$";
    private static final String itoa64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    private static String cryptTo64(int n, int n2) {
        final StringBuffer sb = new StringBuffer();
        while (--n2 >= 0) {
            sb.append("./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".substring(n & 0x3F, (n & 0x3F) + 1));
            n >>= 6;
        }
        return sb.toString();
    }
    
    public static String simpleHash(final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(s.getBytes());
            return Base64.encodeBytes(instance.digest());
        }
        catch (Exception ex) {
            return new String("");
        }
    }
    
    public final String crypt(final String s) throws NoSuchAlgorithmException {
        final StringBuffer sb = new StringBuffer();
        final SecureRandom secureRandom = new SecureRandom();
        while (sb.length() < 8) {
            final int n = (int)(secureRandom.nextFloat() * "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".length());
            sb.append("./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".substring(n, n + 1));
        }
        return this.crypt(s, sb.toString());
    }
    
    public final String crypt(String s, String s2) throws NoSuchAlgorithmException {
        if (s2.startsWith("$1$")) {
            s2 = s2.substring("$1$".length());
        }
        final int index = s2.indexOf(36);
        if (index != -1) {
            s2 = s2.substring(0, index);
        }
        if (s2.length() > 8) {
            s2 = s2.substring(0, 8);
        }
        s2.length();
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(s.getBytes());
        instance.update("$1$".getBytes());
        instance.update(s2.getBytes());
        final MessageDigest instance2 = MessageDigest.getInstance("MD5");
        instance2.update(s.getBytes());
        instance2.update(s2.getBytes());
        instance2.update(s.getBytes());
        final byte[] digest = instance2.digest();
        int length;
        int i;
        int n;
        for (length = digest.length, n = (i = s.length()); i > 0; i -= length) {
            instance.update(digest, 0, (i > length) ? length : i);
        }
        instance2.reset();
        final byte[] bytes = s.getBytes();
        for (int j = n; j > 0; j >>= 1) {
            if ((j & 0x1) == 0x1) {
                instance.update((byte)0);
            }
            else {
                instance.update(bytes[0]);
            }
        }
        final StringBuffer sb = new StringBuffer("$1$");
        sb.append(s2);
        sb.append("$");
        byte[] array = instance.digest();
        final byte[] bytes2 = s2.getBytes();
        for (int k = 0; k < 1000; ++k) {
            instance2.reset();
            if ((k & 0x1) == 0x1) {
                instance2.update(bytes);
            }
            else {
                instance2.update(array);
            }
            if (k % 3 != 0) {
                instance2.update(bytes2);
            }
            if (k % 7 != 0) {
                instance2.update(bytes);
            }
            if ((k & 0x1) != 0x0) {
                instance2.update(array);
            }
            else {
                instance2.update(bytes);
            }
            array = instance2.digest();
        }
        sb.append(cryptTo64((array[0] & 0xFF) << 16 | (array[6] & 0xFF) << 8 | (array[12] & 0xFF), 4));
        sb.append(cryptTo64((array[1] & 0xFF) << 16 | (array[7] & 0xFF) << 8 | (array[13] & 0xFF), 4));
        sb.append(cryptTo64((array[2] & 0xFF) << 16 | (array[8] & 0xFF) << 8 | (array[14] & 0xFF), 4));
        sb.append(cryptTo64((array[3] & 0xFF) << 16 | (array[9] & 0xFF) << 8 | (array[15] & 0xFF), 4));
        sb.append(cryptTo64((array[4] & 0xFF) << 16 | (array[10] & 0xFF) << 8 | (array[5] & 0xFF), 4));
        sb.append(cryptTo64(array[11] & 0xFF, 2));
        s2 = (s = "");
        return sb.toString();
    }
}
