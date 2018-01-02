// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

public interface PrivateInter
{
    void info(final String p0, final String p1, final String p2);
    
    void warnOnce(final String p0, final String p1, final String p2);
    
    void setPara(final BaseChat p0);
    
    boolean doReceivedPrivate(final String p0, final String p1, final String p2);
    
    boolean doSelfPrivate(final String p0, final String p1, final String p2);
    
    boolean addPrivate(final String p0, final String p1);
    
    void joinLeave(final String p0, final boolean p1);
    
    void receivedAvReq(final String p0, final String p1, final String p2);
    
    void receivedAvReject(final String p0, final String p1);
    
    void receivedAvWeb(final String p0, final String p1, final String p2);
    
    void broadcast(final String p0);
    
    void clearAll();
}
