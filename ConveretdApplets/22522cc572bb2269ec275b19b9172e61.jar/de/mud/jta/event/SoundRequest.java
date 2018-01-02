// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import java.net.URL;
import de.mud.jta.PluginMessage;

public class SoundRequest implements PluginMessage
{
    protected URL audioClip;
    
    public SoundRequest(final URL audioClip) {
        this.audioClip = audioClip;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof SoundListener) {
            ((SoundListener)pl).playSound(this.audioClip);
        }
        return null;
    }
}
