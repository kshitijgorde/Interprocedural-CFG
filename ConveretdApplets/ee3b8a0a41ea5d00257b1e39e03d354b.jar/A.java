import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class A extends MouseMotionAdapter
{
    private final H getX;
    
    A(final H getX) {
        this.getX = getX;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        final float n = (mouseEvent.getX() - H.Z(this.getX).Z()) / H.Z(this.getX).I();
        H.Z(this.getX).I(n);
        H.I(this.getX).set_volume(n);
    }
}
