// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import java.util.Vector;

public interface IMixCipher
{
    byte[] encrypt(final byte[] p0, final int p1, final Vector p2);
    
    void decrypt(final byte[] p0);
    
    int getNextPacketEncryptionOverhead();
}
