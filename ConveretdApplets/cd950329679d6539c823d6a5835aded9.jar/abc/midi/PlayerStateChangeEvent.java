// 
// Decompiled by Procyon v0.5.30
// 

package abc.midi;

import java.util.EventObject;

public class PlayerStateChangeEvent extends EventObject
{
    private boolean m_isPlaying;
    
    public PlayerStateChangeEvent(final Object source, final boolean isPlaying) {
        super(source);
        this.m_isPlaying = false;
    }
    
    public boolean isPlayerPlaying() {
        return this.m_isPlaying;
    }
}
