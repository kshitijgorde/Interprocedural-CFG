import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class E extends MouseAdapter
{
    private final H Z;
    
    E(final H z) {
        this.Z = z;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (!H.I(this.Z).a && !H.I(this.Z).H && !H.I(this.Z).K && H.I(this.Z).w) {
            this.Z.Z = true;
            H.C(this.Z).I((mouseEvent.getX() - H.C(this.Z).Z()) / H.C(this.Z).I());
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (!H.I(this.Z).a && !H.I(this.Z).H && !H.I(this.Z).K && H.I(this.Z).w) {
            H.I(this.Z).I((mouseEvent.getX() - H.C(this.Z).Z()) / H.C(this.Z).I());
            this.Z.Z = false;
        }
    }
}
