// 
// Decompiled by Procyon v0.5.30
// 

package irc.dcc;

import irc.Server;
import irc.Source;
import irc.IRCConfiguration;
import irc.BasicInterpretor;

public class DCCChatInterpretor extends BasicInterpretor
{
    public DCCChatInterpretor(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
    }
    
    protected void handleCommand(final Source source, final String s, final String[] array, final String[] array2) {
        if (s.equals("query")) {
            source.report(this.getText(4, "/query"));
        }
        else if (s.equals("ctcp")) {
            source.report(this.getText(5));
        }
        else {
            super.handleCommand(source, s, array, array2);
        }
    }
    
    protected void say(final Source source, final String s) {
        final Server server = source.getServer();
        if (source.talkable()) {
            source.messageReceived(server.getNick(), s);
            server.say(source.getName(), s);
        }
        else {
            source.report(this.getText(1));
        }
    }
}
