// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.plugin;

import java.util.Properties;

public interface Plugin
{
    void receivePluginStringMessage(final String p0, final String p1);
    
    void receivePluginBytesMessage(final String p0, final byte[] p1);
    
    void receiveProfile(final String p0, final Properties p1);
    
    void receiveInvitationDeclined(final String p0);
    
    void receiveInvitation(final String p0);
    
    void registerAPI(final PluginAPI p0, final String p1);
    
    void closeDown();
}
