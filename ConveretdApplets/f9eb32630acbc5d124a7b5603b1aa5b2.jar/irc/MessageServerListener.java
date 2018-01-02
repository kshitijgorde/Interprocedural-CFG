// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface MessageServerListener
{
    Boolean messageReceived(final String p0, final String p1, final String[] p2, final IRCServer p3);
}
