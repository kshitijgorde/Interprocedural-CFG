// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.security;

import com.aviva.framework.util.ConversionUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestTool
{
    private static DigestTool instance;
    private static String hashAlgo;
    private static String sha1;
    
    public String digest(final String data) {
        if (data == null) {
            return null;
        }
        return this.getHashKey(data.getBytes());
    }
    
    public String digestSHA2(final String data) {
        if (data == null) {
            return null;
        }
        return this.getHashKeySHA2(data.getBytes());
    }
    
    private synchronized String getHashKey(final byte[] byteinput) {
        MessageDigest digester = null;
        try {
            digester = MessageDigest.getInstance(DigestTool.sha1);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Not supported");
            return "";
        }
        digester.update(byteinput);
        final byte[] digest = digester.digest();
        return ConversionUtil.bytesToHex(digest);
    }
    
    private synchronized String getHashKeySHA2(final byte[] byteinput) {
        MessageDigest digester = null;
        try {
            digester = MessageDigest.getInstance(DigestTool.hashAlgo);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Not supported");
            return "";
        }
        digester.update(byteinput);
        final byte[] digest = digester.digest();
        return ConversionUtil.bytesToHex(digest);
    }
    
    public static synchronized DigestTool getInstance() {
        if (DigestTool.instance == null) {
            try {
                DigestTool.instance = new DigestTool();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return DigestTool.instance;
    }
    
    public static void main(final String[] args) {
        System.out.println(getInstance().digestSHA2("616263"));
        System.out.println(getInstance().digest("616263"));
    }
    
    static {
        DigestTool.instance = null;
        DigestTool.hashAlgo = "SHA-256";
        DigestTool.sha1 = "SHA-1";
    }
}
