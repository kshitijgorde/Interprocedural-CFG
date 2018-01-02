import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class if extends MouseAdapter implements MouseMotionListener
{
    final do fb;
    
    if(final do fb) {
        this.fb = fb;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final goto b = do.b(this.fb);
        do.a(this.fb, do.a(this.fb, mouseEvent));
        if (do.b(this.fb) != null && do.b(this.fb).b() != null) {
            this.fb.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.fb.setCursor(Cursor.getPredefinedCursor(0));
        }
        if (b != do.b(this.fb)) {
            this.fb.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (do.b(this.fb) != null && do._(this.fb) != null) {
            final String b = do.b(this.fb).b();
            if (b != null) {
                do._(this.fb).actionPerformed(new ActionEvent(this, 1001, b));
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.fb.a(true);
        do.a(this.fb, (goto)null);
        this.fb.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.fb.a(false);
        do.a(this.fb, do.a(this.fb, mouseEvent));
        if (do.b(this.fb) != null) {
            this.fb.setCursor(Cursor.getPredefinedCursor(12));
            this.fb.repaint();
        }
    }
}
