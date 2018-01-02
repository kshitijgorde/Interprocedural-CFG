// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.plugin;

public interface PluginAPI
{
    String getCurrentRoom();
    
    String[] getUsersForCurrentRoom();
    
    void requestProfile(final Plugin p0, final String p1);
    
    void sendPrivateMessage(final String p0, final String p1);
    
    void sendMessage(final String p0);
    
    void sendPluginStringMessage(final String p0, final String p1, final String p2);
    
    void sendPluginInviteMessage(final String p0, final String p1, final String p2, final String p3);
    
    void sendPluginBytesMessage(final String p0, final String p1, final byte[] p2) throws IllegalArgumentException;
    
    void removePlugin(final String p0);
}
