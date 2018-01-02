// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.F;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class B
{
    public static String A(final String s) throws Exception {
        return A(s, 0L, -1L);
    }
    
    public static String A(final String s, final long n, final long n2) throws Exception {
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        final byte[] array = new byte[16384];
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream = new FileInputStream(s));
            if (n > 0L) {
                bufferedInputStream.skip(n);
            }
            long n3 = (n2 < 1L) ? Long.MAX_VALUE : n2;
            int read;
            for (int n4 = (int)((n3 > array.length) ? array.length : n3); n3 > 0L && (read = bufferedInputStream.read(array, 0, n4)) != -1; n4 = (int)n3) {
                instance.update(array, 0, read);
                n3 -= read;
                if (n3 < n4) {}
            }
        }
        finally {
            try {
                bufferedInputStream.close();
            }
            catch (Exception ex) {}
            try {
                inputStream.close();
            }
            catch (Exception ex2) {}
        }
        final byte[] digest = instance.digest();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            final String hexString = Integer.toHexString(digest[i] & 0xFF);
            if (hexString.length() < 2) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
