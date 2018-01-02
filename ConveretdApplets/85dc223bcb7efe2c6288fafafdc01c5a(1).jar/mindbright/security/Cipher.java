// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public abstract class Cipher
{
    public static Cipher getInstance(final String algorithm) {
        try {
            final Class c = Class.forName("mindbright.security." + algorithm);
            return c.newInstance();
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public byte[] encrypt(final byte[] src) {
        final byte[] dest = new byte[src.length];
        this.encrypt(src, 0, dest, 0, src.length);
        return dest;
    }
    
    public abstract void encrypt(final byte[] p0, final int p1, final byte[] p2, final int p3, final int p4);
    
    public byte[] decrypt(final byte[] src) {
        final byte[] dest = new byte[src.length];
        this.decrypt(src, 0, dest, 0, src.length);
        return dest;
    }
    
    public abstract void decrypt(final byte[] p0, final int p1, final byte[] p2, final int p3, final int p4);
    
    public abstract void setKey(final byte[] p0);
    
    public void setKey(final String key) {
        final byte[] mdKey = new byte[32];
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(key.getBytes());
            final byte[] digest = md5.digest();
            System.arraycopy(digest, 0, mdKey, 0, 16);
            System.arraycopy(digest, 0, mdKey, 16, 16);
        }
        catch (Exception e) {
            System.out.println("MD5 not implemented, can't generate key out of string!");
            System.exit(1);
        }
        this.setKey(mdKey);
    }
}
