// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class IRCObject
{
    protected IRCConfiguration _ircConfiguration;
    
    public IRCObject(final IRCConfiguration ircConfiguration) {
        this._ircConfiguration = ircConfiguration;
    }
    
    public IRCConfiguration getIRCConfiguration() {
        return this._ircConfiguration;
    }
    
    public void release() {
    }
    
    public String getText(final int n) {
        return this._ircConfiguration.getText(n);
    }
    
    public String getText(final int n, final String s) {
        return this._ircConfiguration.getText(n, s);
    }
    
    public String getText(final int n, final String s, final String s2) {
        return this._ircConfiguration.getText(n, s, s2);
    }
    
    public String getText(final int n, final String s, final String s2, final String s3) {
        return this._ircConfiguration.getText(n, s, s2, s3);
    }
}
