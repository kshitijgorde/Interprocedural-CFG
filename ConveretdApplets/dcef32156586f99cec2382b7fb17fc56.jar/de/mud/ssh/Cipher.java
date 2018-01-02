// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

public abstract class Cipher
{
    public static Cipher getInstance(final String algorithm) {
        try {
            final Class c = Class.forName("de.mud.ssh." + algorithm);
            return c.newInstance();
        }
        catch (Throwable t) {
            System.err.println("Cipher: unable to load instance of '" + algorithm + "'");
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
        this.setKey(key.getBytes());
    }
}
