// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

import java.util.EventListener;

public interface ConnectorListener extends EventListener
{
    void messageReceived(final ConnectorMessageEvent p0);
    
    void messageSent(final ConnectorMessageEvent p0);
    
    void statusChanged(final ConnectorStatusEvent p0);
}
