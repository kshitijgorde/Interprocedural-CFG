import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class S extends MouseAdapter
{
    private final H getX;
    
    S(final H getX) {
        this.getX = getX;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final float n = (mouseEvent.getX() - H.Z(this.getX).Z()) / H.Z(this.getX).I();
        H.Z(this.getX).I(n);
        H.I(this.getX).set_volume(n);
    }
}
