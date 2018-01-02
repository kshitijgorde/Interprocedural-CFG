// 
// Decompiled by Procyon v0.5.30
// 

package irc.ident;

import irc.IRCConfiguration;
import irc.ident.prv.IdentServer;
import irc.IRCObject;

public class IdentWrapper extends IRCObject
{
    private IdentServer _ident;
    private IdentListener _lis;
    
    public IdentWrapper(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
    }
    
    public Exception start(final String s, final IdentListener lis) {
        this._lis = lis;
        (this._ident = new IdentServer(super._ircConfiguration)).addIdentListener(lis);
        String s2 = super._ircConfiguration.getS("userid");
        if (s2.length() == 0) {
            s2 = s;
        }
        this._ident.setDefaultUser("JAVA", s2);
        try {
            this._ident.start(113);
            return null;
        }
        catch (Exception ex) {
            return ex;
        }
    }
    
    public void stop() {
        this._ident.removeIdentListener(this._lis);
        this._ident.stop();
        this._lis = null;
    }
}
