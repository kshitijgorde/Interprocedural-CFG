// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.util;

import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;

public class hash
{
    public static byte[] sha(final byte[] array) {
        final SHA1Digest sha1Digest = new SHA1Digest();
        sha1Digest.update(array, 0, array.length);
        final byte[] array2 = new byte[sha1Digest.getDigestSize()];
        sha1Digest.doFinal(array2, 0);
        return array2;
    }
    
    public static byte[] sha(final byte[] array, final byte[] array2) {
        final SHA1Digest sha1Digest = new SHA1Digest();
        sha1Digest.reset();
        sha1Digest.update(array, 0, array.length);
        sha1Digest.update(array2, 0, array2.length);
        final byte[] array3 = new byte[sha1Digest.getDigestSize()];
        sha1Digest.doFinal(array3, 0);
        return array3;
    }
    
    public static byte[] sha(final byte[] array, final byte[] array2, final byte[] array3) {
        final SHA1Digest sha1Digest = new SHA1Digest();
        sha1Digest.reset();
        sha1Digest.update(array, 0, array.length);
        sha1Digest.update(array2, 0, array2.length);
        sha1Digest.update(array3, 0, array3.length);
        final byte[] array4 = new byte[sha1Digest.getDigestSize()];
        sha1Digest.doFinal(array4, 0);
        return array4;
    }
    
    public static byte[] md5(final byte[] array) {
        final MD5Digest md5Digest = new MD5Digest();
        md5Digest.reset();
        md5Digest.update(array, 0, array.length);
        final byte[] array2 = new byte[md5Digest.getDigestSize()];
        md5Digest.doFinal(array2, 0);
        return array2;
    }
    
    public static byte[] md5(final byte[] array, final byte[] array2) {
        final MD5Digest md5Digest = new MD5Digest();
        md5Digest.reset();
        md5Digest.update(array, 0, array.length);
        md5Digest.update(array2, 0, array2.length);
        final byte[] array3 = new byte[md5Digest.getDigestSize()];
        md5Digest.doFinal(array3, 0);
        return array3;
    }
    
    public static byte[] md5(final byte[] array, final byte[] array2, final byte[] array3) {
        final MD5Digest md5Digest = new MD5Digest();
        md5Digest.reset();
        md5Digest.update(array, 0, array.length);
        md5Digest.update(array2, 0, array2.length);
        md5Digest.update(array3, 0, array3.length);
        final byte[] array4 = new byte[md5Digest.getDigestSize()];
        md5Digest.doFinal(array4, 0);
        return array4;
    }
}
