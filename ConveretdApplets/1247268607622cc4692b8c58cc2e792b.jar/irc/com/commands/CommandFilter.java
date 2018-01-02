// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.commands;

import irc.com.messages.MircMessage;
import irc.com.messages.Message;
import irc.com.ServerThread;

public class CommandFilter
{
    private ServerThread server;
    
    public CommandFilter(final ServerThread server) {
        this.server = server;
    }
    
    public boolean filteredCompletely(final Message message) {
        try {
            final String lowerCase = message.getCommand().toLowerCase();
            final String[] parameters = message.getParameters();
            final Integer n = ServerCommands.getCommands().get(lowerCase);
            if (n == null) {
                return false;
            }
            switch (n) {
                case -1: {
                    this.sendMessage("PONG", new String[] { parameters[0] });
                    return true;
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public void sendMessage(final String s, final String[] array) {
        this.server.enqueueMessage(new MircMessage(s, array));
    }
}
