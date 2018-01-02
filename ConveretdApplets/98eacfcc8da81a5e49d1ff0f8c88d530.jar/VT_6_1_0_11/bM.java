// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

final class bM
{
    private static final char[] a;
    
    private static String b(final byte[] array) {
        final StringBuffer sb = new StringBuffer(array.length << 1);
        for (int i = 0; i < array.length; ++i) {
            sb.append(bM.a[array[i] >> 4 & 0xF]).append(bM.a[array[i] & 0xF]);
        }
        return sb.toString();
    }
    
    private static byte[] c(final byte[] array) {
        try {
            return MessageDigest.getInstance("MD5").digest(array);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Error(ex.toString());
        }
    }
    
    private static byte[] b(final byte[] array, final byte[] array2) {
        try {
            final MessageDigest instance;
            (instance = MessageDigest.getInstance("MD5")).update(array);
            return instance.digest(array2);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Error(ex.toString());
        }
    }
    
    public static final String a(final byte[] array) {
        return b(c(array));
    }
    
    public static final String a(final byte[] array, final byte[] array2) {
        return b(b(array, array2));
    }
    
    public static final String a(final String s) {
        try {
            return b(c(s.getBytes("8859_1")));
        }
        catch (UnsupportedEncodingException ex) {
            throw new Error(ex.toString());
        }
    }
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
