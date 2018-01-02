// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import com.pchat.sc.MsgOptions;

public interface PrivInter
{
    void setPara(final AppletSpice p0);
    
    void receivedPrivate(final String p0, final String p1, final String p2, final String p3, final MsgOptions p4);
    
    void receivedSelfPrivate(final String p0, final String p1, final String p2, final String p3, final MsgOptions p4);
    
    void receivedPrivSound(final String p0, final String p1, final String p2);
    
    void initPrivate(final String p0, final String p1, final String p2);
    
    void initAv(final String p0, final String p1, final String p2);
    
    void showTyping(final String p0);
    
    void checkTyping();
    
    void joinLeave(final String p0, final boolean p1);
    
    void receivedAvReq(final String p0, final String p1, final String p2);
    
    void receivedAvReject(final String p0, final String p1);
    
    void receivedAvWeb(final String p0, final String p1, final String p2);
    
    void broadcast(final String p0, final MsgOptions p1);
    
    void info(final String p0, final String p1, final String p2, final MsgOptions p3);
    
    void warnOnce(final String p0, final String p1, final String p2, final MsgOptions p3);
    
    void clearAll();
}
