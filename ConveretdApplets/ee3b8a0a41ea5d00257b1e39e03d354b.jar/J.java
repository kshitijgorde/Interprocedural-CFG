import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class J extends MouseAdapter
{
    private final H toggle_mute;
    
    J(final H toggle_mute) {
        this.toggle_mute = toggle_mute;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        H.I(this.toggle_mute).toggle_mute();
    }
}
