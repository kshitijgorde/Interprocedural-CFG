// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.misc;

public abstract class SimplePeer
{
    private long peer;
    
    protected abstract void clear();
    
    public final void a() {
        try {
            if (!this.b()) {
                this.clear();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            if (!this.b()) {
                this.a();
            }
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final native void init();
    
    private final void setPeer(final long peer) {
        try {
            this.peer = peer;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final boolean b() {
        try {
            return ~this.peer == -1L;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        init();
    }
}
