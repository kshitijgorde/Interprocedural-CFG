// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ch
{
    public static boolean m7;
    
    public static ch ne(final String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (ch.m7) {
            try {
                Class.forName("java.security.MessageDigest");
                final ch ch = (ch)Class.forName("mindbright.security.NativeHashState").newInstance();
                ch.i3(s);
                return ch;
            }
            catch (Throwable t) {}
        }
        return (ch)Class.forName("mindbright.security." + s).newInstance();
    }
    
    public void i3(final String s) throws Exception {
    }
    
    public abstract void c3(final byte[] p0, final int p1, final int p2);
    
    public abstract byte[] nd();
    
    public final void c3(final byte[] array) {
        this.c3(array, 0, array.length);
    }
    
    public Object clone() throws CloneNotSupportedException {
        if (this instanceof Cloneable) {
            return super.clone();
        }
        throw new CloneNotSupportedException();
    }
}
