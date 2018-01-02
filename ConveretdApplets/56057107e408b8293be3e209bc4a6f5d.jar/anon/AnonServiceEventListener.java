// 
// Decompiled by Procyon v0.5.30
// 

package anon;

public interface AnonServiceEventListener
{
    void connectionError();
    
    void disconnected();
    
    void connecting(final AnonServerDescription p0);
    
    void connectionEstablished(final AnonServerDescription p0);
    
    void packetMixed(final long p0);
    
    void dataChainErrorSignaled();
}
