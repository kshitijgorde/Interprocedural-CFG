// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

class NativeHeapPeerReference extends PeerReference
{
    NativeHeapPeerReference(final mfa mfa, final jaa jaa) {
        super(mfa, jaa);
    }
    
    @Override
    protected final native long releasePeer(final long p0);
}
