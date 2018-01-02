// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Nick
{
    public static boolean CheckNick(final String s, final String s2) throws NoSuchAlgorithmException {
        return getEncodedNick(s).equals(s2);
    }
    
    public static String getEncodedNick(final String s) {
        final byte[] bytes = s.getBytes();
        byte[] digest;
        try {
            digest = MessageDigest.getInstance("MD5").digest(bytes);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Error("no MD5 support in this VM");
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            final String hexString = Integer.toHexString(digest[i]);
            if (hexString.length() == 1) {
                sb.append('0');
                sb.append(hexString.charAt(hexString.length() - 1));
            }
            else {
                sb.append(hexString.substring(hexString.length() - 2));
            }
        }
        return sb.toString();
    }
    
    public static String getMD5_2(final String s) {
        return getEncodedNick(getEncodedNick(s));
    }
    
    public static String getMD5_3(final String s) {
        return getEncodedNick(getEncodedNick(getEncodedNick(s)));
    }
}
