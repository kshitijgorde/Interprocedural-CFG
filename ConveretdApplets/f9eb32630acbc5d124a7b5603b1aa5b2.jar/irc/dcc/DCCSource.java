// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc;

import irc.Server;
import irc.dcc.prv.DCCChatServer;
import irc.IRCConfiguration;
import irc.Source;

public abstract class DCCSource extends Source
{
    public DCCSource(final IRCConfiguration ircConfiguration, final DCCChatServer dccChatServer) {
        super(ircConfiguration, dccChatServer);
    }
    
    public DCCChatServer getDCCChatServer() {
        return (DCCChatServer)super._server;
    }
}
