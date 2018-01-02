import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class catch extends MouseAdapter implements MouseMotionListener
{
    final s x;
    
    catch(final s x) {
        this.x = x;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final case _ = s._(this.x);
        s._(this.x, s._(this.x, mouseEvent));
        if (s._(this.x) != null) {
            this.x.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.x.setCursor(Cursor.getPredefinedCursor(0));
        }
        if (_ != s._(this.x)) {
            this.x.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (s._(this.x) != null && s._(this.x) != null) {
            final String a = s._(this.x).a();
            if (a != null) {
                s._(this.x).actionPerformed(new ActionEvent(this, 1001, a));
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.x.b(true);
        s._(this.x, (case)null);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.x.b(false);
        s._(this.x, s._(this.x, mouseEvent));
        if (s._(this.x) != null) {
            this.x.setCursor(Cursor.getPredefinedCursor(12));
            this.x.repaint();
        }
    }
}
