// 
// Decompiled by Procyon v0.5.30
// 

package irc.plugin.test;

import irc.Source;
import irc.IRCConfiguration;
import irc.SourceListener;
import irc.plugin.Plugin;

public class Test extends Plugin implements SourceListener
{
    public Test(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
    }
    
    public void sourceCreated(final Source source, final Boolean b) {
        source.addSourceListener(this);
    }
    
    public void sourceRemoved(final Source source) {
        source.removeSourceListener(this);
    }
    
    public void messageReceived(final String s, final String s2, final Source source) {
        if (s2.startsWith("!hello")) {
            source.sendUserString("World!");
        }
    }
    
    public void reportReceived(final String s, final Source source) {
    }
    
    public void noticeReceived(final String s, final String s2, final Source source) {
    }
    
    public void action(final String s, final String s2, final Source source) {
    }
    
    public void clear(final Source source) {
    }
}
