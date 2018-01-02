// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc;

import irc.Interpretor;
import irc.dcc.prv.DCCChatServer;
import irc.IRCConfiguration;

public class DCCChat extends DCCSource
{
    private String _nick;
    
    public DCCChat(final IRCConfiguration ircConfiguration, final DCCChatServer dccChatServer, final String nick) {
        super(ircConfiguration, dccChatServer);
        this._nick = nick;
        this.setInterpretor(new DCCChatInterpretor(ircConfiguration));
    }
    
    public String getType() {
        return "DCCChat";
    }
    
    public String getName() {
        return this._nick;
    }
    
    public boolean talkable() {
        return true;
    }
    
    public void leave() {
        this.getDCCChatServer().close();
        this.getDCCChatServer().leave();
    }
}
