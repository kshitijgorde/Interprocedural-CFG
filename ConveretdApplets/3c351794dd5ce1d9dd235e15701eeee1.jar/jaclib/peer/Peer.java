// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

abstract class Peer
{
    protected PeerReference reference;
    static Class a;
    
    private static final native void init(final Class p0);
    
    protected long a() {
        long a;
        try {
            a = this.reference.a(0);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return a;
    }
    
    public final boolean a(final int n) {
        boolean b;
        try {
            if (n != 25759) {
                this.a();
            }
            b = this.reference.b(n - 25881);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    static Class a(final String s) {
        Class<?> forName;
        try {
            forName = Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
        return forName;
    }
    
    static {
        init((Peer.a == null) ? (Peer.a = a("jaclib.peer.PeerReference")) : Peer.a);
    }
}
