import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class F extends MouseAdapter
{
    private final H stop_playback;
    
    F(final H stop_playback) {
        this.stop_playback = stop_playback;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        H.I(this.stop_playback).stop_playback();
    }
}
