// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

class IUnknownReference extends PeerReference
{
    @Override
    protected final native long releasePeer(final long p0);
    
    IUnknownReference(final IUnknown unknown, final jaa jaa) {
        super(unknown, jaa);
    }
}
