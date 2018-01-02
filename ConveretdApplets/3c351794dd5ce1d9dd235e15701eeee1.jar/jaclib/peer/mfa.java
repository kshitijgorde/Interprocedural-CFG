// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

public class mfa extends Peer
{
    protected mfa(final jaa jaa) {
        try {
            this.reference = new NativeHeapPeerReference(this, jaa);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
