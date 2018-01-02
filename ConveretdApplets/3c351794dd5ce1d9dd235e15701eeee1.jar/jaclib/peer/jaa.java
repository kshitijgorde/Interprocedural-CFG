// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public final class jaa
{
    ReferenceQueue a;
    private PeerReference b;
    private PeerReference c;
    
    public jaa() {
        this.a = new ReferenceQueue();
    }
    
    public final void a(final byte b) {
        try {
            this.a(-16615);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final void a(final PeerReference peerReference, final byte b) {
        try {
            peerReference.a(0);
            if (peerReference == this.c) {
                this.c = peerReference.a;
            }
            if (this.b == peerReference) {
                this.b = peerReference.b;
            }
            if (peerReference.a != null) {
                peerReference.a.b = peerReference.b;
            }
            if (peerReference.b != null) {
                peerReference.b.a = peerReference.a;
            }
            if (b != 69) {
                this.b = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void b(final byte b) {
        try {
            if (b >= -122) {
                this.b((byte)116);
            }
            this.a(-16615);
            while (null != this.b) {
                this.a(this.b, (byte)69);
            }
            this.a(-16615);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void a(final PeerReference b, final int n) {
        try {
            b.b = this.b;
            b.a = null;
            if (null != this.c) {
                this.b.a = b;
            }
            else {
                this.c = b;
            }
            this.b = b;
            if (n != -31112) {
                this.a(null, 1);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final void a(final int n) {
        try {
            while (true) {
                final Reference poll = this.a.poll();
                if (poll == null) {
                    break;
                }
                this.a((PeerReference)poll, (byte)69);
            }
            if (n != -16615) {
                this.a(null, (byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
