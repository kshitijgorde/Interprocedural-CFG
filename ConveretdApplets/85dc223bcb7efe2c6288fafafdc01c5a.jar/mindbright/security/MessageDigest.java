// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public abstract class MessageDigest
{
    public static boolean useNative;
    
    public static MessageDigest getInstance(final String algorithm) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (MessageDigest.useNative) {
            try {
                final Class c1 = Class.forName("java.security.MessageDigest");
                final Class c2 = Class.forName("mindbright.security.NativeHashState");
                final MessageDigest md = c2.newInstance();
                md.init(algorithm);
                return md;
            }
            catch (Throwable t) {}
        }
        final Class c3 = Class.forName("mindbright.security." + algorithm);
        return c3.newInstance();
    }
    
    protected void init(final String algorithm) throws Exception {
    }
    
    public abstract String getName();
    
    public abstract void reset();
    
    public abstract void update(final byte[] p0, final int p1, final int p2);
    
    public abstract byte[] digest();
    
    public abstract int blockSize();
    
    public abstract int hashSize();
    
    public final void update(final byte[] buf) {
        this.update(buf, 0, buf.length);
    }
    
    public int digestInto(final byte[] dest, final int destOff) {
        final byte[] dig = this.digest();
        System.arraycopy(dig, 0, dest, destOff, dig.length);
        return dig.length;
    }
    
    public Object clone() throws CloneNotSupportedException {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }
    
    static {
        MessageDigest.useNative = false;
    }
}
