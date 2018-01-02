import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class K extends MouseAdapter
{
    private /* synthetic */ b a;
    
    private K(final b a, final byte b) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.a.setFocusable(true);
        this.a.requestFocus();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.a.setFocusable(true);
        this.a.requestFocus();
        if (mouseEvent.getX() >= 0 && mouseEvent.getY() >= 0 && mouseEvent.getX() < 256 && mouseEvent.getY() < 240 && this.a.a != null && this.a.a.h != null) {
            this.a.a.h.a(true, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.a.a != null && this.a.a.h != null) {
            this.a.a.h.a(false, 0, 0);
        }
    }
}
