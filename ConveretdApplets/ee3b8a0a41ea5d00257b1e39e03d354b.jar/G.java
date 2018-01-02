import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class G extends MouseMotionAdapter
{
    private final H Z;
    
    G(final H z) {
        this.Z = z;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (!H.I(this.Z).a && !H.I(this.Z).H && !H.I(this.Z).K && H.I(this.Z).w) {
            this.Z.Z = true;
            H.C(this.Z).I((mouseEvent.getX() - H.C(this.Z).Z()) / H.C(this.Z).I());
        }
    }
}
