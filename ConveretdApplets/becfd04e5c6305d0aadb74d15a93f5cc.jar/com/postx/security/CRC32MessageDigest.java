// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.security;

import java.util.zip.CRC32;
import java.security.MessageDigest;

public final class CRC32MessageDigest extends MessageDigest
{
    public static final String Ident = "$Id: CRC32MessageDigest.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private CRC32 crc;
    
    public void engineReset() {
        this.crc.reset();
    }
    
    public CRC32MessageDigest() {
        super("CRC-32");
        this.crc = new CRC32();
    }
    
    public byte[] engineDigest() {
        final long value = this.crc.getValue();
        return new byte[] { (byte)(value >> 24), (byte)(value >> 16), (byte)(value >> 8), (byte)value };
    }
    
    public void engineUpdate(final byte b) {
        this.crc.update(b);
    }
    
    public void engineUpdate(final byte[] array, final int n, final int n2) {
        this.crc.update(array, n, n2);
    }
}
