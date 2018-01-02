// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

public interface IServerManager
{
    Object getId();
    
    void startServerManager(final ForwardScheduler p0) throws Exception;
    
    void shutdown();
}
