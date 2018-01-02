// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Date;

public class Status extends IRCSource implements ReplyServerListener
{
    private ListenerGroup _listeners;
    
    public Status(final IRCConfiguration ircConfiguration, final IRCServer ircServer) {
        super(ircConfiguration, ircServer);
        ircServer.addReplyServerListener(this);
        this._listeners = new ListenerGroup();
        this.setInterpretor(new StatusInterpretor(ircConfiguration));
    }
    
    public void release() {
        ((IRCServer)super._server).removeReplyServerListener(this);
        super.release();
    }
    
    public String getType() {
        return "Status";
    }
    
    public String getName() {
        return this.getServerName();
    }
    
    public String getServerName() {
        return this.getIRCServer().getServerName();
    }
    
    public boolean talkable() {
        return false;
    }
    
    public void leave() {
        if (!super._ircConfiguration.getB("multiserver")) {
            return;
        }
        this.getIRCServer().leaveStatus(this.getName());
    }
    
    public String getNick() {
        return super._server.getNick();
    }
    
    public String getMode() {
        return this.getIRCServer().getMode();
    }
    
    public void addStatusListener(final StatusListener statusListener) {
        this._listeners.addListener(statusListener);
    }
    
    public void removeStatusListener(final StatusListener statusListener) {
        this._listeners.removeListener(statusListener);
    }
    
    public void nickChanged(final String s) {
        this._listeners.sendEvent("nickChanged", s, this);
    }
    
    public void modeChanged(final String s) {
        this._listeners.sendEvent("modeChanged", s, this);
    }
    
    public void invited(final String s, final String s2) {
        this._listeners.sendEvent("invited", s, s2, this);
    }
    
    public Boolean replyReceived(final String s, final String s2, final String[] array, final IRCServer ircServer) {
        if (s2.equals("322")) {
            return Boolean.FALSE;
        }
        if (super._ircConfiguration.getB("useinfo")) {
            final int intValue = new Integer(s2);
            if (intValue >= 300 && intValue != 372) {
                return Boolean.FALSE;
            }
        }
        if (s2.equals("401")) {
            final Source defaultSource = this.getIRCServer().getDefaultSource();
            String string = "";
            for (int i = 1; i < array.length; ++i) {
                string = string + " " + array[i];
            }
            final String substring = string.substring(1);
            if (defaultSource != null) {
                defaultSource.report(substring);
            }
        }
        else if (s2.equals("317")) {
            if (array.length > 3) {
                final long long1 = Long.parseLong(array[2]);
                final long n = long1 % 60L;
                final long n2 = long1 / 60L % 60L;
                final long n3 = long1 / 3600L % 24L;
                final long n4 = long1 / 86400L % 7L;
                final long n5 = long1 / 604800L;
                String s3 = "";
                if (long1 > 604800L) {
                    s3 = s3 + n5 + " weeks ";
                }
                if (long1 > 86400L) {
                    s3 = s3 + n4 + " days ";
                }
                if (long1 > 3600L) {
                    s3 = s3 + n3 + " hours ";
                }
                if (long1 > 60L) {
                    s3 = s3 + n2 + " minutes ";
                }
                final String trim = (s3 + n + " seconds").trim();
                final String string2 = new Date(1000L * Long.parseLong(array[3])).toString();
                this.report(super._ircConfiguration.getText(2305, array[1], trim));
                this.report(super._ircConfiguration.getText(2306, array[1], string2));
            }
        }
        else {
            String string3 = "";
            for (int j = 1; j < array.length; ++j) {
                string3 = string3 + " " + array[j];
            }
            this.report(string3.substring(1));
        }
        return Boolean.FALSE;
    }
}
