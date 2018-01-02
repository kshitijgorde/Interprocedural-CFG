// 
// Decompiled by Procyon v0.5.30
// 

package com.fsecure.launchpoint;

import java.io.FileInputStream;
import java.security.MessageDigest;

class JLaunchPointCheckSum
{
    private static final String SHA1 = "SHA-1";
    
    public final boolean isCheckSumMatch(final String s, final String s2) {
        return s != null && !"".equals(s) && (s2 != null && !"".equals(s2)) && s.equalsIgnoreCase(s2);
    }
    
    public final String getCheckSum(final String s) {
        try {
            if (s != null && !"".equals(s)) {
                final MessageDigest instance = MessageDigest.getInstance("SHA-1");
                final FileInputStream fileInputStream = new FileInputStream(s);
                final byte[] array = new byte[1024];
                int read;
                while ((read = fileInputStream.read(array)) != -1) {
                    instance.update(array, 0, read);
                }
                final byte[] digest = instance.digest();
                final StringBuffer sb = new StringBuffer("");
                for (int i = 0; i < digest.length; ++i) {
                    sb.append(Integer.toString((digest[i] & 0xFF) + 256, 16).substring(1));
                }
                return sb.toString();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
