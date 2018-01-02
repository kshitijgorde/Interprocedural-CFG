import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class synchronized extends MouseAdapter implements MouseMotionListener
{
    final private ja;
    
    synchronized(final private ja) {
        this.ja = ja;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final switch a = private.a(this.ja);
        private.b(this.ja, private.b(this.ja, mouseEvent));
        if (private.a(this.ja) != null && private.a(this.ja)._() != null) {
            this.ja.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.ja.setCursor(Cursor.getPredefinedCursor(0));
        }
        if (a != private.a(this.ja)) {
            this.ja.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (private.a(this.ja) != null && private.b(this.ja) != null) {
            final String _ = private.a(this.ja)._();
            if (_ != null) {
                private.b(this.ja).actionPerformed(new ActionEvent(this, 1001, _));
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.ja.a(true);
        private.b(this.ja, (switch)null);
        this.ja.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.ja.a(false);
        private.b(this.ja, private.b(this.ja, mouseEvent));
        if (private.a(this.ja) != null) {
            this.ja.setCursor(Cursor.getPredefinedCursor(12));
            this.ja.repaint();
        }
    }
}
