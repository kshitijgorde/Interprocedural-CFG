// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.security.MessageDigest;
import java.io.IOException;
import com.postx.security.SecurityUtils;
import java.security.DigestInputStream;
import java.io.InputStream;

public class HashVerificationInputStream extends InputStream
{
    public static final String Ident = "$Id: HashVerificationInputStream.java,v 1.3 2011/01/10 05:13:52 blm Exp $";
    private DigestInputStream src;
    private byte[] embeddedHash;
    
    public HashVerificationInputStream(final InputStream inputStream, final String s) throws IOException {
        this.src = null;
        final MessageDigest messageDigest = SecurityUtils.getMessageDigest(s);
        if (messageDigest == null) {
            throw new IOException("Don't have " + s + " MessageDigest");
        }
        final byte[] embeddedHash = new byte[messageDigest.digest().length];
        int read;
        for (int length = embeddedHash.length, n = 0; n < length && (read = inputStream.read(embeddedHash, n, length - n)) != -1; n += read) {}
        this.src = new DigestInputStream(inputStream, messageDigest);
        this.embeddedHash = embeddedHash;
    }
    
    public int read() throws IOException {
        final int read = this.src.read();
        if (read == -1) {
            return this.checkHash();
        }
        return read;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this.src.read(array, n, n2);
        if (read == -1) {
            return this.checkHash();
        }
        return read;
    }
    
    private int checkHash() throws IOException {
        if (!MessageDigest.isEqual(this.embeddedHash, this.src.getMessageDigest().digest())) {
            throw new BadHashException("Hashes don't match");
        }
        return -1;
    }
}
