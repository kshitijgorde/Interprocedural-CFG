// 
// Decompiled by Procyon v0.5.30
// 

package irc.gui;

import java.awt.Component;
import irc.Source;
import irc.EventDispatcher;
import irc.NullInterpretor;
import irc.IRCConfiguration;
import irc.Interpretor;
import irc.ListenerGroup;
import irc.plugin.Plugin;

public abstract class IRCInterface extends Plugin
{
    protected ListenerGroup _listenerGroup;
    private Interpretor _nullInterpretor;
    
    public IRCInterface(final IRCConfiguration ircConfiguration) {
        super(ircConfiguration);
        this._listenerGroup = new ListenerGroup();
        this._nullInterpretor = new NullInterpretor(super._ircConfiguration);
    }
    
    protected void triggerActiveChanged(final GUISource guiSource) {
        if (EventDispatcher.isEventThread()) {
            this._listenerGroup.sendEvent("activeChanged", guiSource, this);
        }
        else {
            this._listenerGroup.sendEventAsync("activeChanged", guiSource, this);
        }
    }
    
    public void addIRCInterfaceListener(final IRCInterfaceListener ircInterfaceListener) {
        this._listenerGroup.addListener(ircInterfaceListener);
    }
    
    public void removeIRCInterfaceListener(final IRCInterfaceListener ircInterfaceListener) {
        this._listenerGroup.removeListener(ircInterfaceListener);
    }
    
    public GUISource getActive() {
        return null;
    }
    
    public void setActive(final GUISource guiSource) {
    }
    
    public GUISource getGUISource(final Source source) {
        return null;
    }
    
    public Interpretor getInterpretor() {
        return this._nullInterpretor;
    }
    
    public Component getComponent() {
        return null;
    }
}
