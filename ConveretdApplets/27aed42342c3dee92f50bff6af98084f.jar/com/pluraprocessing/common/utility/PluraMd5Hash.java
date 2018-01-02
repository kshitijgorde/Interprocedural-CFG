// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class PluraMd5Hash
{
    public static String getMd5HashedString(final String input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(input.getBytes());
        final byte[] messageDigest = md.digest();
        final StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; ++i) {
            final String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
