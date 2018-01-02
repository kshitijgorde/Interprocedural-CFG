// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public abstract class IRCSource extends Source
{
    public IRCSource(final IRCConfiguration ircConfiguration, final IRCServer ircServer) {
        super(ircConfiguration, ircServer);
    }
    
    public IRCServer getIRCServer() {
        return (IRCServer)super._server;
    }
}
