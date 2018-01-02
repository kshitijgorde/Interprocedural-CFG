// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ci
{
    public static ci ne(final String s) {
        try {
            return (ci)Class.forName("mindbright.security." + s).newInstance();
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public final byte[] nb(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        this.nb(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public abstract void nb(final byte[] p0, final int p1, final byte[] p2, final int p3, final int p4);
    
    public final byte[] na(final byte[] array) {
        final byte[] array2 = new byte[array.length];
        this.na(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public abstract void na(final byte[] p0, final int p1, final byte[] p2, final int p3, final int p4);
    
    public abstract void m9(final byte[] p0);
    
    public void m9(final String s) {
        final byte[] array = new byte[32];
        try {
            final ch ne = ch.ne("MD5");
            ne.c3(s.getBytes());
            final byte[] nd = ne.nd();
            System.arraycopy(nd, 0, array, 0, 16);
            System.arraycopy(nd, 0, array, 16, 16);
        }
        catch (Exception ex) {
            System.out.println("MD5 not implemented, can't generate key out of string!");
            System.exit(1);
        }
        this.m9(array);
    }
}
