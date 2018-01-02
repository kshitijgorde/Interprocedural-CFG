// 
// Decompiled by Procyon v0.5.30
// 

package irc.plugin;

import irc.Server;
import irc.Source;
import irc.IRCApplication;
import irc.IRCConfiguration;

public abstract class Plugin
{
    protected IRCConfiguration _ircConfiguration;
    protected IRCApplication _ircApplication;
    
    public Plugin(final IRCConfiguration ircConfiguration) {
        this._ircConfiguration = ircConfiguration;
    }
    
    public void setIRCApplication(final IRCApplication ircApplication) {
        this._ircApplication = ircApplication;
    }
    
    public void load() {
    }
    
    public void unload() {
        this._ircConfiguration = null;
        this._ircApplication = null;
    }
    
    public void sourceCreated(final Source source, final Boolean b) {
    }
    
    public void sourceRemoved(final Source source) {
    }
    
    public void serverCreated(final Server server) {
    }
    
    public void serverConnected(final Server server) {
    }
    
    public void serverDisconnected(final Server server) {
    }
    
    public void serverRemoved(final Server server) {
    }
    
    public void externalEvent(final Object o) {
    }
    
    public Object getValue(final Object o) {
        return null;
    }
}
