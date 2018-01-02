// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class DefaultSource extends Source
{
    public DefaultSource(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration, new NullServer());
    }
    
    public boolean talkable() {
        return false;
    }
    
    public String getType() {
        return "Default";
    }
    
    public String getName() {
        return "Global";
    }
    
    public void leave() {
    }
}
