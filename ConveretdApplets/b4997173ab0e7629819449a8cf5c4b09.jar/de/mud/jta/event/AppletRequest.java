// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import java.applet.Applet;
import de.mud.jta.PluginMessage;

public class AppletRequest implements PluginMessage
{
    protected Applet applet;
    
    public AppletRequest(final Applet applet) {
        this.applet = applet;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof AppletListener) {
            ((AppletListener)pl).setApplet(this.applet);
        }
        return null;
    }
}
