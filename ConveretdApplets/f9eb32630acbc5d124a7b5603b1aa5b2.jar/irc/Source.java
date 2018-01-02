// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Locale;

public abstract class Source extends IRCObject
{
    protected Server _server;
    private ListenerGroup _listeners;
    protected Interpretor _in;
    
    public Source(final IRCConfiguration ircConfiguration, final Server server) {
        super(ircConfiguration);
        this._listeners = new ListenerGroup();
        this._in = new NullInterpretor(ircConfiguration);
        this._server = server;
    }
    
    public void release() {
        this._in = new NullInterpretor(super._ircConfiguration);
        super.release();
    }
    
    public abstract String getName();
    
    public abstract String getType();
    
    public abstract boolean talkable();
    
    public abstract void leave();
    
    public void setInterpretor(final Interpretor in) {
        this._in = in;
    }
    
    public void sendString(final String s) {
        this._in.sendString(this, s);
    }
    
    public void sendUserString(final String s) {
        if (!s.startsWith("/")) {
            this.sendString(s);
        }
        else {
            String s2 = s.substring(1).trim();
            final int index = s2.indexOf(32);
            if (index >= 0) {
                s2 = s2.substring(0, index);
            }
            if (super._ircConfiguration.mayCommand(s2)) {
                this.sendString(s);
            }
        }
    }
    
    public Interpretor getInterpretor() {
        return this._in;
    }
    
    public void clear() {
        this._listeners.sendEvent("clear", this);
    }
    
    public void messageReceived(final String s, String s2) {
        if (s2.startsWith("\u0001")) {
            s2 = s2.substring(1);
            s2 = s2.substring(0, s2.length() - 1);
            String substring = "";
            final int index = s2.indexOf(32);
            String s3;
            if (index == -1) {
                s3 = s2.toLowerCase(Locale.ENGLISH);
            }
            else {
                s3 = s2.substring(0, index).toLowerCase(Locale.ENGLISH);
                substring = s2.substring(index + 1);
            }
            if (s3.equals("action")) {
                this.action(s, substring);
            }
            else {
                this.getServer().sendStatusMessage("\u0002\u00034[" + s + " " + s3.toUpperCase(Locale.ENGLISH) + "]");
            }
        }
        else {
            this._listeners.sendEvent("messageReceived", s, s2, this);
        }
    }
    
    public void noticeReceived(final String s, final String s2) {
        this._listeners.sendEvent("noticeReceived", s, s2, this);
    }
    
    public void action(final String s, final String s2) {
        this._listeners.sendEvent("action", s, s2, this);
    }
    
    public void report(final String s) {
        this._listeners.sendEvent("reportReceived", s, this);
    }
    
    public void addSourceListener(final SourceListener sourceListener) {
        this._listeners.addListener(sourceListener);
    }
    
    public void removeSourceListener(final SourceListener sourceListener) {
        this._listeners.removeListener(sourceListener);
    }
    
    public Server getServer() {
        return this._server;
    }
    
    public boolean mayDefault() {
        return true;
    }
}
