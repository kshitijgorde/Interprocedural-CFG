// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.codec.digest;

import org.apache.commons.codec.binary.Hex;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class DigestUtils
{
    static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private static MessageDigest getMd5Digest() {
        return getDigest("MD5");
    }
    
    private static MessageDigest getShaDigest() {
        return getDigest("SHA");
    }
    
    public static byte[] md5(final byte[] data) {
        return getMd5Digest().digest(data);
    }
    
    public static byte[] md5(final String data) {
        return md5(data.getBytes());
    }
    
    public static String md5Hex(final byte[] data) {
        return new String(Hex.encodeHex(md5(data)));
    }
    
    public static String md5Hex(final String data) {
        return new String(Hex.encodeHex(md5(data)));
    }
    
    public static byte[] sha(final byte[] data) {
        return getShaDigest().digest(data);
    }
    
    public static byte[] sha(final String data) {
        return sha(data.getBytes());
    }
    
    public static String shaHex(final byte[] data) {
        return new String(Hex.encodeHex(sha(data)));
    }
    
    public static String shaHex(final String data) {
        return new String(Hex.encodeHex(sha(data)));
    }
}
