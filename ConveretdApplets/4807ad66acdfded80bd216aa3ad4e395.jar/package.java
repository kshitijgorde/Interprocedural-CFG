import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class package extends MouseAdapter implements MouseMotionListener
{
    final implements xa;
    
    package(final implements xa) {
        this.xa = xa;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final null a = implements.a(this.xa);
        implements.b(this.xa, implements.a(this.xa, mouseEvent));
        if (implements.a(this.xa) != null) {
            this.xa.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.xa.setCursor(Cursor.getPredefinedCursor(0));
        }
        if (a != implements.a(this.xa)) {
            this.xa.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (implements.a(this.xa) != null && implements.a(this.xa) != null) {
            final String a = implements.a(this.xa).a();
            if (a != null) {
                implements.a(this.xa).actionPerformed(new ActionEvent(this, 1001, a));
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.xa.b(true);
        implements.b(this.xa, null);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.xa.b(false);
        implements.b(this.xa, implements.a(this.xa, mouseEvent));
        if (implements.a(this.xa) != null) {
            this.xa.setCursor(Cursor.getPredefinedCursor(12));
            this.xa.repaint();
        }
    }
}
