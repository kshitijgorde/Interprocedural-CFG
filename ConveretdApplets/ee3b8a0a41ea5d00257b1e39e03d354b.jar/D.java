import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class D extends MouseAdapter
{
    private final H Z;
    
    D(final H z) {
        this.Z = z;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (!H.I(this.Z).a) {
            H.I(this.Z).toggle_playback();
        }
    }
}
