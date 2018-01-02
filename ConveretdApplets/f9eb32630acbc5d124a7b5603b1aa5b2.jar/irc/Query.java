// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Locale;

public class Query extends IRCSource implements ReplyServerListener
{
    private String _nick;
    private String _whois;
    private ListenerGroup _listeners;
    
    public Query(final IRCConfiguration ircConfiguration, final String nick, final IRCServer ircServer) {
        super(ircConfiguration, ircServer);
        ircServer.addReplyServerListener(this);
        this._listeners = new ListenerGroup();
        this._nick = nick;
        this._whois = "";
        if (super._ircConfiguration.getASLMaster()) {
            this.getIRCServer().execute("WHOIS " + this._nick);
        }
        this.setInterpretor(new QueryInterpretor(ircConfiguration));
    }
    
    public void release() {
        ((IRCServer)super._server).removeReplyServerListener(this);
        super.release();
    }
    
    public void addQueryListener(final QueryListener queryListener) {
        this._listeners.addListener(queryListener);
    }
    
    public void removeQueryListeners(final QueryListener queryListener) {
        this._listeners.removeListener(queryListener);
    }
    
    public String getType() {
        return "Query";
    }
    
    public String getName() {
        return this._nick;
    }
    
    public String getWhois() {
        return this._whois;
    }
    
    public boolean talkable() {
        return true;
    }
    
    public void leave() {
        this.getIRCServer().leaveQuery(this.getName());
    }
    
    public void changeNick(final String nick) {
        this._nick = nick;
        this._listeners.sendEvent("nickChanged", nick, this);
    }
    
    public Boolean replyReceived(final String s, final String s2, final String[] array, final IRCServer ircServer) {
        if (s2.equals("311") && array[1].toLowerCase(Locale.ENGLISH).equals(this._nick.toLowerCase(Locale.ENGLISH))) {
            this._whois = array[array.length - 1];
            this._listeners.sendEvent("whoisChanged", this._whois, this);
        }
        return Boolean.FALSE;
    }
}
