// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

public final class NONE extends Cipher
{
    public void setKey(final String skey) {
    }
    
    public void setKey(final byte[] key) {
    }
    
    public synchronized void encrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        System.arraycopy(src, srcOff, dest, destOff, len);
    }
    
    public synchronized void decrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        System.arraycopy(src, srcOff, dest, destOff, len);
    }
}
