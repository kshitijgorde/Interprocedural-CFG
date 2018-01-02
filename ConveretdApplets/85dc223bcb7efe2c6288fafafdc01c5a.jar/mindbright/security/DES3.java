// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class DES3 extends Cipher
{
    DES des1;
    DES des2;
    DES des3;
    
    public DES3() {
        this.des1 = new DES();
        this.des2 = new DES();
        this.des3 = new DES();
    }
    
    public synchronized void encrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        this.des1.encrypt(src, srcOff, dest, destOff, len);
        this.des2.decrypt(dest, destOff, dest, destOff, len);
        this.des3.encrypt(dest, destOff, dest, destOff, len);
    }
    
    public synchronized void decrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        this.des3.decrypt(src, srcOff, dest, destOff, len);
        this.des2.encrypt(dest, destOff, dest, destOff, len);
        this.des1.decrypt(dest, destOff, dest, destOff, len);
    }
    
    public void setKey(final byte[] key) {
        final byte[] subKey = new byte[8];
        this.des1.setKey(key);
        System.arraycopy(key, 8, subKey, 0, 8);
        this.des2.setKey(subKey);
        System.arraycopy(key, 16, subKey, 0, 8);
        this.des3.setKey(subKey);
    }
}
