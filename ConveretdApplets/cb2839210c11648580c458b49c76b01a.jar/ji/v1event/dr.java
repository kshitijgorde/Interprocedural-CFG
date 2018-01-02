// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.awt.event.MouseEvent;
import ji.awt.c;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class dr implements MouseListener, MouseMotionListener
{
    private c a;
    private c b;
    
    public dr() {
        this.a = new c("jiMouseMonitor1");
        this.b = new c("jiMouseMonitor2");
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.a.b(); ++i) {
                ((MouseListener)this.a.b(i)).mouseReleased(mouseEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.a.b(); ++i) {
                ((MouseListener)this.a.b(i)).mousePressed(mouseEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.a.b(); ++i) {
                ((MouseListener)this.a.b(i)).mouseEntered(mouseEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.a.b(); ++i) {
                ((MouseListener)this.a.b(i)).mouseExited(mouseEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.a.b(); ++i) {
                if (this.a.b(i) instanceof MouseMotionListener) {
                    ((MouseListener)this.a.b(i)).mouseClicked(mouseEvent);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.b.b(); ++i) {
                ((MouseMotionListener)this.b.b(i)).mouseMoved(mouseEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        try {
            for (int i = 0; i < this.b.b(); ++i) {
                ((MouseMotionListener)this.b.b(i)).mouseDragged(mouseEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final MouseListener mouseListener) {
        try {
            if (!this.a.a(mouseListener)) {
                this.a.c(mouseListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public void b(final MouseListener mouseListener) {
        try {
            if (this.a.a(mouseListener)) {
                this.a.b(mouseListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final MouseMotionListener mouseMotionListener) {
        try {
            if (!this.b.a(mouseMotionListener)) {
                this.b.c(mouseMotionListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public void b(final MouseMotionListener mouseMotionListener) {
        try {
            if (this.b.a(mouseMotionListener)) {
                this.b.b(mouseMotionListener);
            }
        }
        catch (Exception ex) {}
    }
}
