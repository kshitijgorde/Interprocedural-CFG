// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.xmodel.log.Log;

public class Encrypted
{
    private static final String password = "1234567890123456";
    private static final String hexDigits = "0123456789abcdefABCDEF";
    private static final String transformation = "AES/CBC/PKCS5Padding";
    private static final String algorithm = "AES";
    private String content;
    static final int radix = 16;
    static final Log log;
    
    static {
        log = Log.getLog(Encrypted.class);
    }
    
    public static String encrypt(final String s) {
        return new Encrypted(s).encrypt().toString();
    }
    
    public static String decrypt(final String s) {
        return new Encrypted(s).decrypt().toString();
    }
    
    public Encrypted() {
        this("empty");
    }
    
    public Encrypted(final String content) {
        this.content = null;
        this.content = content;
    }
    
    public Encrypted setContent(final String s) {
        this.content = s;
        return this;
    }
    
    public String encrypt() {
        final byte[] bytes = this.content.getBytes();
        try {
            final SecretKey key = new SecretKeySpec(this.secret(), "AES");
            final Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(1, key, new SecureRandom(SecureRandom.getSeed(16)));
            final String encoded = this.encode(c.doFinal(bytes));
            final String iv = this.encode(c.getIV());
            return String.valueOf(iv) + encoded;
        }
        catch (Exception e) {
            Encrypted.log.fatal(bytes, e);
            return null;
        }
    }
    
    public String decrypt() {
        final byte[] bytes = this.content.getBytes();
        try {
            final SecretKey key = new SecretKeySpec(this.secret(), "AES");
            final Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            final String iv = this.content.substring(0, 32);
            final String encoded = this.content.substring(32, this.content.length());
            c.init(2, key, new IvParameterSpec(this.decode(iv)));
            final byte[] decoded = c.doFinal(this.decode(encoded));
            return new String(decoded);
        }
        catch (Exception e) {
            Encrypted.log.fatal(bytes, e);
            return null;
        }
    }
    
    @Override
    public String toString() {
        return this.content;
    }
    
    public static void main(final String[] arg) {
        for (int i = 0; i < arg.length; ++i) {
            String s = arg[i];
            System.out.println("Input: " + s);
            if (!hexDigits(s)) {
                final Encrypted s2 = new Encrypted(arg[i]);
                s = s2.encrypt();
            }
            System.out.println("Encrypted: " + s);
            final Encrypted s3 = new Encrypted(s);
            s = s3.decrypt();
            System.out.println("Decrypted: " + s + "\n");
        }
    }
    
    private static boolean hexDigits(final String s) {
        boolean result = true;
        for (int i = 0; i < s.length(); ++i) {
            if ("0123456789abcdefABCDEF".indexOf(s.charAt(i)) == -1) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    private String encode(final byte[] bytes) {
        final StringBuilder sb = new StringBuilder();
        for (final byte b : bytes) {
            final String d = Integer.toString(256 + (b & 0xFF), 16);
            sb.append(d.substring(1));
        }
        return sb.toString().toLowerCase();
    }
    
    private byte[] decode(final String hex) {
        final byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; ++i) {
            bts[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }
    
    private byte[] secret() {
        return "1234567890123456".getBytes();
    }
}
