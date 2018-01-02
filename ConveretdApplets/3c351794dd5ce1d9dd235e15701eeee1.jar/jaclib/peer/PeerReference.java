// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class PeerReference extends WeakReference
{
    PeerReference a;
    private long peer;
    PeerReference b;
    
    final long a(final int n) {
        long n2;
        try {
            if (n != 0) {
                this.setPeer(26L);
            }
            long releasePeer;
            if (-1L != ~this.peer) {
                releasePeer = this.releasePeer(this.peer);
                this.peer = 0L;
            }
            else {
                releasePeer = 0L;
            }
            n2 = releasePeer;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n2;
    }
    
    PeerReference(final Peer peer, final jaa jaa) {
        super(peer, jaa.a);
        try {
            jaa.a(this, -31112);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void setPeer(final long peer) {
        try {
            this.a(0);
            this.peer = peer;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final boolean b(final int n) {
        boolean b;
        try {
            if (n >= -108) {
                this.b = null;
            }
            if (0L != this.peer) {
                return true;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    protected abstract long releasePeer(final long p0);
}
