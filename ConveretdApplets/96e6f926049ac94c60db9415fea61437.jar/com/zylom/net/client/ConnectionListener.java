// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.util.EventListener;

public interface ConnectionListener extends EventListener
{
    void connectionOpen(final ConnectionEvent p0);
    
    void connectionClosed(final ConnectionEvent p0);
    
    void dataAvailable(final ConnectionEvent p0);
    
    void dataSend(final ConnectionEvent p0);
    
    void sendError(final ConnectionEvent p0);
    
    void readError(final ConnectionEvent p0);
}
