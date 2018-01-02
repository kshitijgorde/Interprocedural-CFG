// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class NoEncrypt extends Cipher
{
    public void encrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        System.arraycopy(src, srcOff, dest, destOff, len);
    }
    
    public void decrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        System.arraycopy(src, srcOff, dest, destOff, len);
    }
    
    public void setKey(final byte[] key) {
    }
}
