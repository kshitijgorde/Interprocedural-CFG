// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.security;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public final class SecurityUtils
{
    public static final String Ident = "$Id: SecurityUtils.java,v 1.3 2011/01/10 05:13:52 blm Exp $";
    
    public static MessageDigest getMessageDigest(final String s) {
        try {
            if (s.equals("CRC-32")) {
                return new CRC32MessageDigest();
            }
            return MessageDigest.getInstance(s);
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
